package com.luyuan.sys.biz.impl;

import com.luyuan.sys.biz.OperationBiz;
import com.luyuan.sys.dao.OperationDAO;


public class OperationBizImpl implements OperationBiz{


	//dao
	private OperationDAO operationDAO;
	public void setOperationDAO(OperationDAO operationDAO) {
		this.operationDAO = operationDAO;
	}
}
