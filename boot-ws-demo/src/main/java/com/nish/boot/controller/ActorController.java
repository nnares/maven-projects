package com.nish.boot.controller;

import com.nish.boot.model.ActorV1;
import com.nish.boot.model.ActorV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
@RestController
public class ActorController {

    @RequestMapping("/time")
    public String sayHello(){
        return new Date().toString();
    }

    /*  URI Versioning
    http://localhost:8080/v1/actor
    http://localhost:8080/v2/actor
     */

    @GetMapping("v1/actor")
    public ActorV1 getActorV1() {
        return new ActorV1("Tom Cruise");
    }

    @GetMapping("v2/actor")
    public ActorV2 getActorV2() {
        return new ActorV2(new ActorV2.Name("Tom", "Cruise"));
    }


    /*  Request Parameter Versioning
    http://localhost:8080/actor/param?version=1
    http://localhost:8080/actor/param?version=2
     */

    @GetMapping(value = "/actor/param", params = "version=1")
    public ActorV1 getActorParamV1() {
        return new ActorV1("Tom Cruise");
    }


    @GetMapping(value = "/actor/param", params = "version=2")
    public ActorV2 getActorParamV2() {
        return new ActorV2(new ActorV2.Name("Tom", "Cruise"));
    }


    /*      (Custom) Headers Versioning
            http://localhost:8080/actor/header
                headers=[X-API-VERSION=1]
            http://localhost:8080/actor/header
                headers=[X-API-VERSION=2]
     */

    @GetMapping(value = "/actor/header", headers = "X-API-VERSION=1")
    public ActorV1 getActorHeadersV1() {
        return new ActorV1("Tom Cruise");
    }


    @GetMapping(value = "/actor/header", headers = "X-API-VERSION=2")
    public ActorV2 getActorHeadersV2() {
        return new ActorV2(new ActorV2.Name("Tom", "Cruise"));
    }

    /*      Media Type Versioning (a.k.a “Content Negotiation” or “Accept Header”)
            http://localhost:8080/actor/produces
                headers[Accept=application/vnd.company.app-v1+json]
            http://localhost:8080/actor/produces
                headers[Accept=application/vnd.company.app-v2+json]
     */

    @GetMapping(value = "/actor/produces", produces = "application/vnd.company.app-v1+json")
    public ActorV1 getActorProducesV1() {
        return new ActorV1("Tom Cruise");
    }


    @GetMapping(value = "/actor/produces", produces = "application/vnd.company.app-v2+json")
    public ActorV2 getActorProducesV2() {
        return new ActorV2(new ActorV2.Name("Tom", "Cruise"));
    }



}
