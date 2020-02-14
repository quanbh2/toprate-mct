package com.toprate.mct.rest;

import java.util.Collections;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.toparte.mct.dto.ForgetPassDTO;
import com.toparte.mct.dto.ResultDTO;
import com.toprate.base.common.BusinessException;
import com.toprate.mct.business.ForgetPassBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;

public class ForgetPassRsServiceImpl extends AbstractRsService implements ForgetPassRsService {
	@Autowired
	ForgetPassBusinessImpl forgetPassBusinessImpl;

	@Override
	public Response remove(ForgetPassDTO obj) throws Exception {
		if (obj != null) {
			try {
				forgetPassBusinessImpl.delete(obj, request);
				return Response.ok().build();
			} catch (BusinessException e) {
				return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
			}
		} else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@Override
	public Response add(ForgetPassDTO obj) throws Exception {
		try {
			ResultDTO resultdata = forgetPassBusinessImpl.add(obj, request);
			return Response.ok(resultdata).build();
		} catch (Exception e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}
	}

	@Override
	public Response update(ForgetPassDTO obj) throws Exception {
		try {
			ResultDTO resultdata = forgetPassBusinessImpl.update(obj, request);
			return Response.ok(resultdata).build();
		} catch (Exception e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}
	}

	@Override
	public Response doSearch(ForgetPassDTO obj) {
		try {
			DataListDTO data = forgetPassBusinessImpl.doSearch(obj);
			return Response.ok(data).build();
		} catch (Exception e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}
	}
}
