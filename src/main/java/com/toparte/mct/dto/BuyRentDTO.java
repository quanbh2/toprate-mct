package com.toparte.mct.dto;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.toprate.base.utils.CustomJsonDateDeserializerDOng;
import com.toprate.base.utils.CustomJsonDateSerializerDOng;
import com.toprate.mct.bo.BuyRent;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown = true)
public class BuyRentDTO extends QlanBaseDTO<BuyRent> {
	private Long id;
	private String code;
	private String title;
	private String description;
	private Long minPrice;
	private Long maxPrice;
	private Long createBy;

	@JsonSerialize(using = CustomJsonDateSerializerDOng.class)
	@JsonDeserialize(using = CustomJsonDateDeserializerDOng.class)
	private Date createDate;

	@JsonSerialize(using = CustomJsonDateSerializerDOng.class)
	@JsonDeserialize(using = CustomJsonDateDeserializerDOng.class)
	private Date reupDate;

	@JsonSerialize(using = CustomJsonDateSerializerDOng.class)
	@JsonDeserialize(using = CustomJsonDateDeserializerDOng.class)
	private Date updateDate;
	private Long updateBy;
	private Long isDelete;
	private Long type;
	private Long status;
	private Long provinceId;
	private String provinceCode;
	private String provinceName;
	private Long userId;
	private String userFullName;
	private String userTeFax;
	private String userAddress;
	private String keySearch;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getReupDate() {
		return reupDate;
	}

	public void setReupDate(Date reupDate) {
		this.reupDate = reupDate;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Long minPrice) {
		this.minPrice = minPrice;
	}

	public Long getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(Long maxPrice) {
		this.maxPrice = maxPrice;
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

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Long getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
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

	public Long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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
	public BuyRent toModel() {
		BuyRent m = new BuyRent();
		m.setId(this.id);
		m.setCode(this.code);
		m.setTitle(this.title);
		m.setDescription(this.description);
		m.setMinPrice(this.minPrice);
		m.setMaxPrice(this.maxPrice);
		m.setCreateDate(this.createDate);
		m.setCreateBy(this.createBy);
		m.setUpdateDate(this.updateDate);
		m.setUpdateBy(this.updateBy);
		m.setReupDate(this.reupDate);
		m.setIsDelete(this.isDelete);
		m.setType(this.type);
		m.setStatus(this.status);
		m.setProvinceId(this.provinceId);
		m.setCountReup(this.countReup);
		m.setCountReupDay(this.countReupDay);
		m.setCountView(this.countView);
		return m;
	}
}
