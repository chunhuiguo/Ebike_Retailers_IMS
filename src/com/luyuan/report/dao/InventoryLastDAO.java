package com.luyuan.report.dao;
import java.util.List;

import com.luyuan.report.entity.InventoryLast;

public interface InventoryLastDAO  {
	

	public abstract void save(InventoryLast inventoryLast);
	
	public abstract void update(InventoryLast inventoryLast);
	
	public abstract InventoryLast findInventoryLast(int warehouseId, long productId);
		
	public abstract List<InventoryLast> findByDealerId(Integer shopId);
	
}