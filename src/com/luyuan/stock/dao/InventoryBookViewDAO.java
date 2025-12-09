package com.luyuan.stock.dao;

import java.util.List;

import com.luyuan.action.IFieldValidation;
import com.luyuan.stock.entity.InventoryBookView;

public interface InventoryBookViewDAO {
	
	//gch	
	public abstract List<InventoryBookView> findInventoryBook(final IFieldValidation act,  final int shopId, final String startDate, final String endDate, final boolean isDealer);

	public abstract List<InventoryBookView> findInventoryBookWithShop(final IFieldValidation act, final int shopId, final String startDate, final String endDate);
	
	public abstract List<InventoryBookView> findInventoryBookWithWarehouse(final IFieldValidation act, final int warehouseId, final String startDate, final String endDate);
	
	public abstract List<InventoryBookView> findInventoryBook(final IFieldValidation act, final int shopId, final int warehouseId, final String startDate, final String endDate);
}