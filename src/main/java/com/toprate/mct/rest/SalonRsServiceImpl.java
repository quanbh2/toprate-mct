package com.toprate.mct.rest;

import java.util.Collections;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.toparte.mct.dto.SalonDTO;
import com.toprate.base.common.BusinessException;
import com.toprate.mct.business.SalonBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;

public class SalonRsServiceImpl extends AbstractRsService implements SalonRsService {
	@Autowired
	SalonBusinessImpl salonBusinessImpl;
	
	@Override
	public Response doSearch(SalonDTO obj) {

		try {
			DataListDTO data = salonBusinessImpl.doSearch(obj);
			return Response.ok(data).build();
		} catch (Exception e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}
	}

	@Override
	public Response remove(SalonDTO obj) throws Exception {
		if (obj != null) {
			try {
				salonBusinessImpl.delete(obj, request);
				return Response.ok().build();
			} catch (BusinessException e) {
				return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
			}
		} else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@Override
	public Response add(SalonDTO obj) throws Exception {
		try {
			salonBusinessImpl.add(obj, request);
			return Response.ok().build();
		} catch (BusinessException e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}
	}

	@Override
	public Response update(SalonDTO obj) throws Exception {
		try {
			salonBusinessImpl.update(obj, request);
			return Response.ok().build();
		} catch (Exception e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}
	}

	@Override
	public Response countSalonByProvince() {
		try {
			DataListDTO data = salonBusinessImpl.countSalonByProvince();
			return Response.ok(data).build();
		} catch (Exception e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}
	}

}