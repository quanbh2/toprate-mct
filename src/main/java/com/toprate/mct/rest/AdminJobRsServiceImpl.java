package com.toprate.mct.rest;

import java.util.Collections;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.toparte.mct.dto.JobDTO;
import com.toparte.mct.dto.ResultDTO;
import com.toprate.base.common.BusinessException;
import com.toprate.mct.business.JobBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;

public class AdminJobRsServiceImpl extends AbstractRsService implements AdminJobRsService {
	@Autowired
	JobBusinessImpl jobBusinessImpl;

	@Override
	public Response remove(JobDTO obj) throws Exception {
		if (obj != null) {
			try {
				ResultDTO resultdata = jobBusinessImpl.delete(obj, request);
				return Response.ok(resultdata).build();
			} catch (BusinessException e) {
				return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
			}
		} else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	

	@Override
	public Response update(JobDTO obj) throws Exception {
		try {
			ResultDTO resultdata = jobBusinessImpl.updateAdmin(obj, request);
			return Response.ok(resultdata).build();
		} catch (Exception e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}
	}

	@Override
	public Response doSearch(JobDTO obj) {
		try {
			DataListDTO data = jobBusinessImpl.doSearch(obj);
			return Response.ok(data).build();
		} catch (Exception e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}
	}

}
