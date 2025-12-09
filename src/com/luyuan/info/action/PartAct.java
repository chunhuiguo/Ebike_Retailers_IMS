/**
 * @(#)PartAct.java  1.0 10/05/11
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
import com.luyuan.info.biz.PartBiz;
import com.luyuan.info.entity.Part;
import com.luyuan.stock.action.InventoryIntialAct;
import com.luyuan.stock.entity.Inventory;
import com.luyuan.sys.action.PartDiffAct;
import com.luyuan.sys.entity.PartDiff;

/**
 * 
 * 配件Part的表现层action
 *
 * @author gch
 */

public class PartAct extends ListAct {
	
	//配件添加
	public String partAdd() {		
		PartDiffAct partDiffAct = (PartDiffAct) ctx.getBean("partDiffAct");
		List<PartDiff> partDiffList = partDiffAct.getPartDiffList();	
		selectList = partDiffAct.getSelectList();		
		
		if(selectList != null && selectList.size() != 0) {			
			for(int i = 0; i < selectList.size(); i++) {
				Part part = new Part();
				
				part.setCode(partDiffList.get(selectList.get(i)).getPartCode());
				part.setName(partDiffList.get(selectList.get(i)).getPartName());
				part.setSpecType(partDiffList.get(selectList.get(i)).getSpecType());
				part.setPartColor("");
				part.setUnit(partDiffList.get(selectList.get(i)).getUnit());
				part.setCategoryCode("");
				part.setDealerId(1);
				part.setDealerShortName("绿源");
				part.setDescription("");
				
				partBiz.save(part);
			}
		}
		return "success";
	}
	
	//配件查询
	public String part() {
		if(submit == null) {
			page.setListAct("part.html");	
			this.partList(true);
			return "success";
		}
		if(submit.equals("筛选")) {
			submit = null;
			page.setListAct("part.html");	
			this.partList(false);
			return "success";
		}
		return "success";
	}	
	
	//配件库存初始化
	public String inventoryIntialPartList() {		
		page.setListAct("partSelect.html");	
		this.partList(true);
		
		InventoryIntialAct inventoryIntialAct = (InventoryIntialAct) ctx.getBean("pinventoryIntialAct");
		inventory = inventoryIntialAct.getInventory();
		
		return "success";
	}
	
	//配件选择
	public String partSelect() {		
		if(submit != null) {			
			if(submit.equals("确定")) {
				submit = null;				
				
				if(selectList != null && selectList.size() != 0) {						
					inventory.setProductId(partList.get(selectList.get(0)).getId());
					inventory.setProductCode(partList.get(selectList.get(0)).getCode());						
					inventory.setProductName(partList.get(selectList.get(0)).getName());
					if(partList.get(selectList.get(0)).getPartColor() == null)
						inventory.setProductColor("");
					else
						inventory.setProductColor(partList.get(selectList.get(0)).getPartColor());
					inventory.setProductUnit(partList.get(selectList.get(0)).getUnit());
				}
				return "inventoryIntial";				
			}
			if(submit.equals("筛选")) {
				submit = null;
				page.setListAct("partSelect.html");	
				this.partList(false);
				return "success";
			}
			if(submit.equals("退出")) {
				submit = null;						
				return "inventoryIntial";
			}
		}
		else {
			page.setListAct("partSelect.html");	
			this.partList(false);
			return "success";
		}
		
		return "success";
	}	
	
	//class inside method
	//配件列表
	private void partList(boolean clearInputCondition) {		
		if(partList != null)
			partList.clear();
		if(selectList != null)
			selectList.clear();			
		
		if(clearInputCondition) {			
			if(page.getPropsNameList() != null)
				page.getPropsNameList().clear();
			if(page.getPropsValueList()!= null)
				page.getPropsValueList().clear();
		}
					
		partList = partBiz.findPart(this, (Integer) this.getSession().get("parentId"));		
	}
	
	//class inside data;	
	private Inventory inventory;

	//page
	private List<Part> partList;	
	public List<Part> getPartList() {
		return partList;
	}

	private String submit;
	public void setSubmit(String submit) {
		this.submit = submit;
	}

	//spring ApplicationContext
	private ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext( ServletActionContext.getServletContext());
	
	//no ajax
	private List<Integer> selectList;
	public void setSelectList(List<Integer> selectList) {
		this.selectList = selectList;
	}

	//biz
	private PartBiz partBiz;
	public void setPartBiz(PartBiz partBiz) {
		this.partBiz = partBiz;
	}
}