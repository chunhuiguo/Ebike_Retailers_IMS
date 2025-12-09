package com.luyuan.sys.entity;



/**
 * ProductSpec entity. @author MyEclipse Persistence Tools
 */

public class ProductSpec  implements java.io.Serializable {


    // Fields    

     private String specCode;
     private String wheelDiameter;


    // Constructors

    /** default constructor */
    public ProductSpec() {
    }

    
    /** full constructor */
    public ProductSpec(String wheelDiameter) {
        this.wheelDiameter = wheelDiameter;
    }

   
    // Property accessors

    public String getSpecCode() {
        return this.specCode;
    }
    
    public void setSpecCode(String specCode) {
        this.specCode = specCode;
    }

    public String getWheelDiameter() {
        return this.wheelDiameter;
    }
    
    public void setWheelDiameter(String wheelDiameter) {
        this.wheelDiameter = wheelDiameter;
    }
   








}