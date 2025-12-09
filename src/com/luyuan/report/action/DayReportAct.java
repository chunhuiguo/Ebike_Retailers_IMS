package com.luyuan.report.action;

import java.util.ArrayList;
import java.util.List;
import com.luyuan.action.BaseAct;
import com.luyuan.deal.entity.Voucher;
import com.luyuan.info.biz.ProductBiz;
import com.luyuan.info.biz.WarehouseBiz;
import com.luyuan.info.entity.Product;
import com.luyuan.info.entity.Warehouse;
import com.luyuan.report.biz.DayReportBiz;
import com.luyuan.report.entity.PSIProductMonthlyReport;
import com.luyuan.report.model.DayInventory;
import com.luyuan.stock.biz.InventoryBookBiz;
import com.luyuan.stock.entity.InventoryBook;

public class DayReportAct extends BaseAct {
	
//  整车库存日报菜单入口
	public String dayReport() {
		if(voucher != null)
			voucher = null;
		if(warehouse != null)
			warehouse = null;	
		return "success";
	}
//	日期和仓库选择页面
	public String dayChoose() {

		if (submit == null)
			return "success";

		if (submit.equals("选择仓库")) {
			// backString = "list";
			submit = null;
			warehouseList = warehouseBiz.findByDealerId((Integer) this
					.getSession().get("parentId"));
			return "select";
		}

		if (submit.equals("确定")) {
			submit = null;
			if(!dayReportBiz.validation(this,warehouse.getId(),voucher.getCheckDate()))
				return "success";
			this.getList(voucher.getCheckDate(), warehouse.getId());		
			return "certain";
		}

		if (submit.equals("取消")) {
			submit = null;
			return "exit";
		}

		return "success";
	}
	public List<DayInventory> getList(String checkDate,int warehouseId){
		
		int initialQty = 0;
		dayInventoryList = new ArrayList<DayInventory>();
		productList = productBiz.findProduct((Integer) this.getSession()
				.get("parentId"));
		
		InventoryBook[] books = inventoryBookBiz.findInventory(checkDate, warehouseId);
		
		if (dayInventory != null)
			dayInventory = null;
		
		for (int i = 0; i < productList.size(); i++) {
			// System.out.println(productList.get(i).getId());
			DayInventory dayInventory = new DayInventory();
			dayInventory.setCheckDate(checkDate);
			dayInventory.setWarehouseName(warehouseBiz.findById(warehouseId).getName());
			dayInventory.setProductName(productList.get(i).getPrefixName()
					+ "" + productList.get(i).getSuffixName());
			dayInventory.setProductColor(productList.get(i).getColorName());
			initialQty = this.getInitialQty(productList.get(i).getId(),
					warehouseId, checkDate);
			// System.out.println(initialQty);
			dayInventory.setInitialQty(initialQty);
			// System.out.println("aaaaa");
			if (books.length == 0) {
				// System.out.println("0000");
				dayInventory.setInQty(0);
				dayInventory.setOutQty(0);
				dayInventory.setFinalQty(initialQty);
			} else {
				for (int k = 0; k < books.length; k++) {
					if (productList.get(i).getId().equals(
							books[k].getProductId())) {
						// System.out.println("dddd");
						dayInventory.setInQty(books[k].getInQty());
						dayInventory.setOutQty(books[k].getOutQty());
						dayInventory.setFinalQty(initialQty
								+ books[k].getInQty()
								- books[k].getOutQty());
						break;
					} else {
						// System.out.println("bbbbb");
						dayInventory.setInQty(0);
						dayInventory.setOutQty(0);
						dayInventory.setFinalQty(initialQty);
					}
				}
			}
			dayInventoryList.add(dayInventory);
			}
			return dayInventoryList;
	}

	public int getInitialQty(Long productId, int warehouseId, String checkDate) {
		int initialQty = 0;
		int monthBegin = 0;
		int timeIn = 0;
		int timeOut = 0;
		String beginDate = "";
		String endDate = "";
		String year = "";
		String month = "";
		year = checkDate.substring(0, 4);
		month = String.format("%1$02d", Integer.parseInt(checkDate.substring(5,
				7)) - 1);
		
		pSIProductMonthlyReport = dayReportBiz.findMonthBegin(year, month,
				warehouseId, productId);
		if (pSIProductMonthlyReport == null)
			monthBegin = 0;
		else
			monthBegin = pSIProductMonthlyReport.getFinalQty();
		
		if (checkDate.substring(8, 10).equals("01"))
			initialQty = monthBegin;
		else {
			beginDate = checkDate.substring(0, 8) + "01";
			endDate = checkDate.substring(0, 8)
					+ String.format("%1$02d", Integer.parseInt(checkDate
							.substring(8, 10)) - 1);
			
			PSIProductMonthlyReport[] reports = dayReportBiz
					.findPSIProductMonthlyReport((Integer) this.getSession()
							.get("unitId"), beginDate, endDate);
			
			if (reports.length == 0)
				initialQty = monthBegin;
			else {
				for (int k = 0; k < reports.length; k++) {
					if (warehouseId == reports[k].getWarehouseId()
							&& productId.equals(reports[k].getProductId())) {
						System.out.println("xiangdeng");
						System.out.println(reports[k].getInQty());
						System.out.println(reports[k].getOutQty());
						timeIn = reports[k].getInQty();
						timeOut = reports[k].getOutQty();
						initialQty = monthBegin + timeIn - timeOut;
						break;
					} else
						initialQty = monthBegin;
				}
			}
		}
		// System.out.println("hhhhh");
		return initialQty;
	}


	public String execute() throws Exception {
		return "success";
	}
	
	public String pdf() throws Exception {
		return "success";
	}

	public String warehouseForDay() {

		if (submit == null)
			return "success";

		if (submit.equals("确定")) {
			submit = null;
			warehouse = warehouseBiz.findById(selectId);
			selectId = null;
			return "certain";
		}
		if (submit.equals("退出")) {
			submit = null;
			return "exit";
		}
		return "success";
	}

	private List<Warehouse> warehouseList;

	public List<Warehouse> getWarehouseList() {
		return warehouseList;
	}

	public void setWarehouseList(List<Warehouse> warehouseList) {
		this.warehouseList = warehouseList;
	}

	private List<Product> productList;

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	private List<DayInventory> dayInventoryList;

	public List<DayInventory> getDayInventoryList() {
		return dayInventoryList;
	}

	public void setDayInventoryList(List<DayInventory> dayInventoryList) {
		this.dayInventoryList = dayInventoryList;
	}

	private InventoryBookBiz inventoryBookBiz;

	public void setInventoryBookBiz(InventoryBookBiz inventoryBookBiz) {
		this.inventoryBookBiz = inventoryBookBiz;
	}

	private WarehouseBiz warehouseBiz;

	public void setWarehouseBiz(WarehouseBiz warehouseBiz) {
		this.warehouseBiz = warehouseBiz;
	}

	private ProductBiz productBiz;

	public void setProductBiz(ProductBiz productBiz) {
		this.productBiz = productBiz;
	}

	public DayReportBiz dayReportBiz;

	public void setDayReportBiz(DayReportBiz dayReportBiz) {
		this.dayReportBiz = dayReportBiz;
	}

	private Voucher voucher;

	public Voucher getVoucher() {
		return voucher;
	}

	public void setVoucher(Voucher voucher) {
		this.voucher = voucher;
	}

	private Warehouse warehouse;

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	private DayInventory dayInventory;

	public DayInventory getDayInventory() {
		return dayInventory;
	}

	public void setDayInventory(DayInventory dayInventory) {
		this.dayInventory = dayInventory;
	}

	private PSIProductMonthlyReport pSIProductMonthlyReport;

	public PSIProductMonthlyReport getpSIProductMonthlyReport() {
		return pSIProductMonthlyReport;
	}

	public void setpSIProductMonthlyReport(
			PSIProductMonthlyReport pSIProductMonthlyReport) {
		this.pSIProductMonthlyReport = pSIProductMonthlyReport;
	}

	// submit
	private String submit;

	public String getSubmit() {
		return submit;
	}

	public void setSubmit(String submit) {
		this.submit = submit;
	}

	private Integer selectId;

	public Integer getSelectId() {
		return selectId;
	}

	public void setSelectId(Integer selectId) {
		this.selectId = selectId;
	}

}
