/**
 * @(#)InventoryBizImpl.java  1.0 10/05/19
 *
 * Copyright 2010 luyuan-ebike, Inc. All rights reserved.
 * 
 */
package com.luyuan.stock.biz.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.luyuan.action.IFieldValidation;
import com.luyuan.report.dao.InventoryLastDAO;
import com.luyuan.report.dao.PSIProductMonthlyReportDAO;
import com.luyuan.report.entity.InventoryLast;
import com.luyuan.report.entity.PSIProductMonthlyReport;
import com.luyuan.stock.biz.InventoryBiz;
import com.luyuan.stock.dao.InventoryBookDAO;
import com.luyuan.stock.dao.InventoryDAO;
import com.luyuan.stock.dao.InventoryDetailDAO;
import com.luyuan.stock.entity.Inventory;
import com.luyuan.stock.entity.InventoryDetail;
import com.luyuan.util.GetLastMonth;
import com.luyuan.util.Page;

/**
 * 
 * 库存Inventory的业务逻辑层biz
 *
 * @author gch
 */

public class InventoryBizImpl implements InventoryBiz {

	
	public List<Inventory> findByShopId(IFieldValidation act, int shopId, boolean viewZero, boolean isDealer) {		
		return inventoryDAO.findByShopId(act, shopId, viewZero, isDealer);
	}
	
	public List<Inventory> findInventory(IFieldValidation act, int warehouseId, boolean viewZero) {
		return inventoryDAO.findInventory(act, warehouseId, viewZero);
	}
	
	public List<Inventory> findInventory(IFieldValidation act, int shopId, int warehouseId, boolean viewZero) {
		return inventoryDAO.findInventory(act, shopId, warehouseId, viewZero);
	}
	
	public void saveInventoryIntial(List<Inventory> inventoryList, List<InventoryDetail> inventoryDetailList, long productId) {
		//获得上月的年和月
		List<String> dateList = GetLastMonth.lastMonth(new SimpleDateFormat("yyyy").format(new Date()), new SimpleDateFormat("MM").format(new Date()));	
		
		//库存明细
		for(int i = 0; i < inventoryDetailList.size(); i++)
			inventoryDetailDAO.save(inventoryDetailList.get(i));
		
		for(int i = 0; i < inventoryList.size(); i++) {
			if(inventoryDAO.findProduct(inventoryList.get(i).getWarehouseId(), productId) == null) {
				inventoryDAO.save(inventoryList.get(i));
				
				//上月库存结余
				InventoryLast inventoryLast=new InventoryLast();				
				inventoryLast.setDealerId(inventoryList.get(i).getDealerId());
				inventoryLast.setDealerShortName(inventoryList.get(i).getDealerShortName());
				inventoryLast.setShopId(inventoryList.get(i).getShopId());
				inventoryLast.setShopShortName(inventoryList.get(i).getShopShortName());
				inventoryLast.setWarehouseId(inventoryList.get(i).getWarehouseId());
				inventoryLast.setWarehouseName(inventoryList.get(i).getWarehouseName());
				inventoryLast.setProductCode(inventoryList.get(i).getProductCode());
				inventoryLast.setProductColor(inventoryList.get(i).getProductColor());
				inventoryLast.setProductId(inventoryList.get(i).getProductId());
				inventoryLast.setProductName(inventoryList.get(i).getProductName());
				inventoryLast.setQty(inventoryList.get(i).getQty());
				
				inventoryLastDAO.save(inventoryLast);
				
				//库存月报
				PSIProductMonthlyReport psiProductMonthlyReport = new PSIProductMonthlyReport();				
				psiProductMonthlyReport.setReportYear(dateList.get(0));
				psiProductMonthlyReport.setReportMonth(dateList.get(1));
				psiProductMonthlyReport.setDealerId(inventoryList.get(i).getDealerId());
				psiProductMonthlyReport.setDealerShortName(inventoryList.get(i).getDealerShortName());
				psiProductMonthlyReport.setShopId(inventoryList.get(i).getShopId());
				psiProductMonthlyReport.setShopShortName(inventoryList.get(i).getShopShortName());
				psiProductMonthlyReport.setWarehouseId(inventoryList.get(i).getWarehouseId());
				psiProductMonthlyReport.setWarehouseName(inventoryList.get(i).getWarehouseName());
				psiProductMonthlyReport.setProductId(inventoryList.get(i).getProductId());
				psiProductMonthlyReport.setProductName(inventoryList.get(i).getProductName());
				psiProductMonthlyReport.setProductCode(inventoryList.get(i).getProductCode());
				psiProductMonthlyReport.setProductColor(inventoryList.get(i).getProductColor());
				psiProductMonthlyReport.setInitialQty(inventoryList.get(i).getQty());
				psiProductMonthlyReport.setInQty(0);
				psiProductMonthlyReport.setOutQty(0);
				psiProductMonthlyReport.setFinalQty(inventoryList.get(i).getQty());
				
				pSIProductMonthlyReportDAO.save(psiProductMonthlyReport);
			}
			else {
				//库存
				Inventory inventory = inventoryDAO.findProduct(inventoryList.get(i).getWarehouseId(), productId);
				inventory.setQty(inventory.getQty() + inventoryList.get(i).getQty());
				inventory.setTotal(inventory.getTotal() + inventoryList.get(i).getTotal());
				inventory.setAveragePrice(inventory.getTotal() / inventory.getQty());
				
				inventoryDAO.update(inventory);
				
				//上月库存结余
				InventoryLast inventoryLast = inventoryLastDAO.findInventoryLast(inventoryList.get(i).getWarehouseId(), productId);
				inventoryLast.setQty(inventoryLast.getQty() + inventoryList.get(i).getQty());
				
				inventoryLastDAO.update(inventoryLast);
				
				//库存月报
				PSIProductMonthlyReport psiProductMonthReport = pSIProductMonthlyReportDAO.findPSIProductMonthlyReport(dateList.get(0), dateList.get(1), inventoryList.get(i).getWarehouseId(), productId);
				psiProductMonthReport.setInitialQty(psiProductMonthReport.getInitialQty() + inventoryList.get(i).getQty());
				psiProductMonthReport.setFinalQty(psiProductMonthReport.getFinalQty() + inventoryList.get(i).getQty());
				
				pSIProductMonthlyReportDAO.update(psiProductMonthReport);
			}
		}		
	}
	
	public List<InventoryDetail> findInventoryDetail(IFieldValidation act, int warehouseId, long productId) {
		return inventoryDetailDAO.findInventoryDetail(act, warehouseId, productId);
	}
	
	public boolean validation(IFieldValidation act, List<Integer> warehouseIdList, List<String> warehouseNameList, long productId, List<Double> priceList) {
		boolean isError = false;
		String errorMessage = "";
		
		for(int i = 0; i < warehouseIdList.size(); i++) {
			//发生库存台账，不能再初始化
			if(inventoryBookDAO.findInventoryBook(warehouseIdList.get(i), productId) != null) {
				isError = true;
				errorMessage = errorMessage + "仓库【" + warehouseNameList.get(i) + "】已经对该商品执行过库存初始化操作了，不能再次初始化<br>";
				continue;
			}
			
			//同一仓库同一商品可以初始化两次不同的价格，相同的价格只能初始化一次
			if(inventoryDetailDAO.findInventoryDetail(warehouseIdList.get(i), productId, priceList.get(i))) {
				isError = true;
				errorMessage = errorMessage + "仓库【" + warehouseNameList.get(i) + "】已经对该商品执行过库存初始化操作了，不能再次初始化<br>";
			}
		}		
		
		if(isError) {
			act.addFieldError("inventory", errorMessage);
			return false;
		}
		return true;
	}
	
	//DAO
	private InventoryDAO inventoryDAO;
	public void setInventoryDAO(InventoryDAO inventoryDAO) {
		this.inventoryDAO = inventoryDAO;
	}
	
	private InventoryDetailDAO inventoryDetailDAO;
	public void setInventoryDetailDAO(InventoryDetailDAO inventoryDetailDAO) {
		this.inventoryDetailDAO = inventoryDetailDAO;
	}
	
	private InventoryBookDAO inventoryBookDAO;	
	public void setInventoryBookDAO(InventoryBookDAO inventoryBookDAO) {
		this.inventoryBookDAO = inventoryBookDAO;
	}

	private InventoryLastDAO inventoryLastDAO;
	public void setInventoryLastDAO(InventoryLastDAO inventoryLastDAO) {
		this.inventoryLastDAO = inventoryLastDAO;
	}
	
	private PSIProductMonthlyReportDAO pSIProductMonthlyReportDAO;
	public void setpSIProductMonthlyReportDAO(
			PSIProductMonthlyReportDAO pSIProductMonthlyReportDAO) {
		this.pSIProductMonthlyReportDAO = pSIProductMonthlyReportDAO;
	}
	
	public List<Inventory> findByWarehouse(int Warehouseid) {
		// TODO Auto-generated method stub
		return inventoryDAO.findByWarehouse( Warehouseid);
	}
}
