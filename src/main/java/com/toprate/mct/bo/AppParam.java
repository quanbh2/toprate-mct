package com.toprate.mct.bo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.toparte.mct.dto.AppParamDTO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.model.BaseFWModelImpl;

@Table(name="app_param")
@Entity
public class AppParam extends BaseFWModelImpl{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="par_type")
	private String parType;
	@Column(name="par_name")
	private String parName;
	@Column(name="par_code")
	private String parCode;
	@Column(name = "par_value")
	private String parValue;
	@Column(name="description")
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
	public AppParamDTO toDTO() {
		AppParamDTO dto= new AppParamDTO();
		dto.setId(id);
		dto.setParCode(parCode);
		dto.setParName(parName);
		dto.setParType(parType);
		dto.setParValue(parValue);
		dto.setDescription(description);
		return dto;
	}
	
	

}
