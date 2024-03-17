package Parsing;

import java.util.Date;

public class Transaction {
    private String date;
    private String user;
    private double amount;

    public Transaction() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Transaction(String user, double amount,String date) {
        this.date = date;
        this.user = user;
        this.amount = amount;

    }

    @Override
    public String toString() {
        return "Parsing.Transaction{" +
                "date=" + date +
                ", user='" + user + '\'' +
                ", amount=" + amount +
                '}';
    }
}
