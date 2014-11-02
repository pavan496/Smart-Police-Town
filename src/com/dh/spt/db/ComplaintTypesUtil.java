package com.dh.spt.db;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.dh.spt.db.model.ComplaintTypes;

public class ComplaintTypesUtil {
	@SuppressWarnings("unchecked")
	public List<ComplaintTypes> getAllComplaintTypes() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		Session session = sessionFactory.openSession();
		List<ComplaintTypes> allComplaintTypes = session.createCriteria(
				ComplaintTypes.class).list();
		return allComplaintTypes;
	}
}
