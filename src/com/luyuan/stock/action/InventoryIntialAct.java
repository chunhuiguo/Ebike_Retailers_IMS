/**
 * @(#)InventoryAct.java  1.0 10/06/29
 *
 * Copyright 2010 luyuan-ebike, Inc. All rights reserved.
 * 
 */
package com.luyuan.stock.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;

import com.luyuan.action.BaseAct;
import com.luyuan.info.action.WarehouseListAct;
import com.luyuan.info.biz.WarehouseBiz;
import com.luyuan.info.entity.Warehouse;
import com.luyuan.stock.biz.InventoryBiz;
import com.luyuan.stock.entity.Inventory;
import com.luyuan.stock.entity.InventoryDetail;

/**
 * 
 * 库存初始化InvnetoryIntial的表现层action
 *
 * @author gch
 */

public class InventoryIntialAct extends BaseAct {
	
	//整车库存初始化菜单入口
	public String input() {
		if(selectList != null)
			selectList.clear();
		if(inventoryDetailList != null)
			inventoryDetailList.clear();
		inventory = new Inventory();
		
		if(isSuccess) {
			this.addFieldError("inventory.success", "保存成功");
			isSuccess = false;
		}
		return "success";
	}
	
	//整车库存初始化选择基本信息返回入口
	public String infoBack() {
		if(selectList != null)
			selectList.clear();
		return "success";
	}
	
	public String warehouseListBack() {
		WarehouseListAct warehouseListAct = (WarehouseListAct)ctx.getBean("warehouseListAct");
		Warehouse warehouse = warehouseListAct.getWarehouse();
		if(warehouse != null) {
			InventoryDetail inventoryDetail = new InventoryDetail();
			inventoryDetail.setWarehouseId(warehouse.getId());
			inventoryDetail.setWarehouseName(warehouse.getName());
			inventoryDetail.setQty(1);
			inventoryDetailList.add(inventoryDetail);
		}
		return "success";
	}
	
	//整车库存初始化提交入口 
	public String execute() {	
		if(submit.equals("选择商品")) {	
			if(type.equals("整车"))
				return "productDetail";
			else
				return "partDetail";
		}
		if(submit.equals("添加仓库"))
			return "warehouseDetail";
		if(submit.equals("删除仓库")) {
			if(selectList != null && selectList.size() != 0) {									
				for(int i = selectList.size() - 1; i >= 0; i--) {					
					inventoryDetailList.remove(selectList.get(i).intValue());					
				}
				selectList.clear();
			}
			return "success";
		}
		if(submit.equals("计算金额")) {
			this.calculate();
			return "success";
		}
		if(submit.equals("确定")) {
			if(this.validation()) {
				//biz  validation()
				List<Integer> warehouseIdList = new ArrayList<Integer>();
				List<String> warehouseNameList = new ArrayList<String>();
				List<Double> priceList = new ArrayList<Double>();
				for(int i = 0; i < inventoryDetailList.size(); i++) {
					warehouseIdList.add(inventoryDetailList.get(i).getWarehouseId());
					warehouseNameList.add(inventoryDetailList.get(i).getWarehouseName());
					priceList.add(inventoryDetailList.get(i).getPrice());
				}
				if(! inventoryBiz.validation(this, warehouseIdList, warehouseNameList, inventory.getProductId(), priceList))
					return "success";
				
				this.calculate();
				
				boolean isHad = false;
				List<Inventory> inventoryList = new ArrayList<Inventory>();					
								
				for(int i = 0; i < inventoryDetailList.size(); i++) {	
					int shopId = warehouseBiz.findById(inventoryDetailList.get(i).getWarehouseId()).getShopId();
					String shopShortName = warehouseBiz.findById(inventoryDetailList.get(i).getWarehouseId()).getShopShortName();
					
					//库存明细
					inventoryDetailList.get(i).setDealerId((Integer) this.getSession().get("parentId"));
					inventoryDetailList.get(i).setShopId(shopId);
					inventoryDetailList.get(i).setDealerShortName((String) this.getSession().get("parentShortName"));
					inventoryDetailList.get(i).setShopShortName(shopShortName);
					inventoryDetailList.get(i).setProductId(inventory.getProductId());
					
					//库存，经销商给所有仓库一起初始化库存，库存按warehouseId合并
					isHad = false;
					for(int j = 0; j < inventoryList.size(); j++) {
						if(inventoryDetailList.get(i).getWarehouseId().equals(inventoryList.get(j).getWarehouseId())) {
							isHad = true;
							inventoryList.get(j).setQty(inventoryList.get(j).getQty() + inventoryDetailList.get(i).getQty());
							inventoryList.get(j).setTotal(inventoryList.get(j).getTotal() + inventoryDetailList.get(i).getTotal());
							inventoryList.get(j).setAveragePrice(inventoryList.get(j).getTotal() / inventoryList.get(j).getQty());
							break;
						}
					}
					if(! isHad) {
						Inventory inventoryNew = new Inventory();
						
						inventoryNew.setDealerId((Integer) this.getSession().get("parentId"));
						inventoryNew.setDealerShortName((String) this.getSession().get("parentShortName"));
						inventoryNew.setShopId(shopId);
						inventoryNew.setShopShortName(shopShortName);
						inventoryNew.setWarehouseId(inventoryDetailList.get(i).getWarehouseId());
						inventoryNew.setWarehouseName(inventoryDetailList.get(i).getWarehouseName());
						inventoryNew.setProductId(inventory.getProductId());
						inventoryNew.setProductCode(inventory.getProductCode());
						inventoryNew.setProductName(inventory.getProductName());
						inventoryNew.setProductColor(inventory.getProductColor());
						inventoryNew.setProductUnit(inventory.getProductUnit());
						inventoryNew.setQty(inventoryDetailList.get(i).getQty());
						inventoryNew.setTotal(inventoryDetailList.get(i).getTotal());
						inventoryNew.setAveragePrice(inventoryNew.getTotal() / inventoryNew.getQty());
						
						inventoryList.add(inventoryNew);
					}					
				}				
				
				inventoryBiz.saveInventoryIntial(inventoryList, inventoryDetailList, inventory.getProductId());
				isSuccess = true;
				return "OK";				
			}
			return "success";
		}
		if(submit.equals("放弃"))
			return "OK";
		return "success";
	}
	
	//class inside method
	private void calculate() {
		int qty = 0;
		double total = 0;
		for(int i = 0; i < inventoryDetailList.size(); i++) {
			
			if(inventoryDetailList.get(i).getQty() != null && inventoryDetailList.get(i).getPrice() != null) {					
				inventoryDetailList.get(i).setTotal(inventoryDetailList.get(i).getQty() * inventoryDetailList.get(i).getPrice());
				qty = qty + inventoryDetailList.get(i).getQty();
				total = total + inventoryDetailList.get(i).getQty() * inventoryDetailList.get(i).getPrice();					
			}
			else
				inventoryDetailList.get(i).setTotal(null);
		}			
		inventory.setQty(qty);
		inventory.setTotal(total);			
		
		if(selectList != null)
			selectList.clear();
	}
	
	private boolean validation() {
		boolean isError = false;
		
		if(inventory.getProductId() == null) {
			this.addFieldError("inventory.product", "请选择商品");
			isError = true;
		}
		
		if(inventoryDetailList.size() == 0) {
			this.addFieldError("inventoryDetail", "请添加仓库");
			isError = true;
		}
		else {
			for(int i = 0; i < inventoryDetailList.size(); i++) {
				if(inventoryDetailList.get(i).getQty() == null) {
					this.addFieldError("inventoryDetail.qty", "数量不能为空");
					isError = true;
					break;
				}
				if(inventoryDetailList.get(i).getPrice() == null) {
					this.addFieldError("inventoryDetail.price", "单价不能为空");
					isError = true;
					break;
				}
			}
		}
		
		if(isError)
			return false;
		return true;
	}
	
	//class inside data
	private boolean isSuccess = false;
	
	//page
	private Inventory inventory;
	public Inventory getInventory() {
		return inventory;
	}
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	
	private List<InventoryDetail> inventoryDetailList = new ArrayList<InventoryDetail>();
	public List<InventoryDetail> getInventoryDetailList() {
		return inventoryDetailList;
	}
	public void setInventoryDetailList(List<InventoryDetail> inventoryDetailList) {
		this.inventoryDetailList = inventoryDetailList;
	}
	
	private List<Integer> selectList;	
	public void setSelectList(List<Integer> selectList) {
		this.selectList = selectList;
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
	protected ApplicationContext ctx = this.getAppContext();
	
	//biz
	private InventoryBiz inventoryBiz;
	public void setInventoryBiz(InventoryBiz inventoryBiz) {
		this.inventoryBiz = inventoryBiz;
	}
	
	private WarehouseBiz warehouseBiz;
	public void setWarehouseBiz(WarehouseBiz warehouseBiz) {
		this.warehouseBiz = warehouseBiz;
	}
}
