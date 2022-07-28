package com.nish.camel.route;

import com.nish.camel.bean1.SqlParamMap;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class SqlComponentRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        final String sql = "select * from contact";

        from("direct:performDBInsert")
                .log("Inserted new contact")
                .bean(SqlParamMap.class, "getParameterMap")
                .to("sqlComponent:{{insert.sql}}");


        from("direct:performDBFetch")
                .log("Get all contacts")
                .to("sqlComponent:sql");

    }
}

/*
context.addRoutes(new RouteBuilder() {
@Override
public void configure() {
from("timer://foo?repeatCount=1")
.setBody(constant("SELECT * FROM Account LIMIT 10"))
.to("jdbc:myDataSource")
.marshal().json(true)
.to("file:C:\\Users\\USER\\Documents?fileName=account.json");
}
});
* */