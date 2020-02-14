package com.toprate.mct.dao;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.toparte.mct.dto.ResultDTO;
import com.toparte.mct.dto.SalonProductDTO;
import com.toprate.base.utils.QlanResource;
import com.toprate.mct.bo.SalonProduct;
import com.viettel.service.base.dao.BaseFWDAOImpl;

@Repository("salonProductDAO")
public class SalonProductDAO extends BaseFWDAOImpl<SalonProduct, Long> {

	public SalonProductDAO() {
		this.model = new SalonProduct();
	}

	public SalonProductDAO(Session session) {
		this.session = session;
	}
	
//	@Transactional
	public ResultDTO saveOrUpdate(SalonProduct obj) {
		
		ResultDTO resultDTO = new ResultDTO();
		Session session = getSession();
		
		if(obj.getProductId() != null) {
			session.update(obj);
			
			resultDTO.setError(false);
			resultDTO.setMessage("Đã cập nhật thành công");
			resultDTO.setErrorCode(200);
		}else {
			UUID uuid = UUID.randomUUID();
		    String randomUUIDString = uuid.toString();
		    obj.setProductId(randomUUIDString);
		    
			session.save(obj);
			
			resultDTO.setError(false);
			resultDTO.setMessage("Đã cập nhật thành công");
			resultDTO.setErrorCode(200);
		}
		return resultDTO;
	}

	@SuppressWarnings("unchecked")
	public List<SalonProductDTO> doSearch(SalonProductDTO obj) {
		StringBuilder sql = new StringBuilder(
						"SELECT " 
						+ "s.product_id productId ," 
						+ "s.salon_id salonId," 
						+ "s.title title," 
						+ "s.status status,"
						+ "s.manufacturer_id manufacturerId," 
						+ "s.description description,"
						+ "s.create_on createOn," 
						+ "s.create_by createBy," 
						+ "s.update_on updateOn " 
						+ "FROM salon_product as s");

		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");
		sqlCount.append("as salon_product");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());

		query.addScalar("productId", new StringType());
		query.addScalar("salonId", new LongType());
		query.addScalar("manufacturerId", new LongType());
		query.addScalar("status", new LongType());
		query.addScalar("title", new StringType());
		query.addScalar("description", new StringType());
		query.addScalar("createOn", new DateType());
		query.addScalar("updateOn", new DateType());

		query.setResultTransformer(Transformers.aliasToBean(SalonProductDTO.class));

		query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
		query.setMaxResults(obj.getPageSize().intValue());
		obj.setTotalRecord(((BigInteger) queryCount.uniqueResult()).intValue());

		return query.list();
	}
}
