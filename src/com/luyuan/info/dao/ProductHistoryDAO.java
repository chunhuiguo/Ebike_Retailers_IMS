package com.luyuan.info.dao;

import com.luyuan.info.entity.ProductHistory;
import com.luyuan.info.entity.ProductInWarehouse;


public interface ProductHistoryDAO {   
	
	public abstract void save(final ProductInWarehouse productInWarehouse);
	
	public abstract ProductHistory findByProductBarcode(String productBarcode);
}