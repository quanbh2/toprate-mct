package com.toparte.mct.dto;

import java.util.Date;

import javax.persistence.Column;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.toprate.base.utils.CustomJsonDateSerializerDOng;
import com.toprate.mct.bo.AppParam;
import com.toprate.mct.bo.Image;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AppParamDTO extends QlanBaseDTO<AppParam> {
	private Long id;
	private String parType;
	private String parName;
	private String parCode;
	private String parValue;
	private String description;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getParType() {
		return parType;
	}
	public void setParType(String parType) {
		this.parType = parType;
	}
	public String getParName() {
		return parName;
	}
	public void setParName(String parName) {
		this.parName = parName;
	}
	public String getParCode() {
		return parCode;
	}
	public void setParCode(String parCode) {
		this.parCode = parCode;
	}
	public String getParValue() {
		return parValue;
	}
	public void setParValue(String parValue) {
		this.parValue = parValue;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public AppParam toModel() {
		AppParam bo= new AppParam();
		bo.setId(id);
		bo.setParCode(parCode);
		bo.setParName(parName);
		bo.setParType(parType);
		bo.setParValue(parValue);
		bo.setDescription(description);
		return bo;
	}

	

	

}
