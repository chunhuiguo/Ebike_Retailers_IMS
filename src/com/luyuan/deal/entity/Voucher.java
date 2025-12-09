package com.luyuan.deal.entity;



/**
 * 2010-07-14 edited by tom
 * Voucher
 * PVoucher  extends  Voucher
 */

public class Voucher  implements java.io.Serializable {


    // Fields    

     private Long id;
     private String code;
     private String type;
     private Integer subAccountId;
     private String subAccountType;
     private Integer dealerId;
     private Integer shopId;
     private String dealerShortName;
     private String shopShortName;
     private Integer warehouseId;
     private String warehouseName;
     private Integer dealingUnitId;
     private String dealingUnitShortName;
     private String shipOrderCode;
     private Boolean isChecked;
     private Boolean isError;
     private Integer qty;
     private Double total;
     private Double discount;
     private Double actualTotal;
     private Double paidMoney;
     private Integer handlerId;
     private String handlerName;
     private Integer creatorId;
     private String creatorName;
     private Integer accountantId;
     private String accountantName;
     private String createDate;
     private String checkDate;
     private String brief;
     private String productBarcodeTxt;
     private String remark;
 	 private String employeeName;


	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	// Constructors

    /** default constructor */
    public Voucher() {
    }

	/** minimal constructor */
    public Voucher(String code, String type, Integer dealerId, Integer shopId, String dealerShortName, String shopShortName, Integer warehouseId, String warehouseName, Integer dealingUnitId, String dealingUnitShortName, Boolean isChecked, Boolean isError, Integer qty, Double total, Double actualTotal, Integer handlerId, String handlerName, Integer creatorId, String creatorName, String createDate) {
        this.code = code;
        this.type = type;
        this.dealerId = dealerId;
        this.shopId = shopId;
        this.dealerShortName = dealerShortName;
        this.shopShortName = shopShortName;
        this.warehouseId = warehouseId;
        this.warehouseName = warehouseName;
        this.dealingUnitId = dealingUnitId;
        this.dealingUnitShortName = dealingUnitShortName;
        this.isChecked = isChecked;
        this.isError = isError;
        this.qty = qty;
        this.total = total;
        this.actualTotal = actualTotal;
        this.handlerId = handlerId;
        this.handlerName = handlerName;
        this.creatorId = creatorId;
        this.creatorName = creatorName;
        this.createDate = createDate;
    }
    
    /** full constructor */
    public Voucher(String code, String type, Integer subAccountId, String subAccountType, Integer dealerId, Integer shopId, String dealerShortName, String shopShortName, Integer warehouseId, String warehouseName, Integer dealingUnitId, String dealingUnitShortName, String shipOrderCode, Boolean isChecked, Boolean isError, Integer qty, Double total, Double discount, Double actualTotal, Double paidMoney, Integer handlerId, String handlerName, Integer creatorId, String creatorName, Integer accountantId, String accountantName, String createDate, String checkDate, String brief, String productBarcodeTxt, String remark) {
        this.code = code;
        this.type = type;
        this.subAccountId = subAccountId;
        this.subAccountType = subAccountType;
        this.dealerId = dealerId;
        this.shopId = shopId;
        this.dealerShortName = dealerShortName;
        this.shopShortName = shopShortName;
        this.warehouseId = warehouseId;
        this.warehouseName = warehouseName;
        this.dealingUnitId = dealingUnitId;
        this.dealingUnitShortName = dealingUnitShortName;
        this.shipOrderCode = shipOrderCode;
        this.isChecked = isChecked;
        this.isError = isError;
        this.qty = qty;
        this.total = total;
        this.discount = discount;
        this.actualTotal = actualTotal;
        this.paidMoney = paidMoney;
        this.handlerId = handlerId;
        this.handlerName = handlerName;
        this.creatorId = creatorId;
        this.creatorName = creatorName;
        this.accountantId = accountantId;
        this.accountantName = accountantName;
        this.createDate = createDate;
        this.checkDate = checkDate;
        this.brief = brief;
        this.productBarcodeTxt = productBarcodeTxt;
        this.remark = remark;
    }

   
    // Property accessors

    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }

    public Integer getSubAccountId() {
        return this.subAccountId;
    }
    
    public void setSubAccountId(Integer subAccountId) {
        this.subAccountId = subAccountId;
    }

    public String getSubAccountType() {
        return this.subAccountType;
    }
    
    public void setSubAccountType(String subAccountType) {
        this.subAccountType = subAccountType;
    }

    public Integer getDealerId() {
        return this.dealerId;
    }
    
    public void setDealerId(Integer dealerId) {
        this.dealerId = dealerId;
    }

    public Integer getShopId() {
        return this.shopId;
    }
    
    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getDealerShortName() {
        return this.dealerShortName;
    }
    
    public void setDealerShortName(String dealerShortName) {
        this.dealerShortName = dealerShortName;
    }

    public String getShopShortName() {
        return this.shopShortName;
    }
    
    public void setShopShortName(String shopShortName) {
        this.shopShortName = shopShortName;
    }

    public Integer getWarehouseId() {
        return this.warehouseId;
    }
    
    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehouseName() {
        return this.warehouseName;
    }
    
    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public Integer getDealingUnitId() {
        return this.dealingUnitId;
    }
    
    public void setDealingUnitId(Integer dealingUnitId) {
        this.dealingUnitId = dealingUnitId;
    }

    public String getDealingUnitShortName() {
        return this.dealingUnitShortName;
    }
    
    public void setDealingUnitShortName(String dealingUnitShortName) {
        this.dealingUnitShortName = dealingUnitShortName;
    }

    public String getShipOrderCode() {
        return this.shipOrderCode;
    }
    
    public void setShipOrderCode(String shipOrderCode) {
        this.shipOrderCode = shipOrderCode;
    }

    public Boolean getIsChecked() {
        return this.isChecked;
    }
    
    public void setIsChecked(Boolean isChecked) {
        this.isChecked = isChecked;
    }

    public Boolean getIsError() {
        return this.isError;
    }
    
    public void setIsError(Boolean isError) {
        this.isError = isError;
    }

    public Integer getQty() {
        return this.qty;
    }
    
    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Double getTotal() {
        return this.total;
    }
    
    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getDiscount() {
        return this.discount;
    }
    
    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getActualTotal() {
        return this.actualTotal;
    }
    
    public void setActualTotal(Double actualTotal) {
        this.actualTotal = actualTotal;
    }

    public Double getPaidMoney() {
        return this.paidMoney;
    }
    
    public void setPaidMoney(Double paidMoney) {
        this.paidMoney = paidMoney;
    }

    public Integer getHandlerId() {
        return this.handlerId;
    }
    
    public void setHandlerId(Integer handlerId) {
        this.handlerId = handlerId;
    }

    public String getHandlerName() {
        return this.handlerName;
    }
    
    public void setHandlerName(String handlerName) {
        this.handlerName = handlerName;
    }

    public Integer getCreatorId() {
        return this.creatorId;
    }
    
    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorName() {
        return this.creatorName;
    }
    
    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public Integer getAccountantId() {
        return this.accountantId;
    }
    
    public void setAccountantId(Integer accountantId) {
        this.accountantId = accountantId;
    }

    public String getAccountantName() {
        return this.accountantName;
    }
    
    public void setAccountantName(String accountantName) {
        this.accountantName = accountantName;
    }

    public String getCreateDate() {
        return this.createDate;
    }
    
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCheckDate() {
        return this.checkDate;
    }
    
    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

    public String getBrief() {
        return this.brief;
    }
    
    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getProductBarcodeTxt() {
        return this.productBarcodeTxt;
    }
    
    public void setProductBarcodeTxt(String productBarcodeTxt) {
        this.productBarcodeTxt = productBarcodeTxt;
    }

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
}