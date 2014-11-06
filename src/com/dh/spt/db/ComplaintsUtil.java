package com.dh.spt.db;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dh.spt.db.model.ComplaintTypes;
import com.dh.spt.db.model.Complaints;

/**
 * Utilities class to interact with Complaints table.
 * 
 * @author pavan
 *
 */
public class ComplaintsUtil {

	/**
	 * Inserts a new record into Complaint table and returns the auto generated
	 * complaint id
	 * 
	 * @param complaintType
	 * @param incidentLocation
	 * @param incidentDate
	 * @param incdientSummary
	 * @param reporterName
	 * @param reporterContactNo
	 * @param reporterEmailAddress
	 * @param reportedLocation
	 * @return
	 */
	public String createNewComplaint(String complaintType,
			String incidentLocation, Date incidentDate, String incdientSummary,
			String reporterName, String reporterContactNo,
			String reporterEmailAddress, String reportedLocation) {
		// Create a new session with Hibernate
		Session session = HibernateUtil.getSessionFactory().openSession();
		// Initiate a transaction
		Transaction transaction = session.beginTransaction();

		// Create a new complaint object to store
		Complaints newComplaint = new Complaints();
		newComplaint.setComplaintTypes((ComplaintTypes) session.get(
				ComplaintTypes.class, complaintType));

		newComplaint.setIncidentLocataion(incidentLocation);
		newComplaint.setIncidentTime(incidentDate);
		newComplaint.setReporterContactNo(reporterContactNo);
		newComplaint.setReporterEmailAddress(reporterEmailAddress);
		newComplaint.setReporterName(reporterName);
		newComplaint.setSummary(incdientSummary);
		newComplaint.setReportedLocation(reportedLocation);

		// Save the complaint object
		session.save(newComplaint);

		// Commit and close the session
		transaction.commit();
		session.flush();
		session.close();

		// Return the auto generated complaint id to the server
		return newComplaint.getIncidentId();
	}

	public Complaints getComplaintByComplaintId(String incidentid) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		Query query = session.getNamedQuery("HQL_GET_COMPLAINT_BY_INCIDENT");
		query.setString("IncidentId", incidentid);
		Complaints complaint = (Complaints) query.uniqueResult();
		tx.commit();
		session.flush();
		session.close();

		return complaint;
	}

	@SuppressWarnings("unchecked")
	public List<Complaints> getLatestComplaints() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		Query query = session.getNamedQuery("HQL_GET_LATEST_COMPLAINTS");
		List<Complaints> complaints = query.list();
		tx.commit();
		session.flush();
		session.close();

		return complaints;
	}
}
