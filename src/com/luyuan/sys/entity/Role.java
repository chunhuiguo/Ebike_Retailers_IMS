package com.luyuan.sys.entity;

/**
 * Role entity. @author MyEclipse Persistence Tools
 */

public class Role implements java.io.Serializable {

	// Fields

	private Integer roleId;
	private String roleName;
	private String roleType;
	private String creatorCode;
	private Boolean enabled;
	private String info;

	// Constructors

	/** default constructor */
	public Role() {
	}

	/** minimal constructor */
	public Role(String roleName, String roleType, String creatorCode,
			Boolean enabled) {
		this.roleName = roleName;
		this.roleType = roleType;
		this.creatorCode = creatorCode;
		this.enabled = enabled;
	}

	/** full constructor */
	public Role(String roleName, String roleType, String creatorCode,
			Boolean enabled, String info) {
		this.roleName = roleName;
		this.roleType = roleType;
		this.creatorCode = creatorCode;
		this.enabled = enabled;
		this.info = info;
	}

	// Property accessors

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleType() {
		return this.roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public String getCreatorCode() {
		return this.creatorCode;
	}

	public void setCreatorCode(String creatorCode) {
		this.creatorCode = creatorCode;
	}

	public Boolean getEnabled() {
		return this.enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

}