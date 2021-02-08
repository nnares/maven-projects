package com.nish.springannotation.atautowired;


import com.nish.springannotation.atautowired.model.Bike;
import com.nish.springannotation.atautowired.model.EngineCapacity;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/*
* To Avail the @Autowired feature - both of your bean (Container & Containing)  should annotated with - @Component
 * Class hierarchy
 *
 *	    Bike {
 *         EngineCapacity engineCapacity
 *      }
 *
 *  @Autowired fpr method parameter is explained alongwith @Value example
 * */
public class AutowiredAnnotationDemoApp {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
        appContext.register(Bike.class, EngineCapacity.class);
        appContext.refresh();

        //  @Autowired come in play
        Bike bike = appContext.getBean("bike", Bike.class);
        System.out.println("bike = " + bike);


    }

}
