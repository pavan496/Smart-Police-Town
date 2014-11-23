package com.dh.spt.db;

import java.util.List;

import org.hibernate.Session;

import com.dh.spt.db.model.ComplaintTypes;

/**
 * DAO Class to handle all executions related to Complaints table.
 * 
 * @author pavan
 *
 */
public class ComplaintTypesUtil {

	/**
	 * Returns all complaint types in the table
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ComplaintTypes> getAllComplaintTypes() {
		// Create a session
		Session session = HibernateUtil.getSession();

		// Read all complaint types available
		List<ComplaintTypes> allComplaintTypes = session.createCriteria(
				ComplaintTypes.class).list();

		// Close session
		session.flush();
		session.close();

		// Return complaint types
		return allComplaintTypes;
	}
}
