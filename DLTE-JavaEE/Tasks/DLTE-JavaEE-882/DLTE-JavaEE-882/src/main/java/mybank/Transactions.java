package mybank;

import org.example.entity.Transaction;
import org.example.middleware.DatabaseTarget;
import org.example.middleware.UserDatabaseRepository;
import org.example.remotes.StorageTarget;
import org.example.remotes.UserRepository;
import org.example.services.AccountService;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class Transactions {
    GroupOfTransactions groupOfTransactions = new GroupOfTransactions();
    AccountService service;

    public Transactions() {
        service = new AccountService(new DatabaseTarget());
    }

    @WebResult(name = "GroupOfTransaction")
    public GroupOfTransactions findAllByUser( String username){
        groupOfTransactions.setTransactionsList(service.callFindAllUser(username));
        return  groupOfTransactions;
    }


    @WebResult(name = "GroupOfTransaction")
    public GroupOfTransactions findAll( ){
        groupOfTransactions.setTransactionsList(service.callFindAll());
        return  groupOfTransactions;
    }


    @WebResult(name = "Create")
    public GroupOfTransactions create( String username,double withdraw,double currentBalance){
        service.createTransaction(username,withdraw,currentBalance);
        return  groupOfTransactions;
    }

}
