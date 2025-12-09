/**
 * @(#)AccountDAOImpl.java  1.0 10/04/13
 *
 * Copyright 2010 luyuan-ebike, Inc. All rights reserved.
 * 
 */
package com.luyuan.info.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.luyuan.info.dao.ProductHistoryDAO;
import com.luyuan.info.entity.ProductHistory;
import com.luyuan.info.entity.ProductInWarehouse;



public class ProductHistoryDAOImpl extends HibernateDaoSupport implements ProductHistoryDAO {
	
	public void save(final ProductInWarehouse productInWarehouse) {
		/***********Hibernate自带的save方法提示productId为空，无法插入，只好自己写SQL***********/
		//productHistory与productInWarehouse字段、内容都一样
		//productHistory是productInWarehouse的副本
		//所以参数传productInWarehouse
		this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				//productId和productBarcode不允许为空，所以这两个字段不用判断是否为NULL，肯定不是
				String sql = "insert into ProductHistory values (" + productInWarehouse.getProductId()
																	+ ", '" + productInWarehouse.getProductBarcode() + "', ";
				
				if(productInWarehouse.getProductCode() == null)
					sql = sql + "null, ";
				else
					sql = sql + "'" + productInWarehouse.getProductCode() + "', ";
																	
				if(productInWarehouse.getWarehouseCode() == null)
					sql = sql + "null, ";
				else
					sql = sql + "'" + productInWarehouse.getWarehouseCode() + "', ";
				
				if(productInWarehouse.getFrameBarcode() == null)
					sql = sql + "null, ";
				else
					sql = sql + "'" + productInWarehouse.getFrameBarcode() + "', ";
				
				if(productInWarehouse.getControlBarcode() == null)
					sql = sql + "null, ";
				else
					sql = sql + "'" + productInWarehouse.getControlBarcode() + "', ";
				
				if(productInWarehouse.getHubBarcode() == null)
					sql = sql + "null, ";
				else
					sql = sql + "'" + productInWarehouse.getHubBarcode() + "', ";
				
				if(productInWarehouse.getStatus() == null)
					sql = sql + "null, ";
				else
					sql = sql + "'" + productInWarehouse.getStatus() + "', ";
																	
				if(productInWarehouse.getStatusLastDate() == null)
					sql = sql + "null, ";
				else
					sql = sql + "'" + productInWarehouse.getStatusLastDate().toString().substring(0, 19) + "', ";
				
				if(productInWarehouse.getShipOrderCode() == null)
					sql = sql + "null, ";
				else
					sql = sql + "'" + productInWarehouse.getShipOrderCode() + "', ";
				
				if(productInWarehouse.getDealerCode() == null)
					sql = sql + "null, ";
				else
					sql = sql + "'" + productInWarehouse.getDealerCode() + "', ";
				
				if(productInWarehouse.getOldId() == null)
					sql = sql + "null, ";
				else
					sql = sql + productInWarehouse.getOldId() + ", ";
				
				if(productInWarehouse.getProductionPlace() == null)
					sql = sql + "null, ";
				else
					sql = sql + "'" + productInWarehouse.getProductionPlace() + "', ";
				
				if(productInWarehouse.getVoucherId() == null)
					sql = sql + "null, ";
				else
					sql = sql + productInWarehouse.getVoucherId() + ", ";						
							
				if(productInWarehouse.getInspectionDate() == null)
					sql = sql + "null, ";
				else
					sql = sql + "'"  + productInWarehouse.getInspectionDate().toString().substring(0, 19) + "', ";
				
				if(productInWarehouse.getProductionLine() == null)
					sql = sql + "null, ";
				else
					sql = sql + "'" + productInWarehouse.getProductionLine() + "', ";
				
				if(productInWarehouse.getStorageStatus() == null)
					sql = sql + "null, ";
				else
					sql = sql + "'" + productInWarehouse.getStorageStatus() + "', ";
						
				if(productInWarehouse.getStorageDate() == null)
					sql = sql + "null, ";
				else
					sql = sql + "'" + productInWarehouse.getStorageDate().toString().substring(0, 19) + "', ";
				
				if(productInWarehouse.getListCategory() == null)
					sql = sql + "null, ";
				else
					sql = sql + "'" + productInWarehouse.getListCategory() + "', ";
				
				if(productInWarehouse.getServiceCardNumber() == null)
					sql = sql + "null)";
				else
					sql = sql + "'" + productInWarehouse.getServiceCardNumber() + "')";	//由于少最后的"'"符号，耗了2个多小时，本地的测试数据的serviceCardNumber都是空，执行不到这一步，测试时没有发现问题			
				
				SQLQuery query = session.createSQLQuery(sql);			
				
				query.executeUpdate();
				return null;
			}
		});
		
//		ProductHistory productHistory = new ProductHistory();
//		productHistory.setProductId(productInWarehouse.getProductId());
//		System.out.println("**********************");
//		System.out.println(productHistory.getProductId());
//		System.out.println("**********************");
//		productHistory.setProductBarcode(productInWarehouse.getProductBarcode());
//		productHistory.setProductCode(productInWarehouse.getProductCode());
//		productHistory.setWarehouseCode(productInWarehouse.getWarehouseCode());
//		productHistory.setFrameBarcode(productInWarehouse.getFrameBarcode());
//		productHistory.setControlBarcode(productInWarehouse.getControlBarcode());
//		productHistory.setHubBarcode(productInWarehouse.getHubBarcode());
//		productHistory.setStatus(productInWarehouse.getStatus());
//		productHistory.setStatusLastDate(productInWarehouse.getStatusLastDate());
//		productHistory.setShipOrderCode(productInWarehouse.getShipOrderCode());
//		productHistory.setDealerCode(productInWarehouse.getDealerCode());
//		productHistory.setOldId(productInWarehouse.getOldId());
//		productHistory.setProductionPlace(productInWarehouse.getProductionPlace());
//		productHistory.setVoucherId(productInWarehouse.getVoucherId());
//		productHistory.setInspectionDate(productInWarehouse.getInspectionDate());
//		productHistory.setProductionLine(productInWarehouse.getProductionLine());
//		productHistory.setStorageStatus(productInWarehouse.getStorageStatus());
//		productHistory.setStorageDate(productInWarehouse.getStorageDate());
//		productHistory.setListCategory(productInWarehouse.getListCategory());
//		productHistory.setServiceCardNumber(productInWarehouse.getServiceCardNumber());
//		
//		getHibernateTemplate().save(productHistory);
	}	
	
	public ProductHistory findByProductBarcode(String productBarcode) {
		List<ProductHistory> list = this.findByProperty("productBarcode", productBarcode);
		if(list != null && list.size() != 0)
			return list.get(0);
		return null;
	}
	
	//private method
	private List<ProductHistory> findByProperty(String propertyName, Object value) {
		log.debug("finding ProductHistory instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ProductHistory as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	//日志
	private static final Log log = LogFactory.getLog(ProductHistoryDAOImpl.class);	
}