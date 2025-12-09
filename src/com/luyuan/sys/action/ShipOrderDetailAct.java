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

import com.luyuan.action.OptAct;
import com.luyuan.info.biz.ProductBiz;
import com.luyuan.info.entity.Product;
import com.luyuan.sys.entity.ShipOrder;
import com.luyuan.util.Page;
import com.luyuan.util.SelectHelper;


public class ShipOrderDetailAct extends OptAct {
		
	public String execute() {	
		if( select == null )	//未选或发货单明细入口异常，不做改变
			return "success";
		
		ShipOrder shipOrder = SelectHelper.selectShipOrder(select);	//选择
		
//		if(this.getSession().get("unitType").equals("直属门店"))
//			dealerShortName = (String) this.getSession().get("parentShortName");
//		else
//			dealerShortName = (String) this.getSession().get("shortName");		
//		shipOrderCode = shipOrder.getShipOrderCode();
		productBarcodes = shipOrder.getTxtCode().split(",");
		
		productList = productBiz.findProduct(productBarcodes);
		
		select = null;
		return "success";
	}
	
	
	/**************************************************************************************************/
	//page field	
//	private String dealerShortName;
//	public String getDealerShortName() {
//		return dealerShortName;
//	}
//	
//	private String shipOrderCode;
//	public String getShipOrderCode() {
//		return shipOrderCode;
//	}
	
	private String [] productBarcodes;
	public String[] getProductBarcodes() {
		return productBarcodes;
	}
	
	private List<Product> productList;
	public List<Product> getProductList() {
		return productList;
	}


	/**************************************************************************************************/
	//biz
	private ProductBiz productBiz;
	public void setProductBiz(ProductBiz productBiz) {
		this.productBiz = productBiz;
	}			
}