/**
 * @(#)EmployeeAct.java  1.0 10/04/15
 *
 * Copyright 2010 luyuan-ebike, Inc. All rights reserved.
 * 
 */
package com.luyuan.info.action;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.luyuan.action.ListAct;
import com.luyuan.info.biz.ProductBiz;
import com.luyuan.info.biz.ServiceCardBiz;
import com.luyuan.info.entity.InsuranceCard;
import com.luyuan.info.entity.Product;
import com.luyuan.info.entity.ProductCustomer;
import com.luyuan.info.entity.ProductInWarehouse;
import com.luyuan.info.entity.TblSMToSend;
import com.luyuan.sys.biz.UnitBiz;


public class ServiceCardAct extends ListAct {
	
	//进入
	public String execute() {
		shopShortName = (String)this.getSession().get("shortName");
		inputDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		buyDate = "";
		oldProductBarcode = "";
		
		hasInsurance = false;
		self = null;
		other = null;
		enableSaveButton = false;
		
		if(! this.getSession().get("unitType").equals("经销商"))
			dealerShortName = (String) this.getSession().get("parentShortName");
		else
			dealerShortName = (String) this.getSession().get("shortName");
		
		selfInsuranceCard = new InsuranceCard();
		otherInsuranceCard = new InsuranceCard();
		
		productCustomer = new ProductCustomer();
		productCustomer.setDealerCode(dealerShortName);
		productCustomer.setDealerName(dealerShortName);	
		productCustomer.setCustomerSex("男");	
		return "success";
	}
	
	//读取整车信息
	public String readProductInfo() {
		List<String> productInfoList = this.obtainProductInfoList();
				
		this.setProductInfo(productInfoList, false);		
		enableSaveButton = true;
		return "success";
	}
	
	//保存
	public String save() {
		if(! this.getFieldErrors().toString().equals("{}"))
			return "success";
		
		productCustomer.setInputDate(new Timestamp(System.currentTimeMillis()));
		productCustomer.setBuyDate(this.formBuyDate());
		//SMS，没有客户手机号，不发短信
		if(! productCustomer.getCustomerCellPhone().trim().equals(""))
			serviceCardBiz.save(productInWarehouse, productCustomer, selfInsuranceCard, otherInsuranceCard, this.setSMSListInfo());
		else
			serviceCardBiz.save(productInWarehouse, productCustomer, selfInsuranceCard, otherInsuranceCard, null);
		
		//得到保存保险卡的条数
		int insuranceCardCount = 0;
		if(selfInsuranceCard.getCustomerName() != null)
			insuranceCardCount++;
		if(otherInsuranceCard.getCustomerName() != null)
			insuranceCardCount++;
		
		this.addFieldError("saveSuccess", "服务卡录入成功，共保存" + insuranceCardCount + "条保险卡信息，短信已发送！");
		this.execute();
		return "success";
	}
	
	/**validate**/
	public void validateReadProductInfo() {
		enableSaveButton = false;
		oldProductBarcode = productCustomer.getProductBarcode();
		this.clearProductCustomer();
		
		if(productCustomer.getProductBarcode().trim().equals(""))
			this.addFieldError("barcode", "请输入整车编码");	
		//
		int dealerId;
		if(! this.getSession().get("unitType").equals("经销商"))
			dealerId = (Integer) this.getSession().get("parentId");
		else
			dealerId = (Integer) this.getSession().get("unitId");
		if(! serviceCardBiz.isProductSaled(dealerId, productCustomer.getProductBarcode().trim())) {
			this.addFieldError("barcode", "此车还没有填写销售单，请先填写销售单");
			return;
		}
		
		//ProductCustomer表里是否已经输入过
		String errorMessage = serviceCardBiz.validationProductCustomer(productCustomer.getProductBarcode());
		if(! errorMessage.equals("")) {
			this.addFieldError("barcode", errorMessage);
			return;
		}
		
		//判断ProductInWarehouse表
		productInWarehouse = serviceCardBiz.findProductInWarehouse(productCustomer.getProductBarcode());
		if(productInWarehouse == null) {
			this.addFieldError("barcode", "查无此车，或者该车已经完成输卡，请在服务卡查询中查找");
			return;
		}
		if(! productInWarehouse.getDealerCode().equals(dealerShortName)) {
			this.addFieldError("barcode", "该整车隶属于其他经销商，请输入隶属于您的整车编码");
			return;
		}
		
		Timestamp productionDate = productInWarehouse.getInspectionDate();
		if(productionDate.before(new Timestamp(110, 0, 1, 0, 0, 0, 0))) { //生产日期是否早于2010年1月1日，如果true，查productCustomerHistory表
			errorMessage = serviceCardBiz.validationProductCustomerHistory(productCustomer.getProductBarcode());
			if(! errorMessage.equals("")) {
				this.addFieldError("barcode", errorMessage);
				return;
			}
		}
	}	
	
	public void validateSave() {		
		if(productCustomer.getProductCode() == null) {
			enableSaveButton = false;
			this.addFieldError("barcode", "请点击按钮获取整车信息");
		}
		else if((! oldProductBarcode.equals("")) && (! oldProductBarcode.equals(productCustomer.getProductBarcode()))) {
			enableSaveButton = false;
			this.addFieldError("barcode", "整车编码与整车信息不一致，请重新点击按钮获取整车信息");
		}
		else if(oldProductBarcode.equals(productCustomer.getProductBarcode())) {			
			//再次查询ProductCustomer表里是否已经输入过，充分保证卡只录一次服务卡
			String errorMessage = serviceCardBiz.validationProductCustomer(productCustomer.getProductBarcode());
			if(! errorMessage.equals("")) {
				enableSaveButton = false;
				this.addFieldError("barcode", errorMessage);
			}
		}
		
		if(productCustomer.getCustomerName().trim().equals(""))
			this.addFieldError("customerName", "请填写用户姓名");
		if( buyDate.trim().equals("") ) 
		{
			this.addFieldError("buyDate", "请填写购车日期");
			return;
		}
		if(buyDate.compareTo(inputDate) > 0)
			this.addFieldError("buyDate", "购车日期不能大于输卡日期，请填写正确的购车日期");
		if(this.formBuyDate().before(productInWarehouse.getInspectionDate()))
			this.addFieldError("buyDate", "本车的出厂日期是" + productInWarehouse.getInspectionDate().toString().substring(0, 10) + "，购车日期不能小于车辆的出厂日期，请填写正确的购车日期");
		
		//手机号验证正则表达式:^0{0}(1)[0-9]{10}$
		//以1开头的11位数字
		String cellPhoneRegex = "^0{0}(1)[0-9]{10}$";
		if((! productCustomer.getCustomerCellPhone().trim().equals("")) && (! productCustomer.getCustomerCellPhone().trim().matches(cellPhoneRegex))) 
			this.addFieldError("cellPhone", "输入的手机号码不合法，请输入正确的手机号码");
		
		//身份证号验证正则表达式:^(\d{15}|\d{17}[\dx]|\d{17}[\dX])$
		//身份证号15位数字或18位数字，18位的最后一位可能是字母x
		String idNoRegex = "^(\\d{15}|\\d{17}[\\dx]|\\d{17}[\\dX])$";
		if((! productCustomer.getCustomerIdentification().trim().equals("")) && (! productCustomer.getCustomerIdentification().trim().matches(idNoRegex))) 
			this.addFieldError("customerIdentification", "输入的身份证号不合法，请输入正确的身份证号");
		
		if(hasInsurance) {
			String errroMessage = "";
			if(self != null) {
				if(selfInsuranceCard.getCustomerName().trim().equals(""))
					errroMessage = errroMessage + "<br/>请输入保险人本人姓名";
				else if(! selfInsuranceCard.getCustomerName().trim().equals(productCustomer.getCustomerName().trim()) && ! productCustomer.getCustomerName().trim().equals(""))
					errroMessage = errroMessage + "<br/>购车人与保险人本人的姓名必须一样";
				
				if(selfInsuranceCard.getIdentityCode().trim().equals(""))
					errroMessage = errroMessage + "<br/>请输入保险人本人身份证号";
				else if(! selfInsuranceCard.getIdentityCode().trim().matches(idNoRegex))
					errroMessage = errroMessage + "<br/>输入的保险人本人身份证号不合法，请输入正确的身份证号";
				else if(! selfInsuranceCard.getIdentityCode().trim().equals(productCustomer.getCustomerIdentification().trim()) && ! productCustomer.getCustomerIdentification().trim().equals(""))
					errroMessage = errroMessage + "<br/>购车人与保险人本人的身份证号必须一样";
				
				if(selfInsuranceCard.getCellPhone().trim().equals(""))
					errroMessage = errroMessage + "<br/>请输入保险人本人手机号";
				else if(! selfInsuranceCard.getCellPhone().trim().matches(cellPhoneRegex))
					errroMessage = errroMessage + "<br/>输入的保险人本人手机号码不合法，请输入正确的手机号码";
				else if(! selfInsuranceCard.getCellPhone().trim().equals(productCustomer.getCustomerCellPhone().trim()) && ! productCustomer.getCustomerCellPhone().trim().equals(""))
					errroMessage = errroMessage + "<br/>购车人与保险人本人的手机号必须一样";
			}
			if(other != null) {
				if(otherInsuranceCard.getCustomerName().trim().equals(""))
					errroMessage = errroMessage + "<br/>请输入保险人他人姓名";
				if(otherInsuranceCard.getIdentityCode().trim().equals(""))
					errroMessage = errroMessage + "<br/>请输入保险人他人身份证号";
				else if(! otherInsuranceCard.getIdentityCode().trim().matches(idNoRegex))
					errroMessage = errroMessage + "<br/>输入的保险人他人身份证号不合法，请输入正确的身份证号";
				else if(self != null && otherInsuranceCard.getIdentityCode().trim().equals(selfInsuranceCard.getIdentityCode().trim()))
					errroMessage = errroMessage + "<br/>两条保险信息的身份证号不能相同，请输入正确的身份证号";
				if(otherInsuranceCard.getCellPhone().trim().equals(""))
					errroMessage = errroMessage + "<br/>请输入保险人他人手机号";
				else if(! otherInsuranceCard.getCellPhone().trim().matches(cellPhoneRegex))
					errroMessage = errroMessage + "<br/>输入的保险人他人手机号码不合法，请输入正确的手机号码";
			}
			if(! errroMessage.equals(""))
				this.addFieldError("insuranceDetail", errroMessage);
			if(self == null && other == null)
				this.addFieldError("insurance", "请输入至少一条保险信息");
//			self = null;
//			other = null;
		}
	}

	//private method
	private List<TblSMToSend> setSMSListInfo() {
		List<TblSMToSend> tblSMToSendList = new ArrayList<TblSMToSend>();
		String content;
		Timestamp sendTime;
		
		//第1条，温情提示 ，输卡提示信息
		//本地服务电话从哪里获得
		String phone;
		if(! this.getSession().get("unitType").equals("经销商"))
			phone = unitBiz.findById((Integer) this.getSession().get("parentId")).getPhone();			
		else
			phone = unitBiz.findById((Integer) this.getSession().get("unitId")).getPhone();
		content = "温情提示：尊敬的" + productCustomer.getCustomerName().trim() + "，您的整车编号" + productCustomer.getProductBarcode().trim() + "，本地服务电话" + phone + "。绿源安全提示：电动车属非机动车，勿驶入机动车道；电动车属电器产品，请注意防水；停车时做好防盗措施；电池请保持随用随充，定期到当地服务中心进行BTM服务。";
		sendTime = this.formSMSSendDate(0, true);
		tblSMToSendList.add(this.setSMSInfo(content, sendTime));
		
		//第2条，保养提示，1个月提示信息
		content = "保养提示：尊敬的" + productCustomer.getCustomerName().trim() + "，您的车子已骑行一个月，您可以到当地服务中心享受免费保养服务，建议1.5-2个月到当地服务中心对轮胎进行补充气。";
		sendTime = this.formSMSSendDate(1, false);
		tblSMToSendList.add(this.setSMSInfo(content, sendTime));
		
		//第3条，电池BTM提示，8个月提示信
		content = "电池BTM提示：尊敬的" + productCustomer.getCustomerName().trim() + "，您的车子已使用八个月，请注意防范蓄电池过充过热失水问题，您可以到当地服务中心享受电池BTM免费服务。";
		sendTime = this.formSMSSendDate(8, false);
		tblSMToSendList.add(this.setSMSInfo(content, sendTime));
		
		return tblSMToSendList;
	}
	
	private TblSMToSend setSMSInfo(String content, Timestamp sendTime) {
		TblSMToSend tblSMToSend = new TblSMToSend();
		tblSMToSend.setSubTime(new Timestamp(System.currentTimeMillis()));
		tblSMToSend.setOrgAddr("106575257901");
		tblSMToSend.setDestAddr(productCustomer.getCustomerCellPhone());
		tblSMToSend.setSmContent(content);
		tblSMToSend.setSendTime(sendTime);
		tblSMToSend.setTryTimes(0);
		tblSMToSend.setCreatorId("UNKNOWN");
		tblSMToSend.setSmtype(Short.parseShort("0"));
		tblSMToSend.setMessageId("0");
		tblSMToSend.setDestAddrType(Short.parseShort("0"));
		return tblSMToSend;
	}
	
	private void setProductInfo(List<String> productInfoList, boolean clear) {
		productCustomer.setProductPrefixName(productInfoList.get(0));
		productCustomer.setProductSuffixName(productInfoList.get(1));
		productCustomer.setWheelSpecCode(productInfoList.get(2));
		productCustomer.setWheelDiameter(productInfoList.get(3));
		if(productInfoList.get(2) == null || productInfoList.get(3) == null)
			productCustomer.setProductSpecName(null);
		else
			productCustomer.setProductSpecName(productInfoList.get(2) + "|" + productInfoList.get(3));
		productCustomer.setColorCode(productInfoList.get(4));
		productCustomer.setColorName(productInfoList.get(5));
		productCustomer.setProductTypeName(productInfoList.get(6));
		productCustomer.setControlBarcode(productInfoList.get(7));
		productCustomer.setFrameBarcode(productInfoList.get(8));
		productCustomer.setHubBarcode(productInfoList.get(9));
		productCustomer.setListCategory(productInfoList.get(10));
		if(productInfoList.get(10) != null) {
			productCustomer.setListCategoryCode(productInfoList.get(10).split("\\|")[0]);
			productCustomer.setListCategoryName(productInfoList.get(10).split("\\|")[1]);
		}
		if(productInfoList.get(11) == null)
			productCustomer.setServiceCardNumber("");
		else
			productCustomer.setServiceCardNumber(productInfoList.get(11));
		productCustomer.setShipOrderCode(productInfoList.get(12));
		productCustomer.setProductionPlace(productInfoList.get(13));
		productCustomer.setProductionLine(productInfoList.get(14));
		productCustomer.setWarehouseCode(productInfoList.get(15));		
		productCustomer.setProductCode(productInfoList.get(16));
		
		//InspectionDate字段是Timestamp类型的，而productInfoList的泛型是String
		//为了往数据库插入一条ProductCustomer时不报错，没有使用productInfoList的一个元素赋值，而是单独获得InspectionDate的值并赋给productCustomer
		//没有必要查2遍数据库，重构时找一下解决办法？？？
		if(clear)
			productCustomer.setInspectionDate(null);
		else
			productCustomer.setInspectionDate(productInWarehouse.getInspectionDate());
	}
	
	private Timestamp formBuyDate() {		
		int year = Integer.parseInt(buyDate.substring(0, 4));
		int month = Integer.parseInt(buyDate.substring(5, 7));
		int day = Integer.parseInt(buyDate.substring(8, 10));
		return new Timestamp(year - 1900, month - 1, day, 0, 0, 0, 0);
	}
	
	//boolean firstSMS:是否是第一条短信
	//true，使用当前系统时间(加2分钟，确保短信能够发出去)作为sendTime
	//false，使用购买时间后的monthAddend个月作为sendTime，
	private Timestamp formSMSSendDate(int monthAddend, boolean firstSMS) {
		if(firstSMS) {
			String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());	
			int year = Integer.parseInt(now.substring(0, 4));
			int month = Integer.parseInt(now.substring(5, 7));
			int day = Integer.parseInt(now.substring(8, 10));
			int hour = Integer.parseInt(now.substring(11, 13));
			int minute = Integer.parseInt(now.substring(14, 16)) + 2;//当前时间的后2分钟
			int second = Integer.parseInt(now.substring(17, 19));
			return new Timestamp(year - 1900, month - 1, day, hour, minute, second, 0);
		}
		
		int year = Integer.parseInt(buyDate.substring(0, 4)) - 1900;
		int month = Integer.parseInt(buyDate.substring(5, 7)) - 1;
		int day = Integer.parseInt(buyDate.substring(8, 10));
		Calendar time = Calendar.getInstance();
		time.clear();
		time.set(year, month, day, 0, 0, 0);
		time.add(Calendar.MONTH, monthAddend);		
		return new Timestamp(time.get(Calendar.YEAR), time.get(Calendar.MONTH), time.get(Calendar.DATE), 19, 45, 0, 0);
	}
	
	//重新输入条形码，获取整车信息时，清空原来设置的productCustomer相关信息
	//调用setProductInfo()函数，字段设成null
	private void clearProductCustomer() {
		List<String> clearList = new ArrayList<String>();
		for(int i = 0; i < 17; i++)
			clearList.add(null);
		this.setProductInfo(clearList, true);		
	}
	
	//得到整车信息列表
	private List<String> obtainProductInfoList() {
		List<String> productInfoList = new ArrayList<String>();
		System.out.println(productInWarehouse.getProductCode());
		Product product = productBiz.findByCode(productInWarehouse.getProductCode());
		
		if(product == null) {
			productInfoList.add("");
			productInfoList.add("");
			productInfoList.add(productInWarehouse.getProductCode().substring(4, 5));
			productInfoList.add("");
			productInfoList.add(productInWarehouse.getProductCode().substring(5, 7));
			productInfoList.add(productInWarehouse.getProductCode().substring(5, 7));
			productInfoList.add(productInWarehouse.getProductCode().substring(0, 3));
		}
		else {				
			productInfoList.add(product.getPrefixName());
			productInfoList.add(product.getSuffixName());
			productInfoList.add(product.getSpecCode());
			productInfoList.add(product.getSpecName());
			productInfoList.add(product.getColorCode());
			productInfoList.add(product.getColorCode() + "|" + product.getColorName());
			productInfoList.add(product.getCategoryCode() + "|" + product.getPrefixName());
		}
		productInfoList.add(productInWarehouse.getControlBarcode());
		productInfoList.add(productInWarehouse.getFrameBarcode());
		productInfoList.add(productInWarehouse.getHubBarcode());
		productInfoList.add(productInWarehouse.getListCategory());
		productInfoList.add(productInWarehouse.getServiceCardNumber());
		productInfoList.add(productInWarehouse.getShipOrderCode());
		productInfoList.add(productInWarehouse.getProductionPlace());
		productInfoList.add(productInWarehouse.getProductionLine());
		productInfoList.add(productInWarehouse.getWarehouseCode());			
		productInfoList.add(productInWarehouse.getProductCode());
		
		return productInfoList;
	}
	
	//inside data
	private String dealerShortName;
	
	private ProductInWarehouse productInWarehouse;
	
	//记录之前提交（“获取整车信息”）的整车条形码，防止用户获取整车信息后再次改动整车条形码
	private String oldProductBarcode;
	
	//page
	private String shopShortName;
	public String getShopShortName() {
		return shopShortName;
	}
	
	private String buyDate;	
	public String getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(String buyDate) {
		this.buyDate = buyDate;
	}
	
	private String inputDate;
	public String getInputDate() {
		return inputDate;
	}

	private ProductCustomer productCustomer;
	public ProductCustomer getProductCustomer() {
		return productCustomer;
	}
	public void setProductCustomer(ProductCustomer productCustomer) {
		this.productCustomer = productCustomer;
	}
	
	private InsuranceCard selfInsuranceCard;
	public InsuranceCard getSelfInsuranceCard() {
		return selfInsuranceCard;
	}
	public void setSelfInsuranceCard(InsuranceCard selfInsuranceCard) {
		this.selfInsuranceCard = selfInsuranceCard;
	}
	
	private InsuranceCard otherInsuranceCard;
	public InsuranceCard getOtherInsuranceCard() {
		return otherInsuranceCard;
	}
	public void setOtherInsuranceCard(InsuranceCard otherInsuranceCard) {
		this.otherInsuranceCard = otherInsuranceCard;
	}

	private boolean hasInsurance;	
	public boolean isHasInsurance() {
		return hasInsurance;
	}
	public void setHasInsurance(boolean hasInsurance) {
		this.hasInsurance = hasInsurance;
	}
	
	private String self;
	public String getSelf() {
		return self;
	}
	public void setSelf(String self) {
		this.self = self;
	}
	
	private String other;
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	
	private boolean enableSaveButton;
	public boolean isEnableSaveButton() {
		return enableSaveButton;
	}

	//biz
	private ServiceCardBiz serviceCardBiz;
	public void setServiceCardBiz(ServiceCardBiz serviceCardBiz) {
		this.serviceCardBiz = serviceCardBiz;
	}
	
	private UnitBiz unitBiz;
	public void setUnitBiz(UnitBiz unitBiz) {
		this.unitBiz = unitBiz;
	}
	
	private ProductBiz productBiz;
	public void setProductBiz(ProductBiz productBiz) {
		this.productBiz = productBiz;
	}
}