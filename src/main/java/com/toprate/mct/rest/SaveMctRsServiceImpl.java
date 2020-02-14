package com.toprate.mct.rest;

import java.util.Collections;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.toparte.mct.dto.ResultDTO;
import com.toparte.mct.dto.SaveMctDTO;
import com.toprate.mct.business.SaveMctBusiness;
import com.toprate.mct.business.SaveMctBusinessImpl;
import com.toprate.mct.business.Sell4RentBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;

public class SaveMctRsServiceImpl extends AbstractRsService implements SaveMctRsService{

	@Autowired
	SaveMctBusinessImpl saveMctBusinessImpl;
	@Override
	public Response doSearch(SaveMctDTO obj) {
		// TODO Auto-generated method stub
		try {
			DataListDTO data = saveMctBusinessImpl.doSearch(obj,request);
			return Response.ok(data).build();
		} catch (Exception e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}
	}

	@Override
	public Response update(SaveMctDTO obj) {
		// TODO Auto-generated method stub
		try {
			ResultDTO resultdata = saveMctBusinessImpl.update(obj, request);
			return Response.ok(resultdata).build();
		} catch (Exception e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}
	}

	@Override
	public Response delete(SaveMctDTO obj) {
		// TODO Auto-generated method stub
		try {
			ResultDTO resultdata = saveMctBusinessImpl.deleteMct(obj);
			return Response.ok(resultdata).build();
		} catch (Exception e) {
			return Response.ok().entity(Collections.singletonMap("error", e.getMessage())).build();
		}
	}

}
