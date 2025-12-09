package com.luyuan.stock.dao;

import java.util.List;

import com.luyuan.action.IFieldValidation;
import com.luyuan.stock.entity.Inventory;
import com.luyuan.util.Page;

public interface InventoryDAO {
	
	public abstract Inventory findProduct(int shopId, int warehouseId, long productId);
	
	public abstract Inventory findProduct(int warehouseId, long productId);
	
	public abstract void update(Inventory transientInstance);
	
	public abstract void save(Inventory transientInstance);
	
	public abstract List<Inventory> findByShopId(final IFieldValidation act, final Object shopId, final boolean viewZero, final boolean isDealer);
	
	public abstract List<Inventory> findInventory(final IFieldValidation act, final int warehouseId, final boolean viewZero);
	
	public abstract List<Inventory> findInventory(final IFieldValidation act, final int shopId, final int warehouseId, final boolean viewZero);
	
	public abstract List<Inventory> findByWarehouse(int Warehouseid);
}