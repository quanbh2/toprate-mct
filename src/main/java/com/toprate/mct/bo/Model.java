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

import com.toparte.mct.dto.ModelDTO;
import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.model.BaseFWModelImpl;

@Table(name = "model")
@Entity
public class Model extends BaseFWModelImpl {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "CODE")
	private String code;
	@Column(name = "description")
	private String description;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	private Date createdDate;
	@Column(name = "created_by")
	private Long createdBy;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_date")
	private Date updatedDate;
	@Column(name = "updated_by")
	private Long updatedBy;
	@Column(name = "is_delete")
	private Long isDelete;
	@Column(name = "manufacturer_id")
	private Long manufacturerId;
	@Column(name = "type_machine_id")
	private Long typeMachineId;
	public Long getTypeMachineId() {
		return typeMachineId;
	}

	public void setTypeMachineId(Long typeMachineId) {
		this.typeMachineId = typeMachineId;
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

	public Long getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Long isDelete) {
		this.isDelete = isDelete;
	}



	public Long getUpdatedBy() {
		return updatedBy;
	}

	public Long getManufacturerId() {
		return manufacturerId;
	}

	public void setManufacturerId(Long manufacturerId) {
		this.manufacturerId = manufacturerId;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Override
	public ModelDTO toDTO() {
		ModelDTO m = new ModelDTO();
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

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Model that = (Model) o;
		return java.util.Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return java.util.Objects.hash(id);
	}
}
