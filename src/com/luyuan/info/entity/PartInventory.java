package com.luyuan.info.entity;

/**
 * PartInventory entity. @author MyEclipse Persistence Tools
 */

public class PartInventory implements java.io.Serializable {

	// Fields

	private PartInventoryId id;
	private String code;
	private String name;
	private String specType;
	private String partColor;
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
	public PartInventory() {
	}

	/** minimal constructor */
	public PartInventory(PartInventoryId id, String code, String name, Integer qty) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.qty = qty;
	}

	/** full constructor */
	public PartInventory(PartInventoryId id, String code, String name, String specType,
			String partColor, String unit, Integer dealerId, Integer shopId,
			String dealerShortName, String shopShortName, Integer warehouseId,
			String warehouseName, Integer qty) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.specType = specType;
		this.partColor = partColor;
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

	public PartInventoryId getId() {
		return this.id;
	}

	public void setId(PartInventoryId id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpecType() {
		return this.specType;
	}

	public void setSpecType(String specType) {
		this.specType = specType;
	}

	public String getPartColor() {
		return this.partColor;
	}

	public void setPartColor(String partColor) {
		this.partColor = partColor;
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