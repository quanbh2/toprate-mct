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

import com.toparte.mct.dto.ResultDTO;
import com.toparte.mct.dto.SmsBoxDTO;
import com.toprate.mct.bo.BuyRent;
import com.toprate.mct.bo.SmsBox;
import com.viettel.service.base.dao.BaseFWDAOImpl;

@Repository("smsBoxDAO")
public class SmsBoxDAO extends BaseFWDAOImpl<SmsBox, Long> {

	public SmsBoxDAO() {
		this.model = new SmsBox();
	}

	public SmsBoxDAO(Session session) {
		this.session = session;
	}

	public List<SmsBoxDTO> doSearch(SmsBoxDTO obj) {
		StringBuilder sql = new StringBuilder("SELECT m.id id," + "m.title title," + "m.description description,"
				+ "m.recipient_name recipientName," + "m.create_by createBy," + "m.sell_4rent_id sell4rentId,"
				+ "sell.type sell4rentType," + "m.create_date createDate," + "m.is_delete isDelete,"
				+ "m.recipient_del_sms recipientDelSms," + "m.sender_del_sms senderDelSms,"
				+ "m.recipient_id recipientId," + "sell.code codeSell4rent," + "user.full_name userFullName"
				+ " FROM sms_box m " + " INNER JOIN users user" + " ON m.create_by = user.user_id "
				+ " INNER JOIN sell_4rent sell ON sell.id = m.sell_4rent_id  " + " WHERE 1=1 ");

		if (obj.getCreateBy() != null) {
			sql.append(" AND m.create_by = :createBy ");
		}
		if (obj.getRecipientId() != null) {
			sql.append(" AND m.recipient_id = :recipientId ");
		}
		if (obj.getRecipientDelSms() != null) {
			if (obj.getRecipientDelSms() == 0) {
				sql.append(" AND m.recipient_del_sms != 1 ");
			}
		}
		if (obj.getSenderDelSms() != null) {
			if(obj.getSenderDelSms() == 0) {
				sql.append(" AND m.sender_del_sms != 1 ");
			}
		} 

		if (obj.getIsDelete() == null || obj.getIsDelete() != 1) {
			sql.append(" AND m.is_delete != 1 ");
		}

		sql.append(" ORDER BY m.create_date desc");

		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM (");
		sqlCount.append(sql.toString());
		sqlCount.append(")");
		sqlCount.append(" as smsbox;");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		SQLQuery queryCount = getSession().createSQLQuery(sqlCount.toString());

		query.addScalar("id", new LongType());
		query.addScalar("title", new StringType());
		query.addScalar("description", new StringType());
		query.addScalar("createBy", new LongType());
		query.addScalar("createDate", new DateType());
		query.addScalar("recipientId", new LongType());
		query.addScalar("recipientName", new StringType());
		query.addScalar("codeSell4rent", new StringType());
		query.addScalar("sell4rentType", new LongType());
		query.addScalar("isDelete", new LongType());
		query.addScalar("userFullName", new StringType());
		query.addScalar("sell4rentId", new LongType());
		query.addScalar("senderDelSms", new LongType());
		query.addScalar("recipientDelSms", new LongType());

		query.setResultTransformer(Transformers.aliasToBean(SmsBoxDTO.class));

		if (obj.getCreateBy() != null) {
			query.setParameter("createBy", obj.getCreateBy());
			queryCount.setParameter("createBy", obj.getCreateBy());
		}
		if (obj.getRecipientId() != null) {
			query.setParameter("recipientId", obj.getRecipientId());
			queryCount.setParameter("recipientId", obj.getRecipientId());
		}

		query.setFirstResult((obj.getPage().intValue() - 1) * obj.getPageSize().intValue());
		query.setMaxResults(obj.getPageSize().intValue());
		obj.setTotalRecord(((BigInteger) queryCount.uniqueResult()).intValue());
		return query.list();
	}

	@Transactional
	public void delete(SmsBoxDTO obj) {
		Session session = getSession();
		StringBuilder sql = new StringBuilder("delete from sms_box where id = :id ");
		SQLQuery query = session.createSQLQuery(sql.toString());
		query.setParameter("id", obj.getId());
		query.executeUpdate();
	}

	@Transactional
	public ResultDTO saveSms(SmsBox obj) {
		Session session = getSession();
		ResultDTO resultDTO = new ResultDTO();
		session.save(obj);
		resultDTO.setError(false);
		resultDTO.setMessage("Đã gửi tin nhắn thành công");
		resultDTO.setErrorCode(200);
		return resultDTO;
	}

	@Transactional
	public ResultDTO saveOrUpdate(SmsBox obj) {
		Session session = getSession();
		ResultDTO resultDTO = new ResultDTO();

		if (obj.getId() != null) {
			session.update(obj);
			resultDTO.setError(false);
			resultDTO.setMessage("Đã xóa tin nhắn thành công");
			resultDTO.setErrorCode(200);
		} else {
			if (obj.getCreateBy() == obj.getRecipientId()) {
				resultDTO.setError(false);
				resultDTO.setMessage("Không thể gửi tin nhắn cho chính bạn");
				resultDTO.setErrorCode(400);
			} else {
				session.save(obj);
				resultDTO.setError(false);
				resultDTO.setMessage("Đã gửi tin nhắn thành công");
				resultDTO.setErrorCode(200);
			}
		}
		return resultDTO;
	}

}
