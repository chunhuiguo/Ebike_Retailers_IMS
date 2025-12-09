package com.luyuan.info.action;

import java.util.List;
import java.util.Map;

import com.luyuan.action.BaseAct;
import com.luyuan.info.biz.SubAccountTypeBiz;
import com.luyuan.info.entity.SubAccountType;
import com.opensymphony.xwork2.ActionContext;

public class SubAccountTypeAct extends BaseAct {
	public String subAccountTypeList(){
		
		if(subAccountTypeList!=null){
			subAccountTypeList.clear();
		}
		
		if(submit==null){		
			subAccountTypeList = subAccountTypeBiz.findAllType((Integer) this.getSession().get("parentId"));
			return "success";
		}
		if(submit.equals("删除")){
			submit=null;
			for (int i = 0; i < selectList.size(); i++) {
				subAccountTypeBiz.delete(selectList.get(i));
			}
			return "delete";
		}
		if(submit.equals("添加类型")){
			submit=null;
			
			return "add";
		}
		if(submit.equals("返回")){
			submit=null;
			return "back";
		}
		return "success";
	}
	
	public String subAccountTypeAdd() {
		if (submit == null) {
			subAccountTypeList = subAccountTypeBiz.findAllType((Integer) this.getSession().get("parentId"));
			return "success";
		}
		if (submit.equals("保存")) {
			submit = null;
			subAccountType.setDealerId((Integer) this.getSession().get("parentId"));

			if (this.validation(subAccountType)) {
				if (subAccountTypeBiz.validation(this, subAccountType,(Integer) this.getSession().get("parentId"))) {
					subAccountTypeBiz.saveSubAccountType(subAccountType);
					return "save";
				}
			}else{
				return "success";
			}	
		}
		return "success";
	}
	
	//内部校验方法
	private boolean validation(SubAccountType subAccountType){
		boolean isError = false;
		
		if (subAccountType.getName().equals("")) {
			this.addFieldError("subAccountType.name", "类型名称不能为空");
			isError = true;
		}

		if (isError)
			return false;
		else
			return true;
	}
	
	// page act里的数据page用要get	
	private String submit;
	public void setSubmit(String submit) {
		this.submit = submit;
	}
	
	
	private List<SubAccountType> subAccountTypeList;
	public List<SubAccountType> getSubAccountTypeList() {
		return subAccountTypeList;
	}
	
	private SubAccountType subAccountType;	
	public void setSubAccountType(SubAccountType subAccountType) {
		this.subAccountType = subAccountType;
	}
	
	
	private List<Integer> selectList;
	public void setSelectList(List<Integer> selectList) {
		this.selectList = selectList;
	}
	//biz
	private SubAccountTypeBiz subAccountTypeBiz;
	public void setSubAccountTypeBiz(SubAccountTypeBiz subAccountTypeBiz) {
		this.subAccountTypeBiz = subAccountTypeBiz;
	}
}
