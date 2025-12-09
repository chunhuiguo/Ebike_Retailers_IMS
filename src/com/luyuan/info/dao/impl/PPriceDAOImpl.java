package com.luyuan.info.dao.impl;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.luyuan.info.dao.PPriceDAO;
import com.luyuan.info.entity.PPrice;
import com.luyuan.info.entity.Price;

/**
 * A data access object (DAO) providing persistence and search support for
 * PPrice entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.luyuan.info.entity.PPrice
 * @author MyEclipse Persistence Tools
 */

public class PPriceDAOImpl extends HibernateDaoSupport implements PPriceDAO{
	private static final Log log = LogFactory.getLog(PPriceDAOImpl.class);
	// property constants
	public static final String DEALER_ID = "dealerId";
	public static final String DEALER_SHORT_NAME = "dealerShortName";
	public static final String PART_ID = "partId";
	public static final String PART_CODE = "partCode";
	public static final String PART_NAME = "partName";
	public static final String SPEC_TYPE = "specType";
	public static final String PART_COLOR = "partColor";
	public static final String PRICE_TYPE_ID = "priceTypeId";
	public static final String PRICE_TYPE = "priceType";
	public static final String PRICE = "price";
	public static final String DISCOUNT = "discount";

	protected void initDao() {
		// do nothing
	}

	public void save(PPrice transientInstance) {
		log.debug("saving PPrice instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(PPrice persistentInstance) {
		log.debug("deleting PPrice instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public PPrice findById(java.lang.Long id) {
		log.debug("getting PPrice instance with id: " + id);
		try {
			PPrice instance = (PPrice) getHibernateTemplate().get(
					"com.luyuan.info.entity.PPrice", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(PPrice instance) {
		log.debug("finding PPrice instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding PPrice instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from PPrice as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByDealerId(Object dealerId) {
		return findByProperty(DEALER_ID, dealerId);
	}

	public List findByDealerShortName(Object dealerShortName) {
		return findByProperty(DEALER_SHORT_NAME, dealerShortName);
	}

	public List findByPartId(Object partId) {
		return findByProperty(PART_ID, partId);
	}

	public List findByPartCode(Object partCode) {
		return findByProperty(PART_CODE, partCode);
	}

	public List findByPartName(Object partName) {
		return findByProperty(PART_NAME, partName);
	}

	public List findBySpecType(Object specType) {
		return findByProperty(SPEC_TYPE, specType);
	}

	public List findByPartColor(Object partColor) {
		return findByProperty(PART_COLOR, partColor);
	}

	public List findByPriceTypeId(Object priceTypeId) {
		return findByProperty(PRICE_TYPE_ID, priceTypeId);
	}

	public List findByPriceType(Object priceType) {
		return findByProperty(PRICE_TYPE, priceType);
	}

	public List findByPrice(Object price) {
		return findByProperty(PRICE, price);
	}

	public List findByDiscount(Object discount) {
		return findByProperty(DISCOUNT, discount);
	}

	public List findAll() {
		log.debug("finding all PPrice instances");
		try {
			String queryString = "from PPrice";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public PPrice merge(PPrice detachedInstance) {
		log.debug("merging PPrice instance");
		try {
			PPrice result = (PPrice) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(PPrice instance) {
		log.debug("attaching dirty PPrice instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PPrice instance) {
		log.debug("attaching clean PPrice instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}


	public static PPriceDAOImpl getFromApplicationContext(ApplicationContext ctx) {
		return (PPriceDAOImpl) ctx.getBean("PPriceDAO");
	}

	public PPrice findByDealerIdAndPartIdAndPriceTypeId(Integer dealerId,
			Long partId, Integer priceTypeId) {
		// TODO Auto-generated method stub
		List<PPrice> list = getHibernateTemplate().find(
				"from PPrice as model where model.dealerId='" 
				+ dealerId + "' and model.partId='" + partId + "'and model.priceTypeId='"+priceTypeId+"'");		
		if(list != null && list.size() != 0)
			return list.get(0);
		else
			return null;
	}

	public void update(PPrice pPrice) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(pPrice);
	}

	public PPrice findPPrice(Integer dealerId, Integer priceTypeId, String code) {
		List<PPrice> list = getHibernateTemplate().find(
				"from PPrice as model where model.dealerId=" 
				+ dealerId + " and model.priceTypeId=" + priceTypeId + "and model.partCode='"+ code + "'");
		
		if(list != null && list.size() != 0)
			return list.get(0);
		else
			return null;
	}
}