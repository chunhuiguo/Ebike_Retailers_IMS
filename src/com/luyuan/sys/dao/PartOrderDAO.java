package com.luyuan.sys.dao;

import java.util.List;

import com.luyuan.sys.entity.PartOrder;

public interface PartOrderDAO {
	
	//gch
	public abstract List<PartOrder> findByShipOrderId(Object shipOrderId);	
}