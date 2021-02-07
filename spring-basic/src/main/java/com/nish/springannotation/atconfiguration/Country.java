package com.nish.springannotation.atconfiguration;

public class Country {

    private String name;
    private String ccy;

    public Country(String name, String ccy) {
        this.name = name;
        this.ccy = ccy;
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", ccy='" + ccy + '\'' +
                '}';
    }
}
