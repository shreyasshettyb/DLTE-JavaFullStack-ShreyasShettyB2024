package mybank.db.dao.dltemybankdaolayer;

import mybank.db.dao.dltemybankdaolayer.exception.DepositsException;
import mybank.db.dao.dltemybankdaolayer.service.RepositoryMyBank;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.sql.SQLSyntaxErrorException;

@SpringBootApplication
public class DlteMybankDaoLayerApplication {


    public static void main(String[] args) throws DepositsException, SQLSyntaxErrorException {
//        SpringApplication.run(DlteMybankDaoLayerApplication.class, args);
        ConfigurableApplicationContext configurableApplicationContext=SpringApplication.run(DlteMybankDaoLayerApplication.class, args);
//        RepositoryMyBank repo =configurableApplicationContext.getBean(RepositoryMyBank.class);
//        System.out.println(repo.availableDeposits());
        MyBankRemote remote=configurableApplicationContext.getBean(MyBankRemote.class);
        System.out.println(remote.availableDeposits());
    }

}
