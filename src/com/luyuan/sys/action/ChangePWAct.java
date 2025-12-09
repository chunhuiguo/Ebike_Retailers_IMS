package com.luyuan.sys.action;

import java.util.Map;

import com.luyuan.action.BaseAct;
import com.luyuan.sys.biz.UsersBiz;
import com.opensymphony.xwork2.ActionContext;

public class ChangePWAct extends BaseAct {

/*	private Users user;
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	*/
	private String oldPW;
	public String getOldPW() {
		return oldPW;
	}
	public void setOldPW(String oldPW) {
		this.oldPW = oldPW;
	}
	
	private String new1;
	public String getNew1() {
		return new1;
	}
	public void setNew1(String new1) {
		this.new1 = new1;
	}
	
	private String new2;
	public String getNew2() {
		return new2;
	}
	public void setNew2(String new2) {
		this.new2 = new2;
	}	
	
	private UsersBiz usersBiz;
	public void setUsersBiz(UsersBiz usersBiz) {
		this.usersBiz = usersBiz;
	}
	
	private String submit;
	public void setSubmit(String submit) {
		this.submit = submit;
	}
	
	//提示信息
	private String msg = "";
	public String getMsg() {
		return msg;
	}
	public void setMse(String msg) {
		this.msg = msg;
	}
	
	//提交！
	public String execute() {
		
		if(submit == null) {
			oldPW = "";
			new1 = "";
			new2 = "";
			msg = "";
			return "success";
		}

		if(submit.equals("保存")){
			
			oldPW = oldPW.trim();
			if(oldPW.equals("")){
				msg = "您必须输入旧密码！";
				return "success";
			}
			
			new1 = new1.trim();
			new2 = new2.trim();
			if(new1.equals("") || new2.equals("") || !new1.equals(new2)){
				msg = "新密码为空或不一致！请重新输入。";
				return "success";
			}		
			
			String userCode = this.getSession().get("userCode").toString(); 
			try{
				msg = usersBiz.saveNewPW(userCode, oldPW, new1);
			}		//A5211CE9264C0E2763906B4C21999862
			catch(Exception e){
				msg = "更改密码失败！请重试。" + e.getMessage();
			}
			submit = null;
			return "success";
		}

		if(submit.equals("取消")) {
			submit = null;
			return "cancel";
		}
		
		return "success";
	}
}
