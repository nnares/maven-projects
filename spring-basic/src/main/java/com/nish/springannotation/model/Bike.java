package com.nish.springannotation.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Bike {

    @Autowired
    @Qualifier("bikeEngine")
    private Engine engine;

    // parametrise constructor play a role for  autowiring
/*    public Bike(Engine engine){
        this.engine = engine;
    }*/


    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

}
