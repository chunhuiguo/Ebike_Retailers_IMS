/**
 * @(#)DraftAct.java  1.0 10/05/04
 *
 * Copyright 2010 luyuan-ebike, Inc. All rights reserved.
 * 
 */
package com.luyuan.deal.action;

import java.util.ArrayList;
import java.util.List;

import com.luyuan.action.ListAct;
import com.luyuan.deal.biz.VoucherBiz;
import com.luyuan.deal.entity.Voucher;
import com.luyuan.util.TypeMap;

/**
 * 
 * 未处理业务单据Draft的表现层action
 *
 * @author gch
 */

public class DraftAct extends ListAct {

	//整车未处理业务单据(仓管)菜单入口
	public String detail() {
		if(voucherList != null)
			voucherList.clear();
		if(selectList != null)
			selectList.clear();
		voucherType = null;	
		viewPrice = false;
		check = false;
		
		if(this.getSession().get("unitType").equals("经销商"))
			isDealer = true;
		else
			isDealer = false;

		if(type.equals("整车"))
			page.setListAct("draftDetail.html");
		else
			page.setListAct("pdraftDetail.html");
		voucherList = voucherBiz.findVoucher(this, (Integer) this.getSession().get("unitId"), false, null, null, isDealer);
		
		return "success";
	}
	
	//整车未处理业务单据(财务)菜单入口
	public String detailCheck() {
		if(voucherList != null)
			voucherList.clear();
		if(selectList != null)
			selectList.clear();
		voucherType = null;	
		viewPrice = true;
		check = true;
		
		if(this.getSession().get("unitType").equals("经销商"))
			isDealer = true;
		else
			isDealer = false;

		if(type.equals("整车"))
			page.setListAct("draftDetailCheck.html");
		else
			page.setListAct("pdraftDetailCheck.html");
		voucherList = voucherBiz.findVoucher(this, (Integer) this.getSession().get("unitId"), false, null, null, isDealer);
		
		return "success";
	}
	
	//整车未处理业务单据提交入口
	public String execute() {		
		if(this.getSession().get("unitType").equals("经销商"))
			isDealer = true;
		else
			isDealer = false;
		
		if(type.equals("整车"))
			page.setListAct("draft.html");
		else
			page.setListAct("pdraft.html");
		
		if(submit.equals("修改")) {			
			if(selectList != null && selectList.size() != 0) {
				voucher = voucherList.get(selectList.get(0));
				return TypeMap.typeMap.get(voucher.getType());				
			}			
			return "success";
		}
		if(submit.equals("删除")) {			
			if(selectList != null && selectList.size() != 0) {
				List<Long> voucherIdList = new ArrayList<Long>();
				for(int i = 0; i < selectList.size(); i++)
					voucherIdList.add(voucherList.get(selectList.get(i)).getId());
				voucherBiz.delete(voucherIdList);				
				if(viewPrice)
					return "deleteCheck";
				else
					return "delete";
			}
			
			return "success";
		}
		if(submit.equals("筛选")) {			
			if(voucherList != null)
				voucherList.clear();
			if(selectList != null)
				selectList.clear();
			
			if(voucherType.equals("所有单据"))
				voucherList = voucherBiz.findVoucher(this, (Integer) this.getSession().get("unitId"), false, null, null, isDealer);
			else
				voucherList = voucherBiz.findVoucher(this, (Integer) this.getSession().get("unitId"), false, voucherType, null, null, isDealer);			
			
			return "success";
		}
		return "success";
	}
	
	//class inside data
	private boolean viewPrice;
	public boolean isViewPrice() {
		return viewPrice;
	}
	
	private boolean check;	
	public boolean isCheck() {
		return check;
	}

	private Voucher voucher;	
	public Voucher getVoucher() {
		return voucher;
	}
	
	private boolean isDealer = false;

	//page
	private List<Voucher> voucherList;
	public List<Voucher> getVoucherList() {
		return voucherList;
	}
	
	private List<Integer> selectList;
	public void setSelectList(List<Integer> selectList) {
		this.selectList = selectList;
	}
	
	private String voucherType;
	public String getVoucherType() {
		return voucherType;
	}
	public void setVoucherType(String voucherType) {
		this.voucherType = voucherType;
	}	
	
	private String type;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	//submit
	private String submit;
	public void setSubmit(String submit) {
		this.submit = submit;
	}
	
	//biz
	private VoucherBiz voucherBiz;
	public void setVoucherBiz(VoucherBiz voucherBiz) {
		this.voucherBiz = voucherBiz;
	}
}
