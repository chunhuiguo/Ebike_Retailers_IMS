package com.luyuan.sys.biz.impl;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Hex;

import com.luyuan.action.IFieldValidation;
import com.luyuan.sys.biz.UsersBiz;
import com.luyuan.sys.dao.OperationDAO;
import com.luyuan.sys.dao.RoleDAO;
import com.luyuan.sys.dao.UnitDAO;
import com.luyuan.sys.dao.UserRoleDAO;
import com.luyuan.sys.dao.UsersDAO;
import com.luyuan.sys.entity.Operation;
import com.luyuan.sys.entity.Role;
import com.luyuan.sys.entity.Unit;
import com.luyuan.sys.entity.UserRole;
import com.luyuan.sys.entity.Users;

public class UsersBizImpl implements UsersBiz {

	public int authentication(IFieldValidation act, Map<String,Object> session, String userCode, String password) {
		Users user = usersDAO.findByLoginName(userCode);

		//补充检验验证码、时间戳
		
		if (user == null ) {
			act.addFieldError("user.userCode", "用户不存在");
			return 0;
		}
		if(user.getOnline()) {
			act.addFieldError("user.userCode", "该用户已经登录，您不能重复登录！");
			return 0; 
		}
        if (user.getEnabled() != true)
        {
        	act.addFieldError("user.userCode", "您的账号已无效，不能登录！");
        	return 0;
        }
		
		int uLogin = checkUser(user.getPassword(),password);
        if (uLogin == 0 ){
			act.addFieldError("user.password", "密码不正确");
		}
        else {
        	Unit unit = unitDAO.findById(user.getUnitId());
        	this.setInfoOfSession(session, user, unit);
        	//设置在线。。。
        	//OperationBizImpl opbi = new OperationBizImpl();
        	
        	/** 下面这两句暂时不使用！保留 */
        	//Operation[] opModels = getModelByUser(user.getUserCode());
        	//Operation[] opList = getOpByUserAndOpno(user.getUserCode(), opModels[0].getOpNo());
  	

		}
        return uLogin;
	}
	
	private void setInfoOfSession(Map<String,Object> session, Users user , Unit unit){
		session.put("userId", user.getUserId());
		session.put("unitId", user.getUnitId());			//用户所在单位 ID
		session.put("shortName", user.getCompanyCode());  	//用户所在单位 ShortName
		session.put("creatorCode",user.getCreatorCode());	//该用户建立者的编号
		session.put("isDealer",user.getIsDealer());			//true 为经销商用户，否则为系统用户
		session.put("userCode", user.getUserCode());		//用户账号
		session.put("userName", user.getUserName());
		session.put("userType",user.getUserType());			//用户类型：管理员 或 操作员
		//经销商信息！
		session.put("parentId",unit.getParentId());
		session.put("parentShortName",unit.getParentShortName());
		session.put("unitType",unit.getType());
		session.put("databaseMap",unit.getDatabaseMap());
	}	

	/**根据用户账号，返回该用户所有可用模块--实际为各项顶级菜单对象
	 * 参数 userCode：当前用户的账号，可从 SESSION 获取 */
	public Operation[] getModelByUser(String userCode){

		//List opList = usersDAO.getModelByUser(userCode);
		List opList = operationDAO.getModelByUser(userCode);
		Operation[] ops = new Operation[opList.size()];
		Object[] op = new Object[5];
		Iterator it = opList.iterator(); 
		for(int i = 0; i<ops.length; i++){
			op = (Object[]) it.next(); 
			ops[i] = getOpGroup(op);
			//System.out.println(i+":" + ops[i].getOpName());
		}
		
		return ops;
	}
	
	/**根据用户账号，返回该用户可用模块中有权限的下级菜单及菜单项
	 * 参数：opno：根据 getModelByUser(String userCode) 方法返回的Operation[]
	 * 取 Operation[i].getOpNo()，可得到该菜单下的所有项
	 * 循环 Operation[]中的每一项，可以得到全部菜单。
	 */
	public Operation[] getOpByUserAndOpno(String userCode, String opno){
		List opList = operationDAO.getOpByUserAndOpno(userCode, opno);
		Operation[] ops = new Operation[opList.size()-1];
		Object[] op = new Object[5];
		Iterator it = opList.iterator(); 
		it.next();
		for(int i = 0; i<ops.length; i++){
			op = (Object[]) it.next(); 
			ops[i] = getOpGroup(op);
			//System.out.println(i+":" + ops[i].getOpNo()+"   :" + ops[i].getOpName());
		}
		
		return ops;
	}
	
	private Operation getOpGroup(Object[] op){
		Operation ops = new Operation();
		ops.setOpCode(op[0].toString());
		ops.setOpNo(op[1].toString());
		ops.setOpName(op[2].toString());
		ops.setEndFlag(Boolean.parseBoolean(op[3].toString()));
		ops.setUrl(op[4].toString());

		return ops;
	}	
	
	public List<Users> getUserByUserCode(String userCode){
		List<Users> userList = new ArrayList<Users>();
		
		Users userSelf = usersDAO.findByUserCode(userCode);
		if(userSelf != null)
			userList.add(userSelf);
		
		List<Users> userChildList = usersDAO.getUserByUserCode(userCode);
		if(userChildList != null && userChildList.size() != 0)
			userList.addAll(userChildList);
		
		return userList;
	}
	
	public void save(Users user) {
		usersDAO.save(user);
	}
	
	private int checkUser(String userPassword, String password){
		String[] userStr = depart(password);
		//System.out.println("old#:" + userPassword );
		userPassword = hashEncode(userPassword + userStr[2], "SHA");
		//System.out.println("new#:" + userPassword );
        //验证！
        if (userPassword.equals(userStr[0])) {
            //判断是否新用户第一次登录，是则需要修改默认密码
        	String isNew = hashEncode(userStr[1]+"{NYYBDWDX","MD5");
            if (userStr[0] == hashEncode(isNew + userStr[2],"SHA"))
                return 2;       //新用户
            else
                return 1;        //老用户
        }
        else
            return 0;
	}
	
	private static String[] depart(String str)
    {
        String[] s = new String[3];
        s[0] = str.substring(0,40);  //加密串
        int strIndex = str.indexOf("{.}");
        s[1] = str.substring(40,strIndex);  //用户名
        s[2] = str.substring(strIndex+3).trim(); //时间戳
        
        //System.out.println(str + "\r\n#:" + s[0] + "#:" + s[1] + "#:" + s[2]);
        
        return s;

    }
	
	public String hashEncode(String pw, String type) {
		
		byte[] digest;
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(type);
			digest = messageDigest.digest(pw.getBytes());
		} catch (Exception e) {
			throw new IllegalStateException("UTF-8 not supported!");
		}
		return new String(Hex.encodeHex(digest)).toUpperCase();
	}
	
	public Users findByLoginName(String userCode){
		return usersDAO.findByLoginName(userCode);
	}
	
	public void update(Users detachedInstance){
		usersDAO.update(detachedInstance);
	}
	
	public Users merge(Users detachedInstance){
		return usersDAO.merge(detachedInstance);
	}
	
	public String saveNewPW(String userCode, String old, String new1){
		Users u = usersDAO.findByLoginName(userCode);
		old = hashEncode(u.getUserCode() + "{" + old, "MD5");
		if(!u.getPassword().equals(old)) {
			return "旧密码错误，不能修改密码！";
		}
		else {
			u.setPassword(hashEncode(u.getUserCode() + "{" + new1, "MD5"));
			u = merge(u);
			return "修改密码成功，请用新密码登录！";
		}
	}

	//gch
	public Users findById(int userId) {		
		return usersDAO.findById(userId);
	}
	
	public String obtainUserRoles(String userCode) {
		String userRoles = "";
		
		List<UserRole> userRoleList = userRoleDAO.findByUserCode(userCode);
		if(userRoleList != null && userRoleList.size() != 0) {
			Role role;
			for(UserRole userRole : userRoleList) {
				role = roleDAO.findById(userRole.getId().getRoleId());
				
				if(userRoles.equals(""))
					userRoles = role.getRoleName();
				else
					userRoles = userRoles + "、" + role.getRoleName();
			}
		}

		return userRoles;
	}

	//dao
	private UsersDAO usersDAO;
	public void setUsersDAO(UsersDAO usersDAO) {
		this.usersDAO = usersDAO;
	}
	
	private UnitDAO unitDAO;
	public void setUnitDAO(UnitDAO unitDAO) {
		this.unitDAO = unitDAO;
	}
	
	private OperationDAO operationDAO;
	public void setOperationDAO(OperationDAO operationDAO) {
		this.operationDAO = operationDAO;
	}
	
	private UserRoleDAO userRoleDAO;
	public void setUserRoleDAO(UserRoleDAO userRoleDAO) {
		this.userRoleDAO = userRoleDAO;
	}
	
	private RoleDAO roleDAO;
	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}
}
