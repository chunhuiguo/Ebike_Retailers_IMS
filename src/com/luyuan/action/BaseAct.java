/**
 * @(#)BaseAct.java  1.0 10/02/01
 *
 * Copyright 2010 luyuan-ebike, Inc. All rights reserved.
 * 
 */

package com.luyuan.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.luyuan.util.Page;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * action基类,用于实现基于siteMesh的ajax。
 * 提供公共操作所需的方法
 *
 * @author tom
 * 
 */

public abstract class BaseAct extends ActionSupport implements IFieldValidation{
		
	private String ajax;
	public String getAjax() {
		return ajax;
	}
	public void setAjax(String ajax) {
		this.ajax = ajax;
	}
	
	public Page getPage(){
		return null;
	}
	
	public Map<String,Object> getSession(){
		return ActionContext.getContext().getSession();
	}
	
	//spring ApplicationContext
	public ApplicationContext getAppContext(){
		return WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
	}
	
	private String serverDate;
	public String getServerDate() {
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	}
	public void setServerDate(String serverDate) {
		this.serverDate = serverDate;
	}
}
