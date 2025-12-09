package com.luyuan.report.biz;

import java.util.List;

import com.luyuan.action.IFieldValidation;
import com.luyuan.report.entity.PSIProductMonthlyReport;
import com.luyuan.util.Page;


/**
 * 
 */

public interface MonthlySalesBiz {

	public List<PSIProductMonthlyReport> monthlySales(IFieldValidation act, Integer shopId,Boolean isDealer);
	
	public List<PSIProductMonthlyReport> updateReport( Integer shopId, String beginDate, String endDate);
	
	public Boolean validationDate(IFieldValidation act,Integer shopId,String reportYear,String reportMonth);
}