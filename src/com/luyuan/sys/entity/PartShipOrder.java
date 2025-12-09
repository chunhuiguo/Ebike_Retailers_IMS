package com.luyuan.sys.entity;

import java.sql.Timestamp;


/**
 * PartShipOrder entity. @author MyEclipse Persistence Tools
 */

public class PartShipOrder  implements java.io.Serializable {


    // Fields    

     private Integer shipOrderId;
     private String shipOrderCode;
     private String dealerCode;
     private String shipCompany;
     private String shipAddress;
     private String contactName;
     private String contactPhone;
     private Timestamp orderDate;
     private Timestamp shipDate;
     private Integer userId;
     private Integer auditorId;
     private String status;
     private Timestamp statusDate;
     private String boxingInfo;
     private String shippingWay;
     private String driver;
     private String driverPhone;
     private String statisticsCode;
     private String orderType;
     private String warehouseCode;
     private Integer bookingnumber;


    // Constructors

    /** default constructor */
    public PartShipOrder() {
    }

    
    /** full constructor */
    public PartShipOrder(String shipOrderCode, String dealerCode, String shipCompany, String shipAddress, String contactName, String contactPhone, Timestamp orderDate, Timestamp shipDate, Integer userId, Integer auditorId, String status, Timestamp statusDate, String boxingInfo, String shippingWay, String driver, String driverPhone, String statisticsCode, String orderType, String warehouseCode, Integer bookingnumber) {
        this.shipOrderCode = shipOrderCode;
        this.dealerCode = dealerCode;
        this.shipCompany = shipCompany;
        this.shipAddress = shipAddress;
        this.contactName = contactName;
        this.contactPhone = contactPhone;
        this.orderDate = orderDate;
        this.shipDate = shipDate;
        this.userId = userId;
        this.auditorId = auditorId;
        this.status = status;
        this.statusDate = statusDate;
        this.boxingInfo = boxingInfo;
        this.shippingWay = shippingWay;
        this.driver = driver;
        this.driverPhone = driverPhone;
        this.statisticsCode = statisticsCode;
        this.orderType = orderType;
        this.warehouseCode = warehouseCode;
        this.bookingnumber = bookingnumber;
    }

   
    // Property accessors

    public Integer getShipOrderId() {
        return this.shipOrderId;
    }
    
    public void setShipOrderId(Integer shipOrderId) {
        this.shipOrderId = shipOrderId;
    }

    public String getShipOrderCode() {
        return this.shipOrderCode;
    }
    
    public void setShipOrderCode(String shipOrderCode) {
        this.shipOrderCode = shipOrderCode;
    }

    public String getDealerCode() {
        return this.dealerCode;
    }
    
    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }

    public String getShipCompany() {
        return this.shipCompany;
    }
    
    public void setShipCompany(String shipCompany) {
        this.shipCompany = shipCompany;
    }

    public String getShipAddress() {
        return this.shipAddress;
    }
    
    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    public String getContactName() {
        return this.contactName;
    }
    
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return this.contactPhone;
    }
    
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public Timestamp getOrderDate() {
        return this.orderDate;
    }
    
    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public Timestamp getShipDate() {
        return this.shipDate;
    }
    
    public void setShipDate(Timestamp shipDate) {
        this.shipDate = shipDate;
    }

    public Integer getUserId() {
        return this.userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAuditorId() {
        return this.auditorId;
    }
    
    public void setAuditorId(Integer auditorId) {
        this.auditorId = auditorId;
    }

    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getStatusDate() {
        return this.statusDate;
    }
    
    public void setStatusDate(Timestamp statusDate) {
        this.statusDate = statusDate;
    }

    public String getBoxingInfo() {
        return this.boxingInfo;
    }
    
    public void setBoxingInfo(String boxingInfo) {
        this.boxingInfo = boxingInfo;
    }

    public String getShippingWay() {
        return this.shippingWay;
    }
    
    public void setShippingWay(String shippingWay) {
        this.shippingWay = shippingWay;
    }

    public String getDriver() {
        return this.driver;
    }
    
    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getDriverPhone() {
        return this.driverPhone;
    }
    
    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    public String getStatisticsCode() {
        return this.statisticsCode;
    }
    
    public void setStatisticsCode(String statisticsCode) {
        this.statisticsCode = statisticsCode;
    }

    public String getOrderType() {
        return this.orderType;
    }
    
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getWarehouseCode() {
        return this.warehouseCode;
    }
    
    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public Integer getBookingnumber() {
        return this.bookingnumber;
    }
    
    public void setBookingnumber(Integer bookingnumber) {
        this.bookingnumber = bookingnumber;
    }
   








}