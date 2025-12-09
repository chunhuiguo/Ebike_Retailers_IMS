package com.luyuan.sys.entity;

/**
 * Part entity. @author MyEclipse Persistence Tools
 */

public class Part implements java.io.Serializable {

	// Fields

	private Long partId;
	private String partCode;
	private String partName;
	private String specType;
	private String partColor;
	private String unit;

	// Constructors

	/** default constructor */
	public Part() {
	}

	/** minimal constructor */
	public Part(String partCode, String partName) {
		this.partCode = partCode;
		this.partName = partName;
	}

	/** full constructor */
	public Part(String partCode, String partName, String specType,
			String partColor, String unit) {
		this.partCode = partCode;
		this.partName = partName;
		this.specType = specType;
		this.partColor = partColor;
		this.unit = unit;
	}

	// Property accessors

	public Long getPartId() {
		return this.partId;
	}

	public void setPartId(Long partId) {
		this.partId = partId;
	}

	public String getPartCode() {
		return this.partCode;
	}

	public void setPartCode(String partCode) {
		this.partCode = partCode;
	}

	public String getPartName() {
		return this.partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
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

}