/**
 * @(#)OrderDetailDAOImpl.java  1.0 10/05/12
 *
 * Copyright 2010 luyuan-ebike, Inc. All rights reserved.
 * 
 */
package com.luyuan.sys.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.luyuan.sys.dao.OrderDetailDAO;
import com.luyuan.sys.entity.OrderDetail;

/**
 * 
 * 最终会被注入的OrderDetailDAO，实现DAO接口逻辑
 *
 * @see com.luyuan.sys.entity.OrderDetail
 * @author gch
 */

public class OrderDetailDAOImpl extends HibernateDaoSupport implements OrderDetailDAO {
	public List<OrderDetail> findByOrderId(Object orderId) {
		return this.findByProperty("orderId", orderId);
	}
	
	//************************************************************************
	 private List<OrderDetail> findByProperty(String propertyName, Object value) {
	      log.debug("finding OrderDetail instance with property: " + propertyName
	            + ", value: " + value);
	      try {
	         String queryString = "from OrderDetail as model where model." 
	         						+ propertyName + "= ?";
			 return getHibernateTemplate().find(queryString, value);
	      } catch (RuntimeException re) {
	         log.error("find by property name failed", re);
	         throw re;
	      }
		}
	
	//日志
	private static final Log log = LogFactory.getLog(OrderDetailDAOImpl.class);
}