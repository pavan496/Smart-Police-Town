package com.dh.spt.db;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.dh.spt.db.model.ComplaintTypes;

public class HibernateUtil {

	public static SessionFactory getSessionFactory() {
		Configuration configuration = new Configuration();
		configuration.addAnnotatedClass(ComplaintTypes.class);
		configuration.configure();
		StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration
				.buildSessionFactory(serviceRegistry);
		return sessionFactory;
	}
}