package com.toprate.mct.rest;

import java.util.Collections;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.toparte.mct.dto.ResultDTO;
import com.toparte.mct.dto.Sell4RentDTO;
import com.toprate.base.common.BusinessException;
import com.toprate.mct.business.Sell4RentBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;

public class AdminSell4RentRsServiceImpl extends AbstractRsService implements AdminSell4RentRsService {
	/*
	 * @Override public Response doSearch(Sell4RentDTO obj) { DataListDTO data =
	 * Sell4RentBusinessImpl.doSearch(obj); return Response.ok(data).build(); }
	 */
	@Autowired
	Sell4RentBusinessImpl sell4rentBusinessImpl;

	@Override
	public Response remove(Sell4RentDTO obj) throws Exception {
		if (obj != null) {
			try {
				ResultDTO resultdata = sell4rentBusinessImpl.delete(obj, request);
				return Response.ok(resultdata).build();
			} catch (BusinessException e) {
				return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
			}
		} else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	

	@Override
	public Response update(Sell4RentDTO obj) throws Exception {
		try {
			ResultDTO resultdata = sell4rentBusinessImpl.updateAdmin(obj, request);
			return Response.ok(resultdata).build();
		} catch (Exception e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}
	}

	@Override
	public Response doSearch(Sell4RentDTO obj) {
		try {
			DataListDTO data = sell4rentBusinessImpl.doSearch(obj);
			return Response.ok(data).build();
		} catch (Exception e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}
	}



//	@Override
//	public List<ManufacturerDTO> getSell4RentForAutoComplete(Sell4RentDTO obj) {
//		return sell4rentBusinessImpl.getManufacturerForAutoComplete(obj);
//	}
}
