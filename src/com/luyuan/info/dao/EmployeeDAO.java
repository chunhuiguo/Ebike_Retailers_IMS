package com.luyuan.info.dao;

import java.util.List;

import com.luyuan.action.IFieldValidation;
import com.luyuan.info.entity.Employee;
import com.luyuan.util.Page;

public interface EmployeeDAO {   
	//gch
	public abstract List<Employee> findEmployee(final IFieldValidation act, final int shopId, final boolean isDeale, final boolean viewDisabled);
	
	public abstract Employee findByUserId(int userId);
	
	//zsh
	public void save(Employee transientInstance) ;
//	public List findAll();
//	public Employee findById(Integer id);
	public void update(Employee employee);
//	public List<Employee> findByProperty(String propertyName, Object value);
}