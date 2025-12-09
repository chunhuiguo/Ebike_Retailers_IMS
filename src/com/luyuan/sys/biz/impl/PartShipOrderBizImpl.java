package com.luyuan.sys.biz.impl;

import java.util.List;

import com.luyuan.action.IFieldValidation;
import com.luyuan.sys.biz.PartShipOrderBiz;
import com.luyuan.sys.dao.PartShipOrderDAO;
import com.luyuan.sys.entity.PartShipOrder;



public class PartShipOrderBizImpl implements PartShipOrderBiz {

	public List<PartShipOrder> findShipOrder(IFieldValidation act, String dealerShortName) {
		return partShipOrderDAO.findShipOrder(act, dealerShortName);
	}
	
	//DAO
	private PartShipOrderDAO partShipOrderDAO;
	public void setPartShipOrderDAO(PartShipOrderDAO partShipOrderDAO) {
		this.partShipOrderDAO = partShipOrderDAO;
	}
}
