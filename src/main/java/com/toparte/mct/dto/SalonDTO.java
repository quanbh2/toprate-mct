package com.toparte.mct.dto;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.toprate.mct.bo.Salon;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SalonDTO extends QlanBaseDTO<Salon> {
	private Long id;
	private String code;
	private String name;
	private String address;
	private String phone;
	private String email;
	private String description;
	private String content;
	private Long userId;
	private Long isDelete;
	private Long provinceId;
	private String provinceCode;
	private String provinceName;
	private Long status;
	private Long countByProvinceId;
	private Long provinceRegions;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Long isDelete) {
		this.isDelete = isDelete;
	}

	public Long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
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

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}
	
	public Long getCountByProvinceId() {
		return countByProvinceId;
	}

	public void setCountByProvinceId(Long countByProvinceId) {
		this.countByProvinceId = countByProvinceId;
	}
	
	public Long getProvinceRegions() {
		return provinceRegions;
	}

	public void setProvinceRegions(Long provinceRegions) {
		this.provinceRegions = provinceRegions;
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
	public Salon toModel() {
		Salon m = new Salon();
		m.setId(this.id);
		m.setCode(this.code);
		m.setName(this.name);
		m.setDescription(this.description);
		m.setAddress(this.address);
		m.setPhone(this.phone);
		m.setEmail(this.email);
		m.setContent(this.content);
		m.setUserId(this.userId);
		m.setIsDelete(this.isDelete);
		m.setProvinceId(this.provinceId);
		m.setStatus(this.status);
		return m;
	}
}
