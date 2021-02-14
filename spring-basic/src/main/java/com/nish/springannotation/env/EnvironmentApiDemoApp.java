package com.nish.springannotation.env;

import com.nish.springannotation.atconfiguration.Country;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


//Sample code for Environment var

public class EnvironmentApiDemoApp {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
        appContext.register(BeanConfig.class);
        appContext.refresh();

        // extracting bean using bean name - countryInd
        Country country = appContext.getBean("countryInd", Country.class);
        System.out.println("country = " + country);

        appContext.close();
    }

}