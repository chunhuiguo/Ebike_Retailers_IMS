package com.luyuan.deal.entity;



/**
 * VoucherDetail entity. @author MyEclipse Persistence Tools
 */

public class VoucherDetail  implements java.io.Serializable {


    // Fields    

     private Long id;
     private Long voucherId;
     private Long productId;
     private String productName;
     private String productCode;
     private String productColor;
     private String productUnit;
     private Double price;
     private Double discount;
     private String priceType;
     private Integer qty;
     private Double total;
     private String remark;


    // Constructors

    /** default constructor */
    public VoucherDetail() {
    }

	/** minimal constructor */
    public VoucherDetail(Long voucherId, Long productId, String productName, String productCode, String productColor, String productUnit, Double price, Integer qty, Double total) {
        this.voucherId = voucherId;
        this.productId = productId;
        this.productName = productName;
        this.productCode = productCode;
        this.productColor = productColor;
        this.productUnit = productUnit;
        this.price = price;
        this.qty = qty;
        this.total = total;
    }
    
    /** full constructor */
    public VoucherDetail(Long voucherId, Long productId, String productName, String productCode, String productColor, String productUnit, Double price, Double discount, String priceType, Integer qty, Double total, String remark) {
        this.voucherId = voucherId;
        this.productId = productId;
        this.productName = productName;
        this.productCode = productCode;
        this.productColor = productColor;
        this.productUnit = productUnit;
        this.price = price;
        this.discount = discount;
        this.priceType = priceType;
        this.qty = qty;
        this.total = total;
        this.remark = remark;
    }

   
    // Property accessors

    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public Long getVoucherId() {
        return this.voucherId;
    }
    
    public void setVoucherId(Long voucherId) {
        this.voucherId = voucherId;
    }

    public Long getProductId() {
        return this.productId;
    }
    
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return this.productName;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return this.productCode;
    }
    
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductColor() {
        return this.productColor;
    }
    
    public void setProductColor(String productColor) {
        this.productColor = productColor;
    }

    public String getProductUnit() {
        return this.productUnit;
    }
    
    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
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

    public String getPriceType() {
        return this.priceType;
    }
    
    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public Integer getQty() {
        return this.qty;
    }
    
    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Double getTotal() {
        return this.total;
    }
    
    public void setTotal(Double total) {
        this.total = total;
    }

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
   








}