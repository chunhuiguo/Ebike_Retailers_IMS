package com.luyuan.sys.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.luyuan.sys.dao.RoleDAO;
import com.luyuan.sys.entity.Role;


/**
 * 
 * 最终会被注入的RoleDAO，实现DAO接口逻辑
 *
 * @author tom
 */

public class RoleDAOImpl extends HibernateDaoSupport implements RoleDAO {
	
	public List<Role> findByType(String roleType) {
		log.debug("finding Role instance with property: " + roleType);
		try {
			String queryString = "from Role as model where model.roleType='" 
	         						+ roleType +  "' and model.enabled=" + true;
			List<Role> list = getHibernateTemplate().find(queryString);
			return list;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public Role findById(java.lang.Integer id) {
		log.debug("getting Role instance with id: " + id);
		try {
			Role instance = (Role) getHibernateTemplate().get(
					"com.luyuan.sys.entity.Role", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	//************************************************************************

	
	private static final Log log = LogFactory.getLog(RoleDAOImpl.class);

}