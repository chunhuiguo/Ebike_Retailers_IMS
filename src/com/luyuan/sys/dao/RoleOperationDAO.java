package com.luyuan.sys.dao;

import com.luyuan.sys.entity.RoleOperation;

public interface RoleOperationDAO {

	public RoleOperation findByLoginName(String userCode);
	
}