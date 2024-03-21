package task.jpa.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import task.jpa.demo.model.Account;
import task.jpa.demo.services.AccountService;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountRestController {
    @Autowired
    AccountService accountService;
    @PostMapping("/add")
   public Account addAccountApi(@RequestBody Account account){
        return accountService.callAdd(account);
    }
    @PutMapping("/update")
    public Account updateAccountApi(@RequestBody Account account){
        return accountService.callAdd(account);
    }

    @GetMapping("/view")
    public List<Account> viewAccountsApi(){
            return accountService.callFindAll();
    }
}
