package com.nish.camel.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class JdbcRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		final String sql = "select * from contact";
		//from("sql:select * from emptab?dataSource=#dsObj")
		from("timer://my-timer?repeatCount=2&period=4h")
		.setBody(constant(sql))
		.log("${body}")
		.to("jdbc:dataSource")
		.process(ex->{
			Object ob=ex.getIn().getBody();
			System.out.println(ob.toString());
			ex.getMessage().setBody(ob.toString());
		})
		.to( "file:" + "{{destination.file.path}}" + "?fileName=contact_tbl.txt");
	}
}
