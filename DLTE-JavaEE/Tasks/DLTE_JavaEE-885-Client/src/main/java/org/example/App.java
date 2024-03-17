package org.example;

import mybank.Account;
import mybank.AppService;

/**
 * Hello world!
 */
public class App {
    public static void main( String[] args )
    {
        AppService service=new AppService();
        mybank.App source=service.getAppPort();
        //create account
//        source.createAccount(123456789,741852963,"rakesh@gmail.com","rakesh",5000,"rakesh12","rakesh123");
//        System.out.println();
        Account account = source.findByUser("rakesh12");
        System.out.println("Account Details Are:\nAccount Number: "+account.getAccountNumber()+"\nCustomer Id: "+account.getCustomerId()+"\nName: "+account.getName()+"\nBalance: "+account.getBalance());
        source.withdraw("rakesh12","rakesh123",500);
        account = source.findByUser("rakesh12");
        System.out.println("Account Details Are:\nAccount Number: "+account.getAccountNumber()+"\nCustomer Id: "+account.getCustomerId()+"\nName: "+account.getName()+"\nBalance: "+account.getBalance());
    }
}
