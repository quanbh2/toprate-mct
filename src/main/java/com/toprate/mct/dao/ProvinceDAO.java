package com.toprate.mct.dao;

import java.math.BigInteger;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import com.toparte.mct.dto.ManufacturerDTO;
import com.toparte.mct.dto.ProvinceDTO;
import com.toprate.base.utils.ValidateUtils;
import com.toprate.mct.bo.Province;
import com.viettel.service.base.dao.BaseFWDAOImpl;

@Repository("provinceDAO")
public class ProvinceDAO extends BaseFWDAOImpl<Province, Long> {
	public ProvinceDAO() {
		this.model = new Province();
	}

	public ProvinceDAO(Session session) {
		this.session = session;
	}

	public List<ProvinceDTO> doSearch(ProvinceDTO obj) {
		StringBuilder sql = new StringBuilder("SELECT p.id id," 
		+ "p.code code," 
		+ "p.name name," 
		+ "p.description description,"
		+ "p.created_by createdBy," 
		+ "p.created_date createdDate," 
		+ "p.updated_date updatedDate,"
		+ "p.updated_by updatedBy," 
		+ "p.is_delete isDelete," 
		+ "p.regions regions" 
		+ " FROM province p" 
		+ " WHERE 1=1");
		if (StringUtils.isNotEmpty(obj.getCode())) {
			sql.append(" AND (upper(p.code) LIKE upper(:code) escape '&') ");
		}

		if (StringUtils.isNotEmpty(obj.getName())) {
			sql.append(" AND (upper(p.name) LIKE upper(:name) escape '&') ");
		}

		if (StringUtils.isNotEmpty(obj.getDescription())) {
			sql.append(" AND (upper(p.description) LIKE upper(:description) escape '&') ");
		}
		
		if (StringUtils.isNotEmpty(obj.getRegions())) {
			sql.append(" AND (upper(p.regions) LIKE upper(:regions) escape '&') ");
		}

		sql.append(" ORDER BY p.created_date desc");

		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");
		sqlCount.append(" as province;");

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
		query.addScalar("regions", new StringType());

		query.setResultTransformer(Transformers.aliasToBean(ProvinceDTO.class));
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
		if (StringUtils.isNotEmpty(obj.getRegions())) {
			query.setParameter("regions", "%" + ValidateUtils.validateKeySearch(obj.getRegions()) + "%");
			queryCount.setParameter("regions", "%" + ValidateUtils.validateKeySearch(obj.getRegions()) + "%");
		}
		query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
		query.setMaxResults(obj.getPageSize().intValue());
		obj.setTotalRecord(((BigInteger) queryCount.uniqueResult()).intValue());

		return query.list();
	}

	@Transactional
	public void save(ProvinceDTO obj) {
		Session session = getSession();
		session.save(obj.toModel());
	}

	public ProvinceDTO getbycode(String code) {
		StringBuilder sql = new StringBuilder("SELECT p.id id, " 
				+ "p.code code," 
				+ "p.name name," 
				+ "p.description description,"
				+ "p.created_by createdBy," 
				+ "p.created_date createdDate," 
				+ "p.updated_date updatedDate,"
				+ "p.updated_by updatedBy," 
				+ "p.is_delete isDelete," 
				+ "p.regions regions" 
				+ " FROM province p" 
				+ " WHERE 1=1 "
				+ " AND upper(CODE)=upper(:code)");
		
		SQLQuery query= getSession().createSQLQuery(sql.toString());
		query.addScalar("id", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("name", new StringType());
		query.addScalar("description", new StringType());
		query.addScalar("createdBy", new LongType());
		query.addScalar("createdDate", new DateType());
		query.addScalar("updatedDate", new DateType());
		query.addScalar("updatedBy", new LongType());
		query.addScalar("isDelete", new LongType());
		query.addScalar("regions", new StringType());
		query.setResultTransformer(Transformers.aliasToBean(ProvinceDTO.class));
		
		query.setParameter("code", code);
		
		return (ProvinceDTO) query.uniqueResult();
	}

	@Transactional
	public void delete(ProvinceDTO obj) {
		Session session = getSession();
		StringBuilder sql = new StringBuilder("DELETE from province where  id = :id "); 
		SQLQuery query= session.createSQLQuery(sql.toString());
		query.setParameter("id", obj.getId());
		query.executeUpdate();
	}

	public void update(ProvinceDTO obj) {
		Session session = getSession();
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE ");
		sql.append(" 	province p ");
		sql.append(" SET ");
		sql.append(" 	p.id = :id ");
		
		if (StringUtils.isNotEmpty(obj.getCode())) {
			sql.append(" 	,p.code = :code ");
		}

		if (StringUtils.isNotEmpty(obj.getName())) {
			sql.append(" 	,p.name = :name ");
		}
		if (StringUtils.isNotEmpty(obj.getDescription())) {
			sql.append(" 	,p.description = :description ");
		}
		
		if (StringUtils.isNotEmpty(obj.getRegions())) {
			sql.append(" 	,p.regions = :regions ");
		}
		
		sql.append(" WHERE ");
		sql.append(" 	p.id = :id ");
		SQLQuery query= session.createSQLQuery(sql.toString());
		
		if (StringUtils.isNotEmpty(obj.getCode())) {
			query.setParameter("code", obj.getCode());
		}

		if (StringUtils.isNotEmpty(obj.getName())) {
			query.setParameter("name", obj.getName());
		}
		if (StringUtils.isNotEmpty(obj.getDescription())) {
			query.setParameter("description", obj.getDescription());
		}
		if (StringUtils.isNotEmpty(obj.getRegions())) {
			query.setParameter("regions", obj.getRegions());
		}
		
		query.setParameter("id", obj.getId());
		query.executeUpdate();
		
	}

	public List<ProvinceDTO> getProvinceForAutoComplete(ManufacturerDTO obj) {
		StringBuilder sql = new StringBuilder(
				"SELECT p.id id " 
						+ " ,p.code code " 
						+ " ,p.name name " 
						+ " FROM province p" 
						+ " WHERE 1=1 ");
		if (StringUtils.isNotEmpty(obj.getCode())) {
			sql.append(" AND (upper(p.code) LIKE upper(:code) escape '&') ");
		}

		if (StringUtils.isNotEmpty(obj.getName())) {
			sql.append(" AND (upper(p.name) LIKE upper(:name) escape '&') ");
		}
		
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("id", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("name", new StringType());
		query.setResultTransformer(Transformers.aliasToBean(ProvinceDTO.class));
		if (StringUtils.isNotEmpty(obj.getCode())) {
			query.setParameter("code", "%" + ValidateUtils.validateKeySearch(obj.getCode()) + "%");
		}

		if (StringUtils.isNotEmpty(obj.getName())) {
			query.setParameter("name", "%" + ValidateUtils.validateKeySearch(obj.getName()) + "%");
		}
		return query.list();
	}
	public List<ProvinceDTO> doSearchByRegions(ProvinceDTO obj) {
		StringBuilder sql = new StringBuilder("SELECT p.id id," 
		+ "p.code code," 
		+ "p.name name," 
		+ "p.description description,"
		+ "p.created_by createdBy," 
		+ "p.created_date createdDate," 
		+ "p.updated_date updatedDate,"
		+ "p.updated_by updatedBy," 
		+ "p.is_delete isDelete," 
		+ "p.regions regions" 
		+ " FROM province p" 
		+ " WHERE 1=1 AND p.is_delete != 1");
		
		if (StringUtils.isNotEmpty(obj.getRegions())) {
			sql.append(" AND (upper(p.regions) LIKE upper(:regions) escape '&') ");
		}

		sql.append(" ORDER BY p.id");


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
		query.addScalar("regions", new StringType());

		query.setResultTransformer(Transformers.aliasToBean(ProvinceDTO.class));
		
		if (StringUtils.isNotEmpty(obj.getRegions())) {
			query.setParameter("regions", "%" + ValidateUtils.validateKeySearch(obj.getRegions()) + "%");
		}

		return query.list();
	}
}
