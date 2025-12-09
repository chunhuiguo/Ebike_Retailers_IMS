package com.luyuan.sys.entity;



/**
 * PartDiff entity. @author MyEclipse Persistence Tools
 */

public class PartDiff  implements java.io.Serializable {


    // Fields    

     private PartDiffId id;
     private String partCode;
     private String partName;
     private String specType;
     private String unit;


    // Constructors

    /** default constructor */
    public PartDiff() {
    }

    
    /** full constructor */
    public PartDiff(PartDiffId id) {
        this.id = id;
    }
    
    /** full constructor */
    public PartDiff(PartDiffId id, String partCode, String partName, String specType, String unit) {
        this.id = id;
        this.partCode = partCode;
        this.partName = partName;
        this.specType = specType;
        this.unit = unit;
    }

   
    // Property accessors

    public PartDiffId getId() {
        return this.id;
    }
    
    public void setId(PartDiffId id) {
        this.id = id;
    }
    
    public String getPartCode() {
        return this.partCode;
    }
    
    public void setPartCode(String partCode) {
        this.partCode = partCode;
    }

    public String getPartName() {
        return this.partName;
    }
    
    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getSpecType() {
        return this.specType;
    }
    
    public void setSpecType(String specType) {
        this.specType = specType;
    }

    public String getUnit() {
        return this.unit;
    }
    
    public void setUnit(String unit) {
        this.unit = unit;
    }

}