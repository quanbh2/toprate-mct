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

import com.toparte.mct.dto.RoleObjectDTO;
import com.toparte.mct.dto.RoleUserDTO;
import com.toparte.mct.dto.RolesDTO;
import com.toparte.mct.dto.UsersDTO;
import com.toprate.base.common.BusinessException;
import com.toprate.base.utils.QlanResource;
import com.toprate.mct.constant.Constants;
import com.toprate.mct.bo.Roles;
import com.toprate.mct.dao.RolesDAO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;

@Service("rolesBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RolesBusinessImpl extends BaseFWBusinessImpl<RolesDAO, RolesDTO, Roles>
		implements RolesBusiness {

	@Autowired
	private RolesDAO rolesDAO;

	public RolesBusinessImpl() {
		tModel = new Roles();
		tDAO = rolesDAO;
	}

	@Override
	public RolesDAO gettDAO() {
		return rolesDAO;
	}

	@Override
	public long getTotal() {
		return rolesDAO.count("AdClientBO", null);
	}

	public DataListDTO doSearch(RolesDTO obj) {
		List<RolesDTO> ls = rolesDAO.doSearch(obj);
		DataListDTO data = new DataListDTO();
		data.setData(ls);
		data.setTotal(obj.getTotalRecord());
		data.setSize(obj.getTotalRecord());
		data.setStart(1);
		return data;
	}

	public Boolean checkCode(String code, Long appParamId) {
		RolesDTO obj = rolesDAO.getbycode(code);

		if (appParamId == null) {
			if (obj == null) {
				return true;
			} else {
				return false;
			}
		} else {
			if (obj == null) {
				return true;
			} else if (obj != null && obj.getRoleId().longValue() == appParamId) {
				return true;
			} else {
				return false;
			}
		}

	}

	
	@Transactional
	public Long add(RolesDTO obj,HttpServletRequest request) throws Exception{
		if(!checkCode(obj.getRoleCode(), null)){
			throw new BusinessException(QlanResource.get(Constants.ROLE_CODE_EXITS));
		}
		HttpSession httpSession =request.getSession();
		UsersDTO userDto= (UsersDTO) httpSession.getAttribute("userInfo");
		obj.setCreateUser(userDto.getUserName());
		obj.setCreateDate(new Date());
		Long id=save(obj);
		return id;
	}
	@Transactional
	public Long updateRoles(RolesDTO obj,HttpServletRequest request) throws Exception{
		if(!checkCode(obj.getRoleCode(), obj.getRoleId())){
			throw new BusinessException(QlanResource.get(Constants.ROLE_CODE_EXITS));
		}
		Long id=update(obj);
		RolesDTO objOld = rolesDAO.getbycode(obj.getRoleCode());
		return id;
	}
	@Transactional
	public Long lockAndUnLock(List<RolesDTO> obj,HttpServletRequest request) throws Exception{
		if(rolesDAO.updateStatus(obj)) {
			return 1L;
		}else {
			throw new BusinessException(QlanResource.get(Constants.ERROR_UPDATE_STATUS));
		}
//		RolesDTO objOld = rolesDAO.getbycode(obj.getRoleCode());
//		actionAuditBusinessImpl.trackingAdjustment("Roles", id, obj, objOld, QlanResource.get(Constants.MNG_ROLES), request);
	}
	@Transactional
	public Long deleteRoles(RolesDTO obj,HttpServletRequest request) throws Exception{
		if(checkCode(obj.getRoleCode(), null)){
			throw new BusinessException(QlanResource.get(Constants.DELETE_SELECTED_MESSAGE));
		}
			List<RoleObjectDTO> lst = rolesDAO.getRoleObjectByObjectId(obj.getRoleId());
			List<RoleUserDTO> lstRoleUser = rolesDAO.getRoleUserByRoleId(obj.getRoleId());
			if(lst!=null&&lst.size()>0) {
				throw new BusinessException(QlanResource.get(Constants.DELETE_ERROR_REF_ROLE));
			}
			if(lstRoleUser!=null&&lstRoleUser.size()>0) {
				throw new BusinessException(QlanResource.get(Constants.DELETE_ERROR_REF_USER));
			}
		delete(obj);
		return obj.getRoleId();
	}
	
	public List<RolesDTO> getForAutoCompleteRoles(RolesDTO obj){
		return rolesDAO.getForAutoCompleteRoles(obj);
	}
	@Transactional
	public void insertRoleObjectData(RolesDTO userDto) throws Exception {
		rolesDAO.deleteByRoleId(userDto.getRoleId());
		if(userDto.getListObject().size()>0){
		for(RoleObjectDTO roleUser:userDto.getListObject()){
			rolesDAO.insert(roleUser,userDto);
		}
		}
	}
}
