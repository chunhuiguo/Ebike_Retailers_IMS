/**
 * @(#)ProductBizImpl.java  1.0 10/04/12
 *
 * Copyright 2010 luyuan-ebike, Inc. All rights reserved.
 * 
 */
package com.luyuan.info.biz.impl;

import java.util.List;

import com.luyuan.action.IFieldValidation;
import com.luyuan.info.biz.ProductInventoryBiz;
import com.luyuan.info.dao.ProductInventoryDAO;
import com.luyuan.info.entity.ProductInventory;
import com.luyuan.util.Page;

/**
 * 
 * 商品Product的业务逻辑层biz
 *
 * @author gch
 */

public class ProductInventoryBizImpl implements ProductInventoryBiz {

	
	//gch
	public List<ProductInventory> findByShopId(IFieldValidation act, int shopId, boolean isDealer) {
		if(isDealer)
			return productInventoryDAO.findByDealerId(act, shopId);
		return productInventoryDAO.findByShopId(act, shopId);
	}
	
	public List<ProductInventory> findByWarehouseId(IFieldValidation act, int warehouseId) {
		return productInventoryDAO.findByWarehouseId(act, warehouseId);
	}
	
	//DAO
	private ProductInventoryDAO productInventoryDAO;
	public void setProductInventoryDAO(ProductInventoryDAO productInventoryDAO) {
		this.productInventoryDAO = productInventoryDAO;
	}
}
