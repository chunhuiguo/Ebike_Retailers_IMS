package com.luyuan.sys.dao;

import java.util.List;

import com.luyuan.action.IFieldValidation;
import com.luyuan.sys.entity.PartDiff;
import com.luyuan.util.Page;

public interface PartDiffDAO {

	public List<PartDiff> findAll(final IFieldValidation act);
	
	public abstract PartDiff findPartDiff(String partCode);
}
