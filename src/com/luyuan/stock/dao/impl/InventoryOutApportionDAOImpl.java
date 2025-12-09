/**
 * @(#)InventoryOutApportionDAOImpl.java  1.0 10/04/28
 *
 * Copyright 2010 luyuan-ebike, Inc. All rights reserved.
 * 
 */
package com.luyuan.stock.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.luyuan.stock.dao.InventoryOutApportionDAO;
import com.luyuan.stock.entity.InventoryOutApportion;
import com.luyuan.stock.entity.PInventoryOutApportion;
import com.luyuan.util.PaginateHib;

/**
 * 
 * 最终会被注入的InventoryOutApportionDAO，实现DAO接口逻辑
 *
 * @see com.luyuan.stock.entity.InventoryOutApportion
 * @author gch
 */

public class InventoryOutApportionDAOImpl extends PaginateHib implements InventoryOutApportionDAO {
		
	public void save(InventoryOutApportion transientInstance) {
        log.debug("saving " + className + " instance");
        try {
        	if(className.equals("PInventoryOutApportion"))
        		getHibernateTemplate().save(this.inventoryOutApportionCopy(transientInstance));
        	else
        		getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
	
	public List<InventoryOutApportion> findByVoucherDetailId(Object voucherDetailId) {
		return this.findByProperty("voucherDetailId", voucherDetailId);
	}
	
	//************************************************************************
	private List<InventoryOutApportion> findByProperty(String propertyName, Object value) {
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
	
	//class inside method
	private PInventoryOutApportion inventoryOutApportionCopy(InventoryOutApportion inventoryOutApportion) {
		PInventoryOutApportion pinventoryOutApportion = new PInventoryOutApportion();
		
		pinventoryOutApportion.setId(inventoryOutApportion.getId());
		pinventoryOutApportion.setDealerId(inventoryOutApportion.getDealerId());
		pinventoryOutApportion.setShopId(inventoryOutApportion.getShopId());
		pinventoryOutApportion.setDealerShortName(inventoryOutApportion.getDealerShortName());
		pinventoryOutApportion.setShopShortName(inventoryOutApportion.getShopShortName());
		pinventoryOutApportion.setVoucherDetailId(inventoryOutApportion.getVoucherDetailId());
		pinventoryOutApportion.setWarehouseId(inventoryOutApportion.getWarehouseId());
		pinventoryOutApportion.setWarehouseName(inventoryOutApportion.getWarehouseName());
		pinventoryOutApportion.setProductId(inventoryOutApportion.getProductId());
		pinventoryOutApportion.setQty(inventoryOutApportion.getQty());
		pinventoryOutApportion.setPrice(inventoryOutApportion.getPrice());
		pinventoryOutApportion.setTotal(inventoryOutApportion.getTotal());
		
		return pinventoryOutApportion;
	}
	
	//日志
	private static final Log log = LogFactory.getLog(InventoryOutApportionDAOImpl.class);
}