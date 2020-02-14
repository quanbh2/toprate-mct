package com.toparte.mct.dto;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.toprate.mct.bo.SaveMct;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SaveMctDTO extends QlanBaseDTO<SaveMct> {

	private Long id;
	private String code;
	private Long sel4rentId;
	private Long createBy;
	private Date createDate;
	private String path;
	private Long status;
	private String type;
	private String title;
	private String description;
	private Long year;
	private Long price;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

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

	public Long getSel4rentId() {
		return sel4rentId;
	}

	public void setSel4rentId(Long sel4rentId) {
		this.sel4rentId = sel4rentId;
	}

	public Long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
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
	public SaveMct toModel() {
		// TODO Auto-generated method stub
		SaveMct s = new SaveMct();
		s.setId(this.id);
		s.setSel4rentId(this.sel4rentId);
		s.setCreateBy(this.createBy);
		s.setCreateDate(this.createDate);
		s.setStatus((long) 1);
		return s;
	}

}
