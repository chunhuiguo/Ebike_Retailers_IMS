package com.luyuan.info.biz;

import java.util.List;

import com.luyuan.info.action.PriceAct;
import com.luyuan.info.entity.Price;

public interface PriceBiz {

	public List<Price> findAll();
    public Price findByDealerIdAndProductIdAndPriceTypeId(Integer dealerId,Long productId,Integer priceTypeId);
	public void save(Price price);
	public Price findById(Long id);
	public void update(Price price);
	public void delete(Long id);
	public List<Price> findByDealerIdAndProductId(Integer dealerId, Long id);
	public List<Price> findByDealerId(Integer dealerId);
	public Price findPrice(Integer dealerId, Integer priceTypeId, String code);
	public List<Price> findPrice(Integer dealerId, Integer priceTypeId);
	public boolean validation(PriceAct priceAct, Price price, Integer integer);
	public Price findPrice(Integer dealerId, String productCode, String code,
			Integer priceTypeId);
	
	
    
	
}
