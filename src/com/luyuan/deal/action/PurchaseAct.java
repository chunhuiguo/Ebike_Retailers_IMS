/**
 * @(#)PurchaseAct.java  1.0 10/05/03
 *
 * Copyright 2010 luyuan-ebike, Inc. All rights reserved.
 * 
 */
package com.luyuan.deal.action;

import java.util.ArrayList;
import java.util.List;

import com.luyuan.deal.entity.VoucherDetail;
import com.luyuan.info.biz.PartBiz;
import com.luyuan.info.biz.ProductBiz;
import com.luyuan.info.entity.Part;
import com.luyuan.info.entity.Product;
import com.luyuan.info.entity.SubAccount;
import com.luyuan.info.entity.Warehouse;
import com.luyuan.sys.action.PartShipOrderAct;
import com.luyuan.sys.biz.PartDiffBiz;
import com.luyuan.sys.biz.ProductDiffBiz;
import com.luyuan.sys.entity.OrderDetail;
import com.luyuan.sys.entity.PartDiff;
import com.luyuan.sys.entity.PartOrderDetail;
import com.luyuan.sys.entity.PartShipOrder;
import com.luyuan.sys.entity.ProductDiff;
import com.luyuan.sys.entity.ShipOrder;
import com.luyuan.util.SelectHelper;

/**
 * 
 * 进货单Purchase的表现层action
 *
 * @author gch
 */

public class PurchaseAct extends VoucherAct {
	
	/**************************************************************************************************/
	/****进货单入口****/
	
	/**
	 * 进货单录入入口：
	 * 不能看进货价，没有记账权限
	 */
	public String input() {
		voucher = this.getInitVoucher( type + "进货单" );
		
		viewPrice = false;		
		check = false;
		return "success";
	}

	/**
	 * 进货单记账(仓管)入口：
	 * 不能看进货价，有记账权限
	 */
	public String checkNoPriceInput() {
		voucher = this.getInitVoucher( type + "进货单" );
		
		viewPrice = false;
		check = true;
		return "success";
	}
	
	/**
	 * 进货单记账入口：
	 * 看进货价，有记账权限
	 */
	public String checkInput() {
		voucher = this.getInitVoucher( type + "进货单" );
		
		viewPrice = true;		
		check = true;
		return "success";
	}	
	
	/**
	 * 进货单修改入口：
	 */
	public String update() {		
		DraftAct draftAct;
		if(type.equals("整车"))
			draftAct = (DraftAct) ctx.getBean("dealDraftAct");
		else
			draftAct = (DraftAct) ctx.getBean("pdealDraftAct");		
		check = draftAct.isCheck();	
		
		return super.update();
	}
	
	/**
	 * 选择供货单位后，重定向返回
	 * 选择供货单位的同时，设定供货单位子账户
	 */
	public String unitListBack() {
		return super.unitListBack("JH");	
	}	
	
	public String shipOrderListBack() {
		if( select == null )	//未选或选择发货单返回入口异常，不做改变
			return "success";
		
		ShipOrder shipOrder = SelectHelper.selectShipOrder(select);	//选择
		this.setInfo();
		voucher.setShipOrderCode(shipOrder.getShipOrderCode());	
		voucher.setProductBarcodeTxt(shipOrder.getTxtCode().replace(",", "")); //read product barcodes
		this.shipOrder(shipOrder.getShipOrderId());
		
		select = null;
		return "success";
	}
	
	public String partShipOrderListBack() {
		PartShipOrderAct partShipOrderAct = (PartShipOrderAct) ctx.getBean("partShipOrderAct");
		PartShipOrder partShipOrder = partShipOrderAct.getPartShipOrder();
		if(partShipOrder != null) {
			this.setInfo();
			voucher.setShipOrderCode(partShipOrder.getShipOrderCode());
			this.partShipOrder(partShipOrder.getShipOrderId());
		}
		return "success";
	}
	
	
	/**************************************************************************************************/
	/**submit**/
	public String unitList()		{	return "unitList";			}
	
	public String employeeList()	{	return "employeeList";		}
	
	public String warehouseList()	{	return "warehouseList";		}	
	
	public String shipOrderList()	{	return "shipOrderList";	    }
	
	public String partShipOrderList(){  return "partShipOrderList";	}
	
//	public String productList()		{	return "productList";		}
	
	public String partList()		{	return "partList";			}

	public String delProduct()		{	return super.delProduct();	}
	
	public String calculate()		{	return super.calculate();	}
	
	public String importProduct()   {this.product();	return "success";}
	
	public String save() {
		super.save("JH");
		if(viewPrice)
			return "saveCheck";
		else
			return "save";		
	}
	
	public String check() {
		super.saveCheck("JH");
		if(viewPrice)
			return "checkCheck";
		else
			return "check";
	}
	
	public String cancel() {
		voucher = this.getInitVoucher( type + "进货单" );
		return "success";			
	}
	public String entercode(){
		addcode();
		return "success";
	}
	
//	public String print()			{	return "print";				}
	/**end submit**/	
		
//	//整车进货单的提交入口
//	public String execute() {		
//		if(submit.equals("条码扫描")){
//			for (int i=0;i < voucherDetailList.size();i++){
//				if (voucherDetailList.get(i).getProductCode().equals(voucher.getProductcode1()) ){
//					voucherDetailList.get(i).setQty(voucherDetailList.get(i).getQty()+1);
//					return "success";
//				}
//			}
//			Product product = productBiz.findByCode(voucher.getProductcode1());
//			if(product == null) {
//				return "success";				
//			}
//				VoucherDetail voucherDetail = new VoucherDetail();
//				voucherDetail.setProductId(product.getId());
//				voucherDetail.setProductCode(product.getCode());
//				if(product.getPrefixName() == null)
//					voucherDetail.setProductName(product.getSuffixName());
//				if(product.getSuffixName() == null)
//					voucherDetail.setProductName(product.getPrefixName());
//				else
//					voucherDetail.setProductName(product.getPrefixName() + " " + product.getSuffixName());			
//				voucherDetail.setProductColor(product.getColorName());
//				voucherDetail.setProductUnit(product.getUnit());
//				voucherDetail.setQty(1);
//				voucherDetail.setPrice(0.00);
//				voucherDetailList.add(voucherDetail);								
//			//}	//}//}
//			return "success";
//		}
//	}
	
	/**************************************************************************************************/
	/**************************************************************************************************/
	//private method
	
	//选择发货通知单返回设置voucher的基本信息
	private void setInfo() {
		voucherDetailList.clear();
		
		voucher.setDealingUnitId(1);
		voucher.setDealingUnitShortName("绿源");
		SubAccount subAccount = subAccountBiz.findSubAccountForVoucher((Integer) this.getSession().get("parentId"), voucher.getDealingUnitId(), "JH", type);
		if(subAccount != null) {
			voucher.setSubAccountId(subAccount.getId());
			voucher.setSubAccountType(voucher.getDealingUnitShortName() + subAccount.getName());
		}
		else {
			voucher.setSubAccountId(null);
			voucher.setSubAccountType(null);
			this.addFieldError("voucher.subAccountId", voucher.getDealingUnitShortName() + type + "账户不存在或无效，请先开户或启用");
		}		
	}
	
	//2011-02-23 09:44
	//读取整车发货通知单
	private void shipOrder(int shipOrderId){
		productCodeList.clear();
		partCodeList.clear();		
		
		boolean isError = false;//是否有错误提示
		
		List<OrderDetail> orderDetailList = voucherBiz.readShipOrder(shipOrderId);
		if(orderDetailList == null) {
			voucher.setShipOrderCode("");
			return;
		}
		else {
			Product product;
			Part part;
			String errorMessage = "";//错误提示信息						
			boolean isHad = false;//是否已经有该电池，用于电池合并
			
			for(int i = 0; i < orderDetailList.size(); i++) {							
				//整车
				product = productBiz.findByCode(orderDetailList.get(i).getProductCode());
				if(product == null) {
					productCodeList.add(orderDetailList.get(i).getProductCode());
					errorMessage = errorMessage + "商品【" + orderDetailList.get(i).getProductCode() + "】不存在，请先导入商品<br>";
					isError = true;	
				}
				else {
					VoucherDetail voucherDetail = new VoucherDetail();
					voucherDetail.setProductId(product.getId());
					voucherDetail.setProductCode(product.getCode());
					if(product.getPrefixName() == null)
						voucherDetail.setProductName(product.getSuffixName());
					if(product.getSuffixName() == null)
						voucherDetail.setProductName(product.getPrefixName());
					else
						voucherDetail.setProductName(product.getPrefixName() + " " + product.getSuffixName());			
					voucherDetail.setProductColor(product.getColorName());
					voucherDetail.setProductUnit(product.getUnit());
					voucherDetail.setQty(orderDetailList.get(i).getActualQty());
					voucherDetail.setPrice(orderDetailList.get(i).getUnitPrice() - orderDetailList.get(i).getBatteryStdQty() * orderDetailList.get(i).getBatteryPrice());
					voucherDetailList.add(voucherDetail);								
				}	
				
				//标配电池
				if(orderDetailList.get(i).getBatteryQty() != 0) {
					part = partBiz.findByCode(orderDetailList.get(i).getBatteryCode());
					if(part == null) {
						partCodeList.add(orderDetailList.get(i).getBatteryCode());
						errorMessage = errorMessage + "商品【" + orderDetailList.get(i).getBatteryCode() + "】不存在，请先导入商品<br>";
						isError = true;
					}
					else {
						isHad = false;
						for(int j = 0; j < voucherDetailList.size(); j++) {
							if(part.getCode().equals(voucherDetailList.get(j).getProductCode())) {
								voucherDetailList.get(j).setQty(voucherDetailList.get(j).getQty() + orderDetailList.get(i).getBatteryQty());
								isHad = true;
								break;
							}
						}
						if(! isHad) {
							VoucherDetail voucherDetail = new VoucherDetail();
							voucherDetail.setProductId(part.getId());
							voucherDetail.setProductCode(part.getCode());
							voucherDetail.setProductName(part.getName());			
							voucherDetail.setProductColor(part.getPartColor());
							voucherDetail.setProductUnit(part.getUnit());
							voucherDetail.setRemark(part.getSpecType());
							voucherDetail.setQty(orderDetailList.get(i).getBatteryQty());
							voucherDetail.setPrice(orderDetailList.get(i).getBatteryPrice());										
							voucherDetailList.add(voucherDetail);
						}
					}
				}
				
				//换配电池
				if(orderDetailList.get(i).getActualBatteryQty() != 0) {
					part = partBiz.findByCode(orderDetailList.get(i).getActualBatteryCode());
					if(part == null) {
						partCodeList.add(orderDetailList.get(i).getActualBatteryCode());
						errorMessage = errorMessage + "商品【" + orderDetailList.get(i).getActualBatteryCode() + "】不存在，请先导入商品<br>";
						isError = true;
					}
					else {
						isHad = false;
						for(int j = 0; j < voucherDetailList.size(); j++) {
							if(part.getCode().equals(voucherDetailList.get(j).getProductCode())) {
								voucherDetailList.get(j).setQty(voucherDetailList.get(j).getQty() + orderDetailList.get(i).getActualBatteryQty());
								isHad = true;
								break;
							}
						}
						if(! isHad) {
							VoucherDetail voucherDetail = new VoucherDetail();
							voucherDetail.setProductId(part.getId());
							voucherDetail.setProductCode(part.getCode());
							voucherDetail.setProductName(part.getName());			
							voucherDetail.setProductColor(part.getPartColor());
							voucherDetail.setProductUnit(part.getUnit());
							voucherDetail.setRemark(part.getSpecType());
							voucherDetail.setQty(orderDetailList.get(i).getActualBatteryQty());
							voucherDetail.setPrice(orderDetailList.get(i).getActualBatteryPrice());										
							voucherDetailList.add(voucherDetail);
						}
					}
				}						
			}
			
			if(isError)
				this.addFieldError("voucher.product", errorMessage);
		}
	}
	
	//读取配件发货通知单
	private void partShipOrder(int partShipOrderId) {
		partCodeList.clear();
		
		boolean isError = false;//是否有错误提示		
		
		List<PartOrderDetail> partOrderDetailList = voucherBiz.readPartShipOrder(partShipOrderId);					
		if(partOrderDetailList == null) {
			voucher.setShipOrderCode("");
			return;
		}
		else {						
			Part part;
			String errorMessage = "";//错误提示信息										
			
			for(int i = 0; i < partOrderDetailList.size(); i++) {						
				//整车
				part = partBiz.findByCode(partOrderDetailList.get(i).getDestCode());
				if(part == null) {
					partCodeList.add(partOrderDetailList.get(i).getDestCode());
					errorMessage = errorMessage + "商品【" + partOrderDetailList.get(i).getDestCode() + "】不存在，请先导入商品<br>";
					isError = true;	
				}
				else {
					VoucherDetail voucherDetail = new VoucherDetail();
					voucherDetail.setProductId(part.getId());
					voucherDetail.setProductCode(part.getCode());
					voucherDetail.setProductName(part.getName());											
					voucherDetail.setProductColor(part.getPartColor());
					voucherDetail.setProductUnit(part.getUnit());
					voucherDetail.setQty(partOrderDetailList.get(i).getActualQty().intValue());
					voucherDetail.setPrice(partOrderDetailList.get(i).getDestPrice());
					voucherDetailList.add(voucherDetail);								
				}
			}
			
			if(isError)
				this.addFieldError("voucher.product", errorMessage);
		}
	}
	
	private void product() {
		if(productCodeList != null && productCodeList.size() != 0) {
			List<String> productCodeNoRepeatedList = new ArrayList<String>();
			for(int i = 0; i < productCodeList.size(); i++) {
				if(! productCodeNoRepeatedList.contains(productCodeList.get(i)))
					productCodeNoRepeatedList.add(productCodeList.get(i));
			}
			
			List<ProductDiff> productDiffList = productDiffBiz.findProductDiff(productCodeNoRepeatedList);				
			for(int i = 0; i < productDiffList.size(); i++) {
				Product product = new Product();
				product.setCode(productDiffList.get(i).getProductCode());				
				product.setPrefixName(productDiffList.get(i).getPrefixName());				
				product.setSuffixName(productDiffList.get(i).getSuffixName());				
				product.setModelCode(productDiffList.get(i).getModelCode());			
				product.setSeriesCode(productDiffList.get(i).getSeriesCode());				
				product.setSpecCode(productDiffList.get(i).getSpecCode());				
				product.setColorCode(productDiffList.get(i).getColorCode());				
				product.setMotorCode(productDiffList.get(i).getMotorCode());				
				product.setUnit(productDiffList.get(i).getUnit());				
				product.setBatteryCode(productDiffList.get(i).getBatteryCode());				
				product.setBatteryQty(productDiffList.get(i).getBatteryQty());				
				product.setBatteryDesc(productDiffList.get(i).getBatteryDesc());				
				product.setTyreSize(productDiffList.get(i).getTyreSize());				
				product.setVoltage(productDiffList.get(i).getVoltage());				
				product.setControl(productDiffList.get(i).getControl());				
				product.setColorName(productDiffList.get(i).getColorName());				
				product.setSpecName(productDiffList.get(i).getWheelDiameter());				
				product.setCategoryCode(product.getModelCode());				
				product.setBrand("绿源");
				product.setDealerId(1);
				product.setDealerShortName("绿源");
				
				productBiz.save(product);
			}
		}
		if(partCodeList != null && partCodeList.size() != 0) {
			List<String> partCodeNoRepeatedList = new ArrayList<String>();
			for(int i = 0; i < partCodeList.size(); i++) {
				if(! partCodeNoRepeatedList.contains(partCodeList.get(i)))
					partCodeNoRepeatedList.add(partCodeList.get(i));
			}
			
			List<PartDiff> partDiffList = partDiffBiz.findPartDiff(partCodeNoRepeatedList);
			for(int i = 0; i < partDiffList.size(); i++) {
				Part part = new Part();					
				part.setCode(partDiffList.get(i).getPartCode());
				part.setName(partDiffList.get(i).getPartName());
				part.setSpecType(partDiffList.get(i).getSpecType());
				part.setPartColor("");
				part.setUnit(partDiffList.get(i).getUnit());
				part.setCategoryCode("");
				part.setDealerId(1);
				part.setDealerShortName("绿源");
				part.setDescription("");
				
				partBiz.save(part);
			}
		}
	}	
	
	
	/**validate**/
	public void validateReadShipOrder(){	
		if( voucher.getShipOrderCode() == null || voucher.getShipOrderCode().equals("") ) {
			this.addFieldError("voucher.shipOrderCode", "请填写发货通知单编码");
			return;
		}
	}
	
	public void validateImportProduct() {
		if(productCodeList.size() ==0 && partCodeList.size() == 0)
			this.addActionError("没有需要导入的商品");
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
			this.addFieldError("voucher.checkDate", "进货日期不能为空");
		//日期是选择进去的，不会有格式不正确的问题，不用验证
//		else if(voucher.getCheckDate())
//			this.addFieldError("voucher.checkDate", "日期格式不正确(参考：2011-01-23)");
		if( voucher.getDealingUnitId() == null ) 
			this.addFieldError("voucher.dealingUnit", "供货单位不能为空");
		if( voucher.getSubAccountId() == null ) 
			this.addFieldError("voucher.subAccount", "账户不能为空，请重新选择供货单位来填充账户信息");
		if( voucher.getHandlerId() == null ) 
			this.addFieldError("voucher.handler", "经手人不能为空");
		if( voucher.getWarehouseId() == null ) 
			this.addFieldError("voucher.warehouse", "收货仓库不能为空");		
	}
	
//	//class inside data
	/**
	 * productCodeList还没有导入的整车编码列表
	 * 发货通知单中有，但是本地库还没有导入
	 */
	List<String> productCodeList = new ArrayList<String>();
	/**
	 * partCodeList还没有导入的配件编码列表
	 * 发货通知单中有，但是本地库还没有导入
	 */
	List<String> partCodeList = new ArrayList<String>();

	/**
	 * check表明进入进货单后有没有记账权限，即进货单页面上的“记账”按钮可不可用
	 * 用于进货单的特殊情况，录单员有记账权限但是不能看价格(进货价)
	 */
	private boolean check;
	public boolean isCheck() {
		return check;
	}

	/**************************************************************************************************/
	//biz		
	private PartBiz partBiz;
	public void setPartBiz(PartBiz partBiz) {
		this.partBiz = partBiz;
	}	
	
	private ProductDiffBiz productDiffBiz;
	public void setProductDiffBiz(ProductDiffBiz productDiffBiz) {
		this.productDiffBiz = productDiffBiz;
	}
	 
	private PartDiffBiz partDiffBiz;
	public void setPartDiffBiz(PartDiffBiz partDiffBiz) {
		this.partDiffBiz = partDiffBiz;
	}
}
