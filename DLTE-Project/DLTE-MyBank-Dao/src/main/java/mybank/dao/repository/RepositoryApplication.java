package mybank.dao.repository;

import mybank.dao.repository.exception.DepositsException;
import mybank.dao.repository.service.RepositoryMyBank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.sql.SQLSyntaxErrorException;

@SpringBootApplication
public class RepositoryApplication {

    static RepositoryMyBank remote=new RepositoryMyBank();

    public static void main(String[] args) {
        SpringApplication.run(RepositoryApplication.class, args);
        try {
            System.out.println(remote.availableDeposits());
        } catch (SQLSyntaxErrorException e) {
            e.printStackTrace();
        } catch (DepositsException e) {
            e.printStackTrace();
        }
    }

}
