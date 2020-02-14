package com.toprate.mct.dao;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.toparte.mct.dto.AppParamDTO;
import com.toparte.mct.dto.ImageDTO;
import com.toparte.mct.dto.Sell4RentDTO;
import com.toprate.base.utils.ValidateUtils;
import com.toprate.mct.bo.AppParam;
import com.toprate.mct.bo.Image;
import com.toprate.mct.bo.JobApplication;
import com.viettel.service.base.dao.BaseFWDAOImpl;

import java.math.BigInteger;
import java.util.List;

@Repository("appParamDAO")
public class AppParamDAO extends BaseFWDAOImpl<AppParam, Long> {

	public AppParamDAO() {
		this.model = new AppParam();
	}

	@Transactional
	public void saveOrUpdate(AppParam image) {
		getSession().save(image);
	}

	@Transactional
	public void deleteImage(ImageDTO image) {
		StringBuilder sql = new StringBuilder("delete from image where parent_id = :parentId");
		SQLQuery query = this.getSession().createSQLQuery(sql.toString());
		query.setParameter("parentId", image.getParentId());
		query.executeUpdate();
	}

	public List<AppParamDTO> doSearch(AppParamDTO obj) {
			StringBuilder sql = new StringBuilder("" 
					+ "SELECT " 
					+ "m.id id," 
					+ "m.par_type parType," 
					+ "m.par_name parName,"
					+ "m.par_code parCode," 
					+ "m.par_value parValue, " 
					+ "m.description description " 
					+ "FROM app_param as m "
					+ "WHERE 1=1 ");

			

			
			if(StringUtils.isNotEmpty(obj.getParName())) {
				sql.append(" AND upper(m.par_name) like upper(parName)");
			}
			
			if(StringUtils.isNotEmpty(obj.getParType())) {
				sql.append(" AND upper(m.par_type) like upper(parType)");
			}
			
			sql.append(" ORDER BY m.par_type desc");
			
			StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
			sqlCount.append(sql.toString());
			sqlCount.append(")");
			sqlCount.append(" as appParam;");
			
			SQLQuery query = getSession().createSQLQuery(sql.toString());
			SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());
			
			query.addScalar("id", new LongType());
			query.addScalar("parType", new StringType());
			query.addScalar("parName", new StringType());
			query.addScalar("parCode", new StringType());
			query.addScalar("parValue", new StringType());
			query.addScalar("description", new StringType());


			if(StringUtils.isNotEmpty(obj.getParName())) {
				query.setParameter("parName", "%"+ValidateUtils.validateKeySearch(obj.getParName())+"%");
				queryCount.setParameter("parName", "%"+ValidateUtils.validateKeySearch(obj.getParName())+"%");
			}
			
			if(StringUtils.isNotEmpty(obj.getParType())) {
				query.setParameter("parType", "%"+ValidateUtils.validateKeySearch(obj.getParType())+"%");
				queryCount.setParameter("parType", "%"+ValidateUtils.validateKeySearch(obj.getParType())+"%");
			}

			query.setResultTransformer(Transformers.aliasToBean(ImageDTO.class));
			query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
			query.setMaxResults(obj.getPageSize().intValue());
			obj.setTotalRecord(((BigInteger) queryCount.uniqueResult()).intValue());
			
			return query.list();
//		return null;
	}
}
