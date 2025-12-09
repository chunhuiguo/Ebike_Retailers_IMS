package com.luyuan.sys.action;

import java.util.List;

import com.luyuan.action.ListAct;
import com.luyuan.sys.biz.ProductDiffBiz;
import com.luyuan.sys.entity.ProductDiff;

public class ProductDiffAct extends ListAct {
	
	public String execute(){
		page.setListAct("productDiff.html");
		if(submit == null) {			
			if(selectList != null)
				selectList.clear();			
			productDiffList = productDiffBiz.findAll(this);
			return "success";
		}
		if(submit.equals("筛选")) {
			submit = null;
			if(selectList != null)
				selectList.clear();			
			productDiffList = productDiffBiz.findAll(this);
			return "success";
		}
		if(submit.equals("添加")) {
			submit = null;			
			return "productAdd";
		}
		return "success";
	}
	
	//pageList
	private List<ProductDiff> productDiffList;	
	public List<ProductDiff> getProductDiffList() {
		return productDiffList;
	}
	
	private List<Integer> selectList;	
	public List<Integer> getSelectList() {
		return selectList;
	}
	public void setSelectList(List<Integer> selectList) {
		this.selectList = selectList;
	}

	//submit
	private String submit;
	public void setSubmit(String submit) {
		this.submit = submit;
	}

	//biz
	private ProductDiffBiz productDiffBiz;
	public void setProductDiffBiz(ProductDiffBiz productDiffBiz) {
		this.productDiffBiz = productDiffBiz;
	}	
}
