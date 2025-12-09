package com.luyuan.info.entity;

import java.sql.Timestamp;

/**
 * InsuranceCard entity. @author MyEclipse Persistence Tools
 */

public class InsuranceCard implements java.io.Serializable {

	// Fields

	private Integer id;
	private String customerName;
	private Timestamp buyDate;
	private String productBarCode;
	private String identityCode;
	private String cellPhone;
	private String insuranceType;	

	// Constructors

	/** default constructor */
	public InsuranceCard() {
	}

	/** full constructor */
	public InsuranceCard(String customerName, Timestamp buyDate,
			String productBarCode, String identityCode, String cellPhone,
			String insuranceType) {
		this.customerName = customerName;
		this.buyDate = buyDate;
		this.productBarCode = productBarCode;
		this.identityCode = identityCode;
		this.cellPhone = cellPhone;
		this.insuranceType = insuranceType;		
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Timestamp getBuyDate() {
		return this.buyDate;
	}

	public void setBuyDate(Timestamp buyDate) {
		this.buyDate = buyDate;
	}

	public String getProductBarCode() {
		return this.productBarCode;
	}

	public void setProductBarCode(String productBarCode) {
		this.productBarCode = productBarCode;
	}

	public String getIdentityCode() {
		return this.identityCode;
	}

	public void setIdentityCode(String identityCode) {
		this.identityCode = identityCode;
	}

	public String getCellPhone() {
		return this.cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getInsuranceType() {
		return this.insuranceType;
	}

	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}

	

}