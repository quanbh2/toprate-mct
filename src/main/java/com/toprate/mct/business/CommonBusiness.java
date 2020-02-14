package com.toprate.mct.business;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.toprate.mct.dao.BuyRentDAO;

@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class CommonBusiness {
	@Autowired
	BuyRentDAO  buyRentDAO;
	
	public String genCode(String tableName) {
		String code=null;
    	Calendar cal = Calendar.getInstance();
    	String year=String.valueOf(cal.get(Calendar.YEAR));
    	String month=String.valueOf(cal.get(Calendar.MONTH)+1);
    	String date=String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
    	
    	String param=year+month+date;
    	 code=buyRentDAO.getCode(tableName, param);
    	 return param+code;
	}
}
