package com.toparte.mct.dto;

import java.util.List;

import com.google.common.collect.Lists;

public class ValidateDataImportDTO {

	private Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getType() {
		return type;
	}
	public void setType(Long type) {
		this.type = type;
	}
	List<? extends Object>  listData= Lists.newArrayList();
	public List<? extends Object> getListData() {
		return listData;
	}

	public void setListData(List<? extends Object> listData) {
		this.listData = listData;
	}
	private Long type;
	private boolean error;
	private String path;
	public boolean isError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
}
