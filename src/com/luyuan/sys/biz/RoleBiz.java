package com.luyuan.sys.biz;

import java.util.List;

import com.luyuan.sys.entity.Role;

public interface RoleBiz {

	public abstract List<Role> findByType(String roleType);
}
