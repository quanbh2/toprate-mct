package com.toprate.mct.business;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.toparte.mct.dto.ManufacturerDTO;
import com.toparte.mct.dto.ModelDTO;
import com.toparte.mct.dto.TypeMachineDTO;
import com.toparte.mct.dto.UsersDTO;
import com.toparte.mct.dto.ValidateDataImportDTO;
import com.toprate.base.common.CommonUtil;
import com.toprate.base.common.UEncrypt;
import com.toprate.mct.bo.TypeMachine;
import com.toprate.mct.dao.ModelDAO;
import com.toprate.mct.dao.TypeMachineDAO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;

@Service("typeMachineBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TypeMachineBusinessImpl extends BaseFWBusinessImpl<TypeMachineDAO, TypeMachineDTO, TypeMachine>
		implements TypeMachineBusiness {

	private static final Logger LOGGER = Logger.getLogger(TypeMachineBusinessImpl.class);

	@Value("${folder_upload}")
	private String folderUpload;

	@Autowired
	private TypeMachineDAO typeMachineDAO;
	
	@Autowired
	private ModelDAO modelDAO;

	@Override
	public long getTotal() {
		return typeMachineDAO.count("AdClientBO", null);
	}

	public DataListDTO doSearch(TypeMachineDTO obj) {
		List<TypeMachineDTO> ls = typeMachineDAO.doSearch(obj);
		DataListDTO data = new DataListDTO();
		data.setData(ls);
		data.setTotal(obj.getTotalRecord());
		data.setSize(obj.getTotalRecord());
		data.setStart(1);
		return data;
	}

	public void delete(TypeMachineDTO obj, HttpServletRequest request) {
		typeMachineDAO.delete(obj);

	}

	public void add(TypeMachineDTO obj, HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		UsersDTO userDto = (UsersDTO) httpSession.getAttribute("userInfo");
		obj.setCreatedBy(userDto.getUserId());
		obj.setCreatedDate(new Date());
		typeMachineDAO.save(obj);

	}
	public List<TypeMachineDTO> getAllTypeMachine(){
		List<TypeMachineDTO> listResult=typeMachineDAO.getAll();
//		List<ModelDTO> listModel=modelDAO.getAllTypeMachine();
//		
//		TypeMachineDTO ab = new TypeMachineDTO();
////		listResult.add(ab);
//		for (Iterator<ModelDTO> interator = listModel.iterator(); interator.hasNext();) {
//			ModelDTO wi = interator.next();
//
//			if (ab.getId() == null) {
//				ab = new TypeMachineDTO();
//				
//				ab.setId(wi.getTypeMachineId());
//				ab.setCode(wi.getTypeMachineCode());
//				ab.setName(wi.getTypeMachineName());
//				ab.setParentId(wi.getTypeMachineParentId());
//				
//				listResult.add(ab);
//
//			}
//			if (ab.getId().compareTo(wi.getTypeMachineId()) != 0) {
//				ab = new TypeMachineDTO();
//				ab.setId(wi.getTypeMachineId());
//				ab.setCode(wi.getTypeMachineCode());
//				ab.setName(wi.getTypeMachineName());
//				ab.setParentId(wi.getTypeMachineParentId());
//				
//				listResult.add(ab);
//			}
//			if (ab.getId().compareTo(wi.getTypeMachineId()) == 0) {
//				ModelDTO Ana = new ModelDTO();
//				Ana.setId(wi.getId());
//				Ana.setCode(wi.getCode());
//				Ana.setName(wi.getName());
//
//				if(Ana.getId() != null) {
//					ab.getListModel().add(Ana);
//				}
//			}
//		}
		return listResult;

	}
	public void update(TypeMachineDTO obj, HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		UsersDTO userDto = (UsersDTO) httpSession.getAttribute("userInfo");
		TypeMachine typeMachine = typeMachineDAO.get(TypeMachine.class, obj.getId());
		typeMachine.setUpdateBy(userDto.getUserId());
		typeMachine.setUpdatedDate(new Date());
		typeMachine.setCode(obj.getCode());
		typeMachine.setName(obj.getName());
		typeMachine.setDescription(obj.getDescription());
		typeMachine.setParentId(obj.getParentId());
		typeMachineDAO.saveOrUpdate(typeMachine);
	}

	@Transactional
	public ValidateDataImportDTO importCells(ValidateDataImportDTO dataImport, HttpServletRequest request)
			throws Exception {
		HttpSession httpSession = request.getSession();
		UsersDTO userDto = (UsersDTO) httpSession.getAttribute("userInfo");
		try {
			String fileName = folderUpload + File.separatorChar + UEncrypt.decryptFileUploadPath(dataImport.getPath());
			ValidateDataImportDTO dataImportDTO = new ValidateDataImportDTO();
			FileInputStream file = new FileInputStream(new File(fileName));
			String err = "";
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);
			XSSFFont fontBold = workbook.createFont();
			fontBold.setFontHeightInPoints((short) 12);
			fontBold.setFontName("Times New Roman");
			fontBold.setColor(IndexedColors.RED.getIndex());
			fontBold.setBold(true);
			fontBold.setItalic(false);
			CellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setFont(fontBold);
			cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
			cellStyle.setWrapText(true);

			TypeMachineDTO typeMachineDTO = new TypeMachineDTO();
			List<TypeMachine> typeMachineDTOs = Lists.newArrayList();

			int count = 0;
			Row rowTitle = sheet.getRow(2);
			DataFormatter formatter = new DataFormatter();
			boolean check = false;
			List<String> listCode = Lists.newArrayList();
			for (Row row : sheet) {
				typeMachineDTO = new TypeMachineDTO();
				err = "";
				count++;
				if (count > 3 && !CommonUtil.isRowEmpty(row)) {
					err = getValueImport(rowTitle, row, formatter, typeMachineDTO);

					if (StringUtils.isNotEmpty(err)) {
						Cell cell = row.createCell(6);
						cell.setCellValue(err);
						cell.setCellStyle(cellStyle);
						check = true;
					} else if (listCode.contains(typeMachineDTO.getCode())) {
						err += " Mã loại máy đã tồn tại trong file import!";
						Cell cell = row.createCell(6);
						cell.setCellValue(err);
						cell.setCellStyle(cellStyle);
						check = true;
					} else {
						typeMachineDTO.setCreatedDate(new Date());
						typeMachineDTO.setCreatedBy(userDto.getUserId());
						typeMachineDTOs.add(typeMachineDTO.toModel());
					}

					listCode.add(typeMachineDTO.getCode());
				}

			}

			String path = CommonUtil.customUploadFix(workbook, "Imp_LOAI_MAY_ERROR.xlsx", "default", folderUpload);

			dataImportDTO.setError(check);
			dataImportDTO.setPath(UEncrypt.encryptFileUploadPath(path));
//			dataImportDTO.setListData(typeMachineDTOs);
			workbook.close();
			file.close();

			typeMachineDAO.saveList(typeMachineDTOs);

			return dataImportDTO;
		} catch (NullPointerException pointerException) {
			// pointerException.printStackTrace();
			LOGGER.error(pointerException.getMessage(), pointerException);
		} catch (Exception e) {
			// e.printStackTrace();
			LOGGER.error(e.getMessage(), e);
		}
		return new ValidateDataImportDTO();
	}

	private String getValueImport(Row rowTitle, Row row, DataFormatter formatter, TypeMachineDTO dto) {
		String err = "";
		String errone = "";
		String itemCode = "";
		/* Tên model */
		errone = validateImport("CHECK_NULL", formatter.formatCellValue(row.getCell(1)), null);
		if (StringUtils.isNotEmpty(errone)) {
			Cell cellTitle = rowTitle.getCell(1);
			err += errone + " " + formatter.formatCellValue(cellTitle) + ";";
		} else {
			dto.setName(formatter.formatCellValue(row.getCell(1)).trim());
		}
		/* 2. Mã model */
		itemCode = formatter.formatCellValue(row.getCell(2)).replace("\n", "").replace("\r", "");
		List<TypeMachineDTO> list = typeMachineDAO.getByCode(itemCode);
		errone = validateImport("CHECK_DUPLICATE", itemCode, list);
		if (StringUtils.isNotEmpty(errone)) {
			Cell cellTitle = rowTitle.getCell(2);
			err += errone + " " + formatter.formatCellValue(cellTitle) + ";";
		} else {
			dto.setCode(formatter.formatCellValue(row.getCell(2)).trim());
		}

		/* 3. Mô tả */
		dto.setDescription(formatter.formatCellValue(row.getCell(3)));
		
		/*4. Loại máy cha*/
		List<TypeMachineDTO> machineDTOs=typeMachineDAO.getParentByCode(formatter.formatCellValue(row.getCell(4)));
		errone=validateImport("CHECK_DATA", formatter.formatCellValue(row.getCell(4)).trim(),machineDTOs);
		if(StringUtils.isNotEmpty(errone)){
			Cell cellTitle=rowTitle.getCell(4);
				err+=errone +" "+formatter.formatCellValue(cellTitle)+";";
		}else {
			dto.setParentId(machineDTOs.get(0).getId());
		}
		return err;
	}

	private String validateImport(String typeValidate, String data, List<? extends Object> dto) {
		String err = "";
		if (typeValidate.equals("CHECK_NULL") && CommonUtil.isNullImport(data)) {
			return CommonUtil.ERROR_NULL;
		}
		if (typeValidate.equals("CHECK_NUMBER") && CommonUtil.isNotNumberImport(data)) {
			return CommonUtil.ERROR_NOT_FEILD;
		}
		if (typeValidate.equals("CHECK_DUPLICATE")) {
			if (CommonUtil.isNullImport(data)) {
				return CommonUtil.ERROR_NULL;
			}

			if (dto.size() > 0) {
				return CommonUtil.ERROR_DUBLICATE;
			}
		}

		if (typeValidate.equals("CHECK_DATA")) {
			if (CommonUtil.isNullImport(data)) {
				return CommonUtil.ERROR_NULL;
			}

			if (dto == null || dto.size() == 0) {
				return CommonUtil.ERROR_NOT_FOUND;
			}
		}
		return err;
	}

	public List<TypeMachineDTO> getTypeMachineForAutoComplete(TypeMachineDTO obj) {
		return typeMachineDAO.getTypeMachineForAutoComplete(obj);
	}
	
	public List<TypeMachineDTO> getTypeMachineParentForAutoComplete(TypeMachineDTO obj) {
		return typeMachineDAO.getTypeMachineParentForAutoComplete(obj);
	}
}
