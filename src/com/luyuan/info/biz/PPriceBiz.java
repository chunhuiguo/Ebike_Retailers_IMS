package com.luyuan.info.biz;

import com.luyuan.info.entity.PPrice;


public interface PPriceBiz {

    public PPrice findByDealerIdAndPartIdAndPriceTypeId(Integer dealerId,Long partId,Integer priceTypeId);
	public void save(PPrice pPrice);
	public PPrice findById(Long id);
	public void update(PPrice pPrice);
	public void delete(Long id);
	public PPrice findPPrice(Integer dealerId, Integer priceTypeId, String code);
    
	
}
