package com.toprate.mct.business;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.toparte.mct.dto.ObjectsDTO;
import com.toparte.mct.dto.RoleObjectDTO;
import com.toparte.mct.dto.UsersDTO;
import com.toprate.base.common.BusinessException;
import com.toprate.base.utils.QlanResource;
import com.toprate.mct.constant.Constants;
import com.toprate.mct.bo.Objects;
import com.toprate.mct.dao.ObjectsDAO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;

@Service("objectsBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ObjectsBusinessImpl extends BaseFWBusinessImpl<ObjectsDAO, ObjectsDTO, Objects>
		implements ObjectsBusiness {

	@Autowired
	private ObjectsDAO objectsDAO;

	public ObjectsBusinessImpl() {
		tModel = new Objects();
		tDAO = objectsDAO;
	}

	@Override
	public ObjectsDAO gettDAO() {
		return objectsDAO;
	}

	@Override
	public long getTotal() {
		return objectsDAO.count("AdClientBO", null);
	}

	public DataListDTO doSearch(ObjectsDTO obj) {
		List<ObjectsDTO> ls = objectsDAO.doSearch(obj);
		DataListDTO data = new DataListDTO();
		data.setData(ls);
		data.setTotal(obj.getTotalRecord());
		data.setSize(obj.getTotalRecord());
		data.setStart(1);
		return data;
	}

	public Boolean checkCode(String code, Long appParamId) {
		ObjectsDTO obj = objectsDAO.getbycode(code);

		if (appParamId == null) {
			if (obj == null) {
				return true;
			} else {
				return false;
			}
		} else {
			if (obj == null) {
				return true;
			} else if (obj != null && obj.getObjectId().longValue() == appParamId) {
				return true;
			} else {
				return false;
			}
		}

	}

	
	@Transactional
	public Long add(ObjectsDTO obj,HttpServletRequest request) throws Exception{
		if(!checkCode(obj.getObjectCode(), null)){
			throw new BusinessException(QlanResource.get(Constants.OBJECT_CODE_EXITS));
		}
		HttpSession httpSession =request.getSession();
		UsersDTO userDto= (UsersDTO) httpSession.getAttribute("userInfo");
		obj.setCreateUser(userDto.getUserName());
		obj.setCreateDate(new Date());
		Long id=save(obj);
		return id;
	}
	@Transactional
	public Long updateObjects(ObjectsDTO obj,HttpServletRequest request) throws Exception{
		if(!checkCode(obj.getObjectCode(), obj.getObjectId())){
			throw new BusinessException(QlanResource.get(Constants.OBJECT_CODE_EXITS));
		}
		Long id=update(obj);
		ObjectsDTO objOld = objectsDAO.getbycode(obj.getObjectCode());
		return id;
	}
	@Transactional
	public Long lockAndUnLock(ObjectsDTO obj,HttpServletRequest request) throws Exception{
		Long auditType=Constants.ACTION_AUDIT_TYPE.LOCK;
		if(obj.isLock()) {
			obj.setStatus(0L);
		}else {
			obj.setStatus(1L);
			auditType=Constants.ACTION_AUDIT_TYPE.UNLOCK;
		}
		Long id=update(obj);
		ObjectsDTO objOld = objectsDAO.getbycode(obj.getObjectCode());
		return id;
	}
	@Transactional
	public Long deleteObjects(ObjectsDTO obj,HttpServletRequest request) throws Exception{
		if(checkCode(obj.getObjectCode(), null)){
			throw new BusinessException(QlanResource.get(Constants.DELETE_SELECTED_MESSAGE));
		}
			List<RoleObjectDTO> lst = objectsDAO.getRoleObjectByObjectId(obj.getObjectId());
			if(lst!=null&&lst.size()>0) {
				throw new BusinessException(QlanResource.get(Constants.DELETE_ERROR_REF_ROLE));
			}
		delete(obj);
		return obj.getObjectId();
	}
	public DataListDTO getParent() {
		List<ObjectsDTO> ls = objectsDAO.getParent();
		DataListDTO data = new DataListDTO();
		data.setData(ls);
		return data;
	}
	public DataListDTO doSearchObjectRole(RoleObjectDTO obj) {
		List<RoleObjectDTO> ls = objectsDAO.doSearchObjectRole(obj);
		   DataListDTO data = new DataListDTO();
		   data.setData(ls);
		   data.setTotal(obj.getTotalRecord());
		   data.setStart(1);
		return data;
	}
	public List<ObjectsDTO> getForAutoCompleteObject(ObjectsDTO obj) {
		return objectsDAO.getForAutoCompleteObject(obj);
	}
}
