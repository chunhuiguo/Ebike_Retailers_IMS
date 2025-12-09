package com.luyuan.deal.dao;

import com.luyuan.deal.entity.VoucherEXT;


public interface VoucherEXTDAO {
	
	public abstract void save(VoucherEXT voucherEXT);
	
	public abstract void deleteByVoucherId(final Long voucherId);
	
	public abstract boolean hasProductBarcode(int dealerId, String productBarcode);
}