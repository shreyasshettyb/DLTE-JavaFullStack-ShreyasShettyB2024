package task.jpa.demo.model;

//import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "MY_BANK")
public class Account implements Serializable {
    @Id
    @Column(name = "ACCOUNT_NUMBER")
    private long accountNumber;
    @Column(name = "CUSTOMER_ID")
    private long customerId;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "NAME")
    private String name;
    @Column(name = "BALANCE")
    private double balance;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "PASSWORD")
    private String password;

    public Account() {

    }

    public Account(long accountNumber, long customerId, String email, String name, double balance, String username, String password) {
        this.accountNumber = accountNumber;
        this.customerId = customerId;
        this.email = email;
        this.name = name;
        this.balance = balance;
        this.username = username;
        this.password = password;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", customerId=" + customerId +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
