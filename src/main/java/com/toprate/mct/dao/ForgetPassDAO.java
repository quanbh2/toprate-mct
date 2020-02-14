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

import com.toparte.mct.dto.ForgetPassDTO;
import com.toparte.mct.dto.ResultDTO;
import com.toprate.mct.bo.ForgetPass;
import com.viettel.service.base.dao.BaseFWDAOImpl;

@Repository("forgetPassDAO")
public class ForgetPassDAO extends BaseFWDAOImpl<ForgetPass, Long> {

	public ForgetPassDAO() {
		this.model = new ForgetPass();
	}

	public ForgetPassDAO(Session session) {
		this.session = session;
	}

	@SuppressWarnings("unchecked")
	public List<ForgetPassDTO> doSearch(ForgetPassDTO obj) {
		StringBuilder sql = new StringBuilder("SELECT m.id id," + "m.full_name fullName ," + "m.user_name userName ,"
				+ "m.status status," + "m.content content," + "m.email_address emailAddress," + "m.phone phone,"
				+ "m.created_date createdDate," + "m.address address," + "m.is_delete isDelete" + " FROM forget_pass m "
				+ " WHERE 1=1 ");

		if (obj.getUserName() != null) {
			sql.append(" AND m.user_name = :userName ");
		}
		if (obj.getEmailAddress() != null) {
			sql.append(" AND m.email_address = :emailAddress ");
		}
		if (obj.getPhone() != null) {
			sql.append(" AND m.phone = :phone ");
		}

		if (obj.getIsDelete() == null || obj.getIsDelete() != 1) {
			sql.append(" AND m.is_delete != 1 ");
		}

		sql.append(" ORDER BY m.created_date desc");

		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");
		sqlCount.append(" as forgetpass;");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());

		query.addScalar("id", new LongType());
		query.addScalar("fullName", new StringType());
		query.addScalar("userName", new StringType());
		query.addScalar("status", new LongType());
		query.addScalar("createdDate", new DateType());
		query.addScalar("address", new StringType());
		query.addScalar("phone", new StringType());
		query.addScalar("isDelete", new LongType());
		query.addScalar("emailAddress", new StringType());
		query.addScalar("content", new StringType());

		query.setResultTransformer(Transformers.aliasToBean(ForgetPassDTO.class));

		if (obj.getUserName() != null) {
			query.setParameter("userName", obj.getUserName());
			queryCount.setParameter("userName", obj.getUserName());
		}
		if (obj.getEmailAddress() != null) {
			query.setParameter("emailAddress", obj.getEmailAddress());
			queryCount.setParameter("emailAddress", obj.getEmailAddress());
		}
		if (obj.getPhone() != null) {
			query.setParameter("phone", obj.getPhone());
			queryCount.setParameter("phone", obj.getPhone());
		}
		query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
		query.setMaxResults(obj.getPageSize().intValue());
		obj.setTotalRecord(((BigInteger) queryCount.uniqueResult()).intValue());
		return query.list();
	}

	@Transactional
	public ResultDTO saveForgetPass(ForgetPass obj) {
		Session session = getSession();
		ResultDTO resultDTO = new ResultDTO();
		session.save(obj);
		resultDTO.setError(false);
		resultDTO.setMessage("Đã gửi yêu cầu thành công");
		resultDTO.setErrorCode(200);
		return resultDTO;
	}

	@Transactional
	public ResultDTO saveOrUpdate(ForgetPass obj) {
		Session session = getSession();
		ResultDTO resultDTO = new ResultDTO();

		if (obj.getId() != null) {
			session.update(obj);
			resultDTO.setError(false);
			resultDTO.setMessage("Đã xóa yêu cầu thành công");
			resultDTO.setErrorCode(20001);
		} else {
			session.save(obj);
			resultDTO.setError(false);
			resultDTO.setMessage("Đã gửi yêu cầu thành công");
			resultDTO.setErrorCode(20000);

		}
		return resultDTO;
	}

	@Transactional
	public void delete(ForgetPassDTO obj) {
		Session session = getSession();
		StringBuilder sql = new StringBuilder("delete from forget_pass where id = :id ");
		SQLQuery query = session.createSQLQuery(sql.toString());
		query.setParameter("id", obj.getId());
		query.executeUpdate();
	}

}
