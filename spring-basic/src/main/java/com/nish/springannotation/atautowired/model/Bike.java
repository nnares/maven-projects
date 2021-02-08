package com.nish.springannotation.atautowired.model;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/*
 * Class hierarchy
 *
 *	    Bike {
 *         EngineCapacity engineCapacity
 *      }
 *
 * */

@Component
public class Bike {

    // Field injection
    @Autowired
    @Qualifier("bikeEngine")
    private EngineCapacity engineCapacity;


    // parametrise constructor play a role for autowiring
    // Constructor injection
/*
    public Bike(EngineCapacity engineCapacity){
        this.engineCapacity = engineCapacity;
    }
*/

    public EngineCapacity getEngineCapacity() {
        return engineCapacity;
    }

    //Setter injection
    @Autowired
    public void setEngineCapacity(EngineCapacity engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    @Override
    public String toString() {
        return "Bike{" +
                "engineCapacity=" + engineCapacity +
                '}';
    }

}
