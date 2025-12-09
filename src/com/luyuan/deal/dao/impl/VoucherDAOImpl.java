/**
 * @(#)VoucherDAOImpl.java  1.0 10/04/16
 *
 * Copyright 2010 luyuan-ebike, Inc. All rights reserved.
 * 
 */
package com.luyuan.deal.dao.impl;

import java.sql.SQLException;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import com.luyuan.action.IFieldValidation;
import com.luyuan.deal.dao.VoucherDAO;
import com.luyuan.deal.entity.PVoucher;
import com.luyuan.deal.entity.Voucher;
import com.luyuan.util.Page;
import com.luyuan.util.PaginateHib;
import com.luyuan.util.SqlCallback;

/**
 * 
 * 最终会被注入的VoucherDAO，实现DAO接口逻辑
 *
 * @see com.luyuan.deal.entity.Voucher
 * @author gch
 */

public class VoucherDAOImpl extends PaginateHib implements VoucherDAO {
	
	public long save(Voucher transientInstance) {
		log.debug("saving " + className + " instance");
		try {
			PVoucher pvoucher = new PVoucher();
			if(className.equals("PVoucher")) {
				pvoucher = this.voucherCopy(transientInstance);
				getHibernateTemplate().save(pvoucher);
			}
			else
				getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
			if(className.equals("PVoucher"))
				return pvoucher.getId();			
			else
				return transientInstance.getId();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	public void update(Voucher transientInstance) {
		log.debug("updating " + className + " instance");
		try {

			if(className.equals("PVoucher"))
				getHibernateTemplate().merge(this.voucherCopy(transientInstance));
			else
				getHibernateTemplate().merge(transientInstance);
			//getHibernateTemplate().flush();			
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}	
	
	public List<Voucher> findVoucher(final IFieldValidation act, final int shopId, final boolean isChecked, final String startDate, final String endDate, final boolean isDealer) {
		log.debug("finding " + className + " instance with shopId: " + shopId + " and isChecked: " + isChecked);
		SqlCallback sqlCallback = new SqlCallback(){
			public void setPageSql(Page page) {
				if(isDealer) {
					if(! isChecked)
						page.getAttachSql().append(" and model.dealerId=").append(shopId).append(" and model.isChecked=").append(isChecked).append(" order by model.checkDate desc ");
					else
						page.getAttachSql().append(" and model.dealerId=").append(shopId).append(" and model.isChecked=").append(isChecked)	
						.append(" and model.checkDate between '").append(startDate).append("'") 
						.append(" and '").append(endDate).append("' order by model.checkDate desc ");
				}
				else {					
					if(! isChecked)
						page.getAttachSql().append(" and model.shopId=").append(shopId).append(" and model.isChecked=").append(isChecked).append(" order by model.checkDate desc ");
					else
						page.getAttachSql().append(" and model.shopId=").append(shopId).append(" and model.isChecked=").append(isChecked)	
						.append(" and model.checkDate between '").append(startDate).append("'") 
						.append(" and '").append(endDate).append("' order by model.checkDate desc ");
				}
			}
		};
		return this.findList(act, sqlCallback);			
	}
	
	public List<Voucher> findVoucher(final IFieldValidation act, final int shopId, final boolean isChecked, final String voucherType, final String startDate, final String endDate, final boolean isDealer) {
		log.debug("finding " + className + " instance with shopId: " + shopId
				+ " and isChecked: " + isChecked + " and type:" + voucherType);
		SqlCallback sqlCallback = new SqlCallback(){
			public void setPageSql(Page page) {
				if(isDealer) {
					if(! isChecked)
						page.getAttachSql().append(" and model.dealerId=").append(shopId)
						.append(" and model.isChecked=").append(isChecked).append(" and model.type='")
						.append(voucherType).append("' order by model.checkDate desc ");
					else
						page.getAttachSql().append(" and model.dealerId=").append(shopId)
						.append(" and model.isChecked=").append(isChecked).append(" and model.type='")
						.append(voucherType).append("' and model.checkDate between '").append(startDate).append("'") 
						.append(" and '").append(endDate).append("' order by model.checkDate desc ");
				}
				else {					
					if(! isChecked)
						page.getAttachSql().append(" and model.shopId=").append(shopId)
						.append(" and model.isChecked=").append(isChecked).append(" and model.type='")
						.append(voucherType).append("' order by model.checkDate desc ");
					else
						page.getAttachSql().append(" and model.shopId=").append(shopId)
						.append(" and model.isChecked=").append(isChecked).append(" and model.type='")
						.append(voucherType).append("' and model.checkDate between '").append(startDate).append("'") 
						.append(" and '").append(endDate).append("' order by model.checkDate desc ");
				}
			}
		};
		return this.findList(act, sqlCallback);		
	}
	
	public List<Voucher> findVoucher(final IFieldValidation act, final int shopId, final boolean isChecked, final boolean isError, final String startDate, final String endDate, final boolean isDealer) {
		log.debug("finding " + className + " instance with shopId: " + shopId
				+ " and isChecked: " + isChecked + " and isError: " + isError);
		SqlCallback sqlCallback = new SqlCallback(){
			public void setPageSql(Page page) {
				if(isDealer) {
					if(! isChecked)
						page.getAttachSql().append(" and model.dealerId=").append(shopId)
						.append(" and model.isChecked=").append(isChecked).append(" and model.isError=")
						.append(isError).append(" order by model.checkDate desc ");
					else
						page.getAttachSql().append(" and model.dealerId=").append(shopId)
						.append(" and model.isChecked=").append(isChecked).append(" and model.isError=")
						.append(isError).append(" and model.checkDate between '").append(startDate).append("'") 
						.append(" and '").append(endDate).append("' order by model.checkDate desc ");
				}
				else {					
					if(! isChecked)
						page.getAttachSql().append(" and model.shopId=").append(shopId)
						.append(" and model.isChecked=").append(isChecked).append(" and model.isError=")
						.append(isError).append(" order by model.checkDate desc ");
					else
						page.getAttachSql().append(" and model.shopId=").append(shopId)
						.append(" and model.isChecked=").append(isChecked).append(" and model.isError=")
						.append(isError).append(" and model.checkDate between '").append(startDate).append("'") 
						.append(" and '").append(endDate).append("' order by model.checkDate desc ");
				}
			}
		};
		return this.findList(act, sqlCallback);			
	}
	
	public List<Voucher> findVoucher(final IFieldValidation act, final int shopId, final boolean isChecked, final String voucherType, final boolean isError, final String startDate, final String endDate, final boolean isDealer) {
		log.debug("finding " + className + " instance with shopId: " + shopId
				+ " and isChecked: " + isChecked + " and type:" + voucherType
				+ " and isError: " + isError);
		SqlCallback sqlCallback = new SqlCallback(){
			public void setPageSql(Page page) {
				if(isDealer) {
					if(! isChecked)
						page.getAttachSql().append(" and model.dealerId=").append(shopId)
						.append(" and model.isChecked=").append(isChecked).append(" and model.type='")
						.append(voucherType).append("' and model.isError=")
						.append(isError).append(" order by model.checkDate desc ");
					else
						page.getAttachSql().append(" and model.dealerId=").append(shopId)
						.append(" and model.isChecked=").append(isChecked).append(" and model.type='")
						.append(voucherType).append("' and model.isError=").append(isError)
						.append(" and model.checkDate between '").append(startDate).append("'") 
						.append(" and '").append(endDate).append("' order by model.checkDate desc ");
				}
				else {					
					if(! isChecked)
						page.getAttachSql().append(" and model.shopId=").append(shopId)
						.append(" and model.isChecked=").append(isChecked).append(" and model.type='")
						.append(voucherType).append("' and model.isError=")
						.append(isError).append(" order by model.checkDate desc ");
					else
						page.getAttachSql().append(" and model.shopId=").append(shopId)
						.append(" and model.isChecked=").append(isChecked).append(" and model.type='")
						.append(voucherType).append("' and model.isError=").append(isError)
						.append(" and model.checkDate between '").append(startDate).append("'") 
						.append(" and '").append(endDate).append("' order by model.checkDate desc ");
				}
			}
		};
		return this.findList(act, sqlCallback);				
	}
	
	//整车业务单据历史查询
	public List<Voucher> findVoucher(final IFieldValidation act, final int shopId, final Voucher voucher, final boolean isDealer) {
		log.debug("finding " + className + " instance with shopId: " + shopId
				+ " amd input query condition");
		SqlCallback sqlCallback = new SqlCallback(){
			public void setPageSql(Page page) {	
				if(isDealer)
					page.getAttachSql().append(" and model.dealerId=").append(shopId).append(" and model.isChecked=true");
				else
					page.getAttachSql().append(" and model.shopId=").append(shopId).append(" and model.isChecked=true");
				if(! voucher.getCode().equals(""))
					page.getAttachSql().append(" and model.code='").append(voucher.getCode()).append("'");					
				if(! voucher.getShipOrderCode().equals(""))
					page.getAttachSql().append(" and model.shipOrderCode='").append(voucher.getShipOrderCode()).append("'");					
				if(voucher.getDealingUnitId() != null)
					page.getAttachSql().append(" and model.dealingUnitId=").append(voucher.getDealingUnitId());					
				if(voucher.getHandlerId() != null)
					page.getAttachSql().append(" and model.handlerId=").append(voucher.getHandlerId());					
				if(voucher.getWarehouseId() != null)
					page.getAttachSql().append(" and model.warehouseId=").append(voucher.getWarehouseId());					
				if(! voucher.getBrief().equals("所有单据"))
					page.getAttachSql().append(" and model.type='").append(voucher.getBrief()).append("'");		
				if(voucher.getIsError() != null)
					page.getAttachSql().append(" and model.isError=").append(voucher.getIsError());				
				if(! voucher.getCheckDate().equals("") && ! voucher.getCreateDate().equals(""))
					page.getAttachSql().append(" and model.checkDate between '").append(voucher.getCheckDate())
										.append("' and '").append(voucher.getCreateDate()).append("'");				
				page.getAttachSql().append(" order by model.checkDate desc ");				
			}
		};
		return this.findList(act, sqlCallback);					
	}
	
	public Voucher findById(java.lang.Long id) {
		log.debug("getting " + className + " instance with id: " + id);
		try {
			Voucher instance = (Voucher) getHibernateTemplate().get(
					"com.luyuan.deal.entity." + className, id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public void deleteById(final List<Long> voucherIdList) {
		this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String sql = "delete " + className + " as model where 1 = 0";
				if (voucherIdList != null) {
					for(int i = 0; i < voucherIdList.size(); i++)
						sql += " or model.id= " + voucherIdList.get(i).longValue();
				}
				Query query = session.createQuery(sql);	
				query.executeUpdate();
				return null;
			}
		});
    }

	public void evcit(Voucher voucher) {
		getHibernateTemplate().evict(voucher);
	}
	
	
	public List<Voucher> findDraft( Integer shopId,  String beginDate,  String endDate) {
		log.debug("finding " + className + " instance with shopId: "
				+ " and input query condition for a piece of time");
		String queryString = "from "+className+" as model where model.dealerId="
			+ shopId + " and model.isChecked=false and model.createDate between '"+beginDate+"' and '"+endDate+"'";
		return getHibernateTemplate().find(queryString);
	}

	public List<Voucher> findDraft(Integer warehouseId, String checkDate) {
		String queryString = "from "+className+" as model where model.warehouseId="
		+ warehouseId + " and model.isChecked=false and model.createDate='"+checkDate+"'";
	return getHibernateTemplate().find(queryString);
	}		
	//class inside method
	private PVoucher voucherCopy(Voucher voucher){
		PVoucher pvoucher = new PVoucher();
		
		pvoucher.setId(voucher.getId());
		pvoucher.setCode(voucher.getCode());
		pvoucher.setType(voucher.getType());
		pvoucher.setSubAccountId(voucher.getSubAccountId());
		pvoucher.setSubAccountType(voucher.getSubAccountType());
		pvoucher.setDealerId(voucher.getDealerId());
		pvoucher.setShopId(voucher.getShopId());
		pvoucher.setDealerShortName(voucher.getDealerShortName());
		pvoucher.setShopShortName(voucher.getShopShortName());
		pvoucher.setWarehouseId(voucher.getWarehouseId());
		pvoucher.setWarehouseName(voucher.getWarehouseName());
		pvoucher.setDealingUnitId(voucher.getDealingUnitId());
		pvoucher.setDealingUnitShortName(voucher.getDealingUnitShortName());
		pvoucher.setShipOrderCode(voucher.getShipOrderCode());
		pvoucher.setIsChecked(voucher.getIsChecked());
		pvoucher.setIsError(voucher.getIsError());
		pvoucher.setTotal(voucher.getTotal());
		pvoucher.setDiscount(voucher.getDiscount());
		pvoucher.setActualTotal(voucher.getActualTotal());
		pvoucher.setPaidMoney(voucher.getPaidMoney());
		pvoucher.setHandlerId(voucher.getHandlerId());
		pvoucher.setHandlerName(voucher.getHandlerName());
		pvoucher.setCreatorId(voucher.getCreatorId());
		pvoucher.setCreatorName(voucher.getCreatorName());
		pvoucher.setAccountantId(voucher.getAccountantId());
		pvoucher.setAccountantName(voucher.getAccountantName());
		pvoucher.setCreateDate(voucher.getCreateDate());
		pvoucher.setCheckDate(voucher.getCheckDate());
		pvoucher.setBrief(voucher.getBrief());
		pvoucher.setRemark(voucher.getRemark());
		
		return pvoucher;
	}
	
//	private List<Voucher> pvoucherListCopy(List<PVoucher> pvoucherList) {
//		List<Voucher> voucherList = new ArrayList<Voucher>();
//		
//		for(int i = 0; i < pvoucherList.size(); i++) {
//			Voucher pvoucher = new Voucher();
//			PVoucher voucher = pvoucherList.get(i);
//			
//			pvoucher.setId(voucher.getId());
//			pvoucher.setCode(voucher.getCode());
//			pvoucher.setType(voucher.getType());
//			pvoucher.setSubAccountId(voucher.getSubAccountId());
//			pvoucher.setSubAccountType(voucher.getSubAccountType());
//			pvoucher.setDealerId(voucher.getDealerId());
//			pvoucher.setShopId(voucher.getShopId());
//			pvoucher.setDealerShortName(voucher.getDealerShortName());
//			pvoucher.setShopShortName(voucher.getShopShortName());
//			pvoucher.setWarehouseId(voucher.getWarehouseId());
//			pvoucher.setWarehouseName(voucher.getWarehouseName());
//			pvoucher.setDealingUnitId(voucher.getDealingUnitId());
//			pvoucher.setDealingUnitShortName(voucher.getDealingUnitShortName());
//			pvoucher.setShipOrderCode(voucher.getShipOrderCode());
//			pvoucher.setIsChecked(voucher.getIsChecked());
//			pvoucher.setIsError(voucher.getIsError());
//			pvoucher.setTotal(voucher.getTotal());
//			pvoucher.setDiscount(voucher.getDiscount());
//			pvoucher.setActualTotal(voucher.getActualTotal());
//			pvoucher.setPaidMoney(voucher.getPaidMoney());
//			pvoucher.setHandlerId(voucher.getHandlerId());
//			pvoucher.setHandlerName(voucher.getHandlerName());
//			pvoucher.setCreatorId(voucher.getCreatorId());
//			pvoucher.setCreatorName(voucher.getCreatorName());
//			pvoucher.setAccountantId(voucher.getAccountantId());
//			pvoucher.setAccountantName(voucher.getAccountantName());
//			pvoucher.setCreateDate(voucher.getCreateDate());
//			pvoucher.setCheckDate(voucher.getCheckDate());
//			pvoucher.setBrief(voucher.getBrief());
//			pvoucher.setRemark(voucher.getRemark());
//			
//			voucherList.add(pvoucher);
//		}		
//		return voucherList;
//	}

	//日志
	private static final Log log = LogFactory.getLog(VoucherDAOImpl.class);

}