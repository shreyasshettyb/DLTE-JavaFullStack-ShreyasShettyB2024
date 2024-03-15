package org.example;

import mybank.Transaction;
import org.example.entity.Account;
import org.example.services.AccountService;

import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        AccountService accountService = new AccountService();
        Account account = accountService.();
        List<Transaction> transactionList = transaction.findAllByUserDate("shreyas12", "12/03/2024").getTransactionsList();

    }
}
