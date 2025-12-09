package com.luyuan.info.biz.impl;

import java.util.List;

import com.luyuan.action.IFieldValidation;
import com.luyuan.info.biz.PartInventoryBiz;
import com.luyuan.info.dao.PartInventoryDAO;
import com.luyuan.info.entity.PartInventory;
import com.luyuan.util.Page;

public class PartInventoryBizImpl implements PartInventoryBiz {

	//gch
	public List<PartInventory> findByShopId(IFieldValidation act, int shopId) {
		return partInventoryDAO.findByShopId(act, shopId);
	}
	
	public List<PartInventory> findByWarehouseId(IFieldValidation act, int warehouseId) {
		return partInventoryDAO.findByWarehouseId(act, warehouseId);
	}
	
	//DAO
	private PartInventoryDAO partInventoryDAO;
	public void setPartInventoryDAO(PartInventoryDAO partInventoryDAO) {
		this.partInventoryDAO = partInventoryDAO;
	}
}
