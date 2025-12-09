package com.luyuan.report.model;
/**
 * InventoryBook entity. @author MyEclipse Persistence Tools
 */

public class Report  implements java.io.Serializable {


    // Fields    
	private Long productId;
	private Long inQty;
	private Long outQty;
	
	public Report(Long productId, Long inQty, Long outQty) {
		super();
		this.productId = productId;
		this.inQty = inQty;
		this.outQty = outQty;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getInQty() {
		return inQty;
	}

	public void setInQty(Long inQty) {
		this.inQty = inQty;
	}

	public Long getOutQty() {
		return outQty;
	}

	public void setOutQty(Long outQty) {
		this.outQty = outQty;
	}

  
}