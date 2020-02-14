package com.toprate.mct.dao;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.toparte.mct.dto.SaveMctDTO;
import com.toprate.mct.bo.SaveMct;
import com.viettel.service.base.dao.BaseFWDAOImpl;

@Repository("SaveMctDAO")
public class SaveMctDAO extends BaseFWDAOImpl<SaveMct, Long> {

	public SaveMctDAO() {
		this.model = new SaveMct();
	}

	public SaveMctDAO(Session session) {
		this.session = session;
	}

	@Transactional
	public void saveMct(SaveMct obj) {
		Session session = getSession();
		session.save(obj);
	}

	public void deleteMct(SaveMctDTO obj) {
		Session session = getSession();
		StringBuilder sql = new StringBuilder("delete from save_mct where id = :id");
		SQLQuery query = session.createSQLQuery(sql.toString());
		query.setParameter("id", obj.getId());
		query.executeUpdate();
	}

	public List<SaveMctDTO> checkSell4Rent(SaveMctDTO obj) {
		StringBuilder sql = new StringBuilder("" + "SELECT" + "	s.id id," + "	s.create_date createDate "
				+ " FROM save_mct AS s " + " WHERE 1 = 1 ");

		if (obj.getCreateBy() != null) {
			sql.append(" AND s.create_by = :createBy");
		}
		if (obj.getSel4rentId() != null) {
			sql.append(" AND s.sel4rent_id = :sel4rentId");
		}

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addScalar("id", new LongType());
		query.addScalar("createDate", new DateType());

		if (obj.getCreateBy() != null) {
			query.setParameter("createBy", obj.getCreateBy());
		}
		if (obj.getSel4rentId() != null) {
			query.setParameter("sel4rentId", obj.getSel4rentId());
		}

		query.setResultTransformer(Transformers.aliasToBean(SaveMctDTO.class));
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<SaveMctDTO> doSearch(SaveMctDTO obj) {

		StringBuilder sql = new StringBuilder("" + "SELECT" + "	s.id id," + "	s.create_date createDate,"
				+ "	sl.title title," + "	sl.description description," + "	sl.code code," + "	sl.price price," + "	sl.type type,"
				+ "	sl.year year, " + "	(select i.path from image i where i.parent_id = sl.id limit 1) as path "
				+ " FROM save_mct AS s " + " INNER JOIN sell_4rent AS sl ON s.sel4rent_id = sl.id " + " WHERE 1 = 1 ");

		if (obj.getCreateBy() != null) {
			sql.append(" AND s.create_by = :createBy");
		}
		if (obj.getSel4rentId() != null) {
			sql.append(" AND s.sel4rent_id = :sel4rentId");
		}

		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");
		sqlCount.append("as save_mct");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());

		query.addScalar("id", new LongType());
		query.addScalar("createDate", new DateType());
		query.addScalar("title", new StringType());
		query.addScalar("description", new StringType());
		query.addScalar("path", new StringType());
		query.addScalar("code", new StringType());
		query.addScalar("type", new StringType());
		query.addScalar("price", new LongType());
		query.addScalar("year", new LongType());

		if (obj.getCreateBy() != null) {
			query.setParameter("createBy", obj.getCreateBy());
			queryCount.setParameter("createBy", obj.getCreateBy());
		}

		if (obj.getSel4rentId() != null) {
			query.setParameter("sel4rentId", obj.getSel4rentId());
			queryCount.setParameter("sel4rentId", obj.getSel4rentId());
		}

		query.setResultTransformer(Transformers.aliasToBean(SaveMctDTO.class));

		query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
		query.setMaxResults(obj.getPageSize().intValue());
		obj.setTotalRecord(((BigInteger) queryCount.uniqueResult()).intValue());

		return query.list();
	}
}
