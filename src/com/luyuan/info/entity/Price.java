package com.luyuan.info.entity;



/**
 * Price entity. @author MyEclipse Persistence Tools
 */

public class Price  implements java.io.Serializable {


    // Fields    

     private Long id;
     private Integer dealerId;
     private String dealerShortName;
     private Long productId;
     private String productCode;
     private String prefixName;
     private String suffixName;
     private String colorName;
     private Integer priceTypeId;
     private String priceType;
     private Double price;
     private Double discount;


    // Constructors

    /** default constructor */
    public Price() {
    }

	/** minimal constructor */
    public Price(Integer dealerId, String dealerShortName, Long productId, String productCode, String prefixName, String suffixName, String colorName, Integer priceTypeId, String priceType, Double price) {
        this.dealerId = dealerId;
        this.dealerShortName = dealerShortName;
        this.productId = productId;
        this.productCode = productCode;
        this.prefixName = prefixName;
        this.suffixName = suffixName;
        this.colorName = colorName;
        this.priceTypeId = priceTypeId;
        this.priceType = priceType;
        this.price = price;
    }
    
    /** full constructor */
    public Price(Integer dealerId, String dealerShortName, Long productId, String productCode, String prefixName, String suffixName, String colorName, Integer priceTypeId, String priceType, Double price, Double discount) {
        this.dealerId = dealerId;
        this.dealerShortName = dealerShortName;
        this.productId = productId;
        this.productCode = productCode;
        this.prefixName = prefixName;
        this.suffixName = suffixName;
        this.colorName = colorName;
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

    public String getColorName() {
        return this.colorName;
    }
    
    public void setColorName(String colorName) {
        this.colorName = colorName;
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