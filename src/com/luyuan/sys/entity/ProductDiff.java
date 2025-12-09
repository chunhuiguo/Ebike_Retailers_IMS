package com.luyuan.sys.entity;



/**
 * ProductDiff entity. @author MyEclipse Persistence Tools
 */

public class ProductDiff  implements java.io.Serializable {


    // Fields    

     private ProductDiffId id;
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
     private String wheelDiameter;
     private String colorName;

    // Constructors

    /** default constructor */
    public ProductDiff() {
    }

    
    /** full constructor */
    public ProductDiff(ProductDiffId id) {
        this.id = id;
    }
    
    /** full constructor */
    public ProductDiff(ProductDiffId id, String productCode, String prefixName, String suffixName, String modelCode, String seriesCode, String specCode, String colorCode, String motorCode, String unit, String batteryCode, Integer batteryQty, Double batteryPrice, String batteryDesc, String tyreSize, Integer voltage, String control, String wheelDiameter, String colorName) {
        this.id = id;
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
        this.wheelDiameter = wheelDiameter;
        this.colorName = colorName;
    }

   
    // Property accessors

    public String getProductCode() {
		return productCode;
	}


	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}


	public String getPrefixName() {
		return prefixName;
	}


	public void setPrefixName(String prefixName) {
		this.prefixName = prefixName;
	}


	public String getSuffixName() {
		return suffixName;
	}


	public void setSuffixName(String suffixName) {
		this.suffixName = suffixName;
	}


	public String getModelCode() {
		return modelCode;
	}


	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}


	public String getSeriesCode() {
		return seriesCode;
	}


	public void setSeriesCode(String seriesCode) {
		this.seriesCode = seriesCode;
	}


	public String getSpecCode() {
		return specCode;
	}


	public void setSpecCode(String specCode) {
		this.specCode = specCode;
	}


	public String getColorCode() {
		return colorCode;
	}


	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}


	public String getMotorCode() {
		return motorCode;
	}


	public void setMotorCode(String motorCode) {
		this.motorCode = motorCode;
	}


	public String getUnit() {
		return unit;
	}


	public void setUnit(String unit) {
		this.unit = unit;
	}


	public String getBatteryCode() {
		return batteryCode;
	}


	public void setBatteryCode(String batteryCode) {
		this.batteryCode = batteryCode;
	}


	public Integer getBatteryQty() {
		return batteryQty;
	}


	public void setBatteryQty(Integer batteryQty) {
		this.batteryQty = batteryQty;
	}


	public Double getBatteryPrice() {
		return batteryPrice;
	}


	public void setBatteryPrice(Double batteryPrice) {
		this.batteryPrice = batteryPrice;
	}


	public String getBatteryDesc() {
		return batteryDesc;
	}


	public void setBatteryDesc(String batteryDesc) {
		this.batteryDesc = batteryDesc;
	}


	public String getTyreSize() {
		return tyreSize;
	}


	public void setTyreSize(String tyreSize) {
		this.tyreSize = tyreSize;
	}


	public Integer getVoltage() {
		return voltage;
	}


	public void setVoltage(Integer voltage) {
		this.voltage = voltage;
	}


	public String getControl() {
		return control;
	}


	public void setControl(String control) {
		this.control = control;
	}


	public ProductDiffId getId() {
        return this.id;
    }
    
    public void setId(ProductDiffId id) {
        this.id = id;
    }
    
    
    public String getWheelDiameter() {
        return this.wheelDiameter;
    }
    
    public void setWheelDiameter(String wheelDiameter) {
        this.wheelDiameter = wheelDiameter;
    }

    public String getColorName() {
        return this.colorName;
    }
    
    public void setColorName(String colorName) {
        this.colorName = colorName;
    }
}