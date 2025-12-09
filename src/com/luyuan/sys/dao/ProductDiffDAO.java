package com.luyuan.sys.dao;

import java.util.List;

import com.luyuan.action.IFieldValidation;
import com.luyuan.sys.entity.ProductDiff;

public interface ProductDiffDAO {

	public List<ProductDiff> findAll(final IFieldValidation act);
	
	public abstract ProductDiff findProductDiff(String productCode);
}
