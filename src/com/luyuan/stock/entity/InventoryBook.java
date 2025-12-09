package com.luyuan.stock.entity;

/**
 * InventoryBook entity. @author MyEclipse Persistence Tools
 */

public class InventoryBook implements java.io.Serializable {

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
	private Long voucherId;
	private String voucherCode;
	private String voucherType;
	private String voucherDate;
	private String shipOrderCode;
	private Integer inQty;
	private Double inTotal;
	private Integer outQty;
	private Double outTotal;
	private Integer qty;
	private Double total;
	private String remark;

	// Constructors

	/** default constructor */
	public InventoryBook() {
	}

	/** minimal constructor */
	public InventoryBook(Integer dealerId, Integer shopId,
			String dealerShortName, String shopShortName, Integer warehouseId,
			String warehouseName, Long productId, String productName,
			Long voucherId, String voucherCode, String voucherType,
			String voucherDate, Integer qty, Double total) {
		this.dealerId = dealerId;
		this.shopId = shopId;
		this.dealerShortName = dealerShortName;
		this.shopShortName = shopShortName;
		this.warehouseId = warehouseId;
		this.warehouseName = warehouseName;
		this.productId = productId;
		this.productName = productName;
		this.voucherId = voucherId;
		this.voucherCode = voucherCode;
		this.voucherType = voucherType;
		this.voucherDate = voucherDate;
		this.qty = qty;
		this.total = total;
	}

	/** full constructor */
	public InventoryBook(Integer dealerId, Integer shopId,
			String dealerShortName, String shopShortName, Integer warehouseId,
			String warehouseName, Long productId, String productName,
			Long voucherId, String voucherCode, String voucherType,
			String voucherDate, String shipOrderCode, Integer inQty,
			Double inTotal, Integer outQty, Double outTotal, Integer qty,
			Double total, String remark) {
		this.dealerId = dealerId;
		this.shopId = shopId;
		this.dealerShortName = dealerShortName;
		this.shopShortName = shopShortName;
		this.warehouseId = warehouseId;
		this.warehouseName = warehouseName;
		this.productId = productId;
		this.productName = productName;
		this.voucherId = voucherId;
		this.voucherCode = voucherCode;
		this.voucherType = voucherType;
		this.voucherDate = voucherDate;
		this.shipOrderCode = shipOrderCode;
		this.inQty = inQty;
		this.inTotal = inTotal;
		this.outQty = outQty;
		this.outTotal = outTotal;
		this.qty = qty;
		this.total = total;
		this.remark = remark;
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

	public Long getVoucherId() {
		return this.voucherId;
	}

	public void setVoucherId(Long voucherId) {
		this.voucherId = voucherId;
	}

	public String getVoucherCode() {
		return this.voucherCode;
	}

	public void setVoucherCode(String voucherCode) {
		this.voucherCode = voucherCode;
	}

	public String getVoucherType() {
		return this.voucherType;
	}

	public void setVoucherType(String voucherType) {
		this.voucherType = voucherType;
	}

	public String getVoucherDate() {
		return this.voucherDate;
	}

	public void setVoucherDate(String voucherDate) {
		this.voucherDate = voucherDate;
	}

	public String getShipOrderCode() {
		return this.shipOrderCode;
	}

	public void setShipOrderCode(String shipOrderCode) {
		this.shipOrderCode = shipOrderCode;
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

	public Integer getQty() {
		return this.qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Double getTotal() {
		return this.total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}