package com.luyuan.sys.dao;

import java.util.List;

import com.luyuan.sys.entity.PartOrderDetail;

public interface PartOrderDetailDAO {
	
	//gch
	public abstract List<PartOrderDetail> findByOrderId(Object orderId);
}