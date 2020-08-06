package nish.jpmc.mvc.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = {"nish.jpmc.mvc.controller"})
public class BootWebApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(BootWebApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BootWebApplication.class);
    }

}