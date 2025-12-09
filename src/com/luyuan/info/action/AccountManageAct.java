package com.luyuan.info.action;

import java.util.List;

import com.luyuan.action.ListAct;
import com.luyuan.info.biz.AccountBiz;
import com.luyuan.info.biz.SubAccountBiz;
import com.luyuan.info.entity.Account;
import com.luyuan.info.entity.SubAccount;

public class AccountManageAct extends ListAct {
	
	public String accountManage(){
		if (submit == null) {
			page.setListAct("accountManage.html");
			accountList=accountBiz.findAccount(this, (Integer) this.getSession().get("parentId"));
			return "success";
		}

		if(submit.equals("开户")){
			submit=null;
			return "add";
		}
			
		if (submit.equals("启用")) {
			submit = null;

			if (select != null) {
				Account accountSelected = accountList.get(select);
				if (this.validation(accountSelected, true)) {
					accountBiz.update(accountSelected, true);
					return "start";
				}
				select=null;
			}
			return "success";
		}

		if (submit.equals("停用")) {
			submit = null;

			if (select != null) {
				Account accountSelected = accountList.get(select);

				if (this.validation(accountSelected, false)) {

					if (accountBiz.validation(this, accountSelected)) {
						accountBiz.update(accountSelected, false);
						return "stop";
					}
				}
				select=null;
			}
			return "success";
		}
		
		if (submit.equals("查看子账户类型")){
			submit = null;
			return "subAccountTypeDetail";
		}
		
		if (submit.equals("账户明细")) {
			submit = null;
			if (select != null) {
				subAccountList = subAccountBiz.findSubAccount(accountList.get(
						select).getId().intValue());
				unitName = accountList.get(select).getDealingUnitShortName();
				select=null;
				return "subAccountDetail";
			}
			return "success";
		}

		//根据应收应付筛选
		if (submit.equals("筛选")) {
			page.setListAct("accountManage.html");
			if (type.equals("所有类型")) {	
				page.setListAct("accountManage.html");
				accountList=accountBiz.findAccount(this, (Integer) this.getSession().get("parentId"));
			}
			else {	
				page.setListAct("accountManage.html");
				accountList=accountBiz.findAccount(this, (Integer) this.getSession().get("parentId"),type);	
			}
			return "success";
		}

		if (submit.equals("返回")){
			submit = null;
			return "back";
		}	
		return "success";
	}
	
	//method inside act
	//对已选择的账户进行校验是否正在使用
	private boolean validation(Account account, boolean isStart) {
		boolean isError = false;
		if (isStart) {
			if (account.getDisable().equals(true)) {
				this.addFieldError("start", "选择了正在使用的账户！");
				isError = true;
			}

		} else {

			if (account.getDisable().equals(false)) {
				this.addFieldError("stop", "选择了已停用的账户！");
				isError = true;

			}
		}
		if (isError)
			return false;
		else
			return true;
	}

	// page act里的数据page用要get
	private String submit;
	public void setSubmit(String submit) {
		this.submit = submit;
	}
	
	private String type;
	public void setType(String type) {
		this.type = type;
	}
	public String getType() {
		return type;
	}
	
	private List<SubAccount> subAccountList;
	public List<SubAccount> getSubAccountList() {
		return subAccountList;
	}

	private String unitName;
	public String getUnitName() {
		return unitName;
	}

	private List<Account> accountList;
	public void setAccountList(List<Account> accountList) {
		this.accountList = accountList;
	}
	public List<Account> getAccountList() {
		return accountList;
	}
	
	private Integer select;
	public void setSelect(Integer select) {
		this.select = select;
	}
	public Integer getSelect() {
		return select;
	}
	
	private AccountBiz accountBiz;
	public void setAccountBiz(AccountBiz accountBiz) {
		this.accountBiz = accountBiz;
	}
	
	private SubAccountBiz subAccountBiz;	
	public void setSubAccountBiz(SubAccountBiz subAccountBiz) {
		this.subAccountBiz = subAccountBiz;
	}	
}
