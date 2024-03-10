package app.mobilebanking.remotes;

import app.mobilebanking.entity.Account;

import java.util.List;

/**
 * Abstract methods that services needed and we are implementing it in UserFileRepository
 */
public interface UserRepository {
    List<Account> findALL();
    boolean verifyPassword(String username,String password);
    void withdraw(String username,String password,double withdrawAmount);
    double balance(String username);
    void addTransactions();
}
