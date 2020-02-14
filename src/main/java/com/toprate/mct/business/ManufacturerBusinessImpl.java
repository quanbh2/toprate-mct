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
import com.toprate.mct.bo.Manufacturer;
import com.toprate.mct.dao.ManufacturerDAO;
import com.toprate.mct.dao.ModelDAO;
import com.viettel.service.base.business.BaseFWBusinessImpl;
import com.viettel.service.base.dto.DataListDTO;

@Service("manufacturerBusinessImpl")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ManufacturerBusinessImpl extends BaseFWBusinessImpl<ManufacturerDAO, ManufacturerDTO, Manufacturer>
		implements ManufacturerBusiness {
	
	private static final Logger LOGGER = Logger.getLogger(ManufacturerBusinessImpl.class);
	
	@Value("${folder_upload}")
	private String folderUpload;
	@Autowired
	private ManufacturerDAO manufacturerDAO;
	
	@Autowired
	private ModelDAO modelDAO;

	@Override
	public long getTotal() {
		return manufacturerDAO.count("AdClientBO", null);
	}

	public DataListDTO doSearch(ManufacturerDTO obj) {
		List<ManufacturerDTO> ls = manufacturerDAO.doSearch(obj);
		DataListDTO data = new DataListDTO();
		data.setData(ls);
		data.setTotal(obj.getTotalRecord());
		data.setSize(obj.getTotalRecord());
		data.setStart(1);
		return data;
	}

	public void delete(ManufacturerDTO obj, HttpServletRequest request) {
		manufacturerDAO.delete(obj);

	}

	public void add(ManufacturerDTO obj, HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		UsersDTO userDto = (UsersDTO) httpSession.getAttribute("userInfo");
		obj.setCreatedBy(userDto.getUserId());
		obj.setCreatedDate(new Date());
		manufacturerDAO.saveOrUpdate(obj.toModel());

	}

	public void update(ManufacturerDTO obj, HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		UsersDTO userDto = (UsersDTO) httpSession.getAttribute("userInfo");
		Manufacturer manufacturer = manufacturerDAO.get(Manufacturer.class, obj.getId());
		manufacturer.setCode(obj.getCode());
		manufacturer.setName(obj.getName());
		manufacturer.setDescription(obj.getDescription());
		manufacturer.setUpdateBy(userDto.getUserId());
		manufacturer.setUpdatedDate(new Date());
		manufacturer.setFilePath(obj.getFilePath());
		manufacturerDAO.saveOrUpdate(manufacturer);

	}

	
	public List<ManufacturerDTO> getAllManufacturer(){
		List<ManufacturerDTO> listResult=manufacturerDAO.getAll();
//		List<ModelDTO> listModel=manufacturerDAO.getAll();
//		
//		ManufacturerDTO ab = new ManufacturerDTO();
////		listResult.add(ab);
//		for (Iterator<ModelDTO> interator = listModel.iterator(); interator.hasNext();) {
//			ModelDTO wi = interator.next();
//
//			if (ab.getId() == null) {
//				ab = new ManufacturerDTO();
//				
//				ab.setId(wi.getManufacturerId());
//				ab.setCode(wi.getManufacturerCode());
//				ab.setName(wi.getManufacturerName());
//				ab.setFilePath(wi.getManufacturerFilePath());
//				
//				listResult.add(ab);
//
//			}
//			if (ab.getId().compareTo(wi.getManufacturerId()) != 0) {
//				ab = new ManufacturerDTO();
//				ab.setId(wi.getManufacturerId());
//				ab.setCode(wi.getManufacturerCode());
//				ab.setName(wi.getManufacturerName());
//				ab.setFilePath(wi.getManufacturerFilePath());
//				listResult.add(ab);
//			}
//			if (ab.getId().compareTo(wi.getManufacturerId()) == 0) {
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
	public List<ManufacturerDTO> getAllManufacturerForCombobox(){
		return manufacturerDAO.getAllForCombobox();

	}
	public List<ManufacturerDTO> getManufacturerForAutoComplete(ManufacturerDTO obj) {
		List<ManufacturerDTO> list = manufacturerDAO.getManufacturerForAutoComplete(obj);
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
			
			
			ManufacturerDTO manufacturerDTO= new ManufacturerDTO();
			List<Manufacturer> manufacturerDTOs=Lists.newArrayList();
			
			int count = 0;
			Row rowTitle= sheet.getRow(2);
			DataFormatter formatter = new DataFormatter();
			boolean check=false;
			List<String> listCode=Lists.newArrayList();
			for (Row row : sheet) {
				manufacturerDTO= new ManufacturerDTO();
				 err="";
				 count++;
				if (count > 3 && !CommonUtil.isRowEmpty(row)) {
					err=getValueImport(rowTitle, row, formatter,manufacturerDTO);
					
					
					
					if(StringUtils.isNotEmpty(err)){
						Cell cell=row.createCell(6);
						cell.setCellValue(err);
						cell.setCellStyle(cellStyle);
						check=true;
					}  else if(listCode.contains(manufacturerDTO.getCode())){
						err+=" Mã Hãng sản xuất đã tồn tại trong file import!";
						Cell cell=row.createCell(6);
						cell.setCellValue(err);
						cell.setCellStyle(cellStyle);
						check=true;
					} else{
						manufacturerDTO.setCreatedDate(new Date());
						manufacturerDTO.setCreatedBy(userDto.getUserId());
						manufacturerDTOs.add(manufacturerDTO.toModel());
					}
					
					listCode.add(manufacturerDTO.getCode());
				}
				
			}
			
			String path = CommonUtil.customUploadFix(workbook, "Imp_HANG_SX_ERROR.xlsx", "default", folderUpload);
			
			dataImportDTO.setError(check);
			dataImportDTO.setPath(UEncrypt.encryptFileUploadPath(path));
//			dataImportDTO.setListData(manufacturerDTOs);
			workbook.close();
			file.close();
			
			manufacturerDAO.saveList(manufacturerDTOs);
			
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

	
	private String getValueImport(Row rowTitle,Row row,DataFormatter formatter,ManufacturerDTO dto) {
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
			List<ManufacturerDTO> list= manufacturerDAO.getByCode(itemCode);
			errone=validateImport("CHECK_DUPLICATE", itemCode,list);
			if(StringUtils.isNotEmpty(errone)){
				Cell cellTitle=rowTitle.getCell(2);
					err+=errone +" "+formatter.formatCellValue(cellTitle)+";";
			} else {
				dto.setCode(formatter.formatCellValue(row.getCell(2)).trim());
			}
			
			/*3. Mô tả*/
			dto.setDescription(formatter.formatCellValue(row.getCell(3)));
			
		
		
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
