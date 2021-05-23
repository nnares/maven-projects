package com.nish.camel.route;

import org.apache.camel.builder.RouteBuilder;

//@Component
public class KafkaRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        // KafkaSenderRoute : file -> kafka-topic
        from("file: D:/files/json?fileName=myData.json")
                .log("${body}")
                .to("kafka:myKafkaTopic");

        // KafkaReceiverRouter : kafka-topic -> log
        from("kafka:myKafkaTopic")
                .to("log:received-message-from-active-mq");

    }

}
