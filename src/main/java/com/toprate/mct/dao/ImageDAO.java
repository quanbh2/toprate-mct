package com.toprate.mct.dao;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.toparte.mct.dto.ImageDTO;
import com.toparte.mct.dto.Sell4RentDTO;
import com.toprate.mct.bo.Image;
import com.toprate.mct.bo.JobApplication;
import com.viettel.service.base.dao.BaseFWDAOImpl;

import java.util.List;

@Repository("imageDAO")
public class ImageDAO extends BaseFWDAOImpl<Image, Long> {

	public ImageDAO() {
		this.model = new Image();
	}

	@Transactional
	public void saveOrUpdate(Image image) {
		getSession().save(image);
	}

	@Transactional
	public void deleteImage(ImageDTO image) {
		StringBuilder sql = new StringBuilder("delete from image where parent_id = :parentId");
		SQLQuery query = this.getSession().createSQLQuery(sql.toString());
		query.setParameter("parentId", image.getParentId());
		query.executeUpdate();
	}

	public List<ImageDTO> getAllImageByParentId(ImageDTO obj) {
		if (obj.getParentId() != null) {
			StringBuilder sql = new StringBuilder("" 
					+ "SELECT " 
					+ "m.id id," 
					+ "m.path pathName," 
					+ "m.name name,"
					+ "m.fileNameEncrypt fileNameEncrypt," 
					+ "m.parent_id parentId " 
					+ "FROM image as m "
					+ "WHERE m.parent_id = :parentId");

			SQLQuery query = getSession().createSQLQuery(sql.toString());

			query.addScalar("id", new LongType());
			query.addScalar("pathName", new StringType());
			query.addScalar("name", new StringType());
			query.addScalar("fileNameEncrypt", new StringType());
			query.addScalar("parentId", new LongType());

			query.setParameter("parentId", obj.getParentId());

			query.setResultTransformer(Transformers.aliasToBean(ImageDTO.class));

			return query.list();
		} else {
			return null;
		}
//		return null;
	}
}
