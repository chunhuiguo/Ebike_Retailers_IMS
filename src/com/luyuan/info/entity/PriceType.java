package com.luyuan.info.entity;



/**
 * PriceType entity. @author MyEclipse Persistence Tools
 */

public class PriceType  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer dealerId;
     private String type;
     private String flag;
     private String ppFlag;


    // Constructors

    /** default constructor */
    public PriceType() {
    }

    
    /** full constructor */
    public PriceType(Integer dealerId, String type, String flag, String ppFlag) {
        this.dealerId = dealerId;
        this.type = type;
        this.flag = flag;
        this.ppFlag = ppFlag;
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

    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }

    public String getFlag() {
        return this.flag;
    }
    
    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getPpFlag() {
        return this.ppFlag;
    }
    
    public void setPpFlag(String ppFlag) {
        this.ppFlag = ppFlag;
    }
   








}