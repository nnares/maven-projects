package com.nish.kaushik.mapping;

import com.nish.kaushik.dto.Owner;
import com.nish.kaushik.dto.Owner1;
import com.nish.kaushik.dto.Vehicle;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/*
 * 1 -to- 1 mapping : Owner <- Vehicle
 * 1 -to- Many mapping : Owner <- Vehicle
 *
 * */
public class HibernateMapping {

    public static void main(String[] args) {

//        oneToOneMapping();
        oneToManyMapping();
    }

    private static void oneToManyMapping() {

        Owner1 owner = new Owner1();
        owner.setName("First User");
        owner.setPhone("999999999");

        Vehicle v1 = new Vehicle();
        v1.setVehicleName("Car");

        Vehicle v2 = new Vehicle();
        v2.setVehicleName("Jeep");

        Vehicle v3 = new Vehicle();
        v3.setVehicleName("Bus");

        owner.getVehicleList().add(v1);
        owner.getVehicleList().add(v2);
        owner.getVehicleList().add(v3);


        hibernateWriteReadDB(owner);

    }

    private static void oneToOneMapping() {

        Owner owner = new Owner();
        owner.setName("First User");
        owner.setPhone("999999999");

        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleName("Car");
        owner.setVehicle(vehicle);


        hibernateWriteReadDB(owner);

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

}
