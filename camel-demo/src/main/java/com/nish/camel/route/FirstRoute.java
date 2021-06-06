package com.nish.camel.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class FirstRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:firstRoute")
                .log("----------------invoked first route------------------")
                .log("Camel body: ${body}");

    }
}
