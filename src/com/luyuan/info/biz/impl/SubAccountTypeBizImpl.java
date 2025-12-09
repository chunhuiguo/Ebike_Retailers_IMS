package com.luyuan.info.biz.impl;

import java.util.List;

import com.luyuan.action.IFieldValidation;
import com.luyuan.info.biz.SubAccountTypeBiz;
import com.luyuan.info.dao.SubAccountTypeDAO;
import com.luyuan.info.entity.SubAccountType;



/**
 * 
 */

public class SubAccountTypeBizImpl implements SubAccountTypeBiz{
	
	public List<SubAccountType> findAllType(int dealerId){	
		return subAccountTypeDAO.findAllType(dealerId);
	}
	
	public void saveSubAccountType(SubAccountType subAccountType){
		subAccountTypeDAO.save(subAccountType);
	}
	
	public void delete(int id){
		subAccountTypeDAO.delete(subAccountTypeDAO.findById(id));
	}
	
	public boolean validation(IFieldValidation act, SubAccountType subAccountType,Integer dealerId){
		boolean isError = false;
		List<SubAccountType>  subAccountTypeList=subAccountTypeDAO.findAllType(dealerId);
		for (int i = 0; i < subAccountTypeList.size(); i++){	
			if (subAccountTypeList.get(i).getName().equals(subAccountType.getName())){
				act.addFieldError("subAccountType.name","该子账户类型已存在！");
				isError = true;
				break;
			}
		}
		if (isError)
			return false;
		else
			return true;
	}
	
	//dao
	private SubAccountTypeDAO subAccountTypeDAO;
	public void setSubAccountTypeDAO(SubAccountTypeDAO subAccountTypeDAO) {
		this.subAccountTypeDAO = subAccountTypeDAO;
	}
	
}