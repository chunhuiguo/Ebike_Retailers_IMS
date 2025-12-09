package com.luyuan.sys.dao.impl;

import java.sql.SQLException;
import java.util.List;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.luyuan.sys.dao.OperationDAO;

	/**
	 * 
	 * 最终会被注入的OperationDAO，实现DAO接口逻辑
	 *
	 * @author tom
	 */

public class OperationDAOImpl extends HibernateDaoSupport implements OperationDAO {
		
	//获得可用模块信息
	public List getModelByUser(final String userCode){
		
		return  (List)this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {
				String procName = "{Call sys_OP_GetByUser(?)}";
				SQLQuery query = session.createSQLQuery(procName);
				query.setString(0, userCode); //改成 final
				
				List opList = query.list();

				return opList;
			}
		});	
	}
		
	//************************************************************************
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
	public List setSelfOpcode(final String userCode){
		return (List)this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {
				return  session.createSQLQuery("{Call sys_OP_IsByUserAndOpcode(?)}").setString(0, userCode).list();
			}
		});
	}
	//private static final Log log = LogFactory.getLog(OperationDAOImpl.class);

}