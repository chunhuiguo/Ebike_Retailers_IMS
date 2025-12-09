package com.luyuan.report.biz;

import java.util.List;

import com.luyuan.action.IFieldValidation;
import com.luyuan.report.entity.PSIDealingMonthlyReport;
import com.luyuan.util.Page;



/**
 * 
 */

public interface MonthlyDealingBiz {

	public List<PSIDealingMonthlyReport> monthlyDealing(IFieldValidation act, Integer dealerId,String type,String accountType);
	
	public List<PSIDealingMonthlyReport> updateReport(Integer shopId,
			String beginDate, String endDate);	
	
	public Boolean validationDate(IFieldValidation act,Integer shopId,String reportYear,String reportMonth);

}