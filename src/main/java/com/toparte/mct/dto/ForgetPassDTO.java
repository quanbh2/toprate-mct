package com.toparte.mct.dto;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.toprate.base.utils.CustomJsonDateDeserializerDOng;
import com.toprate.base.utils.CustomJsonDateSerializerDOng;
import com.toprate.mct.bo.ForgetPass;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ForgetPassDTO extends QlanBaseDTO<ForgetPass> {

	private Long id;
	private String userName;
	private String fullName;
	private String content;
	private String phone;
	private String emailAddress;
	private String address;
	private Long status;
	private Long isDelete;

	@JsonSerialize(using = CustomJsonDateSerializerDOng.class)
	@JsonDeserialize(using = CustomJsonDateDeserializerDOng.class)
	private Date createdDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Long getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Long isDelete) {
		this.isDelete = isDelete;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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
	public ForgetPass toModel() {
		ForgetPass m = new ForgetPass();
		m.setId(this.id);
		m.setFullName(this.fullName);
		m.setUserName(this.userName);
		m.setCreatedDate(this.createdDate);
		m.setIsDelete(this.isDelete);
		m.setPhone(this.phone);
		m.setAddress(this.address);
		m.setEmailAddress(this.emailAddress);
		m.setStatus(this.status);
		m.setContent(this.content);
		return m;
	}
}
