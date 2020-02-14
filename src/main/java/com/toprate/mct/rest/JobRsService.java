package com.toprate.mct.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.toparte.mct.dto.JobApplicationDTO;
import com.toparte.mct.dto.JobDTO;

@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public interface JobRsService {

	@POST
	@Path("/jobs/doSearch")
	public Response doSearch(JobDTO obj);
	
	@POST
	@Path("/jobs/doSearchByJob")
	public Response doSearchByJob(JobDTO obj);
	
	@POST
	@Path("/jobs/doSearchByJobApp")
	public Response doSearchByJobApp(JobApplicationDTO obj);

	@POST
	@Path("/jobs/add")
	public Response add(JobDTO obj) throws Exception;
	
	@POST
	@Path("/jobs/update")
	public Response update(JobDTO obj) throws Exception;

	@POST
	@Path("/jobApplications/doSearch")
	public Response doSearch(JobApplicationDTO obj);

	@POST
	@Path("/jobApplications/add")
	public Response add(JobApplicationDTO obj) throws Exception;
	
	@POST
	@Path("/jobApplications/update")
	public Response update(JobApplicationDTO obj) throws Exception;

}
