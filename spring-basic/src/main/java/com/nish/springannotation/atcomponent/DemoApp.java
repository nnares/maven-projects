package com.nish.springannotation.atcomponent;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


// Sample code for @Component annotation
public class DemoApp {

    public static void main(String[] args) {


        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("com.nish.springannotation.atcomponent");
        context.refresh();


        // extracting bean using bean name - battery
        // by default instances of this class have the same name as the class name with a lowercase initial
        Battery battery = context.getBean("battery", Battery.class);
        System.out.println("battery = " + battery);

        // extracting bean using .class object
//        Phone phone = context.getBean(Phone.class);

//        Phone phone = (Phone) context.getBean("myPhone");
        Phone phone = context.getBean("myPhone", Phone.class);

        System.out.println(" -------------default values-------------- ");
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
 * battery = Battery{name='DefaultName'}
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