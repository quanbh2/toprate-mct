package com.toprate.mct.business;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.toparte.mct.dto.ManufacturerDTO;
import com.toparte.mct.dto.ModelDTO;
import com.toparte.mct.dto.ProvinceDTO;
import com.toparte.mct.dto.UsersDTO;
import com.toprate.base.common.BusinessException;
import com.toprate.base.utils.QlanResource;
import com.toprate.mct.bo.Province;
import com.toprate.mct.constant.Constants;
import com.toprate.mct.dao.ProvinceDAO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;

@Service("provinceBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ProvinceBusinessImpl extends BaseFWBusinessImpl<ProvinceDAO, ProvinceDTO, Province>
		implements ProvinceBusiness {
	@Autowired
	private ProvinceDAO provinceDAO;

	@Override
	public long getTotal() {
		return provinceDAO.count("AdClientBO", null);
	}
	public DataListDTO doSearch(ProvinceDTO obj) {
		List<ProvinceDTO> ls = provinceDAO.doSearch(obj);
		DataListDTO data = new DataListDTO();
		data.setData(ls);
		data.setTotal(obj.getTotalRecord());
		data.setSize(obj.getTotalRecord());
		data.setStart(1);
		return data;
	}
	public DataListDTO doSearchByRegions(ProvinceDTO obj) {
		List<ProvinceDTO> ls = provinceDAO.doSearchByRegions(obj);
		DataListDTO data = new DataListDTO();
		data.setData(ls);
		data.setTotal(obj.getTotalRecord());
		data.setSize(obj.getTotalRecord());
		data.setStart(1);
		return data;
	}

	public Boolean checkCode(String code, Long id) {
		ProvinceDTO obj = provinceDAO.getbycode(code);
		if (id == null) {
			if (obj == null) {
				return true;
			} else {
				return false;
			}
		} else {
			if (obj == null) {
				return true;
			} else if (obj != null && obj.getId().longValue() == id) {
				return true;
			} else {
				return false;
			}
		}

	}

	@Transactional
	public void add(ProvinceDTO obj, HttpServletRequest request) {
		if (!checkCode(obj.getCode(), null)) {
			throw new BusinessException(QlanResource.get(Constants.PROVINCE_CODE_EXITS));
		}
		HttpSession httpSession = request.getSession();
		UsersDTO userDto = (UsersDTO) httpSession.getAttribute("userInfo");
		obj.setCreatedBy(userDto.getUserId());
		obj.setCreatedDate(new Date());
		provinceDAO.save(obj);
	}

	@Transactional
	public void update(ProvinceDTO obj, HttpServletRequest request) {
		if (!checkCode(obj.getCode(), obj.getId())) {
			throw new BusinessException(QlanResource.get(Constants.PROVINCE_CODE_EXITS));
		}
		HttpSession httpSession = request.getSession();
		UsersDTO userDto = (UsersDTO) httpSession.getAttribute("userInfo");
		obj.setUpdatedBy(userDto.getUserId());
		obj.setUpdatedDate(new Date());
		provinceDAO.update(obj);
	}

	@Transactional
	public void delete(ProvinceDTO obj, HttpServletRequest request) {
		provinceDAO.delete(obj);
	}

	public List<ProvinceDTO> getProvinceForAutoComplete(ManufacturerDTO obj) {
		return provinceDAO.getProvinceForAutoComplete(obj);
	}

}