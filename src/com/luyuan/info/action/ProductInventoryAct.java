/**
 * @(#)UnitAct.java  1.0 10/04/15
 *
 * Copyright 2010 luyuan-ebike, Inc. All rights reserved.
 * 
 */
package com.luyuan.info.action;

import java.util.ArrayList;
import java.util.List;

import com.luyuan.action.ListAct;
import com.luyuan.info.biz.ProductInventoryBiz;
import com.luyuan.info.entity.ProductInventory;
import com.luyuan.util.Page;

/**
 *
 * @author gch
 */

public class ProductInventoryAct extends ListAct {
	
	public String list() {
		selectList = null;
		page =  new Page();
		page.setListAct("/info/productInventoryList!select.html");
		
		this.productInventoryList();
		return "success";
	}	
	
	/**************************************************************************************************/
	/**submit**/
	//确定
	public String confirm(){	
		if(selectList != null) {
			for (Integer i : selectList)
				productInventorySelectedList.add(productInventoryList.get(i));
		}
		return result;
	}
	
	//退出
	public String cancel(){
		productInventorySelectedList = null;
		return result;
	}	
	
	//筛选
	public String filter() {
		this.productInventoryList();
		return "success";
	}
	
	//翻页
	public String select(){
		this.productInventoryList();
		return "success";
	}	
	/**end submit**/
	/**************************************************************************************************/
	
		
	
	/**************************************************************************************************/
	/**************************************************************************************************/
	//private method
	
	//单据获得商品列表，除整车调拨入库单外
	private void productInventoryList() {
		if(productInventoryList != null)
			productInventoryList.clear();
		if(productInventorySelectedList != null)
			productInventorySelectedList.clear();
		if(selectList != null)
			selectList.clear();
		
		int shopId = (Integer) this.getSession().get("unitId");
		boolean isDealer = false;
		//经销商用户
		if(this.getSession().get("unitType").equals("经销商")) {			
			shopId = (Integer) this.getSession().get("parentId");
			isDealer = true;
		}
		
		if(result.equals("transferIn")) {
			this.getTransferInProductInventoryList(shopId, isDealer);
			return;
		}
		
		
		//单据的“仓库”已选择
		if(warehouseId != null) {
			productInventoryList = productInventoryBiz.findByWarehouseId(this, warehouseId);
			return;
		}		
		//单据的“仓库”未选择		
		productInventoryList = productInventoryBiz.findByShopId(this, shopId, isDealer);		
	}
	
	//整车调拨入库单获得商品列表
	private void getTransferInProductInventoryList(int shopId, boolean isDealer) {		
		if(dealingUnitId != null) {
			productInventoryList = productInventoryBiz.findByShopId(this, dealingUnitId, false);
			return;
		}
		if(warehouseId != null) {
			productInventoryList = productInventoryBiz.findByWarehouseId(this, warehouseId);
			return;
		}
		productInventoryList = productInventoryBiz.findByShopId(this, shopId, isDealer);
	}	

	
	/**************************************************************************************************/	
	//page field
	private List<ProductInventory> productInventoryList;
	public List<ProductInventory> getProductInventoryList() {
		return productInventoryList;
	}
	
	/**
	 * selectList选择的商品列表，用于添加一条至多条商品
	 * 在添加商品后清空
	 */
	private List<Integer> selectList;
	public void setSelectList(List<Integer> selectList) {
		this.selectList = selectList;
	}
	
	//用来保存选择的productInventorySelectedList信息
	//供需要的action获得
	private List<ProductInventory> productInventorySelectedList = new ArrayList<ProductInventory>();	
	public List<ProductInventory> getProductInventorySelectedList() {
		return productInventorySelectedList;
	}

	//用于接收别的action传过来的参数
	private String result;	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}


	private Integer warehouseId;	
	public Integer getWarehouseId() {
		return warehouseId;
	}	
	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}
	
	//往来单位Id，调拨入库单使用，从单据选择的链接当参数传递过来的
	private Integer dealingUnitId;
	public Integer getDealingUnitId() {
		return dealingUnitId;
	}	
	public void setDealingUnitId(Integer dealingUnitId) {
		this.dealingUnitId = dealingUnitId;
	}
	

	//biz
	private ProductInventoryBiz productInventoryBiz;
	public void setProductInventoryBiz(ProductInventoryBiz productInventoryBiz) {
		this.productInventoryBiz = productInventoryBiz;
	}		
}