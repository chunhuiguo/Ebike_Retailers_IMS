package com.luyuan.sys.dao;

import java.util.List;

import com.luyuan.sys.entity.Role;

public interface RoleDAO {

	public abstract List<Role> findByType(String roleType);
	
	public abstract Role findById(java.lang.Integer id);
	
}