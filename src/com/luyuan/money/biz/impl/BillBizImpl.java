package com.luyuan.money.biz.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.luyuan.action.IFieldValidation;
import com.luyuan.deal.biz.CodeCreatorService;
import com.luyuan.info.dao.AccountDAO;
import com.luyuan.info.dao.EmployeeDAO;
import com.luyuan.info.dao.SubAccountDAO;
import com.luyuan.info.entity.Account;
import com.luyuan.info.entity.Employee;
import com.luyuan.info.entity.SubAccount;
import com.luyuan.money.biz.BillBiz;
import com.luyuan.money.dao.DealingBookDAO;
import com.luyuan.money.dao.DealingDAO;
import com.luyuan.money.dao.DealingDetailDAO;
import com.luyuan.money.entity.Dealing;
import com.luyuan.money.entity.DealingBook;
import com.luyuan.money.entity.DealingDetail;
import com.luyuan.money.entity.PDealingBook;
import com.luyuan.report.dao.PSIDealingMonthlyReportDAO;
import com.luyuan.util.Page;

/**
 * 
 */

public class BillBizImpl implements BillBiz{
	
	//保存收付款单
	public void saveDealing(Dealing dealing,
			List<DealingDetail> dealingDetailList,boolean isError) {
		
		if (!isError) {
			String prefix;
			if (dealing.getType().equals("付款单"))
				prefix = "FK";
			else
				prefix = "SK";
			dealing.setCode(codeCreatorService.createCode(prefix));
		}
		dealingDAO.save(dealing);

		if (dealingDetailList != null && dealingDetailList.size() != 0) {
			Dealing d = dealingDAO.findByExample(dealing);
			Long id = d.getId();
			for (int i = 0; i < dealingDetailList.size(); i++) {
				if (!dealingDetailList.get(i).getSubAccountType().equals("")) {
					dealingDetailList.get(i).setParentDealingId(id);
					dealingDetailDAO.save(dealingDetailList.get(i));
				}
			}
		}
	}
	
	//更新草稿箱
	public void updateDealing (Dealing dealing,
			List<DealingDetail> dealingDetailList){		
		dealingDAO.update(dealing);
		
		Dealing d = (Dealing) dealingDAO.findByExample(dealing);
		Long id = d.getId();
		
		for (int i = 0; i < dealingDetailList.size(); i++) {
			if (!dealingDetailList.get(i).getSubAccountType().equals("")) {
				dealingDetailList.get(i).setParentDealingId(id);
				dealingDetailDAO.saveorupdate(dealingDetailList.get(i));			
			}
		}
		
		List<DealingDetail> dealingDetailOldList = dealingDetailDAO
				.findByParentDealingId(dealing.getId());

		boolean isHad = false;		
		for (int i = 0; i < dealingDetailOldList.size(); i++) {
			for (int j = 0; j < dealingDetailList.size(); j++) {
				if (dealingDetailOldList.get(i).getId().equals(
						dealingDetailList.get(j).getId())) {
					isHad = true;
					break;
				}
			}
			if (!isHad) {
				dealingDetailDAO.delete(dealingDetailOldList.get(i));
			}
			isHad = false;
		}		
	}
	
	//根据经销商与是否过账查找收付款单
	public List<Dealing> findDealing(IFieldValidation act, Integer dealerId,boolean isChecked){
		return dealingDAO.findDealing(act, dealerId,isChecked);
		
	}
	
	//根据经销商、单据类型（收或者付款单）、 是否过账 、是否红冲   查找收付款单
	public List<Dealing> findDealing(IFieldValidation act, Integer dealerId,String type, boolean isChecked,String errorType){
		return dealingDAO.findDealing(act, dealerId,type,isChecked, errorType);
	}
	
	public List<Dealing> findDealing(IFieldValidation act, Integer dealerId,String type, boolean isChecked){
		return dealingDAO.findDealing(act,dealerId,type,isChecked);
	}
	
	//通过dealing的id查找dealing
	public Dealing findDealing(int id){
		return dealingDAO.findById((long)id);
	}

	//收付款单过账
	public void saveCheckDealing(Dealing dealing,
			List<DealingDetail> dealingDetailList, boolean isNew,boolean isError){
		if(isNew){
			saveDealing(dealing, dealingDetailList,isError);
		}else{
			updateDealing(dealing, dealingDetailList);
		}
		
		for (int i = 0; i < dealingDetailList.size(); i++) {			
			//找到所对应的子账户并更新金额
			SubAccount subAccount = subAccountDAO.findById(dealingDetailList.get(i).getSubAccountId().intValue());
		
			Double balance = subAccount.getBalance()- dealingDetailList.get(i).getTotal();
			subAccount.setBalance(balance);
			subAccountDAO.update(subAccount);

			
			DealingBook dealingBook = new DealingBook();
			dealingBook.setBalance(subAccount.getBalance());	
			if (dealing.getType().equals("付款单"))
				dealingBook.setType("应付");
			else
				dealingBook.setType("应收");			
			dealingBook.setDealerId(dealing.getDealerId());
			dealingBook.setDealerShortName(dealing.getDealerShortName());
			dealingBook.setDealingUnitId(dealing.getDealingUnitId());
			dealingBook.setDealingUnitShortName(dealing
					.getDealingUnitShortName());
			dealingBook.setDueTotal(0.0);
			dealingBook.setActualTotal(dealingDetailList.get(i).getTotal());
			dealingBook.setSubAccountId(subAccount.getId());
			dealingBook.setSubAccountType(subAccount.getName());
			dealingBook.setVoucherDate(dealing.getCheckDate());
			dealingBook.setVoucherId(dealing.getId());
			dealingBook.setVoucherCode(dealing.getCode());
			dealingBook.setVoucherType(dealing.getType());
			dealingBook.setRemark(dealing.getRemark());
			
			if (dealingDetailList.get(i).getSubAccountType().equals(dealing.getDealingUnitShortName()+"整车账户")) {
				//更新整车台账				
				dealingBookDAO.save(dealingBook);
			}else{								
				pDealingBookDAO.save(dealingBook);	
			}
		}

		String type;
		if(dealing.getType().equals("付款单"))
			type="应付";
		else
			type="应收";
		
		Account account =accountDAO.findAccount(dealing.getDealerId(),dealing.getDealingUnitId(),type);		
		Double balance = account.getBalance() - dealing.getTotal();
		account.setBalance(balance);
		
		accountDAO.update(account);
	}
	
	// 校验填写的的时间报表是否已做
	public Boolean validationDate(Integer shopId,
			String checkDate) {
		Boolean isRight = true;	
		List list = pSIDealingMonthlyReportDAO.checkDealingReport(shopId,
				checkDate.substring(0, 4), checkDate.substring(5, 7));
		if (list != null&&list.size()!=0) {
			isRight = false;
		}
		return isRight;
	}
	
	//根据dealingId从草稿箱中删除单据
	public void delete(Long id) {
		List<DealingDetail> dealingDetailList = dealingDetailDAO
				.findByParentDealingId(id);
		if (dealingDetailList != null) {
			for (int i = 0; i < dealingDetailList.size(); i++) {
				dealingDetailDAO.delete(dealingDetailList.get(i));
			}
		}
		dealingDAO.delete(id);
	}
	
	//单据冲抵
	public void saveAgainst(Dealing dealing,
			List<DealingDetail> dealingDetailList,Integer userId){
	
		dealing.setIsError(true);
		dealingDAO.update(dealing);
		dealingDAO.evcit(dealing);
		
		Employee employee = employeeDAO.findByUserId(userId);
		
		dealing.setAccountantId(employee.getId());
		dealing.setAccountantName(employee.getName());
		dealing.setCreateDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		dealing.setTotal(-dealing.getTotal());
		
		for(int i = 0; i < dealingDetailList.size(); i++) {
			dealingDetailList.get(i).setTotal(- dealingDetailList.get(i).getTotal());
		}		
		saveCheckDealing(dealing,dealingDetailList,true,true);
	}
	
	public List<Dealing> findDraft( Integer shopId,  String beginDate,  String endDate){
		return dealingDAO.findDraft( shopId, beginDate, endDate);
	}
	
	//查询单据
	public List<Dealing> findDealing(int dealerId, Dealing dealing){
		return dealingDAO.findDealing(dealerId, dealing);
	}
	
	//billQuery界面查询函数
	public  List<Dealing> findDealing(IFieldValidation act, int dealerId, Dealing dealing){
		return dealingDAO.findDealing(act, dealerId, dealing);
	}
		
	//通过dealing的id查找所有的detail
	public List<DealingDetail> findDealingDetail(long parentDealingId){
		return dealingDetailDAO.findByParentDealingId(parentDealingId);
	}
	
	public List<DealingBook> findDealingBook(IFieldValidation act, int subAccountId,String type){
		return dealingBookDAO.findByType(act, subAccountId,type);
	}
	
	public DealingBook findDealingBookById(long id){
		return dealingBookDAO.findById(id);
	}
	
	public List<DealingBook> findPDealingBook(IFieldValidation act, int subAccountId,String type){
		return pDealingBookDAO.findByType(act, subAccountId,type);
	}
	
	public DealingBook findPDealingBookById(long id){
		return pDealingBookDAO.findById(id);
	}
	
	//dao
	private DealingDAO dealingDAO;	
	public void setDealingDAO(DealingDAO dealingDAO) {
		this.dealingDAO = dealingDAO;
	}
	
	private DealingDetailDAO dealingDetailDAO;
	public void setDealingDetailDAO(DealingDetailDAO dealingDetailDAO) {
		this.dealingDetailDAO = dealingDetailDAO;
	}
	
	private SubAccountDAO subAccountDAO;
	public void setSubAccountDAO(SubAccountDAO subAccountDAO) {
		this.subAccountDAO = subAccountDAO;
	}
	
	private AccountDAO accountDAO;
	public void setAccountDAO(AccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}

	private DealingBookDAO dealingBookDAO;
	public void setDealingBookDAO(DealingBookDAO dealingBookDAO) {
		this.dealingBookDAO = dealingBookDAO;
	}

	private DealingBookDAO pDealingBookDAO;
	public void setpDealingBookDAO(DealingBookDAO pDealingBookDAO) {
		this.pDealingBookDAO = pDealingBookDAO;
	}
	
	private EmployeeDAO employeeDAO;
	public void setEmployeeDAO(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}
	
	private PSIDealingMonthlyReportDAO pSIDealingMonthlyReportDAO;
	public void setpSIDealingMonthlyReportDAO(
			PSIDealingMonthlyReportDAO pSIDealingMonthlyReportDAO) {
		this.pSIDealingMonthlyReportDAO = pSIDealingMonthlyReportDAO;
	}

	// service
	private CodeCreatorService codeCreatorService;
	public void setCodeCreatorService(CodeCreatorService codeCreatorService) {
		this.codeCreatorService = codeCreatorService;
	}

}