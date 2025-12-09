/**
 * @(#)EmployeeAct.java  1.0 10/04/15
 *
 * Copyright 2010 luyuan-ebike, Inc. All rights reserved.
 * 
 */
package com.luyuan.info.action;

import java.util.List;

import org.springframework.context.ApplicationContext;

import com.luyuan.action.ListAct;
import com.luyuan.info.biz.EmployeeBiz;
import com.luyuan.info.entity.Employee;
import com.luyuan.sys.action.UnitListAct;
import com.luyuan.sys.entity.Unit;

/**
 * 
 * 员工管理Employee的表现层action
 *
 * @author gch
 */

public class EmployeeAct extends ListAct {
	//zsh
	//内部职员基本信息
	public String execute() throws Exception {		
		if(submit == null) {			
			page.setListAct("employee.html");
			this.employeeList(true);
			return "success";
		}
		
		if(submit.equals("添加"))
		{
			submit = null;
			return "save";
		}
			
		if(submit.equals("修改"))
		{
			submit = null;
			if(select != null) {
				employee = employeeList.get(select);			
				return "update";
			}
			return "success";
		}
		
		if(submit.equals("查询"))
		{
			submit = null;
			return "select";
		}
		
		if(submit.equals("停用"))
		{
			submit = null;
			//停用代码
			if(select != null) {
				employee = employeeList.get(select);
				employee.setDisable(false);
				employeeBiz.update(employee);
				return "stop";
			}
			return "success";
		}
		return "success";
	}
	
	public String unitListBackToSave() {
		UnitListAct unitListAct = (UnitListAct) ctx.getBean("unitListAct");
		Unit unit = unitListAct.getUnit();
		
		if(unit != null) {			
			employee.setShopId(unit.getId());
			employee.setShopShortName(unit.getShortName());
		}
		return "unitListBack";
	}
	
	public String unitListBackToUpdate() {
		UnitListAct unitListAct = (UnitListAct) ctx.getBean("unitListAct");
		Unit unit = unitListAct.getUnit();
		
		if(unit != null) {			
			employee.setShopId(unit.getId());
			employee.setShopShortName(unit.getShortName());
		}
		return "update";
	}
	
	//添加
	public String save(){
		if(submit == null) {
			employee = new Employee();
			employee.setTitle("会计");
			employee.setDisable(true);
			return "success";
		}
		if(submit.equals("选择门店")) {
			submit = null;
			return "unitDetail";
		}
		if(submit.equals("保存"))
		{
			submit = null;
			//保存代码
			if(this.validation()) {				
				employee.setDealerId((Integer) this.getSession().get("parentId"));
				employee.setDealerShortName((String) this.getSession().get("parentShortName"));			
				employeeBiz.save(employee);
				return "save";
			}
			return "success";
		}
		if(submit.equals("退出")) {
			submit = null;
			return "exit";
		}
		return "success";
	}
	
	//修改
	public String update(){
		if(submit == null)
			return "success";
		
		if(submit.equals("保存"))
		{
			submit = null;
			//修改代码
			if(this.validation()) {					
				employeeBiz.update(employee);
				return "update";
			}
			return "success";
		}
		if(submit.equals("选择门店")) {
			submit = null;
			return "unitDetail";
		}
		if(submit.equals("退出")) {
			submit = null;
			return "exit";
		}
		return "success";
	}
	
	//查询入口
	public String selectInput() {
		employeeTitle = "所有";
		page.setListAct("employeeSelectInfoInput.html");
		this.employeeList(true);			
		return "success";
	}
	
	//查询
	public String select(){		
		page.setListAct("employeeSelectInfo.html");
		if(submit == null) {			
			this.employeeList(false);			
			return "success";
		}		
		if(submit.equals("筛选"))
		{
			submit = null;
			if(employeeTitle.equals("所有"))
				page.getPropsValueList().add(1, "");	
			else
				page.getPropsValueList().add(1, employeeTitle);	
			this.employeeList(false);	
			return "success";
		}			
		if(submit.equals("修改"))
		{
			submit = null;
			if(select != null) {
				employee = employeeList.get(select);			
				return "update";
			}
			return "success";
		}		
		if(submit.equals("退出")) {
			submit = null;
			return "exit";
		}

		return "success";
	}	
	
	/**************************************************************************************************/
	/**************************************************************************************************/
	//private method
	
	//员工列表
	private void employeeList(boolean clearInputCondation) {
		if(employeeList != null)
			employeeList.clear();	
		select = null;	
		if(clearInputCondation) {
			if(page.getPropsNameList() != null)
				page.getPropsNameList().clear();
			if(page.getPropsValueList() != null)
				page.getPropsValueList().clear();
		}
		
		boolean isDealer;
		if(this.getSession().get("unitType").equals("经销商"))
			isDealer = true;
		else
			isDealer = false;
		
		employeeList = employeeBiz.findEmployee(this, (Integer) this.getSession().get("unitId"), isDealer, true);
	}
	
	private boolean validation() {
		boolean isError = false;
		if(employee.getName().equals("")) {
			isError = true;
			this.addFieldError("employee.name", "请输入员工姓名");			
		}
		if(employee.getShopId() == null) {
			isError = true;
			this.addFieldError("employee.unit", "请选择所属门店");
		}
		if(isError)
			return false;
		return true;
	}	
	
	
	/**************************************************************************************************/
	//page field
	private List<Employee> employeeList;	
	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	//no ajax
	private Integer select;	
	public void setSelect(Integer select) {
		this.select = select;
	}	
	
	private String submit;
	public void setSubmit(String submit) {
		this.submit = submit;
	}
	
	private Employee employee;
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	//员工头衔，筛选条件
	private String employeeTitle;
	public String getEmployeeTitle() {
		return employeeTitle;
	}
	public void setEmployeeTitle(String employeeTitle) {
		this.employeeTitle = employeeTitle;
	}	
	
	//spring ApplicationContext
	private ApplicationContext ctx = this.getAppContext();

	//biz
	private EmployeeBiz employeeBiz;
	public void setEmployeeBiz(EmployeeBiz employeeBiz) {
		this.employeeBiz = employeeBiz;
	}
}