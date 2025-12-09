package com.luyuan.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
/*
 * ListAct持有Page对象，并作为分页action父类
 * Page的getNumbers返回页面动态链接页
 */
public class Page implements java.io.Serializable {

	private int size = 20;			//每页记录数
	private int tail = 1;			//总页数
	private String curStr = "1";	//当前页码--字符型用以消除javascript依赖与类型转换ognl错误
	private int cur = 1;			//合法的curStr转化的数字
	
	private String listAct = "";	//链接至action, 由Action设置,意义：对listAct返回的结果分页

	/**************************************************************************************************/
	//查询条件与上次查询条件的副本
	private List<String> propsValueMem = new ArrayList<String>();		//记忆查询值串，判断是否为新查询

	private List<String> propsNameList = new ArrayList<String>();		//实体对象属性名
	private List<String> propsValueList = new ArrayList<String>();		//属性值
	
	private StringBuffer attachSqlMem = new StringBuffer();				//记忆附加sql
	private StringBuffer attachSql = new StringBuffer();				//附加sql
	
	/**************************************************************************************************/
	//页面动态链接页
	private static final String ELLIPSIS = "<span class='ellipsis'>...</span>";
	private static final String PREV = "<<上一页";
	private static final String NEXT = "下一页>>";
	private static final String NULL = "<span class='errors'>无记录！</span>";
	
	public String getNumbers(){
		String shead="";
		String stail="";
		if (tail == 0) return NULL;
		if (tail < 7) {
			for (int i = 1; i <= tail; i++) {
				if (cur == i) shead += p(cur);
				else shead += f(i);
			}
			if (tail == 1) return p(PREV)+shead+p(NEXT);
			if (cur == 1) return p(PREV)+shead+f(NEXT);
			if (cur == tail) return f(PREV)+shead+p(NEXT);
			return f(PREV)+shead+f(NEXT);
		}
		
		if (cur == 1) 	return p(PREV)+p(cur)+f(2)+f(3)+ELLIPSIS+f(tail-1)+f(tail)+f(NEXT);
		if (cur == tail) return f(PREV)+f(1)+f(2)+ELLIPSIS+f(tail-2)+f(tail-1)+p(cur)+p(NEXT);
		
		if (cur > 5)
			shead = f(1)+f(2)+ ELLIPSIS +f(cur-1);
		else{
			for (int i = 1; i <= cur-1; i++) {
				shead += f(i);
			}
		}
		
		if (tail-cur > 4)
			stail = f(cur+1) + ELLIPSIS +f(tail-1)+f(tail);
		else{
			for (int i = cur+1; i <= tail; i++) {
				stail += f(i);
			}
		}
		return f(PREV)+shead+p(cur)+stail+f(NEXT);	
	}
	
	private String f(int num){
		return "<a href='" + fixActByArg(listAct) + 
		"page.curStr=" + num + "'>" + num +"</a>";
	}
	
	private String f(final String prevORnext){
		int num = cur + 1;
		if (prevORnext.equals(PREV))
			num = cur - 1;
		return "<a href='" + fixActByArg(listAct) + 
		"page.curStr=" + num + "' class='prevORnext'>" + prevORnext +"</a>";
	}
	
	private String p(int cur){
		return "<span class='curpage'>"+cur+"</span>";
	}
	
	private String p(final String prevORnext){
		return "<span class='prevORnext'>"+prevORnext+"</span>";
	}
	
	
	/**************************************************************************************************/
	//getter setter
	public int getSize() {	//以备改变每页记录数,如弹出的小窗口
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
	public int getCur() {
		return cur;
	}
	public void setCur(int cur) {	//校验合法后curStr转换为cur，在校验代码中调用该方法
		this.cur = cur;
	}

	public String getCurStr() {
		return curStr;
	}
	public void setCurStr(String curStr) {
		this.curStr = curStr;
	}

	/**************************************************************************************************/
	//确定链接后页码，判断当前页码是否需要改变。对一次新的查询条件与首次查询，当前页码为1。
	public void setPropsValueMem(List<String> propsValueList) {	
		if (propsValueMem.size() != propsValueList.size()){ 	//两种情况
			if (!curStr.equals(cur+"")) 	//跳转
				return;
			curStr = "1";					//新查询条件
			propsValueMem.clear();
			CollectionUtils.addAll(propsValueMem,new Object[propsValueList.size()]);
			Collections.copy(propsValueMem, propsValueList);
			return;
		}	
		for (int i = 0; i < propsValueMem.size(); i++) {		//非首次
			if (!propsValueMem.get(i).equals(propsValueList.get(i))) {
				curStr = "1";
				propsValueMem.clear();
				CollectionUtils.addAll(propsValueMem,new Object[propsValueList.size()]);
				Collections.copy(propsValueMem, propsValueList);
				return;
			}
		}
	}
	

	public void setAttachSqlMem(StringBuffer attachSql) {
		if ( !attachSqlMem.toString().equals(attachSql.toString())){ 	//首次提交--两种情况
//			if (!curStr.equals(cur+"")) 	//跳转
//				return;
			curStr = "1";
			attachSqlMem.delete(0, attachSqlMem.length());
			attachSqlMem.append(attachSql);
		}
		
	}

	public List<String> getPropsNameList() {
		return propsNameList;
	}

	public void setPropsNameList(List<String> propsNameList) {
		this.propsNameList = propsNameList;
	}

	public List<String> getPropsValueList() {
		return propsValueList;
	}

	public void setPropsValueList(List<String> propsValueList) {
		this.propsValueList = propsValueList;
	}

	/**************************************************************************************************/
	public StringBuffer getAttachSql() {
		return attachSql;
	}

	public void setAttachSql(StringBuffer attachSql) {
		this.attachSql = attachSql;
	}

	public void setTail(int count) {//count为总记录数
		tail = count/size;
		if (count % size != 0) {
			tail++;
		}
	}

	public int getTail() {
		return tail;
	}

	public void setListAct(String listAct) {
		this.listAct = listAct;
	}

	public String getListAct() {
		return listAct;
	}
	
	/**************************************************************************************************/
	private String fixActByArg(String act){
		String mark = "?";
		if(act.contains(mark))
			return act;
		return act+"?";
	}
	
}

