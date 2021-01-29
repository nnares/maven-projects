package com.nish.boot.model;

public class ActorV1 {

    String name;

    public ActorV1() {
    }

    public ActorV1(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ActorV1{" +
                "name='" + name + '\'' +
                '}';
    }
}
