package com.luyuan.report.entity;

/**
 * MonthlyReportId entity. @author MyEclipse Persistence Tools
 */

public class MonthlyReportId implements java.io.Serializable {

	// Fields

	private Integer warehouseId;
	private Long productId;

	// Constructors

	/** default constructor */
	public MonthlyReportId() {
	}

	/** full constructor */
	public MonthlyReportId(Integer warehouseId, Long productId) {
		this.warehouseId = warehouseId;
		this.productId = productId;
	}

	// Property accessors

	public Integer getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

}