package com.luyuan.info.dao;

import java.util.List;

import com.luyuan.info.entity.InsuranceCard;


public interface InsuranceCardDAO {   
	
	public abstract void save(InsuranceCard transientInstance);
	
	public abstract List<InsuranceCard> findByProductbarcode(String productBarcode);
}