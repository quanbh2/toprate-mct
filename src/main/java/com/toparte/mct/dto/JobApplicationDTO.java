package com.toparte.mct.dto;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.toprate.base.utils.CustomJsonDateDeserializerDOng;
import com.toprate.base.utils.CustomJsonDateSerializerDOng;
import com.toprate.mct.bo.JobApplication;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown = true)
public class JobApplicationDTO extends QlanBaseDTO<JobApplication> {
	private Long id;
	private String code;
	private String name;
	private String description;

	@JsonSerialize(using = CustomJsonDateSerializerDOng.class)
	@JsonDeserialize(using = CustomJsonDateDeserializerDOng.class)
	private Date createdDate;
	private Long createdBy;

	@JsonSerialize(using = CustomJsonDateSerializerDOng.class)
	@JsonDeserialize(using = CustomJsonDateDeserializerDOng.class)
	private Date reupDate;

	@JsonSerialize(using = CustomJsonDateSerializerDOng.class)
	@JsonDeserialize(using = CustomJsonDateDeserializerDOng.class)
	private Date updatedDate;
	private Long updatedBy;
	private Long isDelete;
	private String phone;
	private Long provinceId;
	private String address;
	private Long typeMachineId;
	private String birthday;
	private String experience;
	private String txtSearch;
	private String provinceName;
	private String provinceCode;
	private String typeMachineName;
	private Long status;
	private Long countReup;
	private Long countReupDay;
	private Long checkReup;
	@JsonSerialize(using = CustomJsonDateSerializerDOng.class)
	@JsonDeserialize(using = CustomJsonDateDeserializerDOng.class)
	private Date fromDate;

	@JsonSerialize(using = CustomJsonDateSerializerDOng.class)
	@JsonDeserialize(using = CustomJsonDateDeserializerDOng.class)
	private Date toDate;
	private Long countView;
	
	public Long getCountView() {
		return countView;
	}

	public void setCountView(Long countView) {
		this.countView = countView;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Long getCheckReup() {
		return checkReup;
	}

	public void setCheckReup(Long checkReup) {
		this.checkReup = checkReup;
	}

	public Long getCountReup() {
		return countReup;
	}

	public void setCountReup(Long countReup) {
		this.countReup = countReup;
	}

	public Long getCountReupDay() {
		return countReupDay;
	}

	public void setCountReupDay(Long countReupDay) {
		this.countReupDay = countReupDay;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getTypeMachineName() {
		return typeMachineName;
	}

	public void setTypeMachineName(String typeMachineName) {
		this.typeMachineName = typeMachineName;
	}

	public String getTxtSearch() {
		return txtSearch;
	}

	public void setTxtSearch(String txtSearch) {
		this.txtSearch = txtSearch;
	}

	public Long getTypeMachineId() {
		return typeMachineId;
	}

	public void setTypeMachineId(Long typeMachineId) {
		this.typeMachineId = typeMachineId;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Long getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Long isDelete) {
		this.isDelete = isDelete;
	}

	public Date getReupDate() {
		return reupDate;
	}

	public void setReupDate(Date reupDate) {
		this.reupDate = reupDate;
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
	public JobApplication toModel() {
		JobApplication j = new JobApplication();
		j.setId(this.id);
		j.setCode(this.code);
		j.setName(this.name);
		j.setDescription(this.description);
		j.setCreatedDate(this.createdDate);
		j.setCreatedBy(this.createdBy);
		j.setUpdatedDate(this.updatedDate);
		j.setUpdateBy(this.updatedBy);
		j.setReupDate(this.reupDate);
		j.setIsDelete(this.isDelete);
		j.setProvinceId(this.provinceId);
		j.setPhone(this.phone);
		j.setAddress(this.address);
		j.setTypeMachineId(this.typeMachineId);
		j.setBirthday(this.birthday);
		j.setExperience(this.experience);
		j.setStatus(this.status);
		j.setCountReup(this.countReup);
		j.setCountReupDay(this.countReupDay);
		j.setCountView(this.countView);
		return j;
	}
}
