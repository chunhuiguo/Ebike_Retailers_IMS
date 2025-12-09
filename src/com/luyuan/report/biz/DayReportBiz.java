package com.luyuan.report.biz;

import java.util.List;

import com.luyuan.action.IFieldValidation;
import com.luyuan.info.entity.Product;
import com.luyuan.report.action.DayReportAct;
import com.luyuan.report.entity.PSIProductMonthlyReport;
import com.luyuan.stock.entity.InventoryBook;

/**
 * 
 */

public interface DayReportBiz {
	
	public PSIProductMonthlyReport[] findPSIProductMonthlyReport(
			Integer shopId, String beginDate, String endDate);

	public PSIProductMonthlyReport findMonthBegin(String year, String month,
			int warehouseId, Long productId);

	public boolean validation(IFieldValidation act, Integer warehouseId,
			String checkDate);


}