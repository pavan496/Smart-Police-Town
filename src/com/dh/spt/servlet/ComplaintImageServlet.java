package com.dh.spt.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dh.spt.db.ComplaintImagesUtil;

/**
 * Servlet class to return image for a complaint based on the image id. The
 * image is read from database and is written to output stream.
 */
@WebServlet("/complaint-image")
public class ComplaintImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ComplaintImageServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Read the request parameter
		String imageId = request.getParameter("id");
		ComplaintImagesUtil ciUtil = new ComplaintImagesUtil();

		// Read the image data from database
		byte[] imageData = ciUtil.getImageData(Integer.parseInt(imageId));

		// If image data is found, write the same. Else, blank page is returned.
		if (imageData != null) {
			// Setting the content type to image.
			response.setContentType("image/gif");
			response.getOutputStream().write(imageData);
		}

	}

}
