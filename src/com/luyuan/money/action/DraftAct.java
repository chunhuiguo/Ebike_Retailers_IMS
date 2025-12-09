package com.luyuan.money.action;

import com.luyuan.money.entity.Dealing;
import com.luyuan.util.TypeMap;

public class DraftAct extends BillAct {

	// ************************************草稿箱*************************************
	public String execute() {
		page.setListAct("draft.html");
		if (submit == null) {
			if (dealingList != null)
				dealingList.clear();
			dealingList = billBiz.findDealing(this, (Integer) this.getSession().get("unitId"), false);
			return "success";
		}

		if (submit.equals("修改单据")) {
			submit = null;			
			if (selectList != null && selectList.size() > 0) {				
				for (int i = 0; i < dealingList.size(); i++) {			
					if (dealingList.get(i).getId().equals(selectList.get(0).longValue())) {
						Dealing dealing = dealingList.get(i);		
						return TypeMap.typeMap.get(dealing.getType());
					}
				}
				selectList.clear();
			}			
			return "success";
		}

		if (submit.equals("删除单据")) {
			submit = null;
			if (selectList != null && selectList.size() > 0) {
				for (int i = 0; i < selectList.size(); i++)
					billBiz.delete(selectList.get(i).longValue());
			}
			return "delete";
		}

		if (submit.equals("筛选")) {
			if (accountType.equals("所有单据")) {
				dealingList = billBiz.findDealing(this, (Integer) this.getSession().get("unitId"), false);
				return "success";
			}
			else {
				dealingList = billBiz
						.findDealing(this, (Integer) this.getSession().get("unitId"), accountType, false);
				return "success";
			}
		}
		return "success";
	}	
}
