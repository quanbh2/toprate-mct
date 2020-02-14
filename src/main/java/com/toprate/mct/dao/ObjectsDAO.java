package com.toprate.mct.dao;

import java.math.BigInteger;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import com.toparte.mct.dto.ObjectsDTO;
import com.toparte.mct.dto.RoleObjectDTO;
import com.toprate.base.utils.ValidateUtils;
import com.toprate.mct.bo.Objects;
import com.viettel.service.base.dao.BaseFWDAOImpl;
@Repository("objectsDAO")
public class ObjectsDAO extends BaseFWDAOImpl<Objects, Long> {
	public ObjectsDAO() {
		this.model = new Objects();
	}

	public ObjectsDAO(Session session) {
		this.session = session;
	}

	
	
	@SuppressWarnings("unchecked")
	public List<ObjectsDTO> getListObjects(Long userId,Long parentId){
		StringBuilder sql= new StringBuilder("SELECT DISTINCT ");
		sql.append(" obj.OBJECT_ID AS objectId,");
		sql.append(" obj.PARENT_ID AS parentId,");
		sql.append(" obj.`STATUS` AS `status`,");
		sql.append(" obj.ORD AS ord,");
		sql.append(" obj.OBJECT_URL AS objectUrl,");
		sql.append(" obj.OBJECT_NAME AS objectName,");
		sql.append(" obj.DESCRIPTION AS description,");
		sql.append(" obj.OBJECT_TYPE_ID AS objectTypeId,");
		sql.append(" obj.OBJECT_CODE AS objectCode,");
		sql.append(" obj.CREATE_USER AS createUser,");
		sql.append(" obj.CREATE_DATE AS createDate");
		sql.append(" FROM objects AS obj  ");
		
		if(parentId !=null){
			sql.append(" JOIN role_object on obj.OBJECT_ID=role_object.OBJECT_ID");
			sql.append(" JOIN role_user ON role_user.ROLE_ID=role_object.ROLE_ID");
			sql.append(" WHERE role_user.USER_ID=:userId AND role_user.IS_ACTIVE=1 AND role_object.IS_ACTIVE=1 AND obj.`STATUS`=1 ");
			sql.append(" AND obj.PARENT_ID =:parentId ");
		} else {
			sql.append(" WHERE obj.OBJECT_ID IN (SELECT objects.PARENT_ID FROM  objects ");
			sql.append(" JOIN role_object on objects.OBJECT_ID=role_object.OBJECT_ID");
			sql.append(" JOIN role_user ON role_user.ROLE_ID=role_object.ROLE_ID");
			sql.append(" WHERE role_user.USER_ID=:userId AND role_user.IS_ACTIVE=1 AND role_object.IS_ACTIVE=1  ) AND obj.`STATUS`=1 ");
		}
		sql.append(" ORDER BY obj.ORD ");
		
		SQLQuery query= getSession().createSQLQuery(sql.toString());
		
		query.addScalar("objectId", new LongType());
		query.addScalar("parentId", new LongType());
		query.addScalar("status", new LongType());
		query.addScalar("ord", new LongType());
		query.addScalar("objectUrl", new StringType());
		query.addScalar("objectName", new StringType());
		query.addScalar("description", new StringType());
		query.addScalar("objectTypeId", new LongType());
		query.addScalar("objectCode", new StringType());
		query.addScalar("createUser", new StringType());
		query.addScalar("createDate", new DateType());
		
		query.setResultTransformer(Transformers.aliasToBean(ObjectsDTO.class));
		
		query.setParameter("userId", userId);
		if(parentId!=null){
			query.setParameter("parentId", parentId);
		}
		
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public List<ObjectsDTO> doSearch(ObjectsDTO obj){
		StringBuilder sql = new StringBuilder("SELECT o.OBJECT_ID objectId,"
				+ "o.OBJECT_CODE objectCode,"
				+ "o.OBJECT_NAME objectName,"
				+ "o.PARENT_ID parentId,"
				+ "o.ORD ord,"
				+ "o.OBJECT_URL objectUrl,"
				+ "o.DESCRIPTION description,"
				+ "o.OBJECT_TYPE_ID objectTypeId,"
				+ "o1.OBJECT_NAME parentName,"
				+ " o.CREATE_USER createUser,"
				+ " o.CREATE_DATE createDate,"
				+ "o.STATUS status"
				+ " FROM objects o"
				+ " left join objects o1 on o1.OBJECT_ID = o.PARENT_ID "
				+ " WHERE 1=1");
		if(StringUtils.isNotEmpty(obj.getObjectCode())){
			sql.append(" AND (upper(o.OBJECT_CODE) LIKE upper(:objectCode) escape '&')");
		}
		
		if(obj.getStatus() != null && obj.getStatus() != 2L){
			sql.append(" AND o.STATUS = :status");
		}
		if(obj.getParentId() != null){
			sql.append(" AND o.PARENT_ID = :parentId");
		}
		if(obj.getObjectTypeId() != null && obj.getObjectTypeId() != 2L){
			sql.append(" AND o.OBJECT_TYPE_ID = :objectTypeId");
		}
		if(StringUtils.isNotEmpty(obj.getObjectName())){
			sql.append(" AND (upper(o.OBJECT_NAME) LIKE upper(:objectName) escape '&') ");
		}
		
		sql.append(" ORDER BY o.ORD");
		
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");
		sqlCount.append(" as objects;");
		
		SQLQuery query= getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount=getSession().createSQLQuery(sqlCount.toString());
		
		query.addScalar("objectId", new LongType());
		query.addScalar("parentId", new LongType());
		query.addScalar("status", new LongType());
		query.addScalar("ord", new LongType());
		query.addScalar("objectUrl", new StringType());
		query.addScalar("objectName", new StringType());
		query.addScalar("description", new StringType());
		query.addScalar("objectTypeId", new LongType());
		query.addScalar("objectCode", new StringType());
		query.addScalar("parentName", new StringType());
		query.addScalar("createUser", new StringType());
		query.addScalar("createDate", new DateType());
		
		query.setResultTransformer(Transformers.aliasToBean(ObjectsDTO.class));
		if(StringUtils.isNotEmpty(obj.getObjectCode())){
		query.setParameter("objectCode", "%"+ ValidateUtils.validateKeySearch(obj.getObjectCode())+"%");
		queryCount.setParameter("objectCode", "%"+ ValidateUtils.validateKeySearch(obj.getObjectCode())+"%");
		}
		if(obj.getStatus() != null && obj.getStatus() != 2L){
			query.setLong("status", obj.getStatus());
			queryCount.setLong("status", obj.getStatus());
		}
		if(obj.getParentId() != null){
			query.setLong("parentId", obj.getParentId());
			queryCount.setLong("parentId", obj.getParentId());
		}
		if(obj.getObjectTypeId() != null && obj.getObjectTypeId() != 2L){
			query.setLong("objectTypeId", obj.getObjectTypeId());
			queryCount.setLong("objectTypeId", obj.getObjectTypeId());
		}
		if(StringUtils.isNotEmpty(obj.getObjectName())){
			query.setParameter("objectName", "%"+ ValidateUtils.validateKeySearch(obj.getObjectName())+"%");
			queryCount.setParameter("objectName", "%"+ ValidateUtils.validateKeySearch(obj.getObjectName())+"%");
		}
		
		query.setFirstResult((obj.getPage().intValue()-1)*obj.getPageSize().intValue());
		query.setMaxResults(obj.getPageSize().intValue());
		obj.setTotalRecord(((BigInteger)queryCount.uniqueResult()).intValue());
		
		return query.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<ObjectsDTO> getParent() {
		StringBuilder sql = new StringBuilder("SELECT OBJECT_ID objectId," 
				+ "OBJECT_NAME objectName," + "OBJECT_CODE objectCode"
				+ " FROM objects WHERE PARENT_ID IS NULL");
		SQLQuery query = getSession().createSQLQuery(sql.toString());

		query.addScalar("objectId", new LongType());
		query.addScalar("objectName", new StringType());
		query.addScalar("objectCode", new StringType());

		query.setResultTransformer(Transformers.aliasToBean(ObjectsDTO.class));

		return query.list();
	}
	public ObjectsDTO getbycode(String code){
		StringBuilder sql = new StringBuilder("SELECT o.OBJECT_ID objectId,"
				+ "o.OBJECT_CODE objectCode,"
				+ "o.OBJECT_NAME objectName,"
				+ "o.PARENT_ID parentId,"
				+ "o.ORD ord,"
				+ "o.OBJECT_URL objectUrl,"
				+ "o.DESCRIPTION description,"
				+ "o.OBJECT_TYPE_ID objectTypeId,"
				+ "o.STATUS status,"
				+ "o.CREATE_USER createUser,"
				+ "o.CREATE_DATE createDate"
				+ " FROM objects o WHERE upper(OBJECT_CODE)=upper(:objectCode)");
		SQLQuery query= getSession().createSQLQuery(sql.toString());
		

		query.addScalar("objectId", new LongType());
		query.addScalar("parentId", new LongType());
		query.addScalar("status", new LongType());
		query.addScalar("ord", new LongType());
		query.addScalar("objectUrl", new StringType());
		query.addScalar("objectName", new StringType());
		query.addScalar("description", new StringType());
		query.addScalar("objectTypeId", new LongType());
		query.addScalar("objectCode", new StringType());
		query.addScalar("createUser", new StringType());
		query.addScalar("createDate", new DateType());
		query.setResultTransformer(Transformers.aliasToBean(ObjectsDTO.class));
		
		query.setParameter("objectCode", code);
		
		return (ObjectsDTO) query.uniqueResult();
	}
	
	public List<RoleObjectDTO> getRoleObjectByObjectId(Long objectId){
		StringBuilder sql = new StringBuilder("SELECT OBJECT_ID objectId," 
				+ "ROLE_ID roleId," 
				+ "IS_ACTIVE isActive"
				+ " FROM role_object WHERE OBJECT_ID =:objectId");
		SQLQuery query = getSession().createSQLQuery(sql.toString());

		query.addScalar("objectId", new LongType());
		query.addScalar("roleId", new LongType());
		query.addScalar("isActive", new LongType());
		
		query.setParameter("objectId", objectId);

		query.setResultTransformer(Transformers.aliasToBean(RoleObjectDTO.class));

		return query.list();
	}
	public List<RoleObjectDTO> doSearchObjectRole(RoleObjectDTO obj){
    	StringBuilder sql = new StringBuilder("select o.OBJECT_CODE objectCode,"
    			+ "o.OBJECT_NAME objectName,"
    			+ "o.OBJECT_URL objectUrl,"
    			+ "o.ORD ord,"
    			+ "o.OBJECT_TYPE_ID objectTypeId,"
    			+ "ro.ROLE_ID roleId,"
    			+ "ro.OBJECT_ID objectId "
    			+ "from objects o "  
    			+"inner join role_object ro on o.OBJECT_ID = ro.OBJECT_ID "
    			+"inner join roles r on r.ROLE_ID=ro.ROLE_ID " 
    			+"where ro.ROLE_ID = :roleId " 
    			+"and ro.IS_ACTIVE=1 "); 
    		
    	sql.append(" ORDER BY o.OBJECT_CODE");
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");
		sqlCount.append(" as objects;");
		SQLQuery query= getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount=getSession().createSQLQuery(sqlCount.toString());
		

		query.addScalar("objectCode", new StringType());
		query.addScalar("objectName", new StringType());
		query.addScalar("objectUrl", new StringType());
		query.addScalar("ord", new LongType());
		query.addScalar("objectTypeId", new LongType());
		query.addScalar("roleId", new LongType());
		query.addScalar("objectId", new LongType());

		
		query.setResultTransformer(Transformers.aliasToBean(RoleObjectDTO.class));
		
		if(obj.getRoleId() != null){
			query.setLong("roleId", obj.getRoleId());
			queryCount.setLong("roleId", obj.getRoleId());
			}
		
		obj.setTotalRecord(((BigInteger) queryCount.uniqueResult()).intValue());
		
		return query.list();     	   	
    }
	@SuppressWarnings("unchecked")
	public List<ObjectsDTO> getForAutoCompleteObject(ObjectsDTO obj) {
		String sql = "select o.OBJECT_CODE objectCode," + 
				"o.OBJECT_NAME objectName," + 
				"o.OBJECT_URL objectUrl," + 
				"o.ORD ord," + 
				"o.OBJECT_TYPE_ID objectTypeId," + 
				"o.OBJECT_ID objectId "
				+ " from objects o "
				+ "WHERE o.status=:status and o.object_id NOT IN"
				+ "(SELECT ro.OBJECT_ID FROM role_object ro where ro.ROLE_ID =:roleId) ";
		
		StringBuilder stringBuilder = new StringBuilder(sql);
		
			if(StringUtils.isNotEmpty(obj.getObjectName())){
			stringBuilder.append(" AND (upper(o.OBJECT_NAME) LIKE upper(:name) escape '&' OR upper(o.OBJECT_CODE) LIKE upper(:name) escape '&')");
			}
		
		stringBuilder.append(" ORDER BY o.OBJECT_CODE");
		stringBuilder.append(" LIMIT 10 ");
		
		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		
		query.addScalar("objectCode", new StringType());
		query.addScalar("objectName", new StringType());
		query.addScalar("objectUrl", new StringType());
		query.addScalar("ord", new LongType());
		query.addScalar("objectTypeId", new LongType());
		query.addScalar("objectId", new LongType());
	
		query.setResultTransformer(Transformers.aliasToBean(ObjectsDTO.class));

		if (StringUtils.isNotEmpty(obj.getObjectName())) {
			query.setParameter("name", "%" + ValidateUtils.validateKeySearch(obj.getObjectName()) + "%");
		}
		
		if(obj.getRoleId()!=null) {
			query.setLong("roleId", obj.getRoleId());
		}
		query.setParameter("status",1L);
		return query.list();
	}
}
