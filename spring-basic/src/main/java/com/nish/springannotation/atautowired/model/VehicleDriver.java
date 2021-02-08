package com.nish.springannotation.atautowired.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VehicleDriver {
    @Autowired
    private Vehicle vehicle;
    public void drive(){
        vehicle.move();
    }

}
