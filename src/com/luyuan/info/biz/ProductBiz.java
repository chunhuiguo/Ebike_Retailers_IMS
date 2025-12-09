package com.luyuan.info.biz;

import java.util.List;

import com.luyuan.action.IFieldValidation;
import com.luyuan.deal.entity.VoucherDetail;
import com.luyuan.info.entity.Product;
import com.luyuan.util.Page;

public interface ProductBiz {	
	
	//gch
	public abstract List<Product> findProduct(int dealerId);
	
	public abstract List<Product> findProduct(IFieldValidation act, int dealerId);
	
	public abstract Product findByCode(String code);
	
	public abstract void save(Product product);
	
	public abstract List<Product> findProduct(String [] productBarcodes);
	
	//chq
	public List<Product> findAll();
	public Product findById(Long productId);
	public List<Product> findByDealerId(Integer dealerId);


}