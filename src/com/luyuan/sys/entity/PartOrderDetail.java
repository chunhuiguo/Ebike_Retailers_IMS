package com.luyuan.sys.entity;



/**
 * PartOrderDetail entity. @author MyEclipse Persistence Tools
 */

public class PartOrderDetail  implements java.io.Serializable {


    // Fields    

     private Integer orderDetailId;
     private Integer orderId;
     private String orgCode;
     private String orgName;
     private String orgType;
     private String orgUnit;
     private Double orgQty;
     private Double orgPrice;
     private String destCode;//实发配件编码
     private String destName;//实发配件名称
     private String destType;//实发配件规格
     private String destUnit;//实发配件单位
     private Double destQty;
     private Double destPrice;//实发配件价格
     private String productCode;
     private String productDescription;
     private Double actualQty;//实发配件数量
     private String warehouseCode;
     private String comment;
     private String boxNumber;
     private String productColor;


    // Constructors

    /** default constructor */
    public PartOrderDetail() {
    }

	/** minimal constructor */
    public PartOrderDetail(Integer orderId) {
        this.orderId = orderId;
    }
    
    /** full constructor */
    public PartOrderDetail(Integer orderId, String orgCode, String orgName, String orgType, String orgUnit, Double orgQty, Double orgPrice, String destCode, String destName, String destType, String destUnit, Double destQty, Double destPrice, String productCode, String productDescription, Double actualQty, String warehouseCode, String comment, String boxNumber, String productColor) {
        this.orderId = orderId;
        this.orgCode = orgCode;
        this.orgName = orgName;
        this.orgType = orgType;
        this.orgUnit = orgUnit;
        this.orgQty = orgQty;
        this.orgPrice = orgPrice;
        this.destCode = destCode;
        this.destName = destName;
        this.destType = destType;
        this.destUnit = destUnit;
        this.destQty = destQty;
        this.destPrice = destPrice;
        this.productCode = productCode;
        this.productDescription = productDescription;
        this.actualQty = actualQty;
        this.warehouseCode = warehouseCode;
        this.comment = comment;
        this.boxNumber = boxNumber;
        this.productColor = productColor;
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

    public String getOrgCode() {
        return this.orgCode;
    }
    
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return this.orgName;
    }
    
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgType() {
        return this.orgType;
    }
    
    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public String getOrgUnit() {
        return this.orgUnit;
    }
    
    public void setOrgUnit(String orgUnit) {
        this.orgUnit = orgUnit;
    }

    public Double getOrgQty() {
        return this.orgQty;
    }
    
    public void setOrgQty(Double orgQty) {
        this.orgQty = orgQty;
    }

    public Double getOrgPrice() {
        return this.orgPrice;
    }
    
    public void setOrgPrice(Double orgPrice) {
        this.orgPrice = orgPrice;
    }

    public String getDestCode() {
        return this.destCode;
    }
    
    public void setDestCode(String destCode) {
        this.destCode = destCode;
    }

    public String getDestName() {
        return this.destName;
    }
    
    public void setDestName(String destName) {
        this.destName = destName;
    }

    public String getDestType() {
        return this.destType;
    }
    
    public void setDestType(String destType) {
        this.destType = destType;
    }

    public String getDestUnit() {
        return this.destUnit;
    }
    
    public void setDestUnit(String destUnit) {
        this.destUnit = destUnit;
    }

    public Double getDestQty() {
        return this.destQty;
    }
    
    public void setDestQty(Double destQty) {
        this.destQty = destQty;
    }

    public Double getDestPrice() {
        return this.destPrice;
    }
    
    public void setDestPrice(Double destPrice) {
        this.destPrice = destPrice;
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

    public Double getActualQty() {
        return this.actualQty;
    }
    
    public void setActualQty(Double actualQty) {
        this.actualQty = actualQty;
    }

    public String getWarehouseCode() {
        return this.warehouseCode;
    }
    
    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getComment() {
        return this.comment;
    }
    
    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getBoxNumber() {
        return this.boxNumber;
    }
    
    public void setBoxNumber(String boxNumber) {
        this.boxNumber = boxNumber;
    }

    public String getProductColor() {
        return this.productColor;
    }
    
    public void setProductColor(String productColor) {
        this.productColor = productColor;
    }
   








}