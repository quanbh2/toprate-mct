package com.toparte.mct.dto;

import java.util.List;

import com.google.common.collect.Lists;

public class ValidateFileImportDTO {
	private Long id;
	private String type;
	private boolean error;
	private String path;
	List<? extends Object>  listData= Lists.newArrayList();
	public List<? extends Object> getListData() {
		return listData;
	}

	public void setListData(List<? extends Object> listData) {
		this.listData = listData;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

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
