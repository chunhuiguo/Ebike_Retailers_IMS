/**
 * @(#)ProductDAOImpl.java  1.0 10/04/08
 *
 * Copyright 2010 luyuan-ebike, Inc. All rights reserved.
 * 
 */
package com.luyuan.info.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.luyuan.action.IFieldValidation;
import com.luyuan.info.dao.ProductDAO;
import com.luyuan.info.entity.Product;
import com.luyuan.util.Page;
import com.luyuan.util.PaginateHib;
import com.luyuan.util.SqlCallback;

/**
 * 
 * 最终会被注入的ProductDAO，实现DAO接口逻辑
 *
 * @see com.luyuan.info.entity.Product
 * @author gch
 */

public class ProductDAOImpl extends PaginateHib implements ProductDAO {
	
	//gch
	public List<Product> findProduct(int dealerId) {		
		log.debug("finding Product instance with dealerId: " + dealerId
	            + " or 1");
	      try {
	    	  List<Product> list = getHibernateTemplate().find(
						"from Product as model where model.dealerId=" 
						+ dealerId + " or model.dealerId=1");
	    	  return list;
	      } catch (RuntimeException re) {
	         log.error("find by property name failed", re);
	         throw re;
	      }		
	}
	
	public Product findProduct(String code) {
		List<Product> list = getHibernateTemplate().find("from Product as model where model.code='" + code + "'");
		if(list != null && list.size() != 0)
			return list.get(0);
		return null;
	}
	
	public List<Product> findProduct(final IFieldValidation act, final int dealerId) {	
		log.debug("finding Product instance with dealerId: " + dealerId
					+ " or 1" + " and input condition");
		SqlCallback sqlCallback = new SqlCallback(){
			public void setPageSql(Page page) {				
				page.getAttachSql().append(" and model.dealerId=1").append(" or model.dealerId=").append(dealerId);				
			}
		};
		return this.findList(act, sqlCallback);				
	}
	
	public Product findByCode(Object code) {
		List<Product> list = findByProperty("code", code);
		if(list != null && list.size() != 0)
			return list.get(0);
		return null;
	}
	
	public void save(Product transientInstance) {
        log.debug("saving Product instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
	
	//chq
	public List findAll() {
		log.debug("finding all Product instances");
		try {
			String queryString = "from Product";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public Product findById(java.lang.Long id) {
		log.debug("getting Product instance with id: " + id);
		try {
			Product instance = (Product) getHibernateTemplate().get(
					"com.luyuan.info.entity.Product", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List findByDealerId(Integer dealerId) {
		return this.findByProperty("dealerId", dealerId);
	}
	
	//************************************************************************
	private List<Product> findByProperty(String propertyName, Object value) {
		log.debug("finding Product instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Product as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	//日志
    private static final Log log = LogFactory.getLog(ProductDAOImpl.class);
}