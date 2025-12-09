package com.luyuan.money.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.luyuan.money.entity.Dealing;
import com.luyuan.util.TypeMap;

public class CourseAct extends BillAct {

	// ***********************************经营历程*************************************

	// 业务单据历史---菜单入口
	public String detail() {
		if (dealingList != null)
			dealingList.clear();
		select = null;
		accountType = null;
		errorType = null;

		endDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		startDate = endDate.substring(0, 8) + "01";

		page.setListAct("courseDetail.html");

		dealingList = billBiz.findDealing(this, (Integer) this
				.getSession().get("unitId"), true);
		return "success";
	}

	// 业务单据历史---查询入口
	public String query() {
		select = null;
		accountType = null;
		errorType = null;

		BillQueryAct billQueryAct = (BillQueryAct) ctx.getBean("billQueryAct");
		dealingList = billQueryAct.getDealingList();

		dealing = billQueryAct.getDealing();
		startDate = billQueryAct.getDealing().getCheckDate();
		endDate = billQueryAct.getDealing().getCreateDate();

		page.setListAct("courseQuery.html");
		dealingList = billBiz.findDealing(this, (Integer) this.getSession().get("parentId"), dealing);
		return "success";
	}

	// 业务单据历史---提交入口
	public String execute() {
		page.setListAct("course.html");

		if (submit.equals("查看")) {
			if (select != null) {
				for (int i = 0; i < dealingList.size(); i++) {
					if (dealingList.get(i).getId().equals(select.longValue())) {
						Dealing dealing = dealingList.get(i);
						return TypeMap.typeMap.get(dealing.getType());
					}
				}
				select = null;
			}
			return "success";
		}
		if (submit.equals("冲抵")) {
			if (select != null) {
				dealing = billBiz.findDealing(select.intValue());
				if (dealing.getIsError()) {
					this.addFieldError("chargeAgainst", "单据【"
							+ dealing.getCode() + "】已经冲抵过了，不能再次冲抵！");
					return "success";
				} else {
					dealingDetailList = billBiz.findDealingDetail(select
							.longValue());
					billBiz.saveAgainst(dealing, dealingDetailList,
							(Integer) this.getSession().get("userId"));
					select = null;
					return "chargeAgainst";
				}
			}
			return "success";
		}
		if (submit.equals("筛选")) {
			if (dealingList != null)
				dealingList.clear();
			select = null;
			dealingList = billBiz.findDealing(this, (Integer) this.getSession().get("unitId"), accountType, true, errorType);
			return "success";
		}
		if (submit.equals("查询")) {
			if (dealingList != null)
				dealingList.clear();
			return "billQuery";
		}

		return "success";
	}

	private String startDate;

	public String getStartDate() {
		return startDate;
	}

	private String endDate;

	public String getEndDate() {
		return endDate;
	}
}
