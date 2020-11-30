package com.nish.ems.util;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import com.nish.ems.model.Employee;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class EmployeeUtil {
	

    private SessionFactory buildSessionFactory() {

		SessionFactory sessionFactory = null;
        try {
            // Create the SessionFactory from hibernate.cfg.xml
/*
			Configuration configuration = new Configuration();
        	configuration.configure("hibernate.cfg.xml");
        	System.out.println("Hibernate Configuration loaded");

        	ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        	System.out.println("Hibernate serviceRegistry created");

        	sessionFactory = configuration.buildSessionFactory(serviceRegistry);
*/

			sessionFactory = new Configuration().configure().buildSessionFactory();

        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
		return sessionFactory;
    }



	/* Method to CREATE an employee in the database */
	public Integer addEmployee(Employee employee) {
		SessionFactory sessionFactory = buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Integer employeeID = null;

		try {
			tx = session.beginTransaction();
			employeeID = (Integer) session.save(employee);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
			sessionFactory.close();
		}
		return employeeID;
	}

	/* Method to  READ all the employees */
	public void listEmployees() {
		SessionFactory sessionFactory = buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			List employees = session.createQuery("FROM Employee").list();
			for (Iterator iterator = employees.iterator(); iterator.hasNext(); ) {
				Employee employee = (Employee) iterator.next();
				System.out.println(" employee : " + employee);
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
			sessionFactory.close();
		}
	}

	/* Method to UPDATE salary for an employee */
	public void updateEmployee(Employee employee) {
		SessionFactory sessionFactory = buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Employee dbEmployee = (Employee) session.get(Employee.class, employee.getId());
			dbEmployee.setSalary(employee.getSalary());
			session.update(dbEmployee);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
			sessionFactory.close();
		}
	}

	/* Method to DELETE an employee from the records */
	public void deleteEmployee(Integer id) {
		SessionFactory sessionFactory = buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Employee employee = (Employee) session.get(Employee.class, id);
			session.delete(employee);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
			sessionFactory.close();
		}
	}

	
}
