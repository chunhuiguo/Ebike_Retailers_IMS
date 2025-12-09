package com.luyuan.sys.entity;

import java.sql.Timestamp;


/**
 * PartOrder entity. @author MyEclipse Persistence Tools
 */

public class PartOrder  implements java.io.Serializable {


    // Fields    

     private Integer orderId;
     private String orderCode;
     private String dealerCode;
     private Timestamp orderDate;
     private String orderType;
     private Timestamp requiredDate;
     private Timestamp shipDate;
     private Timestamp receivedDate;
     private Integer shipOrderId;
     private String shipCompany;
     private String shipAddress;
     private String contactName;
     private String contactPhone;
     private Double orderAmount;
     private String status;
     private Timestamp statusDate;
     private Integer userId;
     private String userName;
     private Integer auditorId;
     private String auditor;
     private Double balance;
     private Double balanceReceipt;
     private Double balanceProduct;
     private Double balancePart;
     private Double balanceRepair;
     private Double balanceAdvertising;
     private Double frozen;
     private Double creditLimit;
     private Double creditLimitFree;
     private Double creditLimitNoFree;
     private Double creditUsed;
     private Double creditFreeUsed;
     private Double creditNoFreeUsed;
     private Double securedLoanUsed;
     private Timestamp auditDate;


    // Constructors

    /** default constructor */
    public PartOrder() {
    }

    
    /** full constructor */
    public PartOrder(String orderCode, String dealerCode, Timestamp orderDate, String orderType, Timestamp requiredDate, Timestamp shipDate, Timestamp receivedDate, Integer shipOrderId, String shipCompany, String shipAddress, String contactName, String contactPhone, Double orderAmount, String status, Timestamp statusDate, Integer userId, String userName, Integer auditorId, String auditor, Double balance, Double balanceReceipt, Double balanceProduct, Double balancePart, Double balanceRepair, Double balanceAdvertising, Double frozen, Double creditLimit, Double creditLimitFree, Double creditLimitNoFree, Double creditUsed, Double creditFreeUsed, Double creditNoFreeUsed, Double securedLoanUsed, Timestamp auditDate) {
        this.orderCode = orderCode;
        this.dealerCode = dealerCode;
        this.orderDate = orderDate;
        this.orderType = orderType;
        this.requiredDate = requiredDate;
        this.shipDate = shipDate;
        this.receivedDate = receivedDate;
        this.shipOrderId = shipOrderId;
        this.shipCompany = shipCompany;
        this.shipAddress = shipAddress;
        this.contactName = contactName;
        this.contactPhone = contactPhone;
        this.orderAmount = orderAmount;
        this.status = status;
        this.statusDate = statusDate;
        this.userId = userId;
        this.userName = userName;
        this.auditorId = auditorId;
        this.auditor = auditor;
        this.balance = balance;
        this.balanceReceipt = balanceReceipt;
        this.balanceProduct = balanceProduct;
        this.balancePart = balancePart;
        this.balanceRepair = balanceRepair;
        this.balanceAdvertising = balanceAdvertising;
        this.frozen = frozen;
        this.creditLimit = creditLimit;
        this.creditLimitFree = creditLimitFree;
        this.creditLimitNoFree = creditLimitNoFree;
        this.creditUsed = creditUsed;
        this.creditFreeUsed = creditFreeUsed;
        this.creditNoFreeUsed = creditNoFreeUsed;
        this.securedLoanUsed = securedLoanUsed;
        this.auditDate = auditDate;
    }

   
    // Property accessors

    public Integer getOrderId() {
        return this.orderId;
    }
    
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderCode() {
        return this.orderCode;
    }
    
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
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

    public String getOrderType() {
        return this.orderType;
    }
    
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Timestamp getRequiredDate() {
        return this.requiredDate;
    }
    
    public void setRequiredDate(Timestamp requiredDate) {
        this.requiredDate = requiredDate;
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

    public Integer getShipOrderId() {
        return this.shipOrderId;
    }
    
    public void setShipOrderId(Integer shipOrderId) {
        this.shipOrderId = shipOrderId;
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

    public Double getOrderAmount() {
        return this.orderAmount;
    }
    
    public void setOrderAmount(Double orderAmount) {
        this.orderAmount = orderAmount;
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

    public String getAuditor() {
        return this.auditor;
    }
    
    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public Double getBalance() {
        return this.balance;
    }
    
    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getBalanceReceipt() {
        return this.balanceReceipt;
    }
    
    public void setBalanceReceipt(Double balanceReceipt) {
        this.balanceReceipt = balanceReceipt;
    }

    public Double getBalanceProduct() {
        return this.balanceProduct;
    }
    
    public void setBalanceProduct(Double balanceProduct) {
        this.balanceProduct = balanceProduct;
    }

    public Double getBalancePart() {
        return this.balancePart;
    }
    
    public void setBalancePart(Double balancePart) {
        this.balancePart = balancePart;
    }

    public Double getBalanceRepair() {
        return this.balanceRepair;
    }
    
    public void setBalanceRepair(Double balanceRepair) {
        this.balanceRepair = balanceRepair;
    }

    public Double getBalanceAdvertising() {
        return this.balanceAdvertising;
    }
    
    public void setBalanceAdvertising(Double balanceAdvertising) {
        this.balanceAdvertising = balanceAdvertising;
    }

    public Double getFrozen() {
        return this.frozen;
    }
    
    public void setFrozen(Double frozen) {
        this.frozen = frozen;
    }

    public Double getCreditLimit() {
        return this.creditLimit;
    }
    
    public void setCreditLimit(Double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public Double getCreditLimitFree() {
        return this.creditLimitFree;
    }
    
    public void setCreditLimitFree(Double creditLimitFree) {
        this.creditLimitFree = creditLimitFree;
    }

    public Double getCreditLimitNoFree() {
        return this.creditLimitNoFree;
    }
    
    public void setCreditLimitNoFree(Double creditLimitNoFree) {
        this.creditLimitNoFree = creditLimitNoFree;
    }

    public Double getCreditUsed() {
        return this.creditUsed;
    }
    
    public void setCreditUsed(Double creditUsed) {
        this.creditUsed = creditUsed;
    }

    public Double getCreditFreeUsed() {
        return this.creditFreeUsed;
    }
    
    public void setCreditFreeUsed(Double creditFreeUsed) {
        this.creditFreeUsed = creditFreeUsed;
    }

    public Double getCreditNoFreeUsed() {
        return this.creditNoFreeUsed;
    }
    
    public void setCreditNoFreeUsed(Double creditNoFreeUsed) {
        this.creditNoFreeUsed = creditNoFreeUsed;
    }

    public Double getSecuredLoanUsed() {
        return this.securedLoanUsed;
    }
    
    public void setSecuredLoanUsed(Double securedLoanUsed) {
        this.securedLoanUsed = securedLoanUsed;
    }

    public Timestamp getAuditDate() {
        return this.auditDate;
    }
    
    public void setAuditDate(Timestamp auditDate) {
        this.auditDate = auditDate;
    }
   








}