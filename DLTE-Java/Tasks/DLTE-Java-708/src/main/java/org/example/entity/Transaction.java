package org.example.entity;

import java.util.Date;

public class Transaction {
    private Date date;
    private long transactionID;
    private long accountNumber;
    private double amount;

    public Transaction(Date date, long transactionID, long accountNumber, double amount) {
        this.date = date;
        this.transactionID = transactionID;
        this.accountNumber = accountNumber;
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(long transactionID) {
        this.transactionID = transactionID;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "date=" + date +
                ", transactionID=" + transactionID +
                ", accountNumber=" + accountNumber +
                ", amount=" + amount +
                '}';
    }
}
