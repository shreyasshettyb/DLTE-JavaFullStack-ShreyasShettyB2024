package task.hql.demo.remotes;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import task.hql.demo.model.Transaction;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {
}
