package com.luyuan.deal.biz;

import java.util.List;

import com.luyuan.action.IFieldValidation;
import com.luyuan.deal.entity.Voucher;
import com.luyuan.deal.entity.VoucherDetail;
import com.luyuan.sys.entity.OrderDetail;
import com.luyuan.sys.entity.PartOrderDetail;
import com.luyuan.util.Page;

public interface VoucherBiz {
	
	/**************************************************************************************************/
	
	public abstract void save(Voucher voucher, List<VoucherDetail> voucherDetailList, String prefix, boolean isError);

	public abstract void update(Voucher voucher, List<VoucherDetail> voucherDetailList);
	
	public abstract void saveCheck(Voucher voucher, List<VoucherDetail> voucherDetailList, List<Long> voucherDetailIdList, String prefix, boolean isNew, boolean isError);
	
	public abstract List<Voucher> findVoucher(IFieldValidation act, int shopId, boolean isChecked, String startDate, String endDate, boolean isDealer);
	
	public abstract List<Voucher> findVoucher(IFieldValidation act, int shopId, boolean isChecked, String voucherType, String startDate, String endDate, boolean isDealer);
	
	public abstract List<Voucher> findVoucher(IFieldValidation act, int shopId, boolean isChecked, boolean isError, String startDate, String endDate, boolean isDealer);
	
	public abstract List<Voucher> findVoucher(IFieldValidation act, int shopId, boolean isChecked, String voucherType, boolean isError, String startDate, String endDate, boolean isDealer);
	
	public abstract List<Voucher> findVoucher(IFieldValidation act, int shopId, Voucher voucher, boolean isDealer);
	
	public abstract Voucher findById(long voucherId);
	
	public abstract List<VoucherDetail> findByVoucherId(long voucherId);
	
	public abstract void delete(List<Long> voucherIdList);
	
	public abstract void saveAgainst(Voucher voucher, List<VoucherDetail> voucherDetailList, int userId);
	
	public abstract String validation(IFieldValidation act, Voucher voucher, List<VoucherDetail> voucherDetailList);
	
	public abstract List<OrderDetail> readShipOrder(int shipOrderId);

	public abstract List<PartOrderDetail> readPartShipOrder(int partShipOrderId);

	public List<Voucher> findDraft( Integer shopId,  String beginDate,  String endDate);
	
	public abstract String validation(IFieldValidation act, Voucher voucher);
}
