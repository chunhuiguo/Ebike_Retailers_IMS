package com.luyuan.info.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;

import com.luyuan.action.BaseAct;
import com.luyuan.info.biz.AccountManageBiz;
import com.luyuan.info.biz.SubAccountBiz;
import com.luyuan.info.biz.SubAccountTypeBiz;
import com.luyuan.info.entity.Account;
import com.luyuan.info.entity.SubAccount;
import com.luyuan.info.entity.SubAccountType;
import com.luyuan.sys.action.UnitListAct;
import com.luyuan.sys.biz.UnitBiz;
import com.luyuan.sys.entity.Unit;
import com.opensymphony.xwork2.ActionContext;

public class AccountAct extends BaseAct {

	public String execute() {
		if (submit == null) {
			
			if(subAccountTypeList!=null){
				subAccountTypeList.clear();
			}
			
			if(subAccountList!=null){
				subAccountList.clear();
			}

			if (isFromMenu == true) {
				account = new Account();
			}
			openAccount = false;
			isFromMenu = true;
			return "success";
		}

		if (submit.equals("选择往来单位")) {
			submit = null;

			openAccount = true;
			isFromMenu = false;
			//所有的减去停用的往来单位
			return "unitDetail";
		}

		if (submit.equals("保存")) {
			submit = null;
			if (this.validationUnit(account)) {

				if (this.validationBalance(subAccountList, selectList)) {

					Unit unit = unitBiz.findById((Integer) this.getSession().get("unitId"));

					account.setDealerId((Integer) this.getSession().get("unitId"));
					account.setDealerShortName(unit.getShortName());
					account.setDisable(true);

					List<SubAccount> newSubAccountList = new ArrayList<SubAccount>();

					for (int j = 0; j < selectList.size(); j++) {
						SubAccount subAccount = new SubAccount();
						subAccount.setDealerId((Integer) this.getSession().get("unitId"));
						subAccount.setDealingUnitId(account.getDealingUnitId());
						subAccount.setType(account.getType());
						subAccount.setDisable(true);
						subAccount.setName(account.getDealingUnitShortName()+subAccountList
								.get(selectList.get(j)).getName());
						subAccount.setBalance(subAccountList.get(
								selectList.get(j)).getBalance());
						newSubAccountList.add(subAccount);
					}

					accountManageBiz.saveAccount(account, newSubAccountList);
					
					account=null;
					if(subAccountList!=null&&subAccountList.size()>0){
						subAccountList.clear();
					}
					if(selectList!=null&&selectList.size()>0){
						selectList.clear();
					}
					return "save";
				}				
			}
			return "success";
		}
		if (submit.equals("选择子账户")) {
			submit = null;
			if (this.validationUnit(account)) {
				//只显示可以添加的类型
				subAccountTypeList = subAccountBiz
						.findAbleOpenAccount((Integer) this.getSession().get("parentId"), account
								.getDealingUnitId(), account.getType());
				if(subAccountTypeList.size() == 0)
					this.addFieldError("subAccount", "子账户已经全部开户！");
			}			
			return "success";
		}
		return "success";
	}
	
	public String unitListBack() {
//		if (selectList != null) {
//			selectList.clear();			
//		}		
		if(subAccountTypeList!=null){
			subAccountTypeList.clear();
		}		
		if(subAccountList!=null){
			subAccountList.clear();
		}
		if (isFromMenu == true) {
			account = new Account();
		}
		openAccount = false;
		isFromMenu = true;
		
		UnitListAct unitListAct = (UnitListAct)ctx.getBean("unitListAct");
		Unit unit = unitListAct.getUnit();
		if(unit != null) {
			account.setDealingUnitShortName(unit.getShortName());
			account.setDealingUnitId(unit.getId());	
		}		
		return "success";
	}

	// 校验
	private boolean validationUnit(Account account) {
		boolean isError = false;
		if (account.getDealingUnitId() == null) {
			this.addFieldError("account.dealingUnitId", "往来单位不能为空");
			isError = true;
		}
		if (isError)
			return false;
		return true;
	}

	private boolean validationBalance(List<SubAccount> subAccountList,
			List<Integer> selectList) {

		if (selectList == null) {
			this.addFieldError("balance", "请选择要添加的子账户！");
			return false;
		}

		List<SubAccount> newSubAccountList = new ArrayList<SubAccount>();

		if (selectList.size() != 0 && selectList != null) {
			for (int j = 0; j < selectList.size(); j++) {
				if (subAccountList.get(selectList.get(j)).getBalance() == null) {
					this.addFieldError("balance", "余额不能为空");
					return false;
				}
			}
		} else {
			this.addFieldError("balance", "请选择要添加的子账户！");
			return false;
		}
		return true;
	}

	// data inside act
	private boolean isFromMenu = true;

	private boolean openAccount = false;

	public void setOpenAccount(boolean openAccount) {
		this.openAccount = openAccount;
	}

	public boolean isOpenAccount() {
		return openAccount;
	}	
	
	//spring ApplicationContext
	private ApplicationContext ctx = this.getAppContext();

	// page act里的数据page用要get
	private String submit;

	public void setSubmit(String submit) {
		this.submit = submit;
	}

	private List<SubAccountType> subAccountTypeList;

	public List<SubAccountType> getSubAccountTypeList() {
		return subAccountTypeList;
	}

	private List<SubAccount> subAccountList;

	public void setSubAccountList(List<SubAccount> subAccountList) {
		this.subAccountList = subAccountList;
	}

	public List<SubAccount> getSubAccountList() {
		return subAccountList;
	}

	private List<Integer> selectList;

	public void setSelectList(List<Integer> selectList) {
		this.selectList = selectList;
	}

	private Account account = new Account();

	public void setAccount(Account account) {
		this.account = account;
	}

	public Account getAccount() {
		return account;
	}

	private SubAccount subAccount;

	public void setSubAccount(SubAccount subAccount) {
		this.subAccount = subAccount;
	}

	public SubAccount getSubAccount() {
		return subAccount;
	}

	// biz
	private SubAccountTypeBiz subAccountTypeBiz;

	public void setSubAccountTypeBiz(SubAccountTypeBiz subAccountTypeBiz) {
		this.subAccountTypeBiz = subAccountTypeBiz;
	}

	private SubAccountBiz subAccountBiz;

	public void setSubAccountBiz(SubAccountBiz subAccountBiz) {
		this.subAccountBiz = subAccountBiz;
	}

	private UnitBiz unitBiz;

	public void setUnitBiz(UnitBiz unitBiz) {
		this.unitBiz = unitBiz;
	}

	private AccountManageBiz accountManageBiz;

	public void setAccountManageBiz(AccountManageBiz accountManageBiz) {
		this.accountManageBiz = accountManageBiz;
	}

}
