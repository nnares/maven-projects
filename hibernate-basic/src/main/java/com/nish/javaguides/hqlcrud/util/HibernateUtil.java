package com.nish.javaguides.hqlcrud.util;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.nish.javaguides.hqlcrud.entity.Student;

public class HibernateUtil {

	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {

		if (sessionFactory == null) {
			try {

			/*	Another way to populate Configuration out of -  hibernate.cfg.xml
				Configuration configObj = new Configuration();
				configObj.configure("hibernate.cfg.xml");
			*/

				Configuration configuration = new Configuration();

				// Hibernate settings equivalent to hibernate.cfg.xml's properties
				Properties settings = new Properties();
				settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
				settings.put(Environment.URL, "jdbc:mysql://localhost:3306/poc");
				settings.put(Environment.USER, "root");
				settings.put(Environment.PASS, "root");
				settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");

				settings.put(Environment.SHOW_SQL, "true");

				settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

				settings.put(Environment.HBM2DDL_AUTO, "create-drop");
//				settings.put(Environment.CACHE_PROVIDER_CONFIG, "org.hibernate.cache.internal.NoCachingRegionFactory");

				configuration.setProperties(settings);
				
				configuration.addAnnotatedClass(Student.class);

				// Since Hibernate Version 4.x, ServiceRegistry Is Being Used
				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties()).build();

				sessionFactory = configuration.buildSessionFactory(serviceRegistry);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sessionFactory;
	}
}