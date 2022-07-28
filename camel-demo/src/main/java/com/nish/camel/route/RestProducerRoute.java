package com.nish.camel.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;


//@Component
public class RestProducerRoute extends RouteBuilder {

/*
    @Override
    public void configure() throws Exception {
        restConfiguration()
                .component("servlet")
                .bindingMode(RestBindingMode.json);

        rest().get("/hello")
                .to("direct:hello");

        from("direct:hello")
                .log("Hello World")
                .transform().simple("Hello World");
    }
*/

    @Override
    public void configure() throws Exception {

/*        restConfiguration().component("servlet").bindingMode(RestBindingMode.json)
                .dataFormatProperty("prettyPrint", "true")
                .contextPath("camel-example/rest").port(8080);*/

        // Define the component and hostname and port
        restConfiguration().component("servlet")
                .host("localhost").port(8080);


            rest("/say")
                    .get("/hello").to("direct:hello")
                    .get("/bye").consumes("application/json").to("direct:bye")
                    .post("/bye").to("mock:update");

            from("direct:hello")
                    .transform().constant("Hello World");
            from("direct:bye")
                    .transform().constant("Bye World");
    }

}
