package task.hql.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task.hql.demo.model.Transaction;
import task.hql.demo.remotes.TransactionJpaRepository;
import task.hql.demo.remotes.TransactionRepository;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    TransactionJpaRepository transactionJpaRepository;
    @Autowired
    TransactionRepository transactionRepository;

    public Transaction callSave(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public List<Transaction> callSearchByAmountRange(Double amount1, Double amount2) {
        return transactionJpaRepository.searchByAmountRange(amount1, amount2);
    }

    public List<Transaction> saveSearchByUserAndType(String username, String type) {
        return transactionJpaRepository.searchByUserAndType(username, type);
    }

}
