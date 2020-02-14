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

import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.model.BaseFWModelImpl;

@Table(name="image")
@Entity
public class Image extends BaseFWModelImpl{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="path")
	private String pathName;
	private String name;
	private Long create_by;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	private Date create_date;
	@Column(name="parent_id")
	private Long parentId;
	@Column(name="image_type")
	private Long imageType;
	@Column(name="fileNameEncrypt")
	private String fileNameEncrypt;
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getPathName() {
		return pathName;
	}


	public void setPathName(String pathName) {
		this.pathName = pathName;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Long getCreate_by() {
		return create_by;
	}


	public void setCreate_by(Long create_by) {
		this.create_by = create_by;
	}


	public Date getCreate_date() {
		return create_date;
	}


	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}


	public Long getParentId() {
		return parentId;
	}


	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	


	public Long getImageType() {
		return imageType;
	}


	public void setImageType(Long imageType) {
		this.imageType = imageType;
	}


	public String getFileNameEncrypt() {
		return fileNameEncrypt;
	}


	public void setFileNameEncrypt(String fileNameEncrypt) {
		this.fileNameEncrypt = fileNameEncrypt;
	}


	public Image() {
		super();
	}


	public Image(Long id, String pathName, String name, Long create_by,
			Date create_date, Long parentId, String fileNameEncrypt) {
		super();
		this.id = id;
		this.pathName = pathName;
		this.name = name;
		this.create_by = create_by;
		this.create_date = create_date;
		this.parentId = parentId;
		this.fileNameEncrypt = fileNameEncrypt;
	}


	@Override
	public BaseFWDTOImpl toDTO() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
