package com.luyuan.info.dao;

import java.util.List;

import com.luyuan.info.entity.SubAccountType;



/**
 * 
 */

public interface SubAccountTypeDAO  {
	
	public List<SubAccountType> findAllType(int dealerId);
	
	public void save(SubAccountType subAccountType);
	
	public void delete(SubAccountType subAccountType);
	
	public SubAccountType findById( java.lang.Integer id);
}