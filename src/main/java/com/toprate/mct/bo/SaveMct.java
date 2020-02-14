package com.toprate.mct.bo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.model.BaseFWModelImpl;

@Table(name = "save_mct")
@Entity
public class SaveMct extends BaseFWModelImpl{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "sel4rent_id")
	private Long sel4rentId;
	
	@Column(name = "create_by")
	private Long createBy;
	
	@Column(name = "create_date")
	private Date createDate;
	
	@Column(name = "status")
	private Long status;
	
	

	public SaveMct() {
		super();
	}

	public SaveMct(Long id, Long sel4rentId, Long createBy, Date createDate, Long status) {
		super();
		this.id = id;
		this.sel4rentId = sel4rentId;
		this.createBy = createBy;
		this.createDate = createDate;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		SaveMct that = (SaveMct) o;
		return java.util.Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return java.util.Objects.hash(id);
	}

	@Override
	public BaseFWDTOImpl toDTO() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
