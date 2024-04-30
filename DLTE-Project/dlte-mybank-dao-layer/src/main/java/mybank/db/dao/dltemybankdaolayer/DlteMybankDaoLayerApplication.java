package mybank.db.dao.dltemybankdaolayer;

import mybank.db.dao.dltemybankdaolayer.exception.DepositsException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import java.sql.SQLSyntaxErrorException;

@SpringBootApplication
//@PropertySource("database.properties")
public class DlteMybankDaoLayerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DlteMybankDaoLayerApplication.class, args);
    }

}
