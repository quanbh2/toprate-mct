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
import org.springframework.transaction.annotation.Transactional;

import com.toparte.mct.dto.TypeMachineDTO;
import com.toprate.base.utils.ValidateUtils;
import com.toprate.mct.bo.TypeMachine;
import com.viettel.service.base.dao.BaseFWDAOImpl;

@Repository("typeMachineDAO")
public class TypeMachineDAO extends BaseFWDAOImpl<TypeMachine, Long> {
	public TypeMachineDAO() {
		this.model = new TypeMachine();
	}

	public TypeMachineDAO(Session session) {
		this.session = session;
	}

	public List<TypeMachineDTO> doSearch(TypeMachineDTO obj) {
		StringBuilder sql = new StringBuilder(
				"SELECT t.id id," 
						+ "t.code code," 
						+ "t.name name," 
						+ "t.description description,"
						+ "t.created_by createdBy," 
						+ "t.created_date createdDate," 
						+ "t.updated_date updatedDate,"
						+ "t.updated_by updatedBy," 
						+ "t.is_delete isDelete,"
						+ "t.parent_id parentId,"
						+ "t2.name parentName " 
						+ " FROM type_machine t"
						+ " LEFT JOIN type_machine t2 on t2.id=t.parent_id " 
						+ " WHERE 1=1");
		if (StringUtils.isNotEmpty(obj.getCode())) {
			sql.append(" AND (upper(t.code) LIKE upper(:code) escape '&') ");
		}

		if (StringUtils.isNotEmpty(obj.getName())) {
			sql.append(" AND (upper(t.name) LIKE upper(:name) escape '&') ");
		}

		if (StringUtils.isNotEmpty(obj.getDescription())) {
			sql.append(" AND (upper(t.description) LIKE upper(:description) escape '&') ");
		}

		sql.append(" ORDER BY t.created_date desc");

		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");
		sqlCount.append(" as type_machine;");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());

		query.addScalar("id", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("name", new StringType());
		query.addScalar("description", new StringType());
		query.addScalar("createdBy", new LongType());
		query.addScalar("createdDate", new DateType());
		query.addScalar("updatedDate", new DateType());
		query.addScalar("updatedBy", new LongType());
		query.addScalar("isDelete", new LongType());
		query.addScalar("parentId", new LongType());
		query.addScalar("parentName", new StringType());

		query.setResultTransformer(Transformers.aliasToBean(TypeMachineDTO.class));
		if (StringUtils.isNotEmpty(obj.getCode())) {
			query.setParameter("code", "%" + ValidateUtils.validateKeySearch(obj.getCode()) + "%");
			queryCount.setParameter("code", "%" + ValidateUtils.validateKeySearch(obj.getCode()) + "%");
		}

		if (StringUtils.isNotEmpty(obj.getName())) {
			query.setParameter("name", "%" + ValidateUtils.validateKeySearch(obj.getName()) + "%");
			queryCount.setParameter("name", "%" + ValidateUtils.validateKeySearch(obj.getName()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getDescription())) {
			query.setParameter("description", "%" + ValidateUtils.validateKeySearch(obj.getDescription()) + "%");
			queryCount.setParameter("description", "%" + ValidateUtils.validateKeySearch(obj.getDescription()) + "%");
		}
		query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
		query.setMaxResults(obj.getPageSize().intValue());
		obj.setTotalRecord(((BigInteger) queryCount.uniqueResult()).intValue());

		return query.list();
	}

	@Transactional
	public void delete(TypeMachineDTO obj) {
		Session session = getSession();
		StringBuilder sql = new StringBuilder("delete from type_machine where id = :id ");
		SQLQuery query = session.createSQLQuery(sql.toString());
		query.setParameter("id", obj.getId());
		query.executeUpdate();
	}

	@Transactional
	public void save(TypeMachineDTO obj) {
		Session session = getSession();
		session.save(obj.toModel());
	}

	@Transactional
	public void saveOrUpdate(TypeMachine typeMachine) {
		Session session = getSession();
		session.saveOrUpdate(typeMachine);

	}
	
	
	public List<TypeMachineDTO> getByCode(String code) {
		StringBuilder sql = new StringBuilder(
				"SELECT t.id id," 
						+ "t.code code," 
						+ "t.name name," 
						+ "t.description description,"
						+ "t.created_by createdBy," 
						+ "t.created_date createdDate," 
						+ "t.updated_date updatedDate,"
						+ "t.updated_by updatedBy," 
						+ "t.is_delete isDelete" 
						+ " FROM type_machine t" 
						+ " WHERE 1=1");
			sql.append(" AND t.code = :code ");


		SQLQuery query = getSession().createSQLQuery(sql.toString());

		query.addScalar("id", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("name", new StringType());
		query.addScalar("description", new StringType());
		query.addScalar("createdBy", new LongType());
		query.addScalar("createdDate", new DateType());
		query.addScalar("updatedDate", new DateType());
		query.addScalar("updatedBy", new LongType());
		query.addScalar("isDelete", new LongType());

		query.setResultTransformer(Transformers.aliasToBean(TypeMachineDTO.class));
		query.setParameter("code",code);
		
		return query.list();

	}
	
	public List<TypeMachineDTO> getParentByCode(String code) {
		StringBuilder sql = new StringBuilder(
				"SELECT t.id id," 
						+ "t.code code," 
						+ "t.name name," 
						+ "t.description description,"
						+ "t.created_by createdBy," 
						+ "t.created_date createdDate," 
						+ "t.updated_date updatedDate,"
						+ "t.updated_by updatedBy," 
						+ "t.is_delete isDelete" 
						+ " FROM type_machine t" 
						+ " WHERE 1=1 AND t.parent_id is null");
			sql.append(" AND t.code = :code ");


		SQLQuery query = getSession().createSQLQuery(sql.toString());

		query.addScalar("id", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("name", new StringType());
		query.addScalar("description", new StringType());
		query.addScalar("createdBy", new LongType());
		query.addScalar("createdDate", new DateType());
		query.addScalar("updatedDate", new DateType());
		query.addScalar("updatedBy", new LongType());
		query.addScalar("isDelete", new LongType());

		query.setResultTransformer(Transformers.aliasToBean(TypeMachineDTO.class));
		query.setParameter("code",code);
		
		return query.list();

	}

	public List<TypeMachineDTO> getTypeMachineForAutoComplete(TypeMachineDTO obj) {
		StringBuilder sql = new StringBuilder(
				"SELECT t.id id " 
						+ " ,t.code code " 
						+ " ,t.name name " 
						+ " FROM type_machine t" 
						+ " WHERE 1=1 ");
		if (StringUtils.isNotEmpty(obj.getCode())) {
			sql.append(" AND (upper(t.code) LIKE upper(:code) escape '&') ");
		}

		if (StringUtils.isNotEmpty(obj.getName())) {
			sql.append(" AND (upper(t.name) LIKE upper(:name) escape '&') ");
		}
		
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("id", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("name", new StringType());
		query.setResultTransformer(Transformers.aliasToBean(TypeMachineDTO.class));
		if (StringUtils.isNotEmpty(obj.getCode())) {
			query.setParameter("code", "%" + ValidateUtils.validateKeySearch(obj.getCode()) + "%");
		}

		if (StringUtils.isNotEmpty(obj.getName())) {
			query.setParameter("name", "%" + ValidateUtils.validateKeySearch(obj.getName()) + "%");
		}
		return query.list();
	}
	
	public List<TypeMachineDTO> getTypeMachineParentForAutoComplete(TypeMachineDTO obj) {
		StringBuilder sql = new StringBuilder(
				"SELECT t.id id " 
						+ " ,t.code code " 
						+ " ,t.name name " 
						+ " FROM type_machine t" 
						+ " WHERE 1=1 AND t.parent_id is null");
		if (StringUtils.isNotEmpty(obj.getCode())) {
			sql.append(" AND (upper(t.code) LIKE upper(:code) escape '&') ");
		}

		if (StringUtils.isNotEmpty(obj.getName())) {
			sql.append(" AND (upper(t.name) LIKE upper(:name) escape '&') ");
		}
		
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("id", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("name", new StringType());
		query.setResultTransformer(Transformers.aliasToBean(TypeMachineDTO.class));
		if (StringUtils.isNotEmpty(obj.getCode())) {
			query.setParameter("code", "%" + ValidateUtils.validateKeySearch(obj.getCode()) + "%");
		}

		if (StringUtils.isNotEmpty(obj.getName())) {
			query.setParameter("name", "%" + ValidateUtils.validateKeySearch(obj.getName()) + "%");
		}
		return query.list();
	}
	
	public List<TypeMachineDTO> getAll() {
		StringBuilder sql = new StringBuilder(
				"SELECT t.id id," 
						+ "t.code code," 
						+ "t.name name," 
						+ "t.description description,"
						+ "t.created_by createdBy," 
						+ "t.created_date createdDate," 
						+ "t.updated_date updatedDate,"
						+ "t.updated_by updatedBy," 
						+ "t.parent_id parentId," 
						+ "t.is_delete isDelete" 
						+ " FROM type_machine t" );


		SQLQuery query = getSession().createSQLQuery(sql.toString());

		query.addScalar("id", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("name", new StringType());
		query.addScalar("description", new StringType());
		query.addScalar("createdBy", new LongType());
		query.addScalar("createdDate", new DateType());
		query.addScalar("updatedDate", new DateType());
		query.addScalar("updatedBy", new LongType());
		query.addScalar("isDelete", new LongType());
		query.addScalar("parentId", new LongType());

		query.setResultTransformer(Transformers.aliasToBean(TypeMachineDTO.class));
		
		return query.list();

	}
}
