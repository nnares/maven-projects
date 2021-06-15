package com.nish.camel.ftl;

import com.nish.camel.bean1.Contact;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FtlRoute extends RouteBuilder {

    Map<String, Object> variableMap = new HashMap<String, Object>();

    @Override
    public void configure() throws Exception {



        from("direct:ftl-conversion")
                .to("direct:fetchDBRoute")
                .log("${body}")

                .process(exchange -> {
                    // List to String
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

                })
                .to("freemarker:templates/contact.ftl")
                .log("${body}");

    }


}
