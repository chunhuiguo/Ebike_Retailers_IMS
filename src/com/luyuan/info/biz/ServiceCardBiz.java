package com.luyuan.info.biz;

import java.sql.Timestamp;
import java.util.List;

import com.luyuan.info.entity.InsuranceCard;
import com.luyuan.info.entity.ProductCustomer;
import com.luyuan.info.entity.ProductInWarehouse;
import com.luyuan.info.entity.TblSMToSend;



public interface ServiceCardBiz  {
	public abstract ProductInWarehouse findProductInWarehouse(String productBarcode);
	
	public abstract void save(ProductInWarehouse productInWarehouse, ProductCustomer productCustomer, InsuranceCard selfInsuranceCard, InsuranceCard otherInsuranceCard, List<TblSMToSend> tblSMToSendList);
	
	public abstract List<InsuranceCard> findInsuranceCard(String productBarcode);
	
	public abstract String validationProductCustomer(String productBarcode);
	
	public abstract String validationProductCustomerHistory(String productBarcode);
	
	public abstract boolean isProductSaled(int dealerId, String productBarcode);
}