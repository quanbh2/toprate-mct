package com.toprate.mct.rest;

import java.util.Collections;
import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.toparte.mct.dto.BuyRentDTO;
import com.toparte.mct.dto.JobApplicationDTO;
import com.toparte.mct.dto.ResultDTO;
import com.toprate.base.common.BusinessException;
import com.toprate.mct.business.BuyRentBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;

public class BuyRentRsServiceImpl extends AbstractRsService implements BuyRentRsService {
	@Autowired
	BuyRentBusinessImpl buyRentBusinessImpl;

	@Override
	public Response doSearch(BuyRentDTO obj) {

		try {
			DataListDTO data = buyRentBusinessImpl.doSearch(obj);
			return Response.ok(data).build();
		} catch (Exception e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}
	}

	@Override
	public Response remove(BuyRentDTO obj) throws Exception {
		if (obj != null) {
			try {
				ResultDTO resultdata = buyRentBusinessImpl.deleteAdmin(obj, request);
				return Response.ok(resultdata).build();
			} catch (BusinessException e) {
				return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
			}
		} else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@Override
	public Response add(BuyRentDTO obj) throws Exception {
		try {
			buyRentBusinessImpl.add(obj, request);
			return Response.ok().build();
		} catch (BusinessException e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}
	}

	@Override
	public Response update(BuyRentDTO obj) throws Exception {
		try {
			ResultDTO resultdata = buyRentBusinessImpl.update(obj, request);
			return Response.ok(resultdata).build();
		} catch (Exception e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}
	}
	
	
	@Override
	public List<BuyRentDTO> getBuyRentForAutoComplete(BuyRentDTO obj) {
		// TODO Auto-generated method stub
		return buyRentBusinessImpl.getBuyRentForAutoComplete(obj);
	}

	@Override
	public Response doSearchByUser(BuyRentDTO obj) {
		try {
			DataListDTO data = buyRentBusinessImpl.doSearchByUser(obj,request);
			return Response.ok(data).build();
		} catch (Exception e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}
	}

}
