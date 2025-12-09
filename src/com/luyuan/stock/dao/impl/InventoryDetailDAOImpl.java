/**
 * @(#)InventoryDetailDAOImpl.java  1.0 10/04/07
 *
 * Copyright 2010 luyuan-ebike, Inc. All rights reserved.
 * 
 */
package com.luyuan.stock.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;

import com.luyuan.action.IFieldValidation;
import com.luyuan.stock.dao.InventoryDetailDAO;
import com.luyuan.stock.entity.InventoryDetail;
import com.luyuan.stock.entity.PInventoryDetail;
import com.luyuan.util.Page;
import com.luyuan.util.PaginateHib;
import com.luyuan.util.SqlCallback;

/**
 * 
 * 最终会被注入的InventoryDetailDAO，实现DAO接口逻辑
 *
 * @see com.luyuan.stock.entity.InventoryDetail
 * @author gch
 */

public class InventoryDetailDAOImpl extends PaginateHib implements InventoryDetailDAO {
	
	public void save(InventoryDetail transientInstance) {
		log.debug("saving " + className + " instance");
		try {
			if(className.equals("PInventoryDetail"))
				getHibernateTemplate().save(this.inventoryDetailCopy(transientInstance));
			else
				getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	public List<InventoryDetail> findInventoryDetail(int shopId, int warehouseId, long productId) {
		log.debug("finding " + className + " instance with shopId: " + shopId + 
				" and warehouseId: " + warehouseId + " and productId: " + productId);
		try {
			List<InventoryDetail> list = getHibernateTemplate().find(
					"from " + className + " as model where model.shopId="	
					+ shopId + " and model.warehouseId=" + warehouseId + 
					" and model.productId=" + productId);
			return list;				
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}	
	}
	
	public boolean findInventoryDetail(int warehouseId, long productId, double price) {
		log.debug("finding " + className + " instance with warehouseId: " + warehouseId + " and productId: " + productId + " and price: " + price);
		try {
			List<InventoryDetail> list = getHibernateTemplate().find(
					"from " + className + " as model where model.warehouseId=" + warehouseId + 
					" and model.productId=" + productId + " and model.price=" + price);
			if(list != null && list.size() != 0)
				return true;
			return false;				
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}	
	}	
	
	public void update(InventoryDetail transientInstance) {
		log.debug("updating " + className + " instance");
		try {
			if(className.equals("PInventoryDetail"))				
				getHibernateTemplate().merge(this.inventoryDetailCopy(transientInstance));
			else
				getHibernateTemplate().merge(transientInstance);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}
	
	public void delete(InventoryDetail persistentInstance) {
        log.debug("deleting " + className + " instance");
        try {
        	if(className.equals("PInventoryDetail")) {
        		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
				session.clear();
				getHibernateTemplate().delete(this.inventoryDetailCopy(persistentInstance));
        	}
			else
				getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
	
	public List<InventoryDetail> findInventoryDetail(final IFieldValidation act, final int warehouseId, final long productId) {
		SqlCallback sqlCallback = new SqlCallback(){
			public void setPageSql(Page page) {
				page.getAttachSql().append(" and model.warehouseId=").append(warehouseId).append(" and model.productId=").append(productId);							
			}
		};
		return this.findList(act, sqlCallback);
	}
	
	//class inside method
	private PInventoryDetail inventoryDetailCopy(InventoryDetail inventoryDetail) {
		PInventoryDetail pinventoryDetail = new PInventoryDetail();
		
		pinventoryDetail.setId(inventoryDetail.getId());
		pinventoryDetail.setDealerId(inventoryDetail.getDealerId());
		pinventoryDetail.setShopId(inventoryDetail.getShopId());
		pinventoryDetail.setDealerShortName(inventoryDetail.getDealerShortName());
		pinventoryDetail.setShopShortName(inventoryDetail.getShopShortName());
		pinventoryDetail.setWarehouseId(inventoryDetail.getWarehouseId());
		pinventoryDetail.setWarehouseName(inventoryDetail.getWarehouseName());
		pinventoryDetail.setProductId(inventoryDetail.getProductId());
		pinventoryDetail.setQty(inventoryDetail.getQty());
		pinventoryDetail.setPrice(inventoryDetail.getPrice());
		pinventoryDetail.setTotal(inventoryDetail.getTotal());
		
		return pinventoryDetail;
	}
	
	//日志
	private static final Log log = LogFactory.getLog(InventoryDetailDAOImpl.class);
}