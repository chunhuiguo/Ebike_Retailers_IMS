package com.luyuan.sys.biz;

import java.util.List;
import java.util.Map;

import com.luyuan.action.IFieldValidation;
import com.luyuan.sys.entity.Operation;
import com.luyuan.sys.entity.Users;

public interface UsersBiz {

	public abstract int authentication (IFieldValidation act, Map<String,Object> session, String userCode, String password);
	
	public abstract Users findByLoginName(String userCode);
	
	public abstract Operation[] getModelByUser(String userCode);	
	
	public abstract Operation[] getOpByUserAndOpno(String userCode, String opno);
	
	public abstract List<Users> getUserByUserCode(String userCode);
	
	public abstract void save(Users user);
	
	public abstract String hashEncode(String pw, String type);
	
	public abstract void update(Users detachedInstance);
	
	public abstract Users merge(Users detachedInstance);
	
	public abstract String saveNewPW(String userCode, String old, String new1);
	
	//gch
	public abstract Users findById(int userId);
	
	public abstract String obtainUserRoles(String userCode);
}
