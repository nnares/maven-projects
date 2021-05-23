package com.nish.camel.route;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;

//@Component
public class FileRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

//        moveAllFile();
//        moveSpecificFile("data");
//        moveSpecificFileWithBody("-Execution-");
//        fileProcess();
//        multiFileProcessor();


        // in camel its easy to read properties value - "{{prop.key}}"
        from("file:" + "{{source.file.path}}")
                .to( "file:" + "{{destination.file.path}}");

    }

    public void moveAllFile() {
        // from("file:E:\\june 2020\\camel\\fileT\\source?noop=true").to("file:E:\\june 2020\\camel\\fileT\\dest");
        from("{{camel.file.source}}").to("{{camel.file.destination}}");
    }

    // copy specific files matching the names
    public void moveSpecificFile(String fileName) {
        from("{{camel.file.source}}")
                .filter(header(Exchange.FILE_NAME).startsWith(fileName))
                .to("{{camel.file.destination}}");
    }

    public void moveSpecificFileWithBody(String content) {
        from("{{camel.file.source}}").filter(body().startsWith(content))
                .to("{{camel.file.destination}}");
    }


    // https://github.com/Java-Techie-jt/spring-boot-apache-camel/blob/master/src/main/java/com/javatechie/spring/camel/api/SpringBootCamelApplication.java

    public void fileProcess() {
        from("file:E:/june 2020/camel/fileT/source/emp.txt")
                .process(p -> {
                    String body = p.getIn().getBody(String.class);
                    StringBuilder sb = new StringBuilder();
                    Arrays.stream(body.split(" "))
                            .forEach(s -> {
                                sb.append(s + ",");
                            });

                    p.getIn().setBody(sb);
                }).to("file:E:/june 2020/camel/fileT/dest/");
//                }).to("file:E:/june 2020/camel/fileT/dest?fileName=records.csv");
//        file:E:/june 2020/camel/fileT/dest
    }

    public void multiFileProcessor() {
        from("file:E:/june 2020/camel/fileT/source/emp.txt?noop=true").unmarshal().csv().split(body().tokenize(",")).choice()
                .when(body().contains("Closed")).to("file:destination?fileName=close.csv")
                .when(body().contains("Pending")).to("file:destination?fileName=Pending.csv")
                .when(body().contains("Interest")).to("file:destination?fileName=Interest.csv");

    }
}
