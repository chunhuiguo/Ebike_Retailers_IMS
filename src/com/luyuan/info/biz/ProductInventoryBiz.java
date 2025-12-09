package com.luyuan.info.biz;

import java.util.List;

import com.luyuan.action.IFieldValidation;
import com.luyuan.info.entity.ProductInventory;
import com.luyuan.util.Page;

public interface ProductInventoryBiz {	
	
	//gch
	public abstract List<ProductInventory> findByShopId(IFieldValidation act, int shopId, boolean isDealer);
	
	public abstract List<ProductInventory> findByWarehouseId(IFieldValidation act, int warehouseId);

}