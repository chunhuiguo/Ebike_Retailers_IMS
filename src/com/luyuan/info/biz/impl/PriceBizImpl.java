package com.luyuan.info.biz.impl;
import java.util.List;

import com.luyuan.info.action.PriceAct;
import com.luyuan.info.biz.PriceBiz;
import com.luyuan.info.dao.PriceDAO;
import com.luyuan.info.entity.Price;


public class PriceBizImpl  implements PriceBiz{
	
	private PriceDAO priceDAO;
	public void setPriceDAO(PriceDAO priceDAO) {
		this.priceDAO = priceDAO;
	}

	public List<Price> findAll() {
		// TODO Auto-generated method stub
		 return priceDAO.findAll();
	}
	
    public void save(Price price){
		
    	priceDAO.save(price);
	}

	public Price findById(Long id) {
		// TODO Auto-generated method stub
		return priceDAO.findById(id);
	}

	public void update(Price price) {
		// TODO Auto-generated method stub
		priceDAO.update(price);
	}

	public void delete(Long id) {
		// TODO Auto-generated method stub
		priceDAO.delete(priceDAO.findById(id));
	}

	public Price findByDealerIdAndProductIdAndPriceTypeId(
			Integer dealerId, Long productId, Integer priceTypeId) {
		// TODO Auto-generated method stub
		return priceDAO.findByDealerIdAndProductIdAndPriceTypeId(dealerId, productId, priceTypeId);
	}

	public List<Price> findByDealerIdAndProductId(Integer dealerId, Long id) {
		// TODO Auto-generated method stub
		return priceDAO.findByDealerIdAndProductId(dealerId,id);
	}

	public List<Price> findByDealerId(Integer dealerId) {
		// TODO Auto-generated method stub
		return priceDAO.findByDealerId(dealerId);
	}

	public Price findPrice(Integer dealerId, Long productId, Integer priceTypeId) {
		// TODO Auto-generated method stub
		return priceDAO.findPrice(dealerId,productId,priceTypeId);
	}

	public List<Price> findPrice(Integer dealerId, Integer priceTypeId) {
		// TODO Auto-generated method stub
		return priceDAO.findPrice(dealerId,priceTypeId);
	}

	public boolean validation(PriceAct priceAct, Price price, Integer dealerId) {
		boolean isError = false;
		List<Price>  priceTypeList=priceDAO.findByDealerId(dealerId);
		for (int i = 0; i < priceTypeList.size(); i++){	
//			if (priceTypeList.get(i).getType().equals(priceType.getType())&&priceTypeList.get(i).getPpFlag().equals(priceType.getPpFlag())&&priceTypeList.get(i).getFlag().equals(priceType.getFlag())){
//				act.addFieldError("priceType.type","该价格类型名称已存在！");
//				isError = true;
//				break;
//			}
		}
		if (isError)
			return false;
		else
			return true;
	}

	public Price findPrice(Integer dealerId, Integer priceTypeId,
			String code) {
		// TODO Auto-generated method stub
		return priceDAO.findPrice(dealerId,priceTypeId,code);
	}

	public Price findPrice(Integer dealerId, String productCode, String code,
			Integer priceTypeId) {
		// TODO Auto-generated method stub
		return priceDAO.findPrice(dealerId,productCode,code,priceTypeId);
	}
	}


