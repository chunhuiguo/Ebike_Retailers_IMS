package com.luyuan.info.biz;

import java.util.List;

import com.luyuan.action.IFieldValidation;
import com.luyuan.info.entity.Account;
import com.luyuan.info.entity.SubAccount;
import com.luyuan.info.entity.SubAccountType;


/**
 * 
 */

public interface AccountManageBiz  {
	
	public void saveAccount(Account account,List<SubAccount> subAccountList);
	
//	public boolean validation(IFieldValidation act, Account account,
//			List<SubAccount> subAccountList);
//	
	
	
}