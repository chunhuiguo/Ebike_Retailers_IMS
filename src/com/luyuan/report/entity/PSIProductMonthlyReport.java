package com.luyuan.report.entity;

/**
 * PSIProductMonthlyReport entity. @author MyEclipse Persistence Tools
 */

public class PSIProductMonthlyReport implements java.io.Serializable {

	// Fields

	private Long id;
	private String reportYear;
	private String reportMonth;
	private Integer dealerId;
	private String dealerShortName;
	private Integer shopId;
	private String shopShortName;
	private Integer warehouseId;
	private String warehouseName;
	private Long productId;
	private String productName;
	private String productCode;
	private String productColor;
	private Integer initialQty;
	private Integer inQty;
	private Integer outQty;
	private Integer finalQty;

	// Constructors

	/** default constructor */
	public PSIProductMonthlyReport() {
	}

	/** full constructor */
	public PSIProductMonthlyReport(String reportYear, String reportMonth,
			Integer dealerId, String dealerShortName, Integer shopId,
			String shopShortName, Integer warehouseId, String warehouseName,
			Long productId, String productName, String productCode,
			String productColor, Integer initialQty, Integer inQty,
			Integer outQty, Integer finalQty) {
		this.reportYear = reportYear;
		this.reportMonth = reportMonth;
		this.dealerId = dealerId;
		this.dealerShortName = dealerShortName;
		this.shopId = shopId;
		this.shopShortName = shopShortName;
		this.warehouseId = warehouseId;
		this.warehouseName = warehouseName;
		this.productId = productId;
		this.productName = productName;
		this.productCode = productCode;
		this.productColor = productColor;
		this.initialQty = initialQty;
		this.inQty = inQty;
		this.outQty = outQty;
		this.finalQty = finalQty;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReportYear() {
		return this.reportYear;
	}

	public void setReportYear(String reportYear) {
		this.reportYear = reportYear;
	}

	public String getReportMonth() {
		return this.reportMonth;
	}

	public void setReportMonth(String reportMonth) {
		this.reportMonth = reportMonth;
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

	public Integer getWarehouseId() {
		return this.warehouseId;
	}

	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getWarehouseName() {
		return this.warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public Long getProductId() {
		return this.productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCode() {
		return this.productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductColor() {
		return this.productColor;
	}

	public void setProductColor(String productColor) {
		this.productColor = productColor;
	}

	public Integer getInitialQty() {
		return this.initialQty;
	}

	public void setInitialQty(Integer initialQty) {
		this.initialQty = initialQty;
	}

	public Integer getInQty() {
		return this.inQty;
	}

	public void setInQty(Integer inQty) {
		this.inQty = inQty;
	}

	public Integer getOutQty() {
		return this.outQty;
	}

	public void setOutQty(Integer outQty) {
		this.outQty = outQty;
	}

	public Integer getFinalQty() {
		return this.finalQty;
	}

	public void setFinalQty(Integer finalQty) {
		this.finalQty = finalQty;
	}

}