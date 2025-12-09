package com.luyuan.sys.biz;

import java.util.List;

import com.luyuan.action.IFieldValidation;
import com.luyuan.sys.entity.PartShipOrder;


public interface PartShipOrderBiz {
	
	public abstract List<PartShipOrder> findShipOrder(IFieldValidation act, String dealerShortName);
}