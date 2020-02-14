package com.toprate.mct.rest;

import com.toparte.mct.dto.SaveMctDTO;
import com.toprate.mct.bo.NewApp;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public interface NewService {

    @GET
    @Path("/new")
    Response getNew();

    @POST
    @Path("/save")
    @Consumes({ MediaType.APPLICATION_JSON})
    @Produces({ MediaType.APPLICATION_JSON})
    Response saveNew(NewApp obj);

    @POST
    @Path("/update")
    @Consumes({ MediaType.APPLICATION_JSON})
    @Produces({ MediaType.APPLICATION_JSON})
    Response updateNew(NewApp obj);

    @GET
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON})
    Response get(@PathParam("id") long id);
//
//    @POST
//    @Path("/update")
//    @Consumes({ MediaType.APPLICATION_JSON})
//    @Produces({ MediaType.APPLICATION_JSON})
//    public Response update(SaveMctDTO obj) throws Exception;
//
//    @POST
//    @Path("/delete")
//    @Consumes({ MediaType.APPLICATION_JSON})
//    @Produces({ MediaType.APPLICATION_JSON})
//    public Response delete(SaveMctDTO obj);
}
