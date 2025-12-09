package com.luyuan.info.dao;

import java.util.List;

import com.luyuan.action.IFieldValidation;
import com.luyuan.info.entity.Account;
import com.luyuan.util.Page;

public interface AccountDAO {   
	
	//gch
	public abstract Account findById(java.lang.Integer id);
	
	public abstract void update(Account transientInstance);
	
	//wml
	public List<Account> findAccount(IFieldValidation act, Integer dealerId,boolean isDisable);
	
	public Account findAccount(Integer dealerId ,Integer dealingUnitId,String type);
	
	public List<Account> findAccount(IFieldValidation act, Integer dealerId ,String type);
	
	public List<Account> findAccount(Integer dealerId ,String type);
	
	public void save(Account account);	
	
	public List<Account> findDisabledAccount(Integer dealerId) ;
	
	public Account findAllAccount(Integer dealerId ,Integer dealingUnitId,String type);
}