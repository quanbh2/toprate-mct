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

import com.toparte.mct.dto.LogSearchDTO;
import com.toparte.mct.dto.ModelDTO;
import com.toprate.base.utils.ValidateUtils;
import com.toprate.mct.bo.LogSearch;
import com.viettel.service.base.dao.BaseFWDAOImpl;

@Repository("logSearchDAO")
public class LogSearchDAO extends BaseFWDAOImpl<LogSearch, Long> {
	public LogSearchDAO() {
		this.model = new LogSearch();
	}

	public LogSearchDAO(Session session) {
		this.session = session;
	}

	@SuppressWarnings("unchecked")
	public List<LogSearchDTO> doSearch(LogSearchDTO obj) {
		StringBuilder sql = new StringBuilder(
				"SELECT m.id id," + "m.content content," + "m.ip ip," + "m.type type," + "m.created_date createdDate,"
						+ "m.count count," + "m.updated_date updatedDate" + " FROM log_search m " + " WHERE 1=1 ");
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			sql.append(" AND (" + "(upper(m.content) LIKE upper(:keySearch) escape '&' ) "
					+ "OR (upper(m.type) LIKE upper(:keySearch) escape '&') "
					+ "OR (upper(m.ip) LIKE upper(:keySearch) escape '&')");
		}
		sql.append(" ORDER BY m.count desc");

		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");
		sqlCount.append(" as logsearch;");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());

		query.addScalar("id", new LongType());
		query.addScalar("content", new StringType());
		query.addScalar("ip", new StringType());
		query.addScalar("count", new LongType());
		query.addScalar("createdDate", new DateType());
		query.addScalar("updatedDate", new DateType());
		query.addScalar("type", new LongType());

		query.setResultTransformer(Transformers.aliasToBean(LogSearchDTO.class));

		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			query.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
			queryCount.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
		}

		query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
		query.setMaxResults(obj.getPageSize().intValue());
		obj.setTotalRecord(((BigInteger) queryCount.uniqueResult()).intValue());
		return query.list();

	}

	@SuppressWarnings("unchecked")
	public List<LogSearchDTO> getAllLogSearch() {
		StringBuilder sql = new StringBuilder(
				"SELECT m.id id," + "m.content content," + "m.ip ip," + "m.type type," + "m.created_date createdDate,"
						+ "m.count count," + "m.updated_date updatedDate" + " FROM log_search m " + " WHERE 1=1 ");
		sql.append(" ORDER BY m.count desc");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("id", new LongType());
		query.addScalar("content", new StringType());
		query.addScalar("ip", new StringType());
		query.addScalar("count", new LongType());
		query.addScalar("createdDate", new DateType());
		query.addScalar("updatedDate", new DateType());
		query.addScalar("type", new LongType());

		query.setResultTransformer(Transformers.aliasToBean(LogSearchDTO.class));

		return query.list();
	}

	@Transactional
	public void delete(LogSearchDTO obj) {
		Session session = getSession();
		StringBuilder sql = new StringBuilder("delete from log_search where id = :id ");
		SQLQuery query = session.createSQLQuery(sql.toString());
		query.setParameter("id", obj.getId());
		query.executeUpdate();
	}

	@Transactional
	public void save(LogSearchDTO obj) {
		Session session = getSession();
		session.save(obj.toModel());
	}

	@Transactional
	public void update(LogSearchDTO obj) {
		Session session = getSession();
		session.update(obj.toModel());
	}
}
