package com.nish.springannotation.env;

import com.nish.springannotation.atconfiguration.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;


@Configuration
@PropertySource("classpath:application.properties")
public class BeanConfig {

    @Autowired
    Environment env;

    @Bean
    public Country countryInd() {
        Country country = new Country();
        country.setName(env.getProperty("country.name.ind"));
        country.setCcy(env.getProperty("country.name.ccy"));
        return country;
    }

}