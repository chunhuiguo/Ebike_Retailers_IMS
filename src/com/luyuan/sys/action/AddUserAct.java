package com.luyuan.sys.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.luyuan.action.ListAct;
import com.luyuan.info.action.EmployeeListAct;
import com.luyuan.info.biz.EmployeeBiz;
import com.luyuan.info.entity.Employee;
import com.luyuan.sys.biz.UnitBiz;
import com.luyuan.sys.biz.UsersBiz;
import com.luyuan.sys.entity.Unit;
import com.luyuan.sys.entity.Users;

public class AddUserAct extends ListAct {	

	//提示信息
	private String msg = "";
	public String getMsg() {
		return msg;
	}
	public void setMse(String msg) {
		this.msg = msg;
	}
	//entity
	private Users user;
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	
	protected List<Unit> unitList = new ArrayList<Unit>();	
	public List<Unit> getUnitList() {
		return unitList;
	}
	public void setUnitList(List<Unit> unitList) {
		this.unitList = unitList;
	}
	
	private Employee employee = new Employee();		
	public Employee getEmployee() {
		return employee;
	}	
	
	//biz
	private UnitBiz unitBiz;
	public void setUnitBiz(UnitBiz unitBiz) {
		this.unitBiz = unitBiz;
	}
	
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
	
	//spring ApplicationContext
	private ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext( ServletActionContext.getServletContext());
	
	private String[] isEnabled = {};
	public String[] getIsEnabled(){
		return this.isEnabled;
	}
	public void setIsEnabled(String[] isEnabled){
		this.isEnabled = isEnabled;
	}

	private String select;
	public String getSelect(){
		return this.select;
	}
	public void setSelect(String select){
		this.select = select;
	}
	
	private String disSave;
	public String getDisSave(){
		return this.disSave;
	}
	public void setDisSave(String disSave){
		this.disSave = disSave;
	}
	
	public String execute() {
		
		/**测试期注释此部分，上线使用取消该注释
		if(!session.get("userType").toString().equals("经销商管理员")) {
			disSave = "none";
			msg = "您没有权限使用该功能，请点击\"取消\"返回首页！";
			return "success";
		}
		 */
		
		disSave = "inline";
		if(submit == null) {			

			
			if(unitList != null)
				unitList.clear();
			
			//if(user == null){
				user = new Users();
				user.setUserType("操作员");
				user.setEnabled(true);
				user.setPassword("NYYBDWDX");
				employee = new Employee();
			//}

			//填充 UnitList
			setNewUnitList();
			return "success";
		}
		if(submit.equals("保存")) {
			if(this.validation()) {				
				msg = saveUser(user);
				submit = null;
				employee.setUserId(user.getUserId());
				employeeBiz.update(employee);
				resetUser();
				
				//user = null;
//				return "success";
			}
			return "success";
		}
		if(submit.equals("取消")) {
			submit = null;
			isEnabled = null;
			user = null;

			return "cancel";
		}
		
		if(submit.equals("检查")) {
			submit = null;
			if(usersBiz.findByLoginName(user.getUserCode()) == null)
				msg = "该账号有效，可以使用！";
			else {
				msg = "用户账号已有人使用，请换一个账号！";
				user.setUserCode("");
			}
			return "success";
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
	
	//gch
	private boolean validation() {
		if(employee.getId() == null) {
			this.addFieldError("employee", "员工不能为空");
			return false;
		}
		return true;
	}
	
	public void setNewUnitList() {
		unitList = unitBiz.findDealerAndUnit((Integer)this.getSession().get("unitId"));
	}
	
	private String saveUser(Users user){
		user.setUserCode(user.getUserCode().trim());
		if(user.getUserCode().equals("")){
			msg = "用户账户不能为空";
			return msg;
		}
		user.setUserName(user.getUserName().trim());
		if(user.getUserName().equals("")){
			msg = "用户姓名不能为空";
			return msg;
		}
		
		if(usersBiz.findByLoginName(user.getUserCode()) != null){
			msg = "用户账号已有人使用，请换一个账号！";
			return msg;
		}
		String pw = user.getUserCode() + "{" + user.getPassword();
		user.setPassword(usersBiz.hashEncode(pw, "MD5"));
		user.setIsDealer(true);
		user.setEnabled(isEnabled.length == 0 ? false : true);
		user.setCreatorCode(this.getSession().get("userCode").toString());	
		user.setCompanyCode(select);
		user.setOnline(false);
		
		Unit u;
		for(int i = 0; i < unitList.size(); i++){
			u = unitList.get(i);
			if(u.getShortName().equals(select)){
				user.setUnitId(u.getId());
			}
		}
		try{
			usersBiz.save(user);
			msg = "保存用户信息： 成功！";
		}
		catch(Exception e){
			msg = "保存用户信息： 失败！" + e.getMessage();
			return msg;
		}
		return msg;
	}
	
	private void resetUser(){
		user.setUserCode("");
		user.setUserName("");
		user.setPassword("NYYBDWDX");
		user.setEnabled(true);
		employee = new Employee();
	}
	
}
