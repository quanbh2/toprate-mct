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

import com.toparte.mct.dto.Sell4RentDTO;
import com.toprate.base.utils.ValidateUtils;
import com.toprate.mct.bo.Sell4Rent;
import com.toprate.mct.constant.Constants.SORTBY;
import com.viettel.service.base.dao.BaseFWDAOImpl;

@Repository("sell4rentDAO")
public class Sell4RentDAO extends BaseFWDAOImpl<Sell4Rent, Long> {

	public Sell4RentDAO() {
		this.model = new Sell4Rent();
	}

	public Sell4RentDAO(Session session) {
		this.session = session;
	}

	@Transactional
	public Long saveSell4Rent(Sell4Rent obj) {
		Session session = getSession();
		return (Long) session.save(obj);
	}

	@Transactional
	public void updateSell4Rent(Sell4Rent obj) {
		Session session = getSession();
		session.update(obj);
	}

	@Transactional
	public void delete(Sell4RentDTO obj) {
		Session session = getSession();
		StringBuilder sql = new StringBuilder("delete from sell_4rent where id = :id");
		SQLQuery query = session.createSQLQuery(sql.toString());
		query.setParameter("id", obj.getId());
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Sell4RentDTO> doSearch(Sell4RentDTO obj) {

		StringBuilder sql = new StringBuilder("" + "SELECT m.id id," + "m.code code," + "m.title title,"
				+ "m.status status," + "m.state state," + "m.price price," + "m.model_id modelId,"
				+ "m.manufacture_id manufactureId," + "m.year year," + "m.description description,"
				+ "m.update_date updateDate," + "m.create_by createBy," + "m.create_date createDate,"
				+ "m.count_reup countReup," + "m.count_reup_day countReupDay," + "m.count_view countView,"
				+ "m.reup_date reupDate," + "m.update_by updateBy," + "m.is_delete isDelete," + "m.type type,"
				+ "m.number number," + "m.type_machine_id typeMachineId," + "m.province_id provinceId,"
				+ "ma.code provinceCode," + "ma.name provinceName," + "user.full_name userFullName,"
				+ "user.te_fax userTeFax," + "user.BIRTH_PLACE userAddress,"
				+ "(select i.name from manufacturer i where i.id = m.manufacture_id) as manufactureName,"
				+ "i.name as typeMachineName,"
				+ "(select i.name from model i where i.id = m.model_id) as modelName,"
				+ "(select i.path from image i where i.parent_id = m.id limit 1) as pathName,"
				+ "(select i.name from image i where i.parent_id = m.id limit 1) as name" 
				+ " FROM sell_4rent m "
				+ " INNER JOIN province ma   ON m.province_id = ma.id " 
				+ " INNER JOIN users user  ON m.create_by = user.user_id " 
				+ " INNER JOIN type_machine i  ON m.type_machine_id = i.id " 
				+ " WHERE 1=1 ");

		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			sql.append(" AND (" + "(upper(m.code) LIKE upper(:keySearch) escape '&' ) "
					+ "OR (upper(m.title) LIKE upper(:keySearch) escape '&') "
					+ "OR (upper(m.description) LIKE upper(:keySearch) escape '&') "
					+ "OR (upper(ma.name) LIKE upper(:keySearch) escape '&')" + ")");
		}
		if (StringUtils.isNotEmpty(obj.getHaveImage())) {
			if (obj.getHaveImage().equals("1")) {
				sql.append(" AND (select path from image where parent_id = m.id limit 1) is not null");
			} else {
				sql.append(" AND (select path from image where parent_id = m.id limit 1) is null");
			}
		}
		if (obj.getId() != null) {
			sql.append(" AND m.id = :id");
		}
		if (obj.getType() != null) {
			sql.append(" AND m.type = :type");
		}
		if (obj.getManufactureId() != null) {
			sql.append(" AND m.manufacture_id = :manufactureId ");
		}

		if (obj.getIsDelete() == null || obj.getIsDelete() != 1) {
			sql.append(" AND m.is_delete != 1");
		}

		if (obj.getTypeMachineId() != null) {
			sql.append(" AND (m.type_machine_id = :typeMachineId  OR i.parent_id=:typeMachineId )");
		}

		if (obj.getStatus() != null) {
			sql.append(" AND m.status = :status");
		}

		if (obj.getState() != null) {
			sql.append(" AND m.state = :state");
		}

		if (obj.getModelId() != null) {
			sql.append(" AND m.model_id = :modelId");
		}

		if (obj.getEndYear() != null || obj.getStartYear() != null) {
			if (obj.getEndYear() != null && obj.getStartYear() != null) {
				sql.append(" AND m.year >= :startYear");
				sql.append(" AND m.year <= :endYear");
			}
			if (obj.getEndYear() == null && obj.getStartYear() != null) {
				sql.append(" AND m.year >= :startYear");
			}
			if (obj.getEndYear() != null && obj.getStartYear() == null) {
				sql.append(" AND m.year <= :endYear");
			}
		}

		if (obj.getStartPrice() != null || obj.getEndPrice() != null) {
			if (obj.getStartPrice() != null && obj.getEndPrice() != null) {
				sql.append(" AND m.price >= :startPrice");
				sql.append(" AND m.price <= :endPrice");
			}
			if (obj.getStartPrice() == null && obj.getEndPrice() != null) {
				sql.append(" AND m.price <= :endPrice");
			}
			if (obj.getStartPrice() != null && obj.getEndPrice() == null) {
				sql.append(" AND m.price >= :startPrice");
			}

		}

		if (StringUtils.isNotEmpty(obj.getProvinceCode())) {
			sql.append(" AND (upper(ma.code) LIKE upper(:provinceCode) escape '&') ");
		}

		if (obj.getProvinceId() != null) {
			sql.append(" AND m.province_id = :provinceId");
		}
		if (obj.getCreateBy() != null) {
			sql.append(" AND m.create_by = :createBy");
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
		if (obj.getSortBy() != null) {
			if (obj.getSortBy() == SORTBY.YEAR_ASC) {
				sql.append(" ORDER BY m.year ASC");
			} else if (obj.getSortBy() == SORTBY.YEAR_DESC) {
				sql.append(" ORDER BY m.year DESC");
			} else if (obj.getSortBy() == SORTBY.PRICE_ASC) {
				sql.append(" ORDER BY m.price ASC");
			} else {
				sql.append(" ORDER BY m.price DESC");
			}
		} else {
			sql.append(" ORDER BY m.reup_date desc");
		}
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");
		sqlCount.append(" as sell4rent;");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());

		query.addScalar("id", new LongType());
		query.addScalar("status", new LongType());
		query.addScalar("state", new LongType());
		query.addScalar("code", new StringType());
		query.addScalar("title", new StringType());
		query.addScalar("price", new LongType());
		query.addScalar("modelId", new LongType());
		query.addScalar("modelName", new StringType());
		query.addScalar("manufactureName", new StringType());
		query.addScalar("manufactureId", new LongType());
		query.addScalar("year", new LongType());
		query.addScalar("description", new StringType());
		query.addScalar("createBy", new LongType());
		query.addScalar("createDate", new DateType());
		query.addScalar("reupDate", new DateType());
		query.addScalar("updateBy", new LongType());
		query.addScalar("updateDate", new DateType());
		query.addScalar("number", new StringType());
		query.addScalar("isDelete", new LongType());
		query.addScalar("type", new LongType());
		query.addScalar("typeMachineId", new LongType());
		query.addScalar("typeMachineName", new StringType());
		query.addScalar("provinceId", new LongType());
		query.addScalar("provinceName", new StringType());
		query.addScalar("provinceCode", new StringType());
		query.addScalar("userFullName", new StringType());
		query.addScalar("userTeFax", new StringType());
		query.addScalar("userAddress", new StringType());
		query.addScalar("pathName", new StringType());
		query.addScalar("name", new StringType());
		query.addScalar("countReup", new LongType());
		query.addScalar("countReupDay", new LongType());
		query.addScalar("countView", new LongType());

		if (obj.getManufactureId() != null) {
			query.setParameter("manufactureId", obj.getManufactureId());
			queryCount.setParameter("manufactureId", obj.getManufactureId());
		}
		if (obj.getTypeMachineId() != null) {
			query.setParameter("typeMachineId", obj.getTypeMachineId());
			queryCount.setParameter("typeMachineId", obj.getTypeMachineId());
		}
		if (obj.getId() != null) {
			query.setParameter("id", obj.getId());
			queryCount.setParameter("id", obj.getId());
		}
		if (obj.getType() != null) {
			query.setParameter("type", obj.getType());
			queryCount.setParameter("type", obj.getType());
		}

		if (obj.getStatus() != null) {
			query.setParameter("status", obj.getStatus());
			queryCount.setParameter("status", obj.getStatus());
		}
		if (obj.getState() != null) {
			query.setParameter("state", obj.getState());
			queryCount.setParameter("state", obj.getState());
		}

		if (obj.getCreateBy() != null) {
			query.setParameter("createBy", obj.getCreateBy());
			queryCount.setParameter("createBy", obj.getCreateBy());
		}

		if (obj.getModelId() != null) {
			query.setParameter("modelId", obj.getModelId());
			queryCount.setParameter("modelId", obj.getModelId());
		}

		if (obj.getEndYear() != null || obj.getStartYear() != null) {
			if (obj.getEndYear() != null && obj.getStartYear() != null) {
				query.setParameter("startYear", obj.getStartYear());
				queryCount.setParameter("startYear", obj.getStartYear());

				query.setParameter("endYear", obj.getEndYear());
				queryCount.setParameter("endYear", obj.getEndYear());
			}
			if (obj.getEndYear() == null && obj.getStartYear() != null) {
				query.setParameter("startYear", obj.getStartYear());
				queryCount.setParameter("startYear", obj.getStartYear());
			}
			if (obj.getEndYear() != null && obj.getStartYear() == null) {
				query.setParameter("endYear", obj.getEndYear());
				queryCount.setParameter("endYear", obj.getEndYear());
			}

		}

		if (obj.getStartPrice() != null || obj.getEndPrice() != null) {
			if (obj.getStartPrice() != null && obj.getEndPrice() != null) {
				query.setParameter("startPrice", obj.getStartPrice());
				queryCount.setParameter("startPrice", obj.getStartPrice());

				query.setParameter("endPrice", obj.getEndPrice());
				queryCount.setParameter("endPrice", obj.getEndPrice());
			}
			if (obj.getStartPrice() == null && obj.getEndPrice() != null) {
				query.setParameter("endPrice", obj.getEndPrice());
				queryCount.setParameter("endPrice", obj.getEndPrice());
			}
			if (obj.getStartPrice() != null && obj.getEndPrice() == null) {
				query.setParameter("startPrice", obj.getStartPrice());
				queryCount.setParameter("startPrice", obj.getStartPrice());
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

		if (StringUtils.isNotEmpty(obj.getProvinceCode())) {
			query.setParameter("provinceCode", "%" + ValidateUtils.validateKeySearch(obj.getProvinceCode()) + "%");
			queryCount.setParameter("provinceCode", "%" + ValidateUtils.validateKeySearch(obj.getProvinceCode()) + "%");
		}

		if (StringUtils.isNotEmpty(obj.getKeySearch())) {
			query.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
			queryCount.setParameter("keySearch", "%" + ValidateUtils.validateKeySearch(obj.getKeySearch()) + "%");
		}
		query.setResultTransformer(Transformers.aliasToBean(Sell4RentDTO.class));

		query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
		query.setMaxResults(obj.getPageSize().intValue());
		obj.setTotalRecord(((BigInteger) queryCount.uniqueResult()).intValue());

		return query.list();
	}

}
