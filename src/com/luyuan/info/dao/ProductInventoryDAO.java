package com.luyuan.info.dao;

import java.util.List;

import com.luyuan.action.IFieldValidation;
import com.luyuan.info.entity.ProductInventory;
import com.luyuan.util.Page;


public interface ProductInventoryDAO {

	public List<ProductInventory> findByShopId(final IFieldValidation act, final int shopId);
	
	public List<ProductInventory> findByDealerId(final IFieldValidation act, final int dealerId);
	
	public List<ProductInventory> findByWarehouseId(final IFieldValidation act, final int warehouseId);
}
