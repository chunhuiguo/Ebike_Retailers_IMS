package com.luyuan.stock.entity;



/**
 * InventoryOutApportion entity. @author MyEclipse Persistence Tools
 */

public class InventoryOutApportion  implements java.io.Serializable {


    // Fields    

     private Long id;
     private Integer dealerId;
     private Integer shopId;
     private String dealerShortName;
     private String shopShortName;
     private Long voucherDetailId;
     private Integer warehouseId;
     private String warehouseName;
     private Long productId;
     private Integer qty;
     private Double price;
     private Double total;


    // Constructors

    /** default constructor */
    public InventoryOutApportion() {
    }

    
    /** full constructor */
    public InventoryOutApportion(Integer dealerId, Integer shopId, String dealerShortName, String shopShortName, Long voucherDetailId, Integer warehouseId, String warehouseName, Long productId, Integer qty, Double price, Double total) {
        this.dealerId = dealerId;
        this.shopId = shopId;
        this.dealerShortName = dealerShortName;
        this.shopShortName = shopShortName;
        this.voucherDetailId = voucherDetailId;
        this.warehouseId = warehouseId;
        this.warehouseName = warehouseName;
        this.productId = productId;
        this.qty = qty;
        this.price = price;
        this.total = total;
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

    public String getDealerShortName() {
        return this.dealerShortName;
    }
    
    public void setDealerShortName(String dealerShortName) {
        this.dealerShortName = dealerShortName;
    }

    public String getShopShortName() {
        return this.shopShortName;
    }
    
    public void setShopShortName(String shopShortName) {
        this.shopShortName = shopShortName;
    }

    public Long getVoucherDetailId() {
        return this.voucherDetailId;
    }
    
    public void setVoucherDetailId(Long voucherDetailId) {
        this.voucherDetailId = voucherDetailId;
    }

    public Integer getWarehouseId() {
        return this.warehouseId;
    }
    
    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehouseName() {
        return this.warehouseName;
    }
    
    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public Long getProductId() {
        return this.productId;
    }
    
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQty() {
        return this.qty;
    }
    
    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Double getPrice() {
        return this.price;
    }
    
    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTotal() {
        return this.total;
    }
    
    public void setTotal(Double total) {
        this.total = total;
    }
   








}