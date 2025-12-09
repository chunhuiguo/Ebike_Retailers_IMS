package com.luyuan.info.entity;

/**
 * ProductInventoryId entity. @author MyEclipse Persistence Tools
 */

public class ProductInventoryId implements java.io.Serializable {

	// Fields

	private Long id;

	// Constructors

	/** default constructor */
	public ProductInventoryId() {
	}

	/** full constructor */
	public ProductInventoryId(Long id) {
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