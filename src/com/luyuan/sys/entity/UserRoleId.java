package com.luyuan.sys.entity;

/**
 * UserRoleId entity. @author MyEclipse Persistence Tools
 */

public class UserRoleId implements java.io.Serializable {

	// Fields

	private String userCode;
	private Integer roleId;

	// Constructors

	/** default constructor */
	public UserRoleId() {
	}

	/** full constructor */
	public UserRoleId(String userCode, Integer roleId) {
		this.userCode = userCode;
		this.roleId = roleId;
	}

	// Property accessors

	public String getUserCode() {
		return this.userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof UserRoleId))
			return false;
		UserRoleId castOther = (UserRoleId) other;

		return ((this.getUserCode() == castOther.getUserCode()) || (this
				.getUserCode() != null
				&& castOther.getUserCode() != null && this.getUserCode()
				.equals(castOther.getUserCode())))
				&& ((this.getRoleId() == castOther.getRoleId()) || (this
						.getRoleId() != null
						&& castOther.getRoleId() != null && this.getRoleId()
						.equals(castOther.getRoleId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUserCode() == null ? 0 : this.getUserCode().hashCode());
		result = 37 * result
				+ (getRoleId() == null ? 0 : this.getRoleId().hashCode());
		return result;
	}

}