package com.luyuan.sys.biz;

import java.util.List;

import com.luyuan.action.IFieldValidation;
import com.luyuan.sys.entity.PartDiff;
import com.luyuan.util.Page;

public interface PartDiffBiz {
	
	public abstract List<PartDiff> findAll(IFieldValidation act);
	
	public abstract List<PartDiff> findPartDiff(List<String> partCodeList);
}
