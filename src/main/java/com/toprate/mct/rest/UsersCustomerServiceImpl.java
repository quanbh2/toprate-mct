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


public class UsersCustomerServiceImpl extends AbstractRsService  implements UsersCustomerService  {

	@Autowired
	UsersBusinessImpl usersBusinessImpl;
	
	@Override
	public Response login(LoginDTO loginDTO) throws Exception {
		ResultDTO resultDTO=usersBusinessImpl.loginCustomer(loginDTO.getUsername(), loginDTO.getPassword(), request);
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
	public Response add(UsersDTO obj) throws Exception {
		try {
			obj.setUserType(Constants.USER_TYPE.CUSTOMER_V0);
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


	


}
