/**
 * @(#)EmployeeAct.java  1.0 10/04/15
 *
 * Copyright 2010 luyuan-ebike, Inc. All rights reserved.
 * 
 */
package com.luyuan.info.action;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.struts2.ServletActionContext;

import com.luyuan.action.ExcelListAct;
import com.luyuan.info.biz.ProductCustomerBiz;
import com.luyuan.info.biz.ServiceCardBiz;
import com.luyuan.info.entity.InsuranceCard;
import com.luyuan.info.entity.ProductCustomer;
import com.luyuan.stock.entity.InventoryBookView;
import com.luyuan.util.Page;


public class ServiceCardListAct extends ExcelListAct {
	
	//进入
	//“重置”
	public String execute() {
		select = null;
		page =  new Page();
		page.setListAct("/info/serviceCardList!select.html");
		inputEndDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		inputStartDate = inputEndDate.substring(0, 8) + "01";
		buyStartDate="";
		buyEndDate="";
		history = false;
		if(! this.getSession().get("unitType").equals("经销商"))
			dealerShortName = (String) this.getSession().get("parentShortName");
		else
			dealerShortName = (String) this.getSession().get("shortName");				
		
		if(productCustomerList != null)
			productCustomerList.clear();
//		productCustomerList = this.productCustomerList();
		
		//记录总条数
		totalCount = 0;
		
		return "success";
	}
	
	public String select() {
		//翻页
		if(productCustomerList != null)
			productCustomerList.clear();
		productCustomerList = this.productCustomerList();		
		return "success";		
	}
	
	public String detail() {
		//详细
		productCustomer = productCustomerList.get(select);
		if(insuranceCardList != null)
			insuranceCardList.clear();
		insuranceCardList = serviceCardBiz.findInsuranceCard(productCustomer.getProductBarcode());
		return "detail";
	}
	
	//查看详细的“返回”
	public String back() {
		select = null;
		return "success";
	}
	
	//“筛选”
	public String filter() {		
		if(productCustomerList != null)
			productCustomerList.clear();
		productCustomerList = this.productCustomerList();
		
		//记录总条数
		totalCount = 0;
		this.obtainTotalCount();
		
		return "success";
	}
	
	//“导出Excel”
	public String exportExcel() throws IOException {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("服务卡信息");
		//列宽
		this.setColumnWidth(sheet, this.getColumnWidthList());
		HSSFRow row;
		
		//第1行
		row = sheet.createRow(0);
		row.createCell(0).setCellValue("服务卡信息查询结果");		
		//合并单元格
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 18));
		//格式
		this.setRowStyle(row, this.getTitleStytle(workbook));
		
		//第2行
		row = sheet.createRow(1);
		row.createCell(0).setCellValue("序号");
		row.createCell(1).setCellValue("用户姓名");
		row.createCell(2).setCellValue("手机号码");
		row.createCell(3).setCellValue("整车编码");
		row.createCell(4).setCellValue("整车型号");
		row.createCell(5).setCellValue("购车日期");
		row.createCell(6).setCellValue("输卡日期");
		row.createCell(7).setCellValue("车架编号");
		row.createCell(8).setCellValue("电机编号");
		row.createCell(9).setCellValue("控制器编号");
		row.createCell(10).setCellValue("地址");
		row.createCell(11).setCellValue("联系电话");
		row.createCell(12).setCellValue("经销单位");
		row.createCell(13).setCellValue("保险人姓名");
		row.createCell(14).setCellValue("保险人身份证");
		row.createCell(15).setCellValue("保险人手机");
		row.createCell(16).setCellValue("保险人姓名");
		row.createCell(17).setCellValue("保险人身份证");
		row.createCell(18).setCellValue("保险人手机");
		//格式
		this.setRowStyle(row, this.getTitleStytle(workbook));
		
		//设置“内容”单元格的格式
		for(int i = 0; i < 19; i++)
			sheet.setDefaultColumnStyle(i, this.getContentStytle(workbook));	
		
		//记录当前分页信息，方便还原
		String curStr = page.getCurStr();
		int cur = page.getCur();
		//从第1页开始得到所有记录
		List<ProductCustomer> productCustomerAllList = new ArrayList<ProductCustomer>();
		for(int i = 0; i < page.getTail(); i++) {
			page.setCurStr(Integer.toString(i + 1));
			productCustomerAllList.addAll(this.productCustomerList());
		}
		//还原分页信息
		page.setCur(cur);
		page.setCurStr(curStr);
		
		if(productCustomerAllList != null && productCustomerAllList.size() != 0) {			
			//第3~n行
			for(int i = 0; i < productCustomerAllList.size(); i++) {
				productCustomer = productCustomerAllList.get(i);
				row = sheet.createRow(i + 2);
				row.createCell(0).setCellValue(i + 1);
				//服务卡信息
				row.createCell(1).setCellValue(productCustomer.getCustomerName());
				row.createCell(2).setCellValue(productCustomer.getCustomerCellPhone());
				row.createCell(3).setCellValue(productCustomer.getProductBarcode());
				row.createCell(4).setCellValue(productCustomer.getProductPrefixName());
				row.createCell(5).setCellValue(productCustomer.getBuyDate().toString().substring(0, 10));
				row.createCell(6).setCellValue(productCustomer.getInputDate().toString().substring(0, 10));
				row.createCell(7).setCellValue(productCustomer.getFrameBarcode());
				row.createCell(8).setCellValue(productCustomer.getHubBarcode());
				row.createCell(9).setCellValue(productCustomer.getControlBarcode());
				row.createCell(10).setCellValue(productCustomer.getCustomerAddress());
				row.createCell(11).setCellValue(productCustomer.getCustomerPhone());
				row.createCell(12).setCellValue(productCustomer.getDealerCode());
				
				//保险卡信息
				if(insuranceCardList != null)
					insuranceCardList.clear();
				insuranceCardList = serviceCardBiz.findInsuranceCard(productCustomer.getProductBarcode());
				if(insuranceCardList != null && insuranceCardList.size() != 0) {
					for(int j = 0; j < insuranceCardList.size(); j++) {
						row.createCell(13 + 3 * j).setCellValue(insuranceCardList.get(j).getCustomerName());
						row.createCell(14 + 3 * j).setCellValue(insuranceCardList.get(j).getIdentityCode());
						row.createCell(15 + 3 * j).setCellValue(insuranceCardList.get(j).getCellPhone());
					}
				}
			}
		}
		
		HttpServletResponse response = ServletActionContext.getResponse();
		//解决中文乱码问题
		String fileName = URLEncoder.encode("服务卡信息查询结果", "UTF-8");
		response.setHeader("Content-Type","application/vnd.ms-excel");
        response.setHeader("Content-Disposition","attachment;filename=" + fileName + ".xls");
		OutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		outputStream.flush();
		outputStream.close();
		//避免getOutputStream()方法已经得调用的异常
		return null;
	}	
	
	//private method
	//得到各列的宽度
	private List<Integer> getColumnWidthList() {
		List<Integer> columnWidthList = new ArrayList<Integer>();
		//宽度：手动调整Excel得到列宽的像素值
		columnWidthList.add(37);
		columnWidthList.add(75);
		columnWidthList.add(95);
		columnWidthList.add(137);
		columnWidthList.add(100);
		columnWidthList.add(90);
		columnWidthList.add(90);
		columnWidthList.add(127);
		columnWidthList.add(137);
		columnWidthList.add(90);
		columnWidthList.add(300);
		columnWidthList.add(105);
		columnWidthList.add(110);
		columnWidthList.add(80);
		columnWidthList.add(150);
		columnWidthList.add(95);
		columnWidthList.add(80);
		columnWidthList.add(150);
		columnWidthList.add(95);
		return columnWidthList;
	}
	
	private List<ProductCustomer> productCustomerList() {		
		return productCustomerBiz.findProductCustomer(this, dealerShortName, buyStartDate, buyEndDate, inputStartDate, inputEndDate, history);
	}
	
	private void obtainTotalCount() {
		totalCount = productCustomerBiz.obtainTotalCount(this, dealerShortName, buyStartDate, buyEndDate, inputStartDate, inputEndDate, history);
	}
	
	
	//page
	private List<ProductCustomer> productCustomerList;
	public List<ProductCustomer> getProductCustomerList() {
		return productCustomerList;
	}
	
	private ProductCustomer productCustomer;
	public ProductCustomer getProductCustomer() {
		return productCustomer;
	}
	
	private List<InsuranceCard> insuranceCardList;	
	public List<InsuranceCard> getInsuranceCardList() {
		return insuranceCardList;
	}

	private String dealerShortName;
	public String getDealerShortName() {
		return dealerShortName;
	}
	
	private String buyStartDate;
	public String getBuyStartDate() {
		return buyStartDate;
	}
	public void setBuyStartDate(String buyStartDate) {
		this.buyStartDate = buyStartDate;
	}
	
	private String buyEndDate;
	public String getBuyEndDate() {
		return buyEndDate;
	}
	public void setBuyEndDate(String buyEndDate) {
		this.buyEndDate = buyEndDate;
	}
	
	private String inputStartDate;
	public String getInputStartDate() {
		return inputStartDate;
	}
	public void setInputStartDate(String inputStartDate) {
		this.inputStartDate = inputStartDate;
	}
	
	private String inputEndDate;
	public String getInputEndDate() {
		return inputEndDate;
	}
	public void setInputEndDate(String inputEndDate) {
		this.inputEndDate = inputEndDate;
	}
	
	private boolean history;
	public boolean isHistory() {
		return history;
	}
	public void setHistory(boolean history) {
		this.history = history;
	}
	
	private Integer totalCount = new Integer(0);	
	public int getTotalCount() {
		return totalCount;
	}

	//no ajax
	private Integer select;	
	public void setSelect(Integer select) {
		this.select = select;
	}
	
	//biz
	private ProductCustomerBiz productCustomerBiz;
	public void setProductCustomerBiz(ProductCustomerBiz productCustomerBiz) {
		this.productCustomerBiz = productCustomerBiz;
	}
	
	private ServiceCardBiz serviceCardBiz;
	public void setServiceCardBiz(ServiceCardBiz serviceCardBiz) {
		this.serviceCardBiz = serviceCardBiz;
	}
}