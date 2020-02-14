package com.toparte.mct.dto;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.google.common.collect.Lists;
import com.toprate.base.utils.CustomJsonDateDeserializerDOng;
import com.toprate.base.utils.CustomJsonDateSerializerDOng;
import com.toprate.mct.bo.Manufacturer;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ManufacturerDTO extends QlanBaseDTO<Manufacturer> {
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
	private String filePath;

	public ManufacturerDTO() {
		
	}

	private List<ModelDTO> listModel= Lists.newArrayList();
	
	public List<ModelDTO> getListModel() {
		return listModel;
	}

	public void setListModel(List<ModelDTO> listModel) {
		this.listModel = listModel;
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

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
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
	public Manufacturer toModel() {
		Manufacturer m = new Manufacturer();
		m.setId(this.id);
		m.setCode(this.code);
		m.setName(this.name);
		m.setDescription(this.description);
		m.setCreatedDate(this.createdDate);
		m.setCreatedBy(this.createdBy);
		m.setUpdatedDate(this.updatedDate);
		m.setUpdateBy(this.updatedBy);
		m.setIsDelete(this.isDelete);
		m.setFilePath(this.filePath);
		return m;
	}
}
