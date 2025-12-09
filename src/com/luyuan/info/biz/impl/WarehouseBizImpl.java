/**
 * @(#)WarehouseBizImpl.java  1.0 10/04/13
 *
 * Copyright 2010 luyuan-ebike, Inc. All rights reserved.
 * 
 */
package com.luyuan.info.biz.impl;

import java.util.ArrayList;
import java.util.List;

import com.luyuan.action.IFieldValidation;
import com.luyuan.info.biz.WarehouseBiz;
import com.luyuan.info.dao.WarehouseDAO;
import com.luyuan.info.entity.Warehouse;
import com.luyuan.util.Page;

/**
 * 
 * 仓库Warehouse的业务逻辑层biz
 *
 * @author gch
 */

public class WarehouseBizImpl implements WarehouseBiz {


	
	//gch
	public List<Warehouse> findWarehouse(IFieldValidation act, int shopId, boolean isDealer, boolean viewDisabled) {
		return warehouseDAO.findWarehouse(act, shopId, isDealer, viewDisabled);
	}
	
	public List<Warehouse> findWarehouse(int unitId, boolean isDealer) {
		List<Warehouse> warehouseList;
		
		if(isDealer)
			warehouseList = warehouseDAO.findByDealerId(unitId);
		else
			warehouseList = warehouseDAO.findByShopId(unitId);		
		
		return warehouseList;
	}
	
	//zsh
	public List findAll() {
		// TODO Auto-generated method stub
		return warehouseDAO.findAll();
	}
	
	public void save(Warehouse warehouse){
		warehouseDAO.save(warehouse);
	}
	
	public Warehouse findById(Integer id){
		return warehouseDAO.findById(id);
	}
	
	public void update(Warehouse warehouse){
		warehouseDAO.update(warehouse);
	}
	
	public List findByProperty(String propertyName, Object value){
		return warehouseDAO.findByProperty(propertyName, value);
	}
	public List<Warehouse> findByDealerId(Integer dealerId) {
		// TODO Auto-generated method stub
		return warehouseDAO.findByDealerId(dealerId);
	}

	//DAO
	private WarehouseDAO warehouseDAO;
	public void setWarehouseDAO(WarehouseDAO warehouseDAO) {
		this.warehouseDAO = warehouseDAO;
	}

}
