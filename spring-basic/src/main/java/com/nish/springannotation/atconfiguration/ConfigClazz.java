package com.nish.springannotation.atconfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigClazz {


    // by default bean name will be - getCountry, same as the method name
    @Bean
    public Country getCountry() {
        return new Country("USA", "USD");
    }

    // here method name is not making any significance(it could be anything). since we are explicitly defining the bean name .
    // only the return type is matters here
    @Bean(name = "countryObj")
    public Country iAmGoingToNoWhere() {
        return new Country("India", "Rs");
    }

    // by default bean name will be - anyMethodName, same as the method name
    @Bean
    public TradeFeedService anyMethodName() {
        TradeFeedService reference = new TradeFeedService();
        reference.setFeedDate("01/01/2020");
        reference.setProductName("Option");
        return reference;
    }

/*
    @Bean
    public DataSource getDataSource(){
        DataSource source = new OracleDataSource();
        source.setURL();
        source.setUser();
        return source;
    }
*/

}

