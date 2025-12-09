package com.luyuan.home.biz;

import java.security.MessageDigest;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.codec.binary.Hex;

import com.luyuan.sys.dao.OperationDAO;
import com.luyuan.sys.dao.UnitDAO;
import com.luyuan.sys.dao.UsersDAO;
import com.luyuan.sys.entity.Operation;
import com.luyuan.sys.entity.Unit;
import com.luyuan.sys.entity.Users;

public class LoginBiz {
	
	public Users getUserByCode(String userCode){
		return usersDAO.findByLoginName(userCode);
	}
	
	public Unit getUnitByUser(Users user){
		return unitDAO.findById(user.getUnitId());
	}
	
	public LinkedHashMap<String,Operation[]> getMenu(String userCode){
		LinkedHashMap<String,Operation[]> menu = new LinkedHashMap<String,Operation[]>();
		Operation[] models = getModelByUser(userCode);
		
		Operation[] opList;
		if (models != null){
			for (int i = 0; i < models.length; i++) {
				opList = getOpByUserAndOpno(userCode, models[i].getOpNo());
				menu.put(models[i].getOpName(), opList);
			}			
		}
		return menu;
	}
	
	public LinkedHashMap<String,Operation[]> getNoEmployeeMenu() {
		LinkedHashMap<String,Operation[]> menu = new LinkedHashMap<String,Operation[]>();
		
		
		//"员工信息管理"
		Operation[] ops1 = new Operation[1];		
		Operation op1 = new Operation();		
		op1.setOpCode("info_employee");
		op1.setOpNo("info06");
		op1.setOpName("员工信息管理");
		op1.setEndFlag(true);
		op1.setUrl("/info/employee.html");
		ops1[0] = op1;		
		//"用户管理"
		Operation[] ops2 = new Operation[1];		
		Operation op2 = new Operation();		
		op2.setOpCode("sys_userManage");
		op2.setOpNo("sys02");
		op2.setOpName("用户管理");
		op2.setEndFlag(true);
		op2.setUrl("/sys/userManage.html");
		ops2[0] = op2;
		
		menu.put("基本信息", ops1);
		menu.put("用户管理", ops2);		

		return menu;
	}
	
	public boolean checkUser(Users user, String encryptStr, String loginTime){
		String pwdAndTime = user.getPassword()+loginTime;
		pwdAndTime = encrypt(pwdAndTime, "SHA");
		return pwdAndTime.equals(encryptStr);
	}
	
	/*
	public int checkUser(Users user, String inputPWD, String loginTime){
		String pwdAndTime = user.getPassword() + loginTime;
		pwdAndTime = encrypt(pwdAndTime, "SHA");
        if (pwdAndTime.equals(inputPWD)) {
            //判断用户是否使用默认密码，是则需要修改默认密码
        	String encryptPWD = encrypt(user.getUserCode()+"{NYYBDWDX","MD5");
            if (user.getPassword().equals(encryptPWD))
                return 2;       //未修改
            return 1;        	//已修改
        }
        return 0;				//密码错误
	}*/

	/**************************************************************************************************/
	/**************************************************************************************************/
	//private method
	
	private String encrypt(String text, String type) {
		byte[] digest;
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(type);
			digest = messageDigest.digest(text.getBytes("utf-8"));
		} catch (Exception e) {
			throw new IllegalStateException("UTF-8 not supported!");
		}
		return new String(Hex.encodeHex(digest)).toUpperCase();
	}
	
	/**根据用户账号，返回该用户所有可用模块--实际为各项顶级菜单对象
	 * 参数 userCode：当前用户的账号，可从 SESSION 获取 */
	private Operation[] getModelByUser(String userCode){
		List opList = operationDAO.getModelByUser(userCode);
		Operation[] ops = new Operation[opList.size()];
		Object[] op = new Object[5];
		Iterator it = opList.iterator(); 
		for(int i = 0; i<ops.length; i++){
			op = (Object[]) it.next(); 
			ops[i] = getOpGroup(op);
		}
		
		return ops;
	}
	
	/**根据用户账号，返回该用户可用模块中有权限的下级菜单及菜单项
	 * 参数：opno：根据 getModelByUser(String userCode) 方法返回的Operation[]
	 * 取 Operation[i].getOpNo()，可得到该菜单下的所有项
	 * 循环 Operation[]中的每一项，可以得到全部菜单。
	 */
	private Operation[] getOpByUserAndOpno(String userCode, String opno){
		List opList = operationDAO.getOpByUserAndOpno(userCode, opno);
		if(opList !=null && opList.size()>0) {			
			Operation[] ops = new Operation[opList.size()-1];
			Object[] op = new Object[5];
			Iterator it = opList.iterator(); 
			it.next();
			for(int i = 0; i<ops.length; i++){
				op = (Object[]) it.next(); 				
				ops[i] = getOpGroup(op);
			}
			
			return ops;
		}
		return null;
	}
	
	private Operation getOpGroup(Object[] op){
		Operation ops = new Operation();
		ops.setOpCode(op[0].toString());
		ops.setOpNo(op[1].toString());
		ops.setOpName(op[2].toString());
		ops.setEndFlag(Boolean.parseBoolean(op[4].toString()));
		ops.setUrl(op[3].toString());		

		return ops;
	}
	
	/**************************************************************************************************/
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
	
}
