package com.luyuan.info.biz;

import java.util.List;

import com.luyuan.info.action.PriceTypeAct;
import com.luyuan.info.entity.PriceType;

public interface PriceTypeBiz {

	public List<PriceType> findAll();
//	public PriceType findByOwnerIdAndPriceType(Integer ownerId, String priceType);
//	public PriceType findById(Integer priceTypeId);

	public PriceType findById(Integer priceTypeId);

	public void save(PriceType priceType);

	public List<PriceType> findByDealerId(Integer dealerId);

	public void delete(Integer id);

	public void update(PriceType priceType);

	public List<PriceType> findByDealerIdAndPpFlag(Integer dealerId,
			String ppFlag);

	public PriceType findByDealerIdAndTypeAndFlagAndPpFlag(Integer dealerId,
			String type, String flag, String ppFlag);

	public List<PriceType> findPriceType(Integer dealerId, String ppFlag,
			String flag);

	public boolean validation(PriceTypeAct priceTypeAct, PriceType priceType,
			Integer integer);
	
}
