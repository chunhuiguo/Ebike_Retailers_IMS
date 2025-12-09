package com.luyuan.sys.entity;



/**
 * ProductDiffId entity. @author MyEclipse Persistence Tools
 */

public class ProductDiffId  implements java.io.Serializable {


    // Fields    

     private Long productId;
     


    // Constructors

    /** default constructor */
    public ProductDiffId() {
    }

    public ProductDiffId(Long productId) {
    	this.productId = productId;
    }
   

   
    // Property accessors

    public Long getProductId() {
        return this.productId;
    }
    
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    
}