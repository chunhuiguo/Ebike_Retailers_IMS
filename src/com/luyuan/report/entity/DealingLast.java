package com.luyuan.report.entity;

/**
 * DealingLast entity. @author MyEclipse Persistence Tools
 */

public class DealingLast implements java.io.Serializable {

	// Fields

	private Long id;
	private String type;
	private Integer subAccountId;
	private String subAccountType;
	private Integer dealerId;
	private String dealerShortName;
	private Integer dealingUnitId;
	private String dealingUnitShortName;
	private Double balance;

	// Constructors

	/** default constructor */
	public DealingLast() {
	}

	/** full constructor */
	public DealingLast(String type, Integer subAccountId,
			String subAccountType, Integer dealerId, String dealerShortName,
			Integer dealingUnitId, String dealingUnitShortName, Double balance) {
		this.type = type;
		this.subAccountId = subAccountId;
		this.subAccountType = subAccountType;
		this.dealerId = dealerId;
		this.dealerShortName = dealerShortName;
		this.dealingUnitId = dealingUnitId;
		this.dealingUnitShortName = dealingUnitShortName;
		this.balance = balance;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
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

	public Integer getDealingUnitId() {
		return this.dealingUnitId;
	}

	public void setDealingUnitId(Integer dealingUnitId) {
		this.dealingUnitId = dealingUnitId;
	}

	public String getDealingUnitShortName() {
		return this.dealingUnitShortName;
	}

	public void setDealingUnitShortName(String dealingUnitShortName) {
		this.dealingUnitShortName = dealingUnitShortName;
	}

	public Double getBalance() {
		return this.balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

}