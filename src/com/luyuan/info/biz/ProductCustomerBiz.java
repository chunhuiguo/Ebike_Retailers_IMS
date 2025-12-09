package com.luyuan.info.biz;

import java.util.List;

import com.luyuan.action.IFieldValidation;
import com.luyuan.info.entity.ProductCustomer;



public interface ProductCustomerBiz  {
	
	public abstract List<ProductCustomer> findProductCustomer(IFieldValidation act, String dealerCode, String buyStartDate, String buyEndDate, String inputStartDate, String inputEndDate, boolean history);
	
	public abstract int obtainTotalCount(IFieldValidation act, String dealerCode, String buyStartDate, String buyEndDate, String inputStartDate, String inputEndDate, boolean history);
}