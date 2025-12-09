/**
 * @(#)EmployeeAct.java  1.0 10/04/15
 *
 * Copyright 2010 luyuan-ebike, Inc. All rights reserved.
 * 
 */
package com.luyuan.info.action;

import java.util.ArrayList;
import java.util.List;

import com.luyuan.action.ListAct;
import com.luyuan.info.biz.EmployeeBiz;
import com.luyuan.info.entity.Employee;
import com.luyuan.sys.biz.UsersBiz;
import com.luyuan.util.Page;

/**
 * 
 * 员工列表EmployeeList的表现层action
 *
 * @author gch
 */

public class EmployeeListAct extends ListAct {
		
	public String list() {
		select = null;
		page =  new Page();
		page.setListAct("/info/employeeList!select.html");
		
		this.employeeList();
		return "success";
	}
	
	public String select() {
		if(holdEmployee())
			return result;
		this.employeeList();
		return "success";
	}
	
	
	/**************************************************************************************************/
	/**************************************************************************************************/
	//private method
	
	private boolean holdEmployee() {
		//翻页
		if( select == null )
			return false;
		//退出，返回
		if( select == -1) {
			employee = null;
			return true;
		}
		//确定
		employee = employeeList.get(select);
		return true;
	}
	
	private void employeeList() {
		if(employeeList != null)
			employeeList.clear();
		if(userCodeList != null)
			userCodeList.clear();
		forUser = false;
		
		boolean isDealer = false;
		if( this.getSession().get("unitType").equals("经销商") )
			isDealer = true;
		employeeList = employeeBiz.findEmployee(this, (Integer) this.getSession().get("unitId"), isDealer, false);
		
		if(result.endsWith("User")) {
			forUser = true;
			if(employeeList != null && employeeList.size() != 0) {				
				for(int i = 0; i < employeeList.size(); i++) {			
					if(employeeList.get(i).getUserId() == null)
						userCodeList.add("");
					else				
						userCodeList.add(usersBiz.findById(employeeList.get(i).getUserId()).getUserCode());
				}
			}
		}
	}
	
	/**************************************************************************************************/
	//page field
	private List<Employee> employeeList;	
	public List<Employee> getEmployeeList() {
		return employeeList;
	}
	
	private List<String> userCodeList = new ArrayList<String>();
	public List<String> getUserCodeList() {
		return userCodeList;
	}
	
	//表明员工选择列表是否用于与用户user有关的操作，决定页面上是否显示“用户”一列
	private boolean forUser;	
	public boolean isForUser() {
		return forUser;
	}

	//no ajax
	private Integer select;	
	public void setSelect(Integer select) {
		this.select = select;
	}
	
	//用来保存选择的employee信息
	//供需要的action获得
	private Employee employee;
	public Employee getEmployee() {
		return employee;
	}	
	
	//用于接收别的action传过来的参数
	private String result;
	public void setResult(String result) {
		this.result = result;
	}

	//biz
	private EmployeeBiz employeeBiz;
	public void setEmployeeBiz(EmployeeBiz employeeBiz) {
		this.employeeBiz = employeeBiz;
	}
	
	private UsersBiz usersBiz;
	public void setUsersBiz(UsersBiz usersBiz) {
		this.usersBiz = usersBiz;
	}	
}