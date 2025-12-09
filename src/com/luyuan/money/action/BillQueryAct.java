/**
 * @(#)VoucherQueryAct.java  1.0 10/06/11
 *
 * Copyright 2010 luyuan-ebike, Inc. All rights reserved.
 * 
 */
package com.luyuan.money.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;

import com.luyuan.action.BaseAct;
import com.luyuan.money.biz.BillBiz;
import com.luyuan.money.entity.Dealing;
import com.luyuan.sys.action.UnitListAct;
import com.luyuan.sys.entity.Unit;
import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * 单据查询VoucherQuery的表现层action
 *
 * @author gch
 */

public class BillQueryAct extends BaseAct {
	
	//业务单据历史查询--进入入口
	public String input() {	
		dealing = new Dealing();
		dealing.setType("账款单据查询");
		String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		dealing.setCheckDate(currentDate.substring(0, 8) + "01");
		dealing.setCreateDate(currentDate);
		return "success";
	}
	
	//业务单据历史查询选择信息---返回入口
	public String infoBack() {		
		return "success";
	}
	
	public String unitListBack() {
		UnitListAct unitListAct = (UnitListAct)ctx.getBean("unitListAct");
		Unit unit = unitListAct.getUnit();
		if(unit != null) {
			dealing.setDealingUnitShortName(unit.getShortName());
			dealing.setDealingUnitId(unit.getId());
		}
		return "success";
	}
	
	//业务单据历史查询---提交入口
	public String execute() {		
		if(submit.equals("选择往来单位"))
			return "unitDetail";
		
		if(submit.equals("确定")) {
			if(errorType.equals("所有单据"))
				dealing.setIsError(null);
			if(errorType.equals("未冲抵单据"))
				dealing.setIsError(false);
			if(errorType.equals("冲抵单据"))
				dealing.setIsError(true);
			return "OK";
		}
		if(submit.equals("重置"))
			this.input();		
		if(submit.equals("退出"))
			return "exit";
		return "success";
	}
	
	//class inside method
//	private boolean validation() {
//		
//	}
	
	//session data
	private Map<String,Object> session = ActionContext.getContext().getSession();	
	private Integer shopId = (Integer) session.get("unitId");
	
	//page
	private Dealing dealing;		
	public Dealing getDealing() {
		return dealing;
	}
	public void setDealing(Dealing dealing) {
		this.dealing = dealing;
	}

	private List<Dealing> dealingList;
	public void setDealingList(List<Dealing> dealingList) {
		this.dealingList = dealingList;
	}
	public List<Dealing> getDealingList() {
		return dealingList;
	}

	private String errorType;
	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}
	
	//spring ApplicationContext
	private ApplicationContext ctx = this.getAppContext();

	//submit
	private String submit;	
	public void setSubmit(String submit) {
		this.submit = submit;
	}

	//biz
	private BillBiz billBiz;
	public void setBillBiz(BillBiz billBiz) {
		this.billBiz = billBiz;
	}
	
}
