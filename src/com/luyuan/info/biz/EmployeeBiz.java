package com.luyuan.info.biz;

import java.util.List;

import com.luyuan.action.IFieldValidation;
import com.luyuan.info.entity.Employee;
import com.luyuan.sys.entity.Users;
import com.luyuan.util.Page;

public interface EmployeeBiz {	
	
	//gch
	public abstract List<Employee> findEmployee(IFieldValidation act, int shopId, boolean isDealer, boolean viewDisabled);
	
	public abstract Employee findByUserId(int userId);	
	
	//zsh
//	public abstract List<Employee> findAll();
	public void save(Employee employee);
//	public Employee findById(Integer id);
	public void update(Employee employee);
//	public List findByProperty(String propertyName, Object value);
}