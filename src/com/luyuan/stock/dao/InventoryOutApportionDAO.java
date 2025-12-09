package com.luyuan.stock.dao;

import java.util.List;

import com.luyuan.stock.entity.InventoryOutApportion;

public interface InventoryOutApportionDAO {	
	
	public abstract void save(InventoryOutApportion transientInstance);
	
	public abstract List<InventoryOutApportion> findByVoucherDetailId(Object voucherDetailId);
}