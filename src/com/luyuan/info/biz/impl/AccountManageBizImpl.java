package com.luyuan.info.biz.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.luyuan.info.biz.AccountManageBiz;
import com.luyuan.info.dao.AccountDAO;
import com.luyuan.info.dao.SubAccountDAO;
import com.luyuan.info.entity.Account;
import com.luyuan.info.entity.SubAccount;
import com.luyuan.report.dao.DealingLastDAO;
import com.luyuan.report.dao.PSIDealingMonthlyReportDAO;
import com.luyuan.report.entity.DealingLast;
import com.luyuan.report.entity.PSIDealingMonthlyReport;
import com.luyuan.util.GetLastMonth;
/**
 * 
 */

public class AccountManageBizImpl implements AccountManageBiz{
		
	//开户，并且在上月账款中添加记录,报表里添加记录
	public void saveAccount(Account account, List<SubAccount> subAccountList) {
		if (accountDAO.findAccount(account.getDealerId(), account
				.getDealingUnitId(), account.getType()) == null) {
			// first account
			account.setBalance(0.0);
			accountDAO.save(account);

			Double balance = 0.0;
			for (int i = 0; i < subAccountList.size(); i++) {
				subAccountList.get(i).setParentAccountId(account.getId());
				subAccountDAO.save(subAccountList.get(i));
				balance += subAccountList.get(i).getBalance();
			}
			account.setBalance(balance);
			accountDAO.update(account);
		} else {
			Account b = accountDAO.findAccount(account.getDealerId(), account
					.getDealingUnitId(), account.getType());

			Double balance = 0.0;
			for (int i = 0; i < subAccountList.size(); i++) {
				subAccountList.get(i).setParentAccountId(b.getId());
				subAccountDAO.save(subAccountList.get(i));
				balance += subAccountList.get(i).getBalance();
			}

			b.setBalance(b.getBalance() + balance);
			accountDAO.update(b);
		}
		
//账款月报初始化
		// 在上月账款中添加记录，报表里添加上月记录
		List<String> lastMonth = GetLastMonth.lastMonth(
				new SimpleDateFormat("yyyy").format(new Date()).toString(),
				new SimpleDateFormat("MM").format(new Date()).toString());
		
		for (int i = 0; i < subAccountList.size(); i++) {
			
			DealingLast dealingLast = new DealingLast();
			dealingLast.setDealerId(account.getDealerId());
			dealingLast.setDealerShortName(account.getDealerShortName());
			dealingLast.setDealingUnitId(account.getDealingUnitId());
			dealingLast.setDealingUnitShortName(account.getDealingUnitShortName());
			dealingLast.setType(account.getType());
			dealingLast.setSubAccountId(subAccountList.get(i).getId());
			dealingLast.setSubAccountType(subAccountList.get(i).getName());
			dealingLast.setBalance(subAccountList.get(i).getBalance());
			
			PSIDealingMonthlyReport report = new PSIDealingMonthlyReport();
			report.setReportMonth(lastMonth.get(1));
			report.setReportYear(lastMonth.get(0));
			report.setDealerId(account.getDealerId());
			report.setDealerShortName(account.getDealerShortName());
			report.setDealingUnitId(account.getDealingUnitId());
			report.setDealingUnitShortName(account.getDealingUnitShortName());
			report.setType(account.getType());
			report.setInitialBalance(subAccountList.get(i).getBalance());
			report.setDueAmount(0.00);
			report.setActualAmount(0.00);
			report.setFinalBalance(subAccountList.get(i).getBalance());
			report.setSubAccountId(subAccountList.get(i).getId());
			report.setSubAccountType(subAccountList.get(i).getName());
			if (subAccountList.get(i).getName().endsWith("整车账户")){				
				dealingLastDAO.save(dealingLast);
				// 报表里添加上月记录								
				pSIDealingMonthlyReportDAO.save(report);
			}else{
				pdealingLastDAO.save(dealingLast);
				// 报表里添加上月记录				
				pSIPDealingMonthlyReportDAO.save(report);
			}
		}
	}
	
	//dao
	private AccountDAO accountDAO;
	public void setAccountDAO(AccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}
	
	private SubAccountDAO subAccountDAO;
	public void setSubAccountDAO(SubAccountDAO subAccountDAO) {
		this.subAccountDAO = subAccountDAO;
	}
	
	private DealingLastDAO  dealingLastDAO;
	public void setDealingLastDAO(DealingLastDAO dealingLastDAO) {
		this.dealingLastDAO = dealingLastDAO;
	}
		
	private DealingLastDAO  pdealingLastDAO;	
	public void setPdealingLastDAO(DealingLastDAO pdealingLastDAO) {
		this.pdealingLastDAO = pdealingLastDAO;
	}

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
}