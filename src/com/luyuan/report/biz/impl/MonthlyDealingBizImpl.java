package com.luyuan.report.biz.impl;

import java.util.ArrayList;
import java.util.List;

import com.luyuan.action.IFieldValidation;
import com.luyuan.deal.dao.VoucherDAO;
import com.luyuan.deal.entity.Voucher;
import com.luyuan.money.dao.DealingBookDAO;
import com.luyuan.money.dao.DealingDAO;
import com.luyuan.money.entity.Dealing;
import com.luyuan.report.biz.MonthlyDealingBiz;
import com.luyuan.report.dao.DealingLastDAO;
import com.luyuan.report.dao.PSIDealingMonthlyReportDAO;
import com.luyuan.report.entity.DealingLast;
import com.luyuan.report.entity.PSIDealingMonthlyReport;
import com.luyuan.util.GetLastMonth;
import com.luyuan.util.Page;

/**
 * 
 */

public class MonthlyDealingBizImpl implements MonthlyDealingBiz {

	public List<PSIDealingMonthlyReport> monthlyDealing(IFieldValidation act, Integer dealerId, String type, String accountType) {
		if (accountType.equals("整车账户"))
			return pSIDealingMonthlyReportDAO.monthlyDealing(act, dealerId, type);
		else
			return pSIPDealingMonthlyReportDAO.monthlyDealing(act, dealerId, type);
	}

	// 生成报表
	public List<PSIDealingMonthlyReport> updateReport(Integer shopId,
			String beginDate, String endDate) {
		// 存储过程
		List dealingList = dealingBookDAO.getDealingReport(shopId, beginDate,endDate);
		
		// 报表与上月账款
		List<PSIDealingMonthlyReport> reportList = new ArrayList<PSIDealingMonthlyReport>();
		List<DealingLast> dealingLastList = dealingLastDAO.findByDealerId(shopId);
			
		// 定义报表与上月库存标志数组, 两个表均有的记录标志位置1,独有的记录标志位为0
		int[] dealingLast = new int[dealingLastList.size()];
		for (int i = 0; i < dealingLast.length; i++)
			dealingLast[i] = 0;

		// 存储过程中的数据转到reportList中
		if (dealingList != null && dealingList.size() > 0) {
			for (int i = 0; i < dealingList.size(); i++) {
				reportList.add(pSIDealingMonthlyReportDAO
						.mergeReport((Object[]) dealingList.get(i)));
			}
		}

		// 更新初始数据与结余数据
		// 1、更新公共的记录数
		// 月报保存、上月账款结余更新
		for (int i = 0; i < reportList.size(); i++) {
			for (int j = 0; j < dealingLastList.size(); j++) {
				if (reportList.get(i).getSubAccountId().equals(
						dealingLastList.get(j).getSubAccountId())) {
					dealingLast[j] = 1;
					reportList.get(i).setInitialBalance(
							dealingLastList.get(j).getBalance());
					reportList.get(i).setFinalBalance(
							dealingLastList.get(j).getBalance()
									- reportList.get(i).getActualAmount()
									+ reportList.get(i).getDueAmount());
					if (reportList.get(i).getActualAmount() == 0
							&& reportList.get(i).getDueAmount() == 0
							&& reportList.get(i).getInitialBalance() == 0
							&& reportList.get(i).getFinalBalance() == 0)
						;
					else
						pSIDealingMonthlyReportDAO.save(reportList.get(i));

					dealingLastList.get(j).setBalance(reportList.get(i).getFinalBalance());
					dealingLastDAO.update(dealingLastList.get(j));
					break;
				}
			}
		}

		// 2、上个月有余额但月报表里该账户无进账、出账
		// 月报里的记录xx，0，0，xx;上个月账款余额不动
		for (int i = 0; i < dealingLastList.size(); i++) {
			if (dealingLast[i] == 0) {
				PSIDealingMonthlyReport report = new PSIDealingMonthlyReport();
				report.setDealerId(dealingLastList.get(i).getDealerId());
				report.setDealerShortName(dealingLastList.get(i)
						.getDealerShortName());
				report.setDealingUnitId(dealingLastList.get(i)
						.getDealingUnitId());
				report.setDealingUnitShortName(dealingLastList.get(i)
						.getDealingUnitShortName());
				report.setInitialBalance(dealingLastList.get(i).getBalance());
				report.setActualAmount(0.00);
				report.setDueAmount(0.00);
				report.setFinalBalance(dealingLastList.get(i).getBalance());
				report.setType(dealingLastList.get(i).getType());
				report
						.setSubAccountId(dealingLastList.get(i)
								.getSubAccountId());
				report.setSubAccountType(dealingLastList.get(i)
						.getSubAccountType());
				report.setReportYear(beginDate.substring(0, 4));
				report.setReportMonth(beginDate.substring(5, 7));
				if (report.getActualAmount() == 0 && report.getDueAmount() == 0
						&& report.getInitialBalance() == 0
						&& report.getFinalBalance() == 0)
					;
				else {
					pSIDealingMonthlyReportDAO.save(report);
					reportList.add(report);
				}
			}
		}
			
		//**********************************配件***************************************************************
		// 存储过程
		List pdealingList = dealingBookDAO.getPDealingReport(shopId, beginDate,endDate);
		
		// 报表与上月账款
		List<PSIDealingMonthlyReport> reportPList = new ArrayList<PSIDealingMonthlyReport>();
		List<DealingLast> pdealingLastList = pdealingLastDAO.findByDealerId(shopId);		

		// 定义报表与上月库存标志数组, 两个表均有的记录标志位置1,独有的记录标志位为0
		int[] dealingPLast = new int[pdealingLastList.size()];
		for (int i = 0; i < dealingPLast.length; i++)
			dealingPLast[i] = 0;

		// 存储过程中的数据转到reportPList中
		if (pdealingList != null && pdealingList.size() > 0) {
			for (int i = 0; i < pdealingList.size(); i++) {
				reportPList.add(pSIPDealingMonthlyReportDAO.mergeReport((Object[]) pdealingList.get(i)));
			}
		}

		// 更新初始数据与结余数据

		// 1、更新公共的记录数
		// 月报保存、上月账款结余更新
		for (int i = 0; i < reportPList.size(); i++) {
			for (int j = 0; j < pdealingLastList.size(); j++) {
				if (reportPList.get(i).getSubAccountId().equals(
						pdealingLastList.get(j).getSubAccountId())) {
					dealingPLast[j] = 1;
					reportPList.get(i).setInitialBalance(
							pdealingLastList.get(j).getBalance());
					reportPList.get(i).setFinalBalance(
							pdealingLastList.get(j).getBalance()
									- reportPList.get(i).getActualAmount()
									+ reportPList.get(i).getDueAmount());
					pSIPDealingMonthlyReportDAO.save(reportPList.get(i));

					pdealingLastList.get(j).setBalance(
							reportPList.get(i).getFinalBalance());
					pdealingLastDAO.update(pdealingLastList.get(j));
					break;
				}
			}
		}

		// 2、上个月有余额但月报表里该账户无进账、出账
		// 月报里的记录xx，0，0，xx;上个月账款余额不动
		for (int i = 0; i < pdealingLastList.size(); i++) {
			if (dealingPLast[i] == 0) {
				PSIDealingMonthlyReport report = new PSIDealingMonthlyReport();
				report.setDealerId(pdealingLastList.get(i).getDealerId());
				report.setDealerShortName(pdealingLastList.get(i)
						.getDealerShortName());
				report.setDealingUnitId(pdealingLastList.get(i)
						.getDealingUnitId());
				report.setDealingUnitShortName(pdealingLastList.get(i)
						.getDealingUnitShortName());
				report.setInitialBalance(pdealingLastList.get(i).getBalance());
				report.setActualAmount(0.00);
				report.setDueAmount(0.00);
				report.setFinalBalance(pdealingLastList.get(i).getBalance());
				report.setType(pdealingLastList.get(i).getType());
				report.setSubAccountId(pdealingLastList.get(i)
								.getSubAccountId());
				report.setSubAccountType(pdealingLastList.get(i)
						.getSubAccountType());
				report.setReportYear(beginDate.substring(0, 4));
				report.setReportMonth(beginDate.substring(5, 7));
				pSIPDealingMonthlyReportDAO.save(report);

				reportPList.add(report);
			}
		}
		List<PSIDealingMonthlyReport> list = new ArrayList<PSIDealingMonthlyReport>();
		list.addAll(reportList);
		list.addAll(reportPList);
		return list;
	}

	// 校验填写的的时间报表是否已做
	public Boolean validationDate(IFieldValidation act, Integer shopId,
			String reportYear, String reportMonth) {
		Boolean isRight = true;		
		
		//判断输入的月份是否有未记账的单据
		String beginDate=reportYear+reportMonth+"01";
		String endDate=reportYear+reportMonth+"31";
		
//		System.out.println("dealingDAO.findDraft(shopId, beginDate, endDate)"+dealingDAO.findDraft(shopId, beginDate, endDate).size());
		List<Dealing> dealingList=dealingDAO.findDraft(shopId, beginDate, endDate);
		
		if(dealingList!=null&dealingList.size()>0){
			act.addFieldError("date", "还有未处理的账款单据！");
			isRight = false;
			return isRight;
		}
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
		
		List list = pSIDealingMonthlyReportDAO.checkDealingReport(shopId,
				reportYear, reportMonth);
		if (list != null&&list.size()>0) {
			// 判断输入的月份月报是否已做
			System.out.println("list.size"+list.size());
			act.addFieldError("date", "该月的月报已做！");
			isRight = false;
			return isRight;
		} else {
			// 判断输入的月份上月的月报是否已做
			List<String> date = GetLastMonth.lastMonth(reportYear, reportMonth);
			List lastList = pSIDealingMonthlyReportDAO.checkDealingReport(
					shopId, date.get(0), date.get(1));
			if (list == null) {
				act.addFieldError("date", "上月的月报还未做！");
				isRight = false;
				return isRight;
			}
		} 
		
		return isRight;
	}

	// dao
	private PSIDealingMonthlyReportDAO pSIDealingMonthlyReportDAO;
	public void setpSIDealingMonthlyReportDAO(
			PSIDealingMonthlyReportDAO pSIDealingMonthlyReportDAO) {
		this.pSIDealingMonthlyReportDAO = pSIDealingMonthlyReportDAO;
	}
	
	private PSIDealingMonthlyReportDAO pSIPDealingMonthlyReportDAO;
	public void setpSIPDealingMonthlyReportDAO(
			PSIDealingMonthlyReportDAO pSIPDealingMonthlyReportDAO) {
		this.pSIPDealingMonthlyReportDAO = pSIPDealingMonthlyReportDAO;
	}

	private DealingBookDAO dealingBookDAO;
	public void setDealingBookDAO(DealingBookDAO dealingBookDAO) {
		this.dealingBookDAO = dealingBookDAO;
	}

	private DealingLastDAO dealingLastDAO;
	public void setDealingLastDAO(DealingLastDAO dealingLastDAO) {
		this.dealingLastDAO = dealingLastDAO;
	}
	
	private DealingLastDAO pdealingLastDAO;
	public void setPdealingLastDAO(DealingLastDAO pdealingLastDAO) {
		this.pdealingLastDAO = pdealingLastDAO;
	}
	
	private DealingDAO dealingDAO;
	public void setDealingDAO(DealingDAO dealingDAO) {
		this.dealingDAO = dealingDAO;
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