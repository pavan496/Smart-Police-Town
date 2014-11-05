package com.dh.spt.db;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		Session session = sessionFactory.openSession();
		List<ComplaintTypes> allComplaintTypes = session.createCriteria(
				ComplaintTypes.class).list();
		return allComplaintTypes;
	}
}
