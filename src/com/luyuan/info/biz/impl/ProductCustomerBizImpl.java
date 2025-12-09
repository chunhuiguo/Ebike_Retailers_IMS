package com.luyuan.info.biz.impl;

import java.util.List;

import com.luyuan.action.IFieldValidation;
import com.luyuan.info.biz.ProductCustomerBiz;
import com.luyuan.info.dao.ProductCustomerDAO;
import com.luyuan.info.entity.ProductCustomer;


public class ProductCustomerBizImpl implements ProductCustomerBiz {
	
	public List<ProductCustomer> findProductCustomer(IFieldValidation act, String dealerCode, String buyStartDate, String buyEndDate, String inputStartDate, String inputEndDate, boolean history) {
		//if(history)
			//return productCustomerHistoryDAO.findProductCustomer(act, dealerCode, buyStartDate, buyEndDate, inputStartDate, inputEndDate, regainCount);
		return productCustomerDAO.findProductCustomer(act, dealerCode, buyStartDate, buyEndDate, inputStartDate, inputEndDate, history);
	}	
	
	public int obtainTotalCount(IFieldValidation act, String dealerCode, String buyStartDate, String buyEndDate, String inputStartDate, String inputEndDate, boolean history) {
		return productCustomerDAO.obtainTotalCount(act, dealerCode, buyStartDate, buyEndDate, inputStartDate, inputEndDate, history);
	}
	
	//dao	
	private ProductCustomerDAO productCustomerDAO;
	public void setProductCustomerDAO(ProductCustomerDAO productCustomerDAO) {
		this.productCustomerDAO = productCustomerDAO;
	}
	
	private ProductCustomerDAO productCustomerHistoryDAO;
	public void setProductCustomerHistoryDAO(
			ProductCustomerDAO productCustomerHistoryDAO) {
		this.productCustomerHistoryDAO = productCustomerHistoryDAO;
	}
}