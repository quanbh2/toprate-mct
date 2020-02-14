package com.toparte.mct.dto;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.toprate.base.utils.CustomJsonDateDeserializerDOng;
import com.toprate.base.utils.CustomJsonDateSerializerDOng;
import com.toprate.mct.bo.SmsBox;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SmsBoxDTO extends QlanBaseDTO<SmsBox> {
	private Long id;
	private String title;
	private String description;
	private Long createBy;

	@JsonSerialize(using = CustomJsonDateSerializerDOng.class)
	@JsonDeserialize(using = CustomJsonDateDeserializerDOng.class)
	private Date createDate;

	private Long recipientId;
	private String recipientName;
	private String codeSell4rent;
	private Long isDelete;
	private Long sell4rentId;
	private Long sell4rentType;
	private String userFullName;
	private Long recipientDelSms;
	private Long senderDelSms;

	public Long getRecipientDelSms() {
		return recipientDelSms;
	}

	public void setRecipientDelSms(Long recipientDelSms) {
		this.recipientDelSms = recipientDelSms;
	}

	public Long getSenderDelSms() {
		return senderDelSms;
	}

	public void setSenderDelSms(Long senderDelSms) {
		this.senderDelSms = senderDelSms;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSell4rentType() {
		return sell4rentType;
	}

	public void setSell4rentType(Long sell4rentType) {
		this.sell4rentType = sell4rentType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getRecipientId() {
		return recipientId;
	}

	public void setRecipientId(Long recipientId) {
		this.recipientId = recipientId;
	}

	public Long getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Long isDelete) {
		this.isDelete = isDelete;
	}

	public Long getSell4rentId() {
		return sell4rentId;
	}

	public void setSell4rentId(Long sell4rentId) {
		this.sell4rentId = sell4rentId;
	}

	public String getUserFullName() {
		return userFullName;
	}

	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}

	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public String getCodeSell4rent() {
		return codeSell4rent;
	}

	public void setCodeSell4rent(String codeSell4rent) {
		this.codeSell4rent = codeSell4rent;
	}

	@Override
	public String catchName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getFWModelId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SmsBox toModel() {
		SmsBox m = new SmsBox();
		m.setId(this.id);
		m.setTitle(this.title);
		m.setDescription(this.description);
		m.setCreateDate(this.createDate);
		m.setCreateBy(this.createBy);
		m.setIsDelete(this.isDelete);
		m.setRecipientId(this.recipientId);
		m.setRecipientName(this.recipientName);
		m.setSell4rentId(this.sell4rentId);
		m.setRecipientDelSms(this.recipientDelSms);
		m.setSenderDelSms(this.senderDelSms);
		return m;
	}
}
