package com.nish.springannotation.atvalue;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


// Sample code for @Value annotation
public class ValueAnnotationDemoApp {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
        appContext.scan("com.nish.springannotation.atvalue");
        appContext.refresh();

        // extracting bean using bean name - DBConnection (since first two characters are uppercase)
        DBConnection dbConnection = appContext.getBean("DBConnection", DBConnection.class);
        dbConnection.printDBConfig();

        executeSQL(dbConnection.getDBConnection());
        appContext.close();

    }

    private static void executeSQL(Connection conn) {

        System.out.println("-----------------------Contact table data-------------------");
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from contact");

            while (rs.next()) {
                System.out.println(rs.getInt(1) + "  " + rs.getString(2)
                        + "  " + rs.getString(3)
                        + "  " + rs.getString(4) + "  " + rs.getString(5));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}

/*
 * o/p =>
 *
 *	---------------------DB Config Details----------------------
 *	Driver Class = com.mysql.jdbc.Driver
 *	DB URL = jdbc:mysql://localhost:3306/poc
 *	User Name = root
 *	Password = root
 *	--------------------------------------
 *	50  Koyel Chowdhary  koyel.chowdhary@example.com  Street -10, Kolkata  9911991199
 *	51  Subhakankshi Nayak  subhakankshi.nayak@example.com  Street -10, Bhuwneshwar  9922992299
 *	52  Gaurav Tiwari  gaurav.tiwari@example.com  5th Street, Aagra UP  9933993399
 *	53  Alok Kumar  alok.kumar@example.com  Baadh, Bihar  9944994499
 *	54  Subhi Ojha  subhi.ojha@example.com  Kankarbagh, Patna  9955995599
 *	55  Shailendra Pandey  shailendra.pandey@example.com  Satna, MP  9966996699
 * */