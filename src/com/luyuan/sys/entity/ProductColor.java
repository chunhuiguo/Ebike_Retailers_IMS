package com.luyuan.sys.entity;



/**
 * ProductColor entity. @author MyEclipse Persistence Tools
 */

public class ProductColor  implements java.io.Serializable {


    // Fields    

     private String colorCode;
     private String colorName;


    // Constructors

    /** default constructor */
    public ProductColor() {
    }

    
    /** full constructor */
    public ProductColor(String colorName) {
        this.colorName = colorName;
    }

   
    // Property accessors

    public String getColorCode() {
        return this.colorCode;
    }
    
    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public String getColorName() {
        return this.colorName;
    }
    
    public void setColorName(String colorName) {
        this.colorName = colorName;
    }
   








}