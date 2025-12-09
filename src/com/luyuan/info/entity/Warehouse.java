package com.luyuan.info.entity;



/**
 * Warehouse entity. @author MyEclipse Persistence Tools
 */

public class Warehouse  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer dealerId;
     private Integer shopId;
     private String dealerShortName;
     private String shopShortName;
     private String name;
     private String address;
     private Boolean disable;
     private String remark;


    // Constructors

    /** default constructor */
    public Warehouse() {
    }

	/** minimal constructor */
    public Warehouse(Integer dealerId, Integer shopId, String dealerShortName, String shopShortName, String name, String address, Boolean disable) {
        this.dealerId = dealerId;
        this.shopId = shopId;
        this.dealerShortName = dealerShortName;
        this.shopShortName = shopShortName;
        this.name = name;
        this.address = address;
        this.disable = disable;
    }
    
    /** full constructor */
    public Warehouse(Integer dealerId, Integer shopId, String dealerShortName, String shopShortName, String name, String address, Boolean disable, String remark) {
        this.dealerId = dealerId;
        this.shopId = shopId;
        this.dealerShortName = dealerShortName;
        this.shopShortName = shopShortName;
        this.name = name;
        this.address = address;
        this.disable = disable;
        this.remark = remark;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
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

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getDisable() {
        return this.disable;
    }
    
    public void setDisable(Boolean disable) {
        this.disable = disable;
    }

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
   








}