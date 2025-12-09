package com.luyuan.info.entity;



/**
 * PPrice entity. @author MyEclipse Persistence Tools
 */

public class PPrice  implements java.io.Serializable {


    // Fields    

     private Long id;
     private Integer dealerId;
     private String dealerShortName;
     private Long partId;
     private String partCode;
     private String partName;
     private String specType;
     private String partColor;
     private Integer priceTypeId;
     private String priceType;
     private Double price;
     private Double discount;


    // Constructors

    /** default constructor */
    public PPrice() {
    }

	/** minimal constructor */
    public PPrice(Integer dealerId, String dealerShortName, Long partId, String partCode, String partName, String specType, String partColor, Integer priceTypeId, String priceType, Double price) {
        this.dealerId = dealerId;
        this.dealerShortName = dealerShortName;
        this.partId = partId;
        this.partCode = partCode;
        this.partName = partName;
        this.specType = specType;
        this.partColor = partColor;
        this.priceTypeId = priceTypeId;
        this.priceType = priceType;
        this.price = price;
    }
    
    /** full constructor */
    public PPrice(Integer dealerId, String dealerShortName, Long partId, String partCode, String partName, String specType, String partColor, Integer priceTypeId, String priceType, Double price, Double discount) {
        this.dealerId = dealerId;
        this.dealerShortName = dealerShortName;
        this.partId = partId;
        this.partCode = partCode;
        this.partName = partName;
        this.specType = specType;
        this.partColor = partColor;
        this.priceTypeId = priceTypeId;
        this.priceType = priceType;
        this.price = price;
        this.discount = discount;
    }

   
    // Property accessors

    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
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

    public Integer getPriceTypeId() {
        return this.priceTypeId;
    }
    
    public void setPriceTypeId(Integer priceTypeId) {
        this.priceTypeId = priceTypeId;
    }

    public String getPriceType() {
        return this.priceType;
    }
    
    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public Double getPrice() {
        return this.price;
    }
    
    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return this.discount;
    }
    
    public void setDiscount(Double discount) {
        this.discount = discount;
    }
   








}