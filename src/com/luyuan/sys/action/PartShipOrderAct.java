/**
 * @(#)UnitAct.java  1.0 10/04/18
 *
 * Copyright 2010 luyuan-ebike, Inc. All rights reserved.
 * 
 */
package com.luyuan.sys.action;

import java.util.List;

import com.luyuan.action.ListAct;
import com.luyuan.sys.biz.PartShipOrderBiz;
import com.luyuan.sys.entity.PartShipOrder;
import com.luyuan.sys.entity.ShipOrder;
import com.luyuan.util.Page;


public class PartShipOrderAct extends ListAct {
	
	public String list() {
		select = null;
		page =  new Page();
		page.setListAct("/sys/partShipOrder!select.html");
		
		this.shipOrderList();
		return "success";
	}
	
	public String select() {
		if(holdShipOrder())
			return "ppurchase";
		this.shipOrderList();
		return "success";
	}
	
		
	/**************************************************************************************************/
	/**************************************************************************************************/
	//private method	

	private boolean holdShipOrder() {
		//翻页
		if( select == null )
			return false;
		//退出，返回
		if( select == -1) {
			partShipOrder = null;
			return true;
		}
		//确定
		partShipOrder = partShipOrderList.get(select);
		return true;
	}
	
	private void shipOrderList() {
		if (partShipOrderList != null)	
			partShipOrderList.clear();
		
		String dealerShortName = "";
		if(this.getSession().get("unitType").equals("直属门店"))
			dealerShortName = (String) this.getSession().get("parentShortName");
		else
			dealerShortName = (String) this.getSession().get("shortName");
		partShipOrderList = partShipOrderBiz.findShipOrder(this, dealerShortName);
	}
	
	
	/**************************************************************************************************/
	//page field
	
	/**
	 * 选中的条目的序号，从0开始
	 */
	private Integer select;	
	public void setSelect(Integer select) {
		this.select = select;
	}
	
	private List<PartShipOrder> partShipOrderList;		
	public List<PartShipOrder> getPartShipOrderList() {
		return partShipOrderList;
	}

	//用来保存选择的partShipOrder信息
	//供需要的action获得
	private PartShipOrder partShipOrder;	
	public PartShipOrder getPartShipOrder() {
		return partShipOrder;
	}


	/**************************************************************************************************/
	//biz
	private PartShipOrderBiz partShipOrderBiz;
	public void setPartShipOrderBiz(PartShipOrderBiz partShipOrderBiz) {
		this.partShipOrderBiz = partShipOrderBiz;
	}
		
}