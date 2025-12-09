/**
 * @(#)VoucherDetailDAOImpl.java  1.0 10/04/16
 *
 * Copyright 2010 luyuan-ebike, Inc. All rights reserved.
 * 
 */
package com.luyuan.deal.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.luyuan.deal.dao.VoucherDetailDAO;
import com.luyuan.deal.entity.PVoucherDetail;
import com.luyuan.deal.entity.VoucherDetail;
import com.luyuan.util.PaginateHib;

/**
 * 
 * 最终会被注入的VoucherDetailDAO，实现DAO接口逻辑
 *
 * @see com.luyuan.deal.entity.Voucher
 * @author gch
 */


public class VoucherDetailDAOImpl extends PaginateHib implements VoucherDetailDAO {
	
	public void save(VoucherDetail transientInstance) {
		log.debug("saving " + className + " instance");
		try {
			if(className.equals("PVoucherDetail"))
				getHibernateTemplate().save(this.voucherDetailCopy(transientInstance));
			else
				getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	public void saveOrUpdate(VoucherDetail transientInstance) {
		log.debug("saving or updating " + className + " instance");
		try {	
			if(className.equals("PVoucherDetail"))
				getHibernateTemplate().merge(this.voucherDetailCopy(transientInstance));
			else
				getHibernateTemplate().merge(transientInstance);
			log.debug("save or update successful");
		} catch (RuntimeException re) {
			log.error("save or update failed", re);
			throw re;
		}
	}
	
	public List<VoucherDetail> findByVoucherId(Object voucherId) {
		return this.findByProperty("voucherId", voucherId);
	}
	
	public void deleteByVoucherId(final List<Long> voucherIdList) {
		this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String sql = "delete " + className + " as model where 1 = 0";
				if (voucherIdList != null) {
					for(int i = 0; i < voucherIdList.size(); i++)
						sql += " or model.voucherId= " + voucherIdList.get(i).longValue();
				}
				Query query = session.createQuery(sql);	
				query.executeUpdate();
				return null;
			}
		});
    }
	
	public void deleteById(final Long voucherDetailId) {
        log.debug("deleting " + className + " instance");
        this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String sql = "delete " + className + " as model where 1 = 0";
				if (voucherDetailId != null)					
					sql += " or model.id= " + voucherDetailId.longValue();
				Query query = session.createQuery(sql);	
				query.executeUpdate();
				return null;
			}
		});
    }
	
//	public void delete(VoucherDetail persistentInstance) {
//        log.debug("deleting " + className + " instance");
//        try {
//        	if(className.equals("PVoucherDetail"))
//				getHibernateTemplate().delete(this.voucherDetailCopy(persistentInstance));
//			else
//				getHibernateTemplate().delete(persistentInstance);
//            log.debug("delete successful");
//        } catch (RuntimeException re) {
//            log.error("delete failed", re);
//            throw re;
//        }
//    }	  
	
	//************************************************************************
	private List<VoucherDetail> findByProperty(String propertyName, Object value) {
		log.debug("finding " + className + " instance with property: "
				+ propertyName + ", value: " + value);
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
	private PVoucherDetail voucherDetailCopy(VoucherDetail voucherDetail) {
		PVoucherDetail pvoucherDetail = new PVoucherDetail();
		
		pvoucherDetail.setId(voucherDetail.getId());
		pvoucherDetail.setVoucherId(voucherDetail.getVoucherId());
		pvoucherDetail.setProductId(voucherDetail.getProductId());
		pvoucherDetail.setProductName(voucherDetail.getProductName());
		pvoucherDetail.setProductCode(voucherDetail.getProductCode());
		pvoucherDetail.setProductColor(voucherDetail.getProductColor());
		pvoucherDetail.setProductUnit(voucherDetail.getProductUnit());
		pvoucherDetail.setPrice(voucherDetail.getPrice());
		pvoucherDetail.setDiscount(voucherDetail.getDiscount());
		pvoucherDetail.setPriceType(voucherDetail.getPriceType());
		pvoucherDetail.setQty(voucherDetail.getQty());
		pvoucherDetail.setTotal(voucherDetail.getTotal());
		pvoucherDetail.setRemark(voucherDetail.getRemark());
		
		return pvoucherDetail;
	}
	
//	private List<VoucherDetail> pvoucherDetailList(List<PVoucherDetail> pvoucherDetailList) {
//		List<VoucherDetail> voucherDetailList = new ArrayList<VoucherDetail>();
//		
//		for(int i = 0; i < pvoucherDetailList.size(); i++) {
//			VoucherDetail pvoucherDetail = new VoucherDetail();
//			PVoucherDetail voucherDetail = pvoucherDetailList.get(i);
//			
//			pvoucherDetail.setId(voucherDetail.getId());
//			pvoucherDetail.setVoucherId(voucherDetail.getVoucherId());
//			pvoucherDetail.setProductId(voucherDetail.getProductId());
//			pvoucherDetail.setProductName(voucherDetail.getProductName());
//			pvoucherDetail.setProductCode(voucherDetail.getProductCode());
//			pvoucherDetail.setProductColor(voucherDetail.getProductColor());
//			pvoucherDetail.setProductUnit(voucherDetail.getProductUnit());
//			pvoucherDetail.setPrice(voucherDetail.getPrice());
//			pvoucherDetail.setDiscount(voucherDetail.getDiscount());
//			pvoucherDetail.setPriceType(voucherDetail.getPriceType());
//			pvoucherDetail.setQty(voucherDetail.getQty());
//			pvoucherDetail.setTotal(voucherDetail.getTotal());
//			pvoucherDetail.setRemark(voucherDetail.getRemark());
//			
//			voucherDetailList.add(pvoucherDetail);
//		}
//		return voucherDetailList;
//	}
	
	//日志
	private static final Log log = LogFactory.getLog(VoucherDetailDAOImpl.class);
}