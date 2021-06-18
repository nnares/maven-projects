package com.nish.camel.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.Properties;

@Configuration
public class MySqlDBConfig {


    @Value("${mysql.db.driver}")
    private String driverClazz;
    @Value("${mysql.db.jdbc.url}")
    private String dbURL;
    @Value("${mysql.db.username}")
    private String userName;
    @Value("${mysql.db.password}")
    private String password;

    @Bean // bean name will be  - dataSource
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClazz);
        dataSource.setUrl(dbURL);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public FreeMarkerConfigurer freemarkerConfig() {
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.getConfiguration();
//        freeMarkerConfigurer.setConfigLocation("/template/");
        freeMarkerConfigurer.setFreemarkerSettings(new Properties());
        return freeMarkerConfigurer;
    }

}



