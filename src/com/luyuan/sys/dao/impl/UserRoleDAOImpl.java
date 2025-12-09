package com.luyuan.sys.dao.impl;


import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.luyuan.sys.dao.UserRoleDAO;
import com.luyuan.sys.entity.UserRole;


/**
 * 
 * 最终会被注入的UserRoleDAO，实现DAO接口逻辑
 *
 * @author tom
 */

public class UserRoleDAOImpl extends HibernateDaoSupport implements UserRoleDAO {
	
	public List<UserRole> findByUserCode(String userCode){
		String queryString = "from UserRole as model where model.id.userCode='" + userCode + "'"; 
		return getHibernateTemplate().find(queryString);
	}
	
	//************************************************************************
	public void save(UserRole userRole){
		log.debug("saving Voucher instance");
		try {
			getHibernateTemplate().save(userRole);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	public void deleteByUserCode(final String userCode) {
		this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String sql = "delete UserRole as model where model.id.userCode='"
					+ userCode + "'";

				Query query = session.createQuery(sql);	
				query.executeUpdate();
				return null;
			}
		});
    }
	
	private static final Log log = LogFactory.getLog(UserRoleDAOImpl.class);
	


}
