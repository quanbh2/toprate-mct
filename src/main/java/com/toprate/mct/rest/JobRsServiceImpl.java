package com.toprate.mct.rest;

import java.util.Collections;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.toparte.mct.dto.JobApplicationDTO;
import com.toparte.mct.dto.JobDTO;
import com.toparte.mct.dto.ResultDTO;
import com.toprate.base.common.BusinessException;
import com.toprate.mct.business.JobApplicationBusinessImpl;
import com.toprate.mct.business.JobBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;

public class JobRsServiceImpl extends AbstractRsService implements JobRsService {

	@Autowired
	JobBusinessImpl jobBusinessImpl;

	@Autowired
	JobApplicationBusinessImpl jobApplicationBusinessImpl;

	@Override
	public Response doSearch(JobDTO obj) {
		DataListDTO data = jobBusinessImpl.doSearch(obj);
		return Response.ok(data).build();
	}

	@Override
	public Response add(JobDTO obj) throws Exception {
		try {
			jobBusinessImpl.add(obj, request);
			return Response.ok().build();
		} catch (BusinessException e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}
	}

	@Override
	public Response doSearch(JobApplicationDTO obj) {
		DataListDTO data = jobApplicationBusinessImpl.doSearch(obj);
		return Response.ok(data).build();
	}

	@Override
	public Response add(JobApplicationDTO obj) throws Exception {
		try {
			jobApplicationBusinessImpl.add(obj, request);
			return Response.ok().build();
		} catch (BusinessException e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}
	}

	@Override
	public Response doSearchByJob(JobDTO obj) {
		// TODO Auto-generated method stub
		try {
			DataListDTO data = jobBusinessImpl.doSearchByJob(obj,request);
			return Response.ok(data).build();
		} catch (BusinessException e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}
	}

	@Override
	public Response doSearchByJobApp(JobApplicationDTO obj) {
		// TODO Auto-generated method stub
		try {
			DataListDTO data = jobApplicationBusinessImpl.doSearchByJobApp(obj,request);
			return Response.ok(data).build();
		} catch (BusinessException e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}
	}

	@Override
	public Response update(JobDTO obj) throws Exception {
		// TODO Auto-generated method stub
		try {
			ResultDTO resultdata = jobBusinessImpl.update(obj, request);
			return Response.ok(resultdata).build();
		} catch (BusinessException e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}
	}
	@Override
	public Response update(JobApplicationDTO obj) throws Exception {
		// TODO Auto-generated method stub
		try {
			ResultDTO resultdata = jobApplicationBusinessImpl.update(obj, request);
			return Response.ok(resultdata).build();
		} catch (BusinessException e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}
	}

}
