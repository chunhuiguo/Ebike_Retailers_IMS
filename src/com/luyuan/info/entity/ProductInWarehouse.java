package com.luyuan.info.entity;

import java.sql.Timestamp;

/**
 * ProductInWarehouse entity. @author MyEclipse Persistence Tools
 */

public class ProductInWarehouse implements java.io.Serializable {

	// Fields

	private Integer productId;
	private String productBarcode;
	private String productCode;
	private String warehouseCode;
	private String frameBarcode;
	private String controlBarcode;
	private String hubBarcode;
	private String status;
	private Timestamp statusLastDate;
	private String shipOrderCode;
	private String dealerCode;
	private Integer oldId;
	private String productionPlace;
	private Integer voucherId;
	private Timestamp inspectionDate;
	private String productionLine;
	private String storageStatus;
	private Timestamp storageDate;
	private String listCategory;
	private String serviceCardNumber;

	// Constructors

	/** default constructor */
	public ProductInWarehouse() {
	}

	/** minimal constructor */
	public ProductInWarehouse(String productBarcode) {
		this.productBarcode = productBarcode;
	}

	/** full constructor */
	public ProductInWarehouse(String productBarcode, String productCode,
			String warehouseCode, String frameBarcode, String controlBarcode,
			String hubBarcode, String status, Timestamp statusLastDate,
			String shipOrderCode, String dealerCode, Integer oldId,
			String productionPlace, Integer voucherId,
			Timestamp inspectionDate, String productionLine,
			String storageStatus, Timestamp storageDate, String listCategory,
			String serviceCardNumber) {
		this.productBarcode = productBarcode;
		this.productCode = productCode;
		this.warehouseCode = warehouseCode;
		this.frameBarcode = frameBarcode;
		this.controlBarcode = controlBarcode;
		this.hubBarcode = hubBarcode;
		this.status = status;
		this.statusLastDate = statusLastDate;
		this.shipOrderCode = shipOrderCode;
		this.dealerCode = dealerCode;
		this.oldId = oldId;
		this.productionPlace = productionPlace;
		this.voucherId = voucherId;
		this.inspectionDate = inspectionDate;
		this.productionLine = productionLine;
		this.storageStatus = storageStatus;
		this.storageDate = storageDate;
		this.listCategory = listCategory;
		this.serviceCardNumber = serviceCardNumber;
	}

	// Property accessors

	public Integer getProductId() {
		return this.productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductBarcode() {
		return this.productBarcode;
	}

	public void setProductBarcode(String productBarcode) {
		this.productBarcode = productBarcode;
	}

	public String getProductCode() {
		return this.productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getWarehouseCode() {
		return this.warehouseCode;
	}

	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}

	public String getFrameBarcode() {
		return this.frameBarcode;
	}

	public void setFrameBarcode(String frameBarcode) {
		this.frameBarcode = frameBarcode;
	}

	public String getControlBarcode() {
		return this.controlBarcode;
	}

	public void setControlBarcode(String controlBarcode) {
		this.controlBarcode = controlBarcode;
	}

	public String getHubBarcode() {
		return this.hubBarcode;
	}

	public void setHubBarcode(String hubBarcode) {
		this.hubBarcode = hubBarcode;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getStatusLastDate() {
		return this.statusLastDate;
	}

	public void setStatusLastDate(Timestamp statusLastDate) {
		this.statusLastDate = statusLastDate;
	}

	public String getShipOrderCode() {
		return this.shipOrderCode;
	}

	public void setShipOrderCode(String shipOrderCode) {
		this.shipOrderCode = shipOrderCode;
	}

	public String getDealerCode() {
		return this.dealerCode;
	}

	public void setDealerCode(String dealerCode) {
		this.dealerCode = dealerCode;
	}

	public Integer getOldId() {
		return this.oldId;
	}

	public void setOldId(Integer oldId) {
		this.oldId = oldId;
	}

	public String getProductionPlace() {
		return this.productionPlace;
	}

	public void setProductionPlace(String productionPlace) {
		this.productionPlace = productionPlace;
	}

	public Integer getVoucherId() {
		return this.voucherId;
	}

	public void setVoucherId(Integer voucherId) {
		this.voucherId = voucherId;
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

	public String getStorageStatus() {
		return this.storageStatus;
	}

	public void setStorageStatus(String storageStatus) {
		this.storageStatus = storageStatus;
	}

	public Timestamp getStorageDate() {
		return this.storageDate;
	}

	public void setStorageDate(Timestamp storageDate) {
		this.storageDate = storageDate;
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

}