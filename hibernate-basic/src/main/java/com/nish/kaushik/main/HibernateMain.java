package com.nish.kaushik.main;

import com.nish.kaushik.dto.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class HibernateMain {

    public static void main(String[] args) {

//        entityObject_write_read();
//        embeddableObject_UserWithAddress_w_r();
//        embeddableObject_UserWith2Address_w_r();
        embeddableObject_UserWithListOfAddresses_w_r();



    }


    private static <T> void hibernateWriteReadDB(T objectToSave) {

        Class clazzObject = objectToSave.getClass();
        System.out.println("clazzObject = " + clazzObject);

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
//            sessionFactory.close();
        }

        // Entity object read
        session = null;
        objectToSave = null;
        session = sessionFactory.openSession();
        session.beginTransaction();
        objectToSave = (T) session.get(clazzObject, 1);

        System.out.println("Read form DB - " + objectToSave);

        session.close();
        sessionFactory.close();
    }


    private static void entityObject_write_read(){
        UserDetails userDetails =  new UserDetails();
        userDetails.setId(1);
        userDetails.setName("First User");
        userDetails.setCountry("India");

        hibernateWriteReadDB(userDetails);

    }


    private static void embeddableObject_UserWithAddress_w_r() {

        // Here Embedded object is having only one Embeddable object
        User user =  new User();
        user.setName("First User");
        user.setPhone("999999999");

        Address address =  new Address();
        address.setStreet("Street 1");
        address.setCity("Bangalore");
        address.setState("Karnataka");
        address.setPinCode("560035");

        user.setAddress(address);

        hibernateWriteReadDB(user);

    }


    private static void embeddableObject_UserWith2Address_w_r() {
        // Here Embedded object is having two Embeddable object
        User2Add user =  new User2Add();
        user.setName("First User");
        user.setPhone("999999999");


        Address homeAdd =  new Address();
        homeAdd.setStreet("Gandhi Chowk");
        homeAdd.setCity("Patna");
        homeAdd.setState("Bihar");
        homeAdd.setPinCode("800006");
        user.setHomeAddress(homeAdd);

        Address officeAddress =  new Address();
        officeAddress.setStreet("Street 1");
        officeAddress.setCity("Bangalore");
        officeAddress.setState("Karnataka");
        officeAddress.setPinCode("560035");
        user.setOfficeAddress(officeAddress);

        hibernateWriteReadDB(user);

    }

    private static void embeddableObject_UserWithListOfAddresses_w_r() {
        // Here Embedded object is having List of Embeddable object
        UserListAdd user =  new UserListAdd();
        user.setName("First User");
        user.setPhone("999999999");

        Address add1 =  new Address();
        add1.setStreet("Gandhi Chowk");
        add1.setCity("Patna");
        add1.setState("Bihar");
        add1.setPinCode("800006");

        Address add2 =  new Address();
        add2.setStreet("Street 1");
        add2.setCity("Bangalore");
        add2.setState("Karnataka");
        add2.setPinCode("560035");

        user.getListOfAddresses().add(add1);
        user.getListOfAddresses().add(add2);


        hibernateWriteReadDB(user);

    }
}
