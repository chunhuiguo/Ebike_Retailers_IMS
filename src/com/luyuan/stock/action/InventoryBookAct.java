/**
 * @(#)InventoryAct.java  1.0 10/05/19
 *
 * Copyright 2010 luyuan-ebike, Inc. All rights reserved.
 * 
 */
package com.luyuan.stock.action;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.struts2.ServletActionContext;

import com.luyuan.action.ExcelListAct;
import com.luyuan.deal.biz.VoucherBiz;
import com.luyuan.deal.entity.Voucher;
import com.luyuan.deal.entity.VoucherDetail;
import com.luyuan.info.biz.WarehouseBiz;
import com.luyuan.info.entity.Warehouse;
import com.luyuan.stock.biz.InventoryBookBiz;
import com.luyuan.stock.entity.InventoryBook;
import com.luyuan.stock.entity.InventoryBookView;
import com.luyuan.sys.biz.UnitBiz;
import com.luyuan.sys.entity.Unit;
import com.luyuan.util.TypeMap;

/**
 * 
 * 库存Invnetory的表现层action
 *
 * @author gch
 */

public class InventoryBookAct extends ExcelListAct {
	
	//库存台账查询(仓管)入口
	public String detail() {
		if(inventoryBookViewList != null)
			inventoryBookViewList.clear();
		shopList.clear();
		if(warehouseList != null)
			warehouseList.clear();
		viewPrice = false;
		select = null;
		shopId = 0;
		warehouseId = 0;		
		
		endDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		startDate = endDate.substring(0, 8) + "01";
		
		if(type.equals("整车"))
			page.setListAct("inventoryBookDetail.html");
		else
			page.setListAct("pinventoryBookDetail.html");
		if(this.getSession().get("unitType").equals("经销商")) {
			shopList = unitBiz.findDealerAndUnit((Integer) this.getSession().get("unitId"));
			warehouseList = warehouseBiz.findWarehouse((Integer) this.getSession().get("unitId"), true);
			inventoryBookViewList = inventoryBookBiz.findInventoryBook(this, (Integer) this.getSession().get("unitId"), startDate, endDate, true);
		}
		else {
			shopList.add(unitBiz.findById((Integer) this.getSession().get("unitId")));
			warehouseList = warehouseBiz.findWarehouse((Integer) this.getSession().get("unitId"), false);
			inventoryBookViewList = inventoryBookBiz.findInventoryBook(this, (Integer) this.getSession().get("unitId"), startDate, endDate, false);
		}		
		
		return "success";
	}
	
	//库存台账查询(财务)入口
	public String detailCheck() {
		if(inventoryBookViewList != null)
			inventoryBookViewList.clear();
		shopList.clear();
		if(warehouseList != null)
			warehouseList.clear();
		viewPrice = true;
		select = null;
		shopId = 0;
		warehouseId = 0;	
		
		endDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		startDate = endDate.substring(0, 8) + "01";
		
		if(type.equals("整车"))
			page.setListAct("inventoryBookDetailCheck.html");
		else
			page.setListAct("pinventoryBookDetailCheck.html");
		
		if(this.getSession().get("unitType").equals("经销商")) {
			shopList = unitBiz.findDealerAndUnit((Integer) this.getSession().get("unitId"));
			warehouseList = warehouseBiz.findWarehouse((Integer) this.getSession().get("unitId"), true);
			inventoryBookViewList = inventoryBookBiz.findInventoryBook(this, (Integer) this.getSession().get("unitId"), startDate, endDate, true);
		}
		else {
			shopList.add(unitBiz.findById((Integer) this.getSession().get("unitId")));
			warehouseList = warehouseBiz.findWarehouse((Integer) this.getSession().get("unitId"), false);
			inventoryBookViewList = inventoryBookBiz.findInventoryBook(this, (Integer) this.getSession().get("unitId"), startDate, endDate, false);
		}
		
		return "success";
	}
	
	public String execute() {
		if(type.equals("整车"))
			page.setListAct("inventoryBook.html");
		else
			page.setListAct("pinventoryBook.html");		
		
		if(submit.equals("筛选")) {
			this.inventoryBookViewList();
			return "success";
		}
		if(submit.equals("查看单据")) {
			if(select != null) {
				if(type.equals("整车")) {					
					voucher = voucherBiz.findById(inventoryBookViewList.get(select).getVoucherId());
					voucherDetailList = voucherBiz.findByVoucherId(voucher.getId());
				}
				else {
					if(inventoryBookViewList.get(select).getVoucherType().startsWith("整车")) {
						voucher = voucherBiz.findById(inventoryBookViewList.get(select).getVoucherId());
						voucherDetailList = voucherBiz.findByVoucherId(voucher.getId());
					}
					else {
						voucher = pvoucherBiz.findById(inventoryBookViewList.get(select).getVoucherId());
						voucherDetailList = pvoucherBiz.findByVoucherId(voucher.getId());
					}
				}
				select = null;
				return TypeMap.typeMap.get(voucher.getType());
			}
			return "success";
		}
		if(submit.equals("设置查询条件")) {
			shopId = 0;
			warehouseId = 0;
			endDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			startDate = endDate.substring(0, 8) + "01";
			if(inventoryBookViewList != null)
				inventoryBookViewList.clear();
			return "inventoryBookQuery";
		}
		return "success";
	}
	
	//库存台账查询返回入口
	public String queryBack() {
		select = null;
				
		if(type.equals("整车"))
			page.setListAct("inventoryBookQueryBack.html");
		else
			page.setListAct("pinventoryBookQueryBack.html");
		
		this.inventoryBookViewList();
		
		return "success";
	}
	
	//查询入口
	public String query() {			
		if(submit.equals("确定"))			
			return "OK";
		if(submit.equals("重置")) {			
			shopId = 0;
			warehouseId = 0;
			endDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			startDate = endDate.substring(0, 8) + "01";
			
			return "success";
		}
		if(submit.equals("退出")) {	
			if(viewPrice)
				return "exitCheck";
			return "exit";
		}
		return "success";
	}
	
	//“导出Excel”
	public String exportExcel() throws IOException {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet(type + "库存台账");
		//列宽
		this.setColumnWidth(sheet, this.getColumnWidthList());
		HSSFRow row;
		
		//第1行
		row = sheet.createRow(0);
		if(viewPrice) {
			row.createCell(0).setCellValue(type + "库存台账查询结果(财务)");		
			//合并单元格
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 14));
		}
		else {
			row.createCell(0).setCellValue(type + "库存台账查询结果(仓管)");		
			//合并单元格
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 11));
		}			
		//格式
		this.setRowStyle(row, this.getTitleStytle(workbook));
		
		//第2行
		row = sheet.createRow(1);
		row.createCell(0).setCellValue("序号");
		row.createCell(1).setCellValue("门店");
		row.createCell(2).setCellValue("仓库");
		row.createCell(3).setCellValue("商品编码");
		row.createCell(4).setCellValue("商品名称");
		row.createCell(5).setCellValue("商品颜色");
		row.createCell(6).setCellValue("单据编码");
		row.createCell(7).setCellValue("单据类型");
		row.createCell(8).setCellValue("单据日期");
		row.createCell(9).setCellValue("入库数量");
		if(viewPrice) {
			row.createCell(10).setCellValue("入库金额");
			row.createCell(11).setCellValue("出库数量");
			row.createCell(12).setCellValue("出库金额");
			row.createCell(13).setCellValue("库存数量");
			row.createCell(14).setCellValue("库存金额");
		}
		else {
			row.createCell(10).setCellValue("出库数量");
			row.createCell(11).setCellValue("库存数量");
		}
		//格式
		this.setRowStyle(row, this.getTitleStytle(workbook));
		
		//设置“内容”单元格的格式
		int columns;
		if(viewPrice)
			columns = 15;
		else
			columns = 12;
		for(int i = 0; i < columns; i++)
			sheet.setDefaultColumnStyle(i, this.getContentStytle(workbook));
		
		//记录当前分页信息，方便还原
		String curStr = page.getCurStr();
		int cur = page.getCur();
		//从第1页开始得到所有记录
		List<InventoryBookView> inventoryBookViewAllList = new ArrayList<InventoryBookView>();
		for(int i = 0; i < page.getTail(); i++) {
			page.setCurStr(Integer.toString(i + 1));
			this.inventoryBookViewList();
			inventoryBookViewAllList.addAll(inventoryBookViewList);
		}
		//还原分页信息
		page.setCur(cur);
		page.setCurStr(curStr);
		
		if(inventoryBookViewAllList != null && inventoryBookViewAllList.size() != 0) {			
			//第3~n行
			InventoryBookView inventoryBookView;
			for(int i = 0; i < inventoryBookViewAllList.size(); i++) {
				inventoryBookView = inventoryBookViewAllList.get(i);			
				row = sheet.createRow(i + 2);
				row.createCell(0).setCellValue(i + 1);
				//库存信息
				row.createCell(1).setCellValue(inventoryBookView.getShopShortName());
				row.createCell(2).setCellValue(inventoryBookView.getWarehouseName());
				row.createCell(3).setCellValue(inventoryBookView.getProductCode());
				row.createCell(4).setCellValue(inventoryBookView.getProductName());
				row.createCell(5).setCellValue(inventoryBookView.getProductColor());
				row.createCell(6).setCellValue(inventoryBookView.getVoucherCode());
				row.createCell(7).setCellValue(inventoryBookView.getVoucherType());
				row.createCell(8).setCellValue(inventoryBookView.getVoucherDate());
				row.createCell(9).setCellValue(inventoryBookView.getInQty());
				if(viewPrice) {
					row.createCell(10).setCellValue(inventoryBookView.getInTotal());
					row.createCell(11).setCellValue(inventoryBookView.getOutQty());
					row.createCell(12).setCellValue(inventoryBookView.getOutTotal());
					row.createCell(13).setCellValue(inventoryBookView.getQty());
					row.createCell(14).setCellValue(inventoryBookView.getTotal());
				}
				else {
					row.createCell(10).setCellValue(inventoryBookView.getOutQty());
					row.createCell(11).setCellValue(inventoryBookView.getQty());
				}
			}
		}
		
		HttpServletResponse response = ServletActionContext.getResponse();
		//解决中文乱码问题
		String fileName;
		if(viewPrice)
			fileName = URLEncoder.encode(type + "库存台账查询结果(财务)", "UTF-8");
		else
			fileName = URLEncoder.encode(type + "库存台账查询结果(仓管)", "UTF-8");
		response.setHeader("Content-Type","application/vnd.ms-excel");
        response.setHeader("Content-Disposition","attachment;filename=" + fileName + ".xls");
		OutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		outputStream.flush();
		outputStream.close();
		//避免getOutputStream()方法已经得调用的异常
		return null;
	}
	
	//得到各列的宽度
	private List<Integer> getColumnWidthList() {
		List<Integer> columnWidthList = new ArrayList<Integer>();
		//宽度：手动调整Excel得到列宽的像素值
		columnWidthList.add(37);
		columnWidthList.add(110);
		columnWidthList.add(150);
		columnWidthList.add(90);
		columnWidthList.add(200);
		columnWidthList.add(150);
		columnWidthList.add(160);
		columnWidthList.add(110);
		columnWidthList.add(90);
		columnWidthList.add(75);
		if(viewPrice) {
			columnWidthList.add(90);
			columnWidthList.add(75);
			columnWidthList.add(90);
			columnWidthList.add(75);
			columnWidthList.add(90);
		}	
		else {
			columnWidthList.add(75);
			columnWidthList.add(75);
		}
		return columnWidthList;
	}	
	
	private void inventoryBookViewList() {
		if(inventoryBookViewList != null)
			inventoryBookViewList.clear();
		
		if(shopId == 0 && warehouseId == 0) {
			if(this.getSession().get("unitType").equals("经销商"))					
				inventoryBookViewList = inventoryBookBiz.findInventoryBook(this, (Integer) this.getSession().get("unitId"), startDate, endDate, true);
			else 
				inventoryBookViewList = inventoryBookBiz.findInventoryBook(this, (Integer) this.getSession().get("unitId"), startDate, endDate, false);
			return;
		}
		if(shopId == 0) {
			inventoryBookViewList = inventoryBookBiz.findInventoryBookWithWarehouse(this, warehouseId, startDate, endDate);
			return;
		}
		if(warehouseId == 0) {
			inventoryBookViewList = inventoryBookBiz.findInventoryBookWithShop(this, shopId, startDate, endDate);
			return;
		}
		else
			inventoryBookViewList = inventoryBookBiz.findInventoryBook(this, shopId, warehouseId, startDate, endDate);
	}
	
	//class inside data
	private boolean viewPrice;	
	public boolean isViewPrice() {
		return viewPrice;
	}

	//page	
	private String startDate;	
	public String getStartDate() {
		return startDate;
	}	
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	private String endDate;
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	private List<InventoryBookView> inventoryBookViewList;
	public List<InventoryBookView> getInventoryBookViewList() {
		return inventoryBookViewList;
	}

	private Integer select;
	public void setSelect(Integer select) {
		this.select = select;
	}
	
	private Voucher voucher;
	public Voucher getVoucher() {
		return voucher;
	}
	
	private List<VoucherDetail> voucherDetailList;
	public List<VoucherDetail> getVoucherDetailList() {
		return voucherDetailList;
	}	
	
	private String type;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	private int shopId;	
	public int getShopId() {
		return shopId;
	}
	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	private int warehouseId;
	public int getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(int warehouseId) {
		this.warehouseId = warehouseId;
	}
	
	private List<Unit> shopList = new ArrayList<Unit>();
	public List<Unit> getShopList() {
		return shopList;
	}
	
	private List<Warehouse> warehouseList;
	public List<Warehouse> getWarehouseList() {
		return warehouseList;
	}

	//submit
	private String submit;	
	public void setSubmit(String submit) {
		this.submit = submit;
	}

	//biz	
	private InventoryBookBiz inventoryBookBiz;
	public void setInventoryBookBiz(InventoryBookBiz inventoryBookBiz) {
		this.inventoryBookBiz = inventoryBookBiz;
	}
	private VoucherBiz voucherBiz;
	public void setVoucherBiz(VoucherBiz voucherBiz) {
		this.voucherBiz = voucherBiz;
	}
	
	private VoucherBiz pvoucherBiz;
	public void setPvoucherBiz(VoucherBiz pvoucherBiz) {
		this.pvoucherBiz = pvoucherBiz;
	}
	
	private UnitBiz unitBiz;
	public void setUnitBiz(UnitBiz unitBiz) {
		this.unitBiz = unitBiz;
	}
	
	private WarehouseBiz warehouseBiz;
	public void setWarehouseBiz(WarehouseBiz warehouseBiz) {
		this.warehouseBiz = warehouseBiz;
	}
}
