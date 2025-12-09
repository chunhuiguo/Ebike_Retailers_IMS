/**
 * @(#)UnitAct.java  1.0 10/04/18
 *
 * Copyright 2010 luyuan-ebike, Inc. All rights reserved.
 * 
 */
package com.luyuan.sys.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.luyuan.action.ListAct;
import com.luyuan.sys.biz.UnitBiz;
import com.luyuan.sys.entity.Unit;

/**
 * 
 * 往来单位Unit的表现层action
 *
 * @author gch
 */

public class UnitAct extends ListAct {
	//zsh
	//内部职员基本信息
	public String execute() throws Exception {		
		if(submit == null) {
			page.setListAct("unit.html");
			this.unitList(true);
			select = null;
			return "success";			
		}
		
		if(submit.equals("添加"))
		{
			submit = null;
			return "save";
		}
			
		if(submit.equals("修改"))
		{
			submit = null;
			if(select != null) {
				unit = unitList.get(select);
				return "update";
			}
			return "success";			
		}
		
		if(submit.equals("查询"))
		{
			submit = null;
			return "select";
		}
		
		if(submit.equals("停用"))
		{
			submit = null;
			if(select != null) {
				unit = unitList.get(select);
				unit.setDisable(false);
				unitBiz.update(unit);
				return "stop";
			}
			return "success";
		}
		return "success";
	}
	
	//添加
	public String save(){
		if(submit == null)
			return "success";
		
		if(submit.equals("保存"))
		{
			submit = null;
			//保存代码
			if(this.validation()) {				
				unit.setParentId((Integer) this.getSession().get("parentId"));			
				unit.setParentShortName((String) this.getSession().get("parentShortName"));
				unit.setRegisterDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
				unit.setDatabaseMap("jxc");
				unitBiz.save(unit);
				return "save";
			}
			return "success";
		}
		if(submit.equals("退出")) {
			submit = null;
			return "exit";
		}
		return "success";
	}	

	//修改
	public String update(){
		if(submit == null)
			return "success";
		
		if(submit.equals("保存"))
		{
			submit = null;
			//修改代码
			if(this.validation()) {				
				unitBiz.update(unit);
				return "update";
			}
			return "success";
		}
		if(submit.equals("退出")) {
			submit = null;
			return "exit";
		}
		return "success";
	}
	
	//查询入口
	public String selectInput() {
		unitType = "所有";
		page.setListAct("unitSelectInfoInput.html");
		this.unitList(true);			
		return "success";
	}
	
	//查询
	public String select(){
		page.setListAct("unitSelectInfo.html");
		if(submit == null) {			
			this.unitList(false);
			return "success";
		}
		
		if(submit.equals("筛选"))
		{
			submit = null;
			if(unitType.equals("所有"))
				page.getPropsValueList().set(2, "");
			else
				page.getPropsValueList().set(2, unitType);		
			this.unitList(false);
			return "success";
		}			
		if(submit.equals("修改"))
		{
			submit = null;
			if(select != null) {
				unit = unitList.get(select);
				return "update";
			}
			return "success";
		}		
		if(submit.equals("退出")) {
			submit = null;
			return "exit";
		}

		return "success";
	}
	
		
	/**************************************************************************************************/
	/**************************************************************************************************/
	//private method	
	
	private void unitList(boolean clearInputCondation) {		
		if (unitList != null) {			
			unitList.clear();
		}
		select = null;	
		if(clearInputCondation) {
			if(page.getPropsNameList() != null)
				page.getPropsNameList().clear();
			if(page.getPropsValueList() != null)
				page.getPropsValueList().clear();
		}						
		unitList = unitBiz.findManageUnit(this, (Integer) this.getSession().get("unitId"));		
	}
	
	private boolean validation() {
		boolean isError = false;
		if(unit.getShortName().equals("")) {
			isError = true;
			this.addFieldError("unit.shortName", "请输入往来单位简称");			
		}
		if(unit.getFullName().equals("")) {
			isError = true;
			this.addFieldError("unit.fullName", "请输入往来单位全称");
		}
		if(isError)
			return false;
		return true;
	}	
	
		
	/**
	 * 选中的条目的序号，从0开始
	 */
	private Integer select;	
	public void setSelect(Integer select) {
		this.select = select;
	}
	
	private List<Unit> unitList;	
	public List<Unit> getUnitList() {
		return unitList;
	}	
	
	private String submit;
	public void setSubmit(String submit) {
		this.submit = submit;
	}
	
	private Unit unit;
	public Unit getUnit() {
		return unit;
	}
	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	
	private String unitType;	
	public String getUnitType() {
		return unitType;
	}
	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}

	/**************************************************************************************************/
	//biz
	private UnitBiz unitBiz;
	public void setUnitBiz(UnitBiz unitBiz) {
		this.unitBiz = unitBiz;
	}			
}