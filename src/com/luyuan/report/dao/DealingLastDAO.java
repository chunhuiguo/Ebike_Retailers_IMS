package com.luyuan.report.dao;

import java.util.List;

import com.luyuan.report.entity.DealingLast;




public interface DealingLastDAO   {
	
	public List<DealingLast> findByDealerId(Integer dealerId);
	
	public void update(DealingLast transientInstance);
	
	public void save(DealingLast transientInstance);
}