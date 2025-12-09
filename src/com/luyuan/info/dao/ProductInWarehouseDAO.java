package com.luyuan.info.dao;

import java.sql.Timestamp;

import com.luyuan.info.entity.ProductInWarehouse;


public interface ProductInWarehouseDAO {   
	
	public abstract ProductInWarehouse findByProductBarcode(String productBarcode);
	
	public abstract void deleteByProductId(final Integer productId);
}