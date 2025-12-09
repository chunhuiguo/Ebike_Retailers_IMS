package com.luyuan.info.biz.impl;
import com.luyuan.info.biz.PPriceBiz;
import com.luyuan.info.dao.PPriceDAO;
import com.luyuan.info.entity.PPrice;

public class PPriceBizImpl implements PPriceBiz{
	

	public PPrice findByDealerIdAndPartIdAndPriceTypeId(Integer dealerId,
			Long partId, Integer priceTypeId) {
		// TODO Auto-generated method stub
		return ppriceDAO.findByDealerIdAndPartIdAndPriceTypeId(dealerId,partId,priceTypeId);
	}

	public void save(PPrice pPrice) {
		// TODO Auto-generated method stub
		ppriceDAO.save(pPrice);
	}

	public void update(PPrice pPrice) {
		// TODO Auto-generated method stub
		ppriceDAO.update(pPrice);
	}

	public void delete(Long id) {
		// TODO Auto-generated method stub
		ppriceDAO.delete(ppriceDAO.findById(id));
	}

	public PPrice findById(Long id) {
		// TODO Auto-generated method stub
		return ppriceDAO.findById(id);
	}

	private PPriceDAO ppriceDAO;
	public void setPpriceDAO(PPriceDAO ppriceDAO) {
		this.ppriceDAO = ppriceDAO;
	}

	public PPrice findPPrice(Integer dealerId, Integer priceTypeId, String code) {
		// TODO Auto-generated method stub
		return ppriceDAO.findPPrice( dealerId,  priceTypeId,  code);
	}
}
