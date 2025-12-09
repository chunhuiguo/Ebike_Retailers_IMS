package com.luyuan.sys.biz;

import java.util.List;

import com.luyuan.action.IFieldValidation;
import com.luyuan.sys.entity.Unit;

public interface UnitBiz {
	
	//gch	
	public abstract List<Unit> findSupplier(IFieldValidation act, int shopId);
	
	public abstract List<Unit> findCustomer(IFieldValidation act, int shopId, boolean isDealer);
	
	public abstract List<Unit> findUnit(IFieldValidation act, int shopId, boolean isDealer);	
	
	public abstract List<Unit> findBtype(IFieldValidation act, int shopId, String unitType);
	
	public abstract List<Unit> findManageUnit(IFieldValidation act, int shopId);
	
	public abstract List<Unit> findDealingUnit(IFieldValidation act, int shopId);
	
	public abstract Unit findById(int unitId);
	
	public abstract List<Unit> findDealerAndUnit(IFieldValidation act, int shopId, boolean isDealer);
	
	public abstract List<Unit> findDealerAndUnit(int shopId);
	
	public abstract List<Unit> findAllDealer(IFieldValidation act);
		
	//wml	
	public List<Unit> findAllUnit(int shopId ,int unitId);
	
	public List<Unit> findUnitByDealingType(IFieldValidation act, int shopId,String dealingType);
	
//	public List<Unit> findNoAccountUnit(IFieldValidation act, int shopId);
//	public List<Unit> findOpenAccountUnit(IFieldValidation act, int shopId);
	
	//ww
//	public abstract List<Unit> findUnit(int dealerId);
	//zsh
	public void save(Unit transientInstance);
	public void update(Unit unit);
}