package com.luyuan.sys.entity;

import java.sql.Timestamp;

/**
 * Users entity. @author MyEclipse Persistence Tools
 */

public class Users implements java.io.Serializable {

	// Fields

	private Integer userId;
	private String userCode;
	private String userName;
	private String password;
	private String userType;
	private Boolean online;
	private Boolean enabled;
	private Boolean isDealer;
	private String creatorCode;
	private Integer unitId;
	private String companyCode;
	private Timestamp onTime;

	// Constructors

	/** default constructor */
	public Users() {
	}

	/** minimal constructor */
	public Users(String userCode, String userName, String password,
			String userType, Boolean online, Boolean enabled, Boolean isDealer,
			String creatorCode, Integer unitId, String companyCode) {
		this.userCode = userCode;
		this.userName = userName;
		this.password = password;
		this.userType = userType;
		this.online = online;
		this.enabled = enabled;
		this.isDealer = isDealer;
		this.creatorCode = creatorCode;
		this.unitId = unitId;
		this.companyCode = companyCode;
	}

	/** full constructor */
	public Users(String userCode, String userName, String password,
			String userType, Boolean online, Boolean enabled, Boolean isDealer,
			String creatorCode, Integer unitId, String companyCode,
			Timestamp onTime) {
		this.userCode = userCode;
		this.userName = userName;
		this.password = password;
		this.userType = userType;
		this.online = online;
		this.enabled = enabled;
		this.isDealer = isDealer;
		this.creatorCode = creatorCode;
		this.unitId = unitId;
		this.companyCode = companyCode;
		this.onTime = onTime;
	}

	// Property accessors

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserCode() {
		return this.userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Boolean getOnline() {
		return this.online;
	}

	public void setOnline(Boolean online) {
		this.online = online;
	}

	public Boolean getEnabled() {
		return this.enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getIsDealer() {
		return this.isDealer;
	}

	public void setIsDealer(Boolean isDealer) {
		this.isDealer = isDealer;
	}

	public String getCreatorCode() {
		return this.creatorCode;
	}

	public void setCreatorCode(String creatorCode) {
		this.creatorCode = creatorCode;
	}

	public Integer getUnitId() {
		return this.unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public String getCompanyCode() {
		return this.companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public Timestamp getOnTime() {
		return this.onTime;
	}

	public void setOnTime(Timestamp onTime) {
		this.onTime = onTime;
	}

}