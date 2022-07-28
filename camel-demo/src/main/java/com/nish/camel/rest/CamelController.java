package com.nish.camel.rest;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CamelController {

    @Autowired
    ProducerTemplate producerTemplate;

    @RequestMapping(value = "/route", method = RequestMethod.GET)
    public void invokeRoute() {
        producerTemplate.sendBody("direct:ftl-conversion", "invoking camel route");
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public void startCamel() {
        producerTemplate.sendBody("direct:firstRoute", "Calling via Spring Boot Rest Controller");
    }

    @RequestMapping(value = "/addContact", method = RequestMethod.GET)
    public void addContact() {
        producerTemplate.sendBody("direct:performDBInsert", "Calling via Spring Boot Rest Controller");
    }

    @RequestMapping(value = "/getAllContact", method = RequestMethod.GET)
    public void getAllContact() {
        producerTemplate.sendBody("direct:fetchDBRoute", "DB fetch");
    }

}