package com.luyuan.info.dao;

import java.util.List;

import com.luyuan.info.entity.SubAccount;

public interface SubAccountDAO {
	
	//gch
	public abstract SubAccount findSubAccountForVoucher(int dealerId, int dealingUnitId, String prefix, String type);
	
	public abstract SubAccount findById(java.lang.Integer id);
	
	public abstract void update(SubAccount transientInstance);
	
	//wml
	public List<SubAccount> findSubAccount(Integer dealerId);	
	
	public List<SubAccount> findSubAccount(Integer dealerId,Integer dealingUnitId,String dealingType);
			
	public void save(SubAccount subAccount);
	
	public SubAccount findSubAccount(int dealerId,int dealingUnitId,String type,String name);
	
	public List<SubAccount> findSubAccount(int parentAccountId);
	
	public List<SubAccount> findAllSubAccount(Integer dealerId,
			Integer dealingUnitId,String type);
}