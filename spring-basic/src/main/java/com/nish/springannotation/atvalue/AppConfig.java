package com.nish.springannotation.atvalue;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.LinkedHashMap;
import java.util.Map;


/*
 * To read the values from propertied file need to instantiate a bean of - PropertySourcesPlaceholderConfigurer
 * and pass it one info
 *   @PropertySource - properties file location
 *
 * */
@Configuration
@PropertySource(value = {"classpath:application.properties"})
public class AppConfig {

    @Bean // default name will be - monthMap , same as method name
    public Map<String, String> monthMap(@Value("${month.code.jan}") String jan,
                                        @Value("${month.code.feb}") String feb,
                                        @Value("${month.code.mar}") String mar,
                                        @Value("${month.code.apr}") String apr,
                                        @Value("${month.code.may}") String may,
                                        @Value("${month.code.jun}") String jun,
                                        @Value("${month.code.jul}") String jul,
                                        @Value("${month.code.aug}") String aug,
                                        @Value("${month.code.sep}") String sep,
                                        @Value("${month.code.oct}") String oct,
                                        @Value("${month.code.nov}") String nov,
                                        @Value("${month.code.dec}") String dec) {

        Map<String, String> monthMap = new LinkedHashMap<>();
        monthMap.put(jan, "January");
        monthMap.put(feb, "February");
        monthMap.put(mar, "March");
        monthMap.put(apr, "April");
        monthMap.put(may, "May");
        monthMap.put(jun, "June");
        monthMap.put(jul, "July");
        monthMap.put(aug, "August");
        monthMap.put(sep, "September");
        monthMap.put(oct, "October");
        monthMap.put(nov, "November");
        monthMap.put(dec, "December");
        return monthMap;
    }

}
