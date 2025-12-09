package com.luyuan.money.entity;

/**
 * DealingDetail entity. @author MyEclipse Persistence Tools
 */

public class DealingDetail implements java.io.Serializable {

	// Fields

	private Long id;
	private Long parentDealingId;
	private Integer subAccountId;
	private String subAccountType;
	private Double total;
	private String remark;

	// Constructors

	/** default constructor */
	public DealingDetail() {
	}

	/** minimal constructor */
	public DealingDetail(Long parentDealingId, Integer subAccountId,
			String subAccountType, Double total) {
		this.parentDealingId = parentDealingId;
		this.subAccountId = subAccountId;
		this.subAccountType = subAccountType;
		this.total = total;
	}

	/** full constructor */
	public DealingDetail(Long parentDealingId, Integer subAccountId,
			String subAccountType, Double total, String remark) {
		this.parentDealingId = parentDealingId;
		this.subAccountId = subAccountId;
		this.subAccountType = subAccountType;
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

	public Long getParentDealingId() {
		return this.parentDealingId;
	}

	public void setParentDealingId(Long parentDealingId) {
		this.parentDealingId = parentDealingId;
	}

	public Integer getSubAccountId() {
		return this.subAccountId;
	}

	public void setSubAccountId(Integer subAccountId) {
		this.subAccountId = subAccountId;
	}

	public String getSubAccountType() {
		return this.subAccountType;
	}

	public void setSubAccountType(String subAccountType) {
		this.subAccountType = subAccountType;
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