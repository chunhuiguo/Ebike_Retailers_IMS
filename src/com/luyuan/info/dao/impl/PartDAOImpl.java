/**
 * @(#)PartDAOImpl.java  1.0 10/07/11
 *
 * Copyright 2010 luyuan-ebike, Inc. All rights reserved.
 * 
 */
package com.luyuan.info.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.luyuan.action.IFieldValidation;
import com.luyuan.info.dao.PartDAO;
import com.luyuan.info.entity.Part;
import com.luyuan.info.entity.Product;
import com.luyuan.util.Page;
import com.luyuan.util.PaginateHib;
import com.luyuan.util.SqlCallback;

/**
 * 
 * 最终会被注入的PartDAO，实现DAO接口逻辑
 *
 * @see com.luyuan.info.entity.Part
 * @author gch
 */


public class PartDAOImpl extends PaginateHib implements PartDAO{
	//gch
	public List<Part> findPart(final IFieldValidation act, final int dealerId) {		
		log.debug("finding Part instance with dealerId: " + dealerId
	            + " or 1 and input condition");
		SqlCallback sqlCallback = new SqlCallback(){
			public void setPageSql(Page page) {				
				page.getAttachSql().append(" and model.dealerId=1").append(" or model.dealerId=").append(dealerId);				
			}
		};
		return this.findList(act, sqlCallback);	
	}
	
	public List<Part> findPart(int dealerId, int unitId) {		
		log.debug("finding Part instance with dealerId: " + dealerId
	            + " or " + unitId);
	      try {
	    	  List<Part> list = getHibernateTemplate().find(
						"from Part as model where model.dealerId=" 
						+ dealerId + " or model.dealerId=" + unitId);
	    	  return list;
	      } catch (RuntimeException re) {
	         log.error("find by property name failed", re);
	         throw re;
	      }		
	}
	
	public Part findByCode(String code) {
		List<Part> list = findByProperty("code", code);
		if(list != null && list.size() != 0)
			return list.get(0);
		return null;
	}
	
	public void save(Part transientInstance) {
        log.debug("saving Part instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
	
	//chq
	public Part findById(java.lang.Long id) {
		log.debug("getting Part instance with id: " + id);
		try {
			Part instance = (Part) getHibernateTemplate().get(
					"com.luyuan.info.entity.Part", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}	

//	public List findByDealerId(Integer dealerId) {
//		return this.findByProperty("dealerId", dealerId);
//	}

	public List findAll() {
		log.debug("finding all Part instances");
		try {
			String queryString = "from Part";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	//*********************************************************
	private List findByProperty(String propertyName, Object value) {
		log.debug("finding Part instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Part as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}	

	public List<Part> findByDealerId(Integer dealerId) {
		// TODO Auto-generated method stub
		String queryString = "from Part as model where model.dealerId="+ dealerId;
		return getHibernateTemplate().find(queryString);
	}

	//日志
	private static final Log log = LogFactory.getLog(PartDAOImpl.class);

}