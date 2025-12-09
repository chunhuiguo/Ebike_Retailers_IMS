/**
 * @(#)CourseAct.java  1.0 10/05/04
 *
 * Copyright 2010 luyuan-ebike, Inc. All rights reserved.
 * 
 */
package com.luyuan.deal.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.luyuan.action.ListAct;
import com.luyuan.deal.biz.VoucherBiz;
import com.luyuan.deal.entity.Voucher;
import com.luyuan.deal.entity.VoucherDetail;
import com.luyuan.util.TypeMap;

/**
 * 
 * 业务单据历史Course的表现层action
 *
 * @author gch
 */

public class CourseAct extends ListAct {

	//整车业务单据查询(仓管)菜单入口
	public String detail() {
		if(voucherList != null)
			voucherList.clear();			
		select = null;
		voucherType = null;
		errorType = null;
		viewPrice = false;
		
		if(this.getSession().get("unitType").equals("经销商"))
			isDealer = true;
		else
			isDealer = false;
		
		endDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		startDate = endDate.substring(0, 8) + "01";
		
		if(type.equals("整车"))
			page.setListAct("courseDetail.html");	
		else
			page.setListAct("pcourseDetail.html");
		voucherList = voucherBiz.findVoucher(this, (Integer) this.getSession().get("unitId"), true, startDate, endDate, isDealer);	
		
		return "success";
	}
	
	//整车业务单据查询(财务)菜单入口
	public String detailCheck() {
		if(voucherList != null)
			voucherList.clear();			
		select = null;
		voucherType = null;
		errorType = null;
		viewPrice = true;
		
		if(this.getSession().get("unitType").equals("经销商"))
			isDealer = true;
		else
			isDealer = false;
		
		endDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		startDate = endDate.substring(0, 8) + "01";
		
		if(type.equals("整车"))
			page.setListAct("courseDetailCheck.html");	
		else
			page.setListAct("pcourseDetailCheck.html");
		voucherList = voucherBiz.findVoucher(this, (Integer) this.getSession().get("unitId"), true, startDate, endDate, isDealer);	
		
		return "success";
	}
	
	//整车业务单据历史查询返回入口
	public String query() {				
		select = null;
		voucherType = null;
		errorType = null;
		
		if(this.getSession().get("unitType").equals("经销商"))
			isDealer = true;
		else
			isDealer = false;
		
		VoucherQueryAct voucherQueryAct;
		if(type.equals("整车"))
			voucherQueryAct = (VoucherQueryAct) ctx.getBean("voucherQueryAct");
		else
			voucherQueryAct = (VoucherQueryAct) ctx.getBean("pvoucherQueryAct");
		voucher = voucherQueryAct.getVoucher();
		startDate = voucherQueryAct.getVoucher().getCheckDate();
		endDate = voucherQueryAct.getVoucher().getCreateDate();
		voucherType = voucherQueryAct.getVoucher().getBrief();
		errorType = voucherQueryAct.getErrorType();
		if(type.equals("整车"))
			page.setListAct("courseQuery.html");
		else
			page.setListAct("pcourseQuery.html");
		voucherList = voucherBiz.findVoucher(this, (Integer) this.getSession().get("unitId"), voucher, isDealer);			
		
		return "success";
	}
	
	//整车业务单据历史提交入口
	public String execute() {
		if(this.getSession().get("unitType").equals("经销商"))
			isDealer = true;
		else
			isDealer = false;
		
		if(type.equals("整车"))
			page.setListAct("course.html");	
		else
			page.setListAct("pcourse.html");	
		
		if(submit.equals("查看")) {			
			if(select != null) {
				voucher = voucherList.get(select);
				return TypeMap.typeMap.get(voucher.getType());				
			}			
			return "success";
		}
		if(submit.equals("冲抵")) {			
			voucher = voucherList.get(select);
			
			if(! voucher.getIsError()) {				
				List<VoucherDetail> voucherDetailList = voucherBiz.findByVoucherId(voucher.getId());
				if(voucher.getType().equals(type + "进货单") || voucher.getType().equals(type + "销售退货单") || voucher.getType().equals(type + "调拨入库单") || voucher.getType().equals(type + "报溢单")) {					
					String errorMessage = voucherBiz.validation(this, voucher, voucherDetailList);
					if(! errorMessage.equals("")) {			
						this.addActionError(errorMessage);
						select = null;
						return "success";
					}
				}
				
				voucherBiz.saveAgainst(voucher, voucherDetailList, (Integer) this.getSession().get("userId"));
				return "chargeAgainst";
			}
			else {
				this.addFieldError("voucher.isError", "单据【" + voucher.getCode() + "】已经冲抵过了，不能再次冲抵");
				select = null;
			}
			
			return "success";
		}
		if(submit.equals("筛选")) {			
			if(voucherList != null)
				voucherList.clear();
			select = null;
			
			if(voucherType.equals("所有单据")) {
				if(errorType.equals("所有单据"))
					voucherList = voucherBiz.findVoucher(this, (Integer) this.getSession().get("unitId"), true, startDate, endDate, isDealer);
				if(errorType.equals("未冲抵单据"))
					voucherList = voucherBiz.findVoucher(this, (Integer) this.getSession().get("unitId"), true, false, startDate, endDate, isDealer);
				if(errorType.equals("冲抵单据"))		
					voucherList = voucherBiz.findVoucher(this, (Integer) this.getSession().get("unitId"), true, true, startDate, endDate, isDealer);
			}				
			else {
				if(errorType.equals("所有单据"))
					voucherList = voucherBiz.findVoucher(this, (Integer) this.getSession().get("unitId"), true, voucherType, startDate, endDate, isDealer);
				if(errorType.equals("未冲抵单据"))
					voucherList = voucherBiz.findVoucher(this, (Integer) this.getSession().get("unitId"), true, voucherType, false, startDate, endDate, isDealer);	
				if(errorType.equals("冲抵单据"))
					voucherList = voucherBiz.findVoucher(this, (Integer) this.getSession().get("unitId"), true, voucherType, true, startDate, endDate, isDealer);	
			}							
			
			return "success";
		}
		if(submit.equals("设置查询条件")) {		
			if(voucherList != null)
				voucherList.clear();
			return "voucherQuery";
		}
		
		return "success";
	}
	
	//class inside data
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
	
	private Integer select;		
	public void setSelect(Integer select) {
		this.select = select;
	}

	private String voucherType;
	public String getVoucherType() {
		return voucherType;
	}
	public void setVoucherType(String voucherType) {
		this.voucherType = voucherType;
	}
	
	private String errorType;
	public String getErrorType() {
		return errorType;
	}
	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}
	
	private String startDate;
	public String getStartDate() {
		return startDate;
	}
	
	private String endDate;
	public String getEndDate() {
		return endDate;
	}
	
	protected boolean viewPrice;
	public boolean isViewPrice() {
		return viewPrice;
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
	
	//spring ApplicationContext
	protected ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext( ServletActionContext.getServletContext());
	
	//biz	
	private VoucherBiz voucherBiz;
	public void setVoucherBiz(VoucherBiz voucherBiz) {
		this.voucherBiz = voucherBiz;
	}
}
