package com.nish.camel.route;

import com.nish.camel.bean1.SqlParamMap;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class SqlComponentRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        final String sql = "select * from contact";

        from("direct:performDBInsert")
                .log("Inserted new contact")
                .bean(SqlParamMap.class, "getParameterMap")
                .to("sqlComponent:{{insert.sql}}");
    }
}
