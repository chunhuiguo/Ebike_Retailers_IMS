package com.luyuan.stock.entity;

/**
 * Inventory entity. @author MyEclipse Persistence Tools
 */

public class Inventory implements java.io.Serializable {

	// Fields

	private Long id;
	private Integer dealerId;
	private Integer shopId;
	private String dealerShortName;
	private String shopShortName;
	private Integer warehouseId;
	private String warehouseName;
	private Long productId;
	private String productName;
	private String productCode;
	private String productColor;
	private String productUnit;
	private Integer qty;
	private Double averagePrice;
	private Double total;

	// Constructors

	/** default constructor */
	public Inventory() {
	}

	/** full constructor */
	public Inventory(Integer dealerId, Integer shopId, String dealerShortName,
			String shopShortName, Integer warehouseId, String warehouseName,
			Long productId, String productName, String productCode,
			String productColor, String productUnit, Integer qty,
			Double averagePrice, Double total) {
		this.dealerId = dealerId;
		this.shopId = shopId;
		this.dealerShortName = dealerShortName;
		this.shopShortName = shopShortName;
		this.warehouseId = warehouseId;
		this.warehouseName = warehouseName;
		this.productId = productId;
		this.productName = productName;
		this.productCode = productCode;
		this.productColor = productColor;
		this.productUnit = productUnit;
		this.qty = qty;
		this.averagePrice = averagePrice;
		this.total = total;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getDealerId() {
		return this.dealerId;
	}

	public void setDealerId(Integer dealerId) {
		this.dealerId = dealerId;
	}

	public Integer getShopId() {
		return this.shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public String getDealerShortName() {
		return this.dealerShortName;
	}

	public void setDealerShortName(String dealerShortName) {
		this.dealerShortName = dealerShortName;
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

	public String getProductUnit() {
		return this.productUnit;
	}

	public void setProductUnit(String productUnit) {
		this.productUnit = productUnit;
	}

	public Integer getQty() {
		return this.qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Double getAveragePrice() {
		return this.averagePrice;
	}

	public void setAveragePrice(Double averagePrice) {
		this.averagePrice = averagePrice;
	}

	public Double getTotal() {
		return this.total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

}