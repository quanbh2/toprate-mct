package com.toprate.mct.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.toparte.mct.dto.ManufacturerDTO;
import com.toparte.mct.dto.ValidateDataImportDTO;

public interface ManufacturerRsService {

	@POST
	@Path("/manufacturers/getManufacturerForAutoComplete")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<ManufacturerDTO> getManufacturerForAutoComplete(ManufacturerDTO obj);

	@POST
	@Path("/manufacturers/doSearch")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response doSearch(ManufacturerDTO obj);

	@POST
	@Path("/manufacturers/remove")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response remove(ManufacturerDTO obj) throws Exception;

	@POST
	@Path("/manufacturers/add")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response add(ManufacturerDTO obj) throws Exception;

	@POST
	@Path("/manufacturers/update")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response update(ManufacturerDTO obj) throws Exception;
	
	@POST
	@Path("/manufacturers/importCells")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response importCells(ValidateDataImportDTO dataImport) throws Exception;
}
