package com.luyuan.report.action;
import java.util.ArrayList;

import java.util.List;

import com.luyuan.action.BaseAct;
import com.luyuan.report.model.Report;
import com.luyuan.stock.biz.InventoryBookBiz;

public class ReportAct extends BaseAct {

	public String report() {
		return "success";
	}

	public String execute() throws Exception {
		reportList = inventoryBookBiz.findInventoryBook((Integer)getSession().get("unitId"),"2010-05-21");
		return "success";
	}

	private List<Report> reportList;
	public List<Report> getReportList() {
		return reportList;
	}
	public void setReportList(List<Report> reportList) {
		this.reportList = reportList;
	}

	private InventoryBookBiz inventoryBookBiz;

	public void setInventoryBookBiz(InventoryBookBiz inventoryBookBiz) {
		this.inventoryBookBiz = inventoryBookBiz;
	}
	

}
