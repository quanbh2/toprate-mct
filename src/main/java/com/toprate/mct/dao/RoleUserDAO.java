package com.toprate.mct.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import com.toparte.mct.dto.RoleUserDTO;
import com.toparte.mct.dto.UsersDTO;
import com.toprate.base.utils.ValidateUtils;
import com.toprate.mct.bo.RoleUser;
import com.toprate.mct.bo.Roles;
import com.viettel.service.base.dao.BaseFWDAOImpl;
@Repository("roleUserDAO")
public class RoleUserDAO extends BaseFWDAOImpl<RoleUser, Long> {
	public RoleUserDAO() {
		this.model = new RoleUser();
	}

	public RoleUserDAO(Session session) {
		this.session = session;
	}

	
	public void deleteByUserId(Long userId){
		StringBuilder sql=new StringBuilder(" DELETE FROM role_user WHERE USER_ID=:userId");
		SQLQuery query=getSession().createSQLQuery(sql.toString());
		query.setParameter("userId", userId);
		
		query.executeUpdate();
	}
	
	public void insert(RoleUserDTO roleUserDTO,UsersDTO userDto){
		StringBuilder sql = new StringBuilder(" INSERT INTO role_user (ROLE_ID,USER_ID,IS_ACTIVE) values (:roleId,:userId,:isActive)");
		SQLQuery query=getSession().createSQLQuery(sql.toString());
		query.setParameter("userId", userDto.getUserId());
		query.setParameter("roleId", roleUserDTO.getRoleId());
		query.setParameter("isActive", 1l);
		query.executeUpdate();
	}
}
