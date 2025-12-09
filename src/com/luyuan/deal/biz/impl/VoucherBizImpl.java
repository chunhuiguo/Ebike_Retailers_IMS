/**
 * @(#)VoucherBizImpl.java  1.0 10/04/19
 *
 * Copyright 2010 luyuan-ebike, Inc. All rights reserved.
 * 
 */
package com.luyuan.deal.biz.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.luyuan.action.IFieldValidation;
import com.luyuan.deal.biz.CodeCreatorService;
import com.luyuan.deal.biz.VoucherBiz;
import com.luyuan.deal.dao.VoucherDAO;
import com.luyuan.deal.dao.VoucherDetailDAO;
import com.luyuan.deal.dao.VoucherEXTDAO;
import com.luyuan.deal.entity.Voucher;
import com.luyuan.deal.entity.VoucherDetail;
import com.luyuan.deal.entity.VoucherEXT;
import com.luyuan.info.dao.AccountDAO;
import com.luyuan.info.dao.EmployeeDAO;
import com.luyuan.info.dao.SubAccountDAO;
import com.luyuan.info.entity.Account;
import com.luyuan.info.entity.Employee;
import com.luyuan.info.entity.SubAccount;
import com.luyuan.money.dao.DealingBookDAO;
import com.luyuan.money.entity.DealingBook;
import com.luyuan.report.dao.InventoryLastDAO;
import com.luyuan.report.dao.PSIProductMonthlyReportDAO;
import com.luyuan.report.entity.InventoryLast;
import com.luyuan.report.entity.PSIProductMonthlyReport;
import com.luyuan.stock.dao.InventoryBookDAO;
import com.luyuan.stock.dao.InventoryDAO;
import com.luyuan.stock.dao.InventoryDetailDAO;
import com.luyuan.stock.dao.InventoryOutApportionDAO;
import com.luyuan.stock.entity.Inventory;
import com.luyuan.stock.entity.InventoryBook;
import com.luyuan.stock.entity.InventoryDetail;
import com.luyuan.stock.entity.InventoryOutApportion;
import com.luyuan.sys.dao.OrderDAO;
import com.luyuan.sys.dao.OrderDetailDAO;
import com.luyuan.sys.dao.PartOrderDAO;
import com.luyuan.sys.dao.PartOrderDetailDAO;
import com.luyuan.sys.entity.Order;
import com.luyuan.sys.entity.OrderDetail;
import com.luyuan.sys.entity.PartOrder;
import com.luyuan.sys.entity.PartOrderDetail;
import com.luyuan.util.GetLastMonth;

/**
 * 
 * 单据Voucher的业务逻辑层biz
 *
 * @author gch
 */

public class VoucherBizImpl implements VoucherBiz {
	
	/**************************************************************************************************/
	
	//保存单据
	public void save(Voucher voucher, List<VoucherDetail> voucherDetailList, String prefix, boolean isError) {
		this.voucherSave(voucher, voucherDetailList, prefix, isError);
	}
	
	//更新单据
	public void update(Voucher voucher, List<VoucherDetail> voucherDetailList) {
		this.voucherUpdate(voucher, voucherDetailList);
	}	
	
	//记账单据
	public void saveCheck(Voucher voucher, List<VoucherDetail> voucherDetailList, List<Long> voucherDetailIdList, String prefix, boolean isNew, boolean isError) {
		this.voucherSaveCheck(voucher, voucherDetailList, voucherDetailIdList, prefix, isNew, isError);		
	}
	
	public List<Voucher> findVoucher(IFieldValidation act, int shopId, boolean isChecked, String startDate, String endDate, boolean isDealer) {
		return voucherDAO.findVoucher(act, shopId, isChecked, startDate, endDate, isDealer);
	}
	
	public List<Voucher> findVoucher(IFieldValidation act, int shopId, boolean isChecked, String voucherType, String startDate, String endDate, boolean isDealer) {
		return voucherDAO.findVoucher(act, shopId, isChecked, voucherType, startDate, endDate, isDealer);
	}
	
	public List<Voucher> findVoucher(IFieldValidation act, int shopId, boolean isChecked, boolean isError, String startDate, String endDate, boolean isDealer) {
		return voucherDAO.findVoucher(act, shopId, isChecked, isError, startDate, endDate, isDealer);
	}
	
	public List<Voucher> findVoucher(IFieldValidation act, int shopId, boolean isChecked, String voucherType, boolean isError, String startDate, String endDate, boolean isDealer) {
		return voucherDAO.findVoucher(act, shopId, isChecked, voucherType, isError, startDate, endDate, isDealer);
	}
	
	//业务单据历史查询
	public List<Voucher> findVoucher(IFieldValidation act, int shopId, Voucher voucher, boolean isDealer) {
		return voucherDAO.findVoucher(act, shopId, voucher, isDealer);
	}
	
	public Voucher findById(long voucherId) {
		return voucherDAO.findById(voucherId);
	}
	
	public List<VoucherDetail> findByVoucherId(long voucherId) {
		return voucherDetailDAO.findByVoucherId(voucherId);
	}
	
	//删除单据
	public void delete(List<Long> voucherIdList) {
		
		voucherDAO.deleteById(voucherIdList);
		voucherDetailDAO.deleteByVoucherId(voucherIdList);
	}
	
	//冲抵单据	
	public void saveAgainst(Voucher voucher, List<VoucherDetail> voucherDetailList, int userId) {			
		List<Long> voucherDetailIdList = new ArrayList<Long>();	
		
		//删除整车单据扩展表的条码信息
		if(voucher.getType().startsWith("整车"))
			voucherEXTDAO.deleteByVoucherId(voucher.getId());
		
		voucher.setIsError(true);
		this.voucherUpdate(voucher);
		voucherDAO.evcit(voucher);
		
		voucher.setId(null);
		Employee employee = employeeDAO.findByUserId(userId);
		voucher.setCreatorId(employee.getId());
		voucher.setCreatorName(employee.getName());
		voucher.setAccountantId(employee.getId());
		voucher.setAccountantName(employee.getName());	
		voucher.setCreateDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));		
		voucher.setQty(- voucher.getQty());
		voucher.setTotal(- voucher.getTotal());
		if(voucher.getDiscount() != null)
			voucher.setDiscount(- voucher.getDiscount());
		voucher.setActualTotal(- voucher.getActualTotal());
		if(voucher.getPaidMoney() != null)
			voucher.setPaidMoney(- voucher.getPaidMoney());
		for(int i = 0; i < voucherDetailList.size(); i++) {
			voucherDetailList.get(i).setQty(- voucherDetailList.get(i).getQty());
			voucherDetailList.get(i).setTotal(- voucherDetailList.get(i).getTotal());			
			voucherDetailIdList.add(voucherDetailList.get(i).getId());
		}
		
		this.voucherSaveCheck(voucher, voucherDetailList, voucherDetailIdList, "", true, true);	
	}
	
	//读取整车发货通知单
	public List<OrderDetail> readShipOrder(int shipOrderId) {		
		List<Order> orderList = orderDAO.findByShipOrderId(shipOrderId);
		List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();		
		for(int i = 0; i < orderList.size(); i++)			
			orderDetailList.addAll(orderDetailDAO.findByOrderId(orderList.get(i).getOrderId()));			
		return orderDetailList;		
	}	
	
	//读取配件发货通知单
	public List<PartOrderDetail> readPartShipOrder(int partShipOrderId) {
		List<PartOrder> partOrderList = partOrderDAO.findByShipOrderId(partShipOrderId);
		List<PartOrderDetail> partOrderDetailList = new ArrayList<PartOrderDetail>();		
		for(int i = 0; i < partOrderList.size(); i++)			
			partOrderDetailList.addAll(partOrderDetailDAO.findByOrderId(partOrderList.get(i).getOrderId()));			
		return partOrderDetailList;	
	}
	
	//校验库存
	public String validation(IFieldValidation act, Voucher voucher, List<VoucherDetail> voucherDetailList) {
		String errorMessage = "";
		boolean isHad = false;
		VoucherDetail voucherDetailValidation;
		
		//组合明细中相同的商品，判断库存总量
		List<VoucherDetail> voucherDetailValidationList = new ArrayList<VoucherDetail>();
		for(int i = 0; i < voucherDetailList.size(); i++) {
			isHad = false;
			for(int j = 0; j < voucherDetailValidationList.size(); j++) {
				if(voucherDetailList.get(i).getProductId().equals(voucherDetailValidationList.get(j).getProductId())) {
					voucherDetailValidationList.get(j).setQty(voucherDetailValidationList.get(j).getQty() + voucherDetailList.get(i).getQty());
					isHad = true;
					break;
				}
			}
			if(! isHad) {
				voucherDetailValidation = new VoucherDetail();
				voucherDetailValidation.setProductId(voucherDetailList.get(i).getProductId());
				voucherDetailValidation.setProductCode(voucherDetailList.get(i).getProductCode());
				voucherDetailValidation.setProductName(voucherDetailList.get(i).getProductName());
				voucherDetailValidation.setQty(voucherDetailList.get(i).getQty());
				voucherDetailValidationList.add(voucherDetailValidation);
			}
		}
		
		for(int i = 0; i < voucherDetailValidationList.size(); i++) {			
			Inventory inventory;

			if(voucherDetailValidationList.get(i).getProductCode().startsWith("6"))
				inventory = inventoryDAO.findProduct(voucher.getShopId(), voucher.getWarehouseId(), voucherDetailValidationList.get(i).getProductId());
			else
				inventory = pinventoryDAO.findProduct(voucher.getShopId(), voucher.getWarehouseId(), voucherDetailValidationList.get(i).getProductId());

			if(inventory == null || inventory.getQty() < voucherDetailValidationList.get(i).getQty())
				errorMessage = errorMessage + "商品【" + voucherDetailValidationList.get(i).getProductName() + "】库存不足<br>";				
		}
		
		return errorMessage;	
	}
	
	//校验库存月报
	public String validation(IFieldValidation act, Voucher voucher) {	
		List list;
		if(voucher.getType().startsWith("整车"))
			list = pSIProductMonthlyReportDAO.checkSalesReport(voucher.getShopId(), voucher.getCheckDate().substring(0, 4), voucher.getCheckDate().substring(5, 7));
		else
			list = pSIPartMonthlyReportDAO.checkSalesReport(voucher.getShopId(), voucher.getCheckDate().substring(0, 4), voucher.getCheckDate().substring(5, 7));
		if(list != null && list.size() != 0)
			return voucher.getCheckDate().substring(0, 4) + "年" + voucher.getCheckDate().substring(5, 7) + "月的库存月报已经做过了，不能再执行单据记账操作";
		return "";
	}	
	
	public List<Voucher> findDraft( Integer shopId,  String beginDate,  String endDate){
		return voucherDAO.findDraft( shopId, beginDate, endDate);
	}
	
	
	
	/************************************************************************************************************************************/	
	//class inside method
	//为了不把函数配成事务，函数名字中的save放到后面
	private void inventoryDetailSave(Voucher voucher, VoucherDetail voucherDetail, boolean isProduct) {
		InventoryDetail inventoryDetail = new InventoryDetail();
		
		inventoryDetail.setDealerId(voucher.getDealerId());
		inventoryDetail.setDealerShortName(voucher.getDealerShortName());
		inventoryDetail.setShopId(voucher.getShopId());
		inventoryDetail.setShopShortName(voucher.getShopShortName());
		inventoryDetail.setWarehouseId(voucher.getWarehouseId());
		inventoryDetail.setWarehouseName(voucher.getWarehouseName());
		inventoryDetail.setProductId(voucherDetail.getProductId());
		inventoryDetail.setQty(voucherDetail.getQty());							
		inventoryDetail.setPrice(voucherDetail.getPrice());
		inventoryDetail.setTotal(voucherDetail.getTotal());	
		
		if(isProduct)
			inventoryDetailDAO.save(inventoryDetail);
		else
			pinventoryDetailDAO.save(inventoryDetail);
	}
	
	//为了不把函数配成事务，函数名字中的save放到后面
	private double inventoryDetailSave(Voucher voucher, List<InventoryOutApportion> inventoryOutApportionList, boolean isProduct) {
		
		double total = 0;
		
		for(int i = 0; i < inventoryOutApportionList.size(); i++) {
			
			InventoryDetail inventoryDetail = new InventoryDetail();
			
			inventoryDetail.setDealerId(voucher.getDealerId());
			inventoryDetail.setDealerShortName(voucher.getDealerShortName());
			inventoryDetail.setShopId(voucher.getShopId());
			inventoryDetail.setShopShortName(voucher.getShopShortName());
			inventoryDetail.setWarehouseId(voucher.getWarehouseId());
			inventoryDetail.setWarehouseName(voucher.getWarehouseName());
			inventoryDetail.setProductId(inventoryOutApportionList.get(i).getProductId());
			inventoryDetail.setQty(inventoryOutApportionList.get(i).getQty());							
			inventoryDetail.setPrice(inventoryOutApportionList.get(i).getPrice());
			inventoryDetail.setTotal(inventoryOutApportionList.get(i).getTotal());	
			
			if(isProduct)
				inventoryDetailDAO.save(inventoryDetail);
			else
				pinventoryDetailDAO.save(inventoryDetail);
			
			total = total + inventoryOutApportionList.get(i).getTotal();
		}	
		return total;
	}
	
	//为了不把函数配成事务，函数名字中的save放到后面
	private void inventoryOutApportionSave(InventoryDetail inventoryDetail, VoucherDetail voucherDetail, int qty, boolean isProduct) {
		
		InventoryOutApportion inventoryOutApportion = new InventoryOutApportion();
		
		inventoryOutApportion.setDealerId(inventoryDetail.getDealerId());
		inventoryOutApportion.setShopId(inventoryDetail.getShopId());
		inventoryOutApportion.setDealerShortName(inventoryDetail.getDealerShortName());
		inventoryOutApportion.setShopShortName(inventoryDetail.getShopShortName());
		inventoryOutApportion.setVoucherDetailId(voucherDetail.getId());
		inventoryOutApportion.setWarehouseId(inventoryDetail.getWarehouseId());
		inventoryOutApportion.setWarehouseName(inventoryDetail.getWarehouseName());
		inventoryOutApportion.setProductId(inventoryDetail.getProductId());
		inventoryOutApportion.setQty(qty);
		inventoryOutApportion.setPrice(inventoryDetail.getPrice());
		inventoryOutApportion.setTotal(qty * inventoryDetail.getPrice());
		
		if(isProduct)
			inventoryOutApportionDAO.save(inventoryOutApportion);
		else
			pinventoryOutApportionDAO.save(inventoryOutApportion);
	}
	
	//为了不把函数配成事务，函数名字中的update放到后面
	private void voucherUpdate(Voucher voucher) {
		voucherDAO.update(voucher);		
	}
	
	//与save(Voucher voucher, List<VoucherDetail> voucherDetailList, String prefix, boolean isError)方法一模一样
	//为了事务不嵌套（一起回滚），复制一遍，起名为voucherSave
	private void voucherSave(Voucher voucher, List<VoucherDetail> voucherDetailList, String prefix, boolean isError) {
		//如果是冲抵单据，则不需要生成单据编码(与原单据一样)
		//如果是修改单据，也不需要生成单据编码，但修改单据调用update()方法，这里不用处理
		if(! isError)
			voucher.setCode(codeCreatorService.createCode(prefix));
		
		long voucherId = voucherDAO.save(voucher);
		voucher.setId(voucherId); //为了保证单据save之后voucher的id也自动更新了，重新设置一遍id
		
		if(voucherDetailList != null && voucherDetailList.size() != 0) {
			
			for(int i = 0; i < voucherDetailList.size(); i++) {				
				voucherDetailList.get(i).setVoucherId(voucherId);
				voucherDetailDAO.save(voucherDetailList.get(i));
			}
		}
	}
	
	//与update(Voucher voucher, List<VoucherDetail> voucherDetailList)方法一模一样
	//为了事务不嵌套（一起回滚），复制一遍，起名为voucherUpdate
	private void voucherUpdate(Voucher voucher, List<VoucherDetail> voucherDetailList) {		
//		long voucherId = voucherDAO.update(voucher);
//		voucher.setId(voucherId); //为了保证单据update之后voucher的id也自动更新了，重新设置一遍id
		
		List<VoucherDetail> voucherDetailOldList = voucherDetailDAO.findByVoucherId(voucher.getId());
				
		boolean isHad = false;
		for(int i = 0; i < voucherDetailOldList.size(); i++) {	
			
			for(int j = 0; j < voucherDetailList.size(); j++) {	
				
				if(voucherDetailOldList.get(i).getId().equals(voucherDetailList.get(j).getId())) {
					isHad = true;
					break;
				}				
			}
			
			if(!isHad)
				voucherDetailDAO.deleteById(voucherDetailOldList.get(i).getId());
			else
				isHad = false;			
		}		
		
		for(int i = 0; i < voucherDetailList.size(); i++) {	
			voucherDetailList.get(i).setVoucherId(voucher.getId());	
			voucherDetailDAO.saveOrUpdate(voucherDetailList.get(i));
		}	
		
		voucherDAO.update(voucher); //voucherDAO的update方法用的是merge（new出一个新的voucher存进数据库），为了保证voucherDetail中的voucherId不是瞬态的（瞬态的不能保存更新），所以更新单据在明细的后面
	}	
	
	//与saveCheck(Voucher voucher, List<VoucherDetail> voucherDetailList, List<Long> voucherDetailIdList, String prefix, boolean isNew, boolean isError)方法一模一样
	//为了事务不嵌套（一起回滚），复制一遍，起名为voucherSaveCheck
	private void voucherSaveCheck(Voucher voucher, List<VoucherDetail> voucherDetailList, List<Long> voucherDetailIdList, String prefix, boolean isNew, boolean isError) {
		
		if(isNew)
			this.voucherSave(voucher, voucherDetailList, prefix, isError);
		else 		
			this.voucherUpdate(voucher, voucherDetailList);		
		
		/*************************整车单据扩展表*************************/
		if((! prefix.startsWith("P")) && (! isError)) { //配件单据不需要，冲抵单据不需要
			String [] productBarcodeArray = voucher.getProductBarcodeTxt().split("\r\n");
			
			VoucherEXT voucherEXT;			
			for(String productbarcode : productBarcodeArray) {
				voucherEXT = new VoucherEXT();
				voucherEXT.setDealerId(voucher.getDealerId());
				voucherEXT.setShopId(voucher.getShopId());
				voucherEXT.setVoucherId(voucher.getId());
				voucherEXT.setVoucherType(voucher.getType());
				voucherEXT.setProductBarcode(productbarcode);
				
				voucherEXTDAO.save(voucherEXT);
			}
		}
		
		/*************************库存账*************************/
		for(int i = 0; i < voucherDetailList.size(); i++) {
			double outTotal = 0;
			double inTotal = 0;
			
			boolean isProduct;
			if(voucherDetailList.get(i).getProductCode().startsWith("6"))
				isProduct = true;    //整车
			else
				isProduct = false;    //配件
			
			//库存明细
			if((voucher.getType().endsWith("进货单") && isError == false) || (voucher.getType().endsWith("调拨入库单") && isError == false) || (voucher.getType().endsWith("销售退货单") && isError == false) || (voucher.getType().endsWith("报溢单") && isError == false) || (voucher.getType().endsWith("销售单") && isError == true) || (voucher.getType().endsWith("调拨出库单") && isError == true) || (voucher.getType().endsWith("进货退货单") && isError == true) || (voucher.getType().endsWith("报损单") && isError == true)) {	//整车进货单、整车调拨入库单、整车销售退货单、整车报溢单和整车销售单冲抵、整车调拨出库单冲抵、整车进货退货单冲抵、整车报损单冲抵库存明细			
				if(! isError)
					this.inventoryDetailSave(voucher, voucherDetailList.get(i), isProduct);
				else {
					List<InventoryOutApportion> invnetoryOutApportionList;
					if(isProduct)
						invnetoryOutApportionList = inventoryOutApportionDAO.findByVoucherDetailId(voucherDetailIdList.get(i));
					else
						invnetoryOutApportionList = pinventoryOutApportionDAO.findByVoucherDetailId(voucherDetailIdList.get(i));
					inTotal = this.inventoryDetailSave(voucher, invnetoryOutApportionList, isProduct);					
				}				
			}
			if((voucher.getType().endsWith("销售单") && isError == false) || (voucher.getType().endsWith("调拨出库单") && isError == false) || (voucher.getType().endsWith("进货退货单") && isError == false) || (voucher.getType().endsWith("报损单") && isError == false) || (voucher.getType().endsWith("进货单") && isError == true) || (voucher.getType().endsWith("调拨入库单") && isError == true) || (voucher.getType().endsWith("销售退货单") && isError == true) || (voucher.getType().endsWith("报溢单") && isError == true)) {	//整车销售单、整车调拨出库单、整车进货退货单、整车报损单和整车进货单冲抵、整车调拨入库单冲抵、整车销售退货单冲抵、整车报溢单冲抵库存明细，先进先出	
				List<InventoryDetail> inventoryDetailList;
				if(isProduct)
					inventoryDetailList = inventoryDetailDAO.findInventoryDetail(voucher.getShopId(), voucher.getWarehouseId(), voucherDetailList.get(i).getProductId());
				else
					inventoryDetailList = pinventoryDetailDAO.findInventoryDetail(voucher.getShopId(), voucher.getWarehouseId(), voucherDetailList.get(i).getProductId());
				int qty = Math.abs(voucherDetailList.get(i).getQty());
				for(int j = 0; j < inventoryDetailList.size(); j++) {
					if(qty < inventoryDetailList.get(j).getQty()) {
						inventoryDetailList.get(j).setQty(inventoryDetailList.get(j).getQty() - qty);
						inventoryDetailList.get(j).setTotal(inventoryDetailList.get(j).getTotal() - qty * inventoryDetailList.get(j).getPrice());
						outTotal = outTotal + qty * inventoryDetailList.get(j).getPrice();
						if(isProduct)
							inventoryDetailDAO.update(inventoryDetailList.get(j));	
						else
							pinventoryDetailDAO.update(inventoryDetailList.get(j));
						this.inventoryOutApportionSave(inventoryDetailList.get(j), voucherDetailList.get(i), qty, isProduct);
						break;
					}
					if(qty == inventoryDetailList.get(j).getQty()) {
						outTotal = outTotal + qty * inventoryDetailList.get(j).getPrice();
						if(isProduct)
							inventoryDetailDAO.delete(inventoryDetailList.get(j));
						else
							pinventoryDetailDAO.delete(inventoryDetailList.get(j));
						this.inventoryOutApportionSave(inventoryDetailList.get(j), voucherDetailList.get(i), qty, isProduct);
						break;
					}
					if(qty > inventoryDetailList.get(j).getQty()) {
						qty = qty - inventoryDetailList.get(j).getQty();
						outTotal = outTotal + inventoryDetailList.get(j).getQty() * inventoryDetailList.get(j).getPrice();
						if(isProduct)
							inventoryDetailDAO.delete(inventoryDetailList.get(j));
						else
							pinventoryDetailDAO.delete(inventoryDetailList.get(j));
						this.inventoryOutApportionSave(inventoryDetailList.get(j), voucherDetailList.get(i), inventoryDetailList.get(j).getQty(), isProduct);
					}
				}
			}
			
			//库存
			Inventory inventory;
			if(isProduct)
				inventory = inventoryDAO.findProduct(voucher.getShopId(), voucher.getWarehouseId(), voucherDetailList.get(i).getProductId());
			else
				inventory = pinventoryDAO.findProduct(voucher.getShopId(), voucher.getWarehouseId(), voucherDetailList.get(i).getProductId());
			if((voucher.getType().endsWith("进货单") && isError == false) || (voucher.getType().endsWith("调拨入库单") && isError == false) || (voucher.getType().endsWith("销售退货单") && isError == false) || (voucher.getType().endsWith("报溢单") && isError == false) || (voucher.getType().endsWith("销售单") && isError == true) || (voucher.getType().endsWith("调拨出库单") && isError == true) || (voucher.getType().endsWith("进货退货单") && isError == true) || (voucher.getType().endsWith("报损单") && isError == true)) {	//整车进货单、整车调拨入库单、整车销售退货单、整车报溢单和整车销售单冲抵、整车调拨出库单冲抵、整车进货退货单冲抵、整车报损单冲抵库存			
				if(inventory == null) {
					inventory = new Inventory();
					inventory.setDealerId(voucher.getDealerId());
					inventory.setDealerShortName(voucher.getDealerShortName());
					inventory.setShopId(voucher.getShopId());
					inventory.setShopShortName(voucher.getShopShortName());
					inventory.setWarehouseId(voucher.getWarehouseId());
					inventory.setWarehouseName(voucher.getWarehouseName());
					inventory.setProductId(voucherDetailList.get(i).getProductId());
					inventory.setProductCode(voucherDetailList.get(i).getProductCode());
					inventory.setProductName(voucherDetailList.get(i).getProductName());				
					inventory.setProductColor(voucherDetailList.get(i).getProductColor());
					inventory.setProductUnit(voucherDetailList.get(i).getProductUnit());
					inventory.setQty(voucherDetailList.get(i).getQty());
					inventory.setTotal(voucherDetailList.get(i).getTotal());	
					if(inventory.getQty() == 0)
						inventory.setAveragePrice(0.0);
					else
						inventory.setAveragePrice(inventory.getTotal() / inventory.getQty());
					
					//上月库存
					InventoryLast inventoryLast = new InventoryLast();
					inventoryLast.setDealerId(voucher.getDealerId());
					inventoryLast.setDealerShortName(voucher.getDealerShortName());
					inventoryLast.setProductCode(voucherDetailList.get(i).getProductCode());
					inventoryLast.setProductColor(voucherDetailList.get(i).getProductColor());
					inventoryLast.setProductId(voucherDetailList.get(i).getProductId());
					inventoryLast.setProductName(voucherDetailList.get(i).getProductName());
					inventoryLast.setQty(0);
					inventoryLast.setShopId(voucher.getShopId());
					inventoryLast.setShopShortName(voucher.getShopShortName());
					inventoryLast.setWarehouseId(voucher.getWarehouseId());
					inventoryLast.setWarehouseName(voucher.getWarehouseName());
					
					//库存月报 
					List<String> dateList = GetLastMonth.lastMonth(voucher.getCheckDate().substring(0, 4), voucher.getCheckDate().substring(5, 7));					
					PSIProductMonthlyReport psiProductMonthlyReport = new PSIProductMonthlyReport();
					psiProductMonthlyReport.setReportYear(dateList.get(0));
					psiProductMonthlyReport.setReportMonth(dateList.get(1));
					psiProductMonthlyReport.setDealerId(voucher.getDealerId());
					psiProductMonthlyReport.setDealerShortName(voucher.getDealerShortName());
					psiProductMonthlyReport.setShopId(voucher.getShopId());
					psiProductMonthlyReport.setShopShortName(voucher.getShopShortName());
					psiProductMonthlyReport.setWarehouseId(voucher.getWarehouseId());
					psiProductMonthlyReport.setWarehouseName(voucher.getWarehouseName());
					psiProductMonthlyReport.setProductId(voucherDetailList.get(i).getProductId());
					psiProductMonthlyReport.setProductName(voucherDetailList.get(i).getProductName());
					psiProductMonthlyReport.setProductCode(voucherDetailList.get(i).getProductCode());
					psiProductMonthlyReport.setProductColor(voucherDetailList.get(i).getProductColor());
					psiProductMonthlyReport.setInitialQty(0);
					psiProductMonthlyReport.setInQty(0);
					psiProductMonthlyReport.setOutQty(0);
					psiProductMonthlyReport.setFinalQty(0);
					
					if(isProduct) {
						inventoryDAO.save(inventory);
						inventoryLastDAO.save(inventoryLast);
						pSIProductMonthlyReportDAO.save(psiProductMonthlyReport);
					}
					else {
						pinventoryDAO.save(inventory);
						pinventoryLastDAO.save(inventoryLast);
						pSIPartMonthlyReportDAO.save(psiProductMonthlyReport);
					}
				}
				else {
					inventory.setQty(inventory.getQty() + Math.abs(voucherDetailList.get(i).getQty()));
					if(! isError)
						inventory.setTotal(inventory.getTotal() + voucherDetailList.get(i).getTotal());
					else
						inventory.setTotal(inventory.getTotal() + inTotal);
					if(inventory.getQty() == 0)
						inventory.setAveragePrice(0.0);
					else
						inventory.setAveragePrice(inventory.getTotal() / inventory.getQty());
					
					if(isProduct)
						inventoryDAO.update(inventory);
					else
						pinventoryDAO.update(inventory);
				}
			}
			if((voucher.getType().endsWith("销售单") && isError == false) || (voucher.getType().endsWith("调拨出库单") && isError == false) || (voucher.getType().endsWith("进货退货单") && isError == false) || (voucher.getType().endsWith("报损单") && isError == false) || (voucher.getType().endsWith("进货单") && isError == true) || (voucher.getType().endsWith("调拨入库单") && isError == true) || (voucher.getType().endsWith("销售退货单") && isError == true) || (voucher.getType().endsWith("报溢单") && isError == true)) {	//整车销售单、整车调拨出库单、整车进货退货单、整车报损单和整车进货单冲抵、整车调拨入库单冲抵、整车销售退货单冲抵、整车报溢单冲抵库存				
				if(inventory != null) {
					inventory.setQty(inventory.getQty() - Math.abs(voucherDetailList.get(i).getQty()));
					inventory.setTotal(inventory.getTotal() - outTotal);
					if(inventory.getQty() == 0)
						inventory.setAveragePrice(0.0);
					else
						inventory.setAveragePrice(inventory.getTotal() / inventory.getQty());
					
					if(isProduct)
						inventoryDAO.update(inventory);
					else
						pinventoryDAO.update(inventory);
				}
			}
			
			//库存台账
			InventoryBook inventoryBook = new InventoryBook();
			inventoryBook.setDealerId(voucher.getDealerId());
			inventoryBook.setDealerShortName(voucher.getDealerShortName());
			inventoryBook.setShopId(voucher.getShopId());
			inventoryBook.setShopShortName(voucher.getShopShortName());
			inventoryBook.setWarehouseId(voucher.getWarehouseId());
			inventoryBook.setWarehouseName(voucher.getWarehouseName());
			inventoryBook.setProductId(voucherDetailList.get(i).getProductId());
			inventoryBook.setProductName(voucherDetailList.get(i).getProductName());
			inventoryBook.setVoucherId(voucher.getId());
			inventoryBook.setVoucherCode(voucher.getCode());
			inventoryBook.setVoucherType(voucher.getType());
			inventoryBook.setVoucherDate(voucher.getCheckDate());
			if(voucher.getType().endsWith("进货单"))
				inventoryBook.setShipOrderCode(voucher.getShipOrderCode());
			if(voucher.getType().endsWith("进货单") || voucher.getType().endsWith("调拨入库单") || voucher.getType().endsWith("销售退货单") || voucher.getType().endsWith("报溢单")) {	//整车进货单、整车调拨入库单、整车销售退货单、整车报溢单库存台账			
				inventoryBook.setInQty(voucherDetailList.get(i).getQty());
				inventoryBook.setOutQty(0);
				if(! isError)
					inventoryBook.setInTotal(voucherDetailList.get(i).getTotal());
				else
					inventoryBook.setInTotal(- outTotal);
				inventoryBook.setOutTotal(0.0);
			}
			if(voucher.getType().endsWith("销售单") || voucher.getType().endsWith("调拨出库单") || voucher.getType().endsWith("进货退货单") || voucher.getType().endsWith("报损单")) {	//整车销售单、整车调拨出库单、整车进货退货单、整车报损单库存台账
				inventoryBook.setOutQty(voucherDetailList.get(i).getQty());
				inventoryBook.setInQty(0);
				if(! isError)
					inventoryBook.setOutTotal(outTotal);
				else
					inventoryBook.setOutTotal(- inTotal);
				inventoryBook.setInTotal(0.0);
			}
			inventoryBook.setQty(inventory.getQty());
			inventoryBook.setTotal(inventory.getTotal());
			
			if(isProduct)
				inventoryBookDAO.save(inventoryBook);
			else
				pinventoryBookDAO.save(inventoryBook);
		}		
		
		/*************************应收应付账*************************/
		if(voucher.getType().endsWith("进货单") || voucher.getType().endsWith("销售单") || voucher.getType().endsWith("进货退货单") || voucher.getType().endsWith("销售退货单")) {
			
			//应收应付子账户
			SubAccount subAccount = subAccountDAO.findById(voucher.getSubAccountId());
			if(voucher.getType().endsWith("进货单") || voucher.getType().endsWith("销售单")) {
				if(voucher.getPaidMoney() == null)
					subAccount.setBalance(subAccount.getBalance() + voucher.getActualTotal());
				else
					subAccount.setBalance(subAccount.getBalance() + voucher.getActualTotal() - voucher.getPaidMoney());			
			}			
			if(voucher.getType().endsWith("进货退货单") || voucher.getType().endsWith("销售退货单")) {
				if(voucher.getPaidMoney() == null)
					subAccount.setBalance(subAccount.getBalance() - voucher.getActualTotal());
				else
					subAccount.setBalance(subAccount.getBalance() - voucher.getActualTotal() + voucher.getPaidMoney());
			}
			subAccountDAO.update(subAccount);
			
			//应收应付总账户
			Account account = accountDAO.findById(subAccount.getParentAccountId());
			if(voucher.getType().endsWith("进货单") || voucher.getType().endsWith("销售单")) {
				if(voucher.getPaidMoney() == null)
					account.setBalance(account.getBalance() + voucher.getActualTotal());			
				else
					account.setBalance(account.getBalance() + voucher.getActualTotal() - voucher.getPaidMoney());			
			}
			if(voucher.getType().endsWith("进货退货单") || voucher.getType().endsWith("销售退货单")) {
				if(voucher.getPaidMoney() == null)
					account.setBalance(account.getBalance() - voucher.getActualTotal());			
				else
					account.setBalance(account.getBalance() - voucher.getActualTotal() + voucher.getPaidMoney());	
			}
			accountDAO.update(account);
			
			//应收应付台账
			DealingBook dealingBook = new DealingBook();
			if(voucher.getType().endsWith("进货单") || voucher.getType().endsWith("进货退货单"))
				dealingBook.setType("应付");
			if(voucher.getType().endsWith("销售单") || voucher.getType().endsWith("销售退货单"))
				dealingBook.setType("应收");
			dealingBook.setSubAccountId(voucher.getSubAccountId());
			dealingBook.setSubAccountType(voucher.getSubAccountType());
			dealingBook.setDealerId(voucher.getDealerId());
			dealingBook.setDealerShortName(voucher.getDealerShortName());
			dealingBook.setDealingUnitId(voucher.getDealingUnitId());
			dealingBook.setDealingUnitShortName(voucher.getDealingUnitShortName());
			dealingBook.setVoucherId(voucher.getId());
			dealingBook.setVoucherCode(voucher.getCode());
			dealingBook.setVoucherType(voucher.getType());
			dealingBook.setVoucherDate(voucher.getCheckDate());
			if(voucher.getType().endsWith("进货单") || voucher.getType().endsWith("销售单"))
				dealingBook.setDueTotal(voucher.getActualTotal());
			if(voucher.getType().endsWith("进货退货单") || voucher.getType().endsWith("销售退货单"))
				dealingBook.setDueTotal(-voucher.getActualTotal());
			if(voucher.getPaidMoney() != null) {
				if(voucher.getType().endsWith("进货单") || voucher.getType().endsWith("销售单"))
					dealingBook.setActualTotal(voucher.getPaidMoney());
				if(voucher.getType().endsWith("进货退货单") || voucher.getType().endsWith("销售退货单"))
					dealingBook.setActualTotal(-voucher.getPaidMoney());
			}
			else
				dealingBook.setActualTotal(0.0);
			dealingBook.setBalance(subAccount.getBalance());
			dealingBookDAO.save(dealingBook);
		}
	}
	/************************************************************************************************************************************/
	
	
	
	//Service
	private CodeCreatorService codeCreatorService;
	public void setCodeCreatorService(CodeCreatorService codeCreatorService) {
		this.codeCreatorService = codeCreatorService;
	}
	
	//DAO
	private VoucherDAO voucherDAO;
	public void setVoucherDAO(VoucherDAO voucherDAO) {
		this.voucherDAO = voucherDAO;
	}

	private VoucherDetailDAO voucherDetailDAO;
	public void setVoucherDetailDAO(VoucherDetailDAO voucherDetailDAO) {
		this.voucherDetailDAO = voucherDetailDAO;
	}
	
	private VoucherEXTDAO voucherEXTDAO;
	public void setVoucherEXTDAO(VoucherEXTDAO voucherEXTDAO) {
		this.voucherEXTDAO = voucherEXTDAO;
	}
	private InventoryDAO inventoryDAO;
	public void setInventoryDAO(InventoryDAO inventoryDAO) {
		this.inventoryDAO = inventoryDAO;
	}
	
	private InventoryDAO pinventoryDAO;	
	public void setPinventoryDAO(InventoryDAO pinventoryDAO) {
		this.pinventoryDAO = pinventoryDAO;
	}

	private InventoryDetailDAO inventoryDetailDAO;
	public void setInventoryDetailDAO(InventoryDetailDAO inventoryDetailDAO) {
		this.inventoryDetailDAO = inventoryDetailDAO;
	}
	
	private InventoryDetailDAO pinventoryDetailDAO;	
	public void setPinventoryDetailDAO(InventoryDetailDAO pinventoryDetailDAO) {
		this.pinventoryDetailDAO = pinventoryDetailDAO;
	}

	private InventoryBookDAO inventoryBookDAO;
	public void setInventoryBookDAO(InventoryBookDAO inventoryBookDAO) {
		this.inventoryBookDAO = inventoryBookDAO;
	}
	
	private InventoryBookDAO pinventoryBookDAO;	
	public void setPinventoryBookDAO(InventoryBookDAO pinventoryBookDAO) {
		this.pinventoryBookDAO = pinventoryBookDAO;
	}

	private InventoryOutApportionDAO inventoryOutApportionDAO;
	public void setInventoryOutApportionDAO(InventoryOutApportionDAO inventoryOutApportionDAO) {
		this.inventoryOutApportionDAO = inventoryOutApportionDAO;
	}
	
	private InventoryOutApportionDAO pinventoryOutApportionDAO;
	public void setPinventoryOutApportionDAO(InventoryOutApportionDAO pinventoryOutApportionDAO) {
		this.pinventoryOutApportionDAO = pinventoryOutApportionDAO;
	}

	private AccountDAO accountDAO;
	public void setAccountDAO(AccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}
	
	private SubAccountDAO subAccountDAO;
	public void setSubAccountDAO(SubAccountDAO subAccountDAO) {
		this.subAccountDAO = subAccountDAO;
	}
	
	private DealingBookDAO dealingBookDAO;
	public void setDealingBookDAO(DealingBookDAO dealingBookDAO) {
		this.dealingBookDAO = dealingBookDAO;
	}

	private EmployeeDAO employeeDAO;
	public void setEmployeeDAO(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}

	private OrderDAO orderDAO;
	public void setOrderDAO(OrderDAO orderDAO) {
		this.orderDAO = orderDAO;
	}

	private OrderDetailDAO orderDetailDAO;
	public void setOrderDetailDAO(OrderDetailDAO orderDetailDAO) {
		this.orderDetailDAO = orderDetailDAO;
	}
	
	private PartOrderDAO partOrderDAO;
	public void setPartOrderDAO(PartOrderDAO partOrderDAO) {
		this.partOrderDAO = partOrderDAO;
	}
	
	private PartOrderDetailDAO partOrderDetailDAO;
	public void setPartOrderDetailDAO(PartOrderDetailDAO partOrderDetailDAO) {
		this.partOrderDetailDAO = partOrderDetailDAO;
	}
	
	private InventoryLastDAO inventoryLastDAO;
	public void setInventoryLastDAO(InventoryLastDAO inventoryLastDAO) {
		this.inventoryLastDAO = inventoryLastDAO;
	}
	
	private InventoryLastDAO pinventoryLastDAO;
	public void setPinventoryLastDAO(InventoryLastDAO pinventoryLastDAO) {
		this.pinventoryLastDAO = pinventoryLastDAO;
	}
	
	private PSIProductMonthlyReportDAO pSIProductMonthlyReportDAO;
	public void setpSIProductMonthlyReportDAO(
			PSIProductMonthlyReportDAO pSIProductMonthlyReportDAO) {
		this.pSIProductMonthlyReportDAO = pSIProductMonthlyReportDAO;
	}
	
	private PSIProductMonthlyReportDAO pSIPartMonthlyReportDAO;
	public void setpSIPartMonthlyReportDAO(
			PSIProductMonthlyReportDAO pSIPartMonthlyReportDAO) {
		this.pSIPartMonthlyReportDAO = pSIPartMonthlyReportDAO;
	}
}
