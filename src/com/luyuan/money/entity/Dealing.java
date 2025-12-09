package com.luyuan.money.entity;

/**
 * Dealing entity. @author MyEclipse Persistence Tools
 */

public class Dealing implements java.io.Serializable {

	// Fields

	private Long id;
	private String code;
	private String type;
	private Integer dealerId;
	private String dealerShortName;
	private Integer dealingUnitId;
	private String dealingUnitShortName;
	private Boolean isChecked;
	private Boolean isError;
	private Double total;
	private Integer handlerId;
	private String handlerName;
	private Integer creatorId;
	private String creatorName;
	private Integer accountantId;
	private String accountantName;
	private String createDate;
	private String checkDate;
	private String brief;
	private String remark;

	// Constructors

	/** default constructor */
	public Dealing() {
	}

	/** minimal constructor */
	public Dealing(String code, String type, Integer dealerId,
			String dealerShortName, Integer dealingUnitId,
			String dealingUnitShortName, Boolean isChecked, Boolean isError,
			Double total, String handlerName, Integer creatorId,
			String creatorName, String createDate) {
		this.code = code;
		this.type = type;
		this.dealerId = dealerId;
		this.dealerShortName = dealerShortName;
		this.dealingUnitId = dealingUnitId;
		this.dealingUnitShortName = dealingUnitShortName;
		this.isChecked = isChecked;
		this.isError = isError;
		this.total = total;
		this.handlerName = handlerName;
		this.creatorId = creatorId;
		this.creatorName = creatorName;
		this.createDate = createDate;
	}

	/** full constructor */
	public Dealing(String code, String type, Integer dealerId,
			String dealerShortName, Integer dealingUnitId,
			String dealingUnitShortName, Boolean isChecked, Boolean isError,
			Double total, Integer handlerId, String handlerName,
			Integer creatorId, String creatorName, Integer accountantId,
			String accountantName, String createDate, String checkDate,
			String brief, String remark) {
		this.code = code;
		this.type = type;
		this.dealerId = dealerId;
		this.dealerShortName = dealerShortName;
		this.dealingUnitId = dealingUnitId;
		this.dealingUnitShortName = dealingUnitShortName;
		this.isChecked = isChecked;
		this.isError = isError;
		this.total = total;
		this.handlerId = handlerId;
		this.handlerName = handlerName;
		this.creatorId = creatorId;
		this.creatorName = creatorName;
		this.accountantId = accountantId;
		this.accountantName = accountantName;
		this.createDate = createDate;
		this.checkDate = checkDate;
		this.brief = brief;
		this.remark = remark;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getDealerId() {
		return this.dealerId;
	}

	public void setDealerId(Integer dealerId) {
		this.dealerId = dealerId;
	}

	public String getDealerShortName() {
		return this.dealerShortName;
	}

	public void setDealerShortName(String dealerShortName) {
		this.dealerShortName = dealerShortName;
	}

	public Integer getDealingUnitId() {
		return this.dealingUnitId;
	}

	public void setDealingUnitId(Integer dealingUnitId) {
		this.dealingUnitId = dealingUnitId;
	}

	public String getDealingUnitShortName() {
		return this.dealingUnitShortName;
	}

	public void setDealingUnitShortName(String dealingUnitShortName) {
		this.dealingUnitShortName = dealingUnitShortName;
	}

	public Boolean getIsChecked() {
		return this.isChecked;
	}

	public void setIsChecked(Boolean isChecked) {
		this.isChecked = isChecked;
	}

	public Boolean getIsError() {
		return this.isError;
	}

	public void setIsError(Boolean isError) {
		this.isError = isError;
	}

	public Double getTotal() {
		return this.total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Integer getHandlerId() {
		return this.handlerId;
	}

	public void setHandlerId(Integer handlerId) {
		this.handlerId = handlerId;
	}

	public String getHandlerName() {
		return this.handlerName;
	}

	public void setHandlerName(String handlerName) {
		this.handlerName = handlerName;
	}

	public Integer getCreatorId() {
		return this.creatorId;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreatorName() {
		return this.creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public Integer getAccountantId() {
		return this.accountantId;
	}

	public void setAccountantId(Integer accountantId) {
		this.accountantId = accountantId;
	}

	public String getAccountantName() {
		return this.accountantName;
	}

	public void setAccountantName(String accountantName) {
		this.accountantName = accountantName;
	}

	public String getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCheckDate() {
		return this.checkDate;
	}

	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}

	public String getBrief() {
		return this.brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}