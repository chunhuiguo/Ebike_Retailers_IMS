package com.luyuan.util;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.luyuan.info.entity.Employee;
import com.luyuan.sys.action.ShipOrderAct;
import com.luyuan.sys.entity.ShipOrder;

/**
 * 由于使用select后需要置null，此外有时select==null也作为返回标志
 * 下面的方法调用前，均应做select == null的判断,保证非null进入
 */
public final class SelectHelper {
	
	public static ShipOrder selectShipOrder(Integer select){
		ShipOrderAct shipOrderAct = (ShipOrderAct)ctx.getBean("shipOrderAct");
		List<ShipOrder> list = shipOrderAct.getShipOrderList();
		if( select >= 0 && select < list.size())		
			return list.get(select);
		return null;
	}	
	
	/**************************************************************************************************/
	
	//spring ApplicationContext
	private static ApplicationContext  ctx = 
		WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
}