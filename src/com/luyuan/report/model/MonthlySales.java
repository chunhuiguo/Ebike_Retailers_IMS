package com.luyuan.report.model;

/**
 * InventoryBook entity. @author MyEclipse Persistence Tools
 */

public class MonthlySales implements java.io.Serializable {

	// Fields
	private String productName;// 车型
	private Integer remainingQty;// 上月结余数
	private Integer inQqty;// 本月进货数
	private Integer outQqty;// 本月销售数
	private Integer qty;// 本月结存数

	public MonthlySales(String productName, Integer remainingQty,
			Integer inQqty,Integer outQqty, Integer qty) {
		super();
		this.productName = productName;	
		this.remainingQty = remainingQty;
		this.inQqty = inQqty;
		this.outQqty = outQqty;
		this.remainingQty = remainingQty;
		this.qty = qty;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getRemainingQty() {
		return remainingQty;
	}

	public void setRemainingQty(Integer remainingQty) {
		this.remainingQty = remainingQty;
	}

	public Integer getInQqty() {
		return inQqty;
	}

	public void setInQqty(Integer inQqty) {
		this.inQqty = inQqty;
	}

	public Integer getOutQqty() {
		return outQqty;
	}

	public void setOutQqty(Integer outQqty) {
		this.outQqty = outQqty;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}
}