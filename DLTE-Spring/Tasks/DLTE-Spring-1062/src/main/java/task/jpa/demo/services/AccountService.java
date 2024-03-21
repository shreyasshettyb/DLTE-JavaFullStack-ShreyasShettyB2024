package task.jpa.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task.jpa.demo.model.Account;
import task.jpa.demo.remotes.UserRepository;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    UserRepository userRepository;

    public Account callAdd(Account account){
        return userRepository.save(account);
    }

    public List<Account> callFindAll(){
        return (List<Account>) userRepository.findAll();
    }

}
