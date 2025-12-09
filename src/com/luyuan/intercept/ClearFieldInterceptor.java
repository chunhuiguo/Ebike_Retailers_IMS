/**
 * @(#)BaseAct.java  1.0 10/04/01
 *
 * Copyright 2010 luyuan-ebike, Inc. All rights reserved.
 * 
 */
package com.luyuan.intercept;

import com.luyuan.action.BaseAct;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 
 * 清除Action的错误信息--解决因Spring管理Action(session bean)导致的错误域保留问题。
 * 把ajax在action调用之后清空，以防用户浏览器javascript异常终止或出现ajaxError后的后续程序能正常运行
 *
 * 
 */

public class ClearFieldInterceptor extends AbstractInterceptor{

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {

		BaseAct act = (BaseAct)invocation.getAction(); 
		act.clearErrorsAndMessages();
		act.clearActionErrors();
		
		String result = invocation.invoke(); 
		
		if( act.getAjax() != null)
			act.setAjax(null);
		
		return result;
	}

}
