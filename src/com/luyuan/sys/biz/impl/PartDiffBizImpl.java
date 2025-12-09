package com.luyuan.sys.biz.impl;

import java.util.ArrayList;
import java.util.List;

import com.luyuan.action.IFieldValidation;
import com.luyuan.sys.biz.PartDiffBiz;
import com.luyuan.sys.dao.PartDiffDAO;
import com.luyuan.sys.entity.PartDiff;
import com.luyuan.sys.entity.ProductDiff;
import com.luyuan.util.Page;




public class PartDiffBizImpl implements PartDiffBiz{

	public List<PartDiff> findAll(IFieldValidation act){
		return partDiffDAO.findAll(act);
	}
	
	public List<PartDiff> findPartDiff(List<String> partCodeList) {
		List<PartDiff> partDiffList = new ArrayList<PartDiff>();
		
		for(int i = 0; i < partCodeList.size(); i++) {
			PartDiff partDiff = partDiffDAO.findPartDiff(partCodeList.get(i));
			if(partDiff != null)
				partDiffList.add(partDiff);
		}
		
		return partDiffList;
	}

	//dao
	private PartDiffDAO partDiffDAO;
	public void setPartDiffDAO(PartDiffDAO partDiffDAO) {
		this.partDiffDAO = partDiffDAO;
	}		
}
