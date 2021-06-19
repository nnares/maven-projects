package com.nish.camel.ftl;

import com.nish.camel.bean1.Contact;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FtlRoute extends RouteBuilder {

    Map<String, Object> variableMap = new HashMap<String, Object>();

    @Override
    public void configure() throws Exception {



/*        from("direct:ftl-conversion")
                .to("direct:fetchDBRoute")
                .log("${body}")

                .process(exchange -> {
*//*                    // List to String
                    List contacts = exchange.getIn().getBody(List.class);

                    List<Contact> contacts1 = (List<Contact>) contacts;
                    for(Contact curr : contacts1) {
                        variableMap.put("contactId", curr.getContactId());
                        variableMap.put("name", curr.getName());
                        variableMap.put("email", curr.getEmail());
                        variableMap.put("address", curr.getAddress());
                        variableMap.put("telephone", curr.getPhone());
                        exchange.getIn().setHeader("CamelFreemarkerDataModel", variableMap);
                    }
*//*

                    List<HashMap<String,Object>> out = (ArrayList<HashMap<String,Object>>) exchange.getIn().getBody();

                    try{
                        for(HashMap<String,Object> currentMap : out){
                            exchange.getIn().setHeader("CamelFreemarkerDataModel", currentMap);
                            System.out.println(currentMap);
                        }
                    }catch(Exception e){
                        System.out.println(e);
                    }

                })
                .to("freemarker:templates/contact.ftl")
                .log("${body}");*/

        from("timer:first-time?period=2000")
                .process(exchange -> {

                    Map<String, Object> variableMap = new HashMap<>();

                    variableMap.put("contact_id", 500);
                    variableMap.put("name", "Alok Kumar");
                    variableMap.put("email", "alok.kumar@example.com");
                    variableMap.put("address", "Baadh, Bihar");
                    variableMap.put("telephone", "9944994499");
                    exchange.getIn().setHeader("CamelFreemarkerDataModel", variableMap);
                })
                .to("freemarker:templates/contact.ftl?contentCache=false")
                .log("${body}");

    }


}
