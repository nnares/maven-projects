package com.nish.camel.route;

import com.nish.camel.bean.TimeBean;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


//@Component
public class TimerRoute extends RouteBuilder {

    @Autowired
    private TimeBean timeBean;

    @Override
    public void configure() throws Exception {

        from("timer:first-time?period=2000") // null
//                .transform().constant("my const msg")
//                .transform().constant("time - " + LocalDateTime.now())
                .bean(timeBean, "getCurrentTime")
                .log("${body}")
        .to("log:first-timer");

    }

}
