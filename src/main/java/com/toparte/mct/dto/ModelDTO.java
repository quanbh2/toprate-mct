package com.toparte.mct.dto;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.toprate.base.utils.CustomJsonDateDeserializerDOng;
import com.toprate.base.utils.CustomJsonDateSerializerDOng;
import com.toprate.mct.bo.Model;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ModelDTO extends QlanBaseDTO<Model> {
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
	private Long manufacturerId;
	private String manufacturerName;
	private String manufacturerCode;
	private String manufacturerFilePath;
	private Long typeMachineId;
	private String typeMachineCode;
	private String typeMachineName;
	private Long typeMachineParentId;

	public Long getTypeMachineParentId() {
		return typeMachineParentId;
	}

	public void setTypeMachineParentId(Long typeMachineParentId) {
		this.typeMachineParentId = typeMachineParentId;
	}

	public Long getTypeMachineId() {
		return typeMachineId;
	}

	public void setTypeMachineId(Long typeMachineId) {
		this.typeMachineId = typeMachineId;
	}

	public String getTypeMachineName() {
		return typeMachineName;
	}

	public void setTypeMachineName(String typeMachineName) {
		this.typeMachineName = typeMachineName;
	}

	public String getTypeMachineCode() {
		return typeMachineCode;
	}

	public void setTypeMachineCode(String typeMachineCode) {
		this.typeMachineCode = typeMachineCode;
	}

	public String getManufacturerCode() {
		return manufacturerCode;
	}

	public void setManufacturerCode(String manufacturerCode) {
		this.manufacturerCode = manufacturerCode;
	}

	public String getManufacturerFilePath() {
		return manufacturerFilePath;
	}

	public void setManufacturerFilePath(String manufacturerFilePath) {
		this.manufacturerFilePath = manufacturerFilePath;
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

	public Long getManufacturerId() {
		return manufacturerId;
	}

	public void setManufacturerId(Long manufacturerId) {
		this.manufacturerId = manufacturerId;
	}

	public String getManufacturerName() {
		return manufacturerName;
	}

	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
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
	public Model toModel() {
		Model m = new Model();
		m.setTypeMachineId(typeMachineId);
		m.setId(this.id);
		m.setCode(this.code);
		m.setName(this.name);
		m.setDescription(this.description);
		m.setCreatedDate(this.createdDate);
		m.setCreatedBy(this.createdBy);
		m.setUpdatedDate(this.updatedDate);
		m.setUpdatedBy(this.updatedBy);
		m.setIsDelete(this.isDelete);
		m.setManufacturerId(this.manufacturerId);
		return m;
	}
}
