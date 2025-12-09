/**
 * @(#)AccountDAOImpl.java  1.0 10/04/13
 *
 * Copyright 2010 luyuan-ebike, Inc. All rights reserved.
 * 
 */
package com.luyuan.info.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.luyuan.info.dao.InsuranceCardDAO;
import com.luyuan.info.entity.InsuranceCard;



public class InsuranceCardDAOImpl extends HibernateDaoSupport implements InsuranceCardDAO {
	
	public void save(InsuranceCard transientInstance) {
		log.debug("saving InsuranceCard instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	public List<InsuranceCard> findByProductbarcode(String productBarcode) {
		return this.findByProperty("productBarCode", productBarcode);
	}
	
	//private method
	private List<InsuranceCard> findByProperty(String propertyName, Object value) {
		log.debug("finding InsuranceCard instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from InsuranceCard as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	//日志
	private static final Log log = LogFactory.getLog(InsuranceCardDAOImpl.class);	
}