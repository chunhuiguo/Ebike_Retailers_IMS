package com.luyuan.sys.dao.impl;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.luyuan.sys.dao.RoleOperationDAO;
import com.luyuan.sys.entity.RoleOperation;


	/**
	 * 
	 * 最终会被注入的RoleOperationDAO，实现DAO接口逻辑
	 *
	 * @author tom
	 */

public class RoleOperationDAOImpl extends HibernateDaoSupport implements RoleOperationDAO {
		
	public RoleOperation findByLoginName(String userCode){
		return null;
	}
		
	//************************************************************************

		
	private static final Log log = LogFactory.getLog(RoleOperationDAOImpl.class);

}