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

import com.toparte.mct.dto.ModelDTO;
import com.toprate.base.utils.ValidateUtils;
import com.toprate.mct.bo.Model;
import com.viettel.service.base.dao.BaseFWDAOImpl;

@Repository("modelDAO")
public class ModelDAO extends BaseFWDAOImpl<Model, Long> {
	public ModelDAO() {
		this.model = new Model();
	}

	public ModelDAO(Session session) {
		this.session = session;
	}

	public List<ModelDTO> doSearch(ModelDTO obj) {
		StringBuilder sql = new StringBuilder("SELECT m.id id," + "m.code code," + "m.name name,"
				+ "m.description description," + "m.created_by createdBy," + "m.created_date createdDate,"
				+ "m.updated_date updatedDate," + "m.updated_by updatedBy," + "m.is_delete isDelete,"
				+ "ma.name manufacturerName," + "ty.name typeMachineName" + " FROM model m"
				+ " INNER JOIN manufacturer ma " + " ON m.manufacturer_id = ma.id " + " INNER JOIN type_machine ty "
				+ " ON ty.id = m.type_machine_id " + " WHERE 1=1");
		if (obj.getId() != null) {
			sql.append(" AND m.id = :id ");
		}
		if (StringUtils.isNotEmpty(obj.getCode())) {
			sql.append(" AND (upper(m.code) LIKE upper(:code) escape '&') ");
		}

		if (StringUtils.isNotEmpty(obj.getName())) {
			sql.append(" AND (upper(m.name) LIKE upper(:name) escape '&') ");
		}

		if (StringUtils.isNotEmpty(obj.getDescription())) {
			sql.append(" AND (upper(m.description) LIKE upper(:description) escape '&') ");
		}

		if (obj.getManufacturerId() != null) {
			sql.append(" AND m.manufacturer_id = :manufacturerId ");
		}
		if (obj.getTypeMachineId() != null) {
			sql.append(" AND m.type_machine_id = :typeMachineId ");
		}

		sql.append(" ORDER BY m.created_date desc");

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
		query.addScalar("manufacturerName", new StringType());
		query.addScalar("typeMachineName", new StringType());

		query.setResultTransformer(Transformers.aliasToBean(ModelDTO.class));
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
		if (obj.getManufacturerId() != null) {
			query.setParameter("manufacturerId", obj.getManufacturerId());
			queryCount.setParameter("manufacturerId", obj.getManufacturerId());
		}
		if (obj.getTypeMachineId() != null) {
			query.setParameter("typeMachineId", obj.getTypeMachineId());
			queryCount.setParameter("typeMachineId", obj.getTypeMachineId());
		}
		if (obj.getId() != null) {
			query.setParameter("id", obj.getId());
			queryCount.setParameter("id", obj.getId());
		}
		query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
		query.setMaxResults(obj.getPageSize().intValue());
		obj.setTotalRecord(((BigInteger) queryCount.uniqueResult()).intValue());

		return query.list();
	}

	public List<ModelDTO> getModelForAutoComplete(ModelDTO obj) {
		StringBuilder sql = new StringBuilder("SELECT " + "m.id id ," + "m.code code ," + "m.name name ,"
				+ "m.description description ," + "m.created_date createdDate ," + "m.manufacturer_id manufacturerId ,"
				+ "m.created_by createdBy ," + "m.type_machine_id typeMachineId " + "FROM model m " + "WHERE 1=1 ");
		if (StringUtils.isNotEmpty(obj.getCode())) {
			sql.append(" AND (upper(m.code) LIKE upper(:code) escape '&') ");
		}

		if (StringUtils.isNotEmpty(obj.getName())) {
			sql.append(" AND (upper(m.name) LIKE upper(:name) escape '&') ");
		}

		if (obj.getManufacturerId() != null) {
			sql.append(" AND m.manufacturer_id = :manufacturerId ");
		}

		if (obj.getTypeMachineId() != null) {
			sql.append(" AND m.type_machine_id = :typeMachineId ");
		}
		sql.append(" limit 10");
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("id", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("name", new StringType());

		query.addScalar("description", new StringType());
		query.addScalar("createdDate", new DateType());
		query.addScalar("manufacturerId", new LongType());
		query.addScalar("createdBy", new LongType());
		query.addScalar("typeMachineId", new LongType());

		if (StringUtils.isNotEmpty(obj.getCode())) {
			query.setParameter("code", "%" + ValidateUtils.validateKeySearch(obj.getCode()) + "%");
		}

		if (StringUtils.isNotEmpty(obj.getName())) {
			query.setParameter("name", "%" + ValidateUtils.validateKeySearch(obj.getName()) + "%");
		}

		if (obj.getManufacturerId() != null) {
			query.setParameter("manufacturerId", obj.getManufacturerId());
		}

		if (obj.getTypeMachineId() != null) {
			query.setParameter("typeMachineId", obj.getTypeMachineId());
		}

		query.setResultTransformer(Transformers.aliasToBean(ModelDTO.class));
		return query.list();

	}

	@Transactional
	public void delete(ModelDTO obj) {
		Session session = getSession();
		StringBuilder sql = new StringBuilder("delete from model where id = :id ");
		SQLQuery query = session.createSQLQuery(sql.toString());
		query.setParameter("id", obj.getId());
		query.executeUpdate();
	}

	@Transactional
	public void saveOrUpdate(Model obj) {
		Session session = getSession();
		session.save(obj);
	}

	@SuppressWarnings("unchecked")
	public List<ModelDTO> getAll() {
		StringBuilder sql = new StringBuilder("SELECT p.id id, " + "p.code code," + "upper(p.name) name,"
				+ "m.id manufacturerId," + "m.code manufacturerCode," + "upper(m.name) manufacturerName,  "
				+ "m.file_path manufacturerFilePath  " + " FROM manufacturer m "
				+ " LEFT JOIN model p ON m.id=p.manufacturer_id AND (m.is_delete =0 or m.is_delete is null)"
				+ " WHERE (p.is_delete =0 or p.is_delete is null) " + " ORDER BY m.id ,p.id ");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("id", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("name", new StringType());
		query.addScalar("manufacturerId", new LongType());
		query.addScalar("manufacturerName", new StringType());
		query.addScalar("manufacturerFilePath", new StringType());
		query.addScalar("manufacturerCode", new StringType());
		query.setResultTransformer(Transformers.aliasToBean(ModelDTO.class));

		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<ModelDTO> getAllTypeMachine() {
		StringBuilder sql = new StringBuilder("SELECT p.id id, " + "p.code code," + "upper(p.name) name,"
				+ "m.id typeMachineId," + "m.code typeMachineCode," + "m.parent_id typeMachineParentId,"
				+ "upper(m.name) typeMachineName  " + " FROM type_machine m "
				+ " LEFT JOIN model p ON m.id=p.type_machine_id AND (m.is_delete =0 or m.is_delete is null)"
				+ " WHERE (p.is_delete =0 or p.is_delete is null) " + " ORDER BY p.type_machine_id,p.id ");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("id", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("name", new StringType());
		query.addScalar("typeMachineId", new LongType());
		query.addScalar("typeMachineParentId", new LongType());
		query.addScalar("typeMachineName", new StringType());
		query.addScalar("typeMachineCode", new StringType());
		query.setResultTransformer(Transformers.aliasToBean(ModelDTO.class));

		return query.list();
	}

	public List<ModelDTO> getByCode(String code) {
		StringBuilder sql = new StringBuilder("SELECT m.id id," + "m.code code," + "m.name name,"
				+ "m.description description," + "m.created_by createdBy," + "m.created_date createdDate,"
				+ "m.updated_date updatedDate," + "m.updated_by updatedBy," + "m.is_delete isDelete,"
				+ "ma.name manufacturerName" + " FROM model m" + " INNER JOIN manufacturer ma "
				+ " ON m.manufacturer_id = ma.id " + " WHERE 1=1");
		sql.append(" AND m.code = :code  ");

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
		query.addScalar("manufacturerName", new StringType());

		query.setResultTransformer(Transformers.aliasToBean(ModelDTO.class));
		query.setParameter("code", code);

		return query.list();
	}
}
