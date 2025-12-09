package com.luyuan.info.dao;

import java.util.List;

import com.luyuan.info.entity.Price;

public interface PriceDAO {

    public List<Price> findAll();
	public void save(Price price);
	public Price findById(Long id);
	public void update(Price price);
	public void delete(Price findById);
	public Price findByDealerIdAndProductIdAndPriceTypeId(
			Integer dealerId, Long productId, Integer priceTypeId);
	public List<Price> findByDealerIdAndProductId(Integer dealerId, Long id);
	public List<Price> findByDealerId(Integer dealerId);
	public Price findPrice(Integer dealerId, Integer priceTypeId, String code);
	public List<Price> findPrice(Integer dealerId, Integer priceTypeId);
	public Price findPrice(Integer dealerId, Long productId, Integer priceTypeId);
	public Price findPrice(Integer dealerId, String productCode, String code,
			Integer priceTypeId); 
}
