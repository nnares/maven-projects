package com.nish.camel.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestConfigurationDefinition;
import org.springframework.stereotype.Component;

/*
 *   trying to hit below endpoint
 *   GET, https://jsonplaceholder.typicode.com/todos/1
 *   =>
 *	{
 *	  "userId": 1,
 *	  "id": 1,
 *	  "title": "delectus aut autem",
 *	  "completed": false
 *	}
 *
 * */
//@Component
public class RestRoute extends RouteBuilder {

    private String uri = "todos/1";

    @Override
    public void configure() throws Exception {

        restConfiguration().host("jsonplaceholder.typicode.com");

        from("timer:first-time?period=2000")
                .log("${body}")
                .to("rest:get:" + uri)
                .log("${body}")
                .log("hello owrld---------------")
                .to("log:first-timer");



    }


}
