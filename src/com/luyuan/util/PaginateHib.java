package com.luyuan.util;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.luyuan.action.IFieldValidation;
/**
 * 分页持久层父类，代替HibernateDaoSupport。并校验跳转字符串--与DB访问相关
 * 如果只有一个库，可以独立为一个spring bean，只处理分页
 * refactor:使用新的IFieldValidation：
 * 1.让PaginateHib与struts2的Action解耦，即PaginateHib与struts2无关
 * 2.获得page
 */
public class PaginateHib extends HibernateDaoSupport {

	private StringBuffer sql = new StringBuffer();

	public List findList(final IFieldValidation act){
		return findList(act,null);
	}

	public List findList(final IFieldValidation act, final SqlCallback callback) {
		
		sql.delete(0, sql.length());
		
		if (validateNumber(act)) return null;
		final Page page = act.getPage();
		
		sql.append("from ").append(className).append(" as model where 1 = 1 ");
		
		HibernateCallback hibernateCallback = new HibernateCallback() {
			public Object doInHibernate(Session session) {
				
				List<String> list =  page.getPropsNameList();
				if (list != null && list.size() > 0){
					for (String propertyName : list) {
						sql.append("and model.").append(propertyName).append(" like ? ");
					}
				}				
				
				StringBuffer attachSql = page.getAttachSql();
				if(callback != null){
					attachSql.delete(0, attachSql.length());
					callback.setPageSql(page);		//change page.attachSql;
					sql.append(attachSql);						
					page.setAttachSqlMem(attachSql);
				}
				
				Query query = session.createQuery(sql.toString());
			
				list = page.getPropsValueList();
				int i = 0;
				if (list != null && list.size() > 0){
					System.out.println("****************************************");
					for (String value : list) {
						System.out.println("+++++++++");
						System.out.println(page.getPropsNameList().get(i));
						System.out.println(page.getPropsValueList().get(i));						
						query.setParameter( i++, "%"+value+"%");
					}
				}
				System.out.println("+++++++++");
				System.out.println(query.toString()); //调试
				System.out.println("****************************************");
				ScrollableResults scrollResult = query.scroll();
				scrollResult.last();
				page.setTail(scrollResult.getRowNumber()+1);
				
				page.setPropsValueMem(list);	//记忆查询值串，用于判断curStr，放于校验之前
				
				//by gch
				//session.close(); //关闭session
				
				if( validateRange(act)){
					sql.delete(0, sql.length());
					return null;
				}
				
				query.setFirstResult( (Integer.valueOf(page.getCurStr()) - 1) * page.getSize() );
				query.setMaxResults( page.getSize() );
				sql.delete(0, sql.length());
				return query.list();
			}
		};
		return this.getHibernateTemplate().executeFind(hibernateCallback);
	} 
	
	private boolean validateNumber(IFieldValidation act) {
		Page page = act.getPage();
		if (page.getCurStr() == null) 
			return true;
		if (page.getCurStr().length() > 10){
			act.addFieldError("page.curStr", page.getCurStr()+" 串太长，超出范围"); 
			return true;
		}
		if (!page.getCurStr().matches("\\d+") || Integer.valueOf(page.getCurStr()) == 0 ){ 
			act.addFieldError("page.curStr", page.getCurStr()+" 不是正整数"); 
			return true;
		}
		return false;
	}
	
	protected boolean validateRange(IFieldValidation act) {
		Page page = act.getPage();
		if (Integer.valueOf(page.getCurStr()) > page.getTail()){
			act.addFieldError("page.curStr", page.getCurStr()+" 大于总页数"); 
			return true;
		}
		page.setCur(Integer.valueOf(page.getCurStr()));		//设置当前页码cur
		return false;
	}
	
	//sping setting
	protected String className;
	
	public void setClassName(String className) {
		this.className = className;
	}
}
