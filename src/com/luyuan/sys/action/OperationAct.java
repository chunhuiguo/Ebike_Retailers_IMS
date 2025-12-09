package com.luyuan.sys.action;

import com.luyuan.action.BaseAct;
import com.luyuan.sys.biz.OperationBiz;
import com.luyuan.sys.biz.UsersBiz;

public class OperationAct  extends BaseAct {

	private OperationBiz operationBiz;
	public void setUsersBiz(OperationBiz operationBiz) {
		this.operationBiz = operationBiz;
	}
	
}
