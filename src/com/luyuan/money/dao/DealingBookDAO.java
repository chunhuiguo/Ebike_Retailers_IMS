package com.luyuan.money.dao;

import java.util.List;

import com.luyuan.action.IFieldValidation;
import com.luyuan.money.entity.DealingBook;
import com.luyuan.util.Page;


/**
 * 
 */

public interface DealingBookDAO  {

	public void save(DealingBook dealingBook);
	
	public List<DealingBook> findBySubAccountId(int subAccountId);
	
	public DealingBook findById(java.lang.Long id);
	
	public List<DealingBook> findByType(IFieldValidation act, int subAccountId,String type);

	public List getDealingReport(final Integer dealerId,final String beginDate,final String endDate);

	public List getPDealingReport(final Integer dealerId,final String beginDate,final String endDate);

	
}