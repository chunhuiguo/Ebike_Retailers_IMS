package com.luyuan.sys.entity;

/**
 * Operation entity. @author MyEclipse Persistence Tools
 */

public class Operation implements java.io.Serializable {

	// Fields

	private String opCode;
	private String opNo;
	private String opName;
	private Boolean endFlag;
	private String url;

	// Constructors

	/** default constructor */
	public Operation() {
	}

	/** minimal constructor */
	public Operation(String opNo, String opName, Boolean endFlag) {
		this.opNo = opNo;
		this.opName = opName;
		this.endFlag = endFlag;
	}

	/** full constructor */
	public Operation(String opNo, String opName, Boolean endFlag, String url) {
		this.opNo = opNo;
		this.opName = opName;
		this.endFlag = endFlag;
		this.url = url;
	}

	// Property accessors

	public String getOpCode() {
		return this.opCode;
	}

	public void setOpCode(String opCode) {
		this.opCode = opCode;
	}

	public String getOpNo() {
		return this.opNo;
	}

	public void setOpNo(String opNo) {
		this.opNo = opNo;
	}

	public String getOpName() {
		return this.opName;
	}

	public void setOpName(String opName) {
		this.opName = opName;
	}

	public Boolean getEndFlag() {
		return this.endFlag;
	}

	public void setEndFlag(Boolean endFlag) {
		this.endFlag = endFlag;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}