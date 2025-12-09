package com.luyuan.action;

/**
 * 选择列出的某一项，继续输入
 *
 */

public class OptAct extends BaseAct {


	/**
	 * 大多action会使用select选择其他ListAct列出list()的项，
	 * 选中的条目的序号，从0开始
	 */
	protected Integer select;	
	public void setSelect(Integer select) {
		this.select = select;
	}
}
