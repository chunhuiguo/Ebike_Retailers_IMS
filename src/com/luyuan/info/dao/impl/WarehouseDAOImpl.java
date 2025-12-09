/**
 * @(#)WarehouseDAOImpl.java  1.0 10/04/13
 *
 * Copyright 2010 luyuan-ebike, Inc. All rights reserved.
 * 
 */
package com.luyuan.info.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.luyuan.action.IFieldValidation;
import com.luyuan.info.dao.WarehouseDAO;
import com.luyuan.info.entity.Warehouse;
import com.luyuan.util.Page;
import com.luyuan.util.PaginateHib;
import com.luyuan.util.SqlCallback;

/**
 * 
 * 最终会被注入的WarehousrDAO，实现DAO接口逻辑
 *
 * @see com.luyuan.info.entity.Warehousr
 * @author gch
 */

public class WarehouseDAOImpl extends PaginateHib implements WarehouseDAO {
	//gch
	public List<Warehouse> findWarehouse(final IFieldValidation act, final int shopId, final boolean isDealer, final boolean viewDisabled) {
		log.debug("finding Warehouse instance with shopId: " + shopId + " and disable: True");
		SqlCallback sqlCallback = new SqlCallback(){
			public void setPageSql(Page page) {	
				if(isDealer)
					page.getAttachSql().append(" and model.dealerId=").append(shopId);	
				else
					page.getAttachSql().append(" and model.shopId=").append(shopId);
				if(! viewDisabled)
					page.getAttachSql().append(" and model.disable=true");
			}
		};
		return this.findList(act, sqlCallback);				
	}
	
	public List<Warehouse> findByDealerId(int dealerId) {
		return this.findByProperty("dealerId", dealerId);
	}
	
	public List<Warehouse> findByShopId(int shopId) {
		return this.findByProperty("shopId", shopId);
	}
	
	//zsh
	public void save(Warehouse transientInstance) {
        log.debug("saving Warehouse instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
    public void update(Warehouse transientInstance) {
        log.debug("updating Warehouse instance");
        try {
            getHibernateTemplate().update(transientInstance);
            log.debug("update successful");
        } catch (RuntimeException re) {
            log.error("update failed", re);
            throw re;
        }
    }
    
    public Warehouse findById( java.lang.Integer id) {
        log.debug("getting Warehouse instance with id: " + id);
        try {
            Warehouse instance = (Warehouse) getHibernateTemplate()
                    .get("com.luyuan.info.entity.Warehouse", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Warehouse instance with property: " + propertyName
              + ", value: " + value);
        try {
           String queryString = "from Warehouse as model where model." 
           						+ propertyName + "= ?";
  		 return getHibernateTemplate().find(queryString, value);
        } catch (RuntimeException re) {
           log.error("find by property name failed", re);
           throw re;
        }
  	}
    
    public List findAll() {
		log.debug("finding all Warehouse instances");
		try {
			String queryString = "from Warehouse";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}   
	
	//日志
	private static final Log log = LogFactory.getLog(WarehouseDAOImpl.class);	
}