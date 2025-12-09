package com.luyuan.sys.entity;

/**
 * Product entity. @author MyEclipse Persistence Tools
 */

public class Product implements java.io.Serializable {

	// Fields

	private Long productId;
	private String productCode;
	private String prefixName;
	private String suffixName;
	private String modelCode;
	private String seriesCode;
	private String specCode;
	private String colorCode;
	private String motorCode;
	private String unit;
	private String batteryCode;
	private Integer batteryQty;
	private Double batteryPrice;
	private String batteryDesc;
	private String tyreSize;
	private Integer voltage;
	private String control;

	// Constructors

	/** default constructor */
	public Product() {
	}

	/** minimal constructor */
	public Product(String productCode, Double batteryPrice) {
		this.productCode = productCode;
		this.batteryPrice = batteryPrice;
	}

	/** full constructor */
	public Product(String productCode, String prefixName, String suffixName,
			String modelCode, String seriesCode, String specCode,
			String colorCode, String motorCode, String unit,
			String batteryCode, Integer batteryQty, Double batteryPrice,
			String batteryDesc, String tyreSize, Integer voltage, String control) {
		this.productCode = productCode;
		this.prefixName = prefixName;
		this.suffixName = suffixName;
		this.modelCode = modelCode;
		this.seriesCode = seriesCode;
		this.specCode = specCode;
		this.colorCode = colorCode;
		this.motorCode = motorCode;
		this.unit = unit;
		this.batteryCode = batteryCode;
		this.batteryQty = batteryQty;
		this.batteryPrice = batteryPrice;
		this.batteryDesc = batteryDesc;
		this.tyreSize = tyreSize;
		this.voltage = voltage;
		this.control = control;
	}

	// Property accessors

	public Long getProductId() {
		return this.productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductCode() {
		return this.productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getPrefixName() {
		return this.prefixName;
	}

	public void setPrefixName(String prefixName) {
		this.prefixName = prefixName;
	}

	public String getSuffixName() {
		return this.suffixName;
	}

	public void setSuffixName(String suffixName) {
		this.suffixName = suffixName;
	}

	public String getModelCode() {
		return this.modelCode;
	}

	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}

	public String getSeriesCode() {
		return this.seriesCode;
	}

	public void setSeriesCode(String seriesCode) {
		this.seriesCode = seriesCode;
	}

	public String getSpecCode() {
		return this.specCode;
	}

	public void setSpecCode(String specCode) {
		this.specCode = specCode;
	}

	public String getColorCode() {
		return this.colorCode;
	}

	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}

	public String getMotorCode() {
		return this.motorCode;
	}

	public void setMotorCode(String motorCode) {
		this.motorCode = motorCode;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getBatteryCode() {
		return this.batteryCode;
	}

	public void setBatteryCode(String batteryCode) {
		this.batteryCode = batteryCode;
	}

	public Integer getBatteryQty() {
		return this.batteryQty;
	}

	public void setBatteryQty(Integer batteryQty) {
		this.batteryQty = batteryQty;
	}

	public Double getBatteryPrice() {
		return this.batteryPrice;
	}

	public void setBatteryPrice(Double batteryPrice) {
		this.batteryPrice = batteryPrice;
	}

	public String getBatteryDesc() {
		return this.batteryDesc;
	}

	public void setBatteryDesc(String batteryDesc) {
		this.batteryDesc = batteryDesc;
	}

	public String getTyreSize() {
		return this.tyreSize;
	}

	public void setTyreSize(String tyreSize) {
		this.tyreSize = tyreSize;
	}

	public Integer getVoltage() {
		return this.voltage;
	}

	public void setVoltage(Integer voltage) {
		this.voltage = voltage;
	}

	public String getControl() {
		return this.control;
	}

	public void setControl(String control) {
		this.control = control;
	}

}