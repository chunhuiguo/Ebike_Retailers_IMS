package com.luyuan.report.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.luyuan.report.dao.DealingLastDAO;
import com.luyuan.report.entity.DealingLast;
import com.luyuan.report.entity.PDealingLast;
import com.luyuan.report.entity.PInventoryLast;
import com.luyuan.util.PaginateHib;

/**
 * A data access object (DAO) providing persistence and search support for
 * DealingLast entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.luyuan.report.entity.DealingLast
 * @author MyEclipse Persistence Tools
 */

public class DealingLastDAOImpl extends PaginateHib implements DealingLastDAO {
	
	

	public void save(DealingLast transientInstance) {
		log.debug("saving " + className + " instance");
		try {
			if(className.equals("PDealingLast"))
				getHibernateTemplate().save(this.dealingLastCopy(transientInstance));
			else
				getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	public void update(DealingLast transientInstance) {		
		log.debug("updating " + className + " instance");
		try {
			if(className.equals("PDealingLast"))
				getHibernateTemplate().merge(this.dealingLastCopy(transientInstance));
			else
				getHibernateTemplate().merge(transientInstance);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}
	
	public List<DealingLast> findByDealerId(Integer dealerId) {
		String queryString = "from "+className+" as model where model.dealerId="
				+ dealerId;
		return getHibernateTemplate().find(queryString);
	}
	
	//class inside method
	private PDealingLast dealingLastCopy(DealingLast dealingLast) {
		PDealingLast pDealingLast = new PDealingLast();
		
		pDealingLast.setBalance(dealingLast.getBalance());
		pDealingLast.setDealerId(dealingLast.getDealerId());
		pDealingLast.setDealerShortName(dealingLast.getDealerShortName());
		pDealingLast.setDealingUnitId(dealingLast.getDealingUnitId());
		pDealingLast.setDealingUnitShortName(dealingLast.getDealingUnitShortName());
		pDealingLast.setId(dealingLast.getId());
		pDealingLast.setSubAccountId(dealingLast.getSubAccountId());
		pDealingLast.setSubAccountType(dealingLast.getSubAccountType());
		pDealingLast.setType(dealingLast.getType());
				
		return pDealingLast;
	}

	//log
	private static final Log log = LogFactory.getLog(DealingLastDAOImpl.class);
}