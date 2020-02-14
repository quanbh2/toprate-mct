/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.toprate.mct.rest;

import java.util.Collections;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.toparte.mct.dto.ObjectsDTO;
import com.toparte.mct.dto.RoleObjectDTO;
import com.toprate.base.common.BusinessException;
import com.toprate.mct.business.ObjectsBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;


public class ObjectsRsServiceImpl extends AbstractRsService implements ObjectsRsService {

//    protected final Logger log = Logger.getLogger(UserRsService.class);
    @Autowired
    ObjectsBusinessImpl objectsBusinessImpl;

		
		@Override
		public Response doSearch(ObjectsDTO obj) {
			DataListDTO data= objectsBusinessImpl.doSearch(obj);
			return Response.ok(data).build();
		}

		@Override
		public Response remove(ObjectsDTO obj) throws Exception{
			if(obj!=null) {
				try {
			Long id = objectsBusinessImpl.deleteObjects(obj,request);
			 return Response.ok(id).build();
				}
				catch (BusinessException e) {
					return Response.ok().entity(Collections.singletonMap("error",e.getMessage())).build();
				}
			}else {
				return Response.status(Response.Status.BAD_REQUEST).build();
			}
		}

		@Override
		public Response add(ObjectsDTO obj) throws Exception {
			try {
				return Response.ok(objectsBusinessImpl.add(obj,request)).build();
			}
			catch (BusinessException e) {
				return Response.ok().entity(Collections.singletonMap("error",e.getMessage())).build();
			}
		}

		@Override
		public Response update(ObjectsDTO obj) throws Exception{
			try {
				return Response.ok(objectsBusinessImpl.updateObjects(obj,request)).build();
			}
			catch (Exception e) {
				return Response.ok().entity(Collections.singletonMap("error",e.getMessage())).build();
			}
		}

		@Override
		public Response getParent() {
			DataListDTO data= objectsBusinessImpl.getParent();
			return Response.ok(data).build();
		}

		@Override
		public Response lockAndUnLock(ObjectsDTO obj) throws Exception {
			try {
				return Response.ok(objectsBusinessImpl.lockAndUnLock(obj,request)).build();
			}
			catch (Exception e) {
				return Response.ok().entity(Collections.singletonMap("error",e.getMessage())).build();
			}
		}
		
		@Override
		public Response doSearchRoleObject(RoleObjectDTO obj) {
			DataListDTO data= objectsBusinessImpl.doSearchObjectRole(obj);
			return Response.ok(data).build();
		}
		@Override
		public Response getForAutoCompleteObject(ObjectsDTO obj) {
			// TODO Auto-generated method stub
			return Response.ok(objectsBusinessImpl.getForAutoCompleteObject(obj)).build();
		}
}
