package com.toprate.mct.rest;

import java.util.Collections;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.toparte.mct.dto.ProvinceDTO;
import com.toprate.base.common.BusinessException;
import com.toprate.mct.business.ProvinceBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;

public class ProvinceRsServiceImpl extends AbstractRsService implements ProvinceRsService {

	@Autowired
	ProvinceBusinessImpl provinceBusinessImpl;

	@Override
	public Response doSearch(ProvinceDTO obj) {
		DataListDTO data = provinceBusinessImpl.doSearch(obj);
		return Response.ok(data).build();
	}

	@Override
	public Response remove(ProvinceDTO obj) throws Exception {
		if (obj != null) {
			try {
				provinceBusinessImpl.delete(obj, request);
				return Response.ok().build();
			} catch (BusinessException e) {
				return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
			}
		} else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@Override
	public Response add(ProvinceDTO obj) throws Exception {
		try {
			provinceBusinessImpl.add(obj, request);
			return Response.ok().build();
		} catch (BusinessException e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}
	}

	@Override
	public Response update(ProvinceDTO obj) throws Exception {
		try {
			provinceBusinessImpl.update(obj, request);
			return Response.ok().build();
		} catch (Exception e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}
	}

}
