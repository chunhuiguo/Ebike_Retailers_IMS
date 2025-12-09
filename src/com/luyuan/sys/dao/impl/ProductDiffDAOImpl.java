package com.luyuan.sys.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.luyuan.action.IFieldValidation;
import com.luyuan.sys.dao.ProductDiffDAO;
import com.luyuan.sys.entity.ProductDiff;
import com.luyuan.util.Page;
import com.luyuan.util.PaginateHib;
import com.luyuan.util.SqlCallback;


public class ProductDiffDAOImpl extends PaginateHib implements ProductDiffDAO {

	public List<ProductDiff> findAll(final IFieldValidation act) {
		log.debug("finding all ProductDiff instances");
		SqlCallback sqlCallback = new SqlCallback(){
			public void setPageSql(Page page) {
				page.getAttachSql().append("");				
			}
		};
		return this.findList(act, sqlCallback);
	}
	
	public ProductDiff findProductDiff(String productCode) {
		List<ProductDiff> productDiffList = this.findByProperty("productCode", productCode);
		if(productDiffList != null && productDiffList.size() != 0)
			return productDiffList.get(0);
		return null;
	}
	
	//************************************************************************
	private List findByProperty(String propertyName, Object value) {
	      log.debug("finding ProductDiff instance with property: " + propertyName
	            + ", value: " + value);
	      try {
	         String queryString = "from ProductDiff as model where model." 
	         						+ propertyName + "= ?";
			 return getHibernateTemplate().find(queryString, value);
	      } catch (RuntimeException re) {
	         log.error("find by property name failed", re);
	         throw re;
	      }
		}
	
   //日志
	private static final Log log = LogFactory.getLog(ProductDiffDAOImpl.class);
}