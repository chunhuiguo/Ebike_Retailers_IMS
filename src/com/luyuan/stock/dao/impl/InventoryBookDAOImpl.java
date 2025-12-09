/**
 * @(#)InventoryBookDAOImpl.java  1.0 10/04/11
 *
 * Copyright 2010 luyuan-ebike, Inc. All rights reserved.
 * 
 */
package com.luyuan.stock.dao.impl;

import java.sql.SQLException;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.luyuan.action.IFieldValidation;
import com.luyuan.report.entity.PSIProductMonthlyReport;
import com.luyuan.report.model.Report;
import com.luyuan.stock.dao.InventoryBookDAO;
import com.luyuan.stock.entity.InventoryBook;
import com.luyuan.stock.entity.PInventoryBook;
import com.luyuan.util.Page;
import com.luyuan.util.PaginateHib;
import com.luyuan.util.SqlCallback;

/**
 * 
 * 最终会被注入的InventoryBookDAO，实现DAO接口逻辑
 *
 * @see com.luyuan.stock.entity.InventoryBook
 * @author gch
 */

public class InventoryBookDAOImpl extends PaginateHib implements InventoryBookDAO {
	
	//gch
	public void save(InventoryBook transientInstance) {
		log.debug("saving " + className + " instance");
		try {
			if(className.equals("PInventoryBook"))
				getHibernateTemplate().save(this.inventoryBookCopy(transientInstance));
			else
				getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	public List<InventoryBook> findInventoryBook(String voucherCode) {
		log.debug("finding " + className + " instance with voucherCode: " + voucherCode);
		try {
			List<InventoryBook> list = getHibernateTemplate().find(
					"from " + className + " as model where model.voucherCode='"	+ voucherCode + "'");
			return list;
				
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}		
	}
	
	public List<InventoryBook> findInventoryBook(int warehouseId, long productId) {
		log.debug("finding " + className + " instance with warehouseId: " + warehouseId + " and productId:" + productId);
		try {
			List<InventoryBook> list = getHibernateTemplate().find(
					"from " + className + " as model where model.warehouseId="	+ warehouseId + " and model.productId=" + productId);
			if(list != null && list.size() != 0)				
				return list;
			else
				return null;				
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}		
	}
	
	public List<InventoryBook> findInventoryBook(final IFieldValidation act, final int shopId, final String startDate, final String endDate, final boolean isDealer) {
		log.debug("finding " + className + " instance with shopId: " + shopId + " and voucherDate between " + startDate + " and " + endDate);
		SqlCallback sqlCallback = new SqlCallback(){
			public void setPageSql(Page page) {
				if(isDealer)
					page.getAttachSql().append(" and model.dealerId=").append(shopId).append(" and model.voucherDate between '" + startDate + "' and '" + endDate + "'").append(" order by model.voucherId desc");
				else
					page.getAttachSql().append(" and model.shopId=").append(shopId).append(" and model.voucherDate between '" + startDate + "' and '" + endDate + "'").append(" order by model.voucherId desc");				
			}
		};
		return this.findList(act, sqlCallback);		
	}
	
	public List<InventoryBook> findInventoryBookWithShop(final IFieldValidation act, final int shopId, final String startDate, final String endDate) {
		log.debug("finding " + className + " instance with shopId: " + shopId + " and voucherDate between " + startDate + " and " + endDate);
		SqlCallback sqlCallback = new SqlCallback(){
			public void setPageSql(Page page) {
				page.getAttachSql().append(" and model.shopId=").append(shopId).append(" and model.voucherDate between '" + startDate + "' and '" + endDate + "'").append(" order by model.voucherId desc");
			}
		};
		return this.findList(act, sqlCallback);	
	}
	
	public List<InventoryBook> findInventoryBookWithWarehouse(final IFieldValidation act, final int warehouseId, final String startDate, final String endDate) {
		log.debug("finding " + className + " instance with warehouseId: " + warehouseId + " and voucherDate between " + startDate + " and " + endDate);
		SqlCallback sqlCallback = new SqlCallback(){
			public void setPageSql(Page page) {
				page.getAttachSql().append(" and model.warehouseId=").append(warehouseId).append(" and model.voucherDate between '" + startDate + "' and '" + endDate + "'").append(" order by model.voucherId desc");
			}
		};
		return this.findList(act, sqlCallback);	
	}
	
	public List<InventoryBook> findInventoryBook(final IFieldValidation act, final int shopId, final int warehouseId, final String startDate, final String endDate) {
		log.debug("finding " + className + " instance with shopId: " + shopId + " and warehouseId: " + warehouseId + " and voucherDate between " + startDate + " and " + endDate);
		SqlCallback sqlCallback = new SqlCallback(){
			public void setPageSql(Page page) {
				page.getAttachSql().append(" and model.shopId=").append(shopId).append(" and model.warehouseId=").append(warehouseId).append(" and model.voucherDate between '" + startDate + "' and '" + endDate + "'").append(" order by model.voucherId desc");
			}
		};
		return this.findList(act, sqlCallback);	
	}
	
	public List<InventoryBook> findAllInventoryBook(final IFieldValidation act, final int shopId, final String startDate, final String endDate, final boolean isDealer) {		
		//设置每页显示1000000000（尽量大的数）条，为的是把所有结果都得到，用于导出Excel
		act.getPage().setSize(1000000000);
		
		log.debug("finding " + className + " instance with shopId: " + shopId + " and voucherDate between " + startDate + " and " + endDate);
		SqlCallback sqlCallback = new SqlCallback(){
			public void setPageSql(Page page) {
				if(isDealer)
					page.getAttachSql().append(" and model.dealerId=").append(shopId).append(" and model.voucherDate between '" + startDate + "' and '" + endDate + "'").append(" order by model.voucherId desc");
				else
					page.getAttachSql().append(" and model.shopId=").append(shopId).append(" and model.voucherDate between '" + startDate + "' and '" + endDate + "'").append(" order by model.voucherId desc");				
			}
		};
		
		List list = this.findList(act, sqlCallback);
		//每页显示的条数设置为原来的20条，不影响用于页面显示的查询功能
		act.getPage().setSize(20);
		return list;			
	}
	
	public List<InventoryBook> findAllInventoryBookWithShop(final IFieldValidation act, final int shopId, final String startDate, final String endDate) {
		//设置每页显示10000（尽量大的数）条，为的是把所有结果都得到，用于导出Excel
		act.getPage().setSize(10000);
		
		log.debug("finding " + className + " instance with shopId: " + shopId + " and voucherDate between " + startDate + " and " + endDate);
		SqlCallback sqlCallback = new SqlCallback(){
			public void setPageSql(Page page) {
				page.getAttachSql().append(" and model.shopId=").append(shopId).append(" and model.voucherDate between '" + startDate + "' and '" + endDate + "'").append(" order by model.voucherId desc");
			}
		};
		
		List list = this.findList(act, sqlCallback);
		//每页显示的条数设置为原来的20条，不影响用于页面显示的查询功能
		act.getPage().setSize(20);
		return list;
	}
	
	public List<InventoryBook> findAllInventoryBookWithWarehouse(final IFieldValidation act, final int warehouseId, final String startDate, final String endDate) {
		//设置每页显示10000（尽量大的数）条，为的是把所有结果都得到，用于导出Excel
		act.getPage().setSize(10000);
		
		log.debug("finding " + className + " instance with warehouseId: " + warehouseId + " and voucherDate between " + startDate + " and " + endDate);
		SqlCallback sqlCallback = new SqlCallback(){
			public void setPageSql(Page page) {
				page.getAttachSql().append(" and model.warehouseId=").append(warehouseId).append(" and model.voucherDate between '" + startDate + "' and '" + endDate + "'").append(" order by model.voucherId desc");
			}
		};
		
		List list = this.findList(act, sqlCallback);
		//每页显示的条数设置为原来的20条，不影响用于页面显示的查询功能
		act.getPage().setSize(20);
		return list;
	}
	
	public List<InventoryBook> findAllInventoryBook(final IFieldValidation act, final int shopId, final int warehouseId, final String startDate, final String endDate) {
		//设置每页显示10000（尽量大的数）条，为的是把所有结果都得到，用于导出Excel
		act.getPage().setSize(10000);
		
		log.debug("finding " + className + " instance with shopId: " + shopId + " and warehouseId: " + warehouseId + " and voucherDate between " + startDate + " and " + endDate);
		SqlCallback sqlCallback = new SqlCallback(){
			public void setPageSql(Page page) {
				page.getAttachSql().append(" and model.shopId=").append(shopId).append(" and model.warehouseId=").append(warehouseId).append(" and model.voucherDate between '" + startDate + "' and '" + endDate + "'").append(" order by model.voucherId desc");
			}
		};
		
		List list = this.findList(act, sqlCallback);
		//每页显示的条数设置为原来的20条，不影响用于页面显示的查询功能
		act.getPage().setSize(20);
		return list;
	}
	
	//ldf
	public List<Report> findInventoryBook(Integer shopId, String date) {
		log.debug("finding " + className + " instance with shopId: " + shopId + 
				" and voucherDate: " + date);
		try {
			List<Report> list = getHibernateTemplate().find(
					"select new com.luyuan.report.model.Report(book.productId ,sum(book.inQty),sum(book.outQty)) from " + className + " as book where book.shopId="+ shopId +" and book.voucherDate='" + date+"' group by book.productId" );
			return list;
				
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	
//	public List<MonthInventory> findInventory(Integer warehouseId, String date) {
//		
//			List<MonthInventory> list = getHibernateTemplate().find(
//					"select new com.luyuan.report.model.MonthInventory(year((v.checkDate), month(v.checkDate),v.dealerId, v.dealerShortName, v.shopId, v.shopShortName, v.warehouseId,  v.warehouseName, book.productId, book.productName, vd.productCode,vd.productColor," +
//					"sum(book.inQty),sum(book.inTotal),sum(book.outQty),sum(book.outTotal)) from com.luyuan.deal.entity.Voucher as v inner join InventoryBook as book on v.id=book.voucherId inner join com.luyuan.deal.entity.VoucherDetail as vd on v.id=vd.voucherId" +
//					" where v.warehouseId="+ warehouseId +" and v.voucherDate='" + date+
//					"' group by v.dealerId, v.shopId, v.warehouseId, book.productId, book.productName, v.shopShortName, v.dealerShortName, v.warehouseName, v.checkDate, vd.productCode,vd.productColor ");
//			return list;		
//	}
    public List findInventory(final String checkDate, final int warehouseId){
		
		return  (List)this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {
				String procName = "{Call sys_dayReport_GetByInventorryBook(?,?)}";
				SQLQuery query = session.createSQLQuery(procName);
				query.setString(0, checkDate); 
				query.setInteger(1, warehouseId); 
				List opList = query.list();
				return opList;
			}
		});	
	}
    public List findInventory(final String beginDate,final String endDate,final int warehouseId){
    	
    	return  (List)this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
			throws HibernateException, SQLException {
				String procName = "{Call sys_dayReport_GetByWarehouseAndTime(?,?,?)}";
				SQLQuery query = session.createSQLQuery(procName);
				query.setInteger(0, warehouseId); 
				query.setString(1, beginDate); 
				query.setString(2, endDate); 
				
				List opList = query.list();
				return opList;
			}
		});
    }

	
	//class inside method
	private PInventoryBook inventoryBookCopy(InventoryBook inventoryBook) {
		PInventoryBook pinventoryBook = new PInventoryBook();
		
		pinventoryBook.setId(inventoryBook.getId());
		pinventoryBook.setDealerId(inventoryBook.getDealerId());
		pinventoryBook.setShopId(inventoryBook.getShopId());
		pinventoryBook.setDealerShortName(inventoryBook.getDealerShortName());
		pinventoryBook.setShopShortName(inventoryBook.getShopShortName());
		pinventoryBook.setWarehouseId(inventoryBook.getWarehouseId());
		pinventoryBook.setWarehouseName(inventoryBook.getWarehouseName());
		pinventoryBook.setProductId(inventoryBook.getProductId());
		pinventoryBook.setProductName(inventoryBook.getProductName());
		pinventoryBook.setVoucherId(inventoryBook.getVoucherId());
		pinventoryBook.setVoucherCode(inventoryBook.getVoucherCode());
		pinventoryBook.setVoucherType(inventoryBook.getVoucherType());
		pinventoryBook.setVoucherDate(inventoryBook.getVoucherDate());
		pinventoryBook.setShipOrderCode(inventoryBook.getShipOrderCode());
		pinventoryBook.setInQty(inventoryBook.getInQty());
		pinventoryBook.setInTotal(inventoryBook.getInTotal());
		pinventoryBook.setOutQty(inventoryBook.getOutQty());
		pinventoryBook.setOutTotal(inventoryBook.getOutTotal());
		pinventoryBook.setQty(inventoryBook.getQty());
		pinventoryBook.setTotal(inventoryBook.getTotal());
		pinventoryBook.setRemark(inventoryBook.getRemark());
		
		return pinventoryBook;
	}
	
	
	
	//日志
	private static final Log log = LogFactory.getLog(InventoryBookDAOImpl.class);	

}