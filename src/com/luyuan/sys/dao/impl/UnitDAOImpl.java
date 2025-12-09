/**
 * @(#)UnitDAOImpl.java  1.0 10/04/18
 *
 * Copyright 2010 luyuan-ebike, Inc. All rights reserved.
 * 
 */
package com.luyuan.sys.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.luyuan.action.IFieldValidation;
import com.luyuan.info.entity.Account;
import com.luyuan.sys.dao.UnitDAO;
import com.luyuan.sys.entity.Unit;
import com.luyuan.util.Page;
import com.luyuan.util.PaginateHib;
import com.luyuan.util.SQLConnection;

/**
 * 
 * 最终会被注入的UnitDAO，实现DAO接口逻辑
 *
 * @see com.luyuan.sys.entity.Unit
 * @author gch
 */

public class UnitDAOImpl extends PaginateHib implements UnitDAO {	
	
	public void save(Unit transientInstance) {
        log.debug("saving Unit instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
    public void update(Unit unit){
    	log.debug("updating Unit instance");
        try {
            getHibernateTemplate().update(unit);
            log.debug("update successful");
        } catch (RuntimeException re) {
            log.error("update failed", re);
            throw re;
        }
    }
	//gch   
  //所有经销商列表
	public List<Unit> findAllDealer(final IFieldValidation act) {
//		log.debug("finding Unit instance for supplier");
//		SqlCallback sqlCallback = new SqlCallback(){
//			public void setPageSql(Page page) {				
//				page.getAttachSql().append(" and model.id=1").append(" and model.disable=true").append(" or model.id=").append(parentId)
//						.append(" and model.id<>").append(shopId).append(" and model.type='经销商'").append(" and model.disable=true")
//						.append(" or model.parentId=").append(shopId).append(" and model.type='供应商'").append(" and model.disable=true");
//			}
//		};	
//		return this.findList(act, sqlCallback);
		
		List<Unit> unitList = new ArrayList<Unit>();
		
		if(this.validateRange(act))
			return unitList; 
		
		String sql = "select * from Unit where type='经销商' and disable=1";	
		
		String msg = "所有经销商";
		
		return this.executeQuery(act, unitList, sql, msg);		
	}
	
	//供应商
	public List<Unit> findSupplier(final IFieldValidation act, final int shopId, final int parentId) {
//		log.debug("finding Unit instance for supplier");
//		SqlCallback sqlCallback = new SqlCallback(){
//			public void setPageSql(Page page) {				
//				page.getAttachSql().append(" and model.id=1").append(" and model.disable=true").append(" or model.id=").append(parentId)
//						.append(" and model.id<>").append(shopId).append(" and model.type='经销商'").append(" and model.disable=true")
//						.append(" or model.parentId=").append(shopId).append(" and model.type='供应商'").append(" and model.disable=true");
//			}
//		};	
//		return this.findList(act, sqlCallback);
		
		List<Unit> unitList = new ArrayList<Unit>();
		
		if(this.validateRange(act))
			return unitList; 
		
		String sql = "select * from Unit where id=1 and disable=1 or id=" + parentId + " and id<>" + shopId + 
					" and type='经销商' and disable=1 or parentId=" + shopId + " and type='供应商' and disable=1";	
		
		String msg = "供应商";
		
		return this.executeQuery(act, unitList, sql, msg);		
	}
	
	//客户
	public List<Unit> findCustomer(final IFieldValidation act, final int shopId, final boolean isDealer) {
		log.debug("finding Unit instance with parentId: " + shopId + " and type: 零售客户/经销商/承包门店" + " and disable: True");
//		SqlCallback sqlCallback = new SqlCallback(){
//			public void setPageSql(Page page) {				
//				if(! isDealer) {
//					page.getAttachSql().append(" and model.parentId=").append(shopId)
//							.append(" and model.type='零售客户'").append(" and model.disable=true");					
//				}
//				else {
//					page.getAttachSql().append(" and model.id<>").append(shopId).append(" and model.parentId=")
//						.append(shopId).append(" and model.type in ('零售客户', '经销商', '承包门店')").append(" and model.disable=true");					
//				}
//			}
//		};
//		return this.findList(act, sqlCallback);	
		
		List<Unit> unitList = new ArrayList<Unit>();
				
		if(this.validateRange(act))
			return unitList;
		
		String sql = "select * from Unit where ";		
		if(! isDealer)
			sql = sql + "parentId=" + shopId + " and type='零售客户' and disable=1";					
		else
			sql = sql + "id<>" + shopId + " and parentId=" + shopId + " and type in ('零售客户', '经销商', '承包门店') and disable=1";
		
		String msg = "客户";
		
		return this.executeQuery(act, unitList, sql, msg);		
	}
	
	//经销商下属有效的直属门店
	public List<Unit> findUnit(final IFieldValidation act, final int dealerId) {
		log.debug("finding Unit instance with parentId: " + dealerId + " and type: 直属门店" + " and disable: True");
//		SqlCallback sqlCallback = new SqlCallback(){
//			public void setPageSql(Page page) {				
//				page.getAttachSql().append(" and model.parentId=").append(dealerId)
//						.append(" and model.type='直属门店'").append(" and model.disable=true");				
//			}
//		};
//		return this.findList(act, sqlCallback);
		
		List<Unit> unitList = new ArrayList<Unit>();
		
		if(this.validateRange(act))
			return unitList;
		
		String sql = "select * from Unit where parentId=" + dealerId + " and type='直属门店' and disable=1";		
		
		String msg = "经销商下属有效的直属门店";
		
		return this.executeQuery(act, unitList, sql, msg);
	}
	
	//直属门店的同级直属门店和所隶属的经销商
	public List<Unit> findUnit(final IFieldValidation act, final int shopId, final int partneId) {
		log.debug("finding Unit instance and Dealer");
//		SqlCallback sqlCallback = new SqlCallback(){
//			public void setPageSql(Page page) {				
//				page.getAttachSql().append(" and model.id=").append(partneId).append(" and model.disable=true")
//						.append(" or model.parentId=").append(partneId).append(" and model.id<>").append(shopId)
//						.append(" and model.type='直属门店'").append(" and model.disable=true");				
//			}
//		};
//		return this.findList(act, sqlCallback);
		
		List<Unit> unitList = new ArrayList<Unit>();
		
		if(this.validateRange(act))
			return unitList;		
		
		String sql = "select * from Unit where id=" + partneId + " and disable=1 or parentId=" 
					+ partneId + " and id<>" + shopId + " and type='直属门店' and disable=1";
		
		String msg = "直属门店的同级直属门店和所隶属的经销商";
		
		return this.executeQuery(act, unitList, sql, msg);
	}
	
	//经销商和承包门店的所有往来单位
	public List<Unit> findDealerBtype(final IFieldValidation act, final int shopId, final int parentId) {
		log.debug("finding Btype for Dealer");
//		SqlCallback sqlCallback = new SqlCallback(){
//			public void setPageSql(Page page) {				
//				page.getAttachSql().append(" and model.id=1").append(" and model.disable=true").append(" or model.id=")
//						.append(parentId).append(" and model.id<>").append(shopId).append(" and model.type='经销商'")
//						.append(" and model.disable=true").append(" or model.parentId=").append(shopId)
//						.append(" and model.id<>").append(shopId).append(" and model.disable=true");			
//			}
//		};
//		return this.findList(act, sqlCallback);	
		
		List<Unit> unitList = new ArrayList<Unit>();
		
		if(this.validateRange(act))
			return unitList;
		
		String sql = "select * from Unit where id=1 and disable=1 or id=" + parentId + " and id<>" + shopId + 
					" and type='经销商' and disable=1 or parentId=" + shopId + " and id<>" + shopId + " and disable=1";
		
		String msg = "经销商和承包门店的所有往来单位";
		
		return this.executeQuery(act, unitList, sql, msg);
	}
	
	//直属门店的所有往来单位
	public List<Unit> findUnitBtype(final IFieldValidation act, final int shopId, final int parentId) {
		log.debug("finding Btype for Unit");
//		SqlCallback sqlCallback = new SqlCallback(){
//			public void setPageSql(Page page) {				
//				page.getAttachSql().append(" and model.id=1").append(" and model.disable=true")
//						.append(" or model.id=").append(parentId).append(" and model.disable=true")
//						.append(" or model.parentId=").append(parentId).append(" and model.id<>")
//						.append(shopId).append(" and model.type in ('零售客户', '直属门店')").append(" and model.disable=true");			
//			}
//		};
//		return this.findList(act, sqlCallback);		
		
		List<Unit> unitList = new ArrayList<Unit>();
		
		if(this.validateRange(act))
			return unitList;
		
		String sql = "select * from Unit where id=1 and disable=1 or id=" + parentId + " and disable=1 or parentId=" 
					+ parentId + " and id<>" + shopId + " and type in ('零售客户', '直属门店') and disable=1";	
		
		String msg = "直属门店的所有往来单位";
		
		return this.executeQuery(act, unitList, sql, msg);
	}
	
	//往来单位管理
	public List<Unit> findManageUnit(final IFieldValidation act, final int shopId) {
		log.debug("finding Manage Unit");
//		SqlCallback sqlCallback = new SqlCallback(){
//			public void setPageSql(Page page) {				
//				page.getAttachSql().append(" and model.parentId=").append(shopId).append(" and model.id<>")
//						.append(shopId).append(" and model.type <> '经销商'");			
//			}
//		};
//		return this.findList(act, sqlCallback);		
		
		List<Unit> unitList = new ArrayList<Unit>();
		
		if(this.validateRange(act))
			return unitList;
		
		String sql = "select * from Unit where parentId=" + shopId + " and id<>" + shopId + " and type <> '经销商'";		
		
		String msg = "往来单位管理";
		
		return this.executeQuery(act, unitList, sql, msg);
	}
	
	//收付款单查询、开户往来单位
	public List<Unit> findDealingUnit(final IFieldValidation act, final int shopId, final int parentId) {
		log.debug("finding Dealing Unit");
//		SqlCallback sqlCallback = new SqlCallback(){
//			public void setPageSql(Page page) {				
//				page.getAttachSql().append(" and model.id=1").append(" and model.disable=true").append(" or model.id=").append(parentId)
//						.append(" and model.id<>").append(shopId).append(" and model.disable=true")
//						.append(" or model.parentId=").append(shopId).append(" and model.id<>").append(shopId)
//						.append(" and model.type in ('零售客户', '经销商', '承包门店','供应商')").append(" and model.disable=true");			
//			}
//		};
//		return this.findList(act, sqlCallback);	
		
		List<Unit> unitList = new ArrayList<Unit>();
		
		if(this.validateRange(act))
			return unitList;
		
		String sql = "select * from Unit where id=1 and disable=1 or id=" + parentId + " and id<>" + shopId + " and disable=1 or parentId=" 
					+ shopId + " and id<>" + shopId + " and type in ('零售客户', '经销商', '承包门店','供应商') and disable=1";
		
		String msg = "收付款单查询/开户往来单位";
		
		return this.executeQuery(act, unitList, sql, msg);
	}
	
	public Unit findById( java.lang.Integer id) {
        log.debug("getting Unit instance with id: " + id);
        try {
            Unit instance = (Unit) getHibernateTemplate()
                    .get("com.luyuan.sys.entity.Unit", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }	
	
	//返回经销商自己和所属的所有直属门店，仓库管理使用，员工管理使用
	public List<Unit> findDealerAndUnit(final IFieldValidation act, final int shopId) {
		log.debug("finding Unit instance with parentId: " + shopId + " and type: 直属门店" + " or id:" + shopId + " and disable: True");
//		SqlCallback sqlCallback = new SqlCallback(){
//			public void setPageSql(Page page) {				
//				page.getAttachSql().append(" and ( (model.parentId=").append(shopId)
//						.append(" and model.type='直属门店'").append(" and model.disable=true)").append(" or (model.id=").append(shopId).append(" and model.disable=true) )");				
//			}
//		};		
//		return this.findList(act, sqlCallback);	
		
		List<Unit> unitList = new ArrayList<Unit>();
		
		if(this.validateRange(act))
			return unitList;
		
		String sql = "select * from Unit where parentId=" + shopId + " and type='直属门店' and disable=1 or id=" + shopId + " and disable=1";
		
		String msg = "经销商自己和所属的所有直属门店";
		
		return this.executeQuery(act, unitList, sql, msg);
	}
	
	//返回经销商自己和所属的所有直属门店，用户管理使用
	public List<Unit> findDealerAndUnit(int shopId) {
		log.debug("finding Unit instance with parentId: " + shopId + " and type: 直属门店" + " or id:" + shopId + " and disable: True");
		try {
			List<Unit> list = getHibernateTemplate().find("from Unit as model where model.parentId=" + shopId + " and model.type='直属门店'" + " and model.disable=true or model.id=" + shopId + " and model.disable=true");						
			return list;				
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}		
	}
	
	//返回门店自己
	public List<Unit> findById(final IFieldValidation act, final int id) {
		log.debug("finding Unit instance with id: " + id + " and disable: True");
//		SqlCallback sqlCallback = new SqlCallback(){
//			public void setPageSql(Page page) {				
//				page.getAttachSql().append(" and model.id=").append(id).append(" and model.disable=true");				
//			}
//		};		
//		return this.findList(act, sqlCallback);
		
		List<Unit> unitList = new ArrayList<Unit>();
		
		if(this.validateRange(act))
			return unitList;
		
		String sql = "select * from Unit where id=" + id + " and disable=1";	
		
		String msg = "门店自己";
		
		return this.executeQuery(act, unitList, sql, msg);
	}
	
	//wml
	public Unit findByShortName(Object shortName) {
		List<Unit> list = this.findByProperty("shortName", shortName);
		if(list != null && list.size() != 0)
			return list.get(0);
		return null;		
	}
	
	//查找除直属门店以外的往来单位
	public List<Unit> findUnitNoShop(final IFieldValidation act, final int shopId) {
//		SqlCallback sqlCallback = new SqlCallback(){
//			public void setPageSql(Page page) {				
//				page.getAttachSql().append(" and model.parentId=").append(shopId)
//						.append(" and model.type not in ('直属门店') and model.disable=true");				
//			}
//		};		
//		if(this.findList(act, sqlCallback) != null)
//			return this.findList(act, sqlCallback);	
//		else
//			return new ArrayList<Unit>();
		
		List<Unit> unitList = new ArrayList<Unit>();
		
		if(this.validateRange(act))
			return unitList;
		
		String sql = "select * from Unit where parentId=" + shopId + " and type not in ('直属门店') and disable=1";		
		
		String msg = "除直属门店以外的往来单位";
		
		return this.executeQuery(act, unitList, sql, msg);
	}
	
	//收付款单选择往来单位
	public List<Unit> findUnitByAccount(final IFieldValidation act, final List<Account> accountList) {
		log.debug("finding Unit instance with account and disable: True");
//		SqlCallback sqlCallback = new SqlCallback() {
//			public void setPageSql(Page page) {
//				page.getAttachSql().append(" and model.id=").append(accountList.get(0).getDealingUnitId()).append(" and model.disable=true");
//				for (int i = 1; i < accountList.size(); i++)
//					page.getAttachSql().append(" or model.id=").append(accountList.get(i).getDealingUnitId()).append(" and model.disable=true");
//			
//			}
//		};
//		return this.findList(act, sqlCallback);
		
		List<Unit> unitList = new ArrayList<Unit>();
		
		if(this.validateRange(act))
			return unitList;
		
		String sql = "select * from Unit where id=" + accountList.get(0).getDealingUnitId() + " and disable=1 ";
		for (int i = 1; i < accountList.size(); i++)
			sql = sql + "or id=" + accountList.get(i).getDealingUnitId() + " and disable=1";
		
		String msg = "收付款单往来单位";
		
		return this.executeQuery(act, unitList, sql, msg);
	}
	
	public List<Unit> findAllUnit(int shopId) {
		log.debug("finding Unit instance with parentId: " + shopId  + " and disable: True");
		try {
			List<Unit> list = getHibernateTemplate().find(
					"from Unit as model where model.parentId=" 
					+ shopId + " and model.disable=" + true);						
			return list;				
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}	
	
	//************************************************************************
	private List<Unit> findByProperty(String propertyName, Object value) {
	      log.debug("finding Unit instance with property: " + propertyName
	            + ", value: " + value);
	      try {
	         String queryString = "from Unit as model where model." 
	         						+ propertyName + "= ?";
			 return getHibernateTemplate().find(queryString, value);
	      } catch (RuntimeException re) {
	         log.error("find by property name failed", re);
	         throw re;
	      }
	}
	
	private List<Unit> executeQuery(IFieldValidation act, List<Unit> unitList, String sql, String msg) {
		Page page = act.getPage();
		
		//初始化
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;	
        
        //开启连接        
        try {
        	Class.forName(SQLConnection.driverName);
        	conn = DriverManager.getConnection(SQLConnection.lyServicesURL, SQLConnection.lyServicesUserName, SQLConnection.lyServicesPassWord);			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("查询" + msg + "信息，开启连接失败");
			System.out.println(e);
		}				
		
		//查询
		try {
			pstmt=conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			pstmt.setMaxRows((Integer.valueOf(page.getCurStr()) - 1) * page.getSize() + page.getSize());
						
			rs = pstmt.executeQuery(); 
			rs.absolute((Integer.valueOf(page.getCurStr()) - 1) * page.getSize());
						
			Unit unit;
			while(rs.next()) {
				unit = new Unit();
				this.obtainUnit(unit, rs);
				unitList.add(unit);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("查询" + msg + "信息，查询失败");
			System.out.println(e);
		}
		
		//关闭连接
		try {
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("查询" + msg + "信息，关闭连接失败");
		}
		
		return unitList;
	}
	
	private Unit obtainUnit(Unit unit, ResultSet rs) {
		
		try {
			unit.setId(rs.getInt("id"));
			unit.setShortName(rs.getString("shortName"));
			unit.setFullName(rs.getString("fullName"));
			unit.setType(rs.getString("type"));
			unit.setParentId(rs.getInt("parentId"));
			unit.setParentShortName(rs.getString("parentShortName"));
			unit.setDirectorName(rs.getString("directorName"));
			unit.setDirectorTitle(rs.getString("directorTitle"));
			unit.setDirectorPhone(rs.getString("directorPhone"));
			unit.setPhone(rs.getString("phone"));
			unit.setFax(rs.getString("fax"));
			unit.setAddress(rs.getString("address"));
			unit.setCity(rs.getString("city"));
			unit.setProvince(rs.getString("province"));
			unit.setDisable(rs.getBoolean("disable"));
			unit.setRegisterDate(rs.getString("registerDate"));
			unit.setEndDate(rs.getString("endDate"));
			unit.setDatabaseMap(rs.getString("databasemap"));
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("查询往来单位信息，从结果集获得结果失败");
			System.out.println(e);
		}
		
		return unit;
	}
	
	//日志
    private static final Log log = LogFactory.getLog(UnitDAOImpl.class);	
}