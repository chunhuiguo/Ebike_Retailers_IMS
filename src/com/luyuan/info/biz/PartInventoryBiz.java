package com.luyuan.info.biz;

import java.util.List;

import com.luyuan.action.IFieldValidation;
import com.luyuan.deal.entity.VoucherDetail;
import com.luyuan.info.entity.Part;
import com.luyuan.info.entity.PartInventory;
import com.luyuan.info.entity.Product;
import com.luyuan.util.Page;

public interface PartInventoryBiz {
	//gch
	public abstract List<PartInventory> findByShopId(IFieldValidation act, int shopId);
	
	public abstract List<PartInventory> findByWarehouseId(IFieldValidation act, int warehouseId);
}
