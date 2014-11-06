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

@WebServlet("/register-complaint")
public class RegisterComplaintServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegisterComplaintServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String complaintType = request.getParameter("complaintType");
		String incidentLocation = request.getParameter("incidentLocation");
		String incidentDateStr = request.getParameter("incidentDate");
		String reportedBy = request.getParameter("reportedBy");
		String contactNo = request.getParameter("contactNo");
		String emailAddress = request.getParameter("emailAddress");
		String incidentSummary = request.getParameter("summary");
		String reportedLocation = request.getParameter("reportedLocation");

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm");

		Date incidentDate;
		try {
			incidentDate = formatter.parse(incidentDateStr);
		} catch (ParseException e) {
			throw new ServletException("Invalid Date Format.");
		}

		if (incidentDate.after(new Date()))
			throw new ServletException(
					"Incident Date cannot be greater than today.");

		ComplaintsUtil complaintsUtil = new ComplaintsUtil();
		String incidentId = complaintsUtil.createNewComplaint(complaintType,
				incidentLocation, incidentDate, incidentSummary, reportedBy,
				contactNo, emailAddress, reportedLocation);

		String respStr = "{\"incidentid\":\"" + incidentId + "\"}";

		response.setContentType("application/json");

		response.getOutputStream().write(respStr.getBytes());
	}

}
