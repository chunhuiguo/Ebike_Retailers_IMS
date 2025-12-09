package com.luyuan.info.biz;

import java.util.List;

import com.luyuan.action.IFieldValidation;
import com.luyuan.deal.entity.VoucherDetail;
import com.luyuan.info.entity.Part;
import com.luyuan.info.entity.Product;
import com.luyuan.util.Page;

public interface PartBiz {
	//gch
	public abstract List<Part> findPart(IFieldValidation act, int dealerId);

	public abstract Part findByCode(String code);
	
	public abstract void save(Part part);
	
	//chq
	public List<Part> findAll();
	public Part findById(Long partId);
	public List<Part> findByDealerId(Integer dealerId);
	public List<Part> findPart(Integer dealerId, int unitId);
}
