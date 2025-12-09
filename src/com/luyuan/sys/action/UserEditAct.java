package com.luyuan.sys.action;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.luyuan.info.action.EmployeeAct;
import com.luyuan.info.action.EmployeeListAct;
import com.luyuan.info.biz.EmployeeBiz;
import com.luyuan.info.entity.Employee;
import com.luyuan.sys.biz.UsersBiz;
import com.luyuan.sys.entity.Users;

public class UserEditAct extends UserManageAct {
	
	//spring ApplicationContext
	protected ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext( ServletActionContext.getServletContext());
	
	private Users user;
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	
	private Employee employee;		
	public Employee getEmployee() {
		return employee;
	}
	
	//biz
	private UsersBiz usersBiz;
	public void setUsersBiz(UsersBiz usersBiz) {
		this.usersBiz = usersBiz;
	}
	
	private EmployeeBiz employeeBiz;	
	public void setEmployeeBiz(EmployeeBiz employeeBiz) {
		this.employeeBiz = employeeBiz;
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
	
	//userManage 页面入口
	public String editIn(){
		msg = "";
		UserManageAct useract = (UserManageAct) ctx.getBean("userManageAct");
		user = useract.getUser();
		
		employee = employeeBiz.findByUserId(user.getUserId());

		return "success";
	}
	
	//提交！
	public String execute() {

		if(submit.equals("保存")){
			
			user.setUserName(user.getUserName().trim());
			if(user.getUserName().equals("")){
				msg = "用户姓名不能为空";
				return "success";
			}
			
			try{
				Employee employeeOld = employeeBiz.findByUserId(user.getUserId());
				if(employeeOld != null) {
					if(employeeOld.getId() != employee.getId()) {						
						employeeOld.setUserId(null);
						employeeBiz.update(employeeOld);
						
						employee.setUserId(user.getUserId());
						employeeBiz.update(employee);
					}
				}
				else {
					employee.setUserId(user.getUserId());
					employeeBiz.update(employee);
				}
				
				//user = 
				usersBiz.update(user);
				msg = "保存用户信息： 成功！";
			}
			catch(Exception e){
				msg = "保存用户信息： 失败！" + e.getMessage();
			}
			submit = null;
			return "success";
		}

		if(submit.equals("取消")) {
			submit = null;
			user = null;
			return "cancel";
		}
		
		//gch
		if(submit.equals("选择员工")) {
			submit = null;
			return "employeeDetail";
		}
		
		return "success";
	}
	
	//gch
	//选择员工返回入口
	public String infoBack() {
		EmployeeListAct employeeListAct = (EmployeeListAct) ctx.getBean("employeeListAct");
		if(employeeListAct.getEmployee() != null)
			employee = employeeListAct.getEmployee();
		return "success";
	}
	
}
