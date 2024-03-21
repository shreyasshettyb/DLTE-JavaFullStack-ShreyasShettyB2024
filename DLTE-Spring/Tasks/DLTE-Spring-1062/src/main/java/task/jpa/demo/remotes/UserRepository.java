package task.jpa.demo.remotes;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import task.jpa.demo.model.Account;

@Repository
public interface UserRepository extends CrudRepository<Account,Long> {

}
