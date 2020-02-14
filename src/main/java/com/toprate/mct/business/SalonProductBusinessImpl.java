package com.toprate.mct.business;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import com.toparte.mct.dto.ResultDTO;
import com.toparte.mct.dto.SalonProductDTO;
import com.toparte.mct.dto.Sell4RentDTO;
import com.toparte.mct.dto.UsersDTO;
import com.toprate.mct.bo.SalonProduct;
import com.toprate.mct.bo.Sell4Rent;
import com.toprate.mct.dao.SalonProductDAO;
import com.toprate.mct.dao.Sell4RentDAO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;

@Service("salonProductBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SalonProductBusinessImpl extends BaseFWBusinessImpl<SalonProductDAO, SalonProductDTO, SalonProduct> {

	@Autowired
	SalonProductDAO salonProductDAO;

	public ResultDTO update(SalonProductDTO obj, HttpServletRequest request) {
//		HttpSession httpSession = request.getSession();
//		UsersDTO userDto = (UsersDTO) httpSession.getAttribute("customerInfo");
//		SalonProduct salonProduct = salonProductDAO.get(SalonProduct.class, obj.getProduct_id());
//		sell4rent.setCode(obj.getCode());
//		sell4rent.setStatus(obj.getStatus());
//		sell4rent.setDescription(obj.getDescription());
//		sell4rent.setUpdate_by(userDto.getUserId());
//		sell4rent.setUpdate_date(new Date());
		
		return salonProductDAO.saveOrUpdate(obj.toModel());

	}
	
	public DataListDTO doSearch(SalonProductDTO obj) {

		List<SalonProductDTO> ls = salonProductDAO.doSearch(obj);
		DataListDTO data = new DataListDTO();
		data.setData(ls);
		data.setTotal(obj.getTotalRecord());
		data.setSize(obj.getTotalRecord());
		data.setStart(1);
		return data;
	}
}
