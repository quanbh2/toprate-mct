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

import com.toparte.mct.dto.BuyRentDTO;
import com.toparte.mct.dto.ResultDTO;
import com.toprate.base.utils.ValidateUtils;
import com.toprate.mct.bo.BuyRent;
import com.viettel.service.base.dao.BaseFWDAOImpl;

@Repository("buyrentDAO")
public class BuyRentDAO extends BaseFWDAOImpl<BuyRent, Long> {
	public BuyRentDAO() {
		this.model = new BuyRent();
	}

	public BuyRentDAO(Session session) {
		this.session = session;
	}

	public List<BuyRentDTO> doSearch(BuyRentDTO obj) {
		StringBuilder sql = new StringBuilder("SELECT m.id id," + "m.code code," + "m.title title," + "m.status status,"
				+ "m.description description," + "m.minprice minPrice," + "m.maxprice maxPrice,"
				+ "m.create_by createBy," + "m.create_date createDate," + "m.reup_date reupDate,"
				+ "m.count_reup countReup," + "m.count_reup_day countReupDay," + "m.update_date updateDate,"
				+ "m.update_by updateBy," + "m.is_delete isDelete," + "m.count_view countView," + "m.type type,"
				+ "m.province_id provinceId," + "ma.code provinceCode," + "ma.name provinceName,"
				+ "user.full_name userFullName," + "user.te_fax userTeFax," + "user.BIRTH_PLACE userAddress"
				+ " FROM buy_rent m " + " INNER JOIN province ma" + " ON m.province_id = ma.id "
				+ " INNER JOIN users user" + " ON m.create_by = user.user_id " + " WHERE 1=1 ");
		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			sql.append(" AND (" + "(upper(m.code) LIKE upper(:keySearch) escape '&' ) "
					+ "OR (upper(m.title) LIKE upper(:keySearch) escape '&') "
					+ "OR (upper(m.description) LIKE upper(:keySearch) escape '&') "
					+ "OR (upper(ma.name) LIKE upper(:keySearch) escape '&')" + ")");
		}

		if (StringUtils.isNotEmpty(obj.getProvinceCode())) {
			sql.append(" AND (upper(ma.code) LIKE upper(:provinceCode) escape '&') ");
		}

		if (obj.getType() != null) {
			sql.append(" AND m.type = :type");
		}
		if (obj.getStatus() != null) {
			sql.append(" AND m.status = :status");
		}

		if (obj.getProvinceId() != null) {
			sql.append(" AND m.province_id = :provinceId ");
		}

		if (obj.getCreateBy() != null) {
			sql.append(" AND m.create_by = :createBy ");
		}

		if (obj.getIsDelete() == null || obj.getIsDelete() != 1) {
			sql.append(" AND m.is_delete != 1 ");
		}

		if (obj.getMinPrice() != null && obj.getMaxPrice() != null) {
			if (obj.getMinPrice() >= 0 && obj.getMaxPrice() >= obj.getMinPrice()) {
				sql.append(" AND !(m.maxPrice <= :minPrice OR m.minPrice >= :maxPrice)");
			}
		} else if (obj.getMinPrice() != null && obj.getMaxPrice() == null) {
			if (obj.getMinPrice() >= 0) {
				sql.append(" AND m.maxPrice > :minPrice");
			}
		} else if (obj.getMinPrice() == null && obj.getMaxPrice() != null) {
			if (obj.getMaxPrice() >= 0) {
				sql.append(" AND m.minPrice < :maxPrice");
			}
		}
		if (obj.getFromDate() != null && obj.getToDate() != null) {
			if (obj.getFromDate().getYear() == obj.getToDate().getYear()
					&& obj.getFromDate().getDate() == obj.getToDate().getDate()
					&& obj.getFromDate().getMonth() == obj.getToDate().getMonth()) {
				sql.append(" AND m.create_date between :fromDate and :toDate ");
			} else {
				sql.append(" AND m.create_date between :fromDate and :toDate ");
			}

		} else if (obj.getFromDate() == null && obj.getToDate() != null) {
			sql.append(" AND m.create_date <= :toDate ");
		} else if (obj.getFromDate() != null && obj.getToDate() == null) {
			sql.append(" AND m.create_date >= :fromDate ");
		}
		sql.append(" ORDER BY m.reup_date desc");

		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");
		sqlCount.append(" as buyrent;");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());

		query.addScalar("id", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("title", new StringType());
		query.addScalar("description", new StringType());
		query.addScalar("minPrice", new LongType());
		query.addScalar("maxPrice", new LongType());
		query.addScalar("createBy", new LongType());
		query.addScalar("createDate", new DateType());
		query.addScalar("reupDate", new DateType());
		query.addScalar("updateDate", new DateType());
		query.addScalar("updateBy", new LongType());
		query.addScalar("isDelete", new LongType());
		query.addScalar("type", new LongType());
		query.addScalar("provinceId", new LongType());
		query.addScalar("provinceName", new StringType());
		query.addScalar("provinceCode", new StringType());
		query.addScalar("userFullName", new StringType());
		query.addScalar("userTeFax", new StringType());
		query.addScalar("userAddress", new StringType());
		query.addScalar("status", new LongType());
		query.addScalar("countReup", new LongType());
		query.addScalar("countReupDay", new LongType());
		query.addScalar("countView", new LongType());

		query.setResultTransformer(Transformers.aliasToBean(BuyRentDTO.class));

		if (StringUtils.isNotEmpty(obj.getProvinceCode())) {
			query.setParameter("provinceCode", "%" + ValidateUtils.validateKeySearch(obj.getProvinceCode()) + "%");
			queryCount.setParameter("provinceCode", "%" + ValidateUtils.validateKeySearch(obj.getProvinceCode()) + "%");
		}

		if (obj.getProvinceId() != null) {
			query.setParameter("provinceId", obj.getProvinceId());
			queryCount.setParameter("provinceId", obj.getProvinceId());
		}
		if (obj.getType() != null) {
			query.setParameter("type", obj.getType());
			queryCount.setParameter("type", obj.getType());
		}
		if (obj.getStatus() != null) {
			query.setParameter("status", obj.getStatus());
			queryCount.setParameter("status", obj.getStatus());
		}
		if (obj.getCreateBy() != null) {
			query.setParameter("createBy", obj.getCreateBy());
			queryCount.setParameter("createBy", obj.getCreateBy());
		}

		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			query.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
			queryCount.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
		}

		if (obj.getMinPrice() != null && obj.getMaxPrice() != null) {
			if (obj.getMinPrice() >= 0 && obj.getMaxPrice() >= obj.getMinPrice()) {
				query.setParameter("minPrice", obj.getMinPrice());
				query.setParameter("maxPrice", obj.getMaxPrice());
				queryCount.setParameter("minPrice", obj.getMinPrice());
				queryCount.setParameter("maxPrice", obj.getMaxPrice());
			}
		} else if (obj.getMinPrice() != null && obj.getMaxPrice() == null) {
			if (obj.getMinPrice() >= 0) {
				query.setParameter("minPrice", obj.getMinPrice());
				queryCount.setParameter("minPrice", obj.getMinPrice());
			}
		} else if (obj.getMinPrice() == null && obj.getMaxPrice() != null) {
			if (obj.getMaxPrice() >= 0) {
				query.setParameter("maxPrice", obj.getMaxPrice());
				queryCount.setParameter("maxPrice", obj.getMaxPrice());
			}
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
	public void delete(BuyRentDTO obj) {
		Session session = getSession();
		StringBuilder sql = new StringBuilder("delete from buy_rent where id = :id ");
		SQLQuery query = session.createSQLQuery(sql.toString());
		query.setParameter("id", obj.getId());
		query.executeUpdate();
	}

	@Transactional
	public void save(BuyRentDTO obj) {
		Session session = getSession();
		session.save(obj.toModel());
	}

	@Transactional
	public void update(BuyRentDTO obj) {
		Session session = getSession();
		session.update(obj.toModel());
	}

	@Transactional
	public ResultDTO saveOrUpdate(BuyRent obj) {
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

	public List<BuyRentDTO> getBuyRentForAutoComplete(BuyRentDTO obj) {
		StringBuilder sql = new StringBuilder("SELECT m.id id," + "m.code code," + "m.title title,"
				+ "m.description description," + "ma.name provinceName" + " FROM buy_rent m "
				+ " INNER JOIN province ma" + " ON m.province_id = ma.id " + " WHERE 1=1 AND m.is_delete != 1");
		if (StringUtils.isNotEmpty(obj.getCode())) {
			sql.append(" AND (upper(m.code) LIKE upper(:code) escape '&') ");
		}

		if (StringUtils.isNotEmpty(obj.getTitle())) {
			sql.append(" AND (upper(m.title) LIKE upper(:title) escape '&') ");
		}

		if (StringUtils.isNotEmpty(obj.getDescription())) {
			sql.append(" AND (upper(m.description) LIKE upper(:description) escape '&') ");
		}

		if (StringUtils.isNotEmpty(obj.getProvinceName())) {
			sql.append(" AND (upper(ma.name) LIKE upper(:provinceName) escape '&') ");
		}

		SQLQuery query = getSession().createSQLQuery(sql.toString());

		query.addScalar("id", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("title", new StringType());
		query.addScalar("description", new StringType());
		query.addScalar("provinceName", new StringType());

		query.setResultTransformer(Transformers.aliasToBean(BuyRentDTO.class));

		if (StringUtils.isNotEmpty(obj.getCode())) {
			query.setParameter("code", "%" + ValidateUtils.validateKeySearch(obj.getCode()) + "%");
		}

		if (StringUtils.isNotEmpty(obj.getTitle())) {
			query.setParameter("title", "%" + ValidateUtils.validateKeySearch(obj.getTitle()) + "%");
		}
		if (StringUtils.isNotEmpty(obj.getDescription())) {
			query.setParameter("description", "%" + ValidateUtils.validateKeySearch(obj.getDescription()) + "%");
		}

		if (StringUtils.isNotEmpty(obj.getProvinceName())) {
			query.setParameter("provinceName", "%" + ValidateUtils.validateKeySearch(obj.getProvinceName()) + "%");
		}

		return query.list();
	}

	public String getCode(String tableName, String param) {
		StringBuilder sql = new StringBuilder(
				"SELECT LPAD(COALESCE(MAX(SUBSTR(CODE,LENGTH(:param) + 1)),0) + 1, 4, '0') FROM " + tableName
						+ "  WHERE CODE like :param ");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.setParameter("param", param + "%");

		return (String) query.uniqueResult();

	}
}
