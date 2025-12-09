package com.luyuan.sys.dao;

import java.util.List;

import com.luyuan.sys.entity.Users;

public interface UsersDAO {

	public Users findByLoginName(String userCode);
	
	public List getModelByUser(String userCode);
	
	public List getOpByUserAndOpno(String userCode, String opNo);
	
	public abstract Users findByUserCode(String userCode);
	
	public List<Users> getUserByUserCode(String userCode);
	
	public void save(Users user);
	
	public void update(Users detachedInstance);
	
	public Users merge(Users detachedInstance);
	
	//gch
	public abstract Users findById( java.lang.Integer id);
}
