/**
 * @(#)AccountDAOImpl.java  1.0 10/04/13
 *
 * Copyright 2010 luyuan-ebike, Inc. All rights reserved.
 * 
 */
package com.luyuan.info.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.luyuan.info.dao.ProductInWarehouseDAO;
import com.luyuan.info.entity.Product;
import com.luyuan.info.entity.ProductInWarehouse;



public class ProductInWarehouseDAOImpl extends HibernateDaoSupport implements ProductInWarehouseDAO {
	
	public ProductInWarehouse findByProductBarcode(String productBarcode) {
		List<ProductInWarehouse> list = this.findByProperty("productBarcode", productBarcode);
		if(list != null && list.size() != 0)
			return list.get(0);
		return null;		
	}
	
//	public ProductInWarehouse findProductInfo(String productBarcode, String dealerShortName) {
//		String queryString = "from ProductInWarehouse as model where model.productBarcode='"
//							+ productBarcode + "' and model.dealerCode='" + dealerShortName + "'";
//		List<ProductInWarehouse> list = getHibernateTemplate().find(queryString);
//		if(list != null && list.size() != 0)
//			return list.get(0);
//		return null;		
//	}
//	
//	public Timestamp findInspectionDate(String productBarcode, String dealerShortName) {
//		String queryString = "from ProductInWarehouse as model where model.productBarcode='"
//			+ productBarcode + "' and model.dealerCode='" + dealerShortName + "'";		
//		ProductInWarehouse productInWarehouse = (ProductInWarehouse) getHibernateTemplate().find(queryString).get(0);		
//		 return productInWarehouse.getInspectionDate();
//	}
	
	public void deleteByProductId(final Integer productId) {       
        this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String sql = "delete ProductInWarehouse as model where 1 = 0";
				if (productId != null)					
					sql += " or model.productId= " + productId.intValue();
				Query query = session.createQuery(sql);	
				query.executeUpdate();
				return null;
			}
		});
    }
	
	
	
	/**********************************************************************************/
	public List<ProductInWarehouse> findByProperty(String propertyName, Object value) {
		log.debug("finding ProductInWarehouse instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ProductInWarehouse as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	//日志
	private static final Log log = LogFactory.getLog(ProductInWarehouseDAOImpl.class);	
}