package com.luyuan.sys.action;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.Response;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.luyuan.deal.entity.Voucher;
import com.luyuan.sys.biz.RoleBiz;
import com.luyuan.sys.biz.UserRoleBiz;
import com.luyuan.sys.biz.UsersBiz;
import com.luyuan.sys.entity.Role;
import com.luyuan.sys.entity.UserRole;
import com.luyuan.sys.entity.UserRoleId;
import com.luyuan.sys.entity.Users;
import com.luyuan.util.TypeMap;


public class RoleAct extends UserManageAct {

	//spring ApplicationContext
	protected ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext( ServletActionContext.getServletContext());
	
	private Users user;
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	
	protected List<Role> roleList = new ArrayList<Role>();	
	public List<Role> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	
	private RoleBiz roleBiz;
	public void setRoleBiz(RoleBiz roleBiz) {
		this.roleBiz = roleBiz;
	}
	
	private UserRoleBiz userRoleBiz;
	public void setUserRoleBiz(UserRoleBiz userRoleBiz) {
		this.userRoleBiz = userRoleBiz;
	}
	
	private List<Integer> selectList;
	public List<Integer> getSelectList() {
		return selectList;
	}
	public void setSelectList(List<Integer> selectList) {
		this.selectList = selectList;
	}
	
	private String submit;
	public void setSubmit(String submit) {
		this.submit = submit;
	}
	
	//提示信息
	private String msg = "";
	public String getMsg() {
		return msg;
	}
	public void setMse(String msg) {
		this.msg = msg;
	}
	
	//userManage 页面入口
	public String roleIn(){
		msg = "";
		UserManageAct roleact = (UserManageAct) ctx.getBean("userManageAct");
		user = roleact.getUser();

		roleList = roleBiz.findByType("经销商操作员");
		return "success";
	}
	
	//提交！
	public String execute() {

		if(submit.equals("保存")) {
			submit = null;
			
			if(selectList != null && selectList.size() != 0) {
				
				try{
					userRoleBiz.deleteByUserCode(user.getUserCode());
				}
				catch(Exception ae){
					msg = "删除原有授权失败！" + ae.getMessage();
					return "success";
				}
				List<UserRole> selRoleList = new ArrayList<UserRole>();	
				for(int i = 0; i < selectList.size(); i++) {
					UserRoleId ur = new UserRoleId();
					ur.setUserCode(user.getUserCode());
					ur.setRoleId(selectList.get(i));
					selRoleList.add(new UserRole(ur));
				}
				
				try{
					userRoleBiz.save(selRoleList);
				}
				catch(Exception be){
					msg = "保存新的授权失败！" + be.getMessage();
					return "success";
				}
				
				msg = "保存授权成功！可点击取消返回首页。";
				return "success";
			}	
			else {
				msg = "您必须至少为用户选择一个岗位！";
				return "success";
			}
		}
		if(submit.equals("取消")) {
			submit = null;
			
			return "cancel";
		}
		return "success";
	}
}
