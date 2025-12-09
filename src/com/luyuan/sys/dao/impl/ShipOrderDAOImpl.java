/**
 * @(#)ShipOrderDAOImpl.java  1.0 10/05/12
 *
 * Copyright 2010 luyuan-ebike, Inc. All rights reserved.
 * 
 */
package com.luyuan.sys.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.luyuan.action.IFieldValidation;
import com.luyuan.sys.dao.ShipOrderDAO;
import com.luyuan.sys.entity.ShipOrder;
import com.luyuan.util.Page;
import com.luyuan.util.PaginateHib;
import com.luyuan.util.SqlCallback;

/**
 * 
 * 最终会被注入的ShipOrderDAO，实现DAO接口逻辑
 *
 * @see com.luyuan.sys.entity.ShipOrder
 * @author gch
 */

public class ShipOrderDAOImpl extends PaginateHib implements ShipOrderDAO {
	
	public List<ShipOrder> findShipOrder(final IFieldValidation act, final String dealerShortName, final String warehouseCode, final String orderStartDate, final String orderEndDate) {
		log.debug("finding ShipOrder instance with DealerCode: " + dealerShortName + " and other conditions");
		SqlCallback sqlCallback = new SqlCallback(){
			public void setPageSql(Page page) {	
				page.getAttachSql().append(" and model.dealerCode='").append(dealerShortName).append("'");
				if(! warehouseCode.equals("所有仓库"))
					page.getAttachSql().append(" and model.warehouseCode='").append(warehouseCode).append("'");
				if(!(orderStartDate.equals("") || orderEndDate.equals("")))
					page.getAttachSql().append(" and model.orderDate between '").append(orderStartDate).append("' and '").append(orderEndDate).append(" 23:59:59'");
				page.getAttachSql().append(" and model.status='核准' order by model.orderDate desc");
			}
		};	
		return this.findList(act, sqlCallback);
	}	
	
	//日志
	private static final Log log = LogFactory.getLog(ShipOrderDAOImpl.class);
}