/**
 * @(#)InventoryBookBizImpl.java  1.0 10/05/19
 *
 * Copyright 2010 luyuan-ebike, Inc. All rights reserved.
 * 
 */
package com.luyuan.stock.biz.impl;

import java.util.Iterator;
import java.util.List;

import com.luyuan.action.IFieldValidation;
import com.luyuan.report.model.Report;
import com.luyuan.stock.biz.InventoryBookBiz;
import com.luyuan.stock.dao.InventoryBookDAO;
import com.luyuan.stock.dao.InventoryBookViewDAO;
import com.luyuan.stock.entity.InventoryBook;
import com.luyuan.stock.entity.InventoryBookView;

/**
 * 
 * 库存台账InventoryBook的业务逻辑层biz
 * 
 * @author gch
 */

public class InventoryBookBizImpl implements InventoryBookBiz {

	public List<InventoryBookView> findInventoryBook(IFieldValidation act, int shopId, String startDate, String endDate, boolean isDealer) {
		return inventoryBookViewDAO.findInventoryBook(act, shopId, startDate, endDate, isDealer);
	}

	public List<InventoryBookView> findInventoryBookWithShop(IFieldValidation act, int shopId, String startDate, String endDate) {
		return inventoryBookViewDAO.findInventoryBookWithShop(act, shopId, startDate, endDate);
	}

	public List<InventoryBookView> findInventoryBookWithWarehouse(IFieldValidation act, int warehouseId, String startDate, String endDate) {
		return inventoryBookViewDAO.findInventoryBookWithWarehouse(act,	warehouseId, startDate, endDate);
	}

	public List<InventoryBookView> findInventoryBook(IFieldValidation act, int shopId, int warehouseId, String startDate, String endDate) {
		return inventoryBookViewDAO.findInventoryBook(act, shopId, warehouseId,	startDate, endDate);
	}

	// public List<InventoryBook> findAllInventoryBook(IFieldValidation act, int
	// shopId, String startDate, String endDate, boolean isDealer) {
	// return inventoryBookDAO.findAllInventoryBook(act, shopId, startDate,
	// endDate, isDealer);
	// }
	//	
	// public List<InventoryBook> findAllInventoryBookWithShop(IFieldValidation
	// act, int shopId, String startDate, String endDate) {
	// return inventoryBookDAO.findAllInventoryBookWithShop(act, shopId,
	// startDate, endDate);
	// }
	//	
	// public List<InventoryBook>
	// findAllInventoryBookWithWarehouse(IFieldValidation act, int warehouseId,
	// String startDate, String endDate) {
	// return inventoryBookDAO.findAllInventoryBookWithWarehouse(act,
	// warehouseId, startDate, endDate);
	// }
	//	
	// public List<InventoryBook> findAllInventoryBook(IFieldValidation act, int
	// shopId, int warehouseId, String startDate, String endDate) {
	// return inventoryBookDAO.findAllInventoryBook(act, shopId, warehouseId,
	// startDate, endDate);
	// }

	public List<Report> findInventoryBook(Integer shopId, String date) {
		// TODO Auto-generated method stub
		return inventoryBookDAO.findInventoryBook(shopId, date);
	}

	// public List<MonthInventory> findInventory(Integer warehouseId, String
	// date){
	//		
	// return inventoryBookDAO.findInventory(warehouseId, date);
	// }

	public InventoryBook[] findInventory(String checkDate, int warehouseId) {

		List bookList = inventoryBookDAO.findInventory(checkDate, warehouseId);
		InventoryBook[] books = new InventoryBook[bookList.size()];
		Object[] op = new Object[10];
		Iterator it = bookList.iterator();
		for (int i = 0; i < books.length; i++) {
			op = (Object[]) it.next();
			books[i] = getDayInventory(op);
			// System.out.println(i+":" + ops[i].getOpName());
		}

		return books;
	}

	public InventoryBook[] findInventory(String beginDate, String endDate,
			int warehouseId) {

		List bookList = inventoryBookDAO.findInventory(beginDate, endDate,
				warehouseId);
		InventoryBook[] books = new InventoryBook[bookList.size()];
		Object[] op = new Object[10];
		Iterator it = bookList.iterator();
		for (int i = 0; i < books.length; i++) {
			op = (Object[]) it.next();
			books[i] = getDayInventory(op);
			// System.out.println(i+":" + ops[i].getOpName());
		}

		return books;
	}

	private InventoryBook getDayInventory(Object[] op) {

		InventoryBook books = new InventoryBook();
		books.setDealerShortName(op[1].toString());
		books.setShopShortName(op[3].toString());
		books.setProductId(Long.parseLong(op[4].toString()));
		books.setProductName(op[6].toString());
		books.setInQty(Integer.parseInt(op[8].toString()));
		books.setOutQty(Integer.parseInt(op[9].toString()));
		return books;
	}

	// DAO
	private InventoryBookDAO inventoryBookDAO;
	public void setInventoryBookDAO(InventoryBookDAO inventoryBookDAO) {
		this.inventoryBookDAO = inventoryBookDAO;
	}
	
	private InventoryBookViewDAO inventoryBookViewDAO;
	public void setInventoryBookViewDAO(InventoryBookViewDAO inventoryBookViewDAO) {
		this.inventoryBookViewDAO = inventoryBookViewDAO;
	}
}
