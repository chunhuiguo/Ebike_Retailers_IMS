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
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;

import com.luyuan.action.ExcelListAct;
import com.luyuan.info.biz.WarehouseBiz;
import com.luyuan.info.entity.ProductCustomer;
import com.luyuan.info.entity.SubAccount;
import com.luyuan.info.entity.Warehouse;
import com.luyuan.stock.biz.InventoryBiz;
import com.luyuan.stock.entity.Inventory;
import com.luyuan.sys.action.UnitListAct;
import com.luyuan.sys.biz.UnitBiz;
import com.luyuan.sys.entity.Unit;

/**
 * 
 * 库存Invnetory的表现层action
 *
 * @author gch
 */

public class InventoryAct extends ExcelListAct {
	
	//当前库存(仓管)入口
	public String detail() {
		if(inventoryList != null)
			inventoryList.clear();
		shopList.clear();
		if(warehouseList != null)
			warehouseList.clear();
		select = null;
		shopId = 0;
		warehouseId = 0;
		viewPrice = false;
		viewZero = false;
		dealerUser = (Boolean)this.getSession().get("isDealer");
		
		if(type.equals("整车"))
			page.setListAct("inventory.html");
		else
			page.setListAct("pinventory.html");
		
		if(dealerUser) {			
			if(this.getSession().get("unitType").equals("经销商")) {
				shopList = unitBiz.findDealerAndUnit((Integer) this.getSession().get("unitId"));
				warehouseList = warehouseBiz.findWarehouse((Integer) this.getSession().get("unitId"), true);
				inventoryList = inventoryBiz.findByShopId(this, (Integer) this.getSession().get("unitId"), viewZero, true);	
			}
			else {
				shopList.add(unitBiz.findById((Integer) this.getSession().get("unitId")));
				warehouseList = warehouseBiz.findWarehouse((Integer) this.getSession().get("unitId"), false);
				inventoryList = inventoryBiz.findByShopId(this, (Integer) this.getSession().get("unitId"), viewZero, false);
			}
			
			this.total();
		}
		
		return "success";
	}
	
	//当前库存(财务)入口
	public String detailCheck() {
		if(inventoryList != null)
			inventoryList.clear();
		shopList.clear();
		if(warehouseList != null)
			warehouseList.clear();
		select = null;
		shopId = 0;
		warehouseId = 0;
		viewPrice = true;
		viewZero = false;
		dealerUser = (Boolean)this.getSession().get("isDealer");
		
		if(type.equals("整车"))
			page.setListAct("inventoryCheck.html");
		else
			page.setListAct("pinventoryCheck.html");
		
		if(dealerUser) {	
			
			if(this.getSession().get("unitType").equals("经销商")) {
				shopList = unitBiz.findDealerAndUnit((Integer) this.getSession().get("unitId"));
				warehouseList = warehouseBiz.findWarehouse((Integer) this.getSession().get("unitId"), true);
				inventoryList = inventoryBiz.findByShopId(this, (Integer) this.getSession().get("unitId"), viewZero, true);	
			}
			else {
				shopList.add(unitBiz.findById((Integer) this.getSession().get("unitId")));
				warehouseList = warehouseBiz.findWarehouse((Integer) this.getSession().get("unitId"), false);
				inventoryList = inventoryBiz.findByShopId(this, (Integer) this.getSession().get("unitId"), viewZero, false);
			}		
			
			this.total();
		}
		
		return "success";
	}
	
	public String execute() {	
		if(type.equals("整车"))
			page.setListAct("inventorySubmit.html");
		else
			page.setListAct("pinventorySubmit.html");
		
		//翻页
		if(submit == null) {
			select = null;
			if(inventoryList != null)
				inventoryList.clear();
			inventoryList = this.inventoryList();				
			return "success";
		}
		if(submit.equals("查看明细")) {
			submit = null;
			
			if(select != null)				
				return "detail";
			return "success";
		}	
		if(submit.equals("筛选")) {
			submit = null;
			
			select = null;
			if(inventoryList != null)
				inventoryList.clear();
			
			inventoryList = this.inventoryList();
			this.total();
							
			return "success";
		}
		return "success";
	}
	
	//选择经销商
	public String unitList()		{	return "unitList";			}
	
	//选择经销商返回
	public String unitListBack() {
		UnitListAct unitListAct = (UnitListAct) ctx.getBean("unitListAct");
		Unit unit = unitListAct.getUnit();
		if(unit != null) {
			dealerId = unit.getId();
			dealerShortName = unit.getShortName();	
			
			submit = null;			
			select = null;
			if(inventoryList != null)
				inventoryList.clear();
			
			inventoryList = this.inventoryList();
			this.total();
		}		
		return "success";	
	}
	
	//查看明细返回
	public String detailBack() {
		return "detailBack";
	}
	
	//“导出Excel”
	public String exportExcel() throws IOException {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet(type + "库存");
		//列宽
		this.setColumnWidth(sheet, this.getColumnWidthList());
		HSSFRow row;
		
		//第1行
		row = sheet.createRow(0);
		if(viewPrice) {
			row.createCell(0).setCellValue("当前" + type + "库存查询结果(财务)");		
			//合并单元格
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 8));
		}
		else {
			row.createCell(0).setCellValue("当前" + type + "库存查询结果(仓管)");		
			//合并单元格
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 6));
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
		row.createCell(6).setCellValue("库存数量");
		if(viewPrice) {
			row.createCell(7).setCellValue("平均单价");
			row.createCell(8).setCellValue("库存金额");
		}		
		//格式
		this.setRowStyle(row, this.getTitleStytle(workbook));
		
		//设置“内容”单元格的格式
		int columns;
		if(viewPrice)
			columns = 9;
		else
			columns = 7;
		for(int i = 0; i < columns; i++)
			sheet.setDefaultColumnStyle(i, this.getContentStytle(workbook));
		
		if(inventoryAllList != null && inventoryAllList.size() != 0) {			
			//第3~n行
			Inventory inventory;
			for(int i = 0; i < inventoryAllList.size(); i++) {
				inventory = inventoryAllList.get(i);			
				row = sheet.createRow(i + 2);
				row.createCell(0).setCellValue(i + 1);
				//库存信息
				row.createCell(1).setCellValue(inventory.getShopShortName());
				row.createCell(2).setCellValue(inventory.getWarehouseName());
				row.createCell(3).setCellValue(inventory.getProductCode());
				row.createCell(4).setCellValue(inventory.getProductName());
				row.createCell(5).setCellValue(inventory.getProductColor());
				row.createCell(6).setCellValue(inventory.getQty());
				if(viewPrice) {
					row.createCell(7).setCellValue(inventory.getAveragePrice());
					row.createCell(8).setCellValue(inventory.getTotal());
				}			
			}
		}
		
		HttpServletResponse response = ServletActionContext.getResponse();
		//解决中文乱码问题
		String fileName;
		if(viewPrice)
			fileName = URLEncoder.encode("当前" + type + "库存查询结果(财务)", "UTF-8");
		else
			fileName = URLEncoder.encode("当前" + type + "库存查询结果(仓管)", "UTF-8");
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
		columnWidthList.add(150);
		columnWidthList.add(110);
		columnWidthList.add(70);
		if(viewPrice) {
			columnWidthList.add(75);
			columnWidthList.add(90);
		}		
		return columnWidthList;
	}
	
	//当前页记录集
	private List<Inventory> inventoryList() {
		if(! dealerUser)
			return inventoryBiz.findByShopId(this, dealerId, viewZero, true);
		else {			
			if(shopId == 0 && warehouseId == 0) {
				if(this.getSession().get("unitType").equals("经销商"))
					return inventoryBiz.findByShopId(this, (Integer) this.getSession().get("unitId"), viewZero, true);	
				else
					return inventoryBiz.findByShopId(this, (Integer) this.getSession().get("unitId"), viewZero, false);			
			}
			if(shopId == 0)
				return inventoryBiz.findInventory(this, warehouseId, viewZero);			
			if(warehouseId == 0)
				return inventoryBiz.findByShopId(this, shopId, viewZero, false);
			else
				return inventoryBiz.findInventory(this, shopId, warehouseId, viewZero);
		}
	}
	
	//所有记录集
	private List<Inventory> inventoryAllList() {
		List<Inventory> inventoryAllListTemp = new ArrayList<Inventory>();
		
		if(page.getTail() == 0)
			return inventoryAllListTemp;
		if(page.getTail() == 1)
			return inventoryList;
		else {			
			//记录当前分页信息，方便还原
			String curStr = page.getCurStr();
			int cur = page.getCur();
			//从第一页开始得到所有记录
			for(int i = 0; i < page.getTail(); i++) {
				page.setCurStr(Integer.toString(i + 1));
				inventoryAllListTemp.addAll(this.inventoryList());
			}
			//还原分页信息
			page.setCur(cur);
			page.setCurStr(curStr);
			
			return inventoryAllListTemp;
		}
	}
	
	//计算所有记录的库存数量和库存金额的总和
	private void total() {
		totalQty = 0;
		total = 0;
		inventoryAllList = this.inventoryAllList();
		
		for(Inventory inventory : inventoryAllList) {
			totalQty = totalQty + inventory.getQty();
			total = total + inventory.getTotal();
		}
	}
	
	//class inside data
	private List<Inventory> inventoryAllList;
	
	//spring ApplicationContext
	private ApplicationContext ctx = this.getAppContext();
	
	private int dealerId = 0;
	
	//page
	private List<Inventory> inventoryList;	
	public List<Inventory> getInventoryList() {
		return inventoryList;
	}

	private Integer select;	
	public Integer getSelect() {
		return select;
	}
	public void setSelect(Integer select) {
		this.select = select;
	}

	private int totalQty = 0;
	public int getTotalQty() {
		return totalQty;
	}
	
	private double total = 0;
	public double getTotal() {
		return total;
	}
	
	private String type;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	private boolean viewPrice;
	public boolean isViewPrice() {
		return viewPrice;
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
	
	private boolean viewZero;	
	public boolean isViewZero() {
		return viewZero;
	}
	public void setViewZero(boolean viewZero) {
		this.viewZero = viewZero;
	}

	private List<Unit> shopList = new ArrayList<Unit>();
	public List<Unit> getShopList() {
		return shopList;
	}
	
	private List<Warehouse> warehouseList;
	public List<Warehouse> getWarehouseList() {
		return warehouseList;
	}
	
	private boolean dealerUser;
	public boolean isDealerUser() {
		return dealerUser;
	}
	
	private String dealerShortName;
	public String getDealerShortName() {
		return dealerShortName;
	}

	//submit
	private String submit;	
	public void setSubmit(String submit) {
		this.submit = submit;
	}

	//biz 
	private InventoryBiz inventoryBiz;
	public void setInventoryBiz(InventoryBiz inventoryBiz) {
		this.inventoryBiz = inventoryBiz;
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
