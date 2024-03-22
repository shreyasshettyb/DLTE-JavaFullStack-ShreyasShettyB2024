package task.hql.demo.remotes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import task.hql.demo.model.Transaction;

import java.util.List;

@Repository
public interface TransactionJpaRepository extends JpaRepository<Transaction, Long> {
    @Query("from Transaction where amount between :amount1 and :amount2")
    List<Transaction> searchByAmountRange(Double amount1, Double amount2);

    @Query(value = "select * from TRANSACTIONS where transaction_username= :username and transaction_Type = :type", nativeQuery = true)
    List<Transaction> searchByUserAndType(String username, String type);
}
