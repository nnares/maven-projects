package com.nish.springannotation.app;

import com.nish.springannotation.model.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotationApp {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
        appContext.register(Car.class, F1Car.class, Driver.class, Racer.class);
        appContext.refresh();

        Driver driver = appContext.getBean(Driver.class);
        driver.drive();
    }
}
