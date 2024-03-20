package org.example.services;

import org.example.entity.Account;
import org.example.entity.Transaction;
import org.example.remotes.StorageTarget;
import org.example.remotes.UserRepository;

import java.sql.Date;
import java.util.List;

/**
 * Function call for the methods in userRepository
 */
public class AccountService {
    UserRepository userRepository;

    public AccountService(StorageTarget storageTarget) {
        userRepository = storageTarget.getUserRepository();
    }

    public boolean callVerifyPassword(String username,String password){
        try {
           return userRepository.verifyPassword(username,password);
        }
        catch (Exception e){
            return false;
        }
    }
    public Double callWithdraw(String username,String password, double withdrawAmount){
        try {
            return userRepository.withdraw(username,password,withdrawAmount);
        }catch (Exception e){
            return null;
        }
    }
    public void callAddTransactions(Account account){
        try {
            userRepository.addTransactions( account);
        }
        catch (Exception e){
            return;
        }
    }
    public List<Transaction> callFindAll(){
        try{
            return userRepository.findALL();
        }
        catch (Exception e){
            return null;
        }
    }
    public List<Transaction> callFindAllUser(String user){
        try{
            return userRepository.findAllUser(user);
        }
        catch (Exception e){
            return null;
        }
    }
    public List<Transaction> callFindAllDate(Date date, String user){
        try{
            return userRepository.findAllByDate(date,user);
        }
        catch (Exception e){
            return null;
        }
    }

    public Account findUserByUsername(String username){
        try{
            return  userRepository.findUserByUsername(username);
        }catch (Exception e){
            return null;
        }
    }

    public void createTransaction(String username,double withdraw,double currentBalaance){
        try{
            userRepository.createTransaction(username,withdraw,currentBalaance);
        }catch (Exception e){

        }
    }
}
