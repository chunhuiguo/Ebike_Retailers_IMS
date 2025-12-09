package com.luyuan.info.biz.impl;


import java.util.List;

import com.luyuan.info.action.PriceTypeAct;
import com.luyuan.info.biz.PriceTypeBiz;
import com.luyuan.info.dao.PriceTypeDAO;
import com.luyuan.info.entity.PriceType;

public class PriceTypeBizImpl implements PriceTypeBiz{

	public List<PriceType> findAll() {
		// TODO Auto-generated method stub
		 return priceTypeDAO.findAll();
	}

	public PriceType findById(Integer priceTypeId) {
		// TODO Auto-generated method stub
		return priceTypeDAO.findById(priceTypeId);
	}


	public void save(PriceType priceType) {
		// TODO Auto-generated method stub
		priceTypeDAO.save(priceType);
	}

	public List<PriceType> findByDealerId(Integer dealerId) {
		// TODO Auto-generated method stub
		 return priceTypeDAO.findByDealerId(dealerId);
	}

	public void delete(Integer id) {
		// TODO Auto-generated method stub
		priceTypeDAO.delete(priceTypeDAO.findById(id));
	}

	public void update(PriceType priceType) {
		// TODO Auto-generated method stub
		priceTypeDAO.update(priceType);
	}

	public List<PriceType> findByDealerIdAndPpFlag(Integer dealerId,
			String ppFlag) {
		// TODO Auto-generated method stub
		return priceTypeDAO.findByDealerIdAndPpFlag(dealerId,ppFlag);
	}

	public PriceType findByDealerIdAndTypeAndFlagAndPpFlag(Integer dealerId,
			String type, String flag, String ppFlag) {
		// TODO Auto-generated method stub
		return priceTypeDAO.findByDealerIdAndTypeAndFlagAndPpFlag(dealerId,type, flag, ppFlag);
	}
	public List<PriceType> findPriceType(Integer dealerId, String ppFlag,
			String flag) {
		// TODO Auto-generated method stub
		return priceTypeDAO.findPriceType(dealerId,ppFlag,flag);
	}
	
	//dao
	private PriceTypeDAO priceTypeDAO;
	public void setPriceTypeDAO(PriceTypeDAO priceTypeDAO) {
		this.priceTypeDAO = priceTypeDAO;
	}

	public boolean validation(PriceTypeAct act, PriceType priceType,
			Integer dealerId) {
		boolean isError = false;
		List<PriceType>  priceTypeList=priceTypeDAO.findByDealerId(dealerId);
		for (int i = 0; i < priceTypeList.size(); i++){	
			if (priceTypeList.get(i).getType().equals(priceType.getType())&&priceTypeList.get(i).getPpFlag().equals(priceType.getPpFlag())&&priceTypeList.get(i).getFlag().equals(priceType.getFlag())){
				act.addFieldError("priceType.type","该价格类型名称已存在！");
				isError = true;
				break;
			}
		}
		if (isError)
			return false;
		else
			return true;
	}

}
