package com.nish.springannotation.model;

import org.springframework.stereotype.Component;

@Component
public class F1Car implements Vehicle {
    @Override
    public void move() {
        System.out.println("F1Car is moving");
    }
}