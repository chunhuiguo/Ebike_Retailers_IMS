package com.luyuan.money.dao;

import java.util.List;

import com.luyuan.action.IFieldValidation;
import com.luyuan.money.entity.Dealing;
import com.luyuan.util.Page;



/**
 * 
 */

public interface DealingDAO  {

	public void save(Dealing dealing);
	
	public void update(Dealing dealing);
	
	public void delete(long id);
	
	public Dealing findByExample(Dealing dealing);
	
	public List<Dealing> findDealing(int dealerId, Dealing dealing);
	
	public List<Dealing> findDealing(IFieldValidation act, Integer dealerId,boolean isChecked);
	
	public List<Dealing> findDealing(IFieldValidation act, Integer dealerId,String type, boolean isChecked,String errorType);
	
	public List<Dealing> findDealing(IFieldValidation act, Integer dealerId,String type, boolean isChecked);
	
	public Dealing findById(java.lang.Long id);
	
	public void evcit(Dealing dealing);
	
	public List<Dealing> findDraft( Integer shopId,  String beginDate,  String endDate);
	
	public  List<Dealing> findDealing(IFieldValidation act, final int dealerId, Dealing dealing);
}