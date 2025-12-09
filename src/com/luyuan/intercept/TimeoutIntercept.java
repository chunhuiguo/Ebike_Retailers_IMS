package com.luyuan.intercept;

import com.luyuan.action.BaseAct;
import com.luyuan.home.action.LoginAct;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/*
 * 会话超时，提示错误信息，返回login页面
 * 对非法访问也做无sessio信息的判断
 */

public class TimeoutIntercept extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Integer userId = (Integer)ActionContext.getContext().getSession().get("userId");
		
		BaseAct action = (BaseAct) invocation.getAction();   
        if (action instanceof LoginAct)
            return invocation.invoke();   

		if( userId == null ) 
			return "timeout";
		return invocation.invoke();
	}

}
