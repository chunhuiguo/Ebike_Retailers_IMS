package com.luyuan.stock.dao;

import java.util.List;

import com.luyuan.action.IFieldValidation;
import com.luyuan.stock.entity.InventoryDetail;

public interface InventoryDetailDAO {
	
	public abstract void save(InventoryDetail transientInstance);
	
	public abstract List<InventoryDetail> findInventoryDetail(int shopId, int warehouseId, long productId);
	
	public abstract boolean findInventoryDetail(int warehouseId, long productId, double price);
	
	public abstract void update(InventoryDetail transientInstance);
	
	public abstract void delete(InventoryDetail persistentInstance);
	
	public abstract List<InventoryDetail> findInventoryDetail(final IFieldValidation act, final int warehouseId, final long productId);
}