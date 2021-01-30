package com.nish.springannotation.app;

import com.nish.springannotation.model.Phone;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


// Sample code for @Component annotation
public class AnnotationApp2 {

    public static void main(String[] args) {


        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("com.nish.springannotation.model");
        context.refresh();

        Phone phone = context.getBean(Phone.class);

        System.out.println(" -------------dafault values-------------- ");
        System.out.println("name - " + phone.getName());
        System.out.println("processor - " + phone.getProcessor());

        System.out.println(" --------------DummyValue---------------- ");
        phone.setDummyValue();

        System.out.println(" -------------ContactRandomly---------------- ");
        System.out.println("Generating random name : " + phone.getContactRandomly());
        System.out.println("Generating random name : " + phone.getContactRandomly());
        System.out.println("Generating random name : " + phone.getContactRandomly());
        System.out.println("Generating random name : " + phone.getContactRandomly());
        System.out.println("Generating random name : " + phone.getContactRandomly());
        System.out.println("Generating random name : " + phone.getContactRandomly());
        context.close();

    }

}

/*
 *  -------------dafault values--------------
 * name - null
 * processor - null
 *  --------------DummyValue----------------
 * name - x 856
 * processor - null
 *  -------------ContactRandomly----------------
 * Generating random name : Iyyar
 * Generating random name : Rahul
 * Generating random name : Jane
 * Generating random name : Rahul
 * Generating random name : Rohit
 * Generating random name : Jane
 *
 * */