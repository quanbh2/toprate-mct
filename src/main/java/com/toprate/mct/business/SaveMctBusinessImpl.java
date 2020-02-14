package com.toprate.mct.business;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import com.toparte.mct.dto.ResultDTO;
import com.toparte.mct.dto.SaveMctDTO;
import com.toparte.mct.dto.UsersDTO;
import com.toprate.mct.bo.SaveMct;
import com.toprate.mct.dao.SaveMctDAO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;

@Service("saveMctBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SaveMctBusinessImpl extends BaseFWBusinessImpl<SaveMctDAO, SaveMctDTO, SaveMct> implements SaveMctBusiness{

	@Autowired
	SaveMctDAO saveMctDAO;
	
	public ResultDTO update(SaveMctDTO obj, HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		UsersDTO userDto = (UsersDTO) httpSession.getAttribute("customerInfo");
		ResultDTO resultDTO = new ResultDTO();
//		
		obj.setCreateBy(userDto.getUserId());
		obj.setCreateDate(new Date());
		obj.setStatus((long)1);
		List<SaveMctDTO> ls = saveMctDAO.checkSell4Rent(obj);
		if(ls.isEmpty()) {
			saveMctDAO.saveMct(obj.toModel());
			
			resultDTO.setData(ls);
			resultDTO.setError(false);
			resultDTO.setMessage("Đã thêm mới thành công");
			resultDTO.setErrorCode(200);
		}else {
			
			resultDTO.setError(true);
			resultDTO.setMessage("Đã lưu tin này");
			resultDTO.setErrorCode(200);
		}
		
		return resultDTO;
	}
	
	public ResultDTO deleteMct(SaveMctDTO obj) {
		saveMctDAO.deleteMct(obj);
		
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setError(false);
		resultDTO.setMessage("Đã xóa thành công");
		resultDTO.setErrorCode(200);
		return resultDTO;
	}
	
	public DataListDTO doSearch(SaveMctDTO obj,HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		UsersDTO userDto = (UsersDTO) httpSession.getAttribute("customerInfo");
//		
		obj.setCreateBy(userDto.getUserId());
		List<SaveMctDTO> ls = saveMctDAO.doSearch(obj);
		
		DataListDTO data = new DataListDTO();
		data.setData(ls);
		data.setTotal(obj.getTotalRecord());
		data.setSize(obj.getTotalRecord());
		data.setStart(1);
		return data;
	}	

	@Override
	public long getTotal() {
		return saveMctDAO.count("AdClientBO", null);
	}
}
