package com.nish.camel.route;

import com.nish.camel.bean.CurrencyBean;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class MQRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("jms:queue:abc")
                .to("file:d:/desti?fileName=mydata.txt");


       // ActiveMqReceiverRouter
        from("activemq:my-activemq-queue")
                .to("log:received-message-from-active-mq");

        // ActiveMqReceiverRouter ->  then marshalling xml to pojo
        from("activemq:my-activemq-queue-xml-queue")
                .unmarshal()
                .jacksonxml(CurrencyBean.class)
                .to("log:received-message-from-active-mq");

/*       // ActiveMqSenderRouter

        //timer
		from("timer:active-mq-timer?period=10000")
		.transform().constant("My message for Active MQ")
		.log("${body}")
		.to("activemq:my-activemq-queue");
		//queue
 */

    }
}
