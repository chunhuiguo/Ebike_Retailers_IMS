package com.luyuan.info.biz.impl;

import java.util.List;

import com.luyuan.deal.dao.VoucherEXTDAO;
import com.luyuan.info.biz.ServiceCardBiz;
import com.luyuan.info.dao.InsuranceCardDAO;
import com.luyuan.info.dao.ProductCustomerDAO;
import com.luyuan.info.dao.ProductDAO;
import com.luyuan.info.dao.ProductHistoryDAO;
import com.luyuan.info.dao.ProductInWarehouseDAO;
import com.luyuan.info.dao.TblSMToSendDAO;
import com.luyuan.info.entity.InsuranceCard;
import com.luyuan.info.entity.ProductCustomer;
import com.luyuan.info.entity.ProductInWarehouse;
import com.luyuan.info.entity.TblSMToSend;


public class ServiceCardBizImpl implements ServiceCardBiz{
	
	public ProductInWarehouse findProductInWarehouse(String productBarcode) {
		return productInWarehouseDAO.findByProductBarcode(productBarcode);
	}	
	
	public void save(ProductInWarehouse productInWarehouse, ProductCustomer productCustomer, InsuranceCard selfInsuranceCard, InsuranceCard otherInsuranceCard, List<TblSMToSend> tblSMToSendList) {	
		productHistoryDAO.save(productInWarehouse);
		productInWarehouseDAO.deleteByProductId(productInWarehouse.getProductId());
		productCustomerDAO.save(productCustomer);		
		if(selfInsuranceCard.getCustomerName() != null &&(! selfInsuranceCard.getCustomerName().trim().equals(""))) {
			selfInsuranceCard.setBuyDate(productCustomer.getBuyDate());
			selfInsuranceCard.setProductBarCode(productInWarehouse.getProductBarcode());
			selfInsuranceCard.setInsuranceType("第三方责任综合险");
			insuranceCardDAO.save(selfInsuranceCard);
		}
		if(otherInsuranceCard.getCustomerName() != null &&(! otherInsuranceCard.getCustomerName().trim().equals(""))) {
			otherInsuranceCard.setBuyDate(productCustomer.getBuyDate());
			otherInsuranceCard.setProductBarCode(productInWarehouse.getProductBarcode());
			otherInsuranceCard.setInsuranceType("第三方责任综合险");
			insuranceCardDAO.save(otherInsuranceCard);
		}
		if(tblSMToSendList != null) {			
			for(TblSMToSend tblSMToSend : tblSMToSendList)
				tblSMToSendDAO.save(tblSMToSend);
		}
	}	
	
	public List<InsuranceCard> findInsuranceCard(String productBarcode) {
		return insuranceCardDAO.findByProductbarcode(productBarcode);
	}
	
	//校验ProductCustomer表里是否有记录
	public String validationProductCustomer(String productBarcode) {
		String errorMessage = "";
		if(productCustomerDAO.findByProductBarcode(productBarcode) != null) {
			errorMessage = "该车已经录入服务卡信息了，不能再次录入，请输入正确的整车编码";
			return errorMessage;
		}		
		return errorMessage;
	}
	
	//校验ProductCustomerHistory表里是否有记录
	public String validationProductCustomerHistory(String productBarcode) {
		String errorMessage = "";		
		if(productCustomerHistoryDAO.findByProductBarcode(productBarcode) != null) {
			errorMessage = "该车已经录入服务卡信息了，不能再次录入，请输入正确的整车编码";
			return errorMessage;
		}		
		return errorMessage;
	}
	
	//校验VoucherEXT表中是否有此条码卖出的记录，没有提示先录销售单
	public boolean isProductSaled(int dealerId, String productBarcode) {
		return voucherEXTDAO.hasProductBarcode(dealerId, productBarcode);
	}
	
	//dao
	private InsuranceCardDAO insuranceCardDAO;
	public void setInsuranceCardDAO(InsuranceCardDAO insuranceCardDAO) {
		this.insuranceCardDAO = insuranceCardDAO;
	}
	
	private ProductCustomerDAO productCustomerDAO;
	public void setProductCustomerDAO(ProductCustomerDAO productCustomerDAO) {
		this.productCustomerDAO = productCustomerDAO;
	}
	
	private ProductCustomerDAO productCustomerHistoryDAO;	
	public void setProductCustomerHistoryDAO(
			ProductCustomerDAO productCustomerHistoryDAO) {
		this.productCustomerHistoryDAO = productCustomerHistoryDAO;
	}

	private ProductInWarehouseDAO productInWarehouseDAO;
	public void setProductInWarehouseDAO(ProductInWarehouseDAO productInWarehouseDAO) {
		this.productInWarehouseDAO = productInWarehouseDAO;
	}
	
	private ProductHistoryDAO productHistoryDAO;
	public void setProductHistoryDAO(ProductHistoryDAO productHistoryDAO) {
		this.productHistoryDAO = productHistoryDAO;
	}
	
	private ProductDAO productDAO;
	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}
	
	private TblSMToSendDAO tblSMToSendDAO;
	public void setTblSMToSendDAO(TblSMToSendDAO tblSMToSendDAO) {
		this.tblSMToSendDAO = tblSMToSendDAO;
	}
	
	private VoucherEXTDAO voucherEXTDAO;
	public void setVoucherEXTDAO(VoucherEXTDAO voucherEXTDAO) {
		this.voucherEXTDAO = voucherEXTDAO;
	}
}