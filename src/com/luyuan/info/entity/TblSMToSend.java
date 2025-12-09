package com.luyuan.info.entity;

import java.sql.Timestamp;

/**
 * TblSMToSend entity. @author MyEclipse Persistence Tools
 */

public class TblSMToSend implements java.io.Serializable {

	// Fields

	private Integer smId;
	private Timestamp subTime;
	private String orgAddr;
	private String destAddr;
	private String smContent;
	private Timestamp sendTime;
	private Integer needStateReport;
	private String serviceId;
	private String feeType;
	private String feeCode;
	private String msgId;
	private Integer tryTimes;
	private Integer reserve1;
	private String reserve2;
	private String creatorId;
	private Short smtype;
	private String messageId;
	private Short destAddrType;

	// Constructors

	/** default constructor */
	public TblSMToSend() {
	}

	/** minimal constructor */
	public TblSMToSend(Timestamp subTime, String orgAddr, String destAddr,
			String smContent, Timestamp sendTime, Integer tryTimes,
			String creatorId, Short smtype, String messageId, Short destAddrType) {
		this.subTime = subTime;
		this.orgAddr = orgAddr;
		this.destAddr = destAddr;
		this.smContent = smContent;
		this.sendTime = sendTime;
		this.tryTimes = tryTimes;
		this.creatorId = creatorId;
		this.smtype = smtype;
		this.messageId = messageId;
		this.destAddrType = destAddrType;
	}

	/** full constructor */
	public TblSMToSend(Timestamp subTime, String orgAddr, String destAddr,
			String smContent, Timestamp sendTime, Integer needStateReport,
			String serviceId, String feeType, String feeCode, String msgId,
			Integer tryTimes, Integer reserve1, String reserve2,
			String creatorId, Short smtype, String messageId, Short destAddrType) {
		this.subTime = subTime;
		this.orgAddr = orgAddr;
		this.destAddr = destAddr;
		this.smContent = smContent;
		this.sendTime = sendTime;
		this.needStateReport = needStateReport;
		this.serviceId = serviceId;
		this.feeType = feeType;
		this.feeCode = feeCode;
		this.msgId = msgId;
		this.tryTimes = tryTimes;
		this.reserve1 = reserve1;
		this.reserve2 = reserve2;
		this.creatorId = creatorId;
		this.smtype = smtype;
		this.messageId = messageId;
		this.destAddrType = destAddrType;
	}

	// Property accessors

	public Integer getSmId() {
		return this.smId;
	}

	public void setSmId(Integer smId) {
		this.smId = smId;
	}

	public Timestamp getSubTime() {
		return this.subTime;
	}

	public void setSubTime(Timestamp subTime) {
		this.subTime = subTime;
	}

	public String getOrgAddr() {
		return this.orgAddr;
	}

	public void setOrgAddr(String orgAddr) {
		this.orgAddr = orgAddr;
	}

	public String getDestAddr() {
		return this.destAddr;
	}

	public void setDestAddr(String destAddr) {
		this.destAddr = destAddr;
	}

	public String getSmContent() {
		return this.smContent;
	}

	public void setSmContent(String smContent) {
		this.smContent = smContent;
	}

	public Timestamp getSendTime() {
		return this.sendTime;
	}

	public void setSendTime(Timestamp sendTime) {
		this.sendTime = sendTime;
	}

	public Integer getNeedStateReport() {
		return this.needStateReport;
	}

	public void setNeedStateReport(Integer needStateReport) {
		this.needStateReport = needStateReport;
	}

	public String getServiceId() {
		return this.serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getFeeType() {
		return this.feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public String getFeeCode() {
		return this.feeCode;
	}

	public void setFeeCode(String feeCode) {
		this.feeCode = feeCode;
	}

	public String getMsgId() {
		return this.msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public Integer getTryTimes() {
		return this.tryTimes;
	}

	public void setTryTimes(Integer tryTimes) {
		this.tryTimes = tryTimes;
	}

	public Integer getReserve1() {
		return this.reserve1;
	}

	public void setReserve1(Integer reserve1) {
		this.reserve1 = reserve1;
	}

	public String getReserve2() {
		return this.reserve2;
	}

	public void setReserve2(String reserve2) {
		this.reserve2 = reserve2;
	}

	public String getCreatorId() {
		return this.creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public Short getSmtype() {
		return this.smtype;
	}

	public void setSmtype(Short smtype) {
		this.smtype = smtype;
	}

	public String getMessageId() {
		return this.messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public Short getDestAddrType() {
		return this.destAddrType;
	}

	public void setDestAddrType(Short destAddrType) {
		this.destAddrType = destAddrType;
	}

}