package com.luyuan.info.entity;



/**
 * SubAccountType entity. @author MyEclipse Persistence Tools
 */

public class SubAccountType  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer dealerId;
     private String name;


    // Constructors

    /** default constructor */
    public SubAccountType() {
    }

    
    /** full constructor */
    public SubAccountType(Integer dealerId, String name) {
        this.dealerId = dealerId;
        this.name = name;
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

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
   








}