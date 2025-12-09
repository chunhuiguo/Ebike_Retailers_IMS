package com.luyuan.info.biz;

import java.util.List;

import com.luyuan.action.IFieldValidation;
import com.luyuan.info.entity.Warehouse;
import com.luyuan.util.Page;

public interface WarehouseBiz {		
	//gch
	public abstract List<Warehouse> findWarehouse(IFieldValidation act, int shopId, boolean isDealer, boolean viewDisabled);
	
	public abstract List<Warehouse> findWarehouse(int unitId, boolean isDealer);
	
	//zsh
	public void save(Warehouse warehouse);
	public List findAll();
	public Warehouse findById( Integer id);
	public void update(Warehouse warehouse);
	public List findByProperty(String propertyName, Object value);
	//chq
	public abstract List<Warehouse> findByDealerId(Integer dealerId);
}