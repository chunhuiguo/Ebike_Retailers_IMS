package com.luyuan.sys.entity;

import java.math.BigDecimal;


/**
 * PartDiffId entity. @author MyEclipse Persistence Tools
 */

public class PartDiffId  implements java.io.Serializable {


    // Fields    

     private Long partId;     


    // Constructors

    /** default constructor */
    public PartDiffId() {
    }

	
    
    /** full constructor */
    public PartDiffId(Long partId) {
        this.partId = partId;        
    }

   
    // Property accessors

    public Long getPartId() {
        return this.partId;
    }
    
    public void setPartId(Long partId) {
        this.partId = partId;
    }

   
   



  

}