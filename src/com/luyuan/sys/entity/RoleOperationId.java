package com.luyuan.sys.entity;

/**
 * RoleOperationId entity. @author MyEclipse Persistence Tools
 */

public class RoleOperationId implements java.io.Serializable {

	// Fields

	private Integer roleId;
	private String opCode;

	// Constructors

	/** default constructor */
	public RoleOperationId() {
	}

	/** full constructor */
	public RoleOperationId(Integer roleId, String opCode) {
		this.roleId = roleId;
		this.opCode = opCode;
	}

	// Property accessors

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getOpCode() {
		return this.opCode;
	}

	public void setOpCode(String opCode) {
		this.opCode = opCode;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof RoleOperationId))
			return false;
		RoleOperationId castOther = (RoleOperationId) other;

		return ((this.getRoleId() == castOther.getRoleId()) || (this
				.getRoleId() != null
				&& castOther.getRoleId() != null && this.getRoleId().equals(
				castOther.getRoleId())))
				&& ((this.getOpCode() == castOther.getOpCode()) || (this
						.getOpCode() != null
						&& castOther.getOpCode() != null && this.getOpCode()
						.equals(castOther.getOpCode())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getRoleId() == null ? 0 : this.getRoleId().hashCode());
		result = 37 * result
				+ (getOpCode() == null ? 0 : this.getOpCode().hashCode());
		return result;
	}

}