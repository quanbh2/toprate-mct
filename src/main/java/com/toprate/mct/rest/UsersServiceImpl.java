package com.toprate.mct.rest;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.toparte.mct.dto.LoginDTO;
import com.toparte.mct.dto.ResultDTO;
import com.toparte.mct.dto.UsersDTO;
import com.toprate.base.common.BusinessException;
import com.toprate.mct.business.UsersBusinessImpl;
import com.toprate.mct.constant.Constants;
import com.viettel.service.base.dto.DataListDTO;


public class UsersServiceImpl extends AbstractRsService  implements UsersService  {

	@Autowired
	UsersBusinessImpl usersBusinessImpl;
	
	@Override
	public Response login(LoginDTO loginDTO) throws Exception {
		ResultDTO resultDTO=usersBusinessImpl.login(loginDTO.getUsername(), loginDTO.getPassword(), request);
		return Response.ok(resultDTO).build();
	}

	@Override
	public void logout() {
		HttpSession httpSession= request.getSession();
		httpSession.invalidate();
	}

	@Override
	public Response changePassword(LoginDTO loginDTO) throws Exception {
		return Response.ok(usersBusinessImpl.changePassword(loginDTO)).build();
	}


	@Override
	public Response doSearch(UsersDTO obj) {
		DataListDTO data= usersBusinessImpl.doSearch(obj);
		return Response.ok(data).build();
	}
	
	@Override
	public Response doSearchCustomer(UsersDTO obj) {
		DataListDTO data= usersBusinessImpl.doSearchCustomer(obj);
		return Response.ok(data).build();
	}

	@Override
	public Response remove(UsersDTO obj) throws Exception{
		if(obj!=null) {
			try {
		Long id = usersBusinessImpl.deleteUsers(obj,request);
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
	public Response add(UsersDTO obj) throws Exception {
		try {
			obj.setUserType(Constants.USER_TYPE.STAFF);
			return Response.ok(usersBusinessImpl.add(obj,request)).build();
		}
		catch (BusinessException e) {
			return Response.ok().entity(Collections.singletonMap("error",e.getMessage())).build();
		}
	}

	@Override
	public Response update(UsersDTO obj) throws Exception{
		
			return Response.ok(usersBusinessImpl.updateUsers(obj,request)).build();
		
	}


	@Override
	public void lock(List<UsersDTO> listUsers) throws Exception {
		usersBusinessImpl.lock(listUsers,request);
	}

	@Override
	public void unlock(List<UsersDTO> listUsers) throws Exception {
		usersBusinessImpl.unlock(listUsers,request);
	}

	@Override
	public Response resetPass(UsersDTO obj) throws Exception {
		return Response.ok(usersBusinessImpl.resetPass(obj,request)).build();
	}

	@Override
	public Response getListRoleByUserId(Long id) throws Exception {
		return Response.ok(usersBusinessImpl.getListRoleByUserId(id)).build();
	}

	@Override
	public void insertUserRoleData(UsersDTO obj) throws Exception {
		usersBusinessImpl.insertUserRoleData(obj);
	}


}
