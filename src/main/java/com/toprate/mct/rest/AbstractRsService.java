package com.toprate.mct.rest;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import com.toparte.mct.dto.SalonProductDTO;
import com.toprate.base.common.BusinessException;
import com.toprate.logger.filter.UserSession;

public abstract class AbstractRsService {
	@Context HttpServletRequest request;
	protected static final String USER_SESSION_KEY="USER_SESSION_KEY";
	protected static final String VSA_USER_TOKEN_KEY="VSA_USER_TOKEN_KEY";
	public UserSession getUserSession(){
		UserSession s=(UserSession)request.getSession().getAttribute(USER_SESSION_KEY);
		if(s==null){
			throw new BusinessException("user is not authen");
		}
		return s;
		
	}
	
}
