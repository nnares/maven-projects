package com.nish.springannotation.atautowired;


import com.nish.springannotation.atautowired.model.VehicleDriver;
import com.nish.springannotation.atautowired.model.Racer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/*
 *  Sample code for @Primary annotation
 *
 *				->Car	->	VehicleDriver
 *	Vehicle -	|
 *				->F1Car	->	Racer
 *
 * */
public class PrimaryAnnotationDemoApp {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
//        appContext.register(Car.class, F1Car.class, VehicleDriver.class, Racer.class);
        appContext.scan("com.nish.springannotation.atautowired.model");
        appContext.refresh();

        //  @Primary come in play
        // bcus of @Primary,  @Autowired will always binds the Car.class object for any Vehicle type object
        Racer racer = appContext.getBean(Racer.class);
        racer.drive();

        //  @Primary come in play
        // bcus of @Primary,  @Autowired will always binds the Car.class object for any Vehicle type object
        VehicleDriver vehicleDriver = appContext.getBean(VehicleDriver.class);
        vehicleDriver.drive();

    }

}
