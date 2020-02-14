package com.toprate.mct.business;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import com.toparte.mct.dto.BuyRentDTO;
import com.toparte.mct.dto.ResultDTO;
import com.toparte.mct.dto.UsersDTO;
import com.toprate.mct.bo.BuyRent;
import com.toprate.mct.constant.Constants;
import com.toprate.mct.dao.BuyRentDAO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;

@Service("buyRentBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class BuyRentBusinessImpl extends BaseFWBusinessImpl<BuyRentDAO, BuyRentDTO, BuyRent>
		implements BuyRentBusiness {
	@Autowired
	private BuyRentDAO buyRentDAO;

	@Autowired
	private CommonBusiness commonBusiness;

	@Override
	public long getTotal() {
		return buyRentDAO.count("AdClientBO", null);
	}

	public DataListDTO doSearch(BuyRentDTO obj) {
		List<BuyRentDTO> ls = buyRentDAO.doSearch(obj);
		DataListDTO data = new DataListDTO();
		data.setData(ls);
		data.setTotal(obj.getTotalRecord());
		data.setSize(obj.getTotalRecord());
		data.setStart(1);
		return data;
	}

	public DataListDTO doSearchByUser(BuyRentDTO obj, HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		UsersDTO userDto = (UsersDTO) httpSession.getAttribute("customerInfo");
		obj.setCreateBy(userDto.getUserId());

		List<BuyRentDTO> ls = buyRentDAO.doSearch(obj);
		DataListDTO data = new DataListDTO();
		data.setData(ls);
		data.setTotal(obj.getTotalRecord());
		data.setSize(obj.getTotalRecord());
		data.setStart(1);
		return data;
	}

	public ResultDTO delete(BuyRentDTO obj, HttpServletRequest request) {
		ResultDTO resultDTO = new ResultDTO();
		buyRentDAO.delete(obj);
		resultDTO.setError(false);
		resultDTO.setMessage("Đã xóa thành công");
		resultDTO.setErrorCode(200);
		return resultDTO;
	}

	public ResultDTO deleteAdmin(BuyRentDTO obj, HttpServletRequest request) {
		ResultDTO resultDTO = new ResultDTO();
		try {
			buyRentDAO.delete(obj);
			resultDTO.setError(false);
			resultDTO.setMessage("Đã xóa thành công");
			resultDTO.setErrorCode(200);
		} catch (Exception e) {
			resultDTO.setError(false);
			resultDTO.setMessage(e.getMessage());
			resultDTO.setErrorCode(403);
		}
		return resultDTO;

	}

	public void add(BuyRentDTO obj, HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		UsersDTO userDto = (UsersDTO) httpSession.getAttribute("customerInfo");
		obj.setCreateBy(userDto.getUserId());
		obj.setCreateDate(new Date());
		String code = commonBusiness.genCode("buy_rent");
		obj.setCode(code);
		buyRentDAO.saveOrUpdate(obj.toModel());

	}
	
	public ResultDTO update(BuyRentDTO obj, HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		UsersDTO usersDTO = (UsersDTO) httpSession.getAttribute("customerInfo");
		ResultDTO resultDTO = new ResultDTO();
		if (obj.getId() != null) {
			if (obj.getCheckReup() != null && obj.getCheckReup() == 1) {
				Date today = new Date();
				today.setHours(0);
				today.setMinutes(0);
				today.setSeconds(0);
				if (obj.getReupDate().before(today)) {
					obj.setCountReupDay((long) 1);
					obj.setCountReup(obj.getCountReup() + 1);
					obj.setReupDate(new Date());
					obj.setUpdateDate(new Date());
					buyRentDAO.update(obj.toModel());
					resultDTO.setError(false);
					resultDTO.setMessage("Đã up tin thành công");
					resultDTO.setErrorCode(200);
				} else {
					if (obj.getCountReupDay() != null && obj.getCountReupDay() >= 1) {
						resultDTO.setError(false);
						resultDTO.setMessage("Bạn đã hết số lần update trong ngày");
						resultDTO.setErrorCode(400);
					} else {
						obj.setCountReup(obj.getCountReup() + 1);
						obj.setCountReupDay(obj.getCountReupDay() + 1);
						obj.setReupDate(new Date());
						obj.setUpdateDate(new Date());
						buyRentDAO.update(obj.toModel());
						resultDTO.setError(false);
						resultDTO.setMessage("Đã up tin thành công");
						resultDTO.setErrorCode(200);
					}
				}
			} else {
				obj.setUpdateBy(usersDTO.getUserId());
				obj.setUpdateDate(new Date());
				buyRentDAO.update(obj.toModel());
				resultDTO.setError(false);
				resultDTO.setMessage("Đã cập nhật thành công");
				resultDTO.setErrorCode(200);
			}

		} else {
			obj.setCreateBy(usersDTO.getUserId());
			obj.setCreateDate(new Date());
			obj.setReupDate(new Date());
			obj.setUpdateDate(new Date());
			obj.setIsDelete(Constants.STATUSDELETE.NOTYET);
			obj.setStatus(Constants.STATUSPOST.PENDING);
			obj.setCountReup((long) 0);
			obj.setCountReupDay((long) 0);
			obj.setCountView((long) 0);
			String code = commonBusiness.genCode("buy_rent");
			obj.setCode(code);
			buyRentDAO.save(obj.toModel());
			resultDTO.setError(false);
			resultDTO.setMessage("Đã thêm mới thành công");
			resultDTO.setErrorCode(200);
		}
		return resultDTO;

	}


	public ResultDTO updateAdmin(BuyRentDTO obj, HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		UsersDTO usersDTO = (UsersDTO) httpSession.getAttribute("userInfo");
		ResultDTO resultDTO = new ResultDTO();
		if (obj.getId() != null) {
			if (obj.getCheckReup() != null && obj.getCheckReup() == 1) {
				Date today = new Date();
				today.setHours(0);
				today.setMinutes(0);
				today.setSeconds(0);
				if (obj.getReupDate().before(today)) {
					obj.setCountReupDay((long) 1);
					obj.setCountReup(obj.getCountReup() + 1);
					obj.setReupDate(new Date());
					obj.setUpdateDate(new Date());
					buyRentDAO.update(obj.toModel());
					resultDTO.setError(false);
					resultDTO.setMessage("Đã up tin thành công");
					resultDTO.setErrorCode(200);
				} else {
					if (obj.getCountReupDay() != null && obj.getCountReupDay() >= 1) {
						resultDTO.setError(false);
						resultDTO.setMessage("Bạn đã hết số lần update trong ngày");
						resultDTO.setErrorCode(400);
					} else {
						obj.setCountReup(obj.getCountReup() + 1);
						obj.setCountReupDay(obj.getCountReupDay() + 1);
						obj.setReupDate(new Date());
						obj.setUpdateDate(new Date());
						buyRentDAO.update(obj.toModel());
						resultDTO.setError(false);
						resultDTO.setMessage("Đã up tin thành công");
						resultDTO.setErrorCode(200);
					}
				}
			} else {
				obj.setUpdateBy(usersDTO.getUserId());
				obj.setUpdateDate(new Date());
				buyRentDAO.update(obj.toModel());
				resultDTO.setError(false);
				resultDTO.setMessage("Đã cập nhật thành công");
				resultDTO.setErrorCode(200);
			}

		} else {
			obj.setCreateBy(usersDTO.getUserId());
			obj.setCreateDate(new Date());
			obj.setReupDate(new Date());
			obj.setUpdateDate(new Date());
			obj.setIsDelete(Constants.STATUSDELETE.NOTYET);
			obj.setStatus(Constants.STATUSPOST.APPROVED);
			obj.setCountReup((long) 0);
			obj.setCountReupDay((long) 0);
			String code = commonBusiness.genCode("buy_rent");
			obj.setCode(code);
			buyRentDAO.save(obj.toModel());
			resultDTO.setError(false);
			resultDTO.setMessage("Đã thêm mới thành công");
			resultDTO.setErrorCode(200);
		}
		return resultDTO;

	}

	public List<BuyRentDTO> getBuyRentForAutoComplete(BuyRentDTO obj) {
		List<BuyRentDTO> list = buyRentDAO.getBuyRentForAutoComplete(obj);
		return list;
	}

}
