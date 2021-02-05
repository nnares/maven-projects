package com.nish.springannotation.atcomponent;

import org.springframework.stereotype.Component;

// by default bean name will be same name as the class name with a lowercase initial - battery
@Component
public class Battery {
    private String name;

    public Battery() {
        name = "DefaultName";
    }

    @Override
    public String toString() {
        return "Battery{" +
                "name='" + name + '\'' +
                '}';
    }
}
