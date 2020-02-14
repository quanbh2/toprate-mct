package com.toparte.mct.dto;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.toprate.base.utils.CustomJsonDateDeserializerDOng;
import com.toprate.base.utils.CustomJsonDateSerializerDOng;
import com.toprate.mct.bo.Image;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageDTO extends QlanBaseDTO<Image> {
	private Long id;
	private String pathName;
	private String name;
	private Long create_by;
	
	@JsonSerialize(using = CustomJsonDateSerializerDOng.class)
	@JsonDeserialize(using = CustomJsonDateDeserializerDOng.class)
	private Date create_date;
	private Long parentId;
	private Long imageType;
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



	public ImageDTO() {
		super();
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
	public Image toModel() {
		Image i = new Image();
		i.setId(this.id);
		i.setPathName(this.pathName);
		i.setName(this.name);
		i.setCreate_date(this.create_date);
		i.setCreate_by(this.create_by);
		i.setParentId(this.parentId);
		i.setImageType(this.imageType);
		i.setFileNameEncrypt(this.fileNameEncrypt);
		
		return i;
	}

	

	

}
