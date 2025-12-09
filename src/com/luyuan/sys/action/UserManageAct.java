package com.luyuan.sys.action;

import java.util.ArrayList;
import java.util.List;

import com.luyuan.action.BaseAct;
import com.luyuan.sys.biz.UsersBiz;
import com.luyuan.sys.entity.Users;
import com.opensymphony.xwork2.ActionContext;

public class UserManageAct extends BaseAct {

	//提示信息
	private String msg = "";
	public String getMsg() {
		return msg;
	}
	
	//entity
	private Users user;
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	
	protected List<Users> userList = new ArrayList<Users>();	
	public List<Users> getUserList() {
		return userList;
	}
	public void setUserList(List<Users> userList) {
		this.userList = userList;
	}
	
	private List<String> userRoles = new ArrayList<String>();
	public List<String> getUserRoles() {
		return userRoles;
	}

	private Integer select;
	public void setSelect(Integer select) {
		this.select = select;
	}
	
	//submit
	private String submit;
	public void setSubmit(String submit) {
		this.submit = submit;
	}
	
	//biz
	private UsersBiz usersBiz;
	public void setUsersBiz(UsersBiz usersBiz) {
		this.usersBiz = usersBiz;
	}
	
	public String execute(){
		
		/**测试期注释此部分，上线使用取消该注释
		String type = ActionContext.getContext().getSession().get("userType").toString();
		if(!type.equals("经销商管理员")) {
			msg = "您没有权限使用该功能，请使用其他功能！";
			return "success";
		}
		*/
		
//		msg = "点击每行右侧的图标，可修改用户信息或为该用户进行授权，管理员账号不能进行授权操作。";
		userList = usersBiz.getUserByUserCode(this.getSession().get("userCode").toString());
		this.obtainUserRoleList();
		return "success";
	}
	
	public String edit(){
		user = userList.get(select);
		return "edit";
	}
	
	public String role(){
		user = userList.get(select);
		return "role";
	}
	
	public String resetPassword()
	{
		user = userList.get(select);
		return "resetPassword";
	}
	
	public String resetPasswordOK()
	{
		user.setPassword("NYYBDWDX");
		String pw = user.getUserCode() + "{" + user.getPassword();
		user.setPassword(usersBiz.hashEncode(pw, "MD5"));
		usersBiz.update(user);
		
		msg = "密码重置成功，请告知用户使用系统默认密码登录后尽快修改密码！";
		return "resetPassword";
	}
	
	private void obtainUserRoleList() {
		if(userRoles != null)
			userRoles.clear();
		
		for(Users eachUser : userList)			
			userRoles.add(usersBiz.obtainUserRoles(eachUser.getUserCode()));
	}
}
