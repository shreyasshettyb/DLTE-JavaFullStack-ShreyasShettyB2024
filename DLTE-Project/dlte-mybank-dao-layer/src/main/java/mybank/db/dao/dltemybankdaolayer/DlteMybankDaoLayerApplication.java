package mybank.db.dao.dltemybankdaolayer;

import mybank.db.dao.dltemybankdaolayer.exception.DepositsException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.sql.SQLSyntaxErrorException;

@SpringBootApplication
public class DlteMybankDaoLayerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DlteMybankDaoLayerApplication.class, args);
    }

}
