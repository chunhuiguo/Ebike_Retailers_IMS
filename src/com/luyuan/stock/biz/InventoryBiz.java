package com.luyuan.stock.biz;

import java.util.List;

import com.luyuan.action.IFieldValidation;
import com.luyuan.report.entity.InventoryLast;
import com.luyuan.report.entity.PSIProductMonthlyReport;
import com.luyuan.stock.entity.Inventory;
import com.luyuan.stock.entity.InventoryDetail;
import com.luyuan.util.Page;

public interface InventoryBiz  {
	
	public abstract List<Inventory> findByShopId(IFieldValidation act, int shopId, boolean viewZero, boolean isDealer);
	
	public abstract List<Inventory> findInventory(IFieldValidation act, int warehouseId, boolean viewZero);
	
	public abstract List<Inventory> findInventory(IFieldValidation act, int shopId, int warehouseId, boolean viewZero);
	
	public abstract void saveInventoryIntial(List<Inventory> inventoryList, List<InventoryDetail> inventoryDetailList, long productId);
	
	public abstract boolean validation(IFieldValidation act, List<Integer> warehouseIdList, List<String> warehouseNameList, long productId, List<Double> priceList);
	
	public abstract List<InventoryDetail> findInventoryDetail(IFieldValidation act, int warehouseId, long productId);
	
	public abstract List<Inventory> findByWarehouse(int Warehouseid);
}