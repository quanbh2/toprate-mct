package com.toprate.mct.rest;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.activation.DataHandler;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.toprate.base.common.BusinessException;
import com.toprate.base.common.UEncrypt;
import com.toprate.base.common.UFile;
import com.toprate.base.common.UString;

/**
 * File server common
 * @author quangtao
 *
 */
public class FileCustomerServerImpl implements FileCustomerServer {
	
	@Value("${folder_upload2}")
	private String folderUpload;

	@Value("${folder_upload}")
	private String folderTemp;

	@Value("${default_sub_folder_upload}")
	private String defaultSubFolderUpload;

	@Value("${allow.file.ext}")
	private String allowFileExt;
	@Value("${allow.folder.dir}")
	private String allowFolderDir;

	static Logger LOGGER = LoggerFactory.getLogger(FileCustomerServerImpl.class);
	
	@Override
	public Response downloadFileImport(HttpServletRequest request) throws Exception {
		String fileName = UEncrypt.decryptFileUploadPath(request.getQueryString());
		if (StringUtils.isEmpty(fileName)) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}

		File file;
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		String filePath = classloader.getResource("../").getPath();
		file = new File(filePath + "/doc-template" + File.separatorChar + fileName);

		if (!file.exists()) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}

		return Response.ok((Object) file)
				.header("Content-Disposition", "attachment; filename=\"" + FilenameUtils.getName(fileName) + "\"")
				.build();
	}
	
	
	@Override
	public Response uploadATTT(List<Attachment> attachments, HttpServletRequest request) {
		String folderParam = UString.getSafeFileName(request.getParameter("folder"));
		String filePathReturn;
		Map<String, List> returnMap = new HashMap();
		List<String> listFilePathReturn = new ArrayList<String>();
		if (!StringUtils.isNotEmpty(folderParam)) {
			folderParam = defaultSubFolderUpload;
		} else {
			if (!isFolderAllowFolderSave(folderParam)) {
				throw new BusinessException("folder khong nam trong white list: folderParam=" + folderParam);
			}
		}

		for (Attachment attachment : attachments) {
			DataHandler dataHandler = attachment.getDataHandler();

			// get filename to be uploaded
			MultivaluedMap<String, String> multivaluedMap = attachment.getHeaders();
			String fileName = UFile.getFileName(multivaluedMap);

			if (!isExtendAllowSave(fileName)) {
				throw new BusinessException("File extension khong nam trong list duoc up load, file_name:" + fileName);
			}
			// write & upload file to server
			try (InputStream inputStream = dataHandler.getInputStream();) {
				String filePath = UFile.writeToFileServerATTT2(inputStream, fileName, folderParam, folderUpload);
				filePathReturn = UEncrypt.encryptFileUploadPath(filePath);
				listFilePathReturn.add(filePathReturn);
			} catch (Exception ex) {
				throw new BusinessException("Loi khi save file", ex);
			}
		}
		returnMap.put("data", listFilePathReturn);
		return Response.ok(listFilePathReturn).build();
	}
	
	@Override
	public Response uploadImgATTT(List<Attachment> attachments, HttpServletRequest request) {
		String folderParam = UString.getSafeFileName(request.getParameter("folder"));
		String filePathReturn;
		Map<String, List> returnMap = new HashMap();
		List<String> listFilePathReturn = new ArrayList<String>();
		if (!StringUtils.isNotEmpty(folderParam)) {
			folderParam = defaultSubFolderUpload;
		} else {
			if (!isFolderAllowFolderSave(folderParam)) {
				throw new BusinessException("folder khong nam trong white list: folderParam=" + folderParam);
			}
		}

		for (Attachment attachment : attachments) {
			DataHandler dataHandler = attachment.getDataHandler();

			// get filename to be uploaded
			MultivaluedMap<String, String> multivaluedMap = attachment.getHeaders();
			String fileName = UFile.getFileName(multivaluedMap);

			if (!isExtendAllowSave(fileName)) {
				throw new BusinessException("File extension khong nam trong list duoc up load, file_name:" + fileName);
			}
			// write & upload file to server
			try (InputStream inputStream = dataHandler.getInputStream();) {
				InputStream newInputStream=resizeImage(inputStream, fileName, 400, 300);
				String filePath = UFile.writeToFileServerATTT2(newInputStream, fileName, folderParam, folderUpload);
				filePathReturn = UEncrypt.encryptFileUploadPath(filePath);
				listFilePathReturn.add(filePathReturn);
			} catch (Exception ex) {
				throw new BusinessException("Loi khi save file", ex);
			}
		}
		returnMap.put("data", listFilePathReturn);
		return Response.ok(listFilePathReturn).build();
	}

	@Override
	public Response uploadTemp(List<Attachment> attachments, HttpServletRequest request) {
		String folderParam = request.getParameter("folder");
		String filePathReturn;
		Map<String, List> returnMap = new HashMap();
		List<String> listFilePathReturn = new ArrayList<String>();
		if (UString.isNullOrWhitespace(folderParam)) {
			folderParam = defaultSubFolderUpload;
		} else {
			if (!isFolderAllowFolderSave(folderParam)) {
				throw new BusinessException("folder khong nam trong white list: folderParam=" + folderParam);
			}
		}

		for (Attachment attachment : attachments) {
			DataHandler dataHandler = attachment.getDataHandler();
			try (InputStream inputStream = dataHandler.getInputStream();) {
				// get filename to be uploaded
				MultivaluedMap<String, String> multivaluedMap = attachment.getHeaders();
				String fileName = UFile.getFileName(multivaluedMap);

				if (!isExtendAllowSave(fileName)) {
					throw new BusinessException(
							"File extension khong nam trong list duoc up load, file_name:" + fileName);
				}
				// write & upload file to server
				String filePath = UFile.writeToFileServerATTT(inputStream, fileName, folderParam, folderTemp);
				filePathReturn = UEncrypt.encryptFileUploadPath(filePath);
				listFilePathReturn.add(filePathReturn);
			} catch (Exception ex) {
				throw new BusinessException("Loi khi save file", ex);
			}
		}
		returnMap.put("data", listFilePathReturn);
		return Response.ok(listFilePathReturn).build();
	}

	@Override
	public Response downloadFileATTT(HttpServletRequest request) throws Exception {
		String fileName = UEncrypt.decryptFileUploadPath(request.getQueryString());
		File file = new File(folderTemp + File.separatorChar + fileName);
		if (!file.exists()) {
			file = new File(folderUpload + File.separatorChar + fileName);
			if (!file.exists()) {
				LOGGER.warn("File {} is not found", fileName);
				return Response.status(Response.Status.BAD_REQUEST).build();
			}

		}
		int lastIndex = fileName.lastIndexOf(File.separatorChar);
		String fileNameReturn = fileName.substring(lastIndex + 1);

		return Response.ok((Object) file)
				.header("Content-Disposition", "attachment; filename=\"" + fileNameReturn + "\"").build();
	}
	
	

	private boolean isFolderAllowFolderSave(String folderDir) {
		return UString.isFolderAllowFolderSave(folderDir, allowFolderDir);

	}

	private boolean isExtendAllowSave(String fileName) {
		return UString.isExtendAllowSave(fileName, allowFileExt);
	}
	
	
	private static InputStream resizeImage(InputStream uploadedInputStream, String fileName, int width, int height) {

        try {
            BufferedImage image = ImageIO.read(uploadedInputStream);
            Image originalImage= image.getScaledInstance(width, height, Image.SCALE_DEFAULT);
            String text = "sanmaycongtrinh.com";

            int type = ((image.getType() == 0) ? BufferedImage.TYPE_INT_ARGB : image.getType());
            BufferedImage resizedImage = new BufferedImage(width, height, type);

            Graphics2D g2d = resizedImage.createGraphics();
            g2d.drawImage(originalImage, 0, 0, width, height, null);
          
            
            Font font = new Font("Arial", Font.ITALIC, 36);
            g2d.setFont(font);
            FontMetrics fm = g2d.getFontMetrics();
            fm = g2d.getFontMetrics();
            g2d.setColor(Color.darkGray);
            g2d.drawString(text, 0, fm.getAscent());
          
            
            g2d.setComposite(AlphaComposite.Src);
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.dispose();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            String[] arr = fileName.split("\\.");
            fileName = arr[arr.length -1];
            ImageIO.write(resizedImage, fileName, byteArrayOutputStream);
            return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            // Something is going wrong while resizing image
            return uploadedInputStream;
        }
    }
	
}
