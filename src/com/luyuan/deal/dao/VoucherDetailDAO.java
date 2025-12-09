package com.luyuan.deal.dao;

import java.util.List;

import com.luyuan.deal.entity.VoucherDetail;

public interface VoucherDetailDAO {
	
	public abstract void save(VoucherDetail transientInstance);
	
	public abstract void saveOrUpdate(VoucherDetail transientInstance);
	
	public abstract List<VoucherDetail> findByVoucherId(Object voucherId);
	
	public abstract void deleteByVoucherId(final List<Long> voucherIdList);
	
//	public abstract void delete(VoucherDetail persistentInstance);
	
	public abstract void deleteById(final Long voucherDetailId);
}