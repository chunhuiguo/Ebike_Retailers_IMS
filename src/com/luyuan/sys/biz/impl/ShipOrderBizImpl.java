package com.luyuan.sys.biz.impl;

import java.util.List;

import com.luyuan.action.IFieldValidation;
import com.luyuan.sys.biz.ShipOrderBiz;
import com.luyuan.sys.dao.ShipOrderDAO;
import com.luyuan.sys.entity.ShipOrder;



public class ShipOrderBizImpl implements ShipOrderBiz {

	public List<ShipOrder> findShipOrder(IFieldValidation act, String dealerShortName, String warehouseCode, String orderStartDate, String orderEndDate) {
		return shipOrderDAO.findShipOrder(act, dealerShortName, warehouseCode, orderStartDate, orderEndDate);
	}
	
	//DAO
	private ShipOrderDAO shipOrderDAO;
	public void setShipOrderDAO(ShipOrderDAO shipOrderDAO) {
		this.shipOrderDAO = shipOrderDAO;
	}
}
