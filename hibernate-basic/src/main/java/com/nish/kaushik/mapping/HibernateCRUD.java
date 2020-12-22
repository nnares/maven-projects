package com.nish.kaushik.mapping;

import com.nish.kaushik.dto.UserDetails;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

// hibernate.hbm2ddl.auto - update should
public class HibernateCRUD {

    public static void main(String[] args) {

        // C - CREATE
        createObjectList();

        // R - RETRIEVE
        retrieveObject();

        // U - UPDATE
        updateObject();

        // D- DELETE
        deleteObject();
    }


    private static void createObjectList() {

        UserDetails userDetails;

        for (int i = 1; i <= 10; i++) {
            userDetails =  new UserDetails();
            userDetails.setId(i);
            userDetails.setName("User " + i);
            userDetails.setCountry("Country " + i);
            saveObjectDB(userDetails);

        }

    }


    private static <T> void saveObjectDB(T objectToSave) {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        // Entity object write
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(objectToSave);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }

    }

    private static void retrieveObject() {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        UserDetails userDetails = session.get(UserDetails.class, 5);

        System.out.println("Read form DB - " + userDetails);

        session.close();
        sessionFactory.close();

    }


    private static void updateObject() {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        UserDetails userDetails =  new UserDetails();
        userDetails.setId(5);
        userDetails.setName("Updated user name");
        userDetails.setCountry("updated country ");
        session.update(userDetails);

        session.getTransaction().commit();
        session.close();

        System.out.println("---------updated successfully-------");

        userDetails=null;
        session = sessionFactory.openSession();

        userDetails = session.get(UserDetails.class, 5);

        System.out.println("Read form DB - " + userDetails);

        session.close();
        sessionFactory.close();
    }

    private static void deleteObject() {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        UserDetails userDetails = session.get(UserDetails.class, 10);

        session.delete(userDetails);
        session.getTransaction().commit();

        System.out.println("Deleted row with id - 10");

        session.close();
        sessionFactory.close();

    }


}