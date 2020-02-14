package com.toprate.mct.business;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
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
import org.apache.poi.ss.util.CellRangeAddressList;
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
import com.toprate.mct.bo.Model;
import com.toprate.mct.dao.ManufacturerDAO;
import com.toprate.mct.dao.ModelDAO;
import com.toprate.mct.dao.TypeMachineDAO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;

@Service("modelBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ModelBusinessImpl extends BaseFWBusinessImpl<ModelDAO, ModelDTO, Model> implements ModelBusiness {
	
	private static final Logger LOGGER = Logger.getLogger(ModelBusinessImpl.class);
	@Value("${folder_upload}")
	private String folderUpload;
	
	@Autowired
	private ModelDAO modelDAO;
	
	@Autowired
	private ManufacturerDAO manufacturerDAO;
	
	@Autowired
	private TypeMachineDAO typeMachineDAO;

	@Override
	public long getTotal() {
		return modelDAO.count("AdClientBO", null);
	}

	public DataListDTO doSearch(ModelDTO obj) {
		List<ModelDTO> ls = modelDAO.doSearch(obj);
		DataListDTO data = new DataListDTO();
		data.setData(ls);
		data.setTotal(obj.getTotalRecord());
		data.setSize(obj.getTotalRecord());
		data.setStart(1);
		return data;
	}

	public void delete(ModelDTO obj, HttpServletRequest request) {
		modelDAO.delete(obj);
	}

	public void add(ModelDTO obj, HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		UsersDTO userDto = (UsersDTO) httpSession.getAttribute("userInfo");
		obj.setCreatedBy(userDto.getUserId());
		obj.setCreatedDate(new Date());
		modelDAO.save(obj.toModel());
		
	}

	public void update(ModelDTO obj, HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		UsersDTO userDto = (UsersDTO) httpSession.getAttribute("userInfo");
		Model model = modelDAO.get(Model.class, obj.getId());
		model.setManufacturerId(obj.getManufacturerId());
		model.setUpdatedBy(userDto.getUserId());
		model.setUpdatedDate(new Date());
		model.setCode(obj.getCode());
		model.setName(obj.getName());
		model.setDescription(obj.getDescription());
		modelDAO.saveOrUpdate(model);
	}
	
	public List<ModelDTO> getModelForAutoComplete(ModelDTO obj) {
		List<ModelDTO> list = modelDAO.getModelForAutoComplete(obj);
		return list;
	}
	
	
	@Transactional
	public ValidateDataImportDTO importCells(ValidateDataImportDTO dataImport,HttpServletRequest request) throws Exception {
		HttpSession httpSession = request.getSession();
		UsersDTO userDto = (UsersDTO) httpSession.getAttribute("userInfo");
		try {
			String fileName = folderUpload + File.separatorChar+ UEncrypt.decryptFileUploadPath(dataImport.getPath());
			ValidateDataImportDTO dataImportDTO=new ValidateDataImportDTO();
			FileInputStream file = new FileInputStream(new File(fileName));
			String err="";
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
			
			
			ModelDTO modelDTO= new ModelDTO();
			List<Model> models=Lists.newArrayList();
			
			int count = 0;
			Row rowTitle= sheet.getRow(2);
			sheet.autoSizeColumn(6);
			
			DataFormatter formatter = new DataFormatter();
			boolean check=false;
			List<String> listCode=Lists.newArrayList();
			for (Row row : sheet) {
				modelDTO= new ModelDTO();
				 err="";
				 count++;
				if (count > 3 && !CommonUtil.isRowEmpty(row)) {
					err=getValueImport(rowTitle, row, formatter,modelDTO);
					
					
					
					if(StringUtils.isNotEmpty(err)){
						Cell cell=row.createCell(6);
						cell.setCellValue(err);
						cell.setCellStyle(cellStyle);
						check=true;
					}  else if(listCode.contains(modelDTO.getCode())){
						err+=" Mã model đã tồn tại trong file import!";
						Cell cell=row.createCell(6);
						cell.setCellValue(err);
						cell.setCellStyle(cellStyle);
						check=true;
					} else{
						modelDTO.setCreatedDate(new Date());
						modelDTO.setCreatedBy(userDto.getUserId());
						models.add(modelDTO.toModel());
					}
					
					listCode.add(modelDTO.getCode());
				}
				
			}
			
			String path = CommonUtil.customUploadFix(workbook, "Imp_MODEL_ERROR.xlsx", "default", folderUpload);
			
			dataImportDTO.setError(check);
			dataImportDTO.setPath(UEncrypt.encryptFileUploadPath(path));
			workbook.close();
			file.close();

			modelDAO.saveList(models);
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

	
	private String getValueImport(Row rowTitle,Row row,DataFormatter formatter,ModelDTO dto) {
		String err="";
		String errone="";
		String itemCode="";
			/*Tên model*/
			errone=validateImport("CHECK_NULL", formatter.formatCellValue(row.getCell(1)),null);
			if(StringUtils.isNotEmpty(errone)){
				Cell cellTitle=rowTitle.getCell(1);
				err+=errone +" "+formatter.formatCellValue(cellTitle)+";";
			} else{
				dto.setName(formatter.formatCellValue(row.getCell(1)).trim());
			}
			/*2. Mã model*/
			itemCode=formatter.formatCellValue(row.getCell(2)).replace("\n", "").replace("\r", "");
			List<ModelDTO> list= modelDAO.getByCode(itemCode);
			errone=validateImport("CHECK_DUPLICATE", itemCode,list);
			if(StringUtils.isNotEmpty(errone)){
				Cell cellTitle=rowTitle.getCell(2);
					err+=errone +" "+formatter.formatCellValue(cellTitle)+";";
			} else {
				dto.setCode(formatter.formatCellValue(row.getCell(2)).trim());
			}
			/*3. hãng*/
			List<ManufacturerDTO> manufacturerDTOs=manufacturerDAO.getByCode(formatter.formatCellValue(row.getCell(3)));
			errone=validateImport("CHECK_DATA", formatter.formatCellValue(row.getCell(3)).trim(),manufacturerDTOs);
			if(StringUtils.isNotEmpty(errone)){
				Cell cellTitle=rowTitle.getCell(3);
					err+=errone +" "+formatter.formatCellValue(cellTitle)+";";
			}else {
				dto.setManufacturerId(manufacturerDTOs.get(0).getId());
			}
			/*4. Loại máy*/
			List<TypeMachineDTO> machineDTOs=typeMachineDAO.getByCode(formatter.formatCellValue(row.getCell(4)));
			errone=validateImport("CHECK_DATA", formatter.formatCellValue(row.getCell(4)).trim(),machineDTOs);
			if(StringUtils.isNotEmpty(errone)){
				Cell cellTitle=rowTitle.getCell(4);
					err+=errone +" "+formatter.formatCellValue(cellTitle)+";";
			}else {
				dto.setTypeMachineId(machineDTOs.get(0).getId());
			}
			
			/*5. Mô tả*/
			dto.setDescription(formatter.formatCellValue(row.getCell(5)));
			
		
		
		return err;
	}
	
	private String validateImport(String typeValidate,String data,List<? extends Object>  dto){
		String err="";
		if(typeValidate.equals("CHECK_NULL") && CommonUtil.isNullImport(data)){
			return CommonUtil.ERROR_NULL;
		}
		if(typeValidate.equals("CHECK_NUMBER") && CommonUtil.isNotNumberImport(data)){
			return CommonUtil.ERROR_NOT_FEILD;
		}
		if(typeValidate.equals("CHECK_DUPLICATE") ) {
			if(CommonUtil.isNullImport(data)){
				return CommonUtil.ERROR_NULL;
			}
			
			if(dto.size()>0 ){
				return CommonUtil.ERROR_DUBLICATE;
			}
		}
		
		if(typeValidate.equals("CHECK_DATA") ){
			if(CommonUtil.isNullImport(data)){
				return CommonUtil.ERROR_NULL;
			}
			
			if(dto==null || dto.size()==0 ){
				return CommonUtil.ERROR_NOT_FOUND;
			}
		}
		return err;
	}
	

}
