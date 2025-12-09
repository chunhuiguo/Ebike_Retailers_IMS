package com.luyuan.report.dao.impl;

import java.sql.SQLException;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import com.luyuan.report.dao.MonthlyReportDAO;
import com.luyuan.report.entity.MonthlyReport;
import com.luyuan.util.PaginateHib;

/**
 * 2010-7-7wml
 */

public class MonthlyReportDAOImpl extends PaginateHib implements
		MonthlyReportDAO {

	public void save(MonthlyReport transientInstance) {
		log.debug("saving MonthlyReport instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	
	

	// ****************************************************************************
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding MonthlyReport instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MonthlyReport as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	// log
	private static final Log log = LogFactory
			.getLog(MonthlyReportDAOImpl.class);
}