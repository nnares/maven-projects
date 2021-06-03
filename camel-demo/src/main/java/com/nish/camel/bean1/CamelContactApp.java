package com.nish.camel.bean1;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;

public class CamelContactApp {
/*
    @Produce(uri = "direct:performDBInsert")
    ProducerTemplate template;

    private void execute(){
        template.sendBody(new Object());
    }

    public static void main(String[] args) {

        ProducerTemplate producerTemplate = context.createProducerTemplate();

    }*/
}

/*

    public static void main(String[] args) {

        try {
            ApplicationContext springCtx = new ClassPathXmlApplicationContext(
                    "database-context.xml");

            CamelContext context = springCtx.getBean("bookCtx", CamelContext.class);

            context.start();

            ProducerTemplate producerTemplate = context.createProducerTemplate();

            // Insert book 1
            Book book1 = buildBook1();
            String resp = producerTemplate.requestBody("direct:insert",  book1, String.class);
            System.out.println("resp:"+resp);

            // Insert book 2
            Book book2 = buildBook2();
            resp = producerTemplate.requestBody("direct:insert",  book2, String.class);
            System.out.println("resp:"+resp);

            // Read all books
            List<Book> resp1 = producerTemplate
                    .requestBody("direct:select",  null, List.class);
            System.out.println("resp1:"+resp1);

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    private static Book buildBook1() {

        Book book = new Book();

        book.setBookId("FICT1");
        book.setBookName("Rogue Lawyer");
        book.setAuthor("John Grisham");
        book.setPrice("$10");
        book.setCreateDate(new Date().toString());
        return book;

    }
*/
