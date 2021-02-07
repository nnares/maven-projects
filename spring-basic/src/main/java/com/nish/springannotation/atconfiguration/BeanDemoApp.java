package com.nish.springannotation.atconfiguration;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


// Sample code for @Configuration & @Bean annotation
public class BeanDemoApp {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("com.nish.springannotation.atconfiguration");
        context.refresh();

        // extracting bean using bean name - countryObj
        Country country = context.getBean("countryObj", Country.class);
        System.out.println("country = " + country);

        // extracting bean using bean name - feedService
        // by default instances of this class have the same name as the methodName annotated with @Bean
        TradeFeedService tradeFeedService = context.getBean("feedService", TradeFeedService.class);
        tradeFeedService.loadTradeFeed();

        context.close();

    }

}

/*
 *  country = Country{name='India', ccy='Rs'}
 *  TradeFeed for productName - Option is been loaded for the date of - 01/01/2020
 *
 * */