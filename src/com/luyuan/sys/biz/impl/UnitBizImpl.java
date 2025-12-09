/**
 * @(#)UnitBizImpl.java  1.0 10/04/18
 *
 * Copyright 2010 luyuan-ebike, Inc. All rights reserved.
 * 
 */
package com.luyuan.sys.biz.impl;

import java.util.List;

import com.luyuan.action.IFieldValidation;
import com.luyuan.info.dao.AccountDAO;
import com.luyuan.info.entity.Account;
import com.luyuan.sys.biz.UnitBiz;
import com.luyuan.sys.dao.UnitDAO;
import com.luyuan.sys.entity.Unit;

/**
 * 
 * 往来单位Unit的业务逻辑层biz
 *
 * @author gch
 */

public class UnitBizImpl implements UnitBiz {

	public void save(Unit transientInstance){
		unitDAO.save(transientInstance);
	}
	public void update(Unit unit){
		unitDAO.update(unit);
	}
	
	//gch	
	public List<Unit> findSupplier(IFieldValidation act, int shopId) {
		Unit unit = unitDAO.findById(shopId);
		return unitDAO.findSupplier(act, shopId, unit.getParentId());
	}
	
	public List<Unit> findCustomer(IFieldValidation act, int shopId, boolean isDealer) {		
		if(isDealer)		
			return unitDAO.findCustomer(act, shopId, isDealer);		
		else {	
			Unit unit = unitDAO.findById(shopId);
			if(unit.getType().equals("直属门店"))
				return unitDAO.findCustomer(act, unit.getParentId(), isDealer);
			else
				return unitDAO.findCustomer(act, shopId, isDealer);
		}
	}
	
	public List<Unit> findUnit(IFieldValidation act, int shopId, boolean isDealer) {
		if(isDealer)
			return unitDAO.findUnit(act, shopId);
		else {
			Unit unit = unitDAO.findById(shopId);
			return unitDAO.findUnit(act, shopId, unit.getParentId());
		}
	}
	
	//所有往来单位
	public List<Unit> findBtype(IFieldValidation act, int shopId, String unitType) {
		Unit unit = unitDAO.findById(shopId);
		if(unitType.equals("直属门店"))
			return unitDAO.findUnitBtype(act, shopId, unit.getParentId());
		else
			return unitDAO.findDealerBtype(act, shopId, unit.getParentId());
	}
	
	public Unit findById(int unitId) {
		return unitDAO.findById(unitId);
	}
	
	//所有经销商列表，公司查库存用 
	public List<Unit> findAllDealer(IFieldValidation act) {
		return unitDAO.findAllDealer(act);
	}
	
	//往来单位管理 
	public List<Unit> findManageUnit(IFieldValidation act, int shopId) {
		return unitDAO.findManageUnit(act, shopId);
	}
	
	//账户开户、收付款单单据查询
	public List<Unit> findDealingUnit(IFieldValidation act, int shopId) {
		Unit unit = unitDAO.findById(shopId);
		return unitDAO.findDealingUnit(act, shopId, unit.getParentId());
	}
	
	//仓库管理使用，员工管理使用
	public List<Unit> findDealerAndUnit(IFieldValidation act, int shopId, boolean isDealer) {
		if(isDealer)
			return unitDAO.findDealerAndUnit(act, shopId);
		else 
			return unitDAO.findById(act, shopId);
	}
	
	//用户管理使用
	public List<Unit> findDealerAndUnit(int shopId) {
			return unitDAO.findDealerAndUnit(shopId);		
	}	
	
	//wml
	public List<Unit> findAllUnit(int shopId,int unitId){		
		List<Unit> list= unitDAO.findAllUnit(shopId);
		list.add(0, unitDAO.findById(unitId));
		Unit unit = unitDAO.findById(shopId);
		if(unit.getParentId() != 0)
			list.add(1, unitDAO.findById(unit.getParentId()));
		return list;
	}	
	
	//通过经销商、单据类型查找往来单位
	public List<Unit> findUnitByDealingType(IFieldValidation act, int shopId,String dealingType){
		String type;
		if(dealingType.equals("付款单")){
			type="应付";
		}
		else
			type="应收";
		
		//根据应收应付查找账户
		List<Account> accountList = accountDAO.findAccount(shopId, type);

		return unitDAO.findUnitByAccount(act,accountList);
		
//		List<Integer> unitIdList=new ArrayList<Integer>();
//		List<Unit> unitList=new ArrayList<Unit>();
//		
//		//获得所有已经开户的往来单位的id
//		if(accountList!=null&&accountList.size()>0){
//			for(int i=0;i<accountList.size();i++){
//				unitIdList.add(accountList.get(i).getDealingUnitId());
//			}
//		}
//		
//		//获得所有往来单位
//		if(unitIdList!=null&&unitIdList.size()>0){
//			for(int i=0;i<unitIdList.size();i++){
//				unitList.add(unitDAO.findById(unitIdList.get(i)));
//			}
//		}
//	
//		return unitList;	
	}
	
//	//所有的减去停用的往来单位
//	public List<Unit> findNoAccountUnit(IFieldValidation act, Page page, int shopId){	
//		List<Unit> allUnitList=unitDAO.findUnitNoShop(act, page, shopId);
//		//删除自己
//		for (int i = allUnitList.size() - 1; i >= 0; i--) {
//			if (allUnitList.get(i).getId().equals(shopId)){
//				allUnitList.remove(allUnitList.get(i));
//				break;
//			}
//		}
//		//加上绿源
//		allUnitList.add(unitDAO.findById(1));
//		
//		
//		//删除停用的
//		List <Account> accountList=accountDAO.findDisabledAccount(shopId);
//		List<Integer> unitIdList=new ArrayList<Integer>();
//		
//		if(accountList!=null&&accountList.size()>0){
//			for(int i=0;i<accountList.size();i++){
//				unitIdList.add(accountList.get(i).getDealingUnitId());
//			}
//		}
//		
//		for (int i = allUnitList.size() - 1; i >= 0; i--) {
//			for (int j = 0; j < unitIdList.size(); j++)
//				if (allUnitList.get(i).getId().equals(unitIdList.get(j))) {
//					allUnitList.remove(allUnitList.get(i));
//					unitIdList.remove(unitIdList.get(j));
//					break;
//				}
//		}	
//		
//		return allUnitList;	
//	}
	
//	//ww
//	public List<Unit> findUnit(int dealerId){
//		return unitDAO.findUnit(act, page, dealerId, false);
//	}
	
	//DAO
	private UnitDAO unitDAO;
	public void setUnitDAO(UnitDAO unitDAO) {
		this.unitDAO = unitDAO;
	}
	
	private AccountDAO accountDAO;
	public void setAccountDAO(AccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}
}
