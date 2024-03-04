package basics.generics;

import java.util.Date;

public class Transactions {
    private Date transactionDate;
    private double amount;
    private String sentTo;
    private String remarks;

    public Transactions(Date transactionDate, double amount, String sentTo, String remarks) {
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.sentTo = sentTo;
        this.remarks = remarks;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getSentTo() {
        return sentTo;
    }

    public void setSentTo(String sentTo) {
        this.sentTo = sentTo;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "Transactions{" +
                "transactionDate=" + transactionDate +
                ", amount=" + amount +
                ", sentTo='" + sentTo + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
