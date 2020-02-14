package com.toprate.mct.business;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import com.toparte.mct.dto.AppParamDTO;
import com.toparte.mct.dto.JobDTO;
import com.toparte.mct.dto.ResultDTO;
import com.toparte.mct.dto.UsersDTO;
import com.toprate.mct.bo.AppParam;
import com.toprate.mct.bo.Job;
import com.toprate.mct.dao.AppParamDAO;
import com.toprate.mct.dao.JobDAO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;

@Service("appParamBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AppParamBusinessImpl extends BaseFWBusinessImpl<AppParamDAO, AppParamDTO, AppParam> implements AppParamBusiness {
	@Autowired
	private AppParamDAO appParamDAO;

	@Autowired
	private CommonBusiness commonBusiness;


	public DataListDTO doSearch(AppParamDTO obj) {
		List<AppParamDTO> ls = appParamDAO.doSearch(obj);
		DataListDTO data = new DataListDTO();
		data.setData(ls);
		data.setTotal(obj.getTotalRecord());
		data.setSize(obj.getTotalRecord());
		data.setStart(1);
		return data;
	}
	
     
}
