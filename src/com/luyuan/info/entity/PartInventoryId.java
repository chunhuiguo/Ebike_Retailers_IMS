package com.luyuan.info.entity;

/**
 * PartInventoryId entity. @author MyEclipse Persistence Tools
 */

public class PartInventoryId implements java.io.Serializable {

	// Fields

	private Long id;
	

	// Constructors

	/** default constructor */
	public PartInventoryId() {
	}

	
	/** full constructor */
	public PartInventoryId(Long id) {
		this.id = id;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

}