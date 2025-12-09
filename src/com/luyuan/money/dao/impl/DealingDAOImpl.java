package com.luyuan.money.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.luyuan.action.IFieldValidation;
import com.luyuan.deal.entity.Voucher;
import com.luyuan.money.dao.DealingDAO;
import com.luyuan.money.entity.Dealing;
import com.luyuan.util.Page;
import com.luyuan.util.PaginateHib;
import com.luyuan.util.SqlCallback;





/**
 * 
 */

public class DealingDAOImpl extends  PaginateHib implements DealingDAO{
	
	public void save(Dealing transientInstance){
		log.debug("saving Dealing instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	public void update(Dealing dealing){
		getHibernateTemplate().update(dealing);
		getHibernateTemplate().flush();
	}
	
	public Dealing findByExample(Dealing dealing){
		 return (Dealing)getHibernateTemplate().findByExample(dealing).get(0);
			
	}
	
	public void delete(long id){
		getHibernateTemplate().delete(findById(id));
	}
	
	public List<Dealing> findDealing(IFieldValidation act, final Integer dealerId, final boolean isChecked) {
		log.debug("finding " + className + " instance with dealerId: "
				+ dealerId + " and isChecked: " + isChecked);
		SqlCallback sqlCallback = new SqlCallback() {
			public void setPageSql(Page page) {
				page.getAttachSql().append(" and model.dealerId=").append(
						dealerId).append(" and model.isChecked=").append(
						isChecked).append(" order by model.checkDate desc");
			}
		};
		return this.findList(act, sqlCallback);
	}
	
	public List<Dealing> findDealing(int dealerId, Dealing dealing){
		try {			
			String hql = "from Dealing as model where model.shopId=" + dealerId + " and model.isChecked=true";
			if(! dealing.getCode().equals(""))
				hql = hql + " and model.code='" + dealing.getCode() + "'";		
			if(dealing.getDealingUnitId() != null)
				hql = hql + " and model.dealingUnitId=" + dealing.getDealingUnitId();
			if(! dealing.getBrief().equals("所有单据"))
				hql = hql + " and model.type='" + dealing.getBrief() + "'";
			if(dealing.getIsError() != null)
				hql = hql + " and model.isError=" + dealing.getIsError();
			if(! dealing.getCheckDate().equals("") && ! dealing.getCreateDate().equals(""))
				hql = hql + " and model.checkDate between '" + dealing.getCheckDate()
						+ "' and '" + dealing.getCreateDate() + "'";			
			hql = hql + " order by model.checkDate desc";			
			
			List<Dealing> list = getHibernateTemplate().find(hql);
			return list;
				
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	
	public List<Dealing> findDealing(IFieldValidation act, final Integer dealerId, final String type,
			final boolean isChecked, final String errorType) {
		log.debug("finding " + className + " instance with dealerId: "
				+ dealerId + " and isChecked: " + isChecked + " and type: "
				+ type + " and errorType: " + errorType);
		SqlCallback sqlCallback = new SqlCallback() {
			public void setPageSql(Page page) {
				page.getAttachSql().append(" and model.dealerId=").append(
						dealerId).append(" and model.isChecked=").append(
						isChecked);
				if(errorType.equals("未冲抵单据"))
					page.getAttachSql().append(" and model.isError=").append(false);
				if(errorType.equals("冲抵单据"))
					page.getAttachSql().append(" and model.isError=").append(true);
				if (type.equals("付款单"))	
					page.getAttachSql().append(" and model.type='").append(type).append("'");
				if (type.equals("收款单"))	
					page.getAttachSql().append(" and model.type='").append(type).append("'");
				page.getAttachSql().append(" order by model.checkDate desc");
			}
		};
		return this.findList(act, sqlCallback);
	}

	public List<Dealing> findDealing(IFieldValidation act, final Integer dealerId, final String type, final boolean isChecked) {
		log.debug("finding " + className + " instance with dealerId: "
				+ dealerId + " and isChecked: " + isChecked + " and type: "
				+ type);
		SqlCallback sqlCallback = new SqlCallback() {
			public void setPageSql(Page page) {
				page.getAttachSql().append(" and model.dealerId=").append(
						dealerId).append(" and model.isChecked=").append(
						isChecked).append(" and model.type='").append(type)
						.append("' order by model.checkDate desc");
			}
		};
		return this.findList(act, sqlCallback);
	}
	

	public Dealing findById(java.lang.Long id) {
		log.debug("getting Dealing instance with id: " + id);
		try {
			Dealing instance = (Dealing) getHibernateTemplate().get(
					"com.luyuan.money.entity.Dealing", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public void evcit(Dealing dealing) {
		getHibernateTemplate().evict(dealing);
	}
	
	
	//billquery页面查询函数
	public  List<Dealing> findDealing(final IFieldValidation act, final int dealerId,final Dealing dealing){
		log.debug("finding " + className + " instance with dealerId: " + dealerId
				+ " and input query condition");
		SqlCallback sqlCallback = new SqlCallback(){
			public void setPageSql(Page page) {				
				page.getAttachSql().append(" and model.dealerId=").append(dealerId).append(" and model.isChecked=true");
				if(! dealing.getCode().equals(""))
					page.getAttachSql().append(" and model.code='").append(dealing.getCode()).append("'");									
				if(dealing.getDealingUnitId() != null)
					page.getAttachSql().append(" and model.dealingUnitId=").append(dealing.getDealingUnitId());							
				if(! dealing.getBrief().equals("所有单据"))
					page.getAttachSql().append(" and model.type='").append(dealing.getBrief()).append("'");		
				if(dealing.getIsError() != null)
					page.getAttachSql().append(" and model.isError=").append(dealing.getIsError());				
				if(! dealing.getCheckDate().equals("") && ! dealing.getCreateDate().equals(""))
					page.getAttachSql().append(" and model.checkDate between '").append(dealing.getCheckDate())
										.append("' and '").append(dealing.getCreateDate()).append("'");				
				page.getAttachSql().append(" order by model.checkDate desc");				
			}
		};
		return this.findList(act, sqlCallback);	
	}

	public List<Dealing> findDraft( Integer shopId,  String beginDate, String endDate) {
		log.debug("finding " + className + " instance with shopId: "
				+ " and input query condition for a piece of time");
		String queryString = "from Dealing as model where model.dealerId="
				+ shopId
				+ " and model.isChecked=false and model.createDate between '"
				+ beginDate + "' and '" + endDate + "'";
		List<Dealing> dealingList = getHibernateTemplate().find(queryString);
		return dealingList;
		
	}
	
	//*************************************************************************
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Dealing instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Dealing as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	//log
	private static final Log log = LogFactory.getLog(DealingDAOImpl.class);

	
}