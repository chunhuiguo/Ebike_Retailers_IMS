/**
 * @(#)UnitAct.java  1.0 10/04/15
 *
 * Copyright 2010 luyuan-ebike, Inc. All rights reserved.
 * 
 */
package com.luyuan.info.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.luyuan.action.ListAct;
import com.luyuan.info.biz.ProductBiz;
import com.luyuan.info.entity.Product;
import com.luyuan.stock.action.InventoryIntialAct;
import com.luyuan.stock.entity.Inventory;
import com.luyuan.sys.action.ProductDiffAct;
import com.luyuan.sys.entity.ProductDiff;

/**
 *
 * @author gch
 */

public class ProductAct extends ListAct {
	
	//整车添加
	public String productAdd() {		
		ProductDiffAct productDiffAct = (ProductDiffAct) ctx.getBean("productDiffAct");
		List<ProductDiff> productDiffList = productDiffAct.getProductDiffList();	
		selectList = productDiffAct.getSelectList();		
		
		if(selectList != null && selectList.size() != 0) {			
			for(int i = 0; i < selectList.size(); i++) {
				Product product = new Product();
				product.setCode(productDiffList.get(selectList.get(i)).getProductCode());				
				product.setPrefixName(productDiffList.get(selectList.get(i)).getPrefixName());				
				product.setSuffixName(productDiffList.get(selectList.get(i)).getSuffixName());				
				product.setModelCode(productDiffList.get(selectList.get(i)).getModelCode());			
				product.setSeriesCode(productDiffList.get(selectList.get(i)).getSeriesCode());				
				product.setSpecCode(productDiffList.get(selectList.get(i)).getSpecCode());				
				product.setColorCode(productDiffList.get(selectList.get(i)).getColorCode());				
				product.setMotorCode(productDiffList.get(selectList.get(i)).getMotorCode());				
				product.setUnit(productDiffList.get(selectList.get(i)).getUnit());				
				product.setBatteryCode(productDiffList.get(selectList.get(i)).getBatteryCode());				
				product.setBatteryQty(productDiffList.get(selectList.get(i)).getBatteryQty());				
				product.setBatteryDesc(productDiffList.get(selectList.get(i)).getBatteryDesc());				
				product.setTyreSize(productDiffList.get(selectList.get(i)).getTyreSize());				
				product.setVoltage(productDiffList.get(selectList.get(i)).getVoltage());				
				product.setControl(productDiffList.get(selectList.get(i)).getControl());				
				product.setColorName(productDiffList.get(selectList.get(i)).getColorName());				
				product.setSpecName(productDiffList.get(selectList.get(i)).getWheelDiameter());				
				product.setCategoryCode(product.getModelCode());				
				product.setBrand("绿源");
				product.setDealerId(1);
				product.setDealerShortName("绿源");
				
				productBiz.save(product);
			}
		}
		return "success";
	}
	
	//整车查询
	public String product() {
		if(submit == null) {			
			page.setListAct("product.html");
			this.productList(true);
			return "success";
		}
		if(submit.equals("筛选")) {
			submit = null;
			page.setListAct("product.html");
			this.productList(false);
			return "success";
		}
		return "success";
	}
	
	//整车库存初始化商品列表
	public String inventoryIntialProductList() {
		page.setListAct("productSelect.html");
		this.productList(true);
		
		InventoryIntialAct inventoryIntialAct = (InventoryIntialAct) ctx.getBean("inventoryIntialAct");
		inventory = inventoryIntialAct.getInventory();
		
		inventoryIntial = true;
		
		return "success";
	}
	
	//商品选择
	public String productSelect() {		
		if(submit != null) {			
			if(submit.equals("确定")) {
				submit = null;				
				
				if(selectList != null && selectList.size() != 0) {						
					inventory.setProductId(productList.get(selectList.get(0)).getId());
					inventory.setProductCode(productList.get(selectList.get(0)).getCode());
					String productname;
					if(productList.get(selectList.get(0)).getPrefixName() == null)
						productname = productList.get(selectList.get(0)).getSuffixName();
					if(productList.get(selectList.get(0)).getSuffixName() == null)
						productname = productList.get(selectList.get(0)).getPrefixName();
					else
						productname = productList.get(selectList.get(0)).getPrefixName() + " " + productList.get(selectList.get(0)).getSuffixName();
					inventory.setProductName(productname);
					if(productList.get(selectList.get(0)).getColorName() == null)
						inventory.setProductColor("");
					else
						inventory.setProductColor(productList.get(selectList.get(0)).getColorName());
					inventory.setProductUnit(productList.get(selectList.get(0)).getUnit());
				}
				return "inventoryIntial";
			}
			if(submit.equals("退出")) {
				submit = null;	
				return "inventoryIntial";
			}
			if(submit.equals("筛选")) {
				submit = null;
				page.setListAct("productSelect.html");
				this.productList(false);
				return "success";
			}
		}
		else {			
			page.setListAct("productSelect.html");
			this.productList(false);
			return "success";
		}
		
		return "success";
	}	
	
	//class inside method
	//商品列表
	private void productList(boolean clearInputCondition) {		
		if(productList != null)
			productList.clear();
		if(selectList != null)
			selectList.clear();	
		
		if(clearInputCondition) {	
			if(page.getPropsNameList() != null)
				page.getPropsNameList().clear();
			if(page.getPropsValueList() != null)
				page.getPropsValueList().clear();
		}
					
		productList = productBiz.findProduct(this, (Integer) this.getSession().get("parentId"));		
	}
	
	//class inside data 

	private Inventory inventory;
	
	//page
	private List<Product> productList;
	public List<Product> getProductList() {
		return productList;
	}
	
	private String submit;
	public void setSubmit(String submit) {
		this.submit = submit;
	}
	
	private boolean inventoryIntial;	
	public boolean isInventoryIntial() {
		return inventoryIntial;
	}

	//spring ApplicationContext
	private ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext( ServletActionContext.getServletContext());
	
	//no ajax
	private List<Integer> selectList;
	public void setSelectList(List<Integer> selectList) {
		this.selectList = selectList;
	}

	//biz
	private ProductBiz productBiz;
	public void setProductBiz(ProductBiz productBiz) {
		this.productBiz = productBiz;
	}
}