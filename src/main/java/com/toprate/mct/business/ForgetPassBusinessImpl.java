package com.toprate.mct.business;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import com.toparte.mct.dto.ForgetPassDTO;
import com.toparte.mct.dto.ResultDTO;
import com.toprate.mct.bo.ForgetPass;
import com.toprate.mct.constant.Constants;
import com.toprate.mct.dao.ForgetPassDAO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;

@Service("forgetPassBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ForgetPassBusinessImpl extends BaseFWBusinessImpl<ForgetPassDAO, ForgetPassDTO, ForgetPass>
		implements ForgetPassBusiness {
	@Autowired
	private ForgetPassDAO forgetPassDAO;

	@Autowired
	private CommonBusiness commonBusiness;

	@Override
	public long getTotal() {
		return forgetPassDAO.count("AdClientBO", null);
	}

	public void delete(ForgetPassDTO obj, HttpServletRequest request) {
		forgetPassDAO.delete(obj);
	}

	@SuppressWarnings("unused")
	public ResultDTO add(ForgetPassDTO obj, HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		obj.setCreatedDate(new Date());
		obj.setIsDelete(Constants.STATUSDELETE.NOTYET);
		ResultDTO resultDTO = forgetPassDAO.saveForgetPass(obj.toModel());
		return resultDTO;

	}

	public ResultDTO update(ForgetPassDTO obj, HttpServletRequest request) {
//		HttpSession httpSession = request.getSession();
//		UsersDTO usersDTO = (UsersDTO) httpSession.getAttribute("customerInfo");
		if (obj.getId() != null) {
			ResultDTO resultData = forgetPassDAO.saveOrUpdate(obj.toModel());
			return resultData;
		} else {
//			obj.setCreateBy(usersDTO.getUserId());
			obj.setCreatedDate(new Date());
			obj.setIsDelete(Constants.STATUSDELETE.NOTYET);
			ResultDTO resultData = forgetPassDAO.saveOrUpdate(obj.toModel());
			return resultData;
		}

	}

	public DataListDTO doSearch(ForgetPassDTO obj) {
		List<ForgetPassDTO> ls = forgetPassDAO.doSearch(obj);
		DataListDTO data = new DataListDTO();
		data.setData(ls);
		data.setTotal(obj.getTotalRecord());
		data.setSize(obj.getTotalRecord());
		data.setStart(1);
		return data;
	}

}
