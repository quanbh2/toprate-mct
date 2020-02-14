package com.toprate.mct.rest;

import java.util.Collections;
import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.toparte.mct.dto.ManufacturerDTO;
import com.toparte.mct.dto.ValidateDataImportDTO;
import com.toprate.base.common.BusinessException;
import com.toprate.mct.business.ManufacturerBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;

public class ManufacturerRsServiceImpl extends AbstractRsService implements ManufacturerRsService {
	@Autowired
	ManufacturerBusinessImpl manufacturerBusinessImpl;

	@Override
	public Response doSearch(ManufacturerDTO obj) {
		DataListDTO data = manufacturerBusinessImpl.doSearch(obj);
		return Response.ok(data).build();
	}

	@Override
	public Response remove(ManufacturerDTO obj) throws Exception {
		if (obj != null) {
			try {
				manufacturerBusinessImpl.delete(obj, request);
				return Response.ok().build();
			} catch (BusinessException e) {
				return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
			}
		} else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@Override
	public Response add(ManufacturerDTO obj) throws Exception {
		try {
			manufacturerBusinessImpl.add(obj, request);
			return Response.ok().build();
		} catch (BusinessException e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}
	}

	@Override
	public Response update(ManufacturerDTO obj) throws Exception {
		try {
			manufacturerBusinessImpl.update(obj, request);
			return Response.ok().build();
		} catch (Exception e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}
	}

	@Override
	public List<ManufacturerDTO> getManufacturerForAutoComplete(ManufacturerDTO obj) {
		return manufacturerBusinessImpl.getManufacturerForAutoComplete(obj);
	}

	@Override
	public Response importCells(ValidateDataImportDTO dataImport) throws Exception {
		return Response.ok(manufacturerBusinessImpl.importCells(dataImport,request)).build();
	}

}
