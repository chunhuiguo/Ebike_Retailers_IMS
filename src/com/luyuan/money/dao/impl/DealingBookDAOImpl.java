package com.luyuan.money.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.luyuan.action.IFieldValidation;
import com.luyuan.money.dao.DealingBookDAO;
import com.luyuan.money.entity.DealingBook;
import com.luyuan.money.entity.PDealingBook;
import com.luyuan.util.Page;
import com.luyuan.util.PaginateHib;
import com.luyuan.util.SqlCallback;

/**
 * 
 */

public class DealingBookDAOImpl extends PaginateHib implements DealingBookDAO{
	
	public void save(DealingBook dealingBook) {
		log.debug("saving " + className + " instance");
		try {
			if(className.equals("PDealingBook"))
				getHibernateTemplate().save(this.dealingBookCopy(dealingBook));
			else
				getHibernateTemplate().save(dealingBook);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	public List<DealingBook> findBySubAccountId(int subAccountId){
		return findByProperty("subAccountId", subAccountId);
	}
	
	//通过类型、子账户查找台账
	public List<DealingBook> findByType(IFieldValidation act, final int subAccountId,final String type){
		log.debug("finding " + className + " instance with subAccountId: "
				+ subAccountId + " and type: " + type);
		SqlCallback sqlCallback = new SqlCallback() {
			public void setPageSql(Page page) {
				page.getAttachSql().append(" and model.subAccountId=").append(
						subAccountId).append(" and model.type='").append(
								type).append("'");
			}
		};
		return this.findList(act, sqlCallback);
	}
	
	public DealingBook findById(java.lang.Long id) {
		log.debug("getting " + className + " instance with id: " + id);
		try {
			DealingBook instance = (DealingBook) getHibernateTemplate().get(
					"com.luyuan.money.entity." + className, id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	
	//调用存储过程wml
	//2010-07-17
	//只得到应收、实收
	public List getDealingReport(final Integer dealerId,final String beginDate,final String endDate) {

		return (List) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						String procName = "{Call sys_MonthlyMoneyReport_dueAndActual(?,?,?)}";
						SQLQuery query = session.createSQLQuery(procName);
						query.setInteger(0, dealerId);
						query.setString(1, beginDate);
						query.setString(2, endDate);
					

						List reportList = query.list();

						return reportList;
					}
				});
	}	
	
	public List getPDealingReport(final Integer dealerId,final String beginDate,final String endDate) {

		return (List) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						String procName = "{Call sys_MonthlyPMoneyReport_dueAndActual(?,?,?)}";
						SQLQuery query = session.createSQLQuery(procName);
						query.setInteger(0, dealerId);
						query.setString(1, beginDate);
						query.setString(2, endDate);
					

						List reportList = query.list();

						return reportList;
					}
				});
	}
	
	//******************************************************************************
	private List findByProperty(String propertyName, Object value) {
		log.debug("finding " + className + " instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from " + className + " as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	//class inside method
	private PDealingBook dealingBookCopy(DealingBook dealingBook) {
		PDealingBook pdealingBook = new PDealingBook();
		
		pdealingBook.setId(dealingBook.getId());
		pdealingBook.setType(dealingBook.getType());
		pdealingBook.setSubAccountId(dealingBook.getSubAccountId());
		pdealingBook.setSubAccountType(dealingBook.getSubAccountType());
		pdealingBook.setDealerId(dealingBook.getDealerId());
		pdealingBook.setDealerShortName(dealingBook.getDealerShortName());
		pdealingBook.setDealingUnitId(dealingBook.getDealingUnitId());
		pdealingBook.setDealingUnitShortName(dealingBook.getDealingUnitShortName());
		pdealingBook.setVoucherId(dealingBook.getVoucherId());
		pdealingBook.setVoucherCode(dealingBook.getVoucherCode());
		pdealingBook.setVoucherType(dealingBook.getVoucherType());
		pdealingBook.setVoucherDate(dealingBook.getVoucherDate());
		pdealingBook.setDueTotal(dealingBook.getDueTotal());
		pdealingBook.setActualTotal(dealingBook.getActualTotal());
		pdealingBook.setBalance(dealingBook.getBalance());
		pdealingBook.setRemark(dealingBook.getRemark());
		
		return pdealingBook;
	}
	
	//日志
	private static final Log log = LogFactory.getLog(DealingBookDAOImpl.class);	
}