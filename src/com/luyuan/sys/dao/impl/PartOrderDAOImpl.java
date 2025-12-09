/**
 * @(#)PartOrderDAOImpl.java  1.0 10/05/12
 *
 * Copyright 2010 luyuan-ebike, Inc. All rights reserved.
 * 
 */
package com.luyuan.sys.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.luyuan.sys.dao.PartOrderDAO;
import com.luyuan.sys.entity.PartOrder;

/**
 * 
 * 最终会被注入的PartOrderDAO，实现DAO接口逻辑
 *
 * @see com.luyuan.sys.entity.PartOrder
 * @author gch
 */

public class PartOrderDAOImpl extends HibernateDaoSupport implements PartOrderDAO {	
	
	public List<PartOrder> findByShipOrderId(Object shipOrderId) {		
		return this.findByProperty("shipOrderId", shipOrderId);
	}
	
	//************************************************************************
	private List<PartOrder> findByProperty(String propertyName, Object value) {
	      log.debug("finding PartOrder instance with property: " + propertyName
	            + ", value: " + value);
	      try {
	         String queryString = "from PartOrder as model where model." 
	         						+ propertyName + "= ?";
			 return getHibernateTemplate().find(queryString, value);
	      } catch (RuntimeException re) {
	         log.error("find by property name failed", re);
	         throw re;
	      }
		}
	
	//日志
	private static final Log log = LogFactory.getLog(PartOrderDAOImpl.class);
}