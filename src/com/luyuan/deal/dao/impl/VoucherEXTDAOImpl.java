/**
 * @(#)VoucherEXTDAOImpl.java  1.0 10/04/16
 *
 * Copyright 2010 luyuan-ebike, Inc. All rights reserved.
 * 
 */
package com.luyuan.deal.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.luyuan.deal.dao.VoucherEXTDAO;
import com.luyuan.deal.entity.VoucherEXT;
import com.luyuan.util.PaginateHib;

/**
 * 
 * 最终会被注入的VoucherEXTDAO，实现DAO接口逻辑
 *
 * @see com.luyuan.deal.entity.VoucherEXT
 * @author gch
 */

public class VoucherEXTDAOImpl extends PaginateHib implements VoucherEXTDAO {
	
	public void save(VoucherEXT transientInstance) {
		log.debug("saving VoucherEXT instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	public void deleteByVoucherId(final Long voucherId) {
        log.debug("deleting voucherEXT instance");
        this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String sql = "delete VoucherEXT as model where 1 = 0";
				if (voucherId != null)					
					sql += " or model.voucherId= " + voucherId.longValue();
				Query query = session.createQuery(sql);	
				query.executeUpdate();
				return null;
			}
		});
    }
	
	public boolean hasProductBarcode(int dealerId, String productBarcode) {
		String queryString = "from VoucherEXT as model where model.dealerId=" + dealerId + " and model.voucherType='整车销售单' and model.productBarcode ='" + productBarcode + "'";
		List<VoucherEXT> list = getHibernateTemplate().find(queryString);
		
		if(list != null && list.size() != 0)
			return true;
		
		return false;
	}
	
	//日志
	private static final Log log = LogFactory.getLog(VoucherEXTDAOImpl.class);

}