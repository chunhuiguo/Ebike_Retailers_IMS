/**
 * @(#)VoucherQueryAct.java  1.0 10/06/11
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
import com.luyuan.deal.entity.Voucher;
import com.luyuan.info.action.EmployeeListAct;
import com.luyuan.info.action.WarehouseListAct;
import com.luyuan.info.entity.Employee;
import com.luyuan.info.entity.SubAccount;
import com.luyuan.info.entity.Warehouse;
import com.luyuan.sys.action.UnitListAct;
import com.luyuan.sys.entity.Unit;

/**
 * 
 * 单据查询VoucherQuery的表现层action
 *
 * @author gch
 */

public class VoucherQueryAct extends ListAct {
	
	//整车业务单据历史查询进入入口
	public String input() {	
		voucher = new Voucher();
		voucher.setType(type + "单据查询");
		String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		voucher.setCheckDate(currentDate.substring(0, 8) + "01");
		voucher.setCreateDate(currentDate);
		return "success";
	}
	
	//整车业务单据历史查询选择员工的返回入口
	public String employeeListBack() {
		EmployeeListAct employeeListAct = (EmployeeListAct)ctx.getBean("employeeListAct");
		Employee employee = employeeListAct.getEmployee();
		if(employee != null) {
			voucher.setHandlerId( employee.getId() );
			voucher.setHandlerName( employee.getName() );
		}
		return "success";
	}
	
	//整车业务单据历史查询选择仓库返回入口
	public String warehouseListBack() {
		WarehouseListAct warehouseListAct = (WarehouseListAct)ctx.getBean("warehouseListAct");
		Warehouse warehouse = warehouseListAct.getWarehouse();
		if(warehouse != null) {
			voucher.setWarehouseId(warehouse.getId());
			voucher.setWarehouseName(warehouse.getName());
		}
		return "success";
	}
	
	//整车业务单据历史查询选择往来单位返回入口
	public String unitListBack() {
		UnitListAct unitListAct = (UnitListAct) ctx.getBean("unitListAct");
		Unit unit = unitListAct.getUnit();
		if(unit != null) {
			voucher.setDealingUnitId( unit.getId() );
			voucher.setDealingUnitShortName( unit.getShortName() );	
		}		
		return "success";	
	}
	
	//整车业务单据历史查询提交入口
	public String execute() {			
		if(submit.equals("选择往来单位"))	
			return "unitDetail";
		if(submit.equals("选择经手人"))
			return "employeeDetail";
		if(submit.equals("选择仓库"))
			return "warehouseDetail";	
		if(submit.equals("确定")) {
			if(this.validation()) {				
				if(errorType.equals("所有单据"))
					voucher.setIsError(null);
				if(errorType.equals("未冲抵单据"))
					voucher.setIsError(false);
				if(errorType.equals("冲抵单据"))
					voucher.setIsError(true);
				return "OK";
			}
			return "success";
		}
		if(submit.equals("重置"))
			this.input();		
		if(submit.equals("退出")) {
			CourseAct courseAct;
			if(type.equals("整车"))
				courseAct = (CourseAct) ctx.getBean("dealCourseAct");
			else
				courseAct = (CourseAct) ctx.getBean("pdealCourseAct");
			boolean viewPrice = courseAct.isViewPrice();
			if(viewPrice)
				return "exitCheck";
			else
				return "exit";
		}			
		return "success";
	}
	
	//class inside method
	private boolean validation() {
		if(voucher.getCheckDate().equals("")) {
			this.addFieldError("voucher.checkDate", "开始日期不能为空");
			return false;
		}
		if(voucher.getCreateDate().equals("")) {
			this.addFieldError("voucher.createDate", "结束日期不能为空");
			return false;
		}
		if(voucher.getCheckDate().compareTo(voucher.getCreateDate()) > 0) {
			this.addFieldError("voucher.createDate", "开始日期不能大于结束日期");
			return false;
		}
		return true;
	}	
	
	//page
	private Voucher voucher;	
	public Voucher getVoucher() {
		return voucher;
	}
	public void setVoucher(Voucher voucher) {
		this.voucher = voucher;
	}
	
	private List<Voucher> voucherList;
	public List<Voucher> getVoucherList() {
		return voucherList;
	}
	
	private String errorType;
	public String getErrorType() {
		return errorType;
	}
	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}
	
	private String type;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	//spring ApplicationContext
	protected ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext( ServletActionContext.getServletContext());

	//submit
	private String submit;	
	public void setSubmit(String submit) {
		this.submit = submit;
	}
}
