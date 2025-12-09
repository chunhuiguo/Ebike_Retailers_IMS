package com.luyuan.money.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.luyuan.money.dao.DealingDetailDAO;
import com.luyuan.money.entity.DealingDetail;





/**
 * 
 */

public class DealingDetailDAOImpl extends HibernateDaoSupport implements DealingDetailDAO{
	
	public void save(DealingDetail transientInstance){
		log.debug("saving DealingDetail instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	public void saveorupdate(DealingDetail dealingDetail){
		try {
			getHibernateTemplate().saveOrUpdate(dealingDetail);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	public void delete(DealingDetail dealingDetail){
		getHibernateTemplate().delete(dealingDetail);
	}
	
	public List<DealingDetail> findByParentDealingId(long parentDealingId){
		return findByProperty("parentDealingId", parentDealingId);
	}
	//*************************************************************************************
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding DealingDetail instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from DealingDetail as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	//log
	private static final Log log = LogFactory.getLog(DealingDetailDAOImpl.class);
}