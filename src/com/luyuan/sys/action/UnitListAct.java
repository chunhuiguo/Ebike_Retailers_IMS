/**
 * @(#)UnitAct.java  1.0 10/04/18
 *
 * Copyright 2010 luyuan-ebike, Inc. All rights reserved.
 * 
 */
package com.luyuan.sys.action;

import java.util.List;

import org.springframework.context.ApplicationContext;

import com.luyuan.action.ListAct;
import com.luyuan.sys.biz.UnitBiz;
import com.luyuan.sys.entity.Unit;
import com.luyuan.util.Page;

/**
 * 
 * 往来单位Unit的表现层action
 *
 * @author gch
 */

public class UnitListAct extends ListAct {
	
	public String list() {
		select = null;
		page =  new Page();
		page.setListAct("/sys/unitList!select.html");
		
		this.unitList();
		return "success";
	}
	
	public String select() {
		if(holdUnit())
			return result;
		this.unitList();
		return "success";
	}
	
		
	/**************************************************************************************************/
	/**************************************************************************************************/
	//private method	

	private boolean holdUnit() {
		//翻页
		if( select == null )
			return false;
		//退出，返回
		if( select == -1) {
			unit = null;
			return true;
		}
		//确定
		unit = unitList.get(select);
		return true;
	}
	
	private void unitList() {
		if (unitList != null)	
			unitList.clear();
		
		boolean isDealer = false;
		if(this.getSession().get("unitType").equals("经销商"))
			isDealer = true;
		
		if(result.contains("purchase")) {
			unitList = unitBiz.findSupplier(this, (Integer) this.getSession().get("unitId"));
			return;
		}
		if(result.contains("sale")) {
			unitList = unitBiz.findCustomer(this, (Integer) this.getSession().get("unitId"), isDealer);
			return;
		}
		if(result.contains("transfer")) {
			unitList = unitBiz.findUnit(this, (Integer) this.getSession().get("unitId"), isDealer);
			return;
		}
		if(result.contains("voucherQuery")) {
			unitList = unitBiz.findBtype(this, (Integer) this.getSession().get("unitId"), (String) this.getSession().get("unitType"));
			return;
		}
		if(result.equals("payment")) {
			//通过经销商、单据类型查找往来单位
			unitList = unitBiz.findUnitByDealingType(this, (Integer) this.getSession().get("unitId"), "付款单");
			return;
		}
		if(result.equals("receipt")) {
			//通过经销商、单据类型查找往来单位
			unitList = unitBiz.findUnitByDealingType(this, (Integer) this.getSession().get("unitId"), "收款单");
			return;
		}
		if(result.equals("billQuery") || result.equals("accountAdd")) {
			// 所有可以开户的往来单位，所有的往来单位（不包括停用的）
			unitList = unitBiz.findDealingUnit(this, (Integer) this.getSession().get("unitId"));
			return;
		}
		if(result.contains("inventory")) {
			//所有经销商列表
			unitList = unitBiz.findAllDealer(this);
			return;
		}
		unitList = unitBiz.findDealerAndUnit(this, (Integer) this.getSession().get("unitId"), isDealer);
	}
	
	
	/**************************************************************************************************/
	//page field
	
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
	
	//用来保存选择的unit信息
	//供需要的action获得
	private Unit unit;
	public Unit getUnit() {
		return unit;
	}
	
	//用于接收别的action传过来的参数
	private String result;
	public void setResult(String result) {
		this.result = result;
	}
	
	//spring ApplicationContext
	protected ApplicationContext ctx = this.getAppContext();

	/**************************************************************************************************/
	//biz
	private UnitBiz unitBiz;
	public void setUnitBiz(UnitBiz unitBiz) {
		this.unitBiz = unitBiz;
	}			
}