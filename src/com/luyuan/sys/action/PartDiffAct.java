package com.luyuan.sys.action;

import java.util.List;

import com.luyuan.action.ListAct;
import com.luyuan.sys.biz.PartDiffBiz;
import com.luyuan.sys.entity.PartDiff;

public class PartDiffAct extends ListAct {
	
	public String execute(){
		page.setListAct("partDiff.html");
		if(submit == null) {			
			if(selectList != null)
				selectList.clear();			
			partDiffList = partDiffBiz.findAll(this);
			return "success";
		}
		if(submit.equals("筛选")) {
			submit = null;
			if(selectList != null)
				selectList.clear();			
			partDiffList = partDiffBiz.findAll(this);
			return "success";
		}
		if(submit.equals("添加")) {
			submit = null;			
			return "partAdd";
		}
		return "success";
	}
	
	//pageList
	private List<PartDiff> partDiffList;
	public List<PartDiff> getPartDiffList() {
		return partDiffList;
	}	
	
	private List<Integer> selectList;
	public List<Integer> getSelectList() {
		return selectList;
	}
	public void setSelectList(List<Integer> selectList) {
		this.selectList = selectList;
	}

	//submit
	private String submit;
	public void setSubmit(String submit) {
		this.submit = submit;
	}

	//biz
	private PartDiffBiz partDiffBiz;
	public void setPartDiffBiz(PartDiffBiz partDiffBiz) {
		this.partDiffBiz = partDiffBiz;
	}
}
