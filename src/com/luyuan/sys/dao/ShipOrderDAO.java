package com.luyuan.sys.dao;

import java.util.List;

import com.luyuan.action.IFieldValidation;
import com.luyuan.sys.entity.ShipOrder;

public interface ShipOrderDAO {
	
	//gch
	public abstract List<ShipOrder> findShipOrder(final IFieldValidation act, final String dealerShortName, final String warehouseCode, final String orderStartDate, final String orderEndDate);
}