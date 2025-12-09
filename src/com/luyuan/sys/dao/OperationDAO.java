package com.luyuan.sys.dao;

import java.util.List;

public interface OperationDAO {

	public List getModelByUser(String userCode);
	
	public List getOpByUserAndOpno(String userCode, String opNo);
	/*
	 * 金君耀2010.11.12插入
	 * 通过opCode和userCode来判断是否具有权限
	 * 参数1：userCode用户输入帐号，可以通过session获得
	 * 参数2：opCode；用于定义权限名opCode
	 * 返回：是否
	 * */
	public List setSelfOpcode(String userCode);
}