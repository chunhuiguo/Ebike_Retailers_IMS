package com.luyuan.money.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;

import com.luyuan.action.ListAct;
import com.luyuan.info.biz.AccountBiz;
import com.luyuan.info.biz.AccountManageBiz;
import com.luyuan.info.biz.EmployeeBiz;
import com.luyuan.info.biz.SubAccountBiz;
import com.luyuan.info.entity.Account;
import com.luyuan.info.entity.Employee;
import com.luyuan.info.entity.SubAccount;
import com.luyuan.money.biz.BillBiz;
import com.luyuan.money.entity.Dealing;
import com.luyuan.money.entity.DealingBook;
import com.luyuan.money.entity.DealingDetail;
import com.luyuan.money.entity.PDealingBook;
import com.luyuan.sys.action.UnitListAct;
import com.luyuan.sys.biz.UnitBiz;
import com.luyuan.sys.entity.Unit;

public class BillAct extends ListAct {

	// method inside act
	protected void calculate() {
		if (selectList != null)
			selectList.clear();
		Double total = 0.0;
		for (int i = 0; i < dealingDetailList.size(); i++) {
			if (dealingDetailList.get(i).getTotal() != null)
				total += dealingDetailList.get(i).getTotal();
		}
		dealing.setTotal(total);
	}

	protected boolean save() {
		Employee employee = employeeBiz.findByUserId((Integer) this.getSession().get("userId"));

		if (isNew) {
			Unit unit = unitBiz.findById((Integer) this.getSession().get("unitId"));
			this.calculate();
			dealing.setDealerId((Integer) this.getSession().get("unitId"));
			dealing.setDealerShortName(unit.getShortName());
			dealing.setCreatorName(employee.getName());
			dealing.setCreatorId(employee.getId());
			dealing.setIsChecked(false);
			dealing.setIsError(false);
			dealing.setCreateDate(new SimpleDateFormat("yyyy-MM-dd")
					.format(new Date()));
			if (validationDraft(dealing, dealingDetailList)) {
				billBiz.saveDealing(dealing, dealingDetailList, false);
				return true;
			}
		} else {
			isNew = true;
			dealing.setCreatorName(employee.getName());
			dealing.setCreatorId(employee.getId());
			this.calculate();
			if (validationDraft(dealing, dealingDetailList)) {
				billBiz.updateDealing(dealing, dealingDetailList);
				return true;
			}
		}
		return false;
	}

	protected boolean check() {
		Employee employee = employeeBiz.findByUserId((Integer) this.getSession().get("userId"));
		if (isNew) {
			Unit unit = unitBiz.findById((Integer) this.getSession().get("unitId"));

			dealing.setDealerId((Integer) this.getSession().get("unitId"));
			dealing.setDealerShortName(unit.getShortName());
			dealing.setAccountantName(employee.getName());
			dealing.setAccountantId(employee.getId());
			dealing.setCreatorName(employee.getName());
			dealing.setCreatorId(employee.getId());
			dealing.setIsChecked(true);
			dealing.setCreateDate(new SimpleDateFormat("yyyy-MM-dd")
					.format(new Date()));
			dealing.setIsError(false);

		} else {
			dealing.setAccountantName(employee.getName());
			dealing.setAccountantId(employee.getId());

			dealing.setIsChecked(true);
			dealing.setCreateDate(new SimpleDateFormat("yyyy-MM-dd")
					.format(new Date()));
		}
		this.calculate();
		if (validation(dealing, dealingDetailList)) {
			billBiz.saveCheckDealing(dealing, dealingDetailList, isNew, false);
			return true;
		} else {
			dealing.setIsChecked(false);
			return false;
		}
	}
	
	public String unitListBack() {
		if (selectList != null) {
			selectList.clear();			
		}
		
		UnitListAct unitListAct = (UnitListAct)ctx.getBean("unitListAct");
		Unit unit = unitListAct.getUnit();
		if(unit != null) {
			dealing.setDealingUnitShortName(unit.getShortName());
			dealing.setDealingUnitId(unit.getId());
		}		
		return "success";
	}

	// validation check
	protected boolean validation(Dealing dealing,
			List<DealingDetail> dealingDetailList) {
		boolean isError = false;
		if (dealing.getCheckDate().equals("")) {
			this.addFieldError("dealing.checkDate", "日期不能为空");
			isError = true;
		}
		if (dealing.getDealingUnitId() == null) {
			this.addFieldError("dealing.dealingUnitId", "单位不能为空");
			isError = true;
		}
		if (dealing.getHandlerName().equals("")) {
			this.addFieldError("dealing.handlerName", "经手人不能为空");
			isError = true;
		}
		for (int i = 0; i < dealingDetailList.size(); i++) {
			if (dealingDetailList.get(i).getTotal() == null) {
				this.addFieldError("dealingDetail.total", "金额不能为空");
				isError = true;
			}
		}
		System.out.println("dealing.getCheckDate()"+dealing.getCheckDate());
		if(!billBiz.validationDate((Integer) this.getSession().get("unitId"), dealing.getCheckDate())){
			this.addFieldError("dealing.checkDate", "该月月结已做，请核对结算日期！");
			isError = true;
		}
		
		if (isError)
			return false;
		else
			return true;

	}

	// 校验草稿箱
	protected boolean validationDraft(Dealing dealing,
			List<DealingDetail> dealingDetailList) {
		boolean isError = false;
		if (dealing.getDealingUnitId() == null) {
			this.addFieldError("dealing.dealingUnitId", "单位不能为空！");
			isError = true;
		}

		if (dealingDetailList.size() == 0 || dealingDetailList == null) {
			this.addFieldError("dealingDetailList", "请选择账户！");
			isError = true;
		}
		for (int i = 0; i < dealingDetailList.size(); i++) {
			if (dealingDetailList.get(i).getTotal() == null) {
				this.addFieldError("dealingDetail.total", "金额不能为空！");
				isError = true;
			}
		}
		if (isError)
			return false;
		else
			return true;
	}

	// data inside act
	protected boolean isNew = true;

	// page act里的数据page用要get
	protected String submit;
	public void setSubmit(String submit) {
		this.submit = submit;
	}

	protected String unitName;
	public String getUnitName() {
		return unitName;
	}

	protected String accountType;
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getAccountType() {
		return accountType;
	}

	protected String errorType;
	public String getErrorType() {
		return errorType;
	}
	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}

	protected String type;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	protected Dealing dealing = new Dealing();
	public Dealing getDealing() {
		return dealing;
	}
	public void setDealing(Dealing dealing) {
		this.dealing = dealing;
	}

	protected List<DealingDetail> dealingDetailList = new ArrayList<DealingDetail>();
	public void setDealingDetailList(List<DealingDetail> dealingDetailList) {
		this.dealingDetailList = dealingDetailList;
	}
	public List<DealingDetail> getDealingDetailList() {
		return dealingDetailList;
	}

	protected List<Dealing> dealingList;
	public List<Dealing> getDealingList() {
		return dealingList;
	}
	public void setDealingList(List<Dealing> dealingList) {
		this.dealingList = dealingList;
	}

	protected List<Account> accountList;
	public List<Account> getAccountList() {
		return accountList;
	}

	protected List<SubAccount> subAccountList;
	public List<SubAccount> getSubAccountList() {
		return subAccountList;
	}
	public void setSubAccountList(List<SubAccount> subAccountList) {
		this.subAccountList = subAccountList;
	}

	protected List<DealingBook> dealingBookList;
	public List<DealingBook> getDealingBookList() {
		return dealingBookList;
	}

	protected List<PDealingBook> pdealingBookList;
	public List<PDealingBook> getPdealingBookList() {
		return pdealingBookList;
	}
	public void setPdealingBookList(List<PDealingBook> pdealingBookList) {
		this.pdealingBookList = pdealingBookList;
	}

	protected List<Integer> selectList;
	public void setSelectList(List<Integer> selectList) {
		this.selectList = selectList;
	}
	public List<Integer> getSelectList() {
		return selectList;
	}

	protected Integer select;
	public Integer getSelect() {
		return select;
	}
	public void setSelect(Integer select) {
		this.select = select;
	}
	
	//spring ApplicationContext
	protected ApplicationContext ctx = this.getAppContext();

	// biz
	protected BillBiz billBiz;
	public void setBillBiz(BillBiz billBiz) {
		this.billBiz = billBiz;
	}

	protected SubAccountBiz subAccountBiz;
	public void setSubAccountBiz(SubAccountBiz subAccountBiz) {
		this.subAccountBiz = subAccountBiz;
	}

	protected UnitBiz unitBiz;
	public void setUnitBiz(UnitBiz unitBiz) {
		this.unitBiz = unitBiz;
	}

	protected EmployeeBiz employeeBiz;
	public void setEmployeeBiz(EmployeeBiz employeeBiz) {
		this.employeeBiz = employeeBiz;
	}

	protected AccountManageBiz accountManageBiz;
	public void setAccountManageBiz(AccountManageBiz accountManageBiz) {
		this.accountManageBiz = accountManageBiz;
	}

	protected AccountBiz accountBiz;
	public void setAccountBiz(AccountBiz accountBiz) {
		this.accountBiz = accountBiz;
	}
}
