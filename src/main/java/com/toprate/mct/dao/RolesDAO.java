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

import com.toparte.mct.dto.RoleObjectDTO;
import com.toparte.mct.dto.RoleUserDTO;
import com.toparte.mct.dto.RolesDTO;
import com.toprate.base.utils.ValidateUtils;
import com.toprate.mct.bo.Roles;
import com.viettel.service.base.dao.BaseFWDAOImpl;
@Repository("rolesDAO")
public class RolesDAO extends BaseFWDAOImpl<Roles, Long> {
	public RolesDAO() {
		this.model = new Roles();
	}

	public RolesDAO(Session session) {
		this.session = session;
	}

	@SuppressWarnings("unchecked")
	public List<RolesDTO> doSearch(RolesDTO obj){
		StringBuilder sql = new StringBuilder("SELECT o.ROLE_ID roleId,"
				+ "o.ROLE_CODE roleCode,"
				+ "o.ROLE_NAME roleName,"
				+ "o.DESCRIPTION description,"
				+ "o.CREATE_USER createUser,"
				+ "o.CREATE_DATE createDate,"
				+ "o.STATUS status"
				+ " FROM roles o"
				+ " WHERE 1=1");
		if(StringUtils.isNotEmpty(obj.getRoleCode())){
			sql.append(" AND (upper(o.ROLE_CODE) LIKE upper(:roleCode) escape '&') ");
		}
		
		if(obj.getStatus() != null && obj.getStatus() != 2L){
			sql.append(" AND o.STATUS = :status");
		}
		
		if(StringUtils.isNotEmpty(obj.getRoleName())){
			sql.append(" AND (upper(o.ROLE_NAME) LIKE upper(:roleName) escape '&') ");
		}
		
		if(StringUtils.isNotEmpty(obj.getDescription())){
			sql.append(" AND (upper(o.DESCRIPTION) LIKE upper(:description) escape '&') ");
		}
		
		sql.append(" ORDER BY o.ROLE_CODE");
		
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");
		sqlCount.append(" as roles;");
		
		SQLQuery query= getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount=getSession().createSQLQuery(sqlCount.toString());
		
		query.addScalar("roleId", new LongType());
		query.addScalar("status", new LongType());
		query.addScalar("roleName", new StringType());
		query.addScalar("description", new StringType());
		query.addScalar("roleCode", new StringType());
		query.addScalar("createUser", new StringType());
		query.addScalar("createDate", new DateType());
		
		query.setResultTransformer(Transformers.aliasToBean(RolesDTO.class));
		if(StringUtils.isNotEmpty(obj.getRoleCode())){
		query.setParameter("roleCode", "%"+ ValidateUtils.validateKeySearch(obj.getRoleCode())+"%");
		queryCount.setParameter("roleCode", "%"+ ValidateUtils.validateKeySearch(obj.getRoleCode())+"%");
		}
		if(obj.getStatus() != null && obj.getStatus() != 2L){
			query.setLong("status", obj.getStatus());
			queryCount.setLong("status", obj.getStatus());
		}
		
		if(StringUtils.isNotEmpty(obj.getRoleName())){
			query.setParameter("roleName", "%"+ ValidateUtils.validateKeySearch(obj.getRoleName())+"%");
			queryCount.setParameter("roleName", "%"+ ValidateUtils.validateKeySearch(obj.getRoleName())+"%");
		}
		if(StringUtils.isNotEmpty(obj.getDescription())){
			query.setParameter("description", "%"+ ValidateUtils.validateKeySearch(obj.getDescription())+"%");
			queryCount.setParameter("description", "%"+ ValidateUtils.validateKeySearch(obj.getDescription())+"%");
		}
		query.setFirstResult((obj.getPage().intValue()-1)*obj.getPageSize().intValue());
		query.setMaxResults(obj.getPageSize().intValue());
		obj.setTotalRecord(((BigInteger)queryCount.uniqueResult()).intValue());
		
		return query.list();
	}
	
	public RolesDTO getbycode(String code){
		StringBuilder sql = new StringBuilder("SELECT o.ROLE_ID roleId,"
				+ "o.ROLE_CODE roleCode,"
				+ "o.ROLE_NAME roleName,"
				+ "o.DESCRIPTION description,"
				+ "o.STATUS status,"
				+ "o.CREATE_USER createUser,"
				+ "o.CREATE_DATE createDate"
				+ " FROM roles o WHERE upper(ROLE_CODE)=upper(:roleCode)");
		SQLQuery query= getSession().createSQLQuery(sql.toString());
		

		query.addScalar("roleId", new LongType());
		query.addScalar("status", new LongType());
		query.addScalar("roleName", new StringType());
		query.addScalar("description", new StringType());
		query.addScalar("roleCode", new StringType());
		query.addScalar("createUser", new StringType());
		query.addScalar("createDate", new DateType());
		query.setResultTransformer(Transformers.aliasToBean(RolesDTO.class));
		
		query.setParameter("roleCode", code);
		
		return (RolesDTO) query.uniqueResult();
	}
	
	public List<RoleObjectDTO> getRoleObjectByObjectId(Long objectId){
		StringBuilder sql = new StringBuilder("SELECT OBJECT_ID objectId," 
				+ "ROLE_ID roleId," 
				+ "IS_ACTIVE isActive"
				+ " FROM role_object WHERE ROLE_ID =:roleId");
		SQLQuery query = getSession().createSQLQuery(sql.toString());

		query.addScalar("objectId", new LongType());
		query.addScalar("roleId", new LongType());
		query.addScalar("isActive", new LongType());
		
		query.setParameter("roleId", objectId);

		query.setResultTransformer(Transformers.aliasToBean(RoleObjectDTO.class));

		return query.list();
	}
	
	public List<RoleUserDTO> getRoleUserByRoleId(Long objectId){
		StringBuilder sql = new StringBuilder("SELECT USER_ID userId," 
				+ "ROLE_ID roleId," 
				+ "IS_ACTIVE isActive"
				+ " FROM role_user WHERE ROLE_ID =:roleId");
		SQLQuery query = getSession().createSQLQuery(sql.toString());

		query.addScalar("userId", new LongType());
		query.addScalar("roleId", new LongType());
		query.addScalar("isActive", new LongType());
		
		query.setParameter("roleId", objectId);

		query.setResultTransformer(Transformers.aliasToBean(RoleUserDTO.class));

		return query.list();
	}
	 public boolean updateStatus(List<RolesDTO> lst){
	    	StringBuilder sql=new StringBuilder("update roles set STATUS =:status where role_id in (:lst)");
	    	SQLQuery query= getSession().createSQLQuery(sql.toString());
	    	List<Long> lstLong = new ArrayList<>();
	    	for(RolesDTO o:lst) {
	    		lstLong.add(o.getRoleId());
	    	}
	    	query.setParameterList("lst", lstLong);
	    	if(lst.get(0).isLock()){
	    	query.setParameter("status", 0L);
	    	}
	    	else {
	    		query.setParameter("status", 1L);
	    	}
	    	int result=query.executeUpdate();
	    	if(result>0) {
	    	return true;
	    	}
	    	else {
	    		return false;
	    	}
	    }
	 
	 @SuppressWarnings("unchecked")
		public List<RolesDTO> getForAutoCompleteRoles(RolesDTO obj) {
			String sql = "select o.ROLE_ID AS roleId," + 
					"o.`STATUS` status," + 
					"o.ROLE_NAME roleName," + 
					
					"o.ROLE_CODE roleCode, " 
					+ "o.DESCRIPTION  description "
					+ " from roles o "
					+ "WHERE o.status=:status  ";
			if(obj.getListId().size()>0){
				sql+=" and o.ROLE_ID NOT IN (:listId)";
			}
			
			StringBuilder stringBuilder = new StringBuilder(sql);
			
				if(StringUtils.isNotEmpty(obj.getRoleName())){
				stringBuilder.append(" AND (upper(o.ROLE_NAME) LIKE upper(:name) escape '&' OR upper(o.ROLE_CODE) LIKE upper(:name) escape '&')");
				}
			
			stringBuilder.append(" ORDER BY o.ROLE_CODE");
			stringBuilder.append(" LIMIT 10 ");
			
			SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
			query.addScalar("description", new StringType());
			query.addScalar("roleName", new StringType());
			query.addScalar("roleCode", new StringType());
			query.addScalar("roleId", new LongType());
			query.addScalar("status", new LongType());
		
			query.setResultTransformer(Transformers.aliasToBean(RolesDTO.class));

			if (StringUtils.isNotEmpty(obj.getRoleName())) {
				query.setParameter("name", "%" + ValidateUtils.validateKeySearch(obj.getRoleName()) + "%");
			}
			
			if(obj.getListId().size()>0) {
				query.setParameterList("listId", obj.getListId());
			}
			query.setParameter("status",1L);
			return query.list();
		}
	 
	 @SuppressWarnings("unchecked")
		public List<RoleUserDTO> getListRoleByUserId(Long userId) {
			String sql = "select o.ROLE_ID AS roleId," + 
					"o.`STATUS` status," + 
					"o.ROLE_NAME roleName," + 
					"o.ROLE_CODE roleCode, "
					+ "o.DESCRIPTION  description,"
					+ "ru.USER_ID  userId" 
					+ " from roles o "
					+ " JOIN role_user ru ON ru.ROLE_ID=o.ROLE_ID "
					+ " WHERE o.status=1 AND  ru.USER_ID=:userId ";
			
			StringBuilder stringBuilder = new StringBuilder(sql);
			
			
			stringBuilder.append(" ORDER BY o.ROLE_CODE");
			
			SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
			query.addScalar("description", new StringType());
			query.addScalar("roleName", new StringType());
			query.addScalar("roleCode", new StringType());
			query.addScalar("roleId", new LongType());
			query.addScalar("status", new LongType());
			query.addScalar("userId", new LongType());
		
			query.setResultTransformer(Transformers.aliasToBean(RoleUserDTO.class));
			query.setLong("userId", userId);
			return query.list();
		}
	 
	 public void deleteByRoleId(Long roleId){
			StringBuilder sql=new StringBuilder(" DELETE FROM role_object WHERE ROLE_ID=:roleId");
			SQLQuery query=getSession().createSQLQuery(sql.toString());
			query.setParameter("roleId", roleId);
			
			query.executeUpdate();
		}
		
		public void insert(RoleObjectDTO roleUserDTO,RolesDTO roleDto){
			StringBuilder sql = new StringBuilder(" INSERT INTO role_object (ROLE_ID,OBJECT_ID,IS_ACTIVE) values (:roleId,:objectId,:isActive)");
			SQLQuery query=getSession().createSQLQuery(sql.toString());
			query.setParameter("objectId", roleUserDTO.getObjectId());
			query.setParameter("roleId", roleDto.getRoleId());
			query.setParameter("isActive", 1l);
			query.executeUpdate();
		}
}
