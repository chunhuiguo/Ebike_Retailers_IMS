/**
 * @(#)PartShipOrderDAOImpl.java  1.0 10/05/12
 *
 * Copyright 2010 luyuan-ebike, Inc. All rights reserved.
 * 
 */
package com.luyuan.sys.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.luyuan.action.IFieldValidation;
import com.luyuan.sys.dao.PartShipOrderDAO;
import com.luyuan.sys.entity.PartShipOrder;
import com.luyuan.util.Page;
import com.luyuan.util.PaginateHib;
import com.luyuan.util.SqlCallback;

/**
 * 
 * 最终会被注入的PartShipOrderDAO，实现DAO接口逻辑
 *
 * @see com.luyuan.sys.entity.PartShipOrder
 * @author gch
 */

public class PartShipOrderDAOImpl extends PaginateHib implements PartShipOrderDAO {
	
	public List<PartShipOrder> findShipOrder(final IFieldValidation act, final String dealerShortName) {
		log.debug("finding PartShipOrder instance with DealerCode: " + dealerShortName);
		SqlCallback sqlCallback = new SqlCallback(){
			public void setPageSql(Page page) {	
				page.getAttachSql().append(" and model.dealerCode='").append(dealerShortName).append("' and model.status='核准' order by model.orderDate desc");
			}
		};	
		return this.findList(act, sqlCallback);
	}	
	
	//日志
	private static final Log log = LogFactory.getLog(PartShipOrderDAOImpl.class);
}