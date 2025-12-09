/**
 * @(#)WarehouseAct.java  1.0 10/04/15
 *
 * Copyright 2010 luyuan-ebike, Inc. All rights reserved.
 * 
 */
package com.luyuan.info.action;

import java.util.List;

import org.springframework.context.ApplicationContext;

import com.luyuan.action.ListAct;
import com.luyuan.info.biz.WarehouseBiz;
import com.luyuan.info.entity.Warehouse;
import com.luyuan.sys.action.UnitListAct;
import com.luyuan.sys.entity.Unit;

/**
 * 
 * 仓库Warehouse的表现层action
 *
 * @author gch
 */

public class WarehouseAct extends ListAct {
	
	//zsh
	//库存仓库基本信息
	public String execute() throws Exception {		
		if(submit == null) {
			page.setListAct("warehouse.html");
			this.warehouseList(true);
			return "success";
		}
		if(submit.equals("添加"))
		{
			submit = null;
			return "save";
		}
			
		if(submit.equals("修改"))
		{
			submit = null;
			if(select != null) {
				warehouse = warehouseList.get(select);
				return "update";
			}
			return "success";			
		}
		
		if(submit.equals("查询"))
		{
			submit = null;
			return "select";
		}
		
		if(submit.equals("停用"))
		{
			submit = null;
			//停用代码
			if(select != null) {
				warehouse = warehouseList.get(select);
				warehouse.setDisable(false);
				warehouseBiz.update(warehouse);
				return "stop";
			}
			return "success";			
		}
		return "success";
	}
	
	public String unitListBackToSave() {
		UnitListAct unitListAct = (UnitListAct) ctx.getBean("unitListAct");
		Unit unit = unitListAct.getUnit();
		
		if(unit != null) {			
			warehouse.setShopId(unit.getId());
			warehouse.setShopShortName(unit.getShortName());
		}
		return "unitListBack";
	}
	
	public String unitListBackToUpdate() {
		UnitListAct unitListAct = (UnitListAct) ctx.getBean("unitListAct");
		Unit unit = unitListAct.getUnit();
		
		if(unit != null) {			
			warehouse.setShopId(unit.getId());
			warehouse.setShopShortName(unit.getShortName());
		}
		return "update";
	}	
	
	//添加
	public String save(){
		if(submit == null) {
			warehouse = new Warehouse();
			warehouse.setDisable(true);
			return "success";
		}
		if(submit.equals("选择门店")) {
			submit = null;
			return "unitDetail";
		}
		if(submit.equals("保存"))
		{
			submit = null;
			//保存代码
			if(this.validation()) {				
				warehouse.setDealerId((Integer) this.getSession().get("parentId"));
				warehouse.setDealerShortName((String) this.getSession().get("parentShortName"));
				warehouseBiz.save(warehouse);
				return "save";
			}
			return "success";
		}
		if(submit.equals("退出")) {
			submit = null;
			return "exit";
		}	
		return "success";
	}
	
	//修改
	public String update(){
		if(submit == null)
			return "success";		
		if(submit.equals("保存")) {
			submit = null;
			//修改代码
			if(this.validation()) {				
				warehouseBiz.update(warehouse);
				return "update";
			}
			return "success";
		}
		if(submit.equals("选择门店")) {
			submit = null;
			return "unitDetail";
		}
		if(submit.equals("退出")) {
			submit = null;
			return "exit";
		}
		return "success";
	}
	
	//查询入口
	public String selectInput() {
		page.setListAct("warehouseSelectInfoInput.html");
		this.warehouseList(true);		
		return "success";
	}
	
	//查询
	public String select(){
		page.setListAct("warehouseSelectInfo.html");
		if(submit == null) {
			this.warehouseList(false);
			return "success";
		}		
		if(submit.equals("筛选"))
		{
			submit = null;		
			this.warehouseList(false);
			return "success";
		}			
		if(submit.equals("修改"))
		{
			submit = null;
			if(select != null) {
				warehouse = warehouseList.get(select);
				return "update";
			}
			return "success";
		}
		if(submit.equals("退出")) {
			submit = null;
			return "exit";
		}
		return "success";
	}
	
	
	/**************************************************************************************************/
	/**************************************************************************************************/
	//private method
	
	//仓库列表
	private void warehouseList(boolean clearInputCondation) {		
		if(warehouseList != null)
			warehouseList.clear();
		select = null;	
		if(clearInputCondation) {
			if(page.getPropsNameList() != null)
				page.getPropsNameList().clear();
			if(page.getPropsValueList() != null)
				page.getPropsValueList().clear();
		}
		
		boolean isDealer = false;
		if( this.getSession().get("unitType").equals("经销商") )
			isDealer = true;
		
		warehouseList = warehouseBiz.findWarehouse(this, (Integer) this.getSession().get("unitId"), isDealer, true);		
	}
	
	private boolean validation() {
		boolean isError = false;
		if(warehouse.getName().equals("")) {
			isError = true;
			this.addFieldError("warehouse.name", "请输入仓库名称");			
		}
		if(warehouse.getShopId() == null) {
			isError = true;
			this.addFieldError("warehouse.unit", "请选择所属门店");
		}
		if(isError)
			return false;
		return true;
	}
	
	/**************************************************************************************************/	
	//page field
	private List<Warehouse> warehouseList;
	public List<Warehouse> getWarehouseList() {
		return warehouseList;
	}
	
	private String submit;
	public void setSubmit(String submit) {
		this.submit = submit;
	}
	
	private Warehouse warehouse;	
	public Warehouse getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}	
	
	//no ajax
	private Integer select;	
	public void setSelect(Integer select) {
		this.select = select;
	}
	
	//spring ApplicationContext
	private ApplicationContext ctx = this.getAppContext();

	//biz
	private WarehouseBiz warehouseBiz;
	public void setWarehouseBiz(WarehouseBiz warehouseBiz) {
		this.warehouseBiz = warehouseBiz;
	}
}