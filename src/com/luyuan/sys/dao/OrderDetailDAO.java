package com.luyuan.sys.dao;

import java.util.List;

import com.luyuan.sys.entity.OrderDetail;

public interface OrderDetailDAO {
	
	//gch
	public abstract List<OrderDetail> findByOrderId(Object orderId);
}