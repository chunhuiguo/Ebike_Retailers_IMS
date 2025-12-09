package com.luyuan.report.dao;

import java.util.List;

import com.luyuan.action.IFieldValidation;
import com.luyuan.report.entity.PSIDealingMonthlyReport;
import com.luyuan.util.Page;


public interface PSIDealingMonthlyReportDAO {
	public void save(PSIDealingMonthlyReport transientInstance);
	
	public PSIDealingMonthlyReport mergeReport(Object[] object);
	
	public List<PSIDealingMonthlyReport> monthlyDealing(IFieldValidation act, Integer dealerId,String type);

	public List checkDealingReport(final Integer shopId,final String reportYear,final String reportMonth);
}