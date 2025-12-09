package com.luyuan.stock.biz;

import java.util.List;

import com.luyuan.action.IFieldValidation;
import com.luyuan.report.model.Report;
import com.luyuan.stock.entity.InventoryBook;
import com.luyuan.stock.entity.InventoryBookView;


public interface InventoryBookBiz  {	
	
	public abstract List<InventoryBookView> findInventoryBook(IFieldValidation act, int shopId, String startDate, String endDate, boolean isDealer);
	
	public abstract List<InventoryBookView> findInventoryBookWithShop(IFieldValidation act, int shopId, String startDate, String endDate);
	
	public abstract List<InventoryBookView> findInventoryBookWithWarehouse(IFieldValidation act, int warehouseId, String startDate, String endDate);
	
	public abstract List<InventoryBookView> findInventoryBook(IFieldValidation act, int shopId, int warehouseId, String startDate, String endDate);
	
//	public abstract List<InventoryBookView> findAllInventoryBook(IFieldValidation act, int shopId, String startDate, String endDate, boolean isDealer);
//	
//	public abstract List<InventoryBookView> findAllInventoryBookWithShop(IFieldValidation act, int shopId, String startDate, String endDate);
//	
//	public abstract List<InventoryBookView> findAllInventoryBookWithWarehouse(IFieldValidation act, int warehouseId, String startDate, String endDate);
//	
//	public abstract List<InventoryBookView> findAllInventoryBook(IFieldValidation act, int shopId, int warehouseId, String startDate, String endDate);
	
	public abstract List<Report> findInventoryBook(Integer shopId, String date);
	
	public InventoryBook[] findInventory( String checkDate,  int warehouseId);
	
	public InventoryBook[] findInventory( String beginDate, String endDate, int warehouseId);

}