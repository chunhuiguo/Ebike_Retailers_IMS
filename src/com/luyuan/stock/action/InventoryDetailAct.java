/**
 * @(#)InventoryAct.java  1.0 10/05/19
 *
 * Copyright 2010 luyuan-ebike, Inc. All rights reserved.
 * 
 */
package com.luyuan.stock.action;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;

import com.luyuan.action.ListAct;
import com.luyuan.info.action.EmployeeListAct;
import com.luyuan.info.biz.PartBiz;
import com.luyuan.info.biz.ProductBiz;
import com.luyuan.info.biz.WarehouseBiz;
import com.luyuan.info.entity.Part;
import com.luyuan.info.entity.Product;
import com.luyuan.info.entity.Warehouse;
import com.luyuan.stock.biz.InventoryBiz;
import com.luyuan.stock.entity.Inventory;
import com.luyuan.stock.entity.InventoryDetail;
import com.luyuan.sys.biz.UnitBiz;
import com.luyuan.sys.entity.Unit;
import com.luyuan.util.Page;

/**
 * 
 * 库存Invnetory的表现层action
 *
 * @author gch
 */

public class InventoryDetailAct extends ListAct {
	
	//进入
	public String input() {
		if(inventoryDetailList != null)
			inventoryDetailList.clear();		
		
		if(type.equals("整车"))
			page.setListAct("inventoryDetail.html");
		else
			page.setListAct("pinventoryDetail.html");
		
		InventoryAct inventoryAct;
		if(type.equals("整车"))
			inventoryAct = (InventoryAct)ctx.getBean("inventoryAct");
		else
			inventoryAct = (InventoryAct)ctx.getBean("pinventoryAct");		
		
		inventory = inventoryAct.getInventoryList().get(inventoryAct.getSelect());
		viewPrice = inventoryAct.isViewPrice();
		
		if(type.equals("整车"))
			product = productBiz.findById(inventory.getProductId());
		else
			part = partBiz.findById(inventory.getProductId());
		
		inventoryDetailList = inventoryBiz.findInventoryDetail(this, inventory.getWarehouseId(), inventory.getProductId());		
				
		return "success";
	}
	
	//退出
	public String exit() {
		return "exit";
	}
	
	//翻页
	public String execute() {
		if(inventoryDetailList != null)
			inventoryDetailList.clear();		
		
		inventoryDetailList = inventoryBiz.findInventoryDetail(this, inventory.getWarehouseId(), inventory.getProductId());				
		
		return "success";
	}
	
	//class inside data	
	private Inventory inventory;
		
	//page	
	private List<InventoryDetail> inventoryDetailList;
	public List<InventoryDetail> getInventoryDetailList() {
		return inventoryDetailList;
	}	
	
	private String type;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	private boolean viewPrice;
	public boolean isViewPrice() {
		return viewPrice;
	}
	
	private Product product;
	public Product getProduct() {
		return product;
	}
	
	private Part part;
	public Part getPart() {
		return part;
	}
	
	//spring ApplicationContext
	protected ApplicationContext ctx = this.getAppContext();

	//biz 
	private InventoryBiz inventoryBiz;
	public void setInventoryBiz(InventoryBiz inventoryBiz) {
		this.inventoryBiz = inventoryBiz;
	}	
	
	private ProductBiz productBiz;
	public void setProductBiz(ProductBiz productBiz) {
		this.productBiz = productBiz;
	}
	
	private PartBiz partBiz;
	public void setPartBiz(PartBiz partBiz) {
		this.partBiz = partBiz;
	}
}
