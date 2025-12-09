/**
 * @(#)VoucherAct.java  1.0 10/05/03
 *
 * Copyright 2010 luyuan-ebike, Inc. All rights reserved.
 * 
 */
package com.luyuan.deal.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;

import com.luyuan.action.OptAct;
import com.luyuan.deal.biz.VoucherBiz;
import com.luyuan.deal.entity.Voucher;
import com.luyuan.deal.entity.VoucherDetail;
import com.luyuan.info.action.EmployeeListAct;
import com.luyuan.info.action.PartInventoryAct;
import com.luyuan.info.action.ProductInventoryAct;
import com.luyuan.info.action.WarehouseListAct;
import com.luyuan.info.biz.EmployeeBiz;
import com.luyuan.info.biz.ProductBiz;
import com.luyuan.info.biz.SubAccountBiz;
import com.luyuan.info.biz.WarehouseBiz;
import com.luyuan.info.entity.Employee;
import com.luyuan.info.entity.PartInventory;
import com.luyuan.info.entity.Product;
import com.luyuan.info.entity.ProductInventory;
import com.luyuan.info.entity.SubAccount;
import com.luyuan.info.entity.Warehouse;
import com.luyuan.sys.action.UnitListAct;
import com.luyuan.sys.entity.Unit;

/**
 * 
 * 单据Voucher的表现层action，父类
 * 涉及Voucher操作的最小公共集，提供了单据的多数默认方法
 *
 * @author gch
 */

public class VoucherAct extends OptAct {
	
	/**************************************************************************************************/	
	/*****辅助输入**************/
	
	/**
	 * input info
	 * 根据登录用户(操作员)的信息设置单据的初始基本信息，
	 * input 非红冲，createDate. isChecked 提交时设定,ShopId ShopShortName依赖warehouseId
	 * @param voucherType	单据类型
	 * @return 				可以不返回，返回仅仅出于增加可读性的目的
	 */
	protected Voucher getInitVoucher(String voucherType){
		voucher = new Voucher();
		
		voucher.setType(voucherType);
		voucher.setCheckDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		voucher.setDealerId((Integer) this.getSession().get("parentId"));
		voucher.setDealerShortName((String) this.getSession().get("parentShortName"));
		Employee employee = employeeBiz.findByUserId((Integer) this.getSession().get("userId"));		
		voucher.setCreatorId(employee.getId());
		voucher.setCreatorName(employee.getName());
		voucher.setIsError(false);
		
		//单据初始化时清空voucherDetailList
		if(voucherDetailList != null)
			voucherDetailList.clear();
		
		return voucher;
	}

	/**
	 * 删除商品		result name="success"
	 * 在voucherDetailList非空的前提下删除选择的商品，并清空选项列表
	 */
	public String delProduct() {
		//if( selectList == null || selectList.size() == 0)	//已有校验								
		for(int i = selectList.size()-1; i >= 0; i--) {					
			voucherDetailList.remove(selectList.get(i).intValue());					
		}
		selectList.clear();
		return "success";
	}
	
	/**
	 * 删除商品前的校验  result name="input"
	 */
	public void validateDelProduct() {
		if( voucherDetailList == null || voucherDetailList.size() == 0){
			this.addActionError("请先添加商品");
			return;
		}
		if( selectList == null || selectList.size() == 0){
			this.addActionError("请先选择商品");
			return;
		}
	}

	/**
	 * 计算金额
	 * refactor : detail.setTotal(0.0);	//原先为null
	 */
	public String calculate() {	
		int qty = 0;
		double total = 0;
		double discount = 1;
		
		//if( voucherDetailList == null )	//已有校验
		for(VoucherDetail detail : voucherDetailList) {
			discount = 1;
			detail.setTotal(0.0);		//原先为null
			
			if( detail.getDiscount() != null)
				discount = detail.getDiscount();
			if( detail.getQty() != null){
				qty += detail.getQty();
			}			
			if( detail.getQty() != null && detail.getPrice() != null){
				detail.setTotal( detail.getQty() * detail.getPrice() * discount );
				total += detail.getTotal();
			}
		}
		voucher.setQty(qty);
		voucher.setTotal(total);
		
		//优惠金额最好默认为0 -- 数据库
		voucher.setActualTotal(total);
		if(voucher.getDiscount() != null)
			voucher.setActualTotal(total - voucher.getDiscount());
		
		return "success";	
	}
	
	/**
	 * 计算金额前的校验，默认校验只验证了数量，--在需要验证价格的地方，需要覆盖这个方法--
	 * 计算金额的时候必须有价格且要保证价格符合要求(不能<0)，所以添加了验证价格的代码
	 */
	public void validateCalculate(){
		if( voucherDetailList == null || voucherDetailList.size() == 0){
			this.addActionError("请先添加商品");
			return;
		}
		for(VoucherDetail detail : voucherDetailList) {
			if( detail.getQty() == null){
				this.addActionError("商品数量不能为空");
				break;
			}
			if(detail.getQty() <= 0) {
				this.addActionError("商品数量不能是0或负数");
				break;
			}
			if(viewPrice) {				
				if(detail.getPrice() == null) {
					this.addActionError("商品单价不能为空");
					break;
				}
				if(detail.getPrice() < 0) {
					this.addActionError("商品单价不能是负数");
					break;
				}
			}
		}
	}
	
	
	
	
	/**************************************************************************************************/	
	/*****公共操作：功能操作，一般会有数据库的操作**************/
	
	//单据选择员工返回入口
	public String employeeListBack() {
		EmployeeListAct employeeListAct = (EmployeeListAct)ctx.getBean("employeeListAct");
		Employee employee = employeeListAct.getEmployee();
		if(employee != null) {
			voucher.setHandlerId( employee.getId() );
			voucher.setHandlerName( employee.getName() );
		}
		return "success";
	}
	
	//单据选择仓库返回入口
	public String warehouseListBack() {
		WarehouseListAct warehouseListAct = (WarehouseListAct)ctx.getBean("warehouseListAct");
		Warehouse warehouse = warehouseListAct.getWarehouse();
		if(warehouse != null) {
			voucher.setWarehouseId(warehouse.getId());
			voucher.setWarehouseName(warehouse.getName());
		}
		return "success";
	}
	
	//单据选择商品返回入口
	public String productListBack() {
		VoucherDetail voucherDetail;
		
		//整车
//		ProductInventoryAct productInventoryAct = (ProductInventoryAct)ctx.getBean("productInventoryAct");
//		List<ProductInventory> productInventoryList = productInventoryAct.getProductInventorySelectedList();
//		if(productInventoryList != null && productInventoryList.size() != 0) {
//			StringBuffer name = new StringBuffer();		
//			for (ProductInventory productInventory : productInventoryList) {						
//				voucherDetail = new VoucherDetail();			
//				voucherDetail.setProductId(productInventory.getId().getId());
//				voucherDetail.setProductCode(productInventory.getCode());
//				
//				if( productInventory.getPrefixName() != null )
//					name.append( productInventory.getPrefixName().trim()).append(" ");
//				if( productInventory.getSuffixName() != null )
//					name.append( productInventory.getSuffixName().trim());
//				voucherDetail.setProductName( name.toString() );
//				name.delete(0, name.length());
//				
//				if(productInventory.getColorName() != null)
//					voucherDetail.setProductColor(productInventory.getColorName());
//				if(productInventory.getUnit() != null)
//					voucherDetail.setProductUnit(productInventory.getUnit());
//				voucherDetail.setQty(1);
//				
//				voucherDetailList.add(voucherDetail);
//			}
//		}
		
		//配件、组件
		PartInventoryAct partInventoryAct = (PartInventoryAct)ctx.getBean("partInventoryAct");
		List<PartInventory> partInventoryList = partInventoryAct.getPartInventorySelectedList();
		if(partInventoryList != null && partInventoryList.size() != 0) {
			for (PartInventory partInventory : partInventoryList) {						
				voucherDetail = new VoucherDetail();
				voucherDetail.setProductId(partInventory.getId().getId());
				voucherDetail.setProductCode(partInventory.getCode());
				voucherDetail.setProductName(partInventory.getName());		
				if(partInventory.getPartColor() != null)
					voucherDetail.setProductColor(partInventory.getPartColor());
				if(partInventory.getUnit() != null)
					voucherDetail.setProductUnit(partInventory.getUnit());
				voucherDetail.setRemark(partInventory.getSpecType());
				voucherDetail.setQty(1);
				voucherDetail.setPrice(0.0);
				
				voucherDetailList.add(voucherDetail);
			}
		}
		
		return "success";
	}
	
	/**
	 * 选择供货单位后，重定向返回
	 * 选择供货单位的同时，设定供货单位子账户
	 */
	public String unitListBack(String subAccountType) {
		UnitListAct unitListAct = (UnitListAct) ctx.getBean("unitListAct");
		Unit unit = unitListAct.getUnit();
		if(unit != null) {
			voucher.setDealingUnitId( unit.getId() );
			voucher.setDealingUnitShortName( unit.getShortName() );	
			//subAccountType表明是否需要设置子账户以及子账户的类型（应收/应付）
			//进货单、进货退货单要设定应付子账户，subAccountType="JH"
			//销售单、销售退货单要设定应收子账户，subAccountType="XS"
			//调拨入库单和调拨出库单不用设定子账户，subAccountType=""
			//报损单和报溢单不用选择往来单位
			if(subAccountType.equals(""))
				return "success";
			
			SubAccount subAccount = subAccountBiz.findSubAccountForVoucher((Integer) this.getSession().get("parentId"), voucher.getDealingUnitId(), subAccountType, type);
			if(subAccount != null) {
				voucher.setSubAccountId(subAccount.getId());
				voucher.setSubAccountType(subAccount.getName());
			}
			else {
				voucher.setSubAccountId(null);
				voucher.setSubAccountType(null);
				this.addFieldError("voucher.subAccountId", voucher.getDealingUnitShortName() + type + "账户不存在或无效，请先开户或启用");
			}
		}		
		return "success";	
	}
	
	/**
	 * 保存单据，保存前的校验在各自action中完成
	 * @param prefix	用于生成编码的前缀
	 */
	protected void save(String prefix){		
		voucher.setShopId(warehouseBiz.findById(voucher.getWarehouseId()).getShopId());			
		voucher.setShopShortName(warehouseBiz.findById(voucher.getWarehouseId()).getShopShortName());
		this.calculate();
		
		//使用单据的创建日期是否为空来判断单据是新填的还是修改以前的单据
		//如果是新填的单据，创建日期是空
		//如果是修改以前的单据，则创建日期不为空
		if(voucher.getCreateDate() == null) {
			voucher.setIsChecked(false);
			voucher.setCreateDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			//新填的单子，保存
			if(type.equals("配件"))	//配件单据编码前加"P"
				prefix = "P" + prefix;
			voucherBiz.save(voucher, voucherDetailList, prefix, false);
			return;
		}
		//修改单子，更新
		voucherBiz.update(voucher, voucherDetailList);
	}
	
	/**
	 * 记账,记账前的校验在各自action中完成
	 */
	public void saveCheck(String prefix) {		
		voucher.setIsChecked(true);	
		this.calculate();	
		
		if(type.equals("配件"))	//配件单据编码前加"P"
			prefix = "P" + prefix;
		
		//设置记账人信息
		Employee employee = employeeBiz.findByUserId((Integer) this.getSession().get("userId"));		
		voucher.setAccountantId(employee.getId());
		voucher.setAccountantName(employee.getName());
		
		//使用单据的创建日期是否为空来判断单据是新填的还是修改以前的单据
		//如果是新填的单据，创建日期是空
		//如果是修改以前的单据，则创建日期不为空
		if( voucher.getCreateDate() == null) {
			voucher.setCreateDate( new SimpleDateFormat("yyyy-MM-dd").format(new Date()) );			
			//新填的单子，倒数第二个参数isNew设为true
			voucherBiz.saveCheck(voucher, voucherDetailList, null, prefix, true, false);
			return;
		}
		//修改单子，倒数第二个参数isNew设为false
		voucherBiz.saveCheck(voucher, voucherDetailList, null, prefix, false, false);
	}
	
	/**
	 * 单据查看入口：
	 * 不能修改单据了
	 */
	public String detail() {
		CourseAct courseAct;
		if(type.equals("整车"))
			courseAct = (CourseAct) ctx.getBean("dealCourseAct");
		else
			courseAct = (CourseAct) ctx.getBean("pdealCourseAct");
		voucher = courseAct.getVoucher();
		voucherDetailList = voucherBiz.findByVoucherId(voucher.getId());
		viewPrice = courseAct.isViewPrice();
		return "success";
	}
	
	/**
	 * 单据修改入口：
	 */
	public String update() {		
		DraftAct draftAct;
		if(type.equals("整车"))
			draftAct = (DraftAct) ctx.getBean("dealDraftAct");
		else
			draftAct = (DraftAct) ctx.getBean("pdealDraftAct");
		voucher = draftAct.getVoucher();
		voucherDetailList = voucherBiz.findByVoucherId(voucher.getId());
		viewPrice = draftAct.isViewPrice();
		return "success";
	}
	
	//根据条码添加整车
	protected void addcode() {
		
		//为了保持条码与明细一致，每次读条码之间都要把明细中已有的整车删掉
		//明细中已有的配件不删除，方便用户操作
		if(voucherDetailList != null) {
			for(int i = voucherDetailList.size()-1; i >= 0; i--) {			
				if(voucherDetailList.get(i).getProductCode().startsWith("6"))
					voucherDetailList.remove(voucherDetailList.get(i));
			}
		}
		
		productBarcodeArray = voucher.getProductBarcodeTxt().split("\r\n");

		for (int i = 0; i < productBarcodeArray.length; i++) {
			// tar[i]为ProductBarcode，可以通过substring(3,12)拆分出整车型号
			// 在记账的时候需要把ProductInWarehouse的内容加入到ProductHistory
			// 然可以生成一个待输服务卡页面；
			// 用户在这个上面补齐服务卡的内容。
			for (int li = 0; li < productBarcodeArray.length; li++) {
				if (productBarcodeArray[i].equals(productBarcodeArray[li])
						&& i != li) {
					this.addFieldError("voucher.codeback", "发现重复条码");
					return;
				}
			}
			
			if (productBarcodeArray[i].length() == 16) {
				Product product = productBiz.findByCode(productBarcodeArray[i].substring(3, 12));
				if (product != null) {
					VoucherDetail voucherDetail = new VoucherDetail();
					voucherDetail.setProductId(product.getId());
					voucherDetail.setProductCode(product.getCode());
					if (product.getPrefixName() == null)
						voucherDetail.setProductName(product.getSuffixName());
					if (product.getSuffixName() == null)
						voucherDetail.setProductName(product.getPrefixName());
					else
						voucherDetail.setProductName(product.getPrefixName() + " " + product.getSuffixName());
					voucherDetail.setProductColor(product.getColorName());
					voucherDetail.setProductUnit(product.getUnit());

					int tqty = 1;
					for (int ii = 0; ii < voucherDetailList.size(); ii++) {
						if (productBarcodeArray[i].substring(3, 12).equals(voucherDetailList.get(ii).getProductCode())) {
							tqty = voucherDetailList.get(ii).getQty() + 1;
							voucherDetailList.get(ii).setQty(tqty);
							break;
						}

					}
					if (tqty == 1) {
						voucherDetail.setQty(tqty);
						voucherDetail.setPrice(0.00);
						voucherDetailList.add(voucherDetail);
					}
				}
			} else {
				this.addFieldError("voucher.codeback", "发现错误条码：" + productBarcodeArray[i]);
			}
		}
	}
	
//	/**
//	 * 更新单据，更新前的校验在各自action中完成
//	 */
//	public void update(){
//		voucher.setShopId(warehouseBiz.findById(voucher.getWarehouseId()).getShopId());			
//		voucher.setShopShortName(warehouseBiz.findById(voucher.getWarehouseId()).getShopShortName());
//		this.calculate();
//		voucherBiz.update(voucher, voucherDetailList);
//	}

	
	
	/**************************************************************************************************/
	/**************************************************************************************************/
	//session data
//	private Map<String,Object> session = ActionContext.getContext().getSession();
//	protected Integer dealerId = (Integer) session.get("parentId");
//	protected Integer shopId = (Integer) session.get("unitId");		
//	protected Integer userId = (Integer) session.get("userId");
//	protected String dealerShortName = (String) session.get("parentShortName");
//	private String shopShortName = (String) session.get("shortName");
//	//private String unitType = (String) session.get("unitType");
	
	//class inside data
	//存放分离好的整车条码
	private String [] productBarcodeArray;
	
	//page field
	protected Voucher voucher;
	public Voucher getVoucher() {
		return voucher;
	}
	public void setVoucher(Voucher voucher) {		
		this.voucher = voucher;
	}

	protected List<VoucherDetail> voucherDetailList = new ArrayList<VoucherDetail>();	
	public List<VoucherDetail> getVoucherDetailList() {
		return voucherDetailList;
	}
	public void setVoucherDetailList(List<VoucherDetail> voucherDetailList) {
		this.voucherDetailList = voucherDetailList;
	}		
	
	/**
	 * type用于区分整车与配件（二者使用不同的表），在spring配置文件中设置，
	 * 该字段与voucher.type不同，voucher.type用于如下区分：
	 * 进货单、进货退货单、销售单、销售退货单、调拨出库单、调拨入库单
	 */
	protected String type;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * selectList选择的商品列表，用于删除一条至多条商品
	 * 在删除商品后清空
	 */
	protected List<Long> selectList;
	public void setSelectList(List<Long> selectList) {
		this.selectList = selectList;
	}
	
	//spring ApplicationContext
	protected ApplicationContext ctx = this.getAppContext();
	
	/**
	 * viewPrice单据页面是否能够看到商品价格(主要是进货价)，也表明有没有记账权限
	 * 记账员(经销商)可以看到价格并有记账权限，录单员(门店)不能看到价格且没有记账权限
	 * 特殊情况：门店的操作员有(进货单)记账权限但是不能看到价格(进货价)，还需要另外一个boolean类型的内部变量(暂定为check)
	 */
	protected boolean viewPrice;
	public boolean isViewPrice(){
		return viewPrice;
	}	
	
	/**************************************************************************************************/
	//biz
	protected VoucherBiz voucherBiz;
	public void setVoucherBiz(VoucherBiz voucherBiz) {
		this.voucherBiz = voucherBiz;
	}
	
	protected EmployeeBiz employeeBiz;
	public void setEmployeeBiz(EmployeeBiz employeeBiz) {
		this.employeeBiz = employeeBiz;
	}	
	
	protected WarehouseBiz warehouseBiz;
	public void setWarehouseBiz(WarehouseBiz warehouseBiz) {
		this.warehouseBiz = warehouseBiz;
	}
	
	protected SubAccountBiz subAccountBiz;
	public void setSubAccountBiz(SubAccountBiz subAccountBiz) {
		this.subAccountBiz = subAccountBiz;
	}
	
	protected ProductBiz productBiz;
	public void setProductBiz(ProductBiz productBiz) {
		this.productBiz = productBiz;
	}
}
