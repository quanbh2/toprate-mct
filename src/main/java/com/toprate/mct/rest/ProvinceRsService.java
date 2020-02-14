package com.toprate.mct.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.toparte.mct.dto.ProvinceDTO;

@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public interface ProvinceRsService {

	@POST
	@Path("/provinces/doSearch")
	public Response doSearch(ProvinceDTO obj);

	@POST
	@Path("/provinces/remove")
	public Response remove(ProvinceDTO obj) throws Exception;

	@POST
	@Path("/provinces/add")
	public Response add(ProvinceDTO obj) throws Exception;

	@POST
	@Path("/provinces/update")
	public Response update(ProvinceDTO obj) throws Exception;
}
