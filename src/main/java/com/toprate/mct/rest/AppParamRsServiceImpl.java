package com.toprate.mct.rest;

import javax.ws.rs.core.Response;

import org.apache.commons.httpclient.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;

import com.toparte.mct.dto.AppParamDTO;
import com.toprate.mct.business.AppParamBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;

public class AppParamRsServiceImpl extends AbstractRsService implements AppParamRsService {

	@Autowired
	AppParamBusinessImpl appParamBusinessImpl;


	@Override
	public Response doSearch(AppParamDTO obj) {
		DataListDTO data = appParamBusinessImpl.doSearch(obj);
		return Response.ok(data).build();
	}

	@Override
	public Response add(AppParamDTO obj) throws Exception {
			
			return Response.ok(appParamBusinessImpl.save(obj)).build();
	}


	@Override
	public Response update(AppParamDTO obj) throws Exception {
			return Response.ok(appParamBusinessImpl.update(obj)).build();
	}

	@Override
	public Response delete(AppParamDTO obj) throws Exception {
		appParamBusinessImpl.delete(obj);
		return Response.ok(HttpStatus.SC_OK).build();
	}


}
