package com.toprate.mct.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.toparte.mct.dto.AppParamDTO;

@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public interface AppParamRsService {

	@POST
	@Path("/appParam/doSearch")
	public Response doSearch(AppParamDTO obj);
	
	

	@POST
	@Path("/appParam/add")
	public Response add(AppParamDTO obj) throws Exception;
	
	@POST
	@Path("/appParam/update")
	public Response update(AppParamDTO obj) throws Exception;
	
	
	@POST
	@Path("/appParam/delete")
	public Response delete(AppParamDTO obj) throws Exception;


}
