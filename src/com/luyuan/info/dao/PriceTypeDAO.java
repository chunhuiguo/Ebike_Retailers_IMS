package com.luyuan.info.dao;

import java.util.List;
import com.luyuan.info.entity.PriceType;

public interface PriceTypeDAO {

    public List<PriceType> findAll();

	public PriceType findById(Integer priceTypeId);

	public void save(PriceType priceType);

	public List<PriceType> findByDealerId(Integer dealerId);

	public void delete(PriceType findById);

	public void update(PriceType priceType);

	public List<PriceType> findByDealerIdAndPpFlag(Integer dealerId,
			String ppFlag);

	public PriceType findByDealerIdAndTypeAndFlagAndPpFlag(Integer dealerId,
			String type, String flag, String ppFlag);

	public List<PriceType> findPriceType(Integer dealerId, String ppFlag,
			String flag);
    
}
