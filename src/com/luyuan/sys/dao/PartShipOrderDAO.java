package com.luyuan.sys.dao;

import java.util.List;

import com.luyuan.action.IFieldValidation;
import com.luyuan.sys.entity.PartShipOrder;

public interface PartShipOrderDAO {
	
	//gch
	public abstract List<PartShipOrder> findShipOrder(final IFieldValidation act, final String dealerShortName);
}