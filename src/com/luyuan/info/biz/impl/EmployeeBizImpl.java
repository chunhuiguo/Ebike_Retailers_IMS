/**
 * @(#)EmployeeBizImpl.java  1.0 10/04/13
 *
 * Copyright 2010 luyuan-ebike, Inc. All rights reserved.
 * 
 */
package com.luyuan.info.biz.impl;

import java.util.List;

import com.luyuan.action.IFieldValidation;
import com.luyuan.info.biz.EmployeeBiz;
import com.luyuan.info.dao.EmployeeDAO;
import com.luyuan.info.entity.Employee;
import com.luyuan.sys.entity.Users;
import com.luyuan.util.Page;

/**
 * 
 * 员工Employee的业务逻辑层biz
 *
 * @author gch
 */

public class EmployeeBizImpl implements EmployeeBiz {

	
	//gch
	public List<Employee> findEmployee(IFieldValidation act, int shopId, boolean isDealer, boolean viewDisabled) {
		return employeeDAO.findEmployee(act, shopId, isDealer, viewDisabled);
	}
	
	public Employee findByUserId(int userId) {
		return employeeDAO.findByUserId(userId);
	}
	
	//zsh
//	public List<Employee> findAll() {
//		// TODO Auto-generated method stub
//		return employeeDAO.findAll();
//	}
	
	public void save(Employee employee){
		employeeDAO.save(employee);
	}
	
//	public Employee findById(Integer id){
//		return employeeDAO.findById(id);
//	}
	
	public void update(Employee employee){
		employeeDAO.update(employee);
	}
	
//	public List findByProperty(String propertyName, Object value){
//		return employeeDAO.findByProperty(propertyName, value);
//	}
	
	//DAO
	private EmployeeDAO employeeDAO;
	public void setEmployeeDAO(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}
}
