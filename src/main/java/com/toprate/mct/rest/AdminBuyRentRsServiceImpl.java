package com.toprate.mct.rest;

import java.util.Collections;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.toparte.mct.dto.BuyRentDTO;
import com.toparte.mct.dto.ResultDTO;
import com.toprate.base.common.BusinessException;
import com.toprate.mct.business.BuyRentBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;

public class AdminBuyRentRsServiceImpl extends AbstractRsService implements AdminBuyRentRsService {

	/*
	 * @Override public Response doSearch(Sell4RentDTO obj) { DataListDTO data =
	 * Sell4RentBusinessImpl.doSearch(obj); return Response.ok(data).build(); }
	 */
	@Autowired
	BuyRentBusinessImpl buyRentBusinessImpl;

	@Override
	public Response remove(BuyRentDTO obj) throws Exception {
		if (obj != null) {
			try {
				ResultDTO resultdata= buyRentBusinessImpl.delete(obj, request);
				return Response.ok(resultdata).build();
			} catch (BusinessException e) {
				return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
			}
		} else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@Override
	public Response update(BuyRentDTO obj) throws Exception {
		try {
			ResultDTO resultdata = buyRentBusinessImpl.updateAdmin(obj, request);
			return Response.ok(resultdata).build();
		} catch (Exception e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}
	}

	@Override
	public Response doSearch(BuyRentDTO obj) {
		try {
			DataListDTO data = buyRentBusinessImpl.doSearch(obj);
			return Response.ok(data).build();
		} catch (Exception e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}
	}

}