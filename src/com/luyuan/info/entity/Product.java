package com.luyuan.info.entity;



/**
 * Product entity. @author MyEclipse Persistence Tools
 */

public class Product  implements java.io.Serializable {


    // Fields    

     private Long id;
     private String code;
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
     private String batteryDesc;
     private String tyreSize;
     private Integer voltage;
     private String control;
     private String colorName;
     private String specName;
     private String categoryCode;
     private String brand;
     private Integer dealerId;
     private String dealerShortName;


    // Constructors

    /** default constructor */
    public Product() {
    }

	/** minimal constructor */
    public Product(String code, String categoryCode, String brand, Integer dealerId, String dealerShortName) {
        this.code = code;
        this.categoryCode = categoryCode;
        this.brand = brand;
        this.dealerId = dealerId;
        this.dealerShortName = dealerShortName;
    }
    
    /** full constructor */
    public Product(String code, String prefixName, String suffixName, String modelCode, String seriesCode, String specCode, String colorCode, String motorCode, String unit, String batteryCode, Integer batteryQty, String batteryDesc, String tyreSize, Integer voltage, String control, String colorName, String specName, String categoryCode, String brand, Integer dealerId, String dealerShortName) {
        this.code = code;
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
        this.batteryDesc = batteryDesc;
        this.tyreSize = tyreSize;
        this.voltage = voltage;
        this.control = control;
        this.colorName = colorName;
        this.specName = specName;
        this.categoryCode = categoryCode;
        this.brand = brand;
        this.dealerId = dealerId;
        this.dealerShortName = dealerShortName;
    }

   
    // Property accessors

    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
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

    public String getColorName() {
        return this.colorName;
    }
    
    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getSpecName() {
        return this.specName;
    }
    
    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public String getCategoryCode() {
        return this.categoryCode;
    }
    
    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getBrand() {
        return this.brand;
    }
    
    public void setBrand(String brand) {
        this.brand = brand;
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
   








}