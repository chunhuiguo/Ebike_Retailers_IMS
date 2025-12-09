/**
 * @(#)AccountDAOImpl.java  1.0 10/04/13
 *
 * Copyright 2010 luyuan-ebike, Inc. All rights reserved.
 * 
 */
package com.luyuan.info.dao.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.luyuan.info.dao.TblSMToSendDAO;
import com.luyuan.info.entity.TblSMToSend;



public class TblSMToSendDAOImpl extends HibernateDaoSupport implements TblSMToSendDAO {
	
	public void save(TblSMToSend transientInstance) {
		log.debug("saving TblSMToSend instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	//日志
	private static final Log log = LogFactory.getLog(TblSMToSendDAOImpl.class);	
}