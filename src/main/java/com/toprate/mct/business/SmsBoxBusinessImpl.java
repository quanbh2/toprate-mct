package com.toprate.mct.business;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import com.toparte.mct.dto.BuyRentDTO;
import com.toparte.mct.dto.ResultDTO;
import com.toparte.mct.dto.SmsBoxDTO;
import com.toparte.mct.dto.UsersDTO;
import com.toprate.mct.bo.SmsBox;
import com.toprate.mct.constant.Constants;
import com.toprate.mct.dao.SmsBoxDAO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;

@Service("smsBoxBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SmsBoxBusinessImpl extends BaseFWBusinessImpl<SmsBoxDAO, SmsBoxDTO, SmsBox> implements SmsBoxBusiness {

	@Autowired
	private SmsBoxDAO smsBoxDAO;

	@Autowired
	private CommonBusiness commonBusiness;

	@Override
	public long getTotal() {
		return smsBoxDAO.count("AdClientBO", null);
	}

	public void delete(SmsBoxDTO obj, HttpServletRequest request) {
		smsBoxDAO.delete(obj);

	}

	public ResultDTO add(SmsBoxDTO obj, HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		UsersDTO userDto = (UsersDTO) httpSession.getAttribute("customerInfo");
		obj.setCreateBy(userDto.getUserId());
		obj.setCreateDate(new Date());
		obj.setIsDelete(Constants.STATUSDELETE.NOTYET);
		ResultDTO resultDTO = smsBoxDAO.saveSms(obj.toModel());
		return resultDTO;

	}
	public ResultDTO update(SmsBoxDTO obj, HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		UsersDTO usersDTO = (UsersDTO) httpSession.getAttribute("customerInfo");
		if (obj.getId() != null) {
			ResultDTO resultData = smsBoxDAO.saveOrUpdate(obj.toModel());
			return resultData;
		} else {
			obj.setCreateBy(usersDTO.getUserId());
			obj.setCreateDate(new Date());
			obj.setIsDelete(Constants.STATUSDELETE.NOTYET);
			obj.setRecipientDelSms(Constants.STATUSDELETE.NOTYET);
			ResultDTO resultData = smsBoxDAO.saveOrUpdate(obj.toModel());
			return resultData;
		}

	}
	public DataListDTO doSearch(SmsBoxDTO obj) {
		List<SmsBoxDTO> ls = smsBoxDAO.doSearch(obj);
		DataListDTO data = new DataListDTO();
		data.setData(ls);
		data.setTotal(obj.getTotalRecord());
		data.setSize(obj.getTotalRecord());
		data.setStart(1);
		return data;
	}

}
