package com.luyuan.money.action;

import java.util.List;

import com.luyuan.action.ListAct;
import com.luyuan.deal.biz.VoucherBiz;
import com.luyuan.deal.entity.Voucher;
import com.luyuan.deal.entity.VoucherDetail;
import com.luyuan.info.biz.AccountBiz;
import com.luyuan.info.biz.SubAccountBiz;
import com.luyuan.info.entity.Account;
import com.luyuan.info.entity.SubAccount;
import com.luyuan.money.biz.BillBiz;
import com.luyuan.money.entity.Dealing;
import com.luyuan.money.entity.DealingBook;
import com.luyuan.money.entity.DealingDetail;
import com.luyuan.util.TypeMap;

public class DueBookAct extends ListAct {

	// *****************************应收*********************************
	public String receivable() {
		if (submit == null) {
			page.setListAct("receivable.html");
			accountList = accountBiz.findAccount(this, (Integer) this.getSession().get("unitId"), "应收");
			return "success";
		}
		if (submit.equals("查看明细")) {
			submit = null;
			if (select != null) {
				subAccountList = subAccountBiz.findSubAccount((int) select);
				for (int i = 0; i < accountList.size(); i++) {
					if (accountList.get(i).getId().equals(select))
						unitName = accountList.get(i).getDealingUnitShortName();
				}
				select = null;
				return "receivableDetail";
			}
			return "success";
		}
		if (submit.equals("查看往来账")) {
			submit = null;
			if (select != null) {
				for (int i = 0; i < subAccountList.size(); i++) {
					if (subAccountList.get(i).getId().equals(select)) {
						if (subAccountList.get(i).getName().endsWith("整车账户")) {
							return "dealingBookList";
						} else {
							return "pDealingBookList";
						}
					}
				}
				select = null;
				
			}
			return "success";
		}

		if (submit.equals("退出")) {
			submit = null;
			return "success";
		}

		return "success";
	}

	// ***********************************整车应收台账***********************************
	public String receivableBook() {
		if (submit == null) {

			if (pdealingBookList != null && pdealingBookList.size() > 0) {
				pdealingBookList.clear();
			}
			page.setListAct("receivableBook.html");
			dealingBookList = billBiz.findDealingBook(this, select,	"应收");
			return "success";
		}
		if (submit.equals("查看单据")) {
			submit = null;
			if (select != null) {
				DealingBook dealingBook = billBiz
						.findDealingBookById(dealingBookList.get(select)
								.getId());
				if (dealingBookList.get(select).getVoucherType().equals("付款单")
						|| dealingBookList.get(select).getVoucherType().equals(
								"收款单")) {
					dealing = billBiz.findDealing(dealingBook.getVoucherId()
							.intValue());
					dealingDetailList = billBiz.findDealingDetail(dealing
							.getId());
					select = null;
					return TypeMap.typeMap.get(dealing.getType());
				} else {
					voucher = voucherBiz.findById(dealingBook.getVoucherId());
					voucherDetailList = voucherBiz.findByVoucherId(dealingBook
							.getVoucherId());
					select = null;
					return TypeMap.typeMap.get(voucher.getType());
				}
			}
			return "success";
		}

		if (submit.equals("退出")) {
			submit = null;
			return "exit";
		}
		return "success";
	}

	// ***********************************配件应收台账***********************************
	public String pReceivableBook() {
		if (submit == null) {
			if (dealingBookList != null && dealingBookList.size() > 0) {
				dealingBookList.clear();
			}
			page.setListAct("pReceivableBook.html");
			pdealingBookList = billBiz.findPDealingBook(this, select, "应收");
			return "success";
		}
		if (submit.equals("查看单据")) {
			submit = null;
			if (select != null) {
				DealingBook pdealingBook = billBiz
						.findPDealingBookById(pdealingBookList.get(select)
								.getId());
				if (pdealingBookList.get(select).getVoucherType().equals("付款单")
						|| pdealingBookList.get(select).getVoucherType()
								.equals("收款单")) {
					dealing = billBiz.findDealing(pdealingBook.getVoucherId()
							.intValue());
					dealingDetailList = billBiz.findDealingDetail(dealing
							.getId());
					select = null;
					return TypeMap.typeMap.get(dealing.getType());
				} else {
					voucher = pvoucherBiz.findById(pdealingBook.getVoucherId());
					voucherDetailList = pvoucherBiz.findByVoucherId(pdealingBook
							.getVoucherId());
					select = null;
					return TypeMap.typeMap.get(voucher.getType());
				}
			}
			return "success";
		}

		if (submit.equals("退出")) {
			submit = null;
			return "exit";
		}
		return "success";

	}

	// *****************************应付*********************************
	public String payable() {
		if (submit == null) {
			page.setListAct("payable.html");
			accountList = accountBiz.findAccount(this, (Integer) this.getSession().get("unitId"), "应付");
			return "success";
		}
		if (submit.equals("查看明细")) {
			submit = null;
			if (select != null) {
				subAccountList = subAccountBiz.findSubAccount((int) select);
				for (int i = 0; i < accountList.size(); i++) {
					if (accountList.get(i).getId().equals(select))
						unitName = accountList.get(i).getDealingUnitShortName();
				}
				select = null;
				return "payableDetail";
			}
			return "success";
		}
		if (submit.equals("查看往来账")) {
			submit = null;
			if (select != null) {
				for (int i = 0; i < subAccountList.size(); i++) {
					if (subAccountList.get(i).getId().equals(select)) {
						if (subAccountList.get(i).getName().endsWith("整车账户")) {
							return "dealingBookList";
						} else {
							return "pDealingBookList";
						}
					}
				}
				select = null;
			}
			return "success";
		}

		if (submit.equals("退出")) {
			submit = null;
			return "success";
		}
		return "success";
	}

	// ***********************************整车应付台账***********************************
	public String payableBook() {
		if (submit == null) {
			if (pdealingBookList != null && pdealingBookList.size() > 0) {
				pdealingBookList.clear();
			}
			page.setListAct("payableBook.html");
			dealingBookList = billBiz.findDealingBook(this, select, "应付");
			return "success";
		}
		if (submit.equals("查看单据")) {
			submit = null;

			if (select != null) {
				DealingBook dealingBook = billBiz
						.findDealingBookById(dealingBookList.get(select)
								.getId());
				if (dealingBookList.get(select).getVoucherType().equals("付款单")
						|| dealingBookList.get(select).getVoucherType().equals(
								"收款单")) {
					dealing = billBiz.findDealing(dealingBook.getVoucherId()
							.intValue());
					dealingDetailList = billBiz.findDealingDetail(dealing
							.getId());
					select = null;
					return TypeMap.typeMap.get(dealing.getType());
				} else {
					voucher = voucherBiz.findById(dealingBook.getVoucherId());
					voucherDetailList = voucherBiz.findByVoucherId(dealingBook
							.getVoucherId());
					select = null;
					return TypeMap.typeMap.get(voucher.getType());
				}
			}
			return "success";
		}

		if (submit.equals("退出")) {
			submit = null;
			return "exit";
		}
		return "success";
	}

	// ***********************************配件应付台账***********************************
	public String pPayableBook() {
		if (submit == null) {
			if (dealingBookList != null && dealingBookList.size() > 0) {
				dealingBookList.clear();
			}
			page.setListAct("pPayableBook.html");
			pdealingBookList = billBiz.findPDealingBook(this, select, "应付");
			return "success";
		}
		if (submit.equals("查看单据")) {
			submit = null;
			if (select != null) {
				System.out.println("select" + select);
				DealingBook pdealingBook = billBiz
						.findPDealingBookById(pdealingBookList.get(select)
								.getId());
				if (pdealingBookList.get(select).getVoucherType().equals("付款单")
						|| pdealingBookList.get(select).getVoucherType()
								.equals("收款单")) {
					dealing = billBiz.findDealing(pdealingBook.getVoucherId()
							.intValue());
					dealingDetailList = billBiz.findDealingDetail(dealing
							.getId());
					select = null;
					return TypeMap.typeMap.get(dealing.getType());
				} else {
					voucher = pvoucherBiz.findById(pdealingBook.getVoucherId());
					voucherDetailList = pvoucherBiz.findByVoucherId(pdealingBook
							.getVoucherId());
					select = null;
					return TypeMap.typeMap.get(voucher.getType());
				}
			}
			return "success";
		}

		if (submit.equals("退出")) {
			submit = null;
			return "exit";
		}
		return "success";

	}

	// page act里的数据page用要get
	private String submit;
	public void setSubmit(String submit) {
		this.submit = submit;
	}

	private String unitName;
	public String getUnitName() {
		return unitName;
	}

	private Dealing dealing;
	public Dealing getDealing() {
		return dealing;
	}

	private Voucher voucher;
	public Voucher getVoucher() {
		return voucher;
	}

	private List<DealingDetail> dealingDetailList;
	public List<DealingDetail> getDealingDetailList() {
		return dealingDetailList;
	}

	private List<VoucherDetail> voucherDetailList;
	public List<VoucherDetail> getVoucherDetailList() {
		return voucherDetailList;
	}

	private List<Account> accountList;
	public List<Account> getAccountList() {
		return accountList;
	}

	private List<SubAccount> subAccountList;
	public List<SubAccount> getSubAccountList() {
		return subAccountList;
	}

	public void setSubAccountList(List<SubAccount> subAccountList) {
		this.subAccountList = subAccountList;
	}

	private List<DealingBook> dealingBookList;

	public List<DealingBook> getDealingBookList() {
		return dealingBookList;
	}

	private List<DealingBook> pdealingBookList;
	public List<DealingBook> getPdealingBookList() {
		return pdealingBookList;
	}
	public void setPdealingBookList(List<DealingBook> pdealingBookList) {
		this.pdealingBookList = pdealingBookList;
	}

	private Integer select;
	public void setSelect(Integer select) {
		this.select = select;
	}

	// biz
	private BillBiz billBiz;
	public void setBillBiz(BillBiz billBiz) {
		this.billBiz = billBiz;
	}

	private VoucherBiz voucherBiz;
	public void setVoucherBiz(VoucherBiz voucherBiz) {
		this.voucherBiz = voucherBiz;
	}
	
	private VoucherBiz pvoucherBiz;
	public void setPvoucherBiz(VoucherBiz pvoucherBiz) {
		this.pvoucherBiz = pvoucherBiz;
	}

	private SubAccountBiz subAccountBiz;
	public void setSubAccountBiz(SubAccountBiz subAccountBiz) {
		this.subAccountBiz = subAccountBiz;
	}

	private AccountBiz accountBiz;
	public void setAccountBiz(AccountBiz accountBiz) {
		this.accountBiz = accountBiz;
	}
}
