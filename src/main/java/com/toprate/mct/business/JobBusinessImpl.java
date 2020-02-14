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
import com.toparte.mct.dto.JobDTO;
import com.toparte.mct.dto.ResultDTO;
import com.toparte.mct.dto.UsersDTO;
import com.toprate.mct.bo.Job;
import com.toprate.mct.constant.Constants;
import com.toprate.mct.dao.JobDAO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;

@Service("jobBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class JobBusinessImpl extends BaseFWBusinessImpl<JobDAO, JobDTO, Job> implements JobBusiness {
	@Autowired
	private JobDAO jobDAO;

	@Autowired
	private CommonBusiness commonBusiness;

	@Override
	public long getTotal() {
		return jobDAO.count("AdClientBO", null);
	}

	public DataListDTO doSearch(JobDTO obj) {
		obj.setIsDelete((long) 0);
		List<JobDTO> ls = jobDAO.doSearch(obj);
		DataListDTO data = new DataListDTO();
		data.setData(ls);
		data.setTotal(obj.getTotalRecord());
		data.setSize(obj.getTotalRecord());
		data.setStart(1);
		return data;
	}

	public DataListDTO doSearchByJob(JobDTO obj, HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		UsersDTO userDto = (UsersDTO) httpSession.getAttribute("customerInfo");
		obj.setCreatedBy(userDto.getUserId());
		obj.setIsDelete(Constants.STATUSDELETE.NOTYET);
		List<JobDTO> ls = jobDAO.doSearch(obj);
		DataListDTO data = new DataListDTO();
		data.setData(ls);
		data.setTotal(obj.getTotalRecord());
		data.setSize(obj.getTotalRecord());
		data.setStart(1);
		return data;
	}

	public ResultDTO update(JobDTO obj, HttpServletRequest request) {
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
					obj.setUpdatedDate(new Date());
					jobDAO.update(obj.toModel());
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
						obj.setUpdatedDate(new Date());
						jobDAO.update(obj.toModel());
						resultDTO.setError(false);
						resultDTO.setMessage("Đã up tin thành công");
						resultDTO.setErrorCode(200);
					}
				}
			} else {
				obj.setUpdatedBy(usersDTO.getUserId());
				obj.setUpdatedDate(new Date());
				jobDAO.update(obj.toModel());
				resultDTO.setError(false);
				resultDTO.setMessage("Đã cập nhật thành công");
				resultDTO.setErrorCode(200);
			}

		} else {
			obj.setCreatedBy(usersDTO.getUserId());
			obj.setCreatedDate(new Date());
			obj.setUpdatedDate(new Date());
			obj.setReupDate(new Date());
			obj.setIsDelete(Constants.STATUSDELETE.NOTYET);
			obj.setStatus(Constants.STATUSPOST.PENDING);
			obj.setCountReup((long) 0);
			obj.setCountReupDay((long) 0);
			String code = commonBusiness.genCode("job");
			obj.setCode(code);
			jobDAO.save(obj.toModel());
			resultDTO.setError(false);
			resultDTO.setMessage("Đã thêm mới thành công");
			resultDTO.setErrorCode(200);
		}
		return resultDTO;

	}
	public ResultDTO updateAdmin(JobDTO obj, HttpServletRequest request) {
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
					obj.setUpdatedDate(new Date());
					jobDAO.update(obj.toModel());
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
						obj.setUpdatedDate(new Date());
						jobDAO.update(obj.toModel());
						resultDTO.setError(false);
						resultDTO.setMessage("Đã up tin thành công");
						resultDTO.setErrorCode(200);
					}
				}
			} else {
				obj.setUpdatedBy(usersDTO.getUserId());
				obj.setUpdatedDate(new Date());
				jobDAO.update(obj.toModel());
				resultDTO.setError(false);
				resultDTO.setMessage("Đã cập nhật thành công");
				resultDTO.setErrorCode(200);
			}

		} else {
			obj.setCreatedBy(usersDTO.getUserId());
			obj.setCreatedDate(new Date());
			obj.setUpdatedDate(new Date());
			obj.setReupDate(new Date());
			obj.setIsDelete(Constants.STATUSDELETE.NOTYET);
			obj.setStatus(Constants.STATUSPOST.APPROVED);
			obj.setCountReup((long) 0);
			obj.setCountReupDay((long) 0);
			String code = commonBusiness.genCode("job");
			obj.setCode(code);
			jobDAO.save(obj.toModel());
			resultDTO.setError(false);
			resultDTO.setMessage("Đã thêm mới thành công");
			resultDTO.setErrorCode(200);
		}
		return resultDTO;

	}

	public void add(JobDTO obj, HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		UsersDTO userDto = (UsersDTO) httpSession.getAttribute("customerInfo");
		obj.setCreatedBy(userDto.getUserId());
		obj.setCreatedDate(new Date());
		String code = commonBusiness.genCode("job");
		obj.setCode(code);
		jobDAO.saveOrUpdate(obj.toModel());

	}

	public ResultDTO delete(JobDTO obj, HttpServletRequest request) {
		ResultDTO resultDTO = new ResultDTO();
		jobDAO.delete(obj);
		resultDTO.setError(false);
		resultDTO.setMessage("Đã xóa thành công");
		resultDTO.setErrorCode(200);
		return resultDTO;
	}
}
