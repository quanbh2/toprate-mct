package com.toprate.mct.rest;

import java.util.Collections;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.toparte.mct.dto.JobApplicationDTO;
import com.toparte.mct.dto.ResultDTO;
import com.toprate.base.common.BusinessException;
import com.toprate.mct.business.JobApplicationBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;

public class AdminJobApplicationRsServiceImpl extends AbstractRsService implements AdminJobApplicationRsService {
	@Autowired
	JobApplicationBusinessImpl jobApplicationBusinessImpl;

	@Override
	public Response remove(JobApplicationDTO obj) throws Exception {
		if (obj != null) {
			try {
				ResultDTO resultdata = jobApplicationBusinessImpl.delete(obj, request);
				return Response.ok(resultdata).build();
			} catch (BusinessException e) {
				return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
			}
		} else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@Override
	public Response update(JobApplicationDTO obj) throws Exception {
		try {
			ResultDTO resultdata = jobApplicationBusinessImpl.updateAdmin(obj, request);
			return Response.ok(resultdata).build();
		} catch (Exception e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}
	}

	@Override
	public Response doSearch(JobApplicationDTO obj) {
		try {
			DataListDTO data = jobApplicationBusinessImpl.doSearch(obj);
			return Response.ok(data).build();
		} catch (Exception e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}
	}
}
