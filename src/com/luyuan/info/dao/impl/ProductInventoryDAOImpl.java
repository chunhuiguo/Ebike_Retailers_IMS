package com.luyuan.info.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.luyuan.action.IFieldValidation;
import com.luyuan.info.dao.ProductInventoryDAO;
import com.luyuan.info.entity.ProductInventory;
import com.luyuan.util.Page;
import com.luyuan.util.PaginateHib;
import com.luyuan.util.SqlCallback;


public class ProductInventoryDAOImpl extends PaginateHib implements ProductInventoryDAO {

	public List<ProductInventory> findByShopId(final IFieldValidation act, final int shopId) {
		SqlCallback sqlCallback = new SqlCallback(){
			public void setPageSql(Page page) {
				page.getAttachSql().append(" and (model.shopId IS NULL").append(" or model.shopId=").append(shopId).append(") order by model.qty desc");						
			}
		};
		return this.findList(act, sqlCallback);
	}
	
	public List<ProductInventory> findByDealerId(final IFieldValidation act, final int dealerId) {
		SqlCallback sqlCallback = new SqlCallback(){
			public void setPageSql(Page page) {
				page.getAttachSql().append(" and (model.dealerId IS NULL").append(" or model.dealerId=").append(dealerId).append(") order by model.qty desc");						
			}
		};
		return this.findList(act, sqlCallback);
	}
	
	public List<ProductInventory> findByWarehouseId(final IFieldValidation act, final int warehouseId) {
		SqlCallback sqlCallback = new SqlCallback(){
			public void setPageSql(Page page) {
				page.getAttachSql().append(" and (model.warehouseId IS NULL").append(" or model.warehouseId=").append(warehouseId).append(") order by model.qty desc");				
			}
		};
		return this.findList(act, sqlCallback);
	}
	
	//************************************************************************
	private List findByProperty(String propertyName, Object value) {
	      log.debug("finding ProductInvneotry instance with property: " + propertyName
	            + ", value: " + value);
	      try {
	         String queryString = "from ProductInvneotry as model where model." 
	         						+ propertyName + "= ?";
			 return getHibernateTemplate().find(queryString, value);
	      } catch (RuntimeException re) {
	         log.error("find by property name failed", re);
	         throw re;
	      }
		}
	
   //日志
	private static final Log log = LogFactory.getLog(ProductInventoryDAOImpl.class);
}