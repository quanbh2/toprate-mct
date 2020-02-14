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

@Table(name = "sell_4rent")
@Entity
public class Sell4Rent extends BaseFWModelImpl {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "code")
	private String code;
	@Column(name = "model_id")
	private Long modelId;
	@Column(name = "manufacture_id")
	private Long manufactureId;
	@Column(name = "status")
	private Long status;
	@Column(name = "state")
	private Long state;
	@Column(name = "title")
	private String title;
	@Column(name = "description")
	private String description;
	@Column(name = "price")
	private Long price;
	@Column(name = "create_by")
	private Long createBy;

	@Column(name = "create_date")
	private Date createDate;
	@Column(name = "reup_date")
	private Date reupDate;
	@Column(name = "update_by")
	private Long updateBy;
	@Column(name = "update_date")
	private Date updateDate;
	@Column(name = "year")
	private Long year;
	@Column(name = "number")
	private String number;
	@Column(name = "is_delete")
	private Long isDelete;
	@Column(name = "type")
	private Long type;
	@Column(name = "type_machine_id")
	private Long typeMachineId;
	@Column(name = "province_id")
	private Long provinceId;
	@Column(name = "count_reup")
	private Long countReup;
	@Column(name = "count_reup_day")
	private Long countReupDay;
	@Column(name = "count_view")
	private Long countView;
	
	public Long getCountView() {
		return countView;
	}

	public void setCountView(Long countView) {
		this.countView = countView;
	}
	
	public Long getCountReup() {
		return countReup;
	}

	public void setCountReup(Long countReup) {
		this.countReup = countReup;
	}

	public Long getCountReupDay() {
		return countReupDay;
	}

	public void setCountReupDay(Long countReupDay) {
		this.countReupDay = countReupDay;
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

	public Long getStatus() {
		return status;
	}
	
	public void setStatus(Long status) {
		this.status = status;
	}
	

	public Long getState() {
		return state;
	}

	public void setState(Long state) {
		this.state = state;
	}
	
	
	public Date getReupDate() {
		return reupDate;
	}

	public void setReupDate(Date reupDate) {
		this.reupDate = reupDate;
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

	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public Long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}

	public Sell4Rent() {
		super();
	}

	public Long getModelId() {
		return modelId;
	}

	public void setModelId(Long modelId) {
		this.modelId = modelId;
	}

	public Long getManufactureId() {
		return manufactureId;
	}

	public void setManufactureId(Long manufactureId) {
		this.manufactureId = manufactureId;
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

	public Long getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Long getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Long isDelete) {
		this.isDelete = isDelete;
	}

	public Long getTypeMachineId() {
		return typeMachineId;
	}

	public void setTypeMachineId(Long typeMachineId) {
		this.typeMachineId = typeMachineId;
	}

	@Override
	public BaseFWDTOImpl toDTO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sell4Rent other = (Sell4Rent) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		return true;
	}

}
