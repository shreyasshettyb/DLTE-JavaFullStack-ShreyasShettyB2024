package task.jdbctemplate.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import task.jdbctemplate.demo.model.Transaction;
import task.jdbctemplate.demo.service.TransactionService;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    Logger logger = LoggerFactory.getLogger(TransactionController.class);

    @PostMapping("/save")
    public Transaction save(@RequestBody Transaction transaction) {
        try {
            return transactionService.apiAddTransaction(transaction);
        } catch (Exception exception) {
            logger.error("Error occurred while saving transaction: " + exception.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage(), exception);
        }
    }

    @GetMapping("/view/sender/{sender}")
    public List<Transaction> findBySender(@PathVariable("sender") String sender) {
        try {
            return transactionService.apiFindBySender(sender);
        } catch (Exception exception) {
            logger.error("Error occurred while fetching transactions by sender: " + exception.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
        }
    }

    @GetMapping("/view/receiver/{receiver}")
    public List<Transaction> findByReceiver(@PathVariable("receiver") String receiver) {
        try {
            return transactionService.apiFindByReceiver(receiver);
        } catch (Exception exception) {
            logger.error("Error occurred while fetching transactions by receiver: " + exception.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
        }
    }

    @GetMapping("/view/amount/{amount}")
    public List<Transaction> findByAmount(@PathVariable("amount") double amount) {
        try {
            return transactionService.apiFindByAmount(amount);
        } catch (Exception exception) {
            logger.error("Error occurred while fetching transactions by amount: " + exception.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
        }
    }
}

