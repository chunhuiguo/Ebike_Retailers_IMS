package com.luyuan.deal.entity;



/**
 * CodeCreator entity. @author MyEclipse Persistence Tools
 */

public class CodeCreator  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer dealerId;
     private String prefix;
     private String year;
     private Integer numberSerial;
     private Long version;


    // Constructors

    /** default constructor */
    public CodeCreator() {
    }

    
    /** full constructor */
    public CodeCreator(Integer dealerId, String prefix, String year, Integer numberSerial, Long version) {
        this.dealerId = dealerId;
        this.prefix = prefix;
        this.year = year;
        this.numberSerial = numberSerial;
        this.version = version;
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

    public String getPrefix() {
        return this.prefix;
    }
    
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getYear() {
        return this.year;
    }
    
    public void setYear(String year) {
        this.year = year;
    }

    public Integer getNumberSerial() {
        return this.numberSerial;
    }
    
    public void setNumberSerial(Integer numberSerial) {
        this.numberSerial = numberSerial;
    }

    public Long getVersion() {
        return this.version;
    }
    
    public void setVersion(Long version) {
        this.version = version;
    }
   








}