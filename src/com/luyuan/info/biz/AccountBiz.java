package com.luyuan.info.biz;

import java.util.List;

import com.luyuan.action.IFieldValidation;
import com.luyuan.info.entity.Account;
import com.luyuan.util.Page;

 

/**
 * 
 */

public interface AccountBiz  {
	public List<Account> findAccount(IFieldValidation act, Integer dealerId);
	
	public List<Account> findAccount(IFieldValidation act, Integer dealerId ,String type);
	
	public void update(Account account,boolean isStart);
	
	public boolean validation(IFieldValidation act, Account account);
}