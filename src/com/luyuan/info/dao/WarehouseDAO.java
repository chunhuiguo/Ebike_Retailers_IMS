package com.luyuan.info.dao;

import java.util.List;

import com.luyuan.action.IFieldValidation;
import com.luyuan.info.entity.Warehouse;
import com.luyuan.util.Page;

public interface WarehouseDAO {   
	//gch
	public abstract List<Warehouse> findWarehouse(final IFieldValidation act, final int shopId, final boolean isDealer, final boolean viewDisabled);
	
	public abstract List<Warehouse> findByDealerId(int dealerId);
	
	public abstract List<Warehouse> findByShopId(int shopId);
	
	//zsh
	public void save(Warehouse transientInstance);
	public List findAll();
	public Warehouse findById( java.lang.Integer id);
	public void update(Warehouse transientInstance);
	public List findByProperty(String propertyName, Object value);
}