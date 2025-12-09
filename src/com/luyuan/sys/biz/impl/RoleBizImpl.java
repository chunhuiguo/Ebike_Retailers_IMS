package com.luyuan.sys.biz.impl;

import java.util.List;

import com.luyuan.sys.biz.RoleBiz;
import com.luyuan.sys.dao.RoleDAO;
import com.luyuan.sys.entity.Role;

public class RoleBizImpl implements RoleBiz {

	//dao
	private RoleDAO roleDAO;
	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}
	
	public List<Role> findByType(String roleType) {
		// TODO Auto-generated method stub
		return roleDAO.findByType(roleType);
	}

}
