package com.luyuan.info.biz.impl;

import java.util.ArrayList;
import java.util.List;

import com.luyuan.action.IFieldValidation;
import com.luyuan.info.biz.AccountBiz;
import com.luyuan.info.dao.AccountDAO;
import com.luyuan.info.dao.SubAccountDAO;
import com.luyuan.info.entity.Account;
import com.luyuan.info.entity.SubAccount;
import com.luyuan.util.Page;
/**
 * 
 */

public class AccountBizImpl implements AccountBiz{
	
	public List<Account> findAccount(IFieldValidation act, Integer dealerId) {
		//false时返回全部的，true时返回可用的
		return accountDAO.findAccount( act, dealerId,false);
	}
	
	public List<Account> findAccount(IFieldValidation act, Integer dealerId ,String type){
		return accountDAO.findAccount(act, dealerId, type);
	}
	
	//启用或停用总账户
	public void update(Account account, boolean isStart) {
		List<SubAccount> subAccountList = new ArrayList<SubAccount>();
		if (isStart) {

			account.setDisable(true);
			accountDAO.update(account);
			subAccountList.addAll(subAccountDAO.findSubAccount((int) account
					.getId()));

			for (int i = 0; i < subAccountList.size(); i++) {
				subAccountList.get(i).setDisable(true);
				subAccountDAO.update(subAccountList.get(i));
			}
		} else {
			account.setDisable(false);
			accountDAO.update(account);
			subAccountList.addAll(subAccountDAO.findSubAccount((int) account
					.getId()));

			for (int i = 0; i < subAccountList.size(); i++) {
				subAccountList.get(i).setDisable(false);
				subAccountDAO.update(subAccountList.get(i));
			}
		}
	}
	
	//停用账户时的校验
	public boolean validation(IFieldValidation act,Account account) {
		boolean isError = false;
		
		List<SubAccount> subAccountList = new ArrayList<SubAccount>();
		subAccountList.addAll(subAccountDAO.findSubAccount((int)account.getId()));
			
		for (int i = 0; i < subAccountList.size(); i++){
			// 账户不等于0时不允许停用账户
			if (!subAccountList.get(i).getBalance().equals(0.0)){
				act.addFieldError("stop","【"+subAccountList.get(i).getName()+"】余额非0，不允许停用！");
				isError = true;
				break;
			}
		}

		if (isError)
			return false;
		else
			return true;

	}
	
	
	
	//dao
	private AccountDAO accountDAO;
	public void setAccountDAO(AccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}
	
	private SubAccountDAO subAccountDAO;
	public void setSubAccountDAO(SubAccountDAO subAccountDAO) {
		this.subAccountDAO = subAccountDAO;
	}
	
}