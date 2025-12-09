/**
 * @(#)CodeCreatorDAOImpl.java  1.0 10/04/06
 *
 * Copyright 2010 luyuan-ebike, Inc. All rights reserved.
 * 
 */
package com.luyuan.deal.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.luyuan.deal.dao.CodeCreatorDAO;
import com.luyuan.deal.entity.CodeCreator;

/**
 * 
 * 最终会被注入的CodeCreatorDAO，实现DAO接口逻辑
 *
 * @see com.luyuan.deal.entity.CodeCreator
 * @author gch
 */

public class CodeCreatorDAOImpl extends HibernateDaoSupport implements CodeCreatorDAO {
	
	public CodeCreator findNumber(int dealerId, String prefix, String year) {
		log.debug("finding CodeCreator instance with dealerId: " + dealerId
				+ " and prefix: " + prefix + " and year: " + year);
		try {
			List<CodeCreator> list = getHibernateTemplate().find(
					"from CodeCreator as model where model.dealerId=" 
					+ dealerId + " and model.prefix='" + prefix + 
					"' and model.year=" + year);
			if(list !=null && list.size() != 0)
				return list.get(0);
			return null;
				
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}		
	}
	
	public void update(CodeCreator transientInstance) {
		log.debug("updating CodeCreator instance");
		try {
			getHibernateTemplate().update(transientInstance);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}		
	}
	
	public void save(CodeCreator transientInstance) {
		log.debug("saving CodeCreator instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	//日志
	private static final Log log = LogFactory.getLog(CodeCreatorDAOImpl.class);
}