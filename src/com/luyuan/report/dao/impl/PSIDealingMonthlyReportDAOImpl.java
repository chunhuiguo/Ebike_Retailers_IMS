package com.luyuan.report.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.luyuan.action.IFieldValidation;
import com.luyuan.report.dao.PSIDealingMonthlyReportDAO;
import com.luyuan.report.entity.PSIDealingMonthlyReport;
import com.luyuan.report.entity.PSIPDealingMonthlyReport;
import com.luyuan.util.Page;
import com.luyuan.util.PaginateHib;
import com.luyuan.util.SqlCallback;

/**
 * A data access object (DAO) providing persistence and search support for
 * PSIDealingMonthlyReport entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.luyuan.report.entity.PSIDealingMonthlyReport
 * @author MyEclipse Persistence Tools
 */

public class PSIDealingMonthlyReportDAOImpl extends PaginateHib implements PSIDealingMonthlyReportDAO {

	public void save(PSIDealingMonthlyReport transientInstance) {
		log.debug("saving " + className + " instance");
		try {
			if(className.equals("PSIPDealingMonthlyReport"))
				getHibernateTemplate().save(this.pSIDealingMonthlyReport(transientInstance));
			else
				getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	public List<PSIDealingMonthlyReport> monthlyDealing(final IFieldValidation act, final Integer dealerId,final String type){
		log.debug("finding " + className + " instance with dealerId: "
				+ dealerId+" with type:"+type);
		SqlCallback sqlCallback = new SqlCallback() {			
			public void setPageSql(Page page) {	
				page.getAttachSql().append(" and model.dealerId=").append(dealerId);
				if(!type.equals("")){
					page.getAttachSql().append(" and model.type='").append(type).append("'");
				}				
			}
			
		};
		return this.findList(act, sqlCallback);
	}
	
	
	//调用存储过程wml2010-07-18
	//验证该日期的报表是否已做
	public List checkDealingReport(final Integer dealerId,final String reportYear,final String reportMonth) {

		return (List) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						String procName = "{Call sys_checkDealingReport(?,?,?)}";
						SQLQuery query = session.createSQLQuery(procName);
						query.setInteger(0, dealerId);
						query.setString(1, reportYear);
						query.setString(2, reportMonth);
					
						List reportList = query.list();

						return reportList;
					}
				});
	}	
	
	
	//将存储过程中的对象组装成PSIReport表对象
	public PSIDealingMonthlyReport mergeReport(Object[] object){
		PSIDealingMonthlyReport report = new PSIDealingMonthlyReport();
		report.setActualAmount(Double.parseDouble(object[10].toString()));
		report.setDealerId(Integer.parseInt(object[5].toString()));
		report.setDealerShortName(object[6].toString());
		report.setDealingUnitId(Integer.parseInt(object[7].toString()));
		report.setDealingUnitShortName(object[8].toString());
		report.setDueAmount(Double.parseDouble(object[9].toString()));
		report.setFinalBalance(0.00);
		report.setInitialBalance(0.00);
		report.setReportMonth(object[1].toString());
		report.setReportYear(object[0].toString());
		report.setSubAccountId(Integer.parseInt(object[3].toString()));
		report.setSubAccountType(object[4].toString());
		report.setType(object[2].toString());
		return report;
	}
	
	//class inside method
	private PSIPDealingMonthlyReport pSIDealingMonthlyReport(PSIDealingMonthlyReport pSIDealingMonthlyReport) {
		PSIPDealingMonthlyReport pSIPDealingMonthlyReport = new PSIPDealingMonthlyReport();
		
		pSIPDealingMonthlyReport.setId(pSIDealingMonthlyReport.getId());		
		pSIPDealingMonthlyReport.setDealerId(pSIDealingMonthlyReport.getDealerId());
		pSIPDealingMonthlyReport.setDealerShortName(pSIDealingMonthlyReport.getDealerShortName());
		pSIPDealingMonthlyReport.setDealingUnitId(pSIDealingMonthlyReport.getDealingUnitId());
		pSIPDealingMonthlyReport.setDealingUnitShortName(pSIDealingMonthlyReport.getDealingUnitShortName());
		pSIPDealingMonthlyReport.setInitialBalance(pSIDealingMonthlyReport.getInitialBalance());
		pSIPDealingMonthlyReport.setDueAmount(pSIDealingMonthlyReport.getDueAmount());
		pSIPDealingMonthlyReport.setActualAmount(pSIDealingMonthlyReport.getActualAmount());
		pSIPDealingMonthlyReport.setFinalBalance(pSIDealingMonthlyReport.getFinalBalance());
		pSIPDealingMonthlyReport.setReportYear(pSIDealingMonthlyReport.getReportYear());
		pSIPDealingMonthlyReport.setReportMonth(pSIDealingMonthlyReport.getReportMonth());
		pSIPDealingMonthlyReport.setSubAccountId(pSIDealingMonthlyReport.getSubAccountId());
		pSIPDealingMonthlyReport.setSubAccountType(pSIDealingMonthlyReport.getSubAccountType());
		pSIPDealingMonthlyReport.setType(pSIDealingMonthlyReport.getType());
		
		return pSIPDealingMonthlyReport;
	}

	//log
	private static final Log log = LogFactory
	.getLog(PSIDealingMonthlyReportDAOImpl.class);
}