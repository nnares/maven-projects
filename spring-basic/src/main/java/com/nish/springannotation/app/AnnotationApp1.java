package com.nish.springannotation.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackages = "com.nish.springannotation.model")
@Configuration
public class AnnotationApp1 {

    public static void main(String[] args) {


        ApplicationContext context = new AnnotationConfigApplicationContext(AnnotationApp1.class);

//        Driver driver = (Driver) context.getBean("driver");
//        driver.drive();

        for (String beanName : context.getBeanDefinitionNames()) {
            System.out.println(beanName);
        }

    }
}
