/**
 * @(#)PartAct.java  1.0 10/05/11
 *
 * Copyright 2010 luyuan-ebike, Inc. All rights reserved.
 * 
 */
package com.luyuan.info.action;

import java.util.ArrayList;
import java.util.List;

import com.luyuan.action.ListAct;
import com.luyuan.info.biz.PartInventoryBiz;
import com.luyuan.info.entity.PartInventory;
import com.luyuan.util.Page;

/**
 * 
 * 配件Part的表现层action
 *
 * @author gch
 */

public class PartInventoryAct extends ListAct {
	
	public String list() {
		selectList = null;
		page =  new Page();
		page.setListAct("/info/partInventoryList!select.html");
		if(result.startsWith("p") && ! result.startsWith("pu"))
			part = true;
		else
			part = false;
		
		this.partInventoryList();
		return "success";
	}
	
	/**************************************************************************************************/
	/**submit**/
	//确定
	public String confirm(){		
		if(selectList != null) {
			for (Integer i : selectList)
				partInventorySelectedList.add(partInventoryList.get(i));
		}
		return result;
	}
	
	//退出
	public String cancel(){
		partInventorySelectedList = null;
		return result;
	}	
	
	//筛选
	public String filter() {
		this.partInventoryList();
		return "success";
	}
	
	//翻页
	public String select(){
		this.partInventoryList();
		return "success";
	}	
	/**end submit**/
	/**************************************************************************************************/
		
	
	/**************************************************************************************************/
	/**************************************************************************************************/
	//private method
	
	//单据获得商品列表，除调拨入库单外
	private void partInventoryList() {
		if(partInventoryList != null)
			partInventoryList.clear();
		if(partInventorySelectedList != null)
			partInventorySelectedList.clear();
		
		if(result.endsWith("transferIn")) {
			this.getTransferInPartInventoryList();
			return;
		}
		
		if(warehouseId != null) {
			partInventoryList = partInventoryBiz.findByWarehouseId(this, warehouseId);;
			return;
		}
		partInventoryList = partInventoryBiz.findByShopId(this, (Integer) this.getSession().get("unitId"));;		
	}	
	
	//调拨入库单获得商品列表
	private void getTransferInPartInventoryList() {		
		if(dealingUnitId != null) {
			partInventoryList = partInventoryBiz.findByShopId(this, dealingUnitId);
			return;
		}
		if(warehouseId != null) {
			partInventoryList = partInventoryBiz.findByWarehouseId(this, warehouseId);
			return;
		}
		partInventoryList = partInventoryBiz.findByShopId(this, (Integer) this.getSession().get("unitId"));
	}	
	
	
	/**************************************************************************************************/	
	//page field
	private List<PartInventory> partInventoryList;	
	public List<PartInventory> getPartInventoryList() {
		return partInventoryList;
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
	private List<PartInventory> partInventorySelectedList = new ArrayList<PartInventory>();	
	public List<PartInventory> getPartInventorySelectedList() {
		return partInventorySelectedList;
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
	
	private Integer dealingUnitId;
	public Integer getDealingUnitId() {
		return dealingUnitId;
	}
	
	public void setDealingUnitId(Integer dealingUnitId) {
		this.dealingUnitId = dealingUnitId;
	}
	
	
	//配件单据还是整车单据选择组件
	private boolean part;	
	public boolean isPart() {
		return part;
	}

	//biz
	private PartInventoryBiz partInventoryBiz;
	public void setPartInventoryBiz(PartInventoryBiz partInventoryBiz) {
		this.partInventoryBiz = partInventoryBiz;
	}		
}