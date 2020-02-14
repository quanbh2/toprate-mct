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
import com.toparte.mct.dto.ImageDTO;
import com.toparte.mct.dto.ResultDTO;
import com.toparte.mct.dto.Sell4RentDTO;
import com.toparte.mct.dto.UsersDTO;
import com.toprate.mct.bo.Sell4Rent;
import com.toprate.mct.constant.Constants;
import com.toprate.mct.dao.ImageDAO;
import com.toprate.mct.dao.ModelDAO;
import com.toprate.mct.dao.Sell4RentDAO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;

@Service("sell4rentBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Sell4RentBusinessImpl extends BaseFWBusinessImpl<Sell4RentDAO, Sell4RentDTO, Sell4Rent>
		implements Sell4RentBusiness {

	@Autowired
	private Sell4RentDAO sell4rentDAO;

	@Autowired
	private ModelDAO modelDAO;

	@Autowired
	private ImageDAO imageDAO;

	@Autowired
	private CommonBusiness commonBusiness;

	@Override
	public long getTotal() {
		return sell4rentDAO.count("AdClientBO", null);
	}

	// Insert ảnh
	private void insert(Long sellId, List<ImageDTO> listImages) {
		if (listImages != null) {
			for (ImageDTO image : listImages) {
				image.setParentId(sellId);
				imageDAO.saveOrUpdate(image.toModel());
			}
		}
	}

	public ResultDTO delete(Sell4RentDTO obj, HttpServletRequest request) {
		ResultDTO resultDTO = new ResultDTO();
		sell4rentDAO.delete(obj);
		resultDTO.setError(false);
		resultDTO.setMessage("Đã xóa thành công");
		resultDTO.setErrorCode(200);
		return resultDTO;
	}

	public void deleteByUser(Sell4RentDTO obj, HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		UsersDTO userDto = (UsersDTO) httpSession.getAttribute("customerInfo");
		obj.setCreateBy(userDto.getUserId());
		if (obj.getId() != null) {
			sell4rentDAO.updateSell4Rent(obj.toModel());
		}
	}

	public ResultDTO update(Sell4RentDTO obj, HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		UsersDTO userDto = (UsersDTO) httpSession.getAttribute("customerInfo");
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
					sell4rentDAO.updateSell4Rent(obj.toModel());
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
						sell4rentDAO.updateSell4Rent(obj.toModel());
						resultDTO.setError(false);
						resultDTO.setMessage("Đã up tin thành công");
						resultDTO.setErrorCode(200);
					}
				}

			} else {

				obj.setUpdateBy(userDto.getUserId());
				obj.setUpdateDate(new Date());
				ImageDTO image = new ImageDTO();
				sell4rentDAO.updateSell4Rent(obj.toModel());
				image.setParentId(obj.getId());
				imageDAO.deleteImage(image);
				this.insert(obj.getId(), obj.getListImages());
				resultDTO.setError(false);
				resultDTO.setMessage("Đã cập nhật thành công");
				resultDTO.setErrorCode(200);
			}

		} else {
			obj.setCreateBy(userDto.getUserId());
			obj.setCreateDate(new Date());
			obj.setReupDate(new Date());
			obj.setUpdateDate(new Date());
			obj.setIsDelete(Constants.STATUSDELETE.NOTYET);
			obj.setStatus(Constants.STATUSPOST.PENDING);
			obj.setCountReup((long) 0);
			obj.setCountReupDay((long) 0);
			String code = commonBusiness.genCode("sell_4rent");
			obj.setCode(code);

			Long sellId = sell4rentDAO.saveSell4Rent(obj.toModel());
			insert(sellId, obj.getListImages());
			resultDTO.setError(false);
			resultDTO.setMessage("Đã thêm mới thành công");
			resultDTO.setErrorCode(200);
		}
		return resultDTO;

	}
	public ResultDTO updateCountView(Sell4RentDTO obj, HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		UsersDTO usersDTO = (UsersDTO) httpSession.getAttribute("customerInfo");
		ResultDTO resultDTO = new ResultDTO();
		if (obj.getId() != null) {
				obj.setCountView(obj.getCountView() + 1);
				sell4rentDAO.update(obj.toModel());
				resultDTO.setError(false);
				resultDTO.setMessage("Đã cập nhật thành công");
				resultDTO.setErrorCode(200);
		} else {
			obj.setCreateBy(usersDTO.getUserId());
			obj.setCreateDate(new Date());
			obj.setReupDate(new Date());
			obj.setUpdateDate(new Date());
			obj.setIsDelete(Constants.STATUSDELETE.NOTYET);
			obj.setStatus(Constants.STATUSPOST.PENDING);
			obj.setCountReup((long) 0);
			obj.setCountReupDay((long) 0);
			String code = commonBusiness.genCode("sell_4rent");
			obj.setCode(code);

			Long sellId = sell4rentDAO.saveSell4Rent(obj.toModel());
			insert(sellId, obj.getListImages());
			resultDTO.setError(false);
			resultDTO.setMessage("Đã thêm mới thành công");
			resultDTO.setErrorCode(200);
		}
		return resultDTO;

	}
	public ResultDTO updateAdmin(Sell4RentDTO obj, HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		UsersDTO userDto = (UsersDTO) httpSession.getAttribute("userInfo");
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
					sell4rentDAO.updateSell4Rent(obj.toModel());
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
						sell4rentDAO.updateSell4Rent(obj.toModel());
						resultDTO.setError(false);
						resultDTO.setMessage("Đã up tin thành công");
						resultDTO.setErrorCode(200);
					}
				}

			} else {

				obj.setUpdateBy(userDto.getUserId());
				obj.setUpdateDate(new Date());
				ImageDTO image = new ImageDTO();
				sell4rentDAO.updateSell4Rent(obj.toModel());
				image.setParentId(obj.getId());
				imageDAO.deleteImage(image);
				this.insert(obj.getId(), obj.getListImages());
				resultDTO.setError(false);
				resultDTO.setMessage("Đã cập nhật thành công");
				resultDTO.setErrorCode(200);
			}

		} else {
			obj.setCreateBy(userDto.getUserId());
			obj.setCreateDate(new Date());
			obj.setReupDate(new Date());
			obj.setUpdateDate(new Date());
			obj.setIsDelete(Constants.STATUSDELETE.NOTYET);
			obj.setStatus(Constants.STATUSPOST.APPROVED);
			obj.setCountReup((long) 0);
			obj.setCountReupDay((long) 0);
			String code = commonBusiness.genCode("sell_4rent");
			obj.setCode(code);

			Long sellId = sell4rentDAO.saveSell4Rent(obj.toModel());
			insert(sellId, obj.getListImages());
			resultDTO.setError(false);
			resultDTO.setMessage("Đã thêm mới thành công");
			resultDTO.setErrorCode(200);
		}
		return resultDTO;

	}

	public DataListDTO doSearch(Sell4RentDTO obj) {
		List<Sell4RentDTO> ls = sell4rentDAO.doSearch(obj);
		for (Sell4RentDTO sell : ls) {
			ImageDTO imageDTO = new ImageDTO();
			imageDTO.setParentId(sell.getId());
			sell.setListImages(imageDAO.getAllImageByParentId(imageDTO));
		}
		DataListDTO data = new DataListDTO();
		data.setData(ls);
		data.setTotal(obj.getTotalRecord());
		data.setSize(obj.getTotalRecord());
		data.setStart(1);
		return data;
	}

	public DataListDTO doSearchByUser(Sell4RentDTO obj, HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		UsersDTO userDto = (UsersDTO) httpSession.getAttribute("customerInfo");
		obj.setCreateBy(userDto.getUserId());
		List<Sell4RentDTO> ls = sell4rentDAO.doSearch(obj);
		for (Sell4RentDTO sell : ls) {
			ImageDTO imageDTO = new ImageDTO();
			imageDTO.setParentId(sell.getId());
			sell.setListImages(imageDAO.getAllImageByParentId(imageDTO));
		}
		DataListDTO data = new DataListDTO();
		data.setData(ls);
		data.setTotal(obj.getTotalRecord());
		data.setSize(obj.getTotalRecord());
		data.setStart(1);
		return data;
	}

}
