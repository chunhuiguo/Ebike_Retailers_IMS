package com.luyuan.sys.biz.impl;

import java.util.ArrayList;
import java.util.List;

import com.luyuan.action.IFieldValidation;
import com.luyuan.sys.biz.ProductDiffBiz;
import com.luyuan.sys.dao.ProductDiffDAO;
import com.luyuan.sys.entity.ProductDiff;
import com.luyuan.util.Page;




public class ProductDiffBizImpl implements ProductDiffBiz{

	public List<ProductDiff> findAll(IFieldValidation act){
		return productDiffDAO.findAll(act);
	}
	
	public List<ProductDiff> findProductDiff(List<String> productCodeList) {
		List<ProductDiff> productDiffList = new ArrayList<ProductDiff>();
		
		for(int i = 0; i < productCodeList.size(); i++) {
			ProductDiff productDiff = productDiffDAO.findProductDiff(productCodeList.get(i));
			if(productDiff != null)
				productDiffList.add(productDiff);
		}
		
		return productDiffList;
	}

	//dao
	private ProductDiffDAO productDiffDAO;
	public void setProductDiffDAO(ProductDiffDAO productDiffDAO) {
		this.productDiffDAO = productDiffDAO;
	}	
}
