/**
 * @(#)SubAccountDAOImpl.java  1.0 10/04/13
 *
 * Copyright 2010 luyuan-ebike, Inc. All rights reserved.
 * 
 */
package com.luyuan.info.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.luyuan.info.dao.SubAccountDAO;
import com.luyuan.info.entity.SubAccount;

/**
 * 
 * 最终会被注入的SubAccountDAO，实现DAO接口逻辑
 *
 * @see com.luyuan.info.entity.SubAccount
 * @author gch
 */

public class SubAccountDAOImpl extends HibernateDaoSupport implements SubAccountDAO {
	
	//gch
	public SubAccount findSubAccountForVoucher(int dealerId, int dealingUnitId, String prefix, String type) {
		log.debug("finding SubAccount instance with dealerId: " + dealerId + 
				" and dealingUnitId: " + dealingUnitId + " and disable: True" 
				+ " for voucherType: " + prefix);
		try {
			if(prefix.equals("JH")) {
				
				List<SubAccount> list = getHibernateTemplate().find(
						"from SubAccount as model where model.dealerId=" 
						+ dealerId + " and model.dealingUnitId=" + dealingUnitId
						+ " and model.type='应付' and model.name like '%" + type + "账户'"
						+ " and model.disable=" + true);

				if(list != null && list.size() != 0)
					return list.get(0);
			}
			if(prefix.equals("XS")) {
				
				List<SubAccount> list = getHibernateTemplate().find(
						"from SubAccount as model where model.dealerId=" 
						+ dealerId + " and model.dealingUnitId=" + dealingUnitId
						+ " and model.type='应收' and model.name like '%" + type + "账户'"
						+ " and model.disable=" + true);
				
				if(list != null && list.size() != 0)
					return list.get(0);
			}
			
			return null;
				
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public SubAccount findById(java.lang.Integer id) {
		log.debug("getting SubAccount instance with id: " + id);
		try {
			SubAccount instance = (SubAccount) getHibernateTemplate().get(
					"com.luyuan.info.entity.SubAccount", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public void update(SubAccount transientInstance) {
		log.debug("updating SubAccount instance");
		try {
			getHibernateTemplate().update(transientInstance);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}
	
	//wml
	public SubAccount findSubAccount(int dealerId,int dealingUnitId,String type,String name){
		List<SubAccount> list = getHibernateTemplate().find(
				"from SubAccount as model where model.dealerId=" + dealerId
						+ " and model.dealingUnitId=" + dealingUnitId
						+ " and model.type='" + type + "' and model.name='"
						+ name + "' and model.disable=" + true);
		if (list != null && list.size() != 0)
			return list.get(0);
		return null;
	
	}
	//通过经销商查找所有子帐户
	public List<SubAccount> findSubAccount(Integer dealerId){
		String queryString = "from SubAccount as model where model.dealerId="
			+ dealerId + "and model.disable=" + true;
		return getHibernateTemplate().find(queryString);
	}
	
	//通过经销商、往来单位、单据类型查找对应子帐户
	public List<SubAccount> findSubAccount(Integer dealerId,
			Integer dealingUnitId,String dealingType) {
		String type;
		if (dealingType.equals("付款单")) {
			type = "应付";
		} else
			type = "应收";
		
		String queryString = "from SubAccount as model where model.dealerId="
				+ dealerId + " and model.type='" + type+ "' and model.dealingUnitId=" + dealingUnitId+ " and model.disable=" + true;
		List<SubAccount> list= getHibernateTemplate().find(queryString);
		if(list!=null&&list.size()>0){
			return list;
		}
		return null;
	}
	
	//
	public List<SubAccount> findAllSubAccount(Integer dealerId,
			Integer dealingUnitId,String type) {
		String queryString = "from SubAccount as model where model.dealerId="
			+ dealerId + " and model.type='" + type+ "' and model.dealingUnitId=" + dealingUnitId;
	List<SubAccount> list= getHibernateTemplate().find(queryString);	
	return list;
	}	
	
	//通过总帐户的id查找子帐户
	public List<SubAccount> findSubAccount(int parentAccountId){
		return findByProperty("parentAccountId", parentAccountId);
	}
	
	public void save(SubAccount subAccount){
		getHibernateTemplate().save(subAccount);
	}
	
	
	//*******************************************************************************
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding SubAccount instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from SubAccount as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	//日志
	private static final Log log = LogFactory.getLog(SubAccountDAOImpl.class);
}