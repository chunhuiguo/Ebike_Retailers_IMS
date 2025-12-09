package com.luyuan.report.model;
/**
 * InventoryBook entity. @author MyEclipse Persistence Tools
 */

public class DayInventory  implements java.io.Serializable {



		// Fields
		
		private String checkDate;		
		private String warehouseName;
		private String productName;
		private String productCode;
		private String productColor;
		private Integer initialQty;
		private Integer inQty;
		private Integer outQty;
		private Integer finalQty;

		// Constructors

		/** default constructor */
		public DayInventory() {
		}

		/** full constructor */
		public DayInventory( String checkDate, String warehouseName,  String productName, String productCode,
				String productColor,Integer initialQty, Integer inQty,Integer outQty, Integer finalQty) {
			
			this.checkDate = checkDate;	
			this.warehouseName = warehouseName;
			this.productName = productName;
			this.productCode = productCode;
			this.productColor = productColor;
			this.initialQty = initialQty;
			this.inQty = inQty;	
			this.outQty = outQty;
			this.finalQty = finalQty;
			
		}
		// Property accessors

		public String getCheckDate() {
			return checkDate;
		}

		public void setCheckDate(String checkDate) {
			this.checkDate = checkDate;
		}

		public String getWarehouseName() {
			return warehouseName;
		}

		public void setWarehouseName(String warehouseName) {
			this.warehouseName = warehouseName;
		}

		public String getProductName() {
			return productName;
		}

		public void setProductName(String productName) {
			this.productName = productName;
		}

		public String getProductCode() {
			return productCode;
		}

		public void setProductCode(String productCode) {
			this.productCode = productCode;
		}

		public String getProductColor() {
			return productColor;
		}

		public void setProductColor(String productColor) {
			this.productColor = productColor;
		}

		

		public Integer getInQty() {
			return inQty;
		}

		public void setInQty(Integer inQty) {
			this.inQty = inQty;
		}

		public Integer getOutQty() {
			return outQty;
		}

		public void setOutQty(Integer outQty) {
			this.outQty = outQty;
		}

		public Integer getInitialQty() {
			return initialQty;
		}

		public void setInitialQty(Integer initialQty) {
			this.initialQty = initialQty;
		}

		public Integer getFinalQty() {
			return finalQty;
		}

		public void setFinalQty(Integer finalQty) {
			this.finalQty = finalQty;
		}

		

		

	}
