package com.nish.camel.route;

import com.nish.camel.bean1.Contact;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

@Component
public class JdbcRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		final String sql = "select * from contact";

		from("timer://my-timer?repeatCount=1")
				.to("direct:fetchDBRoute")
				.log("${body}")
				.marshal().json(true)
				.to("file:" + "{{destination.file.path}}" + "?fileName=contact.json");


/*
		from("timer://my-timer?repeatCount=2&period=4h")

				.to("direct:fetchDBRoute")
				.log("${body}")

				.process(exchange -> {
					// List to String
					List contacts = exchange.getIn().getBody(List.class);

					StringBuilder sb = new StringBuilder();
					contacts.forEach(e -> sb.append(e + ",\n"));
					exchange.getIn().setBody(sb);
				})
				.to("file:" + "{{destination.file.path}}" + "?fileName=contact_tbl.txt&fileExist=Append");
*/

		from("direct:fetchDBRoute")
				.log("Get all contacts")
				.setBody(constant(sql))
				.to("jdbc:dataSource");

	}
}
