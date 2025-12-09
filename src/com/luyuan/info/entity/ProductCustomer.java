package com.luyuan.info.entity;

import java.sql.Timestamp;

/**
 * ProductCustomer entity. @author MyEclipse Persistence Tools
 */

public class ProductCustomer implements java.io.Serializable {

	// Fields

	private Integer productCustomerId;
	private String productBarcode;
	private String dealerCode;
	private String dealerName;
	private String productPrefixName;
	private String productSuffixName;
	private String productSpecName;
	private String wheelSpecCode;
	private String wheelDiameter;
	private String colorCode;
	private String colorName;
	private String productTypeName;
	private String controlBarcode;
	private String frameBarcode;
	private String hubBarcode;
	private String listCategoryCode;
	private String listCategoryName;
	private String listCategory;
	private String serviceCardNumber;
	private String customerName;
	private String customerSex;
	private String customerAddress;
	private String customerPhone;
	private String customerCellPhone;
	private String customerIdentification;
	private Timestamp buyDate;
	private Timestamp inputDate;
	private String shipOrderCode;
	private String productionPlace;
	private Timestamp inspectionDate;
	private String productionLine;
	private String warehouseCode;
	private String orgDealerId;
	private String productCode;

	// Constructors

	/** default constructor */
	public ProductCustomer() {
	}

	/** minimal constructor */
	public ProductCustomer(String productBarcode) {
		this.productBarcode = productBarcode;
	}

	/** full constructor */
	public ProductCustomer(String productBarcode, String dealerCode,
			String dealerName, String productPrefixName,
			String productSuffixName, String productSpecName,
			String wheelSpecCode, String wheelDiameter, String colorCode,
			String colorName, String productTypeName, String controlBarcode,
			String frameBarcode, String hubBarcode, String listCategoryCode,
			String listCategoryName, String listCategory,
			String serviceCardNumber, String customerName, String customerSex,
			String customerAddress, String customerPhone,
			String customerCellPhone, String customerIdentification,
			Timestamp buyDate, Timestamp inputDate, String shipOrderCode,
			String productionPlace, Timestamp inspectionDate,
			String productionLine, String warehouseCode, String orgDealerId,
			String productCode) {
		this.productBarcode = productBarcode;
		this.dealerCode = dealerCode;
		this.dealerName = dealerName;
		this.productPrefixName = productPrefixName;
		this.productSuffixName = productSuffixName;
		this.productSpecName = productSpecName;
		this.wheelSpecCode = wheelSpecCode;
		this.wheelDiameter = wheelDiameter;
		this.colorCode = colorCode;
		this.colorName = colorName;
		this.productTypeName = productTypeName;
		this.controlBarcode = controlBarcode;
		this.frameBarcode = frameBarcode;
		this.hubBarcode = hubBarcode;
		this.listCategoryCode = listCategoryCode;
		this.listCategoryName = listCategoryName;
		this.listCategory = listCategory;
		this.serviceCardNumber = serviceCardNumber;
		this.customerName = customerName;
		this.customerSex = customerSex;
		this.customerAddress = customerAddress;
		this.customerPhone = customerPhone;
		this.customerCellPhone = customerCellPhone;
		this.customerIdentification = customerIdentification;
		this.buyDate = buyDate;
		this.inputDate = inputDate;
		this.shipOrderCode = shipOrderCode;
		this.productionPlace = productionPlace;
		this.inspectionDate = inspectionDate;
		this.productionLine = productionLine;
		this.warehouseCode = warehouseCode;
		this.orgDealerId = orgDealerId;
		this.productCode = productCode;
	}

	// Property accessors

	public Integer getProductCustomerId() {
		return this.productCustomerId;
	}

	public void setProductCustomerId(Integer productCustomerId) {
		this.productCustomerId = productCustomerId;
	}

	public String getProductBarcode() {
		return this.productBarcode;
	}

	public void setProductBarcode(String productBarcode) {
		this.productBarcode = productBarcode;
	}

	public String getDealerCode() {
		return this.dealerCode;
	}

	public void setDealerCode(String dealerCode) {
		this.dealerCode = dealerCode;
	}

	public String getDealerName() {
		return this.dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	public String getProductPrefixName() {
		return this.productPrefixName;
	}

	public void setProductPrefixName(String productPrefixName) {
		this.productPrefixName = productPrefixName;
	}

	public String getProductSuffixName() {
		return this.productSuffixName;
	}

	public void setProductSuffixName(String productSuffixName) {
		this.productSuffixName = productSuffixName;
	}

	public String getProductSpecName() {
		return this.productSpecName;
	}

	public void setProductSpecName(String productSpecName) {
		this.productSpecName = productSpecName;
	}

	public String getWheelSpecCode() {
		return this.wheelSpecCode;
	}

	public void setWheelSpecCode(String wheelSpecCode) {
		this.wheelSpecCode = wheelSpecCode;
	}

	public String getWheelDiameter() {
		return this.wheelDiameter;
	}

	public void setWheelDiameter(String wheelDiameter) {
		this.wheelDiameter = wheelDiameter;
	}

	public String getColorCode() {
		return this.colorCode;
	}

	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}

	public String getColorName() {
		return this.colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public String getProductTypeName() {
		return this.productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}

	public String getControlBarcode() {
		return this.controlBarcode;
	}

	public void setControlBarcode(String controlBarcode) {
		this.controlBarcode = controlBarcode;
	}

	public String getFrameBarcode() {
		return this.frameBarcode;
	}

	public void setFrameBarcode(String frameBarcode) {
		this.frameBarcode = frameBarcode;
	}

	public String getHubBarcode() {
		return this.hubBarcode;
	}

	public void setHubBarcode(String hubBarcode) {
		this.hubBarcode = hubBarcode;
	}

	public String getListCategoryCode() {
		return this.listCategoryCode;
	}

	public void setListCategoryCode(String listCategoryCode) {
		this.listCategoryCode = listCategoryCode;
	}

	public String getListCategoryName() {
		return this.listCategoryName;
	}

	public void setListCategoryName(String listCategoryName) {
		this.listCategoryName = listCategoryName;
	}

	public String getListCategory() {
		return this.listCategory;
	}

	public void setListCategory(String listCategory) {
		this.listCategory = listCategory;
	}

	public String getServiceCardNumber() {
		return this.serviceCardNumber;
	}

	public void setServiceCardNumber(String serviceCardNumber) {
		this.serviceCardNumber = serviceCardNumber;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerSex() {
		return this.customerSex;
	}

	public void setCustomerSex(String customerSex) {
		this.customerSex = customerSex;
	}

	public String getCustomerAddress() {
		return this.customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerPhone() {
		return this.customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getCustomerCellPhone() {
		return this.customerCellPhone;
	}

	public void setCustomerCellPhone(String customerCellPhone) {
		this.customerCellPhone = customerCellPhone;
	}

	public String getCustomerIdentification() {
		return this.customerIdentification;
	}

	public void setCustomerIdentification(String customerIdentification) {
		this.customerIdentification = customerIdentification;
	}

	public Timestamp getBuyDate() {
		return this.buyDate;
	}

	public void setBuyDate(Timestamp buyDate) {
		this.buyDate = buyDate;
	}

	public Timestamp getInputDate() {
		return this.inputDate;
	}

	public void setInputDate(Timestamp inputDate) {
		this.inputDate = inputDate;
	}

	public String getShipOrderCode() {
		return this.shipOrderCode;
	}

	public void setShipOrderCode(String shipOrderCode) {
		this.shipOrderCode = shipOrderCode;
	}

	public String getProductionPlace() {
		return this.productionPlace;
	}

	public void setProductionPlace(String productionPlace) {
		this.productionPlace = productionPlace;
	}

	public Timestamp getInspectionDate() {
		return this.inspectionDate;
	}

	public void setInspectionDate(Timestamp inspectionDate) {
		this.inspectionDate = inspectionDate;
	}

	public String getProductionLine() {
		return this.productionLine;
	}

	public void setProductionLine(String productionLine) {
		this.productionLine = productionLine;
	}

	public String getWarehouseCode() {
		return this.warehouseCode;
	}

	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}

	public String getOrgDealerId() {
		return this.orgDealerId;
	}

	public void setOrgDealerId(String orgDealerId) {
		this.orgDealerId = orgDealerId;
	}

	public String getProductCode() {
		return this.productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

}