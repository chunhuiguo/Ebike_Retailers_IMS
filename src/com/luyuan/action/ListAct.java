package com.luyuan.action;

import com.luyuan.util.Page;

/**
 * 分页列出数据项，以供选择；
 * redirect设置选择后返回的action
 */

public class ListAct extends BaseAct {

	protected Page page = new Page();
	public Page getPage() {
		return page;
	}
	
	/**
	 * 根据此字段重定向到相应action
	 */
	protected String redirect;
	public String getRedirect() {
		return redirect;
	}
	public void setRedirect(String redirect) {
		this.redirect = redirect;
	}
}
