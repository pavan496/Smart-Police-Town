package com.dh.spt.db;

import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
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
		Session session = HibernateUtil.getSession();
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

	/**
	 * Returns the complaint object by querying based on the incident id
	 * provided. Creates a Hibernate session internally
	 * 
	 * @param incidentid
	 * @return
	 */
	public Complaints getComplaintByComplaintId(String incidentid) {

		// Initiate a Hibernate session.
		Session session = HibernateUtil.getSession();

		// Read complaint object
		Complaints complaint = getComplaintByComplaintId(incidentid, session);

		// Read the complaint images for the complaint. This is needed as in
		// hibernate configuration, lazy loading is enabled for this class
		Hibernate.initialize(complaint.getComplaintImageses());
		// close the session
		session.flush();
		session.close();

		// Return the complaint object
		return complaint;
	}

	/**
	 * 
	 * @param incidentId
	 * @param session
	 * @return
	 */
	public Complaints getComplaintByComplaintId(String incidentId,
			Session session) {
		// Read the query to be fired from named queries
		Query query = session.getNamedQuery("HQL_GET_COMPLAINT_BY_INCIDENT");

		// Add the parameter
		query.setString("IncidentId", incidentId);

		// Read the query
		return (Complaints) query.uniqueResult();

	}

	/**
	 * Get latest complaints logged.
	 * 
	 * @return List of complaints
	 */
	@SuppressWarnings("unchecked")
	public List<Complaints> getLatestComplaints() {

		// Initiate a Hibernate session.
		Session session = HibernateUtil.getSession();

		// Read the query to be fired from named queries
		Query query = session.getNamedQuery("HQL_GET_LATEST_COMPLAINTS");

		// Fire the query to read the result
		List<Complaints> complaints = query.list();

		// close session.
		session.flush();
		session.close();

		// Return the complaints read from database.
		return complaints;
	}
}
