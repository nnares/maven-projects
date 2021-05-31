package com.nish.camel.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class JdbcRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		final String sql = "select * from contact";
        //from("timer:first-time?period=2000")
		from("timer://my-timer?repeatCount=2&period=4h")

		.setBody(constant(sql))
		.log("${body}")
		.to("jdbc:dataSource")
		.to( "file:" + "{{destination.file.path}}" + "?fileName=contact_tbl.txt?fileExist=Append");
	}
}
