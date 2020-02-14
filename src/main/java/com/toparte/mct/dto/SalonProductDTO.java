package com.toparte.mct.dto;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.toprate.mct.bo.Salon;
import com.toprate.mct.bo.SalonProduct;
import com.toprate.mct.bo.Sell4Rent;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SalonProductDTO extends QlanBaseDTO<SalonProduct> {
	private String productId;
	private Long salonId;
	private Long status;
	private Long manufacturerId;
	private String title;
	private String description;
	private Date createOn;
	private Long createBy;
	private Date updateOn;
	private Long updateBy;

	public SalonProductDTO() {
		super();
	}

	

	public SalonProductDTO(String productId, Long salonId, Long status, Long manufacturerId, String title,
			String description, Date createOn, Long createBy, Date updateOn, Long updateBy) {
		super();
		this.productId = productId;
		this.salonId = salonId;
		this.status = status;
		this.manufacturerId = manufacturerId;
		this.title = title;
		this.description = description;
		this.createOn = createOn;
		this.createBy = createBy;
		this.updateOn = updateOn;
		this.updateBy = updateBy;
	}



	public String getProductId() {
		return productId;
	}



	public void setProductId(String productId) {
		this.productId = productId;
	}



	public Long getSalonId() {
		return salonId;
	}



	public void setSalonId(Long salonId) {
		this.salonId = salonId;
	}



	public Long getStatus() {
		return status;
	}



	public void setStatus(Long status) {
		this.status = status;
	}



	public Long getManufacturerId() {
		return manufacturerId;
	}



	public void setManufacturerId(Long manufacturerId) {
		this.manufacturerId = manufacturerId;
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



	public Date getCreateOn() {
		return createOn;
	}



	public void setCreateOn(Date createOn) {
		this.createOn = createOn;
	}



	public Long getCreateBy() {
		return createBy;
	}



	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}



	public Date getUpdateOn() {
		return updateOn;
	}



	public void setUpdateOn(Date updateOn) {
		this.updateOn = updateOn;
	}



	public Long getUpdateBy() {
		return updateBy;
	}



	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
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
	public SalonProduct toModel() {
		// TODO Auto-generated method stub
		SalonProduct s = new SalonProduct();
		
		s.setProductId(this.productId);
		s.setTitle(this.title);
		s.setSalonId(this.salonId);
		s.setManufacturerId(this.manufacturerId);
		s.setStatus(this.status);
		s.setDescription(this.description);
		s.setCreateOn(this.createOn);
		s.setCreateBy(this.createBy);
		s.setUpdateOn(this.updateOn);
		s.setUpdateBy(this.updateBy);
		
		return s;
	}

}
