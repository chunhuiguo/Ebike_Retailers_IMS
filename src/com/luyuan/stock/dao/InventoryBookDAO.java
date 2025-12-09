package com.luyuan.stock.dao;

import java.util.List;


import com.luyuan.action.IFieldValidation;
import com.luyuan.report.model.MonthInventory;

import com.luyuan.report.model.MonthlySales;

import com.luyuan.report.model.Report;
import com.luyuan.report.model.DayInventory;
import com.luyuan.stock.entity.InventoryBook;
import com.luyuan.util.Page;

public interface InventoryBookDAO {
	
	//gch
	public abstract void save(InventoryBook transientInstance);
	
	public abstract List<InventoryBook> findInventoryBook(String voucherCode);
	
	public abstract List<InventoryBook> findInventoryBook(int warehouseId, long productId);
	
	public abstract List<InventoryBook> findInventoryBook(final IFieldValidation act,  final int shopId, final String startDate, final String endDate, final boolean isDealer);

	public abstract List<InventoryBook> findInventoryBookWithShop(final IFieldValidation act, final int shopId, final String startDate, final String endDate);
	
	public abstract List<InventoryBook> findInventoryBookWithWarehouse(final IFieldValidation act, final int warehouseId, final String startDate, final String endDate);
	
	public abstract List<InventoryBook> findInventoryBook(final IFieldValidation act, final int shopId, final int warehouseId, final String startDate, final String endDate);
	
	public abstract List<InventoryBook> findAllInventoryBook(final IFieldValidation act,  final int shopId, final String startDate, final String endDate, final boolean isDealer);

	public abstract List<InventoryBook> findAllInventoryBookWithShop(final IFieldValidation act, final int shopId, final String startDate, final String endDate);
	
	public abstract List<InventoryBook> findAllInventoryBookWithWarehouse(final IFieldValidation act, final int warehouseId, final String startDate, final String endDate);
	
	public abstract List<InventoryBook> findAllInventoryBook(final IFieldValidation act, final int shopId, final int warehouseId, final String startDate, final String endDate);
	
	//ldf
	public abstract List<Report> findInventoryBook(Integer shopId, String date);

//	public abstract List<MonthInventory> findInventory(Integer warehouseId, String date);
	public List findInventory( String checkDate,  int warehouseId);
    public List findInventory(final String beginDate,final String endDate,final int warehouseId);


    }