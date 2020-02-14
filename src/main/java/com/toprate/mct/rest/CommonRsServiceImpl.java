package com.toprate.mct.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Collections;

import javax.ws.rs.core.Response;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.toparte.mct.dto.RolesDTO;
import com.toprate.base.common.CommonUtil;
import com.toprate.base.common.UEncrypt;
import com.toprate.mct.business.ModelBusinessImpl;
import com.toprate.mct.business.RolesBusinessImpl;



public class CommonRsServiceImpl extends AbstractRsService  implements CommonRsService  {

	

	@Autowired
	RolesBusinessImpl rolesBusinessImpl;
	
	@Autowired
	ModelBusinessImpl modelBusinessImpl;
	
	@Value("${folder_upload}")
	private String folderUpload;
	
	
	@Override
	public Response getForAutoCompleteRoles(RolesDTO obj) {
		return Response.ok(rolesBusinessImpl.getForAutoCompleteRoles(obj)).build();
	}



	@Override
	public Response getTemplateImport(String templateName) throws Exception {
		
		InputStream excelFileToRead;
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		String filePath = classloader.getResource("../").getPath();
		excelFileToRead = new FileInputStream(
				filePath + "/doc-template" + File.separatorChar + templateName);
		XSSFWorkbook wb = new XSSFWorkbook(excelFileToRead);

		String path = CommonUtil.customUploadFix(wb, templateName, "default", folderUpload);
		
		
		 path = UEncrypt.encryptFileUploadPath(path);
		return Response.ok(Collections.singletonMap("fileName", path)).build();
	}

	




}
