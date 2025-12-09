package com.luyuan.sys.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.luyuan.action.IFieldValidation;
import com.luyuan.sys.dao.PartDiffDAO;
import com.luyuan.sys.entity.PartDiff;
import com.luyuan.util.Page;
import com.luyuan.util.PaginateHib;
import com.luyuan.util.SqlCallback;


public class PartDiffDAOImpl extends PaginateHib implements PartDiffDAO {

	public List<PartDiff> findAll(final IFieldValidation act) {
		log.debug("finding all ProductDiff instances");
		SqlCallback sqlCallback = new SqlCallback(){
			public void setPageSql(Page page) {
				page.getAttachSql().append("");				
			}
		};
		return this.findList(act, sqlCallback);
	}
	
	public PartDiff findPartDiff(String partCode) {
		List<PartDiff> partDiffList = this.findByProperty("partCode", partCode);
		if(partDiffList != null && partDiffList.size() != 0)
			return partDiffList.get(0);
		return null;
	}
	
	//************************************************************************
	private List findByProperty(String propertyName, Object value) {
	      log.debug("finding PartDiff instance with property: " + propertyName
	            + ", value: " + value);
	      try {
	         String queryString = "from PartDiff as model where model." 
	         						+ propertyName + "= ?";
			 return getHibernateTemplate().find(queryString, value);
	      } catch (RuntimeException re) {
	         log.error("find by property name failed", re);
	         throw re;
	      }
		}
	
   //日志
	private static final Log log = LogFactory.getLog(PartDiffDAOImpl.class);
}