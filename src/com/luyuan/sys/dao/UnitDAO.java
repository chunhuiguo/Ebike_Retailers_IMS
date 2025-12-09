package com.luyuan.sys.dao;

import java.util.List;

import com.luyuan.action.IFieldValidation;
import com.luyuan.info.entity.Account;
import com.luyuan.sys.entity.Unit;
import com.luyuan.util.Page;

public interface UnitDAO {
	
	//gch	
	public abstract List<Unit> findSupplier(final IFieldValidation act, final int shopId, final int parentId);
	
	public abstract List<Unit> findCustomer(final IFieldValidation act, final int shopId, final boolean isDealer);
	
	public abstract List<Unit> findUnit(final IFieldValidation act, final int dealerId);
	
	public abstract List<Unit> findUnit(final IFieldValidation act, final int shopId, final int partneId);
	
	public abstract List<Unit> findDealerBtype(final IFieldValidation act, final int shopId, final int parentId);
	
	public abstract List<Unit> findUnitBtype(final IFieldValidation act, final int shopId, final int parentId);
	
	public abstract List<Unit> findManageUnit(final IFieldValidation act, final int shopId);
	
	public abstract List<Unit> findDealingUnit(final IFieldValidation act, final int shopId, final int parentId);
	
	public abstract Unit findById(java.lang.Integer id);
	
	public abstract List<Unit> findDealerAndUnit(final IFieldValidation act, final int shopId);
	
	public abstract List<Unit> findDealerAndUnit(int shopId);
	
	public abstract List<Unit> findById(final IFieldValidation act, final int id);
	
	public abstract List<Unit> findAllDealer(final IFieldValidation act);
	
	//wml
	public abstract Unit findByShortName(Object shortName);	
	
	public List<Unit> findUnitByAccount(IFieldValidation act, List<Account> accountList);
	
	public List<Unit> findAllUnit(int shopId);
	
	public List<Unit> findUnitNoShop(final IFieldValidation act, final int shopId);
	
//	public List<Unit> findOpenAccountUnit(IFieldValidation act, int shopId);
	//zsh
	public void save(Unit transientInstance);
	public void update(Unit unit);
}