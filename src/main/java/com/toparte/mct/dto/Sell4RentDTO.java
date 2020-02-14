package com.toparte.mct.dto;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.toprate.base.utils.CustomJsonDateDeserializerDOng;
import com.toprate.base.utils.CustomJsonDateSerializerDOng;
import com.toprate.mct.bo.Sell4Rent;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Sell4RentDTO extends QlanBaseDTO<Sell4Rent> {
	private Long id;
	private String code;
	private Long modelId;
	private Long manufactureId;
	private Long status;
	private Long state;
	private String title;
	private String description;
	private Long price;
	private Long createBy;
	@JsonSerialize(using = CustomJsonDateSerializerDOng.class)
	@JsonDeserialize(using = CustomJsonDateDeserializerDOng.class)
	private Date reupDate;

	@JsonSerialize(using = CustomJsonDateSerializerDOng.class)
	@JsonDeserialize(using = CustomJsonDateDeserializerDOng.class)
	private Date createDate;
	private Long updateBy;

	@JsonSerialize(using = CustomJsonDateSerializerDOng.class)
	@JsonDeserialize(using = CustomJsonDateDeserializerDOng.class)
	private Date updateDate;
	private Long year;
	private String number;
	private Long isDelete;
	private Long type;
	private Long typeMachineId;
	private Long provinceId;
	private String provinceName;
	private String provinceCode;
	private Long startYear;
	private Long endYear;
	private Long startPrice;
	private Long endPrice;
	private String modelName;
	private String manufactureName;
	private String typeMachineName;
	private String userFullName;
	private String userTeFax;
	private String userAddress;
	private String keySearch;
	private String pathName;
	private String name;
	private Long parentId;
	private List<ImageDTO> listImages;
	private String haveImage;
	private Long sortBy;
	private Long countReup;
	private Long countReupDay;
	private Long checkReup;

	@JsonSerialize(using = CustomJsonDateSerializerDOng.class)
	@JsonDeserialize(using = CustomJsonDateDeserializerDOng.class)
	private Date fromDate;

	@JsonSerialize(using = CustomJsonDateSerializerDOng.class)
	@JsonDeserialize(using = CustomJsonDateDeserializerDOng.class)
	private Date toDate;
	private Long countView;
	
	public Long getCountView() {
		return countView;
	}

	public void setCountView(Long countView) {
		this.countView = countView;
	}
	
	
	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Long getCheckReup() {
		return checkReup;
	}

	public void setCheckReup(Long checkReup) {
		this.checkReup = checkReup;
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

	public Long getSortBy() {
		return sortBy;
	}

	public void setSortBy(Long sortBy) {
		this.sortBy = sortBy;
	}

	public Long getId() {
		return id;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
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

	public Date getReupDate() {
		return reupDate;
	}

	public void setReupDate(Date reupDate) {
		this.reupDate = reupDate;
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

	public String getTypeMachineName() {
		return typeMachineName;
	}

	public void setTypeMachineName(String typeMachineName) {
		this.typeMachineName = typeMachineName;
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

	public Long getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Long isDelete) {
		this.isDelete = isDelete;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public Long getTypeMachineId() {
		return typeMachineId;
	}

	public void setTypeMachineId(Long typeMachineId) {
		this.typeMachineId = typeMachineId;
	}

	public Long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public Long getStartYear() {
		return startYear;
	}

	public void setStartYear(Long startYear) {
		this.startYear = startYear;
	}

	public Long getEndYear() {
		return endYear;
	}

	public void setEndYear(Long endYear) {
		this.endYear = endYear;
	}

	public Long getStartPrice() {
		return startPrice;
	}

	public void setStartPrice(Long startPrice) {
		this.startPrice = startPrice;
	}

	public Long getEndPrice() {
		return endPrice;
	}

	public void setEndPrice(Long endPrice) {
		this.endPrice = endPrice;
	}

	public String getManufactureName() {
		return manufactureName;
	}

	public void setManufactureName(String manufactureName) {
		this.manufactureName = manufactureName;
	}

	public String getUserFullName() {
		return userFullName;
	}

	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}

	public String getUserTeFax() {
		return userTeFax;
	}

	public void setUserTeFax(String userTeFax) {
		this.userTeFax = userTeFax;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getKeySearch() {
		return keySearch;
	}

	public void setKeySearch(String keySearch) {
		this.keySearch = keySearch;
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

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public List<ImageDTO> getListImages() {
		return listImages;
	}

	public void setListImages(List<ImageDTO> listImages) {
		this.listImages = listImages;
	}

	public String getHaveImage() {
		return haveImage;
	}

	public void setHaveImage(String haveImage) {
		this.haveImage = haveImage;
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
	public Sell4Rent toModel() {
		// TODO Auto-generated method stub
		Sell4Rent s = new Sell4Rent();
		s.setId(this.id);
		s.setCode(this.code);
		s.setManufactureId(this.manufactureId);
		s.setModelId(this.modelId);
		s.setStatus(this.status);
		s.setState(this.state);
		s.setTitle(this.title);
		s.setDescription(this.description);
		s.setPrice(this.price);
		s.setCreateBy(this.createBy);
		s.setCreateDate(this.createDate);
		s.setReupDate(this.reupDate);
		s.setUpdateBy(this.updateBy);
		s.setUpdateDate(this.updateDate);
		s.setYear(this.year);
		s.setNumber(this.number);
		s.setIsDelete(this.isDelete);
		s.setType(this.type);
		s.setTypeMachineId(this.typeMachineId);
		s.setProvinceId(this.provinceId);
		s.setCountReup(this.countReup);
		s.setCountReupDay(this.countReupDay);
		s.setCountView(this.countView);
		return s;
	}
}
