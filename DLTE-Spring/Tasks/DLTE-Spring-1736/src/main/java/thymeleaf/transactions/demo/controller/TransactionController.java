package thymeleaf.transactions.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import thymeleaf.transactions.demo.model.Transaction;
import thymeleaf.transactions.demo.service.TransactionService;


import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    Logger logger = LoggerFactory.getLogger(TransactionController.class);

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping("/save")
    public Transaction save(@RequestBody Transaction transaction) {
        try {
            return transactionService.apiAddTransaction(transaction);
        } catch (Exception exception) {
            logger.error("Error occurred : " + exception.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage(), exception);
        }
    }

    @PreAuthorize("hasAuthority('customer')")
    @GetMapping("/view/sender/{sender}")
    public List<Transaction> findBySender(@PathVariable("sender") String sender) {
        try {
            return transactionService.apiFindBySender(sender);
        } catch (Exception exception) {
            logger.error("Error occurred : " + exception.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
        }
    }

    @PreAuthorize("hasAuthority('customer')")
    @GetMapping("/view/receiver/{receiver}")
    public List<Transaction> findByReceiver(@PathVariable("receiver") String receiver) {
        try {
            return transactionService.apiFindByReceiver(receiver);
        } catch (Exception exception) {
            logger.error("Error occurred : " + exception.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
        }
    }

    @PreAuthorize("hasAuthority('customer')")
    @GetMapping("/view/amount/{amount}")
    public List<Transaction> findByAmount(@PathVariable("amount") double amount) {
        try {
            return transactionService.apiFindByAmount(amount);
        } catch (Exception exception) {
            logger.error("Error occurred : " + exception.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
        }
    }

    @PreAuthorize("hasAnyAuthority('admin','manager')")
    @PutMapping("/update/remarks/{transaction_id}/{remark}")
    public String updateRemarks(@PathVariable ("transaction_id") long transaction_id,@PathVariable("remark") String remark){
        try{
            return transactionService.apiUpdateRemarks(remark,transaction_id);
        }catch (Exception exception){
            logger.error("Error occurred : " + exception.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
        }

    }

    @PreAuthorize("hasAuthority('admin')")
    @DeleteMapping("/delete/dates/{start}/{end}")
    public String removeTransactionBetweenDates(@PathVariable ("start")Date start,@PathVariable("end") Date end){
       try {
           return transactionService.apiRemoveTransactionByDates(start,end);
       }catch (Exception exception){
           logger.error("Error occurred : " + exception.getMessage());
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
       }
    }
}

