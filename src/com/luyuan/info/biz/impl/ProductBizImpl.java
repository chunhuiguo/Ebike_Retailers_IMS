/**
 * @(#)ProductBizImpl.java  1.0 10/04/12
 *
 * Copyright 2010 luyuan-ebike, Inc. All rights reserved.
 * 
 */
package com.luyuan.info.biz.impl;

import java.util.ArrayList;
import java.util.List;

import com.luyuan.action.IFieldValidation;
import com.luyuan.info.biz.ProductBiz;
import com.luyuan.info.dao.ProductDAO;
import com.luyuan.info.entity.Product;
import com.luyuan.sys.dao.UnitDAO;

/**
 * 
 * 商品Product的业务逻辑层biz
 *
 * @author gch
 */

public class ProductBizImpl implements ProductBiz {

	
	//gch
	public List<Product> findProduct(int dealerId) {
		return productDAO.findProduct(dealerId);
	}
	
	public List<Product> findProduct(IFieldValidation act, int dealerId) {
		return productDAO.findProduct(act, dealerId);
	}
	
	public Product findByCode(String code) {
		return productDAO.findByCode(code);
	}
	
	public void save(Product product) {
		productDAO.save(product);
	}
	
	public List<Product> findProduct(String [] productBarcodes) {
		List<Product> productList = new ArrayList<Product>();		
		
		for(String productBarcode : productBarcodes) {
			//去除最后的空行
			if(! productBarcode.equals("\r\n")) {
				//除了第一个，条码前面有"\r\n"（例如“\r\n01666A542P130889”），应该去除
				if(productBarcode.startsWith("\r\n"))
					productBarcode = productBarcode.substring(2, 18);
				
				productList.add(productDAO.findProduct(productBarcode.substring(3, 12)));
			}
		}
		
		return productList;
	}
	
	//chq
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		return productDAO.findAll();
	}	
	
	public Product findById(Long productId) {
		// TODO Auto-generated method stub
		return productDAO.findById(productId);
	}   
	
	public List<Product> findByDealerId(Integer dealerId) {
		// TODO Auto-generated method stub
		return productDAO.findByDealerId(dealerId);
	}
	
	//DAO
	private ProductDAO productDAO;
	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}
	
	private UnitDAO unitDAO;
	public void setUnitDAO(UnitDAO unitDAO) {
		this.unitDAO = unitDAO;
	}	
}
