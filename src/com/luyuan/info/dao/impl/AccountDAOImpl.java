/**
 * @(#)AccountDAOImpl.java  1.0 10/04/13
 *
 * Copyright 2010 luyuan-ebike, Inc. All rights reserved.
 * 
 */
package com.luyuan.info.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.luyuan.action.IFieldValidation;
import com.luyuan.info.dao.AccountDAO;
import com.luyuan.info.entity.Account;
import com.luyuan.util.Page;
import com.luyuan.util.PaginateHib;
import com.luyuan.util.SqlCallback;

/**
 * 
 * 最终会被注入的AccountDAO，实现DAO接口逻辑
 *
 * @see com.luyuan.info.entity.Account
 * @author gch
 */

public class AccountDAOImpl extends PaginateHib implements AccountDAO {
	
	//gch
	public Account findById(java.lang.Integer id) {
		log.debug("getting Account instance with id: " + id);
		try {
			Account instance = (Account) getHibernateTemplate().get(
					"com.luyuan.info.entity.Account", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public void update(Account transientInstance) {
		log.debug("updating Account instance");
		try {
			getHibernateTemplate().update(transientInstance);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}
	
	//wml
	//通过经销商Id查找所有总帐户
	//true 可用的账户    false  所有的账户
	public List<Account> findAccount(IFieldValidation act, final Integer dealerId, final boolean isDisable) {
		log.debug("finding " + className + " instance with dealerId: "
				+ dealerId + " and isDisable: " + isDisable);
		SqlCallback sqlCallback = new SqlCallback() {
			public void setPageSql(Page page) {
				if (isDisable)
					page.getAttachSql().append(" and model.dealerId=").append(
							dealerId).append(" and model.disable=").append(
							isDisable);
				else
					page.getAttachSql().append(" and model.dealerId=").append(
							dealerId);
			}
		};
		return this.findList(act, sqlCallback);
	}
	
	//查找停用的账户
	public List<Account> findDisabledAccount(Integer dealerId) {
		String queryString = "from Account as model where model.dealerId="
			+ dealerId + " and model.disable=" + false;
		return getHibernateTemplate().find(queryString);
	}

	
	//根据经销商、应付或者应收查找总账户
	public List<Account> findAccount(Integer dealerId, String type) {
		String queryString = "from Account as model where model.dealerId="
				+ dealerId + " and model.disable=" + true + " and model.type='"
				+ type + "'";
		List<Account> list = getHibernateTemplate().find(queryString);
		return list;
	}
	
	
	public List<Account> findAccount(IFieldValidation act, final Integer dealerId, final String type) {
		log.debug("finding " + className + " instance with dealerId: "
				+ dealerId + " and type: " + type);
		SqlCallback sqlCallback = new SqlCallback() {
			public void setPageSql(Page page) {
				page.getAttachSql().append(" and model.dealerId=").append(
						dealerId).append(" and model.type='").append(
								type).append("'");
			}
		};
		return this.findList(act, sqlCallback);
	}
	
	//查找所有可用的账户
	public Account findAccount(Integer dealerId ,Integer dealingUnitId,String type){
		String queryString = "from Account as model where model.dealerId="
				+ dealerId + " and model.disable=" + true
				+ " and model.dealingUnitId=" + dealingUnitId
				+ "and model.type='" + type + "'";
		List<Account> list=getHibernateTemplate().find(queryString);	
		if (list != null && list.size() > 0)
			return list.get(0);
		else
			return null;
		
	}
	
	//所有账户包括停用的
	public Account findAllAccount(Integer dealerId ,Integer dealingUnitId,String type){
		String queryString = "from Account as model where model.dealerId="
			+ dealerId+ " and model.dealingUnitId=" + dealingUnitId
			+ "and model.type='" + type + "'";
	List<Account> list=getHibernateTemplate().find(queryString);
	return list.get(0);
	}		
	
	public void save(Account account){
		getHibernateTemplate().save(account);
	}	
	
	//**************************************************************************
	private List findByProperty(String propertyName, Object value) {
		log.debug("finding Account instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Account as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	//日志
	private static final Log log = LogFactory.getLog(AccountDAOImpl.class);	
}