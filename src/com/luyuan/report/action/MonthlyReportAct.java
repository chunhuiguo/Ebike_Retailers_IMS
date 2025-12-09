package com.luyuan.report.action;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.luyuan.action.ListAct;
import com.luyuan.report.biz.MonthlyDealingBiz;
import com.luyuan.report.biz.MonthlySalesBiz;
import com.luyuan.report.entity.PSIDealingMonthlyReport;
import com.luyuan.report.entity.PSIProductMonthlyReport;


public class MonthlyReportAct extends ListAct {

	//整车销售月报查询--菜单入口
	public String menuInSales() {
		
		String reportYear = new SimpleDateFormat("yyyy").format(new Date());
		String reportMonth = String.format("%1$02d",Integer.parseInt(new SimpleDateFormat("MM").format(new Date()))-1);
		List<String> valueList= new ArrayList<String>();
		List<String> nameList= new ArrayList<String>();
		valueList.add(reportYear);
		valueList.add(reportMonth);
		page.setPropsValueList(valueList);
		nameList.add("reportYear");
		nameList.add("reportMonth");
		page.setPropsNameList(nameList);
		
		if(this.getSession().get("unitType").equals("经销商"))
			isDealer = true;
		else
			isDealer = false;
		page.setListAct("monthlySalesIn.html");		
		psiProductMonthlyReportList=monthlySalesBiz.monthlySales(this, (Integer)this.getSession().get("unitId"),isDealer);
		return "success";
	}
	
	//整车销售月报查询--提交入口
	public String salesSubmitIn(){
		if(submit==null){
			page.setListAct("salesQuery.html");
			psiProductMonthlyReportList=monthlySalesBiz.monthlySales(this, (Integer)this.getSession().get("unitId"),isDealer);
			return "success";
		}
		if(submit.equals("查询")){
			submit=null;
			
			page.setListAct("salesQuery.html");
			psiProductMonthlyReportList=monthlySalesBiz.monthlySales(this, (Integer)this.getSession().get("unitId"),isDealer);
		}
		return "success";
	}
	
	//整车销售月报生成--菜单入口
	public String monthlySalesM(){
		return "success";
	}
	
	//整车销售月报生成--提交入口
	public String salesReport() {
		if (submit.equals("生成报表")) {
			submit = null;
			String beginDate = reportYear + "-" + reportMonth + "-" + "01";
			String endDate = reportYear + "-" + reportMonth + "-" + "31";
			
			if (this.validation(reportYear, reportMonth)) {
				if (monthlySalesBiz.validationDate(this,(Integer) this.getSession()
						.get("unitId"),reportYear, reportMonth)) {
					psiProductMonthlyReportList=monthlySalesBiz.updateReport((Integer) this.getSession()
							.get("unitId"), beginDate, endDate);					
					return "report";
				}
				return "success";
			}
			return "success";
		}
		return "success";
	}
	
	//报表样式ireport
	public String monthlySales(){
//		System.out.println("success");
//		System.out.println("psiProductMonthlyReportList"+psiProductMonthlyReportList.size());
		return "success";
	}
	
	//*********************************************************************************
	//*********************************************************************************
	
	
	//账款月报查询--菜单入口
	public String menuInDealing() {
		
		String reportYear = new SimpleDateFormat("yyyy").format(new Date());
		String reportMonth = String.format("%1$02d",Integer.parseInt(new SimpleDateFormat("MM").format(new Date()))-1);
		List<String> valueList= new ArrayList<String>();
		List<String> nameList= new ArrayList<String>();
		valueList.add(reportYear);
		valueList.add(reportMonth);
		page.setPropsValueList(valueList);
		nameList.add("reportYear");
		nameList.add("reportMonth");
		page.setPropsNameList(nameList);
		
		
		page.setListAct("monthlyDealingIn.html");	
		accountType="整车账户";
		type="应收";
		psiDealingMonthlyReportList=monthlyDealingBiz.monthlyDealing(this, (Integer)this.getSession().get("unitId"),type,accountType);
		return "success";
	}
	
	//整车账款月报生成--菜单入口
	public String monthlyDealingM(){
		return "success";
	}
	
	
	//账款月报查询--提交入口
	public String dealingSubmitIn() {
		if(submit==null){
			page.setListAct("dealingQuery.html");
			psiDealingMonthlyReportList = monthlyDealingBiz.monthlyDealing(this, (Integer) this.getSession().get("unitId"), type, accountType);
			return "success";
		}
		if (submit.equals("查询")) {
			submit = null;
			page.setListAct("dealingQuery.html");
			psiDealingMonthlyReportList = monthlyDealingBiz.monthlyDealing(this, (Integer) this.getSession().get("unitId"), type, accountType);

		}
		return "success";
	}
	
	//整车账款月报生成--提交入口
	public String dealingReport() {
		if (submit.equals("生成报表")) {
			submit = null;
			String beginDate = reportYear + "-" + reportMonth + "-" + "01";
			String endDate = reportYear + "-" + reportMonth + "-" + "31";
			
			if (this.validation(reportYear, reportMonth)) {
				if (monthlyDealingBiz.validationDate(this,(Integer) this.getSession()
						.get("unitId"),reportYear, reportMonth)) {
					psiDealingMonthlyReportList=monthlyDealingBiz.updateReport((Integer) this.getSession()
							.get("unitId"), beginDate, endDate);
//					this.addFieldError("date", "success！");
					return "report";
				}
				return "success";
			}
			return "success";
		}
		return "success";
	}
	
	//报表样式ireport
	public String monthlyDealing(){
		return "success";
	}
	
	
	
	//校验时间格式
	protected boolean validation(String reportYear,String reportMonth ) {
		boolean isError = false;

		if (reportYear.equals("")) {
			this.addFieldError("date", "年份不能为空");
			isError = true;
		}
		if (reportMonth.equals("")) {
			this.addFieldError("date", "月份不能为空");
			isError = true;
		}
		if (reportYear.length()!="9999".length()) {
			this.addFieldError("date", "年份必须是四位数字！");
			isError = true;
		}
		if (reportYear.compareTo("1000")<0||reportYear.compareTo("9999")>0) {
			this.addFieldError("date", "年份输入不正确！");
			isError = true;
		}
		if (reportMonth.length()!="99".length()) {
			this.addFieldError("date", "月份必须是两位数字！");
			isError = true;
		}
		if (reportMonth.compareTo("01")<0||reportMonth.compareTo("12")>0) {
			this.addFieldError("date", "月份输入不正确！");
			isError = true;
		}
		if (isError)
			return false;
		else
			return true;

	}

	//act
	private Boolean isDealer;
	
	
	//submit
	private String submit;
	public void setSubmit(String submit) {
		this.submit = submit;
	}
	
	// page act里的数据page用要get
	private String reportMonth;
	public void setReportMonth(String reportMonth) {
		this.reportMonth = reportMonth;
	}	
	public String getReportMonth() {
		return reportMonth;
	}

	private String reportYear;
	public void setReportYear(String reportYear) {
		this.reportYear = reportYear;
	}
	public String getReportYear() {
		return reportYear;
	}
	
	private String type;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	private String accountType;
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	private List<PSIProductMonthlyReport> psiProductMonthlyReportList;
	public List<PSIProductMonthlyReport> getPsiProductMonthlyReportList() {
		return psiProductMonthlyReportList;
	}
	
	
	private List<PSIDealingMonthlyReport> psiDealingMonthlyReportList;
	public List<PSIDealingMonthlyReport> getPsiDealingMonthlyReportList() {
		return psiDealingMonthlyReportList;
	}

	//biz
	private MonthlySalesBiz monthlySalesBiz;
	public void setMonthlySalesBiz(MonthlySalesBiz monthlySalesBiz) {
		this.monthlySalesBiz = monthlySalesBiz;
	}
	
	private MonthlyDealingBiz monthlyDealingBiz;
	public void setMonthlyDealingBiz(MonthlyDealingBiz monthlyDealingBiz) {
		this.monthlyDealingBiz = monthlyDealingBiz;
	}
	
//	private MonthlyDealingBiz monthlyPDealingBiz;
//	public void setMonthlyPDealingBiz(MonthlyDealingBiz monthlyPDealingBiz) {
//		this.monthlyPDealingBiz = monthlyPDealingBiz;
//	}
}
