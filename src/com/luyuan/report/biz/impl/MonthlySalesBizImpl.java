package com.luyuan.report.biz.impl;

import java.util.ArrayList;
import java.util.List;

import com.luyuan.action.IFieldValidation;
import com.luyuan.deal.dao.VoucherDAO;
import com.luyuan.deal.entity.Voucher;
import com.luyuan.report.biz.MonthlySalesBiz;
import com.luyuan.report.dao.InventoryLastDAO;
import com.luyuan.report.dao.MonthlyReportDAO;
import com.luyuan.report.dao.PSIProductMonthlyReportDAO;
import com.luyuan.report.entity.InventoryLast;
import com.luyuan.report.entity.PSIProductMonthlyReport;
import com.luyuan.sys.dao.UnitDAO;
import com.luyuan.util.GetLastMonth;
import com.luyuan.util.Page;

/**
 * 
 */

public class MonthlySalesBizImpl implements MonthlySalesBiz {

	public List<PSIProductMonthlyReport> monthlySales(IFieldValidation act,	Integer shopId, Boolean isDealer) {
		return pSIProductMonthlyReportDAO.monthlySales(act, shopId,	isDealer);
	}

	// 校验填写的的时间报表是否已做
	public Boolean validationDate(IFieldValidation act, Integer shopId,
			String reportYear, String reportMonth) {
		Boolean isRight = true;
		
		//判断输入的月份是否有未记账的单据
		String beginDate=reportYear+reportMonth+"01";
		String endDate=reportYear+reportMonth+"31";
		List<Voucher> voucherList=voucherDAO.findDraft(shopId, beginDate, endDate);
		if(voucherList!=null&voucherList.size()>0){
			act.addFieldError("date", "还有未处理的整车单据！");
			isRight = false;
			return isRight;
		}
		List<Voucher> pvoucherList=pvoucherDAO.findDraft(shopId, beginDate, endDate);
		if(pvoucherList!=null&pvoucherList.size()>0){
			act.addFieldError("date", "还有未处理的配件单据！");
			isRight = false;
			return isRight;
		}
		
		// 判断输入的月份月报是否已做
		List list = pSIProductMonthlyReportDAO.checkSalesReport(shopId,
				reportYear, reportMonth);
		if (list != null&&list.size()>0) {
			act.addFieldError("date", "该月的月报已做！");
			isRight = false;
			return isRight;
		} else {
			List<String> date = GetLastMonth.lastMonth(reportYear, reportMonth);
			List lastList = pSIProductMonthlyReportDAO.checkSalesReport(shopId,
					date.get(0), date.get(1));
			if (list == null) {
				act.addFieldError("date", "上月的月报还未做！");
				isRight = false;
			}
		}
		return isRight;
	}

	public List<PSIProductMonthlyReport> updateReport(Integer dealerId,
			String beginDate, String endDate) {

		// 存储过程
		List salesList = pSIProductMonthlyReportDAO.getReport(dealerId, beginDate,
				endDate);

		// 报表与上月库存
		List<PSIProductMonthlyReport> reportList = new ArrayList<PSIProductMonthlyReport>();
		List<InventoryLast> inventoryLastList = inventoryLastDAO
				.findByDealerId(dealerId);

		// 定义报表与上月库存标志数组
		// int[] report=new int[salesList.size()];
		int[] inventoryLast = new int[inventoryLastList.size()];
		for (int i = 0; i < inventoryLast.length; i++)
			inventoryLast[i] = 0;

		// 存储过程中的数据转到reportList中
		if (salesList != null && salesList.size() > 0) {
			for (int i = 0; i < salesList.size(); i++) {
				PSIProductMonthlyReport productMonthlyReport = new PSIProductMonthlyReport();
				productMonthlyReport = pSIProductMonthlyReportDAO
						.mergeReport((Object[]) salesList.get(i));
				reportList.add(productMonthlyReport);
			}
		}

		System.out.println("impl" + reportList.size());

		// 更新初始数据与结余数据

		// 1、更新公共的记录数
		// 月报保存、上月库存结余更新
		for (int i = 0; i < reportList.size(); i++) {
			for (int j = 0; j < inventoryLastList.size(); j++) {
				if (inventoryLastList.get(j).getProductCode().equals(
						reportList.get(i).getProductCode())
						&& inventoryLastList.get(j).getWarehouseId().equals(
								reportList.get(i).getWarehouseId())) {
					// 两个表均有的记录标志位置1,独有的记录标志位为0
					inventoryLast[j] = 1;
					// 更新报表里的期初期末值,并保存
					reportList.get(i).setInitialQty(
							inventoryLastList.get(j).getQty());
					reportList.get(i).setFinalQty(
							reportList.get(i).getInitialQty()
									+ reportList.get(i).getInQty()
									- reportList.get(i).getOutQty());
					if (reportList.get(i).getInitialQty() == 0
							&& reportList.get(i).getFinalQty() == 0
							&& reportList.get(i).getInQty() == 0
							&& reportList.get(i).getOutQty() == 0)
						;
					else
						pSIProductMonthlyReportDAO.save(reportList.get(i));
					
					// 更新上月库存中的数量,并更新到数据库中
					inventoryLastList.get(j).setQty(
							reportList.get(i).getFinalQty());
					inventoryLastDAO.update(inventoryLastList.get(j));
					break;
				}

			}
		}

		// 2、上个月有库存但月报表里该产品无销售和无进货的
		// 月报里的记录xx，0，0，xx;上个月库存不动
		for (int i = 0; i < inventoryLast.length; i++) {
			if (inventoryLast[i] == 0) {
				PSIProductMonthlyReport monthlyReport = new PSIProductMonthlyReport();

				monthlyReport.setDealerId(inventoryLastList.get(i)
						.getDealerId());
				monthlyReport.setDealerShortName(inventoryLastList.get(i)
						.getDealerShortName());
				monthlyReport.setFinalQty(inventoryLastList.get(i).getQty());
				monthlyReport.setInitialQty(inventoryLastList.get(i).getQty());
				monthlyReport.setInQty(0);
				monthlyReport.setOutQty(0);
				monthlyReport.setProductCode(inventoryLastList.get(i)
						.getProductCode());
				monthlyReport.setProductColor(inventoryLastList.get(i)
						.getProductColor());
				monthlyReport.setProductId(inventoryLastList.get(i)
						.getProductId());
				monthlyReport.setProductName(inventoryLastList.get(i)
						.getProductName());
				monthlyReport.setReportMonth(beginDate.substring(5, 7));
				monthlyReport.setReportYear(beginDate.substring(0, 4));
				monthlyReport.setShopId(inventoryLastList.get(i).getShopId());
				monthlyReport.setShopShortName(inventoryLastList.get(i)
						.getShopShortName());
				monthlyReport.setWarehouseId(inventoryLastList.get(i)
						.getWarehouseId());
				monthlyReport.setWarehouseName(inventoryLastList.get(i)
						.getWarehouseName());
				// 保存
				if (monthlyReport.getInitialQty() == 0
						&& monthlyReport.getFinalQty() == 0
						&& monthlyReport.getInQty() == 0
						&& monthlyReport.getOutQty() == 0)
					;
				else {
					pSIProductMonthlyReportDAO.save(monthlyReport);
					reportList.add(monthlyReport);
				}

			}
		}

		// //3、上个月库存没有但月报表里该产品有销售和进货的
		// //月报保存，上个月库存添加记录
		// for (int i = 0; i < report.length; i++) {
		// if (report[i] == 0) {
		// reportList.get(i).setInitialQty(0);
		// reportList.get(i).setFinalQty(reportList.get(i).getInQty()
		// - reportList.get(i).getOutQty());
		// pSIProductMonthlyReportDAO.save(reportList.get(i));
		//				
		// InventoryLast newInventoryLast=new InventoryLast();
		// newInventoryLast.setDealerId(reportList.get(i).getDealerId());
		// newInventoryLast.setDealerShortName(reportList.get(i).getDealerShortName());
		// newInventoryLast.setProductCode(reportList.get(i).getProductCode());
		// newInventoryLast.setProductId(reportList.get(i).getProductId());
		// newInventoryLast.setProductName(reportList.get(i).getProductName());
		// newInventoryLast.setQty(reportList.get(i).getFinalQty());
		// newInventoryLast.setShopId(reportList.get(i).getShopId());
		// newInventoryLast.setShopShortName(reportList.get(i).getShopShortName());
		// newInventoryLast.setWarehouseId(reportList.get(i).getWarehouseId());
		// newInventoryLast.setWarehouseName(reportList.get(i).getWarehouseName());
		// newInventoryLast.setProductColor(reportList.get(i).getProductColor());
		//
		// inventoryLastDAO.save(newInventoryLast);
		// }
		// }

		System.out.println("impl2" + reportList.size());
		return reportList;
	}

	// dao
	private PSIProductMonthlyReportDAO pSIProductMonthlyReportDAO;
	public void setpSIProductMonthlyReportDAO(
			PSIProductMonthlyReportDAO pSIProductMonthlyReportDAO) {
		this.pSIProductMonthlyReportDAO = pSIProductMonthlyReportDAO;
	}	

	private UnitDAO unitDAO;
	public void setUnitDAO(UnitDAO unitDAO) {
		this.unitDAO = unitDAO;
	}

	private InventoryLastDAO inventoryLastDAO;
	public void setInventoryLastDAO(InventoryLastDAO inventoryLastDAO) {
		this.inventoryLastDAO = inventoryLastDAO;
	}
	
	private VoucherDAO voucherDAO;
	public void setVoucherDAO(VoucherDAO voucherDAO) {
		this.voucherDAO = voucherDAO;
	}
	
	private VoucherDAO pvoucherDAO;
	public void setPvoucherDAO(VoucherDAO pvoucherDAO) {
		this.pvoucherDAO = pvoucherDAO;
	}

}