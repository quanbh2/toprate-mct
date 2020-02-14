package com.toprate.mct.dao;

import java.math.BigInteger;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.toparte.mct.dto.SalonDTO;
import com.toprate.base.utils.ValidateUtils;
import com.toprate.mct.bo.Salon;
import com.viettel.service.base.dao.BaseFWDAOImpl;

@Repository("salonDAO")
public class SalonDAO extends BaseFWDAOImpl<Salon, Long> {

	public SalonDAO() {
		this.model = new Salon();
	}

	public SalonDAO(Session session) {
		this.session = session;
	}

	public List<SalonDTO> countSalonByProvince() {
		StringBuilder sql = new StringBuilder("SELECT p.id provinceId," + "count(m.id) countByProvinceId, "
				+ "p.name provinceName, " + "p.code provinceCode," + "p.regions provinceRegions"
				+ " FROM salon m RIGHT JOIN province p ON m.province_id = p.id " + " WHERE 1=1 AND p.is_delete != 1");
		sql.append(" GROUP BY p.id ORDER BY p.id");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("provinceId", new LongType());
		query.addScalar("countByProvinceId", new LongType());
		query.addScalar("provinceName", new StringType());
		query.addScalar("provinceCode", new StringType());
		query.addScalar("provinceRegions", new LongType());

		query.setResultTransformer(Transformers.aliasToBean(SalonDTO.class));
		return query.list();
	}

	public List<SalonDTO> doSearch(SalonDTO obj) {
		StringBuilder sql = new StringBuilder("SELECT m.id id," + "m.code code," + "m.name name,"
				+ "m.description description," + "m.address address," + "m.email email," + "m.phone phone,"
				+ "m.content content," + " m.user_id user_id," + " m.province_id provinceId, " + "m.status status,"
				+ " p.code provinceCode, " + " p.name provinceName " 
				+ " FROM salon m INNER JOIN province p ON m.province_id =  p.id " + " WHERE 1=1 AND m.is_delete != 1");

		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			sql.append(" AND (" + "(upper(m.code) LIKE upper(:keySearch) escape '&' ) "
					+ "OR (upper(m.description) LIKE upper(:keySearch) escape '&') "
					+ "OR (upper(m.address) LIKE upper(:keySearch) escape '&') "
					+ "OR (upper(m.phone) LIKE upper(:keySearch) escape '&') "
					+ "OR (upper(m.content) LIKE upper(:keySearch) escape '&') "
					+ "OR (upper(p.name) LIKE upper(:keySearch) escape '&') "
					+ "OR (upper(m.name) LIKE upper(:keySearch) escape '&')" + ")");
		}
		if (obj.getProvinceCode() != null) {
			sql.append(" AND p.code = :provinceCode ");
		}

		if (obj.getProvinceId() != null) {
			sql.append(" AND m.province_id = :provinceId ");
		}

		if (obj.getStatus() != null) {
			sql.append(" AND m.status = :status ");
		}

		if (obj.getUserId() != null) {
			sql.append(" AND m.user_id = :userId ");
		}

		sql.append(" ORDER BY m.id");

		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");
		sqlCount.append(" as salon;");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());

		query.addScalar("id", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("name", new StringType());
		query.addScalar("description", new StringType());
		query.addScalar("content", new StringType());
		query.addScalar("provinceName", new StringType());
		query.addScalar("provinceCode", new StringType());
		query.addScalar("address", new StringType());
		query.addScalar("email", new StringType());
		query.addScalar("phone", new StringType());
		query.addScalar("status", new LongType());

		query.setResultTransformer(Transformers.aliasToBean(SalonDTO.class));

		if (obj.getUserId() != null) {
			query.setParameter("userId", obj.getUserId());
			queryCount.setParameter("userId", obj.getUserId());
		}

		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			query.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
			queryCount.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
		}

		if (obj.getProvinceCode() != null) {
			query.setParameter("provinceCode", obj.getProvinceCode());
			queryCount.setParameter("provinceCode", obj.getProvinceCode());
		}

		if (obj.getProvinceId() != null) {
			query.setParameter("provinceId", obj.getProvinceId());
			queryCount.setParameter("provinceId", obj.getProvinceId());
		}

		if (obj.getStatus() != null) {
			query.setParameter("status", obj.getStatus());
			queryCount.setParameter("status", obj.getStatus());
		}

		query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
		query.setMaxResults(obj.getPageSize().intValue());
		obj.setTotalRecord(((BigInteger) queryCount.uniqueResult()).intValue());
		return query.list();
	}

	@Transactional
	public void delete(SalonDTO obj) {
		Session session = getSession();
		StringBuilder sql = new StringBuilder("delete from salon where id = :id ");
		SQLQuery query = session.createSQLQuery(sql.toString());
		query.setParameter("id", obj.getId());
		query.executeUpdate();
	}

	@Transactional
	public void save(SalonDTO obj) {
		Session session = getSession();
		session.save(obj.toModel());
	}

	@Transactional
	public void saveOrUpdate(Salon salon) {
		Session session = getSession();
		session.saveOrUpdate(salon);

	}
}
