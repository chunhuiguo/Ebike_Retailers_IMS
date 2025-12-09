/**
 * @(#)SubAccountBizImpl.java  1.0 10/04/13
 *
 * Copyright 2010 luyuan-ebike, Inc. All rights reserved.
 * 
 */
package com.luyuan.info.biz.impl;

import java.util.ArrayList;
import java.util.List;

import com.luyuan.info.biz.SubAccountBiz;
import com.luyuan.info.dao.AccountDAO;
import com.luyuan.info.dao.SubAccountDAO;
import com.luyuan.info.dao.SubAccountTypeDAO;
import com.luyuan.info.entity.Account;
import com.luyuan.info.entity.SubAccount;
import com.luyuan.info.entity.SubAccountType;
import com.luyuan.money.entity.DealingDetail;

/**
 * 
 * 子账户SubAccount的业务逻辑层biz
 *
 * @author gch
 */

public class SubAccountBizImpl implements SubAccountBiz {

	
	//gch
	public SubAccount findSubAccountForVoucher(int dealerId, int dealingUnitId, String prefix, String type) {
		return subAccountDAO.findSubAccountForVoucher(dealerId, dealingUnitId, prefix, type);
	}
	
	//wml
	//将选择的子帐户添加到付款单的detail中
	public List<DealingDetail> selectSubAccount(List<Integer> selectList,
			List<SubAccount> subAccountList) {
		List<DealingDetail> dealingDetailList=new ArrayList<DealingDetail>();		
		for (int i = 0; i < selectList.size(); i++) {
			DealingDetail dealingDetail=new DealingDetail();	
			SubAccount subAccount=subAccountList.get(selectList.get(i));
			
			dealingDetail.setSubAccountType(subAccount.getName());
			dealingDetail.setSubAccountId(subAccount.getId());			
			dealingDetailList.add(dealingDetail);
		}
		return dealingDetailList;
	}
	
	//通过总帐户id查找子帐户
	public List<SubAccount> findSubAccount(int parentAccountId){
		return subAccountDAO.findSubAccount(parentAccountId);
	}
	
	
	public List<SubAccount> findSubAccount(Integer dealerId) {
		return subAccountDAO.findSubAccount(dealerId);
	}
	
	
	// 通过经销商、往来单位、单据类型查找对应的子帐户
	public List<SubAccount> findSubAccount(Integer dealerId,
			Integer dealingUnitId,String dealingType) {
		
		return subAccountDAO.findSubAccount(dealerId, dealingUnitId,dealingType);
	}
	
	public List<SubAccountType> findAbleOpenAccount(Integer dealerId,Integer unitId,String type){	
		List<SubAccountType> subAccountTypeList=subAccountTypeDAO.findAllType(dealerId.intValue());
		List<SubAccount> subAccountList=subAccountDAO.findAllSubAccount(dealerId, unitId, type);
		for(int i=subAccountTypeList.size()-1;i>=0;i--){
			for(int j=0;j<subAccountList.size();j++){
				if(subAccountList.get(j).getName().endsWith(subAccountTypeList.get(i).getName())){
					subAccountTypeList.remove(subAccountTypeList.get(i));
					break;
				}					
			}
		}
		return subAccountTypeList;
	}
	
	//DAO
	private SubAccountDAO subAccountDAO;
	public void setSubAccountDAO(SubAccountDAO subAccountDAO) {
		this.subAccountDAO = subAccountDAO;
	}
	
	private SubAccountTypeDAO subAccountTypeDAO;
	public void setSubAccountTypeDAO(SubAccountTypeDAO subAccountTypeDAO) {
		this.subAccountTypeDAO = subAccountTypeDAO;
	}
	
	private AccountDAO accountDAO;
	public void setAccountDAO(AccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}
}
