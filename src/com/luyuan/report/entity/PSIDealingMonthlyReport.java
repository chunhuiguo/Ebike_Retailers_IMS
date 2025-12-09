package com.luyuan.report.entity;

/**
 * PSIDealingMonthlyReport entity. @author MyEclipse Persistence Tools
 */

public class PSIDealingMonthlyReport implements java.io.Serializable {

	// Fields

	private Long id;
	private String reportYear;
	private String reportMonth;
	private Integer dealerId;
	private String dealerShortName;
	private Integer dealingUnitId;
	private String dealingUnitShortName;
	private String type;
	private Integer subAccountId;
	private String subAccountType;
	private Double initialBalance;
	private Double dueAmount;
	private Double actualAmount;
	private Double finalBalance;

	// Constructors

	/** default constructor */
	public PSIDealingMonthlyReport() {
	}

	/** full constructor */
	public PSIDealingMonthlyReport(String reportYear, String reportMonth,
			Integer dealerId, String dealerShortName, Integer dealingUnitId,
			String dealingUnitShortName, String type, Integer subAccountId,
			String subAccountType, Double initialBalance, Double dueAmount,
			Double actualAmount, Double finalBalance) {
		this.reportYear = reportYear;
		this.reportMonth = reportMonth;
		this.dealerId = dealerId;
		this.dealerShortName = dealerShortName;
		this.dealingUnitId = dealingUnitId;
		this.dealingUnitShortName = dealingUnitShortName;
		this.type = type;
		this.subAccountId = subAccountId;
		this.subAccountType = subAccountType;
		this.initialBalance = initialBalance;
		this.dueAmount = dueAmount;
		this.actualAmount = actualAmount;
		this.finalBalance = finalBalance;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReportYear() {
		return this.reportYear;
	}

	public void setReportYear(String reportYear) {
		this.reportYear = reportYear;
	}

	public String getReportMonth() {
		return this.reportMonth;
	}

	public void setReportMonth(String reportMonth) {
		this.reportMonth = reportMonth;
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

	public Double getInitialBalance() {
		return this.initialBalance;
	}

	public void setInitialBalance(Double initialBalance) {
		this.initialBalance = initialBalance;
	}

	public Double getDueAmount() {
		return this.dueAmount;
	}

	public void setDueAmount(Double dueAmount) {
		this.dueAmount = dueAmount;
	}

	public Double getActualAmount() {
		return this.actualAmount;
	}

	public void setActualAmount(Double actualAmount) {
		this.actualAmount = actualAmount;
	}

	public Double getFinalBalance() {
		return this.finalBalance;
	}

	public void setFinalBalance(Double finalBalance) {
		this.finalBalance = finalBalance;
	}

}