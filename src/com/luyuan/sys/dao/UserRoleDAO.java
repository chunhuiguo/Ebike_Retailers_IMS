package com.luyuan.sys.dao;

import java.util.List;

import com.luyuan.sys.entity.UserRole;

public interface UserRoleDAO {

	public List<UserRole> findByUserCode(String userCode);
	
	public void save(UserRole userRole);
	
	public void deleteByUserCode(String userCode);
	
}