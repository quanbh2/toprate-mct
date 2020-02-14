package com.toparte.mct.dto;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.toprate.base.utils.CustomJsonDateDeserializerDOng;
import com.toprate.base.utils.CustomJsonDateSerializerDOng;
import com.toprate.mct.bo.Province;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProvinceDTO extends QlanBaseDTO<Province> {
	private Long id;
	private String name;
	private String code;
	private String description;
	@JsonSerialize(using = CustomJsonDateSerializerDOng.class)
	@JsonDeserialize(using = CustomJsonDateDeserializerDOng.class)
	private Date createdDate;
	private Long createdBy;
	
	@JsonSerialize(using = CustomJsonDateSerializerDOng.class)
	@JsonDeserialize(using = CustomJsonDateDeserializerDOng.class)
	private Date updatedDate;
	private Long updatedBy;
	private Long isDelete;
	private String regions;

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public String getRegions() {
		return regions;
	}

	public void setRegions(String regions) {
		this.regions = regions;
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
	public Province toModel() {
		Province p = new Province();
		p.setId(this.id);
		p.setCode(this.code);
		p.setName(this.name);
		p.setDescription(this.description);
		p.setCreatedDate(this.createdDate);
		p.setCreatedBy(this.createdBy);
		p.setUpdatedDate(this.updatedDate);
		p.setUpdateBy(this.updatedBy);
		p.setIsDelete(this.isDelete);
		p.setRegions(this.regions);
		return p;
	}

}
