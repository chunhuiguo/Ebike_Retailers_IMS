package com.luyuan.sys.dao;

import java.util.List;

import com.luyuan.sys.entity.Order;

public interface OrderDAO {
	
	//gch
	public abstract List<Order> findByShipOrderId(Object shipOrderId);	
}