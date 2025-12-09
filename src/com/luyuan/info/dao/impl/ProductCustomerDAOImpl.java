/**
 * @(#)AccountDAOImpl.java  1.0 10/04/13
 *
 * Copyright 2010 luyuan-ebike, Inc. All rights reserved.
 * 
 */
package com.luyuan.info.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.luyuan.action.IFieldValidation;
import com.luyuan.info.dao.ProductCustomerDAO;
import com.luyuan.info.entity.ProductCustomer;
import com.luyuan.util.Page;
import com.luyuan.util.PaginateHib;
import com.luyuan.util.SQLConnection;



public class ProductCustomerDAOImpl extends PaginateHib implements ProductCustomerDAO {
	
	public void save(ProductCustomer transientInstance) {
		log.debug("saving ProductCustomer instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	public ProductCustomer findByProductBarcode(String productBarcode) {
		List<ProductCustomer> list = this.findByProperty("productBarcode", productBarcode);
		if(list != null && list.size() != 0)
			return list.get(0);
		return null;
	}
	
	public List<ProductCustomer> findProductCustomer(final IFieldValidation act, final String dealerCode, final String buyStartDate, final String buyEndDate, final String inputStartDate, final String inputEndDate, boolean history) {
		log.debug("finding ProductCustomer instance with dealerCode: " + dealerCode);
//		SqlCallback sqlCallback = new SqlCallback(){
//			public void setPageSql(Page page) {				
//				page.getAttachSql().append(" and model.dealerCode='").append(dealerCode).append("'");
//				if((! buyStartDate.trim().equals("")) && (! buyEndDate.trim().equals("")))
//					page.getAttachSql().append(" and model.buyDate between '").append(buyStartDate).append("' and '").append(buyEndDate).append("'");
//				if((! inputStartDate.trim().equals("")) && (! inputEndDate.trim().equals("")))
//					page.getAttachSql().append(" and model.inputDate between '").append(inputStartDate).append("' and '").append(inputEndDate).append(" 23:59:59'");
//				page.getAttachSql().append(" order by model.inputDate desc ");			
//			}
//		};	
//		return this.findList(act, sqlCallback);		
		
		List<ProductCustomer> productCustomerList = new ArrayList<ProductCustomer>();
		Page page = act.getPage();
		
		if(this.validateRange(act))
			return productCustomerList;
		
		//初始化
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "";
        ResultSet rs = null;	
        
        //开启连接        
        try {
        	Class.forName(SQLConnection.driverName);
        	conn = DriverManager.getConnection(SQLConnection.lyCRMURL, SQLConnection.lyCRMUserName, SQLConnection.lyCRMPassWord);			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("查询服务卡信息，开启连接失败");
			System.out.println(e);
		}		
		
		if(history)
			sql = "select * from ProductCustomerHistory";
		else
			sql = "select * from ProductCustomer";
		sql = sql + " where DealerCode='" + dealerCode + "'";
		if((! buyStartDate.trim().equals("")) && (! buyEndDate.trim().equals("")))
			sql = sql + " and BuyDate between '" + buyStartDate + "' and '" + buyEndDate + "'";
		if((! inputStartDate.trim().equals("")) && (! inputEndDate.trim().equals("")))
			sql = sql + " and InputDate between '" + inputStartDate + "' and '" + inputEndDate + " 23:59:59'";
				
		List<String> list =  page.getPropsNameList();
		if (list != null && list.size() > 0){
			for (String propertyName : list) {
				propertyName = propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1); //表中字段的首字母大写
				sql = sql + " and " + propertyName + " like ?";
			}
		}
		sql = sql + " order by InputDate desc ";		
		
		try {
			pstmt=conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			pstmt.setMaxRows((Integer.valueOf(page.getCurStr()) - 1) * page.getSize() + page.getSize());
			
			list = page.getPropsValueList();
			int i = 0;
			if (list != null && list.size() > 0){			
				for (String value : list)
					pstmt.setString( ++i, "%"+value+"%");
			}
			
			rs = pstmt.executeQuery(); 
			rs.absolute((Integer.valueOf(page.getCurStr()) - 1) * page.getSize());
			
			
			ProductCustomer productCustomer;
			while(rs.next()) {
				productCustomer = new ProductCustomer();
				this.obtainProductCustomer(productCustomer, rs);
				productCustomerList.add(productCustomer);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("查询服务卡信息，查询失败");
			System.out.println(e);
		}
		
		//关闭连接
		try {
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("查询服务卡信息，关闭连接失败");
		}
		
		return productCustomerList;
	}
	
	public int obtainTotalCount(IFieldValidation act, String dealerCode, String buyStartDate, String buyEndDate, String inputStartDate, String inputEndDate, boolean history) {
		Page page = act.getPage();
		int count = 0;
		
        //初始化
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "";
        ResultSet rs = null;	
        
        try {
        	//开启连接        
        	Class.forName(SQLConnection.driverName);
        	conn = DriverManager.getConnection(SQLConnection.lyCRMURL, SQLConnection.lyCRMUserName, SQLConnection.lyCRMPassWord);			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("查询服务卡总记录数，开启连接失败");
			System.out.println(e);
		}
	
		if(history)
			sql = "select count(1) from ProductCustomerHistory";
		else
			sql = "select count(1) from ProductCustomer";
		sql = sql + " where DealerCode='" + dealerCode + "'";
		if((! buyStartDate.trim().equals("")) && (! buyEndDate.trim().equals("")))
			sql = sql + " and BuyDate between '" + buyStartDate + "' and '" + buyEndDate + "'";
		if((! inputStartDate.trim().equals("")) && (! inputEndDate.trim().equals("")))
			sql = sql + " and InputDate between '" + inputStartDate + "' and '" + inputEndDate + " 23:59:59'";
		
		List<String> list =  page.getPropsNameList();
		if (list != null && list.size() > 0){
			for (String propertyName : list) {
				propertyName = propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1); //表中字段的首字母大写
				sql = sql + " and " + propertyName + " like ?";
			}
		}		
		
		try {
			pstmt=conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			list = page.getPropsValueList();
			int i = 0;
			if (list != null && list.size() > 0){			
				for (String value : list)					
					pstmt.setString( ++i, "%"+value+"%");
			}
			
			rs = pstmt.executeQuery();
			rs.next();
			count = rs.getInt(1);
			page.setTail(count);
			rs.close();
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("查询服务卡总记录数，查询失败");
			System.out.println(e);
		}
		
		//关闭连接
		try {
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("查询服务卡总记录数，关闭连接失败");
		}
		
		return count;
	}
	
	
	//private method
	private List<ProductCustomer> findByProperty(String propertyName, Object value) {
		log.debug("finding ProductCustomer instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from "+ className + " as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}	
	
	private ProductCustomer obtainProductCustomer(ProductCustomer productCustomer, ResultSet rs) {
		
		try {
			productCustomer.setProductCustomerId(rs.getInt("ProductCustomerId"));
			productCustomer.setProductBarcode(rs.getString("ProductBarcode"));
			productCustomer.setDealerCode(rs.getString("DealerCode"));
			productCustomer.setDealerName(rs.getString("DealerName"));
			productCustomer.setProductPrefixName(rs.getString("ProductPrefixName"));
			productCustomer.setProductSuffixName(rs.getString("ProductSuffixName"));
			productCustomer.setProductSpecName(rs.getString("ProductSpecName"));
			productCustomer.setWheelSpecCode(rs.getString("WheelSpecCode"));
			productCustomer.setWheelDiameter(rs.getString("WheelDiameter"));
			productCustomer.setColorCode(rs.getString("ColorCode"));
			productCustomer.setColorName(rs.getString("ColorName"));
			productCustomer.setProductTypeName(rs.getString("ProductTypeName"));
			productCustomer.setControlBarcode(rs.getString("ControlBarcode"));
			productCustomer.setFrameBarcode(rs.getString("FrameBarcode"));
			productCustomer.setHubBarcode(rs.getString("HubBarcode"));
			productCustomer.setListCategoryCode(rs.getString("ListCategoryCode"));
			productCustomer.setListCategoryName(rs.getString("ListCategoryName"));
			productCustomer.setListCategory(rs.getString("ListCategory"));
			productCustomer.setServiceCardNumber(rs.getString("ServiceCardNumber"));
			productCustomer.setCustomerName(rs.getString("CustomerName"));
			productCustomer.setCustomerSex(rs.getString("CustomerSex"));
			productCustomer.setCustomerAddress(rs.getString("CustomerAddress"));
			productCustomer.setCustomerPhone(rs.getString("CustomerPhone"));
			productCustomer.setCustomerCellPhone(rs.getString("CustomerCellPhone"));
			productCustomer.setCustomerIdentification(rs.getString("CustomerIdentification"));
			productCustomer.setBuyDate(rs.getTimestamp("BuyDate"));
			productCustomer.setInputDate(rs.getTimestamp("InputDate"));
			productCustomer.setShipOrderCode(rs.getString("ShipOrderCode"));
			productCustomer.setProductionPlace(rs.getString("ProductionPlace"));
			productCustomer.setInspectionDate(rs.getTimestamp("InspectionDate"));
			productCustomer.setProductionLine(rs.getString("ProductionLine"));
			productCustomer.setWarehouseCode(rs.getString("WarehouseCode"));
			productCustomer.setOrgDealerId(rs.getString("OrgDealerId"));
			productCustomer.setProductCode(rs.getString("ProductCode"));
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("查询服务卡信息，从结果集获得结果失败");
			System.out.println(e);
		}
		
		return productCustomer;
	}
	
	//日志
	private static final Log log = LogFactory.getLog(ProductCustomerDAOImpl.class);	
}