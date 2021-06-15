package com.nish.camel.ftl;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;

public class FTLTest {
/*
    private Exchange createLetter() {
        Exchange exchange = context.getEndpoint("direct:a").createExchange();
        Message msg = exchange.getIn();
        msg.setHeader("firstName", "Claus");
        msg.setHeader("lastName", "Ibsen");
        msg.setHeader("item", "Camel in Action");
        msg.setBody("PS: Next beer is on me, James");
        return exchange;
    }

    @Test
    public void testFreemarkerLetter() throws Exception {
        MockEndpoint mock = getMockEndpoint("mock:result");
        mock.expectedMessageCount(1);
        mock.expectedBodiesReceived("Dear Ibsen, Claus\n\nThanks for the order of Camel in Action."
                + "\n\nRegards Camel Riders Bookstore\nPS: Next beer is on me, James");

        template.send("direct:a", createLetter());

        mock.assertIsSatisfied();
    }

    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            public void configure() throws Exception {
                from("direct:a")
                        .to("freemarker:org/apache/camel/component/freemarker/letter.ftl")
                        .to("mock:result");
            }
        };
    }
    */

}
