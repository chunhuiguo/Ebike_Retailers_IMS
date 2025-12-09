package com.luyuan.info.dao.impl;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.luyuan.info.dao.PriceDAO;
import com.luyuan.info.entity.Price;

/**
 * A data access object (DAO) providing persistence and search support for Price
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.luyuan.info.entity.Price
 * @author MyEclipse Persistence Tools
 */

public class PriceDAOImpl extends HibernateDaoSupport implements PriceDAO{
	private static final Log log = LogFactory.getLog(PriceDAOImpl.class);
	// property constants
	public static final String DEALER_ID = "dealerId";
	public static final String DEALER_SHORT_NAME = "dealerShortName";
	public static final String PRODUCT_ID = "productId";
	public static final String PRODUCT_CODE = "productCode";
	public static final String PREFIX_NAME = "prefixName";
	public static final String SUFFIX_NAME = "suffixName";
	public static final String COLOR_NAME = "colorName";
	public static final String PRICE_TYPE_ID = "priceTypeId";
	public static final String PRICE_TYPE = "priceType";
	public static final String PRICE = "price";
	public static final String DISCOUNT = "discount";

	protected void initDao() {
		// do nothing
	}

	public void save(Price transientInstance) {
		log.debug("saving Price instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Price persistentInstance) {
		log.debug("deleting Price instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Price findById(java.lang.Long id) {
		log.debug("getting Price instance with id: " + id);
		try {
			Price instance = (Price) getHibernateTemplate().get(
					"com.luyuan.info.entity.Price", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Price instance) {
		log.debug("finding Price instance by example");
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
		log.debug("finding Price instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Price as model where model."
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

	public List findByProductId(Object productId) {
		return findByProperty(PRODUCT_ID, productId);
	}

	public List findByProductCode(Object productCode) {
		return findByProperty(PRODUCT_CODE, productCode);
	}

	public List findByPrefixName(Object prefixName) {
		return findByProperty(PREFIX_NAME, prefixName);
	}

	public List findBySuffixName(Object suffixName) {
		return findByProperty(SUFFIX_NAME, suffixName);
	}

	public List findByColorName(Object colorName) {
		return findByProperty(COLOR_NAME, colorName);
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
		log.debug("finding all Price instances");
		try {
			String queryString = "from Price";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Price merge(Price detachedInstance) {
		log.debug("merging Price instance");
		try {
			Price result = (Price) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Price instance) {
		log.debug("attaching dirty Price instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Price instance) {
		log.debug("attaching clean Price instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static PriceDAOImpl getFromApplicationContext(ApplicationContext ctx) {
		return (PriceDAOImpl) ctx.getBean("PriceDAO");
	}

	public Price findByDealerIdAndProductIdAndPriceTypeId(Integer dealerId,
			Long productId, Integer priceTypeId) {
		List<Price> list = getHibernateTemplate().find(
				"from Price as model where model.dealerId=" 
				+ dealerId + " and model.productId=" + productId + "and model.priceTypeId="+priceTypeId);
		
		if(list != null && list.size() != 0)
			return list.get(0);
		else
			return null;		
	}

	public void update(Price price) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(price);
	}

	public List<Price> findByDealerIdAndProductId(Integer dealerId, Long id) {
		// TODO Auto-generated method stub
		String queryString = "from Price as model where model.dealerId="+ dealerId +" and model.productId=" + id;
		return getHibernateTemplate().find(queryString);
	}

	public List<Price> findByDealerId(Integer dealerId) {
		// TODO Auto-generated method stub
		String queryString = "from Price as model where model.dealerId='"+ dealerId +"'";
		return getHibernateTemplate().find(queryString);
	}

	public Price findPrice(Integer dealerId, Long productId, Integer priceTypeId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Price> findPrice(Integer dealerId, Integer priceTypeId) {
		String queryString = "from Price as model where model.dealerId="+ dealerId +" and model.priceTypeId=" + priceTypeId;
		return getHibernateTemplate().find(queryString);
	}

	public Price findPrice(Integer dealerId, Integer priceTypeId, String code) {
		// TODO Auto-generated method stub
		List<Price> list = getHibernateTemplate().find(
				"from Price as model where model.dealerId=" 
				+ dealerId + " and model.priceTypeId=" + priceTypeId + "and model.productCode='"+ code + "'");
		
		if(list != null && list.size() != 0)
			return list.get(0);
		else
			return null;
	}

	public Price findPrice(Integer dealerId, String productCode, String code,
			Integer priceTypeId) {
		List<Price> list = getHibernateTemplate().find(
				"from Price as model where model.dealerId=" 
				+ dealerId + " and model.productCode='" + productCode + "' or model.productCode='" + code + "' and model.priceTypeId=" + priceTypeId );
		
		if(list != null && list.size() != 0)
			return list.get(0);
		else
			return null;
	}

	
	}
