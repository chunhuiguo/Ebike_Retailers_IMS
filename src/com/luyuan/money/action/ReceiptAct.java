package com.luyuan.money.action;

import com.luyuan.money.entity.Dealing;

public class ReceiptAct extends BillAct {

	public String receiptInput() {
		dealing = new Dealing();
		dealingDetailList.clear();
		submit = null;
		dealing.setType("收款单");
		
		return "success";
	}
	
	public String receiptUpdate() {
		if(selectList != null)
			selectList.clear();
		DraftAct draftAct = (DraftAct) ctx.getBean("moneyDraftAct");
		
		dealing = billBiz.findDealing(draftAct.getSelectList().get(0));
		dealingDetailList = billBiz.findDealingDetail(draftAct.getSelectList().get(0));
		
		isNew = false;
		submit=null;
		
		return "success";
	}
	
	public String receiptDetail() {
		CourseAct courseAct = (CourseAct) ctx.getBean("moneyCourseAct");
		dealing = billBiz.findDealing(courseAct.getSelect());
		dealingDetailList = billBiz.findDealingDetail(courseAct.getSelect());
		submit=null;
		return "success";
	}
	
	public String infoBack(){
		if (selectList != null) {
			selectList.clear();			
		}
		return "success";
	}
	
	// ****************************收款单***********************************
	public String execute() {

		if (submit.equals("选择账户")) {	
			return "subAccountDetail";
		}

		if (submit.equals("选择付款单位")) {
			return "unitDetail";
		}

		if (submit.equals("删除账户")) {
			if (selectList != null && selectList.size() != 0) {
				for (int i = selectList.size() - 1; i >= 0; i--) {
					dealingDetailList.remove(selectList.get(i).intValue());
				}
				selectList.clear();
			}
			return "success";
		}

		if (submit.equals("计算金额")) {
			this.calculate();	
			return "success";
		}

		if (submit.equals("保存")) {
			if (this.save())
				return "save";
			return "success";
		}

		if (submit.equals("记账")) {
			if (this.check()) {
				dealing.setIsChecked(false);
				isNew = true;
				return "check";
			}                                    
			return "success";
		}

		if (submit.equals("放弃")) {
			return "cancel";
		}
		return "success";
	}
}