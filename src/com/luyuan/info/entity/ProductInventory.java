package com.luyuan.info.entity;

/**
 * ProductInventory entity. @author MyEclipse Persistence Tools
 */

public class ProductInventory implements java.io.Serializable {

	// Fields

	private ProductInventoryId id;
	private String code;
	private String prefixName;
	private String suffixName;
	private String colorName;
	private String unit;
	private Integer dealerId;
	private Integer shopId;
	private String dealerShortName;
	private String shopShortName;
	private Integer warehouseId;
	private String warehouseName;
	private Integer qty;

	// Constructors

	/** default constructor */
	public ProductInventory() {
	}

	/** minimal constructor */
	public ProductInventory(ProductInventoryId id, String code, Integer qty) {
		this.id = id;
		this.code = code;
		this.qty = qty;
	}

	/** full constructor */
	public ProductInventory(ProductInventoryId id, String code, String prefixName,
			String suffixName, String colorName, String unit, Integer dealerId,
			Integer shopId, String dealerShortName, String shopShortName,
			Integer warehouseId, String warehouseName, Integer qty) {
		this.id = id;
		this.code = code;
		this.prefixName = prefixName;
		this.suffixName = suffixName;
		this.colorName = colorName;
		this.unit = unit;
		this.dealerId = dealerId;
		this.shopId = shopId;
		this.dealerShortName = dealerShortName;
		this.shopShortName = shopShortName;
		this.warehouseId = warehouseId;
		this.warehouseName = warehouseName;
		this.qty = qty;
	}

	// Property accessors

	public ProductInventoryId getId() {
		return this.id;
	}

	public void setId(ProductInventoryId id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPrefixName() {
		return this.prefixName;
	}

	public void setPrefixName(String prefixName) {
		this.prefixName = prefixName;
	}

	public String getSuffixName() {
		return this.suffixName;
	}

	public void setSuffixName(String suffixName) {
		this.suffixName = suffixName;
	}

	public String getColorName() {
		return this.colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}
	
	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
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

	public Integer getQty() {
		return this.qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

}