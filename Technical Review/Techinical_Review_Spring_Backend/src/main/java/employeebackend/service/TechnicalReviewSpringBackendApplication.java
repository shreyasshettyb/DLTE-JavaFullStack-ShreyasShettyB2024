package employeebackend.service;

import employeebackend.service.repository.DataBaseRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class TechnicalReviewSpringBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(TechnicalReviewSpringBackendApplication.class, args);
    }

}
