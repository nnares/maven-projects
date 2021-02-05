package com.nish.springannotation.atcomponent;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component("myPhone")
public class Phone {

    private String name;
    private String processor;

    // get any contact randomly
    public String getContactRandomly() {

        List<String> contacts = Arrays.asList("Peter", "Roland", "Lucy", "Robert", "Jane", "Rohit", "Shikhar", "Kohli", "Iyyar", "Rahul");
        int i =  new Random().nextInt(contacts.size());
        return contacts.get(i);
    }

    public String getProcessor() {
        return processor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public void setDummyValue() {
        setName("iphone x");
        setName("x 856");
        System.out.println("name - " + getName());
        System.out.println("processor - " + getProcessor());
    }

}
