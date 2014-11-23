package com.dh.spt.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dh.spt.db.ComplaintsUtil;

/**
 * Servlet used to register a new complaint. Response from the servlet is a json
 * object consisting the incident id generated while registering.
 * 
 * @author pavan
 *
 */
@WebServlet("/register-complaint")
public class RegisterComplaintServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegisterComplaintServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Reading all the input parameters.
		String complaintType = request.getParameter("complaintType");
		String incidentLocation = request.getParameter("incidentLocation");
		String incidentDateStr = request.getParameter("incidentDate");
		String reportedBy = request.getParameter("reportedBy");
		String contactNo = request.getParameter("contactNo");
		String emailAddress = request.getParameter("emailAddress");
		String incidentSummary = request.getParameter("summary");
		String reportedLocation = request.getParameter("reportedLocation");

		// Date formatter for formatting the date passed.
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm");

		// Validations on the date. If the date is passed as a future date,
		// exception will be thrown back.
		Date incidentDate;
		try {
			incidentDate = formatter.parse(incidentDateStr);
		} catch (ParseException e) {
			throw new ServletException("Invalid Date Format.");
		}

		if (incidentDate.after(new Date()))
			throw new ServletException(
					"Incident Date cannot be greater than today.");

		// Creating a new complaint
		ComplaintsUtil complaintsUtil = new ComplaintsUtil();
		String incidentId = complaintsUtil.createNewComplaint(complaintType,
				incidentLocation, incidentDate, incidentSummary, reportedBy,
				contactNo, emailAddress, reportedLocation);

		// Constructing the response json in string format.
		String respStr = "{\"incidentid\":\"" + incidentId + "\"}";

		// Setting the content type to JSON. If this is not set, the response
		// will be treated as string instead of a json object.
		response.setContentType("application/json");

		//Writing the response back.
		response.getOutputStream().write(respStr.getBytes());
	}

}
