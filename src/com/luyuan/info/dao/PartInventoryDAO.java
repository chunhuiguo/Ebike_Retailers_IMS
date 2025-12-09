package com.luyuan.info.dao;

import java.util.List;

import com.luyuan.action.IFieldValidation;
import com.luyuan.info.entity.PartInventory;
import com.luyuan.util.Page;


public interface PartInventoryDAO {

	public abstract List<PartInventory> findByShopId(final IFieldValidation act, final int shopId);
	
	public abstract List<PartInventory> findByWarehouseId(final IFieldValidation act, final int warehouseId);
}
