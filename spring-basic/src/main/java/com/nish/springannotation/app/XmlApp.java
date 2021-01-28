package com.nish.springannotation.app;

import com.nish.springannotation.model.Bike;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlApp {


    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        Bike bike = (Bike) context.getBean("bike");
        System.out.println("HorsePower - " + bike.getEngine().getHorsePower());
    }

}
