package com.luyuan.info.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.luyuan.info.dao.SubAccountTypeDAO;
import com.luyuan.info.entity.SubAccountType;



/**
 * 
 */

public class SubAccountTypeDAOImpl extends HibernateDaoSupport implements SubAccountTypeDAO{
	
	public List<SubAccountType> findAllType(int dealerId) {
		String queryString = "from SubAccountType as model where model.dealerId="+ dealerId;
		return getHibernateTemplate().find(queryString);
	}
	
	public void save(SubAccountType subAccountType){
		getHibernateTemplate().save(subAccountType);
	}
	
	public void delete(SubAccountType persistentInstance) {
        log.debug("deleting SubAccountType instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
	
	public SubAccountType findById( java.lang.Integer id) {
        log.debug("getting SubAccountType instance with id: " + id);
        try {
            SubAccountType instance = (SubAccountType) getHibernateTemplate()
                    .get("com.luyuan.info.entity.SubAccountType", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
	
	//log
	private static final Log log = LogFactory.getLog(SubAccountTypeDAOImpl.class);

	
}