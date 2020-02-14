package com.toprate.mct.rest;

import java.util.Collections;
import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.toparte.mct.dto.LogSearchDTO;
import com.toparte.mct.dto.ManufacturerDTO;
import com.toparte.mct.dto.ModelDTO;
import com.toparte.mct.dto.ProvinceDTO;
import com.toparte.mct.dto.TypeMachineDTO;
import com.toprate.mct.business.LogSearchBusinessImpl;
import com.toprate.mct.business.ManufacturerBusinessImpl;
import com.toprate.mct.business.ModelBusinessImpl;
import com.toprate.mct.business.ProvinceBusinessImpl;
import com.toprate.mct.business.TypeMachineBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;

public class CustomerCommonRsServiceImpl extends AbstractRsService implements CustomerCommonRsService {
	@Autowired
	ModelBusinessImpl modelBusinessImpl;

	@Autowired
	ManufacturerBusinessImpl manufacturerBusinessImpl;

	@Autowired
	ProvinceBusinessImpl provinceBusinessImpl;

	@Autowired
	TypeMachineBusinessImpl typeMachineBusinessImpl;

	@Autowired
	LogSearchBusinessImpl logSearchBusinesssImpl;

	@Override
	public Response doSearchLogSearch(LogSearchDTO obj) {
		try {
			DataListDTO data = logSearchBusinesssImpl.doSearchLogSearch(obj);
			return Response.ok(data).build();
		} catch (Exception e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}
	}
	@Override
	public Response doSearchModelById(ModelDTO obj) {
		DataListDTO data = modelBusinessImpl.doSearch(obj);
		return Response.ok(data).build();
	}

	@Override
	public Response updateLogSearch(LogSearchDTO obj) throws Exception {
		try {
			logSearchBusinesssImpl.updateLogSearch(obj, request);
			return Response.ok().build();
		} catch (Exception e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}
	}

	@Override
	public Response removeLogSearch(LogSearchDTO obj) throws Exception {
		try {
			logSearchBusinesssImpl.delete(obj, request);
			return Response.ok().build();
		} catch (Exception e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}
	}

	@Override
	public Response getAllLogSearch() {
		return Response.ok(logSearchBusinesssImpl.getAllLogSearch()).build();
	}

	@Override
	public Response getManufacturer() {
		return Response.ok(manufacturerBusinessImpl.getAllManufacturer()).build();
	}

	@Override
	public Response getTypeMachine() {
		return Response.ok(typeMachineBusinessImpl.getAllTypeMachine()).build();
	}

	@Override
	public List<ProvinceDTO> getProvinceForAutoComplete(ManufacturerDTO obj) {
		return provinceBusinessImpl.getProvinceForAutoComplete(obj);
	}

	@Override
	public List<TypeMachineDTO> getTypeMachineForAutoComplete(TypeMachineDTO obj) {
		return typeMachineBusinessImpl.getTypeMachineForAutoComplete(obj);
	}

	@Override
	public List<ManufacturerDTO> getManufacturerForAutoComplete(ManufacturerDTO obj) {
		// TODO Auto-generated method stub
		return manufacturerBusinessImpl.getManufacturerForAutoComplete(obj);
	}

	@Override
	public List<ModelDTO> getModelForAutoComplete(ModelDTO obj) {

		return modelBusinessImpl.getModelForAutoComplete(obj);
	}
	
	@Override
	public Response doSearchByRegions(ProvinceDTO obj) {

		try {
			DataListDTO data = provinceBusinessImpl.doSearchByRegions(obj);
			return Response.ok(data).build();
		} catch (Exception e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}
	}
	
	@Override
	public Response getManufacturerForCombobox() {
		return Response.ok(manufacturerBusinessImpl.getAllManufacturerForCombobox()).build();
	}

}
