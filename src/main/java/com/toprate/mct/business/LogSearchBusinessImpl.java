package com.toprate.mct.business;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.toparte.mct.dto.LogSearchDTO;
import com.toparte.mct.dto.ManufacturerDTO;
import com.toparte.mct.dto.ModelDTO;
import com.toparte.mct.dto.TypeMachineDTO;
import com.toparte.mct.dto.UsersDTO;
import com.toprate.mct.bo.LogSearch;
import com.toprate.mct.dao.LogSearchDAO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;

@Service("logSearchBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LogSearchBusinessImpl extends BaseFWBusinessImpl<LogSearchDAO, LogSearchDTO, LogSearch>
		implements LogSearchBusiness {
	@Autowired
	private LogSearchDAO logSearchDAO;

	@Autowired
	private CommonBusiness commonBusiness;

	@Override
	public long getTotal() {
		return logSearchDAO.count("AdClientBO", null);
	}

	public DataListDTO doSearchLogSearch(LogSearchDTO obj) {
		List<LogSearchDTO> ls = logSearchDAO.doSearch(obj);
		DataListDTO data = new DataListDTO();
		data.setData(ls);
		data.setTotal(obj.getTotalRecord());
		data.setSize(obj.getTotalRecord());
		data.setStart(1);
		return data;
	}

	public List<LogSearchDTO> getAllLogSearch() {
		List<LogSearchDTO> listResult = logSearchDAO.getAllLogSearch();
		return listResult;
	}

	public void delete(LogSearchDTO obj, HttpServletRequest request) {
		logSearchDAO.delete(obj);
	}

	public void updateLogSearch(LogSearchDTO obj, HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
//		UsersDTO usersDTO = (UsersDTO) httpSession.getAttribute("customerInfo");
		if (obj.getId() != null) {
			obj.setCount(obj.getCount() + 1);
			obj.setUpdatedDate(new Date());
			logSearchDAO.update(obj.toModel());
		} else {
			obj.setCreatedDate(new Date());
			obj.setCount((long) 1);
			logSearchDAO.save(obj.toModel());
		}
	}

}
