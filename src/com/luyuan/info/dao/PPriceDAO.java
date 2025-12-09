package com.luyuan.info.dao;
import com.luyuan.info.entity.PPrice;

public interface PPriceDAO {

	public PPrice findByDealerIdAndPartIdAndPriceTypeId(Integer dealerId, Long partId,
			Integer priceTypeId);

	public void save(PPrice pPrice);

	public void update(PPrice pPrice);

	public void delete(PPrice pPrice);

	public PPrice findById(Long id);

	public PPrice findPPrice(Integer dealerId, Integer priceTypeId, String code);
    
}