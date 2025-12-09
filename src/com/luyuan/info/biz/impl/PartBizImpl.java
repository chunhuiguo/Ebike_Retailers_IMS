package com.luyuan.info.biz.impl;

import java.util.ArrayList;
import java.util.List;

import com.luyuan.action.IFieldValidation;
import com.luyuan.deal.entity.PVoucherDetail;
import com.luyuan.deal.entity.VoucherDetail;
import com.luyuan.info.biz.PartBiz;
import com.luyuan.info.dao.PartDAO;
import com.luyuan.info.entity.Part;
import com.luyuan.util.Page;

public class PartBizImpl implements PartBiz {

	//gch
	public List<Part> findPart(IFieldValidation act, int dealerId) {
		return partDAO.findPart(act, dealerId);
	}
	
	public Part findByCode(String code) {
		return partDAO.findByCode(code);
	}
	
	public void save(Part part) {
		partDAO.save(part);
	}

	//chq
	public List<Part> findAll(){
		
		return partDAO.findAll();
	}	
	
	public Part findById(Long partId) {
		// TODO Auto-generated method stub
		return partDAO.findById(partId);
	}
	public List<Part> findByDealerId(Integer dealerId) {
		// TODO Auto-generated method stub
		return partDAO.findByDealerId(dealerId);
	}
	
	public List<Part> findPart(Integer dealerId, int unitId) {
		// TODO Auto-generated method stub
		return partDAO.findPart(dealerId, unitId);
	}
	//DAO
	private PartDAO partDAO;
	public void setPartDAO(PartDAO partDAO) {
		this.partDAO = partDAO;
	}
}
