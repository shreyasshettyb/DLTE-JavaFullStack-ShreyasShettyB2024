package mybank.db.dao.dltemybankdaolayer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;


@SpringBootApplication
@PropertySource("classpath:application-database.properties")
public class DlteMybankDaoLayerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DlteMybankDaoLayerApplication.class, args);
    }

}
