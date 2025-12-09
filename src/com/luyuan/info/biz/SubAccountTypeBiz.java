package com.luyuan.info.biz;

import java.util.List;

import com.luyuan.action.IFieldValidation;
import com.luyuan.info.entity.SubAccountType;



public interface SubAccountTypeBiz {
	public List<SubAccountType> findAllType(int dealerId);
	
	public void saveSubAccountType(SubAccountType subAccountType);
	
	public void delete(int id);
	
	public boolean validation(IFieldValidation act, SubAccountType subAccountType,Integer dealerId);
}