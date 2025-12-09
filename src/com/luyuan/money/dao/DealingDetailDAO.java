package com.luyuan.money.dao;

import java.util.List;

import com.luyuan.money.entity.DealingDetail;



/**
 * 
 */

public interface DealingDetailDAO  {

	public void save(DealingDetail dealingDetail);
	
	public void saveorupdate(DealingDetail dealingDetail);
	
	public void delete(DealingDetail dealingDetail);
	
	public List<DealingDetail> findByParentDealingId(long parentDealingId);
}