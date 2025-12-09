package com.luyuan.biz;

import java.util.Map;

import com.luyuan.util.Page;
import com.opensymphony.xwork2.ActionContext;


public abstract class ServiceBiz {
	
//	public List getList(Page page){
//		this.setListParam(page);
//		return paginate.getList(page);
//	}
//	
//	public void validatePage(BaseAct act,Page page){
//		this.setListParam(page);
//		try {
//			paginate.setCount(page);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		if(page.getCurPageStr()==null) return;
//		if(!page.getCurPageStr().matches("\\d+")){
//			act.addFieldError("page.curPageStr", page.getCurPageStr()+"不是正整数");return;
//		}
//		if( page.getPageCount() != 0 && Integer.valueOf(page.getCurPageStr()) > page.getPageCount()){
//			act.addFieldError("page.curPageStr", page.getCurPageStr()+"大于总页数");return;
//		}
//		page.setCurrentPage(Integer.valueOf(page.getCurPageStr()));
//	}
	
	protected abstract void setListParam(Page page);
	
//	protected Paginate paginate;
//	public void setPaginate(Paginate paginate) {
//		this.paginate = paginate;
//	}
	
	protected Map<String,Object> session = ActionContext.getContext().getSession();
}
