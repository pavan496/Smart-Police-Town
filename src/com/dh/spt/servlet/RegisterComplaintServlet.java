package com.dh.spt.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import com.dh.spt.db.HibernateUtil;

@WebServlet("/register-complaint")
public class RegisterComplaintServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegisterComplaintServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String summary = request.getParameter("summary");
		System.out.println(summary);
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();

	/*	Complaint complaint = new Complaint();
		complaint.setSummary(summary);

		session.save(complaint);
		session.getTransaction().commit();
*/
		session.close();

		response.getOutputStream().write("Fuck Yeah!!!".getBytes());

	}

}
