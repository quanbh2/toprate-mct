package com.toprate.mct.rest;

import java.util.Collections;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.toparte.mct.dto.ModelDTO;
import com.toparte.mct.dto.ValidateDataImportDTO;
import com.toprate.base.common.BusinessException;
import com.toprate.mct.business.ModelBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;

public class ModelRsServiceImpl extends AbstractRsService implements ModelRsService{

	@Autowired
	ModelBusinessImpl modelBusinessImpl;
	
	@Override
	public Response doSearch(ModelDTO obj) {
		DataListDTO data = modelBusinessImpl.doSearch(obj);
		return Response.ok(data).build();
	}

	@Override
	public Response remove(ModelDTO obj) throws Exception {
		if (obj != null) {
			try {
				modelBusinessImpl.delete(obj, request);
				return Response.ok().build();
			} catch (BusinessException e) {
				return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
			}
		} else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@Override
	public Response add(ModelDTO obj) throws Exception {
		try {
			modelBusinessImpl.add(obj, request);
			return Response.ok().build();
		} catch (BusinessException e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}
	}

	@Override
	public Response update(ModelDTO obj) throws Exception {
		try {
			modelBusinessImpl.update(obj, request);
			return Response.ok().build();
		} catch (Exception e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}
	}

	@Override
	public Response importCells(ValidateDataImportDTO dataImport) throws Exception {
		return Response.ok(modelBusinessImpl.importCells(dataImport,request)).build();
	}
	
}
