package com.toprate.mct.bo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.model.BaseFWModelImpl;

@SuppressWarnings("serial")
@Table(name = "sms_box")
@Entity
public class SmsBox extends BaseFWModelImpl {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "title")
	private String title;
	@Column(name = "description")
	private String description;
	@Column(name = "create_by")
	private Long createBy;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	private Date createDate;
	@Column(name = "is_delete")
	private Long isDelete;
	@Column(name = "sell_4rent_id")
	private Long sell4rentId;
	@Column(name = "recipient_del_sms")
	private Long recipientDelSms;
	@Column(name = "sender_del_sms")
	private Long senderDelSms;
	@Column(name = "recipient_id")
	private Long recipientId;
	@Column(name = "recipient_name")
	private String recipientName;

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

	public Long getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Long isDelete) {
		this.isDelete = isDelete;
	}

	public Long getRecipientId() {
		return recipientId;
	}

	public void setRecipientId(Long recipientId) {
		this.recipientId = recipientId;
	}

	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public Long getSell4rentId() {
		return sell4rentId;
	}

	public void setSell4rentId(Long sell4rentId) {
		this.sell4rentId = sell4rentId;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public BaseFWDTOImpl toDTO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		SmsBox that = (SmsBox) o;
		return java.util.Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return java.util.Objects.hash(id);
	}

}
