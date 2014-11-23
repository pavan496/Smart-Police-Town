package com.dh.spt.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Utility class for any common hibernate activities.
 * 
 * @author pavan
 *
 */
public class HibernateUtil {

	private static SessionFactory sessionFactory = null;

	/**
	 * Initiate hibernate session when server boots up
	 */
	static {
		initiateSessionFactory();
	}

	/**
	 * Creates a hibernate session based on the properties and connection.
	 */
	private static void initiateSessionFactory() {
		Configuration configuration = new Configuration();
		configuration.configure();
		StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}

	/**
	 * Returns a connected hibernate session.
	 * @return
	 */
	public static Session getSession() {
		if (sessionFactory == null)
			initiateSessionFactory();

		return sessionFactory.openSession();
	}

}