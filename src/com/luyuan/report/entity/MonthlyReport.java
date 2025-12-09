package com.luyuan.report.entity;

/**
 * MonthlyReport entity. @author MyEclipse Persistence Tools
 */

public class MonthlyReport implements java.io.Serializable {

	// Fields

	private MonthlyReportId id;
	private Integer dealerId;
	private String dealerShortName;
	private Integer shopId;
	private String shopShortName;
//	private Integer warehouseId;
	private String warehouseName;
//	private Long productId;
	private String productName;
	
	//******************************
	private String productCode;
	private String productColor;
	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductColor() {
		return productColor;
	}

	public void setProductColor(String productColor) {
		this.productColor = productColor;
	}
	//***********************************************

	private String checkDate;
	private Integer inQty;
	private Double inTotal;
	private Integer outQty;
	private Double outTotal;

	// Constructors

	/** default constructor */
	public MonthlyReport() {
	}

	/** full constructor */
	public MonthlyReport(MonthlyReportId id) {
		this.id = id;

	}

	public MonthlyReport(MonthlyReportId id, Integer dealerId,
			String dealerShortName, Integer shopId, String shopShortName,
			Integer warehouseId, String warehouseName, Long productId,
			String productName) {
		this.id = id;
		this.dealerId = dealerId;
		this.dealerShortName = dealerShortName;
		this.shopId = shopId;
		this.shopShortName = shopShortName;
//		this.warehouseId = warehouseId;
		this.warehouseName = warehouseName;
//		this.productId = productId;
		this.productName = productName;
	}

	// Property accessors

	public MonthlyReportId getId() {
		return id;
	}

	public void setId(MonthlyReportId id) {
		this.id = id;
	}
	
	public Integer getDealerId() {
		return this.dealerId;
	}

	public void setDealerId(Integer dealerId) {
		this.dealerId = dealerId;
	}

	public String getDealerShortName() {
		return this.dealerShortName;
	}

	public void setDealerShortName(String dealerShortName) {
		this.dealerShortName = dealerShortName;
	}

	public Integer getShopId() {
		return this.shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public String getShopShortName() {
		return this.shopShortName;
	}

	public void setShopShortName(String shopShortName) {
		this.shopShortName = shopShortName;
	}

//	public Integer getWarehouseId() {
//		return this.warehouseId;
//	}
//
//	public void setWarehouseId(Integer warehouseId) {
//		this.warehouseId = warehouseId;
//	}

	public String getWarehouseName() {
		return this.warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

//	public Long getProductId() {
//		return this.productId;
//	}
//
//	public void setProductId(Long productId) {
//		this.productId = productId;
//	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCheckDate() {
		return this.checkDate;
	}

	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}

	public Integer getInQty() {
		return this.inQty;
	}

	public void setInQty(Integer inQty) {
		this.inQty = inQty;
	}

	public Double getInTotal() {
		return this.inTotal;
	}

	public void setInTotal(Double inTotal) {
		this.inTotal = inTotal;
	}

	public Integer getOutQty() {
		return this.outQty;
	}

	public void setOutQty(Integer outQty) {
		this.outQty = outQty;
	}

	public Double getOutTotal() {
		return this.outTotal;
	}

	public void setOutTotal(Double outTotal) {
		this.outTotal = outTotal;
	}
}