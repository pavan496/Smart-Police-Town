package com.dh.spt.db;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Utility class for any common hibernate activities
 * 
 * @author pavan
 *
 */
public class HibernateUtil {

	public static SessionFactory getSessionFactory() {
		Configuration configuration = new Configuration();		
		configuration.configure();
		StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration
				.buildSessionFactory(serviceRegistry);
		return sessionFactory;
	}
}