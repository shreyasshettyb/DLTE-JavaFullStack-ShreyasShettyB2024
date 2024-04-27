package task.jdbctemplate.demo.model;

import java.util.Date;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class Transaction {
    private long transactionId;

    @NotNull
    private Date transactionDate;

    @NotEmpty
    private String sentTo;

    @NotEmpty
    private String receivedBy;

    @Positive
    private double amount;

    @NotEmpty
    private String remarks;

    public Transaction(long transactionId, Date transactionDate, String sentTo, String receivedBy, double amount, String remarks) {
        this.transactionId = transactionId;
        this.transactionDate = transactionDate;
        this.sentTo = sentTo;
        this.receivedBy = receivedBy;
        this.amount = amount;
        this.remarks = remarks;
    }

    public Transaction() {
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getSentTo() {
        return sentTo;
    }

    public void setSentTo(String sentTo) {
        this.sentTo = sentTo;
    }

    public String getReceivedBy() {
        return receivedBy;
    }

    public void setReceivedBy(String receivedBy) {
        this.receivedBy = receivedBy;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", transactionDate=" + transactionDate +
                ", sentTo='" + sentTo + '\'' +
                ", receivedBy='" + receivedBy + '\'' +
                ", amount=" + amount +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
