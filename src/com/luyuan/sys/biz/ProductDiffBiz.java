package com.luyuan.sys.biz;

import java.util.List;

import com.luyuan.action.IFieldValidation;
import com.luyuan.sys.entity.ProductDiff;
import com.luyuan.util.Page;

public interface ProductDiffBiz {
	
	public abstract List<ProductDiff> findAll(IFieldValidation act);
	
	public abstract List<ProductDiff> findProductDiff(List<String> productCodeList);
}
