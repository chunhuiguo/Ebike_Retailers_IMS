/**
 * @(#)WarehouseAct.java  1.0 10/04/15
 *
 * Copyright 2010 luyuan-ebike, Inc. All rights reserved.
 * 
 */
package com.luyuan.info.action;

import java.util.List;

import com.luyuan.action.ListAct;
import com.luyuan.info.biz.WarehouseBiz;
import com.luyuan.info.entity.Warehouse;
import com.luyuan.util.Page;

/**
 * 
 * 仓库Warehouse的表现层action
 *
 * @author gch
 */

public class WarehouseListAct extends ListAct {
	
	public String list() {
		select = null;
		page =  new Page();
		page.setListAct("/info/warehouseList!select.html");
		
		this.warehouseList();
		return "success";
	}
	
	public String select() {
		if(holdWarehouse())
			return result;
		this.warehouseList();
		return "success";
	}
	
	//gch
//	/**************************************************************************************************/
//	//整车进货单仓库列表
//	public String purchaseWarehouseList() {	
//		select = null;
//		page =  new Page();
//		page.setListAct("/info/warehouseList!purchaseWarehouseSelect.html");
//
//		this.getVoucherWarehouseList();
//		return "success";
//	}
//	
//	public String purchaseWarehouseSelect(){
//		PurchaseAct purchaseAct = (PurchaseAct)ctx.getBean("purchaseAct");
//		Voucher voucher = purchaseAct.getVoucher();
//		if( setVoucherWarehouseInfo(voucher) )
//			return "purchase";
//		this.getVoucherWarehouseList();
//		return "success";
//	}
//	/**************************************************************************************************/
//	
//	/**************************************************************************************************/
//	//整车进货退货单仓库列表
//	public String purchaseBackWarehouseList() {	
//		select = null;
//		page =  new Page();
//		page.setListAct("/info/warehouseList!purchaseBackWarehouseSelect.html");
//
//		this.getVoucherWarehouseList();
//		return "success";
//	}
//	
//	public String purchaseBackWarehouseSelect(){
//		PurchaseBackAct purchaseBackAct = (PurchaseBackAct)ctx.getBean("purchaseBackAct");
//		Voucher voucher = purchaseBackAct.getVoucher();
//		if( setVoucherWarehouseInfo(voucher) )
//			return "purchaseBack";
//		this.getVoucherWarehouseList();
//		return "success";
//	}
//	/**************************************************************************************************/
//	
//	/**************************************************************************************************/
//	//整车销售单仓库列表
//	public String saleWarehouseList() {	
//		select = null;
//		page =  new Page();
//		page.setListAct("/info/warehouseList!saleWarehouseSelect.html");
//
//		this.getVoucherWarehouseList();
//		return "success";
//	}
//	
//	public String saleWarehouseSelect(){
//		SaleAct saleAct = (SaleAct)ctx.getBean("saleAct");
//		Voucher voucher = saleAct.getVoucher();
//		if( setVoucherWarehouseInfo(voucher) )
//			return "sale";
//		this.getVoucherWarehouseList();
//		return "success";
//	}
//	/**************************************************************************************************/
//	
//	/**************************************************************************************************/
//	//整车销售退货单仓库列表
//	public String saleBackWarehouseList() {	
//		select = null;
//		page =  new Page();
//		page.setListAct("/info/warehouseList!saleBackWarehouseSelect.html");
//
//		this.getVoucherWarehouseList();
//		return "success";
//	}
//	
//	public String saleBackWarehouseSelect(){
//		SaleBackAct saleBackAct = (SaleBackAct)ctx.getBean("saleBackAct");
//		Voucher voucher = saleBackAct.getVoucher();
//		if( setVoucherWarehouseInfo(voucher) )
//			return "saleBack";
//		this.getVoucherWarehouseList();
//		return "success";
//	}
//	/**************************************************************************************************/
//	
//	/**************************************************************************************************/
//	//整车调拨入库单仓库列表
//	public String transferInWarehouseList() {	
//		select = null;
//		page =  new Page();
//		page.setListAct("/info/warehouseList!transferInWarehouseSelect.html");
//
//		this.getVoucherWarehouseList();
//		return "success";
//	}
//	
//	public String transferInWarehouseSelect(){
//		TransferInAct transferInAct = (TransferInAct)ctx.getBean("transferInAct");
//		Voucher voucher = transferInAct.getVoucher();
//		if( setVoucherWarehouseInfo(voucher) )
//			return "transferIn";
//		this.getVoucherWarehouseList();
//		return "success";
//	}
//	/**************************************************************************************************/
//	
//	/**************************************************************************************************/
//	//整车调拨出库单仓库列表
//	public String transferOutWarehouseList() {	
//		select = null;
//		page =  new Page();
//		page.setListAct("/info/warehouseList!transferOutWarehouseSelect.html");
//
//		this.getVoucherWarehouseList();
//		return "success";
//	}
//	
//	public String transferOutWarehouseSelect(){
//		TransferOutAct transferOutAct = (TransferOutAct)ctx.getBean("transferOutAct");
//		Voucher voucher = transferOutAct.getVoucher();
//		if( setVoucherWarehouseInfo(voucher) )
//			return "transferOut";
//		this.getVoucherWarehouseList();
//		return "success";
//	}
//	/**************************************************************************************************/
//	
//	/**************************************************************************************************/
//	//整车报损单仓库列表
//	public String lossWarehouseList() {	
//		select = null;
//		page =  new Page();
//		page.setListAct("/info/warehouseList!lossWarehouseSelect.html");
//
//		this.getVoucherWarehouseList();
//		return "success";
//	}
//	
//	public String lossWarehouseSelect(){
//		LossAct lossAct = (LossAct)ctx.getBean("lossAct");
//		Voucher voucher = lossAct.getVoucher();
//		if( setVoucherWarehouseInfo(voucher) )
//			return "loss";
//		this.getVoucherWarehouseList();
//		return "success";
//	}
//	/**************************************************************************************************/
//	
//	/**************************************************************************************************/
//	//整车报溢单仓库列表
//	public String overflowWarehouseList() {	
//		select = null;
//		page =  new Page();
//		page.setListAct("/info/warehouseList!overflowWarehouseSelect.html");
//
//		this.getVoucherWarehouseList();
//		return "success";
//	}
//	
//	public String overflowWarehouseSelect(){
//		OverflowAct overflowAct = (OverflowAct)ctx.getBean("overflowAct");
//		Voucher voucher = overflowAct.getVoucher();
//		if( setVoucherWarehouseInfo(voucher) )
//			return "overflow";
//		this.getVoucherWarehouseList();
//		return "success";
//	}
//	/**************************************************************************************************/
//	
//	/**************************************************************************************************/
//	//整车业务单据历史查询仓库列表
//	public String voucherQueryWarehouseList() {	
//		select = null;
//		page =  new Page();
//		page.setListAct("/info/warehouseList!voucherQueryWarehouseSelect.html");
//
//		this.getVoucherWarehouseList();
//		return "success";
//	}
//	
//	public String voucherQueryWarehouseSelect(){
//		VoucherQueryAct voucherQueryAct = (VoucherQueryAct)ctx.getBean("voucherQueryAct");
//		Voucher voucher = voucherQueryAct.getVoucher();
//		if( setVoucherWarehouseInfo(voucher) )
//			return "voucherQuery";
//		this.getVoucherWarehouseList();
//		return "success";
//	}
//	/**************************************************************************************************/
//	
//	/**************************************************************************************************/
//	//配件进货单仓库列表
//	public String ppurchaseWarehouseList() {	
//		select = null;
//		page =  new Page();
//		page.setListAct("/info/warehouseList!ppurchaseWarehouseSelect.html");
//
//		this.getVoucherWarehouseList();
//		return "success";
//	}
//	
//	public String ppurchaseWarehouseSelect(){
//		PurchaseAct purchaseAct = (PurchaseAct)ctx.getBean("ppurchaseAct");
//		Voucher voucher = purchaseAct.getVoucher();
//		if( setVoucherWarehouseInfo(voucher) )
//			return "ppurchase";
//		this.getVoucherWarehouseList();
//		return "success";
//	}
//	/**************************************************************************************************/
//	
//	/**************************************************************************************************/
//	//配件进货退货单仓库列表
//	public String ppurchaseBackWarehouseList() {	
//		select = null;
//		page =  new Page();
//		page.setListAct("/info/warehouseList!ppurchaseBackWarehouseSelect.html");
//
//		this.getVoucherWarehouseList();
//		return "success";
//	}
//	
//	public String ppurchaseBackWarehouseSelect(){
//		PurchaseBackAct purchaseBackAct = (PurchaseBackAct)ctx.getBean("ppurchaseBackAct");
//		Voucher voucher = purchaseBackAct.getVoucher();
//		if( setVoucherWarehouseInfo(voucher) )
//			return "ppurchaseBack";
//		this.getVoucherWarehouseList();
//		return "success";
//	}
//	/**************************************************************************************************/
//	
//	/**************************************************************************************************/
//	//配件销售单仓库列表
//	public String psaleWarehouseList() {	
//		select = null;
//		page =  new Page();
//		page.setListAct("/info/warehouseList!psaleWarehouseSelect.html");
//
//		this.getVoucherWarehouseList();
//		return "success";
//	}
//	
//	public String psaleWarehouseSelect(){
//		SaleAct saleAct = (SaleAct)ctx.getBean("psaleAct");
//		Voucher voucher = saleAct.getVoucher();
//		if( setVoucherWarehouseInfo(voucher) )
//			return "psale";
//		this.getVoucherWarehouseList();
//		return "success";
//	}
//	/**************************************************************************************************/
//	
//	/**************************************************************************************************/
//	//配件销售退货单仓库列表
//	public String psaleBackWarehouseList() {	
//		select = null;
//		page =  new Page();
//		page.setListAct("/info/warehouseList!psaleBackWarehouseSelect.html");
//
//		this.getVoucherWarehouseList();
//		return "success";
//	}
//	
//	public String psaleBackWarehouseSelect(){
//		SaleBackAct saleBackAct = (SaleBackAct)ctx.getBean("psaleBackAct");
//		Voucher voucher = saleBackAct.getVoucher();
//		if( setVoucherWarehouseInfo(voucher) )
//			return "psaleBack";
//		this.getVoucherWarehouseList();
//		return "success";
//	}
//	/**************************************************************************************************/
//	
//	/**************************************************************************************************/
//	//配件调拨入库单仓库列表
//	public String ptransferInWarehouseList() {	
//		select = null;
//		page =  new Page();
//		page.setListAct("/info/warehouseList!ptransferInWarehouseSelect.html");
//
//		this.getVoucherWarehouseList();
//		return "success";
//	}
//	
//	public String ptransferInWarehouseSelect(){
//		TransferInAct transferInAct = (TransferInAct)ctx.getBean("ptransferInAct");
//		Voucher voucher = transferInAct.getVoucher();
//		if( setVoucherWarehouseInfo(voucher) )
//			return "ptransferIn";
//		this.getVoucherWarehouseList();
//		return "success";
//	}
//	/**************************************************************************************************/
//	
//	/**************************************************************************************************/
//	//配件调拨出库单仓库列表
//	public String ptransferOutWarehouseList() {	
//		select = null;
//		page =  new Page();
//		page.setListAct("/info/warehouseList!ptransferOutWarehouseSelect.html");
//
//		this.getVoucherWarehouseList();
//		return "success";
//	}
//	
//	public String ptransferOutWarehouseSelect(){
//		TransferOutAct transferOutAct = (TransferOutAct)ctx.getBean("ptransferOutAct");
//		Voucher voucher = transferOutAct.getVoucher();
//		if( setVoucherWarehouseInfo(voucher) )
//			return "ptransferOut";
//		this.getVoucherWarehouseList();
//		return "success";
//	}
//	/**************************************************************************************************/
//	
//	/**************************************************************************************************/
//	//配件报损单仓库列表
//	public String plossWarehouseList() {	
//		select = null;
//		page =  new Page();
//		page.setListAct("/info/warehouseList!plossWarehouseSelect.html");
//
//		this.getVoucherWarehouseList();
//		return "success";
//	}
//	
//	public String plossWarehouseSelect(){
//		LossAct lossAct = (LossAct)ctx.getBean("plossAct");
//		Voucher voucher = lossAct.getVoucher();
//		if( setVoucherWarehouseInfo(voucher) )
//			return "ploss";
//		this.getVoucherWarehouseList();
//		return "success";
//	}
//	/**************************************************************************************************/
//	
//	/**************************************************************************************************/
//	//配件报溢单仓库列表
//	public String poverflowWarehouseList() {	
//		select = null;
//		page =  new Page();
//		page.setListAct("/info/warehouseList!poverflowWarehouseSelect.html");
//
//		this.getVoucherWarehouseList();
//		return "success";
//	}
//	
//	public String poverflowWarehouseSelect(){
//		OverflowAct overflowAct = (OverflowAct)ctx.getBean("poverflowAct");
//		Voucher voucher = overflowAct.getVoucher();
//		if( setVoucherWarehouseInfo(voucher) )
//			return "poverflow";
//		this.getVoucherWarehouseList();
//		return "success";
//	}
//	/**************************************************************************************************/
//	
//	/**************************************************************************************************/
//	//配件业务单据历史查询仓库列表
//	public String pvoucherQueryWarehouseList() {	
//		select = null;
//		page =  new Page();
//		page.setListAct("/info/warehouseList!pvoucherQueryWarehouseSelect.html");
//
//		this.getVoucherWarehouseList();
//		return "success";
//	}
//	
//	public String pvoucherQueryWarehouseSelect(){
//		VoucherQueryAct voucherQueryAct = (VoucherQueryAct)ctx.getBean("pvoucherQueryAct");
//		Voucher voucher = voucherQueryAct.getVoucher();
//		if( setVoucherWarehouseInfo(voucher) )
//			return "pvoucherQuery";
//		this.getVoucherWarehouseList();
//		return "success";
//	}
//	/**************************************************************************************************/
//	
//	/**************************************************************************************************/
//	//整车库存初始化仓库列表
//	public String inventoryIntialWarehouseList() {	
//		select = null;
//		page =  new Page();
//		page.setListAct("/info/warehouseList!inventoryIntialWarehouseSelect.html");
//
//		this.getVoucherWarehouseList();
//		return "success";
//	}
//	
//	public String inventoryIntialWarehouseSelect(){
//		InventoryIntialAct inventoryIntialAct = (InventoryIntialAct) ctx.getBean("inventoryIntialAct");
//		List<InventoryDetail> inventoryDetailList = inventoryIntialAct.getInventoryDetailList();	
//		if( setInventoryIntialWarehouseInfo(inventoryDetailList) )
//			return "inventoryIntial";
//		this.getVoucherWarehouseList();
//		return "success";
//	}
//	/**************************************************************************************************/
//	
//	/**************************************************************************************************/
//	//配件库存初始化仓库列表
//	public String pinventoryIntialWarehouseList() {	
//		select = null;
//		page =  new Page();
//		page.setListAct("/info/warehouseList!pinventoryIntialWarehouseSelect.html");
//
//		this.getVoucherWarehouseList();
//		return "success";
//	}
//	
//	public String pinventoryIntialWarehouseSelect(){
//		InventoryIntialAct inventoryIntialAct = (InventoryIntialAct) ctx.getBean("pinventoryIntialAct");
//		List<InventoryDetail> inventoryDetailList = inventoryIntialAct.getInventoryDetailList();	
//		if( setInventoryIntialWarehouseInfo(inventoryDetailList) )
//			return "pinventoryIntial";
//		this.getVoucherWarehouseList();
//		return "success";
//	}
	/**************************************************************************************************/
	
	
	/**************************************************************************************************/
	/**************************************************************************************************/
	//private method
	
	private boolean holdWarehouse() {
		//翻页
		if( select == null )
			return false;
		//退出，返回
		if( select == -1) {
			warehouse = null;
			return true;
		}
		//确定
		warehouse = warehouseList.get(select);
		return true;
	}
	
	//仓库列表
	private void warehouseList() {		
		if(warehouseList != null)
			warehouseList.clear();	
		
		boolean isDealer = false;
		if( this.getSession().get("unitType").equals("经销商") )
			isDealer = true;		
		warehouseList = warehouseBiz.findWarehouse(this, (Integer) this.getSession().get("unitId"), isDealer, false);
	}
	

//	
//	private boolean setInventoryIntialWarehouseInfo(List<InventoryDetail> inventoryDetailList){
//		//翻页
//		if( select == null )
//			return false;
//		//退出，返回
//		if( select == -1)			
//			return true;
//		//确定
//		InventoryDetail inventoryDetail = new InventoryDetail();
//		inventoryDetail.setWarehouseId(warehouseList.get(select).getId());
//		inventoryDetail.setWarehouseName(warehouseList.get(select).getName());
//		inventoryDetail.setQty(1);
//		inventoryDetailList.add(inventoryDetail);
//		return true;			
//	}
	
	/**************************************************************************************************/	
	//page field
	private List<Warehouse> warehouseList;
	public List<Warehouse> getWarehouseList() {
		return warehouseList;
	}	
	
	//用来保存选择的warehouse信息
	//供需要的action获得
	private Warehouse warehouse;	
	public Warehouse getWarehouse() {
		return warehouse;
	}
	
	//用于接收别的action传过来的参数
	private String result;
	public void setResult(String result) {
		this.result = result;
	}
	
	//no ajax
	private Integer select;	
	public void setSelect(Integer select) {
		this.select = select;
	}

	//biz
	private WarehouseBiz warehouseBiz;
	public void setWarehouseBiz(WarehouseBiz warehouseBiz) {
		this.warehouseBiz = warehouseBiz;
	}
}