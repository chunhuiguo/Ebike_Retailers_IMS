package com.luyuan.info.dao;

import java.util.List;

import com.luyuan.action.IFieldValidation;
import com.luyuan.info.entity.Part;
import com.luyuan.info.entity.Product;
import com.luyuan.util.Page;

public interface PartDAO {
	//gch
	public abstract List<Part> findPart(final IFieldValidation act, final int dealerId);
	
	public abstract List<Part> findPart(int dealerId, int unitId);
	
	public abstract Part findByCode(String code);
	
	public abstract void save(Part transientInstance);

    //chq
	public List<Part> findAll();
	public Part findById(Long partId);
	public List<Part> findByDealerId(Integer dealerId);
}