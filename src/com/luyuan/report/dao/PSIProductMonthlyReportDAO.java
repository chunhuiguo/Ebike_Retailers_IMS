package com.luyuan.report.dao;

import java.util.List;

import com.luyuan.action.IFieldValidation;
import com.luyuan.report.entity.PSIProductMonthlyReport;
import com.luyuan.util.Page;

public interface PSIProductMonthlyReportDAO {

	public void save(PSIProductMonthlyReport PSIProductMonthlyReport);
	
	//gch
	public abstract void update(PSIProductMonthlyReport transientInstance);
	
	//gch
	public abstract PSIProductMonthlyReport findPSIProductMonthlyReport(String reportYear, String reportMonth, int warehouseId, long productId);

	public List<PSIProductMonthlyReport> monthlySales(final IFieldValidation act, final Integer shopId,	final Boolean isDealer);
	
	public void saveReport(String reportYear,String reportMonth,Integer shopId);
	
	public PSIProductMonthlyReport mergeReport(Object[] object);
	
	public List checkSalesReport(final Integer shopId,final String reportYear,final String reportMonth);
	
//	public PSIProductMonthlyReport getLast();

	public PSIProductMonthlyReport findMonthBegin(String year, String month,
			int warehouseId, Long productId);
	
	public List getReport(final Integer shopId,final String beginDate,final String endDate) ;
	public List<PSIProductMonthlyReport> findLastMonth(String year,
			String month, Integer warehouseId);

}