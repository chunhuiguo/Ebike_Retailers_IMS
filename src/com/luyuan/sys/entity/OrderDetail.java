package com.luyuan.sys.entity;



/**
 * OrderDetail entity. @author MyEclipse Persistence Tools
 */

public class OrderDetail  implements java.io.Serializable {


    // Fields    

     private Integer orderDetailId;
     private Integer orderId;
     private String productCode;//整车编码
     private String productDescription;//整车名称
     private Integer orderQty;
     private Double unitPrice;//整车价格，空车价格 = unitPrice - batteryStdQty * batteryPrice
     private String salePriceType;
     private String batteryCode;//实发标配电池编码
     private Integer batteryQty;//实发标配电池数量
     private Double batteryPrice;//实发标配电池价格
     private Boolean isSumQty;
     private Integer monthRebates;
     private Integer quarterRebates;
     private Integer yearRebates;
     private String batteryDescription;//实发标配电池名称
     private String actualBatteryCode;//实发换配电池编码
     private Integer actualBatteryQty;//实发换配电池数量
     private Double actualBatteryPrice;//实发换配电池价格
     private String actualBatteryDescription;//实发换配电池名称
     private String brief;
     private Integer batteryStdQty;//标配电池数量
     private Integer actualQty;//实发整车数量
     private Double discountPrice;
     private String discountType;
     private String orgSalePriceType;
     private Double orgUnitPrice;
     private Integer orgBatteryQty;
     private Integer orgActualBatteryQty;


    // Constructors

    /** default constructor */
    public OrderDetail() {
    }

	/** minimal constructor */
    public OrderDetail(Integer orderQty) {
        this.orderQty = orderQty;
    }
    
    /** full constructor */
    public OrderDetail(Integer orderId, String productCode, String productDescription, Integer orderQty, Double unitPrice, String salePriceType, String batteryCode, Integer batteryQty, Double batteryPrice, Boolean isSumQty, Integer monthRebates, Integer quarterRebates, Integer yearRebates, String batteryDescription, String actualBatteryCode, Integer actualBatteryQty, Double actualBatteryPrice, String actualBatteryDescription, String brief, Integer batteryStdQty, Integer actualQty, Double discountPrice, String discountType, String orgSalePriceType, Double orgUnitPrice, Integer orgBatteryQty, Integer orgActualBatteryQty) {
        this.orderId = orderId;
        this.productCode = productCode;
        this.productDescription = productDescription;
        this.orderQty = orderQty;
        this.unitPrice = unitPrice;
        this.salePriceType = salePriceType;
        this.batteryCode = batteryCode;
        this.batteryQty = batteryQty;
        this.batteryPrice = batteryPrice;
        this.isSumQty = isSumQty;
        this.monthRebates = monthRebates;
        this.quarterRebates = quarterRebates;
        this.yearRebates = yearRebates;
        this.batteryDescription = batteryDescription;
        this.actualBatteryCode = actualBatteryCode;
        this.actualBatteryQty = actualBatteryQty;
        this.actualBatteryPrice = actualBatteryPrice;
        this.actualBatteryDescription = actualBatteryDescription;
        this.brief = brief;
        this.batteryStdQty = batteryStdQty;
        this.actualQty = actualQty;
        this.discountPrice = discountPrice;
        this.discountType = discountType;
        this.orgSalePriceType = orgSalePriceType;
        this.orgUnitPrice = orgUnitPrice;
        this.orgBatteryQty = orgBatteryQty;
        this.orgActualBatteryQty = orgActualBatteryQty;
    }

   
    // Property accessors

    public Integer getOrderDetailId() {
        return this.orderDetailId;
    }
    
    public void setOrderDetailId(Integer orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Integer getOrderId() {
        return this.orderId;
    }
    
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getProductCode() {
        return this.productCode;
    }
    
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductDescription() {
        return this.productDescription;
    }
    
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Integer getOrderQty() {
        return this.orderQty;
    }
    
    public void setOrderQty(Integer orderQty) {
        this.orderQty = orderQty;
    }

    public Double getUnitPrice() {
        return this.unitPrice;
    }
    
    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getSalePriceType() {
        return this.salePriceType;
    }
    
    public void setSalePriceType(String salePriceType) {
        this.salePriceType = salePriceType;
    }

    public String getBatteryCode() {
        return this.batteryCode;
    }
    
    public void setBatteryCode(String batteryCode) {
        this.batteryCode = batteryCode;
    }

    public Integer getBatteryQty() {
        return this.batteryQty;
    }
    
    public void setBatteryQty(Integer batteryQty) {
        this.batteryQty = batteryQty;
    }

    public Double getBatteryPrice() {
        return this.batteryPrice;
    }
    
    public void setBatteryPrice(Double batteryPrice) {
        this.batteryPrice = batteryPrice;
    }

    public Boolean getIsSumQty() {
        return this.isSumQty;
    }
    
    public void setIsSumQty(Boolean isSumQty) {
        this.isSumQty = isSumQty;
    }

    public Integer getMonthRebates() {
        return this.monthRebates;
    }
    
    public void setMonthRebates(Integer monthRebates) {
        this.monthRebates = monthRebates;
    }

    public Integer getQuarterRebates() {
        return this.quarterRebates;
    }
    
    public void setQuarterRebates(Integer quarterRebates) {
        this.quarterRebates = quarterRebates;
    }

    public Integer getYearRebates() {
        return this.yearRebates;
    }
    
    public void setYearRebates(Integer yearRebates) {
        this.yearRebates = yearRebates;
    }

    public String getBatteryDescription() {
        return this.batteryDescription;
    }
    
    public void setBatteryDescription(String batteryDescription) {
        this.batteryDescription = batteryDescription;
    }

    public String getActualBatteryCode() {
        return this.actualBatteryCode;
    }
    
    public void setActualBatteryCode(String actualBatteryCode) {
        this.actualBatteryCode = actualBatteryCode;
    }

    public Integer getActualBatteryQty() {
        return this.actualBatteryQty;
    }
    
    public void setActualBatteryQty(Integer actualBatteryQty) {
        this.actualBatteryQty = actualBatteryQty;
    }

    public Double getActualBatteryPrice() {
        return this.actualBatteryPrice;
    }
    
    public void setActualBatteryPrice(Double actualBatteryPrice) {
        this.actualBatteryPrice = actualBatteryPrice;
    }

    public String getActualBatteryDescription() {
        return this.actualBatteryDescription;
    }
    
    public void setActualBatteryDescription(String actualBatteryDescription) {
        this.actualBatteryDescription = actualBatteryDescription;
    }

    public String getBrief() {
        return this.brief;
    }
    
    public void setBrief(String brief) {
        this.brief = brief;
    }

    public Integer getBatteryStdQty() {
        return this.batteryStdQty;
    }
    
    public void setBatteryStdQty(Integer batteryStdQty) {
        this.batteryStdQty = batteryStdQty;
    }

    public Integer getActualQty() {
        return this.actualQty;
    }
    
    public void setActualQty(Integer actualQty) {
        this.actualQty = actualQty;
    }

    public Double getDiscountPrice() {
        return this.discountPrice;
    }
    
    public void setDiscountPrice(Double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getDiscountType() {
        return this.discountType;
    }
    
    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public String getOrgSalePriceType() {
        return this.orgSalePriceType;
    }
    
    public void setOrgSalePriceType(String orgSalePriceType) {
        this.orgSalePriceType = orgSalePriceType;
    }

    public Double getOrgUnitPrice() {
        return this.orgUnitPrice;
    }
    
    public void setOrgUnitPrice(Double orgUnitPrice) {
        this.orgUnitPrice = orgUnitPrice;
    }

    public Integer getOrgBatteryQty() {
        return this.orgBatteryQty;
    }
    
    public void setOrgBatteryQty(Integer orgBatteryQty) {
        this.orgBatteryQty = orgBatteryQty;
    }

    public Integer getOrgActualBatteryQty() {
        return this.orgActualBatteryQty;
    }
    
    public void setOrgActualBatteryQty(Integer orgActualBatteryQty) {
        this.orgActualBatteryQty = orgActualBatteryQty;
    }
   








}