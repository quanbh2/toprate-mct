package com.toprate.mct.business;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import com.toparte.mct.dto.SalonDTO;
import com.toparte.mct.dto.UsersDTO;
import com.toprate.mct.bo.Salon;
import com.toprate.mct.dao.SalonDAO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;

@Service("salonBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SalonBusinessImpl extends BaseFWBusinessImpl<SalonDAO, SalonDTO, Salon> implements SalonBusiness {
	@Autowired
	private SalonDAO salonDAO;

	@Override
	public long getTotal() {
		return salonDAO.count("AdClientBO", null);
	}

	public DataListDTO countSalonByProvince() {
		List<SalonDTO> ls = salonDAO.countSalonByProvince();
		DataListDTO data = new DataListDTO();
		data.setData(ls);
		return data;
	}

	public DataListDTO doSearch(SalonDTO obj) {
		List<SalonDTO> ls = salonDAO.doSearch(obj);
		DataListDTO data = new DataListDTO();
		data.setData(ls);
		data.setTotal(obj.getTotalRecord());
		data.setSize(obj.getTotalRecord());
		data.setStart(1);
		return data;
	}

	public void delete(SalonDTO obj, HttpServletRequest request) {
		salonDAO.delete(obj);

	}

	public void add(SalonDTO obj, HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		UsersDTO userDto = (UsersDTO) httpSession.getAttribute("customerInfo");
		salonDAO.saveOrUpdate(obj.toModel());

	}

	public void update(SalonDTO obj, HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		UsersDTO userDto = (UsersDTO) httpSession.getAttribute("customerInfo");
		Salon salon = salonDAO.get(Salon.class, obj.getId());
		salon.setCode(obj.getCode());
		salon.setName(obj.getName());
		salon.setDescription(obj.getDescription());
		salon.setContent(obj.getContent());
		salon.setProvinceId(obj.getProvinceId());
		salonDAO.saveOrUpdate(salon);

	}
}