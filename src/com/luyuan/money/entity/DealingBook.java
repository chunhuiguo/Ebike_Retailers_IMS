package com.luyuan.money.entity;

/**
 * DealingBook entity. @author MyEclipse Persistence Tools
 */

public class DealingBook implements java.io.Serializable {

	// Fields

	private Long id;
	private String type;
	private Integer subAccountId;
	private String subAccountType;
	private Integer dealerId;
	private String dealerShortName;
	private Integer dealingUnitId;
	private String dealingUnitShortName;
	private Long voucherId;
	private String voucherCode;
	private String voucherType;
	private String voucherDate;
	private Double dueTotal;
	private Double actualTotal;
	private Double balance;
	private String remark;

	// Constructors

	/** default constructor */
	public DealingBook() {
	}

	/** minimal constructor */
	public DealingBook(String type, Integer subAccountId,
			String subAccountType, Integer dealerId, String dealerShortName,
			Integer dealingUnitId, String dealingUnitShortName, Long voucherId,
			String voucherCode, String voucherType, String voucherDate,
			Double dueTotal, Double actualTotal, Double balance) {
		this.type = type;
		this.subAccountId = subAccountId;
		this.subAccountType = subAccountType;
		this.dealerId = dealerId;
		this.dealerShortName = dealerShortName;
		this.dealingUnitId = dealingUnitId;
		this.dealingUnitShortName = dealingUnitShortName;
		this.voucherId = voucherId;
		this.voucherCode = voucherCode;
		this.voucherType = voucherType;
		this.voucherDate = voucherDate;
		this.dueTotal = dueTotal;
		this.actualTotal = actualTotal;
		this.balance = balance;
	}

	/** full constructor */
	public DealingBook(String type, Integer subAccountId,
			String subAccountType, Integer dealerId, String dealerShortName,
			Integer dealingUnitId, String dealingUnitShortName, Long voucherId,
			String voucherCode, String voucherType, String voucherDate,
			Double dueTotal, Double actualTotal, Double balance, String remark) {
		this.type = type;
		this.subAccountId = subAccountId;
		this.subAccountType = subAccountType;
		this.dealerId = dealerId;
		this.dealerShortName = dealerShortName;
		this.dealingUnitId = dealingUnitId;
		this.dealingUnitShortName = dealingUnitShortName;
		this.voucherId = voucherId;
		this.voucherCode = voucherCode;
		this.voucherType = voucherType;
		this.voucherDate = voucherDate;
		this.dueTotal = dueTotal;
		this.actualTotal = actualTotal;
		this.balance = balance;
		this.remark = remark;
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

	public Double getDueTotal() {
		return this.dueTotal;
	}

	public void setDueTotal(Double dueTotal) {
		this.dueTotal = dueTotal;
	}

	public Double getActualTotal() {
		return this.actualTotal;
	}

	public void setActualTotal(Double actualTotal) {
		this.actualTotal = actualTotal;
	}

	public Double getBalance() {
		return this.balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}