package com.luyuan.info.biz;

import java.util.List;

import com.luyuan.info.entity.SubAccount;
import com.luyuan.info.entity.SubAccountType;
import com.luyuan.money.entity.DealingDetail;

public interface SubAccountBiz {	
	
	//gch
	public abstract SubAccount findSubAccountForVoucher(int dealerId, int dealingUnitId, String prefix, String type);
	
	//wml
	public List<DealingDetail> selectSubAccount(List<Integer> selectList,
			List<SubAccount> subAccountList);
	
	public List<SubAccount> findSubAccount(int parentAccountId);
	
	public List<SubAccount> findSubAccount(Integer dealerId);
	
	public List<SubAccountType> findAbleOpenAccount(Integer dealerId,Integer unitId,String type);
	
	public List<SubAccount> findSubAccount(Integer dealerId,Integer dealingUnitId,String dealingType);
}