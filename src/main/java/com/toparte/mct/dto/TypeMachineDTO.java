package com.toparte.mct.dto;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.google.common.collect.Lists;
import com.toprate.mct.bo.TypeMachine;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TypeMachineDTO extends QlanBaseDTO<TypeMachine> {
	private Long id;
	private String name;
	private String code;
	private String description;
	private Date createdDate;
	private Long createdBy;
	private Date updatedDate;
	private Long updatedBy;
	private Long isDelete;
	private Long parentId;
	private String parentName;
	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	private List<ModelDTO> listModel = Lists.newArrayList();

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
	public TypeMachine toModel() {
		TypeMachine tm = new TypeMachine();
		tm.setId(this.id);
		tm.setCode(this.code);
		tm.setName(this.name);
		tm.setDescription(this.description);
		tm.setCreatedDate(this.createdDate);
		tm.setCreatedBy(this.createdBy);
		tm.setUpdatedDate(this.updatedDate);
		tm.setUpdateBy(this.updatedBy);
		tm.setIsDelete(this.isDelete);
		tm.setParentId(this.parentId);
		return tm;
	}
}
