package com.nish.springannotation.atautowired;

import com.nish.springannotation.atautowired.model.Bike;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlApp {


    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        Bike bike = (Bike) context.getBean("bike");
        System.out.println("bike = " + bike);
    }

}
// o/p =>  bike = Bike{engineCapacity=EngineCapacity{horsePower=220}}
