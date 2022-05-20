package com.nishchay.sample;

public abstract class AbstractGreeting {

    public String greet() {
        return "Hii.. " + getName() + symbol();
    }

    private String symbol() {
        return "!!";
    }

    protected abstract String getName();
}