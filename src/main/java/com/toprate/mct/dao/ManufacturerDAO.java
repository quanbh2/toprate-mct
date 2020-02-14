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

import com.toparte.mct.dto.ManufacturerDTO;
import com.toprate.base.utils.ValidateUtils;
import com.toprate.mct.bo.Manufacturer;
import com.viettel.service.base.dao.BaseFWDAOImpl;

@Repository("manufacturerDAO")
public class ManufacturerDAO extends BaseFWDAOImpl<Manufacturer, Long> {
	public ManufacturerDAO() {
		this.model = new Manufacturer();
	}

	public ManufacturerDAO(Session session) {
		this.session = session;
	}

	public List<ManufacturerDTO> doSearch(ManufacturerDTO obj) {
		StringBuilder sql = new StringBuilder(
				"SELECT m.id id," 
						+ "m.code code," 
						+ "m.name name," 
						+ "m.description description,"
						+ "m.created_by createdBy," 
						+ "m.created_date createdDate," 
						+ "m.updated_date updatedDate,"
						+ "m.updated_by updatedBy," 
						+ "m.file_path filePath," 
						+ "m.is_delete isDelete" 
						+ " FROM manufacturer m" 
						+ " WHERE 1=1 ");
		if (StringUtils.isNotEmpty(obj.getCode())) {
			sql.append(" AND (upper(m.code) LIKE upper(:code) escape '&') ");
		}

		if (StringUtils.isNotEmpty(obj.getName())) {
			sql.append(" AND (upper(m.name) LIKE upper(:name) escape '&') ");
		}

		if (StringUtils.isNotEmpty(obj.getDescription())) {
			sql.append(" AND (upper(m.description) LIKE upper(:description) escape '&') ");
		}

		sql.append(" ORDER BY m.created_date desc");

		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");
		sqlCount.append(" as manufacturer;");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());

		query.addScalar("id", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("name", new StringType());
		query.addScalar("description", new StringType());
		query.addScalar("filePath", new StringType());
		query.addScalar("createdBy", new LongType());
		query.addScalar("createdDate", new DateType());
		query.addScalar("updatedDate", new DateType());
		query.addScalar("updatedBy", new LongType());
		query.addScalar("isDelete", new LongType());
		query.addScalar("filePath", new StringType());

		query.setResultTransformer(Transformers.aliasToBean(ManufacturerDTO.class));
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
	public void delete(ManufacturerDTO obj) {
		Session session = getSession();
		StringBuilder sql = new StringBuilder("delete from manufacturer where id = :id ");
		SQLQuery query = session.createSQLQuery(sql.toString());
		query.setParameter("id", obj.getId());
		query.executeUpdate();
	}


	@Transactional
	public void saveOrUpdate(Manufacturer obj) {
		Session session = getSession();
		session.save(obj);
	}

	public List<ManufacturerDTO> getManufacturerForAutoComplete(ManufacturerDTO obj) {
		StringBuilder sql = new StringBuilder(
				"SELECT m.id id " 
						+ " ,m.code code " 
						+ " ,m.name name " 
						+ " FROM manufacturer m" 
						+ " WHERE 1=1 ");
		if (StringUtils.isNotEmpty(obj.getCode())) {
			sql.append(" AND (upper(m.code) LIKE upper(:code) escape '&') ");
		}

		if (StringUtils.isNotEmpty(obj.getName())) {
			sql.append(" AND (upper(m.name) LIKE upper(:name) escape '&') ");
		}
		
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("id", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("name", new StringType());
		query.setResultTransformer(Transformers.aliasToBean(ManufacturerDTO.class));
		if (StringUtils.isNotEmpty(obj.getCode())) {
			query.setParameter("code", "%" + ValidateUtils.validateKeySearch(obj.getCode()) + "%");
		}

		if (StringUtils.isNotEmpty(obj.getName())) {
			query.setParameter("name", "%" + ValidateUtils.validateKeySearch(obj.getName()) + "%");
		}
		return query.list();
		
	}
	
	public List<ManufacturerDTO> getByCode(String code) {
		StringBuilder sql = new StringBuilder(
				"SELECT m.id id," 
						+ "m.code code," 
						+ "m.name name," 
						+ "m.description description,"
						+ "m.created_by createdBy," 
						+ "m.created_date createdDate," 
						+ "m.updated_date updatedDate,"
						+ "m.updated_by updatedBy," 
						+ "m.is_delete isDelete" 
						+ " FROM manufacturer m" 
						+ " WHERE 1=1 ");
			sql.append(" AND m.code = :code ");


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

		query.setResultTransformer(Transformers.aliasToBean(ManufacturerDTO.class));
			query.setParameter("code", code);

		return query.list();
	}
	
	
	public List<ManufacturerDTO> getAll() {
		StringBuilder sql = new StringBuilder(
				"SELECT m.id id," 
						+ "m.code code," 
						+ "m.name name," 
						+ "m.description description,"
						+ "m.created_by createdBy," 
						+ "m.created_date createdDate," 
						+ "m.updated_date updatedDate,"
						+ "m.updated_by updatedBy," 
						+ "m.file_path filePath," 
						+ "m.is_delete isDelete" 
						+ " FROM manufacturer m order by m.id ASC" );

		SQLQuery query = getSession().createSQLQuery(sql.toString());

		query.addScalar("id", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("name", new StringType());
		query.addScalar("description", new StringType());
		query.addScalar("filePath", new StringType());
		query.addScalar("createdBy", new LongType());
		query.addScalar("createdDate", new DateType());
		query.addScalar("updatedDate", new DateType());
		query.addScalar("updatedBy", new LongType());
		query.addScalar("isDelete", new LongType());
		query.setResultTransformer(Transformers.aliasToBean(ManufacturerDTO.class));
		

		return query.list();
	}
	
	public List<ManufacturerDTO> getAllForCombobox() {
		StringBuilder sql = new StringBuilder(
				"SELECT m.id id," 
						+ "m.code code," 
						+ "m.name name," 
						+ "m.description description,"
						+ "m.created_by createdBy," 
						+ "m.created_date createdDate," 
						+ "m.updated_date updatedDate,"
						+ "m.updated_by updatedBy," 
						+ "m.file_path filePath," 
						+ "m.is_delete isDelete" 
						+ " FROM manufacturer m order by m.name" );

		SQLQuery query = getSession().createSQLQuery(sql.toString());

		query.addScalar("id", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("name", new StringType());
		query.addScalar("description", new StringType());
		query.addScalar("filePath", new StringType());
		query.addScalar("createdBy", new LongType());
		query.addScalar("createdDate", new DateType());
		query.addScalar("updatedDate", new DateType());
		query.addScalar("updatedBy", new LongType());
		query.addScalar("isDelete", new LongType());
		query.setResultTransformer(Transformers.aliasToBean(ManufacturerDTO.class));
		

		return query.list();
	}
}
