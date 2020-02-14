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

import com.toparte.mct.dto.JobDTO;
import com.toparte.mct.dto.ResultDTO;
import com.toprate.base.utils.ValidateUtils;
import com.toprate.mct.bo.Job;
import com.viettel.service.base.dao.BaseFWDAOImpl;

@Repository("jobDAO")
public class JobDAO extends BaseFWDAOImpl<Job, Long> {
	public JobDAO() {
		this.model = new Job();
	}

	public JobDAO(Session session) {
		this.session = session;
	}

	@SuppressWarnings("unchecked")
	public List<JobDTO> doSearch(JobDTO obj) {
		StringBuilder sql = new StringBuilder("SELECT j.id id," + "j.code code," + "j.name name,"
				+ "j.description description," + "j.created_by createdBy," + "j.created_date createdDate,"
				+ "j.count_reup countReup," + "j.count_reup_day countReupDay," + "j.reup_date reupDate,"
				+ "j.updated_date updatedDate," + "j.updated_by updatedBy," + "j.count_view countView,"
				+ "j.phone phone," + "j.status status," + "j.province_id provinceId," + "j.address address,"
				+ "j.is_delete isDelete," + "p.name provinceName" + " FROM job j" + " LEFT JOIN province p"
				+ " ON p.id = j.province_id" + " WHERE 1=1 ");
		if (StringUtils.isNotEmpty(obj.getTxtSearch())) {
			sql.append(" AND (" + "(upper(j.name) LIKE upper(:textSearch) escape '&')"
					+ "OR (upper(j.description) LIKE upper(:textSearch) escape '&') "
					+ "OR (upper(p.name) LIKE upper(:textSearch) escape '&')"
					+ "OR (upper(j.address) LIKE upper(:textSearch) escape '&')" + ")");
		}
		if (obj.getIsDelete() == null || obj.getIsDelete() != 1) {
			sql.append(" AND j.is_delete != 1 ");
		}
		if (obj.getProvinceId() != null) {
			sql.append(" AND p.id = :provinceId ");
		}

		if (obj.getCreatedBy() != null) {
			sql.append(" AND j.created_by = :createdBy ");
		}
		if (obj.getStatus() != null) {
			sql.append(" AND j.status = :status");
		}

		if (StringUtils.isNotEmpty(obj.getProvinceCode())) {
			sql.append(" AND p.code = :provinceCode ");
		}
		if (obj.getFromDate() != null && obj.getToDate() != null) {
			if (obj.getFromDate().getYear() == obj.getToDate().getYear()
					&& obj.getFromDate().getDate() == obj.getToDate().getDate()
					&& obj.getFromDate().getMonth() == obj.getToDate().getMonth()) {
				sql.append(" AND j.created_date between :fromDate and :toDate ");
			} else {
				sql.append(" AND j.created_date between :fromDate and :toDate ");
			}

		} else if (obj.getFromDate() == null && obj.getToDate() != null) {
			sql.append(" AND j.created_date <= :toDate ");
		} else if (obj.getFromDate() != null && obj.getToDate() == null) {
			sql.append(" AND j.created_date >= :fromDate ");
		}

		sql.append(" ORDER BY j.reup_date desc");

		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");
		sqlCount.append(" as job;");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());

		query.addScalar("id", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("name", new StringType());
		query.addScalar("description", new StringType());
		query.addScalar("createdBy", new LongType());
		query.addScalar("createdDate", new DateType());
		query.addScalar("updatedDate", new DateType());
		query.addScalar("reupDate", new DateType());
		query.addScalar("updatedBy", new LongType());
		query.addScalar("isDelete", new LongType());
		query.addScalar("provinceId", new LongType());
		query.addScalar("phone", new StringType());
		query.addScalar("address", new StringType());
		query.addScalar("provinceName", new StringType());
		query.addScalar("status", new LongType());
		query.addScalar("countReup", new LongType());
		query.addScalar("countReupDay", new LongType());
		query.addScalar("countView", new LongType());

		query.setResultTransformer(Transformers.aliasToBean(JobDTO.class));
		if (StringUtils.isNotEmpty(obj.getTxtSearch())) {
			query.setParameter("textSearch", "%" + ValidateUtils.validateKeySearch(obj.getTxtSearch()) + "%");
			queryCount.setParameter("textSearch", "%" + ValidateUtils.validateKeySearch(obj.getTxtSearch()) + "%");
		}

		if (StringUtils.isNotEmpty(obj.getProvinceCode())) {
			query.setParameter("provinceCode", obj.getProvinceCode());
			queryCount.setParameter("provinceCode", obj.getProvinceCode());
		}

		if (obj.getProvinceId() != null) {
			query.setParameter("provinceId", obj.getProvinceId());
			queryCount.setParameter("provinceId", obj.getProvinceId());
		}

		if (obj.getCreatedBy() != null) {
			query.setParameter("createdBy", obj.getCreatedBy());
			queryCount.setParameter("createdBy", obj.getCreatedBy());
		}
		if (obj.getStatus() != null) {
			query.setParameter("status", obj.getStatus());
			queryCount.setParameter("status", obj.getStatus());
		}
		if (obj.getFromDate() != null && obj.getToDate() != null) {
			obj.getFromDate().setHours(0);
			obj.getFromDate().setMinutes(0);
			obj.getFromDate().setSeconds(0);
			obj.getToDate().setHours(23);
			obj.getToDate().setMinutes(59);
			obj.getToDate().setSeconds(59);
			if (obj.getFromDate().getYear() == obj.getToDate().getYear()
					&& obj.getFromDate().getDate() == obj.getToDate().getDate()
					&& obj.getFromDate().getMonth() == obj.getToDate().getMonth()) {
				query.setParameter("fromDate", obj.getFromDate());
				queryCount.setParameter("fromDate", obj.getFromDate());
				query.setParameter("toDate", obj.getToDate());
				queryCount.setParameter("toDate", obj.getToDate());
			} else {
				query.setParameter("fromDate", obj.getFromDate());
				queryCount.setParameter("fromDate", obj.getFromDate());
				query.setParameter("toDate", obj.getToDate());
				queryCount.setParameter("toDate", obj.getToDate());
			}

		} else if (obj.getFromDate() == null && obj.getToDate() != null) {
			obj.getToDate().setHours(23);
			obj.getToDate().setMinutes(59);
			obj.getToDate().setSeconds(59);
			query.setParameter("toDate", obj.getToDate());
			queryCount.setParameter("toDate", obj.getToDate());
		} else if (obj.getFromDate() != null && obj.getToDate() == null) {
			obj.getFromDate().setHours(0);
			obj.getFromDate().setMinutes(0);
			obj.getFromDate().setSeconds(0);
			query.setParameter("fromDate", obj.getFromDate());
			queryCount.setParameter("fromDate", obj.getFromDate());
		}

		query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
		query.setMaxResults(obj.getPageSize().intValue());
		obj.setTotalRecord(((BigInteger) queryCount.uniqueResult()).intValue());
		return query.list();
	}

	@Transactional
	public ResultDTO saveOrUpdate(Job obj) {
		Session session = getSession();
		ResultDTO resultDTO = new ResultDTO();

		if (obj.getId() != null) {
			session.update(obj);
			resultDTO.setError(false);
			resultDTO.setMessage("Đã cập nhật thành công");
			resultDTO.setErrorCode(200);
		} else {
			session.save(obj);
			resultDTO.setError(false);
			resultDTO.setMessage("Đã thêm mới thành công");
			resultDTO.setErrorCode(200);
		}
		return resultDTO;
	}

	@Transactional
	public void save(JobDTO obj) {
		Session session = getSession();
		session.save(obj.toModel());
	}

	@Transactional
	public void update(JobDTO obj) {
		Session session = getSession();
		session.update(obj.toModel());
	}

	@Transactional
	public void delete(JobDTO obj) {
		Session session = getSession();
		StringBuilder sql = new StringBuilder("delete from job where id = :id");
		SQLQuery query = session.createSQLQuery(sql.toString());
		query.setParameter("id", obj.getId());
		query.executeUpdate();
	}

}
