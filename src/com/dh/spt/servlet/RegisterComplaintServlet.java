package com.dh.spt.servlet;

import java.io.IOException;
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
		String incidentDate = request.getParameter("incidentDate");
		String incidentTime = request.getParameter("incidentTime");
		String reportedBy = request.getParameter("reportedBy");
		String contactNo = request.getParameter("contactNo");
		String emailAddress = request.getParameter("emailAddress");
		String incidentSummary = request.getParameter("summary");

		ComplaintsUtil complaintsUtil = new ComplaintsUtil();
		String incidentId = complaintsUtil.createNewComplaint(complaintType,
				incidentLocation, new Date(), incidentSummary, reportedBy,
				contactNo, emailAddress);

		String respStr = "{\"incidentid\":\"" + incidentId + "\"}";

		response.setContentType("application/json");

		response.getOutputStream().write(respStr.getBytes());
	}

}
