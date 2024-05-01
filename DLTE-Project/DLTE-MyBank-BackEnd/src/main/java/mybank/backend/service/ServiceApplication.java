package mybank.backend.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@SpringBootApplication
//@PropertySource(value={"classpath:/dlte-mybank-dao-layer/application.properties"}, ignoreResourceNotFound = true)
public class ServiceApplication {

    public static void main(String[] args) {
//        Resource resource = new ClassPathResource("database.properties");
//        System.setProperty("spring.config.location","classpath:/dlte-mybank-dao-layer/application.properties");
        SpringApplication.run(ServiceApplication.class, args);
    }

}

