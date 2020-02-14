package com.toprate.mct.rest;

import java.util.Collections;
import java.util.UUID;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.toparte.mct.dto.SalonProductDTO;
import com.toprate.mct.business.SalonProductBusinessImpl;

public class SalonProductsServiceImpl extends AbstractRsService implements SalonProductsService {
	
	@Autowired
	SalonProductBusinessImpl salonProductBusinessImpl;
	
	@Override
	public Response doSearch(SalonProductDTO obj) {
		// TODO Auto-generated method stub
		try {
			return Response.ok(salonProductBusinessImpl.doSearch(obj)).build();
		} catch (Exception e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}
	}

	@Override
	public Response remove(SalonProductDTO obj) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response update(SalonProductDTO obj) throws Exception {
		// TODO Auto-generated method stub
		try {
			return Response.ok(salonProductBusinessImpl.update(obj, request)).build();
		} catch (Exception e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}
	}
	
}
