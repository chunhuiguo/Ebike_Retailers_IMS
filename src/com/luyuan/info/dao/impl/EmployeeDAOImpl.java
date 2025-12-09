/**
 * @(#)EmployeeDAOImpl.java  1.0 10/04/13
 *
 * Copyright 2010 luyuan-ebike, Inc. All rights reserved.
 * 
 */
package com.luyuan.info.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.luyuan.action.IFieldValidation;
import com.luyuan.info.dao.EmployeeDAO;
import com.luyuan.info.entity.Employee;
import com.luyuan.util.Page;
import com.luyuan.util.PaginateHib;
import com.luyuan.util.SqlCallback;

/**
 * 
 * 最终会被注入的EmployeeDAO，实现DAO接口逻辑
 *
 * @see com.luyuan.info.entity.Employee
 * @author gch
 */

public class EmployeeDAOImpl extends PaginateHib implements EmployeeDAO {
    
	//gch
	public List<Employee> findEmployee(final IFieldValidation act, final int shopId, final boolean isDealer, final boolean viewDisabled) {
		log.debug("finding Employee instance with shopId: " + shopId + " and disable: True");
		SqlCallback sqlCallback = new SqlCallback(){
			public void setPageSql(Page page) {				
				if(isDealer)
					page.getAttachSql().append(" and model.dealerId=").append(shopId);
				else
					page.getAttachSql().append(" and model.shopId=").append(shopId);
				if(! viewDisabled)
					page.getAttachSql().append(" and model.disable=true");
			}
		};
		return this.findList(act, sqlCallback);			
	}
	
	public Employee findByUserId(int userId) {	
		List<Employee> employeeList = this.findByProperty("userId", userId);
		if(employeeList != null && employeeList.size() != 0)
			return this.findByProperty("userId", userId).get(0);
		return null;
	}
	
	//zsh
	public void save(Employee transientInstance) {
        log.debug("saving Employee instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
    public void update(Employee employee){
    	log.debug("updating Employee instance");
        try {
            getHibernateTemplate().update(employee);
            log.debug("update successful");
        } catch (RuntimeException re) {
            log.error("update failed", re);
            throw re;
        }
    }
    
//    public Employee findById( java.lang.Integer id) {
//        log.debug("getting Employee instance with id: " + id);
//        try {
//            Employee instance = (Employee) getHibernateTemplate()
//                    .get("com.luyuan.info.entity.Employee", id);
//            return instance;
//        } catch (RuntimeException re) {
//            log.error("get failed", re);
//            throw re;
//        }
//    }
	
//	public List findAll() {
//		log.debug("finding all Employee instances");
//		try {
//			String queryString = "from Employee";
//		 	return getHibernateTemplate().find(queryString);
//		} catch (RuntimeException re) {
//			log.error("find all failed", re);
//			throw re;
//		}
//	}	
	
	private List<Employee> findByProperty(String propertyName, Object value) {
	      log.debug("finding Employee instance with property: " + propertyName
	            + ", value: " + value);
	      try {
	         String queryString = "from Employee as model where model." 
	         						+ propertyName + "= ?";
			 return getHibernateTemplate().find(queryString, value);
	      } catch (RuntimeException re) {
	         log.error("find by property name failed", re);
	         throw re;
	      }
		}
	
	//日志
	private static final Log log = LogFactory.getLog(EmployeeDAOImpl.class);	
}