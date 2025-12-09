/**
 * @(#)InventoryBookDAOImpl.java  1.0 10/04/11
 *
 * Copyright 2010 luyuan-ebike, Inc. All rights reserved.
 * 
 */
package com.luyuan.stock.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.luyuan.action.IFieldValidation;
import com.luyuan.stock.dao.InventoryBookViewDAO;
import com.luyuan.stock.entity.InventoryBookView;
import com.luyuan.util.Page;
import com.luyuan.util.PaginateHib;
import com.luyuan.util.SqlCallback;

/**
 * 
 * 最终会被注入的InventoryBookViewDAO，实现DAO接口逻辑
 *
 * @see com.luyuan.stock.entity.InventoryBookView
 * @author gch
 */

public class InventoryBookViewDAOImpl extends PaginateHib implements InventoryBookViewDAO {
	
	//gch		
	public List<InventoryBookView> findInventoryBook(final IFieldValidation act, final int shopId, final String startDate, final String endDate, final boolean isDealer) {
		log.debug("finding " + className + " instance with shopId: " + shopId + " and voucherDate between " + startDate + " and " + endDate);
		SqlCallback sqlCallback = new SqlCallback(){
			public void setPageSql(Page page) {
				if(isDealer)
					page.getAttachSql().append(" and model.dealerId=").append(shopId).append(" and model.voucherDate between '" + startDate + "' and '" + endDate + "'").append(" order by model.voucherId desc");
				else
					page.getAttachSql().append(" and model.shopId=").append(shopId).append(" and model.voucherDate between '" + startDate + "' and '" + endDate + "'").append(" order by model.voucherId desc");				
			}
		};
		return this.findList(act, sqlCallback);		
	}
	
	public List<InventoryBookView> findInventoryBookWithShop(final IFieldValidation act, final int shopId, final String startDate, final String endDate) {
		log.debug("finding " + className + " instance with shopId: " + shopId + " and voucherDate between " + startDate + " and " + endDate);
		SqlCallback sqlCallback = new SqlCallback(){
			public void setPageSql(Page page) {
				page.getAttachSql().append(" and model.shopId=").append(shopId).append(" and model.voucherDate between '" + startDate + "' and '" + endDate + "'").append(" order by model.voucherId desc");
			}
		};
		return this.findList(act, sqlCallback);	
	}
	
	public List<InventoryBookView> findInventoryBookWithWarehouse(final IFieldValidation act, final int warehouseId, final String startDate, final String endDate) {
		log.debug("finding " + className + " instance with warehouseId: " + warehouseId + " and voucherDate between " + startDate + " and " + endDate);
		SqlCallback sqlCallback = new SqlCallback(){
			public void setPageSql(Page page) {
				page.getAttachSql().append(" and model.warehouseId=").append(warehouseId).append(" and model.voucherDate between '" + startDate + "' and '" + endDate + "'").append(" order by model.voucherId desc");
			}
		};
		return this.findList(act, sqlCallback);	
	}
	
	public List<InventoryBookView> findInventoryBook(final IFieldValidation act, final int shopId, final int warehouseId, final String startDate, final String endDate) {
		log.debug("finding " + className + " instance with shopId: " + shopId + " and warehouseId: " + warehouseId + " and voucherDate between " + startDate + " and " + endDate);
		SqlCallback sqlCallback = new SqlCallback(){
			public void setPageSql(Page page) {
				page.getAttachSql().append(" and model.shopId=").append(shopId).append(" and model.warehouseId=").append(warehouseId).append(" and model.voucherDate between '" + startDate + "' and '" + endDate + "'").append(" order by model.voucherId desc");
			}
		};
		return this.findList(act, sqlCallback);	
	}	
	
	//日志
	private static final Log log = LogFactory.getLog(InventoryBookViewDAOImpl.class);	

}