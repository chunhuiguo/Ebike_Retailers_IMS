package com.luyuan.sys.action;

import java.util.Map;

import com.luyuan.action.BaseAct;
import com.luyuan.sys.biz.UsersBiz;
import com.luyuan.sys.entity.Users;
import com.opensymphony.xwork2.ActionContext;

public class UsersAct extends BaseAct {

	public String login() {
		return "login";
	}
	
	public String enter() {
		if (submit.equals("登录")) {
			submit = null;
			Map<String,Object> session = this.getSession();
			//System.out.println("@###########"+getPwstr()+"&&" + user.getPassword()+"!!");
			int loginResult = usersBiz.authentication(this, session, user.getUserCode(),getPwstr());
			
			//待补充：第一次登录判断，新用户=2
			if (loginResult != 0)  
				return "success";
			else 
				return "input";
		}	
		return "login";
	}
	//时间戳，防止重放
	private String loginTime;
	public void setLoginTime(String logintime) {
		this.loginTime = logintime;
	}
	public String getLoginTime(){
		this.loginTime = (new java.util.Date()).toString();
		return this.loginTime;
	}
	
	private String pwstr;
	public void setPwstr(String pwstr) {
		this.pwstr = pwstr;
	}
	public String getPwstr(){
		return this.pwstr;
	}
	/** 验证码 */
	private String valiCode;
	public void setValiCode(String valicode) {
		this.valiCode = valicode;
	}
	public String getValiCoder(){
		return this.valiCode;
	}
	//biz
	private UsersBiz usersBiz;
	public void setUsersBiz(UsersBiz usersBiz) {
		this.usersBiz = usersBiz;
	}

	//page
	private String submit;
	public void setSubmit(String submit) {
		this.submit = submit;
	}

	//entity
	private Users user;
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	
}
