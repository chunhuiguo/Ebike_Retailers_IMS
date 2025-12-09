/**
 * @(#)UnitAct.java  1.0 10/04/15
 *
 * Copyright 2010 luyuan-ebike, Inc. All rights reserved.
 * 
 */
package com.luyuan.info.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.luyuan.action.BaseAct;
import com.luyuan.info.biz.AccountManageBiz;
import com.luyuan.info.biz.SubAccountBiz;
import com.luyuan.info.entity.SubAccount;
import com.luyuan.money.action.PaymentAct;
import com.luyuan.money.action.ReceiptAct;
import com.luyuan.money.entity.Dealing;
import com.luyuan.money.entity.DealingDetail;
import com.luyuan.util.TypeMap;
import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * 往来单位Unit的表现层action
 *
 * 
 */

public class SubAccountAct extends BaseAct {
	
	public String paymentSubAccountList(){
		
		PaymentAct paymentAct=(PaymentAct)ctx.getBean("paymentAct");
		dealing=paymentAct.getDealing();
		dealingDetailList=paymentAct.getDealingDetailList();
		
		this.subAccountList();
		return "success";
		
	}
	public String receiptSubAccountList(){
		
		ReceiptAct receiptAct=(ReceiptAct)ctx.getBean("receiptAct");
		dealing=receiptAct.getDealing();
		dealingDetailList=receiptAct.getDealingDetailList();
		
		this.subAccountList();
		return "success";
		
	}
	
//	public String manageSubAccountList(){
//		
//		AccountManageAct accountManageAct=(AccountManageAct)ctx.getBean("accountManageAct");
//		Integer select=accountManageAct.getSelect();
//		List<Account> accountList=accountManageAct.getAccountList();
//		subAccountList=subAccountBiz.findSubAccount(accountList.get(select).getId().intValue());
//		return "success";
//	}
	
	//***********************************************
	public void subAccountList(){
		
		if(subAccountList!=null){
			subAccountList.clear();
		}
		
		if(selectList!=null){
			selectList.clear();
		}
				
		if (dealing.getDealingUnitId()!=null) {
			subAccountList = subAccountBiz.findSubAccount((Integer) this.getSession().get("unitId"), dealing
					.getDealingUnitId(),dealing.getType());
		} 
	
	}
	
	//***************************************************************
	public String subAccountSelect() {
		if (submit.equals("确定")) {
			submit = null;
			if (selectList != null && selectList.size() != 0) {
				dealingDetailList.addAll(subAccountBiz.selectSubAccount(
						selectList, subAccountList));

			}
			return TypeMap.typeMap.get(dealing.getType());

		}

		if (submit.equals("退出")) {
			submit = null;
			return TypeMap.typeMap.get(dealing.getType());
		}
		return "success";
	}
	
	//data inside
	private List<DealingDetail> dealingDetailList;
	private Dealing dealing;
	
	
	//spring ApplicationContext
	private ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext( ServletActionContext.getServletContext());	
	//page
	
	private List<Integer> selectList;
	public void setSelectList(List<Integer> selectList) {
		this.selectList = selectList;
	}
	
	private List<SubAccount> subAccountList;
	public List<SubAccount> getSubAccountList() {
		return subAccountList;
	}
	public void setSubAccountList(List<SubAccount> subAccountList) {
		this.subAccountList = subAccountList;
	}
	
	
	private String submit;
	public void setSubmit(String submit) {
		this.submit = submit;
	}
	
	// biz
	private SubAccountBiz subAccountBiz;
	public void setSubAccountBiz(SubAccountBiz subAccountBiz) {
		this.subAccountBiz = subAccountBiz;
	}

	private AccountManageBiz accountManageBiz;
	public void setAccountManageBiz(AccountManageBiz accountManageBiz) {
		this.accountManageBiz = accountManageBiz;
	}
	
	
}