package com.luyuan.info.dao;

import java.util.List;

import com.luyuan.action.IFieldValidation;
import com.luyuan.info.entity.Product;
import com.luyuan.util.Page;

public interface ProductDAO {
   
	//gch
	public abstract List<Product> findProduct(int dealerId);
	
	public abstract Product findProduct(String code);
	
	public abstract List<Product> findProduct(final IFieldValidation act, final int dealerId);
	
	public abstract Product findByCode(Object code);
	
	public abstract void save(Product transientInstance);
	
	//chq
	public List<Product> findAll();
	public Product findById(Long productId);
	public List<Product> findByDealerId(Integer dealerId);
}