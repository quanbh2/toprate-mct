/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.toprate.mct.rest;

import java.util.Collections;
import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.toparte.mct.dto.RolesDTO;
import com.toprate.base.common.BusinessException;
import com.toprate.mct.business.RolesBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;


public class RolesRsServiceImpl extends AbstractRsService implements RolesRsService {

//    protected final Logger log = Logger.getLogger(UserRsService.class);
    @Autowired
    RolesBusinessImpl rolesBusinessImpl;

		
		@Override
		public Response doSearch(RolesDTO obj) {
			DataListDTO data= rolesBusinessImpl.doSearch(obj);
			return Response.ok(data).build();
		}

		@Override
		public Response remove(RolesDTO obj) throws Exception{
			if(obj!=null) {
				try {
			Long id = rolesBusinessImpl.deleteRoles(obj,request);
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
		public Response add(RolesDTO obj) throws Exception {
			try {
				return Response.ok(rolesBusinessImpl.add(obj,request)).build();
			}
			catch (BusinessException e) {
				return Response.ok().entity(Collections.singletonMap("error",e.getMessage())).build();
			}
		}

		@Override
		public Response update(RolesDTO obj) throws Exception{
			try {
				return Response.ok(rolesBusinessImpl.updateRoles(obj,request)).build();
			}
			catch (Exception e) {
				return Response.ok().entity(Collections.singletonMap("error",e.getMessage())).build();
			}
		}

		@Override
		public Response lockAndUnLock(List<RolesDTO> obj) throws Exception {
			try {
				return Response.ok(rolesBusinessImpl.lockAndUnLock(obj,request)).build();
			}
			catch (Exception e) {
				return Response.ok().entity(Collections.singletonMap("error",e.getMessage())).build();
			}
		}
		@Override
		public void insertRoleObjectData(RolesDTO obj) throws Exception {
			rolesBusinessImpl.insertRoleObjectData(obj);
		}
}
