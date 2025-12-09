package com.luyuan.info.entity;



/**
 * Employee entity. @author MyEclipse Persistence Tools
 */

public class Employee  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer dealerId;
     private Integer shopId;
     private Integer userId;
     private String dealerShortName;
     private String shopShortName;
     private String name;
     private String title;
     private String phone;
     private String address;
     private Boolean disable;
     private String remark;


    // Constructors

    /** default constructor */
    public Employee() {
    }

	/** minimal constructor */
    public Employee(Integer dealerId, Integer shopId, String dealerShortName, String shopShortName, String name, Boolean disable) {
        this.dealerId = dealerId;
        this.shopId = shopId;
        this.dealerShortName = dealerShortName;
        this.shopShortName = shopShortName;
        this.name = name;
        this.disable = disable;
    }
    
    /** full constructor */
    public Employee(Integer dealerId, Integer shopId, Integer userId, String dealerShortName, String shopShortName, String name, String title, String phone, String address, Boolean disable, String remark) {
        this.dealerId = dealerId;
        this.shopId = shopId;
        this.userId = userId;
        this.dealerShortName = dealerShortName;
        this.shopShortName = shopShortName;
        this.name = name;
        this.title = title;
        this.phone = phone;
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

    public Integer getUserId() {
        return this.userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
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