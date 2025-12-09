package com.luyuan.report.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.luyuan.report.dao.InventoryLastDAO;
import com.luyuan.report.entity.InventoryLast;

import com.luyuan.report.entity.PInventoryLast;
import com.luyuan.stock.entity.Inventory;

import com.luyuan.util.PaginateHib;



public class InventoryLastDAOImpl extends PaginateHib implements InventoryLastDAO{
	
	

	public void save(InventoryLast transientInstance) {
		log.debug("saving " + className + " instance");
		try {
			if(className.equals("PInventoryLast"))
				getHibernateTemplate().save(this.inventoryLastCopy(transientInstance));
			else
				getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	
	public void update(InventoryLast transientInstance) {
		log.debug("updating " + className + " instance");
		try {
			if(className.equals("PInventoryLast"))
				getHibernateTemplate().saveOrUpdate(this.inventoryLastCopy(transientInstance));
			else
				getHibernateTemplate().saveOrUpdate(transientInstance);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}
	
	public InventoryLast findInventoryLast(int warehouseId, long productId) {
		log.debug("finding " + className + " instance with warehouseId: " + warehouseId
				 + " and productId: " + productId);
		try {
			List<InventoryLast> list = getHibernateTemplate().find(
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
	
	public List<InventoryLast> findByDealerId(Integer shopId ){
		String queryString;
//		if(isDealer)
//			queryString = "from InventoryLast as model where model.dealerId="
//				+ shopId ;
//		else
			queryString = "from " + className + " as model where model.shopId="
				+ shopId ;
		return getHibernateTemplate().find(queryString);
	}
	
	
	//class inside method
	private PInventoryLast inventoryLastCopy(InventoryLast inventoryLast) {
		PInventoryLast pinventoryLast = new PInventoryLast();
		
		pinventoryLast.setId(inventoryLast.getId());
		pinventoryLast.setDealerId(inventoryLast.getDealerId());
		pinventoryLast.setDealerShortName(inventoryLast.getDealerShortName());
		pinventoryLast.setShopId(inventoryLast.getShopId());
		pinventoryLast.setShopShortName(inventoryLast.getShopShortName());
		pinventoryLast.setWarehouseId(inventoryLast.getWarehouseId());
		pinventoryLast.setWarehouseName(inventoryLast.getWarehouseName());
		pinventoryLast.setProductId(inventoryLast.getProductId());
		pinventoryLast.setProductName(inventoryLast.getProductName());
		pinventoryLast.setProductCode(inventoryLast.getProductCode());
		pinventoryLast.setProductColor(inventoryLast.getProductColor());
		pinventoryLast.setQty(inventoryLast.getQty());
		
		return pinventoryLast;
	}
	
	//log
	private static final Log log = LogFactory.getLog(InventoryLastDAOImpl.class);
}