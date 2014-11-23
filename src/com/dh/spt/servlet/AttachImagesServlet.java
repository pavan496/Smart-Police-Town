package com.dh.spt.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.dh.spt.db.ComplaintImagesUtil;

/**
 * Servlet class to save image attached to a complaint. Apache commons file
 * upload is used to parse the multi part form data.
 * 
 * @author pavan
 *
 */
@WebServlet("/attach-images")
public class AttachImagesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String incidentId;

	public AttachImagesServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Iterator<FileItem> items = null;
		List<FileItem> fileItemList = null;
		HashMap<String, byte[]> files = new HashMap<String, byte[]>();

		// Initializing the file upload components
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);

		// Parse the uploaded multi part request
		try {
			fileItemList = upload.parseRequest(request);
		} catch (FileUploadException e) {
			throw new ServletException("Unable to parse request");
		}

		// In case of any non file upload fields, read the same.
		readFormFieldValues(fileItemList);

		// For each file item found in the upload request
		items = fileItemList.iterator();

		while (items.hasNext()) {
			FileItem thisItem = (FileItem) items.next();
			if (!thisItem.isFormField()) {
				if (!"".equals(thisItem.getName())) {
					// If the file uploaded is not blank, save it to a hashmap
					files.put(thisItem.getName(), thisItem.get());
				}
			}
		}

		// Pass the hashmap to complaint images utility to save the same to
		// database.
		ComplaintImagesUtil complaintImagesUtil = new ComplaintImagesUtil();
		complaintImagesUtil.saveImages(incidentId, files);

	}

	/**
	 * Used to read non upload components of a multipart file upload request
	 * 
	 * @param fileItemList
	 */
	private void readFormFieldValues(List<FileItem> fileItemList) {
		Iterator<FileItem> items = fileItemList.iterator();
		while (items.hasNext()) {
			FileItem thisItem = (FileItem) items.next();
			if (thisItem.isFormField()) {
				switch (thisItem.getFieldName()) {
				case "aiIncId":
					incidentId = thisItem.getString();
					break;
				default:
					break;
				}
			}
		}

	}
}
