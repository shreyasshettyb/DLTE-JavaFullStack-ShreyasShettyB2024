package mybank;

import org.example.entity.Transaction;
import org.example.middleware.DatabaseTarget;
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
    public GroupOfTransactions findAllByUserDate( String username, String date){
        groupOfTransactions.setTransactionsList(service.callFindAllDate(Date.valueOf(date),username));
        return  groupOfTransactions;
    }

}
