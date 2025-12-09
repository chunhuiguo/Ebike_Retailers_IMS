package com.luyuan.sys.entity;

import java.sql.Timestamp;


/**
 * ShipOrder entity. @author MyEclipse Persistence Tools
 */

public class ShipOrder  implements java.io.Serializable {


    // Fields    

     private Integer shipOrderId;
     private String shipOrderCode;
     private String dealerCode;
     private Timestamp orderDate;
     private String warehouseCode;
     private Timestamp shipDate;
     private Timestamp receivedDate;
     private String shipCompany;
     private String shipAddress;
     private String contactName;
     private String contactPhone;
     private Integer userId;
     private String userName;
     private Integer auditorId;
     private String auditorName;
     private String status;
     private Timestamp statusDate;
     private String batteryOrderStatus;
     private Timestamp settlementDate;
     private String txtCode;
     private Integer bookingNumber;


    // Constructors

    /** default constructor */
    public ShipOrder() {
    }

    
    /** full constructor */
    public ShipOrder(String shipOrderCode, String dealerCode, Timestamp orderDate, String warehouseCode, Timestamp shipDate, Timestamp receivedDate, String shipCompany, String shipAddress, String contactName, String contactPhone, Integer userId, String userName, Integer auditorId, String auditorName, String status, Timestamp statusDate, String batteryOrderStatus, Timestamp settlementDate, String txtCode, Integer bookingNumber) {
        this.shipOrderCode = shipOrderCode;
        this.dealerCode = dealerCode;
        this.orderDate = orderDate;
        this.warehouseCode = warehouseCode;
        this.shipDate = shipDate;
        this.receivedDate = receivedDate;
        this.shipCompany = shipCompany;
        this.shipAddress = shipAddress;
        this.contactName = contactName;
        this.contactPhone = contactPhone;
        this.userId = userId;
        this.userName = userName;
        this.auditorId = auditorId;
        this.auditorName = auditorName;
        this.status = status;
        this.statusDate = statusDate;
        this.batteryOrderStatus = batteryOrderStatus;
        this.settlementDate = settlementDate;
        this.txtCode = txtCode;
        this.bookingNumber = bookingNumber;
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

    public Timestamp getOrderDate() {
        return this.orderDate;
    }
    
    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public String getWarehouseCode() {
        return this.warehouseCode;
    }
    
    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public Timestamp getShipDate() {
        return this.shipDate;
    }
    
    public void setShipDate(Timestamp shipDate) {
        this.shipDate = shipDate;
    }

    public Timestamp getReceivedDate() {
        return this.receivedDate;
    }
    
    public void setReceivedDate(Timestamp receivedDate) {
        this.receivedDate = receivedDate;
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

    public Integer getUserId() {
        return this.userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAuditorId() {
        return this.auditorId;
    }
    
    public void setAuditorId(Integer auditorId) {
        this.auditorId = auditorId;
    }

    public String getAuditorName() {
        return this.auditorName;
    }
    
    public void setAuditorName(String auditorName) {
        this.auditorName = auditorName;
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

    public String getBatteryOrderStatus() {
        return this.batteryOrderStatus;
    }
    
    public void setBatteryOrderStatus(String batteryOrderStatus) {
        this.batteryOrderStatus = batteryOrderStatus;
    }

    public Timestamp getSettlementDate() {
        return this.settlementDate;
    }
    
    public void setSettlementDate(Timestamp settlementDate) {
        this.settlementDate = settlementDate;
    }

    public String getTxtCode() {
        return this.txtCode;
    }
    
    public void setTxtCode(String txtCode) {
        this.txtCode = txtCode;
    }

    public Integer getBookingNumber() {
        return this.bookingNumber;
    }
    
    public void setBookingNumber(Integer bookingNumber) {
        this.bookingNumber = bookingNumber;
    }
   








}