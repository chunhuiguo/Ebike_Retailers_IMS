package com.luyuan.sys.entity;

/**
 * RoleOperation entity. @author MyEclipse Persistence Tools
 */

public class RoleOperation implements java.io.Serializable {

	// Fields

	private RoleOperationId id;

	// Constructors

	/** default constructor */
	public RoleOperation() {
	}

	/** full constructor */
	public RoleOperation(RoleOperationId id) {
		this.id = id;
	}

	// Property accessors

	public RoleOperationId getId() {
		return this.id;
	}

	public void setId(RoleOperationId id) {
		this.id = id;
	}

}