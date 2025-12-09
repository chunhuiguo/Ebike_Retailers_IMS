/**
 * @(#)UnitAct.java  1.0 10/04/18
 *
 * Copyright 2010 luyuan-ebike, Inc. All rights reserved.
 * 
 */
package com.luyuan.sys.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.luyuan.action.ListAct;
import com.luyuan.sys.biz.ShipOrderBiz;
import com.luyuan.sys.entity.ShipOrder;
import com.luyuan.util.Page;


public class ShipOrderAct extends ListAct {
	
	/**************************************************************************************************/
	/**
	 * 查询入口execute与选择入口list分开，确保redirect = null。
	 */
	public String execute() {	//shipOrder.html
		this.setBasicInfo();
		redirect = null;
		page = new Page();
		page.setListAct("shipOrder!select.html");
		this.getShipOrderListByLogin();
		return "success";
	}
	
	public String list(){		//shipOrder!list.html
		this.setBasicInfo();
		page = new Page();
		page.setListAct("shipOrder!select.html");
		this.getShipOrderListByLogin();
		return "success";
	}
	
	/**
	 * 分页提供选择
	 * 根据用户输入的条件进行筛选
	 */
	public String select(){
		this.getShipOrderListByLogin();
		return "success";
	}	
	
	/**************************************************************************************************/
	/**************************************************************************************************/
	//private method
	/**
	 * 根据登录用户列出对应发货单信息
	 */	
	private void getShipOrderListByLogin(){
		if (shipOrderList != null)			
			shipOrderList.clear();
		
		shipOrderList = shipOrderBiz.findShipOrder(this, dealerShortName, warehouseCode, orderStartDate, orderEndDate);
	}
	
	/**
	 * 设置基本参数，页面使用
	 */	
	private void setBasicInfo() {
		if(this.getSession().get("unitType").equals("直属门店"))
			dealerShortName = (String) this.getSession().get("parentShortName");
		else
			dealerShortName = (String) this.getSession().get("shortName");
		
		warehouseCode = "所有仓库";
		
		orderEndDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		orderStartDate = orderEndDate.substring(0, 8) + "01";
	}
	
	
	/**************************************************************************************************/
	//page field	
	private List<ShipOrder> shipOrderList;		
	public List<ShipOrder> getShipOrderList() {
		return shipOrderList;
	}
	
	private String warehouseCode;
	public String getWarehouseCode() {
		return warehouseCode;
	}
	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}
	
	private String orderStartDate;
	public String getOrderStartDate() {
		return orderStartDate;
	}
	public void setOrderStartDate(String orderStartDate) {
		this.orderStartDate = orderStartDate;
	}
	
	private String orderEndDate;
	public String getOrderEndDate() {
		return orderEndDate;
	}
	public void setOrderEndDate(String orderEndDate) {
		this.orderEndDate = orderEndDate;
	}
	
	private String dealerShortName;
	public String getDealerShortName() {
		return dealerShortName;
	}


	/**************************************************************************************************/
	//biz
	private ShipOrderBiz shipOrderBiz;
	public void setShipOrderBiz(ShipOrderBiz shipOrderBiz) {
		this.shipOrderBiz = shipOrderBiz;
	}			
}