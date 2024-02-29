package basics.exception;

public class Account {
    //Initialization
    Long accountNumber = 2135464789L;
    Double accountBalance = 500000.0;
    String accountHolder = "Shreyas";

    //Getters and Setters
    public Double getAccountBalance(){
        return accountBalance;
    }

    public void setAccountBalance(Double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }
}

