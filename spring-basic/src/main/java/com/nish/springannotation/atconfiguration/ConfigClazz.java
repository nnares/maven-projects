package com.nish.springannotation.atconfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigClazz {

    // here method name is not making any significance, it could be anything.
    // only the return type is matters here
    @Bean(name = "countryObj")
    public Country iAmGoingToNoWhere() {
        return new Country("India", "Rs");
    }

    // by default name will be - feedService, same as the method name
    @Bean
    public TradeFeedService feedService() {
        TradeFeedService feedService = new TradeFeedService();
        feedService.setFeedDate("01/01/2020");
        feedService.setProductName("Option");
        return feedService;
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

