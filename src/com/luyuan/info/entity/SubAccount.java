package com.luyuan.info.entity;



/**
 * SubAccount entity. @author MyEclipse Persistence Tools
 */

public class SubAccount  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer dealerId;
     private Integer dealingUnitId;
     private String type;
     private String name;
     private Double balance;
     private Integer parentAccountId;
     private Boolean disable;


    // Constructors

    /** default constructor */
    public SubAccount() {
    }

    
    /** full constructor */
    public SubAccount(Integer dealerId, Integer dealingUnitId, String type, String name, Double balance, Integer parentAccountId, Boolean disable) {
        this.dealerId = dealerId;
        this.dealingUnitId = dealingUnitId;
        this.type = type;
        this.name = name;
        this.balance = balance;
        this.parentAccountId = parentAccountId;
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

    public Integer getDealingUnitId() {
        return this.dealingUnitId;
    }
    
    public void setDealingUnitId(Integer dealingUnitId) {
        this.dealingUnitId = dealingUnitId;
    }

    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public Double getBalance() {
        return this.balance;
    }
    
    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Integer getParentAccountId() {
        return this.parentAccountId;
    }
    
    public void setParentAccountId(Integer parentAccountId) {
        this.parentAccountId = parentAccountId;
    }

    public Boolean getDisable() {
        return this.disable;
    }
    
    public void setDisable(Boolean disable) {
        this.disable = disable;
    }
   








}