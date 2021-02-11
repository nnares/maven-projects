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

    private static void executeSQL(Connection conn){

        System.out.println("-----------------------Contact table data-------------------");
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from contact");

            while (rs.next()){
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
 *  country = Country{name='India', ccy='Rs'}
 *  TradeFeed for productName - Option is been loaded for the date of - 01/01/2020
 *
 * */