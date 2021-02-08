package com.nish.springannotation.atautowired.model;

import org.springframework.stereotype.Component;

@Component("bikeEngine")
public class EngineCapacity {

    private int horsePower;

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    @Override
    public String toString() {
        return "EngineCapacity{" +
                "horsePower=" + horsePower +
                '}';
    }
}
