/**
 * @(#)SaleBackAct.java  1.0 10/05/09
 *
 * Copyright 2010 luyuan-ebike, Inc. All rights reserved.
 * 
 */
package com.luyuan.deal.action;

import com.luyuan.deal.entity.VoucherDetail;
import com.luyuan.info.entity.Product;
import com.luyuan.info.entity.Warehouse;
import com.luyuan.info.biz.ProductBiz;

/**
 * 
 * 销售退货单SaleBack的表现层action
 *
 * @author gch
 */

public class SaleBackAct extends VoucherAct {
	
	/**************************************************************************************************/
	/****销售退货单入口****/
	
	/**
	 * 销售退货单录入入口：
	 * 没有记账权限
	 */
		
	
	public String input() {
		voucher = this.getInitVoucher( type + "销售退货单" );
		
		viewPrice = false;		
		return "success";
	}	
		
	/**
	 * 销售退货单记账入口：
	 * 有记账权限
	 */
	public String checkInput() {
		voucher = this.getInitVoucher( type + "销售退货单" );
		
		viewPrice = true;		
		return "success";
	}		
	
	/**
	 * 选择供货单位后，重定向返回
	 * 选择供货单位的同时，设定供货单位子账户
	 */
	public String unitListBack() {
		return super.unitListBack("XS");	
	}		
	
	
	/**************************************************************************************************/
	/**submit**/
	public String unitList()		{	return "unitList";			}
	
	public String employeeList()	{	return "employeeList";		}
	
	public String warehouseList()	{	return "warehouseList";		}	
	
//	public String productList()		{	return "productList";		}
	
	public String partList()		{	return "partList";			}

	public String delProduct()		{	return super.delProduct();	}
	
	public String calculate()		{	return super.calculate();	}
	
	public String save() {
		super.save("XSTH");
		if(viewPrice)
			return "saveCheck";
		else
			return "save";		
	}
	
	public String check() {
		super.saveCheck("XSTH");
		if(viewPrice)
			return "checkCheck";
		else
			return "check";
	}
	
	public String cancel() {
		voucher = this.getInitVoucher( type + "销售退货单" );
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
			this.addFieldError("voucher.checkDate", "退货日期不能为空");
		//日期是选择进去的，不会有格式不正确的问题，不用验证
//		else if(voucher.getCheckDate())
//			this.addFieldError("voucher.checkDate", "日期格式不正确(参考：2011-01-23)");
		if( voucher.getDealingUnitId() == null ) 
			this.addFieldError("voucher.dealingUnit", "退货单位不能为空");
		if( voucher.getSubAccountId() == null ) 
			this.addFieldError("voucher.subAccount", "账户不能为空购买，请重新选择退货单位来填充账户信息");
		if( voucher.getHandlerId() == null ) 
			this.addFieldError("voucher.handler", "经手人不能为空");
		if( voucher.getWarehouseId() == null ) 
			this.addFieldError("voucher.warehouse", "收货仓库不能为空");		
	}
}

