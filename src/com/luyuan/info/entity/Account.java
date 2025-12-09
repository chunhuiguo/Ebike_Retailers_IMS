package com.luyuan.info.entity;



/**
 * Account entity. @author MyEclipse Persistence Tools
 */

public class Account  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer dealerId;
     private String dealerShortName;
     private Integer dealingUnitId;
     private String dealingUnitShortName;
     private String type;
     private Double balance;
     private Boolean disable;


    // Constructors

    /** default constructor */
    public Account() {
    }

    
    /** full constructor */
    public Account(Integer dealerId, String dealerShortName, Integer dealingUnitId, String dealingUnitShortName, String type, Double balance, Boolean disable) {
        this.dealerId = dealerId;
        this.dealerShortName = dealerShortName;
        this.dealingUnitId = dealingUnitId;
        this.dealingUnitShortName = dealingUnitShortName;
        this.type = type;
        this.balance = balance;
        this.disable = disable;
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

    public String getDealerShortName() {
        return this.dealerShortName;
    }
    
    public void setDealerShortName(String dealerShortName) {
        this.dealerShortName = dealerShortName;
    }

    public Integer getDealingUnitId() {
        return this.dealingUnitId;
    }
    
    public void setDealingUnitId(Integer dealingUnitId) {
        this.dealingUnitId = dealingUnitId;
    }

    public String getDealingUnitShortName() {
        return this.dealingUnitShortName;
    }
    
    public void setDealingUnitShortName(String dealingUnitShortName) {
        this.dealingUnitShortName = dealingUnitShortName;
    }

    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }

    public Double getBalance() {
        return this.balance;
    }
    
    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Boolean getDisable() {
        return this.disable;
    }
    
    public void setDisable(Boolean disable) {
        this.disable = disable;
    }
   








}