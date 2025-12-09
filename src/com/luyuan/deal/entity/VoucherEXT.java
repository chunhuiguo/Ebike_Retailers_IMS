package com.luyuan.deal.entity;



/**
 * VoucherEXT entity. @author MyEclipse Persistence Tools
 */

public class VoucherEXT  implements java.io.Serializable {


    // Fields    

     private Long id;
     private Integer dealerId;
     private Integer shopId;
     private Long voucherId;
     private String voucherType;
     private String productBarcode;


    // Constructors

    /** default constructor */
    public VoucherEXT() {
    }

    
    /** full constructor */
    public VoucherEXT(Integer dealerId, Integer shopId, Long voucherId, String voucherType, String productBarcode) {
        this.dealerId = dealerId;
        this.shopId = shopId;
        this.voucherId = voucherId;
        this.voucherType = voucherType;
        this.productBarcode = productBarcode;
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

    public Integer getShopId() {
        return this.shopId;
    }
    
    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Long getVoucherId() {
        return this.voucherId;
    }
    
    public void setVoucherId(Long voucherId) {
        this.voucherId = voucherId;
    }

    public String getVoucherType() {
        return this.voucherType;
    }
    
    public void setVoucherType(String voucherType) {
        this.voucherType = voucherType;
    }

    public String getProductBarcode() {
        return this.productBarcode;
    }
    
    public void setProductBarcode(String productBarcode) {
        this.productBarcode = productBarcode;
    }
   








}