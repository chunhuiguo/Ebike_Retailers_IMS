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
import com.luyuan.report.dao.PSIProductMonthlyReportDAO;
import com.luyuan.report.entity.PSIPartMonthlyReport;
import com.luyuan.report.entity.PSIProductMonthlyReport;
import com.luyuan.stock.entity.Inventory;
import com.luyuan.sys.dao.UnitDAO;
import com.luyuan.util.Page;
import com.luyuan.util.PaginateHib;
import com.luyuan.util.SqlCallback;


public class PSIProductMonthlyReportDAOImpl extends PaginateHib implements PSIProductMonthlyReportDAO {
	
	
	public void save(PSIProductMonthlyReport transientInstance) {
		log.debug("saving " + className + " instance");
		try {
			if(className.equals("PSIPartMonthlyReport"))
				getHibernateTemplate().save(this.psiProductMonthlyReportCopy(transientInstance));
			else
				getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	//gch
	public void update(PSIProductMonthlyReport transientInstance) {
		log.debug("updating " + className + " instance");
		try {
			if(className.equals("PSIPartMonthlyReport"))
				getHibernateTemplate().merge(this.psiProductMonthlyReportCopy(transientInstance));
			else
				getHibernateTemplate().merge(transientInstance);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}	
	}
	
	//gch
	public PSIProductMonthlyReport findPSIProductMonthlyReport(String reportYear, String reportMonth, int warehouseId, long productId) {
		log.debug("finding " + className + " instance with reportYear: " + reportYear + " and reportMonth: " 
				+ reportMonth + " and warehouseId: " + warehouseId + " and productId: " + productId);
		try {
			List<PSIProductMonthlyReport> list = getHibernateTemplate().find(
					"from " + className + " as model where model.reportYear='" + reportYear 
					+ "' and model.reportMonth='" + reportMonth + "' and model.warehouseId=" 
					+ warehouseId + " and model.productId=" + productId);
			if(list != null && list.size() != 0)
				return list.get(0);
			return null;
				
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}			
	}
	
	//通过inventoryLast与该月的voucher表进行统计并生成PSIProductMonthlyReport表中的记录
	public void saveReport(String reportYear,String reportMonth,Integer shopId){
		PSIProductMonthlyReport report=new PSIProductMonthlyReport();
		//经销商
		report.setDealerId(shopId);
		report.setDealerShortName(unitDAO.findById(shopId).getShortName());
		report.setFinalQty(0);
	
	}
	

	
	//将存储过程中的对象组装成PSIReport表对象
	public PSIProductMonthlyReport mergeReport(Object[] object){
		PSIProductMonthlyReport report = new PSIProductMonthlyReport();
		report.setDealerId((Integer)object[2]);
		report.setDealerShortName(object[3].toString());
		report.setFinalQty(0);
		report.setInitialQty(0);
		report.setInQty((Integer)object[12]);
		report.setOutQty((Integer)object[14]);
		report.setProductCode(object[9].toString());
		report.setProductColor(object[11].toString());
		report.setProductId(Long.parseLong(object[8].toString()));
		report.setProductName(object[10].toString());
		report.setReportMonth(object[1].toString());
		report.setReportYear(object[0].toString());
		report.setShopId((Integer)object[4]);
		report.setShopShortName(object[5].toString());
		report.setWarehouseId((Integer)object[6]);
		report.setWarehouseName(object[7].toString());
		return report;
	}
	
	
	public List<PSIProductMonthlyReport> monthlySales(IFieldValidation act, final Integer shopId,final Boolean isDealer){
		log.debug("finding " + className + " instance with shopId: "
				+ shopId);
		SqlCallback sqlCallback = new SqlCallback() {
			
			public void setPageSql(Page page) {	
				if(isDealer)
					page.getAttachSql().append(" and model.dealerId=").append(shopId);
				else
					page.getAttachSql().append(" and model.shopId=").append(shopId);
			}
			
		};
		return this.findList(act, sqlCallback);
	}
	

	//调用存储过程wml2010-07-18
	//验证该日期的报表是否已做
	public List checkSalesReport(final Integer shopId,final String reportYear,final String reportMonth) {

		return (List) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						String procName = "{Call sys_checkSalesReport(?,?,?)}";
						SQLQuery query = session.createSQLQuery(procName);
						query.setInteger(0, shopId);
						query.setString(1, reportYear);
						query.setString(2, reportMonth);
					

						List reportList = query.list();

						return reportList;
					}
				});
}
	

	//
	//调用存储过程wml2010-07-10
	//只得到进出，没有期初期末值
	public List getReport(final Integer shopId,final String beginDate,final String endDate) {
		return (List) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						String procName = "{Call sys_MonthlyReport_GetByInventoryBook(?,?,?)}";
						SQLQuery query = session.createSQLQuery(procName);
						query.setInteger(0, shopId);
						query.setString(1, beginDate);
						query.setString(2, endDate);
					

						List reportList = query.list();

						return reportList;
					}
				});
	}	
	
//得到月初库存
	public PSIProductMonthlyReport findMonthBegin(String year, String month,int warehouseId, Long productId) {
		List<PSIProductMonthlyReport> list = getHibernateTemplate().find(
				"from " + className + " as model where model.reportYear='" + year + " 'and model.reportMonth='" + month + 
				"'and model.warehouseId="+warehouseId + "and model.productId="+productId);	
		if(list != null && list.size() != 0)
			return list.get(0);
		else
			return null;
	}
	//查上月月报
	public List<PSIProductMonthlyReport> findLastMonth(String year,
			String month, Integer warehouseId) {
		log.debug("finding " + className + " instance with reportYear: " + year + 
				" and reportMonth: " + month + " and warehouseId: " + warehouseId);
		return getHibernateTemplate().find("from " + className + " as model where model.reportYear='" 
				+ year + "' and model.reportMonth='" + month + "' and model.warehouseId="+warehouseId);
	}
	
//	//获取最后一条
//	public PSIProductMonthlyReport getLast() {
//		String queryString = "from  PSIProductMonthlyReport as p where p.id=(SELECT MAX(id) FROM PSIProductMonthlyReport)";
//		List<PSIProductMonthlyReport> pSIProductMonthlyReportList = getHibernateTemplate()
//				.find(queryString);
//		if (pSIProductMonthlyReportList != null
//				&& pSIProductMonthlyReportList.size() > 0)
//			return pSIProductMonthlyReportList.get(0);
//		else
//			return null;
//	}

	//class inside method
	private PSIPartMonthlyReport psiProductMonthlyReportCopy(PSIProductMonthlyReport psiProductMonthlyReport) {
		PSIPartMonthlyReport psiPartMonthlyReport = new PSIPartMonthlyReport();
		
		psiPartMonthlyReport.setId(psiProductMonthlyReport.getId());
		psiPartMonthlyReport.setReportYear(psiProductMonthlyReport.getReportYear());
		psiPartMonthlyReport.setReportMonth(psiProductMonthlyReport.getReportMonth());
		psiPartMonthlyReport.setDealerId(psiProductMonthlyReport.getDealerId());
		psiPartMonthlyReport.setDealerShortName(psiProductMonthlyReport.getDealerShortName());
		psiPartMonthlyReport.setShopId(psiProductMonthlyReport.getShopId());
		psiPartMonthlyReport.setShopShortName(psiProductMonthlyReport.getShopShortName());
		psiPartMonthlyReport.setWarehouseId(psiProductMonthlyReport.getWarehouseId());
		psiPartMonthlyReport.setWarehouseName(psiProductMonthlyReport.getWarehouseName());
		psiPartMonthlyReport.setProductId(psiProductMonthlyReport.getProductId());
		psiPartMonthlyReport.setProductName(psiProductMonthlyReport.getProductName());
		psiPartMonthlyReport.setProductCode(psiProductMonthlyReport.getProductCode());
		psiPartMonthlyReport.setProductColor(psiProductMonthlyReport.getProductColor());
		psiPartMonthlyReport.setInitialQty(psiProductMonthlyReport.getInitialQty());
		psiPartMonthlyReport.setInQty(psiProductMonthlyReport.getInQty());
		psiPartMonthlyReport.setOutQty(psiProductMonthlyReport.getOutQty());
		psiPartMonthlyReport.setFinalQty(psiProductMonthlyReport.getFinalQty());
		
		return psiPartMonthlyReport;
	}
	

	//dao
	private UnitDAO unitDAO;
	public void setUnitDAO(UnitDAO unitDAO) {
		this.unitDAO = unitDAO;
	}	
	//log
	private static final Log log = LogFactory
	.getLog(PSIProductMonthlyReportDAOImpl.class);
		
}