package com.luyuan.sys.biz.impl;

import java.util.List;

import com.luyuan.sys.biz.UserRoleBiz;
import com.luyuan.sys.dao.UserRoleDAO;
import com.luyuan.sys.entity.UserRole;

public class UserRoleBizImpl implements UserRoleBiz {

	private UserRoleDAO userRoleDAO;
	public void setUserRoleDAO(UserRoleDAO userRoleDAO) {
		this.userRoleDAO = userRoleDAO;
	}
	
	public void save(List<UserRole> userRoleList){
		for(int i = 0; i < userRoleList.size(); i++) {
			userRoleDAO.save(userRoleList.get(i));
		}
	}
	
	public void deleteByUserCode(String userCode) {
		// TODO Auto-generated method stub
		userRoleDAO.deleteByUserCode(userCode);
	}

}
