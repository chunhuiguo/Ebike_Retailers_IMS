package com.luyuan.report.biz.impl;

import java.util.Iterator;
import java.util.List;
import com.luyuan.action.IFieldValidation;
import com.luyuan.deal.dao.VoucherDAO;
import com.luyuan.deal.entity.Voucher;
import com.luyuan.report.biz.DayReportBiz;
import com.luyuan.report.dao.PSIProductMonthlyReportDAO;
import com.luyuan.report.entity.PSIProductMonthlyReport;
import com.luyuan.stock.entity.InventoryBook;

/**
 * 
 */

public class DayReportBizImpl implements DayReportBiz{
	
//  将存储过程得到的list组合成数组
	public PSIProductMonthlyReport[] findPSIProductMonthlyReport(
			Integer shopId, String beginDate, String endDate) {
		List list = pSIProductMonthlyReportDAO.getReport(shopId,beginDate,endDate);
//		System.out.println(list.size());
		PSIProductMonthlyReport[] reports = new PSIProductMonthlyReport[list.size()];
		Object[] op = new Object[17];
		Iterator it = list.iterator(); 
//		System.out.println(reports.length);
		for(int i = 0; i < reports.length; i++){
			op = (Object[]) it.next(); 
			reports[i] = mergeReport(op);
			//System.out.println(i+":" + ops[i].getOpName());
		}
		
		return reports;
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
	
	//从PSI表里得到月初库存
	public PSIProductMonthlyReport findMonthBegin(String year, String month,int warehouseId, Long productId) {
		// TODO Auto-generated method stub
		return pSIProductMonthlyReportDAO.findMonthBegin(year,month,warehouseId,productId);
		
	}
	
    //校验当天是否还有未处理单据，上月月报是否已做
	public boolean validation(IFieldValidation act, Integer warehouseId,
			String checkDate) {
        Boolean isRight = true;
		
		//判断选择的日期是否有未记账的单据
		List<Voucher> voucherList=voucherDAO.findDraft(warehouseId, checkDate);
		if(voucherList!=null&voucherList.size()>0){
			act.addFieldError("date", "还有未处理的整车单据！");
			isRight = false;
		}
		
        String year = "";
		String month = "";
		year = checkDate.substring(0, 4);
		month = String.format("%1$02d", Integer.parseInt(checkDate.substring(5,
				7)) - 1);
		//判断上月的日报是否已做
		List<PSIProductMonthlyReport> pSIProductMonthlyReportList=pSIProductMonthlyReportDAO.findLastMonth(year,month,warehouseId);
		System.out.println(pSIProductMonthlyReportList.size());
		System.out.println("上个月");
		if(pSIProductMonthlyReportList.size()==0){
			act.addFieldError("lastMonth", "上个月月报还未做！");
			isRight = false;
		}
			return isRight;
	}	
	
	private PSIProductMonthlyReportDAO pSIProductMonthlyReportDAO;
	public void setpSIProductMonthlyReportDAO(
			PSIProductMonthlyReportDAO pSIProductMonthlyReportDAO) {
		this.pSIProductMonthlyReportDAO = pSIProductMonthlyReportDAO;
	}
	private VoucherDAO voucherDAO;
	public void setVoucherDAO(VoucherDAO voucherDAO) {
		this.voucherDAO = voucherDAO;
	}

	
}