package com.nish.springannotation.atautowired;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackages = "com.nish.springannotation.atautowired.model")
@Configuration
public class ContextAllBeanDemo {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(ContextAllBeanDemo.class);

        for (String beanName : context.getBeanDefinitionNames()) {
            System.out.println(beanName);
        }

    }
}
