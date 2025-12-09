package com.luyuan.stock.entity;



/**
 * InventoryBookViewId entity. @author MyEclipse Persistence Tools
 */

public class InventoryBookView  implements java.io.Serializable {


    // Fields    

     private Long id;
     private Integer dealerId;
     private Integer shopId;
     private String dealerShortName;
     private String shopShortName;
     private Integer warehouseId;
     private String warehouseName;
     private Long productId;
     private String productName;
     private Long voucherId;
     private String voucherCode;
     private String voucherType;
     private String voucherDate;
     private String shipOrderCode;
     private Integer inQty;
     private Double inTotal;
     private Integer outQty;
     private Double outTotal;
     private Integer qty;
     private Double total;
     private String remark;
     private String productCode;
     private String productColor;


    // Constructors

    /** default constructor */
    public InventoryBookView() {
    }

	/** minimal constructor */
    public InventoryBookView(Long id, Integer dealerId, Integer shopId, String dealerShortName, String shopShortName, Integer warehouseId, String warehouseName, Long productId, String productName, Long voucherId, String voucherCode, String voucherType, String voucherDate, Integer qty, Double total) {
        this.id = id;
        this.dealerId = dealerId;
        this.shopId = shopId;
        this.dealerShortName = dealerShortName;
        this.shopShortName = shopShortName;
        this.warehouseId = warehouseId;
        this.warehouseName = warehouseName;
        this.productId = productId;
        this.productName = productName;
        this.voucherId = voucherId;
        this.voucherCode = voucherCode;
        this.voucherType = voucherType;
        this.voucherDate = voucherDate;
        this.qty = qty;
        this.total = total;
    }
    
    /** full constructor */
    public InventoryBookView(Long id, Integer dealerId, Integer shopId, String dealerShortName, String shopShortName, Integer warehouseId, String warehouseName, Long productId, String productName, Long voucherId, String voucherCode, String voucherType, String voucherDate, String shipOrderCode, Integer inQty, Double inTotal, Integer outQty, Double outTotal, Integer qty, Double total, String remark, String productCode, String productColor) {
        this.id = id;
        this.dealerId = dealerId;
        this.shopId = shopId;
        this.dealerShortName = dealerShortName;
        this.shopShortName = shopShortName;
        this.warehouseId = warehouseId;
        this.warehouseName = warehouseName;
        this.productId = productId;
        this.productName = productName;
        this.voucherId = voucherId;
        this.voucherCode = voucherCode;
        this.voucherType = voucherType;
        this.voucherDate = voucherDate;
        this.shipOrderCode = shipOrderCode;
        this.inQty = inQty;
        this.inTotal = inTotal;
        this.outQty = outQty;
        this.outTotal = outTotal;
        this.qty = qty;
        this.total = total;
        this.remark = remark;
        this.productCode = productCode;
        this.productColor = productColor;
    }

   
    // Property accessors

    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
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

    public Long getProductId() {
        return this.productId;
    }
    
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return this.productName;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getVoucherId() {
        return this.voucherId;
    }
    
    public void setVoucherId(Long voucherId) {
        this.voucherId = voucherId;
    }

    public String getVoucherCode() {
        return this.voucherCode;
    }
    
    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }

    public String getVoucherType() {
        return this.voucherType;
    }
    
    public void setVoucherType(String voucherType) {
        this.voucherType = voucherType;
    }

    public String getVoucherDate() {
        return this.voucherDate;
    }
    
    public void setVoucherDate(String voucherDate) {
        this.voucherDate = voucherDate;
    }

    public String getShipOrderCode() {
        return this.shipOrderCode;
    }
    
    public void setShipOrderCode(String shipOrderCode) {
        this.shipOrderCode = shipOrderCode;
    }

    public Integer getInQty() {
        return this.inQty;
    }
    
    public void setInQty(Integer inQty) {
        this.inQty = inQty;
    }

    public Double getInTotal() {
        return this.inTotal;
    }
    
    public void setInTotal(Double inTotal) {
        this.inTotal = inTotal;
    }

    public Integer getOutQty() {
        return this.outQty;
    }
    
    public void setOutQty(Integer outQty) {
        this.outQty = outQty;
    }

    public Double getOutTotal() {
        return this.outTotal;
    }
    
    public void setOutTotal(Double outTotal) {
        this.outTotal = outTotal;
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

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getProductCode() {
        return this.productCode;
    }
    
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductColor() {
        return this.productColor;
    }
    
    public void setProductColor(String productColor) {
        this.productColor = productColor;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof InventoryBookView) ) return false;
		 InventoryBookView castOther = ( InventoryBookView ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getDealerId()==castOther.getDealerId()) || ( this.getDealerId()!=null && castOther.getDealerId()!=null && this.getDealerId().equals(castOther.getDealerId()) ) )
 && ( (this.getShopId()==castOther.getShopId()) || ( this.getShopId()!=null && castOther.getShopId()!=null && this.getShopId().equals(castOther.getShopId()) ) )
 && ( (this.getDealerShortName()==castOther.getDealerShortName()) || ( this.getDealerShortName()!=null && castOther.getDealerShortName()!=null && this.getDealerShortName().equals(castOther.getDealerShortName()) ) )
 && ( (this.getShopShortName()==castOther.getShopShortName()) || ( this.getShopShortName()!=null && castOther.getShopShortName()!=null && this.getShopShortName().equals(castOther.getShopShortName()) ) )
 && ( (this.getWarehouseId()==castOther.getWarehouseId()) || ( this.getWarehouseId()!=null && castOther.getWarehouseId()!=null && this.getWarehouseId().equals(castOther.getWarehouseId()) ) )
 && ( (this.getWarehouseName()==castOther.getWarehouseName()) || ( this.getWarehouseName()!=null && castOther.getWarehouseName()!=null && this.getWarehouseName().equals(castOther.getWarehouseName()) ) )
 && ( (this.getProductId()==castOther.getProductId()) || ( this.getProductId()!=null && castOther.getProductId()!=null && this.getProductId().equals(castOther.getProductId()) ) )
 && ( (this.getProductName()==castOther.getProductName()) || ( this.getProductName()!=null && castOther.getProductName()!=null && this.getProductName().equals(castOther.getProductName()) ) )
 && ( (this.getVoucherId()==castOther.getVoucherId()) || ( this.getVoucherId()!=null && castOther.getVoucherId()!=null && this.getVoucherId().equals(castOther.getVoucherId()) ) )
 && ( (this.getVoucherCode()==castOther.getVoucherCode()) || ( this.getVoucherCode()!=null && castOther.getVoucherCode()!=null && this.getVoucherCode().equals(castOther.getVoucherCode()) ) )
 && ( (this.getVoucherType()==castOther.getVoucherType()) || ( this.getVoucherType()!=null && castOther.getVoucherType()!=null && this.getVoucherType().equals(castOther.getVoucherType()) ) )
 && ( (this.getVoucherDate()==castOther.getVoucherDate()) || ( this.getVoucherDate()!=null && castOther.getVoucherDate()!=null && this.getVoucherDate().equals(castOther.getVoucherDate()) ) )
 && ( (this.getShipOrderCode()==castOther.getShipOrderCode()) || ( this.getShipOrderCode()!=null && castOther.getShipOrderCode()!=null && this.getShipOrderCode().equals(castOther.getShipOrderCode()) ) )
 && ( (this.getInQty()==castOther.getInQty()) || ( this.getInQty()!=null && castOther.getInQty()!=null && this.getInQty().equals(castOther.getInQty()) ) )
 && ( (this.getInTotal()==castOther.getInTotal()) || ( this.getInTotal()!=null && castOther.getInTotal()!=null && this.getInTotal().equals(castOther.getInTotal()) ) )
 && ( (this.getOutQty()==castOther.getOutQty()) || ( this.getOutQty()!=null && castOther.getOutQty()!=null && this.getOutQty().equals(castOther.getOutQty()) ) )
 && ( (this.getOutTotal()==castOther.getOutTotal()) || ( this.getOutTotal()!=null && castOther.getOutTotal()!=null && this.getOutTotal().equals(castOther.getOutTotal()) ) )
 && ( (this.getQty()==castOther.getQty()) || ( this.getQty()!=null && castOther.getQty()!=null && this.getQty().equals(castOther.getQty()) ) )
 && ( (this.getTotal()==castOther.getTotal()) || ( this.getTotal()!=null && castOther.getTotal()!=null && this.getTotal().equals(castOther.getTotal()) ) )
 && ( (this.getRemark()==castOther.getRemark()) || ( this.getRemark()!=null && castOther.getRemark()!=null && this.getRemark().equals(castOther.getRemark()) ) )
 && ( (this.getProductCode()==castOther.getProductCode()) || ( this.getProductCode()!=null && castOther.getProductCode()!=null && this.getProductCode().equals(castOther.getProductCode()) ) )
 && ( (this.getProductColor()==castOther.getProductColor()) || ( this.getProductColor()!=null && castOther.getProductColor()!=null && this.getProductColor().equals(castOther.getProductColor()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getDealerId() == null ? 0 : this.getDealerId().hashCode() );
         result = 37 * result + ( getShopId() == null ? 0 : this.getShopId().hashCode() );
         result = 37 * result + ( getDealerShortName() == null ? 0 : this.getDealerShortName().hashCode() );
         result = 37 * result + ( getShopShortName() == null ? 0 : this.getShopShortName().hashCode() );
         result = 37 * result + ( getWarehouseId() == null ? 0 : this.getWarehouseId().hashCode() );
         result = 37 * result + ( getWarehouseName() == null ? 0 : this.getWarehouseName().hashCode() );
         result = 37 * result + ( getProductId() == null ? 0 : this.getProductId().hashCode() );
         result = 37 * result + ( getProductName() == null ? 0 : this.getProductName().hashCode() );
         result = 37 * result + ( getVoucherId() == null ? 0 : this.getVoucherId().hashCode() );
         result = 37 * result + ( getVoucherCode() == null ? 0 : this.getVoucherCode().hashCode() );
         result = 37 * result + ( getVoucherType() == null ? 0 : this.getVoucherType().hashCode() );
         result = 37 * result + ( getVoucherDate() == null ? 0 : this.getVoucherDate().hashCode() );
         result = 37 * result + ( getShipOrderCode() == null ? 0 : this.getShipOrderCode().hashCode() );
         result = 37 * result + ( getInQty() == null ? 0 : this.getInQty().hashCode() );
         result = 37 * result + ( getInTotal() == null ? 0 : this.getInTotal().hashCode() );
         result = 37 * result + ( getOutQty() == null ? 0 : this.getOutQty().hashCode() );
         result = 37 * result + ( getOutTotal() == null ? 0 : this.getOutTotal().hashCode() );
         result = 37 * result + ( getQty() == null ? 0 : this.getQty().hashCode() );
         result = 37 * result + ( getTotal() == null ? 0 : this.getTotal().hashCode() );
         result = 37 * result + ( getRemark() == null ? 0 : this.getRemark().hashCode() );
         result = 37 * result + ( getProductCode() == null ? 0 : this.getProductCode().hashCode() );
         result = 37 * result + ( getProductColor() == null ? 0 : this.getProductColor().hashCode() );
         return result;
   }   





}