package mybank;

import org.example.entity.Account;
import org.example.middleware.DatabaseTarget;
import org.example.services.AccountService;

import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class App {

    AccountService service;
    public App() {
        service = new AccountService(new DatabaseTarget());
    }

    @WebResult(name = "Account")
    public Account findByUser(String username){
       Account account =service.callFindUserByUsername(username);
        return  account;
    }

    @WebResult(name = "")
    public void createAccount( long accountNumber,long customerId,String email,String name,double balance,String username,String password){
        service.callAddTransactions(new Account(accountNumber,customerId,email,name,balance,username,password));

    }

    @WebResult(name = "")
    public void withdraw(String username, String password, double withdrawAmount){
        service.callWithdraw(username,password,withdrawAmount);
    }
}
