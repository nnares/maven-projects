package com.nish.camel.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CommonRoutes extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        final String sql = "select * from contact";


        from("direct:fetchDBRoute")
                .log("Get all contacts")
                .setBody(constant(sql))
                .to("jdbc:dataSource");




    }
}