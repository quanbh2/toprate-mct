package com.toprate.mct.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.toparte.mct.dto.LogSearchDTO;
import com.toparte.mct.dto.ManufacturerDTO;
import com.toparte.mct.dto.ModelDTO;
import com.toparte.mct.dto.ProvinceDTO;
import com.toparte.mct.dto.TypeMachineDTO;

@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public interface CustomerCommonRsService {

	@GET
	@Path("/getManufacturer")
	public Response getManufacturer();
	
	@GET
	@Path("/getManufacturerForCombobox")
	public Response getManufacturerForCombobox();

	@GET
	@Path("/getTypeMachine")
	public Response getTypeMachine();

	@POST
	@Path("/doSearchByRegions")
	public Response doSearchByRegions(ProvinceDTO obj);

	@POST
	@Path("/logSearch/doSearchLogSearch")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response doSearchLogSearch(LogSearchDTO obj);

	@POST
	@Path("/logSearch/updateLogSearch")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updateLogSearch(LogSearchDTO obj) throws Exception;

	@POST
	@Path("/logSearch/removeLogSearch")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response removeLogSearch(LogSearchDTO obj) throws Exception;

	@GET
	@Path("/logSearch/getAllLogSearch")
	public Response getAllLogSearch();

	@POST
	@Path("/provinces/getProvinceForAutoComplete")
	public List<ProvinceDTO> getProvinceForAutoComplete(ManufacturerDTO obj);

	@POST
	@Path("/typeMachines/getTypeMachineForAutoComplete")
	public List<TypeMachineDTO> getTypeMachineForAutoComplete(TypeMachineDTO obj);

	@POST
	@Path("/manufacturer/getManufacturerForAutoComplete")
	public List<ManufacturerDTO> getManufacturerForAutoComplete(ManufacturerDTO obj);

	@POST
	@Path("/model/getModelForAutoComplete")
	public List<ModelDTO> getModelForAutoComplete(ModelDTO obj);
	
	@POST
	@Path("/model/doSearchModelById")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response doSearchModelById(ModelDTO obj);
}
