package com.luyuan.info.dao.impl;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.luyuan.info.dao.PriceTypeDAO;
import com.luyuan.info.entity.PriceType;

public class PriceTypeDAOImpl extends HibernateDaoSupport implements  PriceTypeDAO{
	
	
	private static final Log log = LogFactory.getLog(PriceTypeDAO.class);
	
	public void save(PriceType transientInstance) {
		log.debug("saving PriceType instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(PriceType persistentInstance) {
		log.debug("deleting PriceType instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public PriceType findById(java.lang.Integer id) {
		log.debug("getting PriceType instance with id: " + id);
		try {
			PriceType instance = (PriceType) getHibernateTemplate().get(
					"com.luyuan.info.entity.PriceType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(PriceType instance) {
		log.debug("finding PriceType instance by example");
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
		log.debug("finding PriceType instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from PriceType as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

//	public List findByDealerId(Object dealerId) {
//		return findByProperty("dealeId", dealerId);
//	}


	public List findAll() {
		log.debug("finding all PriceType instances");
		try {
			String queryString = "from PriceType";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public PriceType merge(PriceType detachedInstance) {
		log.debug("merging PriceType instance");
		try {
			PriceType result = (PriceType) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(PriceType instance) {
		log.debug("attaching dirty PriceType instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PriceType instance) {
		log.debug("attaching clean PriceType instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static PriceTypeDAO getFromApplicationContext(ApplicationContext ctx) {
		return (PriceTypeDAO) ctx.getBean("PriceTypeDAO");
	}

	public List<PriceType> findByDealerId(Integer dealerId) {
		// TODO Auto-generated method stub
		String queryString = "from PriceType as model where model.dealerId="+ dealerId;
		return getHibernateTemplate().find(queryString);
	}

	public void update(PriceType priceType) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(priceType);
	}

	public List<PriceType> findByDealerIdAndPpFlag(Integer dealerId,
			String ppFlag) {
		// TODO Auto-generated method stub
		String queryString = "from PriceType as model where model.dealerId='"+ dealerId +"' and model.ppFlag='" + ppFlag+"'";
		return getHibernateTemplate().find(queryString);
	}

	public PriceType findByDealerIdAndTypeAndFlagAndPpFlag(Integer dealerId,
			String type, String flag, String ppFlag) {
		// TODO Auto-generated method stub
		List<PriceType> list = getHibernateTemplate().find(
				"from PriceType as model where model.dealerId=" 
				+ dealerId + " and model.type='" + type +"' and model.flag='" + flag + "'and model.ppFlag='"+ppFlag+"'");
		
		if(list != null && list.size() != 0)
			return list.get(0);
		else
			return null;	
	}

	public List<PriceType> findPriceType(Integer dealerId, String ppFlag,
			String flag) {
		String queryString = "from PriceType as model where model.dealerId='"+ dealerId +"' and model.ppFlag='" + ppFlag+"'and model.flag='"+flag+"'";
		return getHibernateTemplate().find(queryString);
	}

	
}