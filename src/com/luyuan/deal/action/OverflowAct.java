/**
 * @(#)OverflowAct.java  1.0 10/05/04
 *
 * Copyright 2010 luyuan-ebike, Inc. All rights reserved.
 * 
 */
package com.luyuan.deal.action;

import com.luyuan.deal.entity.VoucherDetail;
import com.luyuan.info.biz.ProductBiz;
import com.luyuan.info.entity.Product;
import com.luyuan.info.entity.Warehouse;
import com.luyuan.sys.biz.UnitBiz;
/**
 * 
 * 报溢单Overflow的表现层action
 *
 * @author gch
 */

public class OverflowAct extends VoucherAct {
	
	/**************************************************************************************************/
	/****报溢单入口****/
	
	/**
	 * 报溢单录入入口：
	 * 不能看到进货价格，没有记账权限
	 */
	public String input() {
		voucher = this.getInitVoucher( type + "报溢单" );
		
		voucher.setDealingUnitId(unitBiz.findById((Integer) this.getSession().get("unitId")).getId());
		voucher.setDealingUnitShortName(unitBiz.findById((Integer) this.getSession().get("unitId")).getShortName());
		
		viewPrice = false;		
		return "success";
	}	
		
	/**
	 * 报溢单记账入口：
	 * 能看到进货价格，有记账权限
	 */
	public String checkInput() {
		voucher = this.getInitVoucher( type + "报溢单" );
		
		voucher.setDealingUnitId(unitBiz.findById((Integer) this.getSession().get("unitId")).getId());
		voucher.setDealingUnitShortName(unitBiz.findById((Integer) this.getSession().get("unitId")).getShortName());
		
		viewPrice = true;		
		return "success";
	}		
	
	
	/**************************************************************************************************/
	/**submit**/	
	public String employeeList()	{	return "employeeList";		}
	
	public String warehouseList()	{	return "warehouseList";		}	
	
//	public String productList()		{	return "productList";		}
	
	public String partList()		{	return "partList";			}

	public String delProduct()		{	return super.delProduct();	}
	
	public String calculate()		{	return super.calculate();	}
	
	public String save() {
		super.save("BY");
		if(viewPrice)
			return "saveCheck";
		else
			return "save";		
	}
	
	public String check() {
		super.saveCheck("BY");
		if(viewPrice)
			return "checkCheck";
		else
			return "check";
	}
	
	public String cancel() {
		voucher = this.getInitVoucher( type + "报溢单" );
		voucher.setDealingUnitId(unitBiz.findById((Integer) this.getSession().get("unitId")).getId());
		voucher.setDealingUnitShortName(unitBiz.findById((Integer) this.getSession().get("unitId")).getShortName());
		return "success";			
	}
	
//	public String print()			{	return "print";				}
	/**end submit**/
	
	
	/**************************************************************************************************/
	/**************************************************************************************************/
	//private method	
	
	/**validate**/	
	
	public String entercode(){
		addcode();
		return "success";
	}
	
	public void validateSave(){		
		validateVoucher();	
	}
	
	public void validateCheck(){		
		validateVoucher();
		validateCalculate();
		
		if(! this.getFieldErrors().toString().equals("{}"))
			return;
		
		//设置shopId，下面校验库存月报是要用到voucher.shopId
		Warehouse warehouse = warehouseBiz.findById(voucher.getWarehouseId());
		voucher.setShopId(warehouse.getShopId());	
		voucher.setShopShortName(warehouse.getShopShortName());
		
		//校验当前月的库存月报是否已经做过，如果做过了就不能再进行记账操作		
		String errorMessage = voucherBiz.validation(this, voucher);
		if(! errorMessage.equals(""))			
			this.addActionError(errorMessage);
	}
	
	private void validateVoucher(){
		if( voucher.getCheckDate().trim().equals("") ) 
			this.addFieldError("voucher.checkDate", "报溢日期不能为空");
		//日期是选择进去的，不会有格式不正确的问题，不用验证
//		else if(voucher.getCheckDate())
//			this.addFieldError("voucher.checkDate", "日期格式不正确(参考：2011-01-23)");
		if( voucher.getHandlerId() == null ) 
			this.addFieldError("voucher.handler", "经手人不能为空");
		if( voucher.getWarehouseId() == null ) 
			this.addFieldError("voucher.warehouse", "入库仓库不能为空");		
	}
	
	
	//biz
	private UnitBiz unitBiz;
	public void setUnitBiz(UnitBiz unitBiz) {
		this.unitBiz = unitBiz;
	}	
}

