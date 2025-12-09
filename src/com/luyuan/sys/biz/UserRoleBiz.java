package com.luyuan.sys.biz;

import java.util.List;

import com.luyuan.sys.entity.UserRole;

public interface UserRoleBiz {
	
	public void save(List<UserRole> userRoleList);
	
	public void deleteByUserCode(String userCode);

}
