package com.toprate.mct.business;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpRequest;

import com.toparte.mct.dto.LoginDTO;
import com.toparte.mct.dto.ResultDTO;
import com.toparte.mct.dto.UsersDTO;

public interface UsersBusiness {

	UsersDTO getUsersInfo(String userName);
	
	ResultDTO login(String userName, String password,HttpServletRequest request) throws Exception;

	ResultDTO changePassword(LoginDTO loginDTO) throws Exception;
}
