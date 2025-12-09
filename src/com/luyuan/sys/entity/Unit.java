package com.luyuan.sys.entity;



/**
 * Unit entity. @author MyEclipse Persistence Tools
 */

public class Unit  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String shortName;
     private String fullName;
     private String type;
     private Integer parentId;
     private String parentShortName;
     private String directorName;
     private String directorTitle;
     private String directorPhone;
     private String phone;
     private String fax;
     private String address;
     private String city;
     private String province;
     private Boolean disable;
     private String registerDate;
     private String endDate;
     private String databaseMap;


    // Constructors

    /** default constructor */
    public Unit() {
    }

	/** minimal constructor */
    public Unit(String shortName, String fullName, String type, Integer parentId, String parentShortName, Boolean disable, String registerDate, String databaseMap) {
        this.shortName = shortName;
        this.fullName = fullName;
        this.type = type;
        this.parentId = parentId;
        this.parentShortName = parentShortName;
        this.disable = disable;
        this.registerDate = registerDate;
        this.databaseMap = databaseMap;
    }
    
    /** full constructor */
    public Unit(String shortName, String fullName, String type, Integer parentId, String parentShortName, String directorName, String directorTitle, String directorPhone, String phone, String fax, String address, String city, String province, Boolean disable, String registerDate, String endDate, String databaseMap) {
        this.shortName = shortName;
        this.fullName = fullName;
        this.type = type;
        this.parentId = parentId;
        this.parentShortName = parentShortName;
        this.directorName = directorName;
        this.directorTitle = directorTitle;
        this.directorPhone = directorPhone;
        this.phone = phone;
        this.fax = fax;
        this.address = address;
        this.city = city;
        this.province = province;
        this.disable = disable;
        this.registerDate = registerDate;
        this.endDate = endDate;
        this.databaseMap = databaseMap;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getShortName() {
        return this.shortName;
    }
    
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getFullName() {
        return this.fullName;
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }

    public Integer getParentId() {
        return this.parentId;
    }
    
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getParentShortName() {
        return this.parentShortName;
    }
    
    public void setParentShortName(String parentShortName) {
        this.parentShortName = parentShortName;
    }

    public String getDirectorName() {
        return this.directorName;
    }
    
    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getDirectorTitle() {
        return this.directorTitle;
    }
    
    public void setDirectorTitle(String directorTitle) {
        this.directorTitle = directorTitle;
    }

    public String getDirectorPhone() {
        return this.directorPhone;
    }
    
    public void setDirectorPhone(String directorPhone) {
        this.directorPhone = directorPhone;
    }

    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return this.fax;
    }
    
    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return this.city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return this.province;
    }
    
    public void setProvince(String province) {
        this.province = province;
    }

    public Boolean getDisable() {
        return this.disable;
    }
    
    public void setDisable(Boolean disable) {
        this.disable = disable;
    }

    public String getRegisterDate() {
        return this.registerDate;
    }
    
    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getEndDate() {
        return this.endDate;
    }
    
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDatabaseMap() {
        return this.databaseMap;
    }
    
    public void setDatabaseMap(String databaseMap) {
        this.databaseMap = databaseMap;
    }
   








}