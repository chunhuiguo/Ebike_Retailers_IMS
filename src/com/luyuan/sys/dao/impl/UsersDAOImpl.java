/**
 * @(#)BaseAct.java  1.0 10/03/31
 *
 * Copyright 2010 luyuan-ebike, Inc. All rights reserved.
 * 
 */
package com.luyuan.sys.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

//import com.luyuan.deal.entity.Voucher;
import com.luyuan.sys.dao.UsersDAO;
import com.luyuan.sys.entity.Users;


/**
 * 
 * 最终会被注入的UsersDAO，实现DAO接口逻辑
 *
 * @author tom
 */

public class UsersDAOImpl extends HibernateDaoSupport implements UsersDAO {
	
	public Users findByLoginName(String userCode){
		List<Users> list = this.findByProperty("userCode",userCode);
		if (list != null && list.size() > 0)
			return (Users)list.get(0);
		return null;
	}
	
	//************************************************************************
	private List<Users> findByProperty(String propertyName, Object value) {
		log.debug("finding Users instance with property: " + propertyName
	            + ", value: " + value);
		try {
			String queryString = "from Users as model where model." 
	         						+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	//获得可用模块信息
	public List getModelByUser(final String userCode){
		
		return  (List)this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {
				String procName = "{Call sys_OP_GetByUser(?)}";
				SQLQuery query = session.createSQLQuery(procName);
				query.setString(0, userCode); 
				
				List opList = query.list();

				return opList;
			}
		});	
	}	
	
	public List getOpByUserAndOpno(final String userCode, final String opNo){
		
		return  (List)this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {
				String procName = "{Call sys_OP_GetByNOAndUser(?,?)}";
				SQLQuery query = session.createSQLQuery(procName);
				query.setString(0, userCode); 
				query.setString(1, opNo); 
				List opList = query.list();

				return opList;
			}
		});	
	}
	
	public Users findByUserCode(String userCode) {
		List<Users> list = this.findByProperty("userCode", userCode);
		if(list != null && list.size() != 0)
			return list.get(0);
		return null;
	}
	
	public List<Users> getUserByUserCode(String userCode){
		
		try {
			List<Users> list = getHibernateTemplate().find(
					"from Users as model where model.creatorCode='" + userCode +"'");
			return list;
			
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}	
	}
	
	public void save(Users user) {
		log.debug("saving User instance");
		try {
			getHibernateTemplate().save(user);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	public void update(Users detachedInstance) {
        log.debug("updating Users instance");
        try {
        	getHibernateTemplate().update(detachedInstance);
            log.debug("update successful");            
        } catch (RuntimeException re) {
            log.error("update failed", re);
            throw re;
        }
    }
	
	public Users merge(Users detachedInstance) {
        log.debug("merging Users instance");
        try {
        	Users result = (Users) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
	
	//gch
	public Users findById( java.lang.Integer id) {
        log.debug("getting Users instance with id: " + id);
        try {
            Users instance = (Users) getHibernateTemplate()
                    .get("com.luyuan.sys.entity.Users", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
	
	private static final Log log = LogFactory.getLog(UsersDAOImpl.class);

}