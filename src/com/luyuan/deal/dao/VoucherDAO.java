package com.luyuan.deal.dao;

import java.util.List;

import com.luyuan.action.IFieldValidation;
import com.luyuan.deal.entity.Voucher;
import com.luyuan.report.model.MonthInventory;
import com.luyuan.util.Page;

public interface VoucherDAO {

	public abstract long save(Voucher transientInstance);
	
	public abstract void update(Voucher transientInstance);
	
	public abstract List<Voucher> findVoucher(final IFieldValidation act, final int shopId, final boolean isCherked, final String startDate, final String endDate, final boolean isDealer);
	
	public abstract List<Voucher> findVoucher(final IFieldValidation act, final int shopId, final boolean isChecked, final String voucherType, final String startDate, final String endDate, final boolean isDealer);
	
	public abstract List<Voucher> findVoucher(final IFieldValidation act, final int shopId, final boolean isChecked, final boolean isError, final String startDate, final String endDate, final boolean isDealer);
	
	public abstract List<Voucher> findVoucher(final IFieldValidation act, final int shopId, final boolean isChecked, final String voucherType, final boolean isError, final String startDate, final String endDate, final boolean isDealer);
	
	public abstract List<Voucher> findVoucher(final IFieldValidation act, final int shopId, final Voucher voucher, final boolean isDealer);
	
	public abstract Voucher findById(java.lang.Long id);
	
	public abstract void deleteById(final List<Long> voucherIdList);
	
	public abstract void evcit(Voucher voucher);
	
	public List<Voucher> findDraft( Integer shopId,  String beginDate,  String endDate);

	public abstract List<Voucher> findDraft(Integer warehouseId,
			String checkDate);
}