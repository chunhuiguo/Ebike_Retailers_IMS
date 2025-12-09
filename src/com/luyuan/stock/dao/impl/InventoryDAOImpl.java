/**
 * @(#)InventoryDAOImpl.java  1.0 10/04/07
 *
 * Copyright 2010 luyuan-ebike, Inc. All rights reserved.
 * 
 */
package com.luyuan.stock.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.luyuan.action.IFieldValidation;
import com.luyuan.stock.dao.InventoryDAO;
import com.luyuan.stock.entity.Inventory;
import com.luyuan.stock.entity.PInventory;
import com.luyuan.util.Page;
import com.luyuan.util.PaginateHib;
import com.luyuan.util.SqlCallback;

/**
 * 
 * 最终会被注入的InventoryDAO，实现DAO接口逻辑
 *
 * @see com.luyuan.stock.entity.Inventory
 * @author gch
 */

public class InventoryDAOImpl extends PaginateHib implements InventoryDAO {
	
	public Inventory findProduct(int shopId, int warehouseId, long productId) {
		log.debug("finding " + className + " instance with shopId: " + shopId
				 + " and warehouseId: " + warehouseId + " and productId: " + productId);
		try {
			List<Inventory> list = getHibernateTemplate().find(
					"from " + className + " as model where model.shopId=" 
					+ shopId + " and model.warehouseId=" + warehouseId + 
					" and model.productId=" + productId);
			if(list != null && list.size() != 0)
				return list.get(0);
			return null;
				
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}				
	}
	
	public Inventory findProduct(int warehouseId, long productId) {
		log.debug("finding " + className + " instance with warehouseId: " + warehouseId
				 + " and productId: " + productId);
		try {
			List<Inventory> list = getHibernateTemplate().find(
					"from " + className + " as model where model.warehouseId=" 
					+ warehouseId + " and model.productId=" + productId);
			if(list != null && list.size() != 0)
				return list.get(0);
			return null;
				
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}				
	}	
	
	public void update(Inventory transientInstance) {
		log.debug("updating " + className + " instance");
		try {
			if(className.equals("PInventory"))
				getHibernateTemplate().merge(this.inventoryCopy(transientInstance));
			else
				getHibernateTemplate().merge(transientInstance);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}		
	}
	
	public void save(Inventory transientInstance) {
		log.debug("saving " + className + " instance");
		try {
			if(className.equals("PInventory"))
				getHibernateTemplate().save(this.inventoryCopy(transientInstance));
			else
				getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	public List<Inventory> findByShopId(final IFieldValidation act, final Object shopId, final boolean viewZero, final boolean isDealer) {
		SqlCallback sqlCallback = new SqlCallback(){
			public void setPageSql(Page page) {
				if(isDealer)
					page.getAttachSql().append(" and model.dealerId=").append(shopId);
				else
					page.getAttachSql().append(" and model.shopId=").append(shopId);
				if(! viewZero)
					page.getAttachSql().append(" and model.qty <> 0");
			}
		};
		return this.findList(act, sqlCallback);	
	}
	
	public List<Inventory> findInventory(final IFieldValidation act, final int warehouseId, final boolean viewZero) {
		SqlCallback sqlCallback = new SqlCallback(){
			public void setPageSql(Page page) {
				page.getAttachSql().append(" and model.warehouseId=").append(warehouseId);	
				if(! viewZero)
					page.getAttachSql().append(" and model.qty <> 0");
			}
		};
		return this.findList(act, sqlCallback);	
	}
	
	public List<Inventory> findInventory(final IFieldValidation act, final int shopId, final int warehouseId, final boolean viewZero) {
		SqlCallback sqlCallback = new SqlCallback(){
			public void setPageSql(Page page) {
				page.getAttachSql().append(" and model.shopId=").append(shopId).append(" and model.warehouseId=").append(warehouseId);	
				if(! viewZero)
					page.getAttachSql().append(" and model.qty <> 0");
			}
		};
		return this.findList(act, sqlCallback);	
	}
	
	//*********************************************************
	private List<Inventory> findByProperty(String propertyName, Object value) {
	      log.debug("finding " + className + " instance with property: " + propertyName
	            + ", value: " + value);
	      try {
	         String queryString = "from " + className + " as model where model." 
	         						+ propertyName + "= ?";
			 return getHibernateTemplate().find(queryString, value);
	      } catch (RuntimeException re) {
	         log.error("find by property name failed", re);
	         throw re;
	      }
		}
	
	public List<Inventory> findByWarehouse(int Warehouseid) {
	      log.debug("finding " + className + " instance with  warehouseId: " + Warehouseid);
	      try {
	         String queryString = "from " + className + " as model where model.warehouseId ="+ Warehouseid;
			 return getHibernateTemplate().find(queryString);
	      } catch (RuntimeException re) {
	         log.error("find by property name failed", re);
	         
	         throw re;

	      }
		}
	//class inside method
	private PInventory inventoryCopy(Inventory inventory) {
		PInventory pinventory = new PInventory();
		
		pinventory.setId(inventory.getId());
		pinventory.setDealerId(inventory.getDealerId());
		pinventory.setShopId(inventory.getShopId());
		pinventory.setDealerShortName(inventory.getDealerShortName());
		pinventory.setShopShortName(inventory.getShopShortName());
		pinventory.setWarehouseId(inventory.getWarehouseId());
		pinventory.setWarehouseName(inventory.getWarehouseName());
		pinventory.setProductId(inventory.getProductId());
		pinventory.setProductCode(inventory.getProductCode());
		pinventory.setProductName(inventory.getProductName());
		pinventory.setProductColor(inventory.getProductColor());
		pinventory.setProductUnit(inventory.getProductUnit());
		pinventory.setQty(inventory.getQty());
		pinventory.setAveragePrice(inventory.getAveragePrice());
		pinventory.setTotal(inventory.getTotal());
		
		return pinventory;
	}
	
	//日志
	private static final Log log = LogFactory.getLog(InventoryDAOImpl.class);
}