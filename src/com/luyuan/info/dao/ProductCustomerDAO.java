package com.luyuan.info.dao;

import java.util.List;

import com.luyuan.action.IFieldValidation;
import com.luyuan.info.entity.ProductCustomer;


public interface ProductCustomerDAO {   
	
	public abstract void save(ProductCustomer transientInstance);
	
	public abstract ProductCustomer findByProductBarcode(String productBarcode);
	
	public abstract List<ProductCustomer> findProductCustomer(final IFieldValidation act, final String dealerCode, final String buyStartDate, final String buyEndDate, final String inputStartDate, final String inputEndDate, boolean history);
	
	public abstract int obtainTotalCount(IFieldValidation act, String dealerCode, String buyStartDate, String buyEndDate, String inputStartDate, String inputEndDate, boolean history);
}