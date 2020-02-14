package com.toprate.mct.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.toparte.mct.dto.TypeMachineDTO;
import com.toparte.mct.dto.ValidateDataImportDTO;

public interface TypeMachineRsService {
	@POST
	@Path("/typeMachines/doSearch")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response doSearch(TypeMachineDTO obj);

	@POST
	@Path("/typeMachines/remove")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response remove(TypeMachineDTO obj) throws Exception;

	@POST
	@Path("/typeMachines/add")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response add(TypeMachineDTO obj) throws Exception;

	@POST
	@Path("/typeMachines/update")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response update(TypeMachineDTO obj) throws Exception;
	
	
	@POST
	@Path("/typeMachines/importCells")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response importCells(ValidateDataImportDTO dataImport) throws Exception;
	
	
	@POST
	@Path("/typeMachines/getTypeMachineForAutoComplete")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getTypeMachineForAutoComplete(TypeMachineDTO obj);
	
	@POST
	@Path("/typeMachines/getTypeMachineParentForAutoComplete")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getTypeMachineParentForAutoComplete(TypeMachineDTO obj);
}
