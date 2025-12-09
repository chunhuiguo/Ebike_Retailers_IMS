package com.luyuan.sys.biz;

import java.util.List;

import com.luyuan.action.IFieldValidation;
import com.luyuan.sys.entity.ShipOrder;


public interface ShipOrderBiz {
	
	public abstract List<ShipOrder> findShipOrder(IFieldValidation act, String dealerShortName, String warehouseCode, String orderStartDate, String orderEndDate);
}