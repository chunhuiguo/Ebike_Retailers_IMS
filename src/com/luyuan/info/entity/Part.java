package com.luyuan.info.entity;



/**
 * Part entity. @author MyEclipse Persistence Tools
 */

public class Part  implements java.io.Serializable {


    // Fields    

     private Long id;
     private String code;
     private String name;
     private String specType;
     private String partColor;
     private String unit;
     private String categoryCode;
     private Integer dealerId;
     private String dealerShortName;
     private String description;


    // Constructors

    /** default constructor */
    public Part() {
    }

	/** minimal constructor */
    public Part(String code, String name, String categoryCode, Integer dealerId, String dealerShortName) {
        this.code = code;
        this.name = name;
        this.categoryCode = categoryCode;
        this.dealerId = dealerId;
        this.dealerShortName = dealerShortName;
    }
    
    /** full constructor */
    public Part(String code, String name, String specType, String partColor, String unit, String categoryCode, Integer dealerId, String dealerShortName, String description) {
        this.code = code;
        this.name = name;
        this.specType = specType;
        this.partColor = partColor;
        this.unit = unit;
        this.categoryCode = categoryCode;
        this.dealerId = dealerId;
        this.dealerShortName = dealerShortName;
        this.description = description;
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

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
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

    public String getCategoryCode() {
        return this.categoryCode;
    }
    
    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
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

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
   








}