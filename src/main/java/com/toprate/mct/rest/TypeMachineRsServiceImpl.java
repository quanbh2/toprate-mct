package com.toprate.mct.rest;

import java.util.Collections;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.toparte.mct.dto.TypeMachineDTO;
import com.toparte.mct.dto.ValidateDataImportDTO;
import com.toprate.base.common.BusinessException;
import com.toprate.mct.business.TypeMachineBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;

public class TypeMachineRsServiceImpl extends AbstractRsService implements TypeMachineRsService {

	@Autowired
	TypeMachineBusinessImpl typeMachineBusinessImpl;

	@Override
	public Response doSearch(TypeMachineDTO obj) {
		DataListDTO data = typeMachineBusinessImpl.doSearch(obj);
		return Response.ok(data).build();
	}

	@Override
	public Response remove(TypeMachineDTO obj) throws Exception {
		if (obj != null) {
			try {
				typeMachineBusinessImpl.delete(obj, request);
				return Response.ok().build();
			} catch (BusinessException e) {
				return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
			}
		} else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@Override
	public Response add(TypeMachineDTO obj) throws Exception {
		try {
			typeMachineBusinessImpl.add(obj, request);
			return Response.ok().build();
		} catch (BusinessException e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}
	}

	@Override
	public Response update(TypeMachineDTO obj) throws Exception {
		try {
			typeMachineBusinessImpl.update(obj, request);
			return Response.ok().build();
		} catch (Exception e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}
	}

	@Override
	public Response importCells(ValidateDataImportDTO dataImport) throws Exception {
		return Response.ok(typeMachineBusinessImpl.importCells(dataImport,request)).build();
	}

	@Override
	public Response getTypeMachineForAutoComplete(TypeMachineDTO obj) {
		return Response.ok(typeMachineBusinessImpl.getTypeMachineForAutoComplete(obj)).build();
	}
	
	@Override
	public Response getTypeMachineParentForAutoComplete(TypeMachineDTO obj) {
		return Response.ok(typeMachineBusinessImpl.getTypeMachineParentForAutoComplete(obj)).build();
	}

}
