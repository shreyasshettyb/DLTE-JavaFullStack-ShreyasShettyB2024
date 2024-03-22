package task.hql.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import task.hql.demo.model.Transaction;
import task.hql.demo.services.TransactionService;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionRestController {
    @Autowired
    TransactionService transactionService;

    @PostMapping(value = "/save", consumes = "application/xml")
    public Transaction saveTransactionApi(@RequestBody Transaction transaction) {
        return transactionService.callSave(transaction);
    }

    @GetMapping("/view/range/{amount1}/{amount2}")
    public List<Transaction> viewTransactionByRangeAmountApi(@PathVariable("amount1") double amount1, @PathVariable("amount2") double amount2) {
        return transactionService.callSearchByAmountRange(amount1, amount2);
    }

    @GetMapping("/view/{user}/{type}")
    public List<Transaction> viewTransactionByUserAndType(@PathVariable("user") String username, @PathVariable("type") String type) {
        return transactionService.saveSearchByUserAndType(username, type);
    }
}
