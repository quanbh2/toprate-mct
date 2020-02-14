package com.toprate.mct.rest;

import java.util.Collections;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.toparte.mct.dto.ResultDTO;
import com.toparte.mct.dto.SmsBoxDTO;
import com.toprate.base.common.BusinessException;
import com.toprate.mct.business.SmsBoxBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;

public class SmsBoxRsServiceImpl extends AbstractRsService implements SmsBoxRsService {
	@Autowired
	SmsBoxBusinessImpl smsBoxBusinessImpl;

	@Override
	public Response remove(SmsBoxDTO obj) throws Exception {
		if (obj != null) {
			try {
				smsBoxBusinessImpl.delete(obj, request);
				return Response.ok().build();
			} catch (BusinessException e) {
				return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
			}
		} else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@Override
	public Response add(SmsBoxDTO obj) throws Exception {
		try {
			ResultDTO resultdata = smsBoxBusinessImpl.add(obj, request);
			return Response.ok(resultdata).build();
		} catch (Exception e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}
	}
	
	@Override
	public Response update(SmsBoxDTO obj) throws Exception {
		try {
			ResultDTO resultdata = smsBoxBusinessImpl.update(obj, request);
			return Response.ok(resultdata).build();
		} catch (Exception e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}
	}
	@Override
	public Response doSearch(SmsBoxDTO obj) {
		try {
			DataListDTO data = smsBoxBusinessImpl.doSearch(obj);
			return Response.ok(data).build();
		} catch (Exception e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}
	}
}
