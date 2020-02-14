package com.toprate.mct.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;

public interface FileServer {
	@GET
    @Path("/downloadImport")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadFileImport(@Context HttpServletRequest request)throws Exception;
	
	
	@POST
    @Path("/uploadATTT")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response uploadATTT(List<Attachment> attachments, @Context HttpServletRequest request);
    
    @POST
    @Path("/uploadTemp")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response uploadTemp(List<Attachment> attachments, @Context HttpServletRequest request);   
    
    @GET
    @Path("/downloadFileATTT")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadFileATTT(@Context HttpServletRequest request) throws Exception;
    
    
    @POST
    @Path("/uploadImgATTT")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response uploadImgATTT(List<Attachment> attachments, @Context HttpServletRequest request);
}
