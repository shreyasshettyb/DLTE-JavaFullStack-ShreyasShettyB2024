package org.example;

import java.util.Date;

public class CreditCard {
    private Date dateOfTransaction;
    private Double amountInTransaction;
    private String toRecipient;
    private String remarks;

    public CreditCard() {
    }

    public CreditCard(Date dateOfTransaction, Double amountInTransaction, String toRecipient, String remarks) {
        this.dateOfTransaction = dateOfTransaction;
        this.amountInTransaction = amountInTransaction;
        this.toRecipient = toRecipient;
        this.remarks = remarks;
    }

    public Date getDateOfTransaction() {
        return dateOfTransaction;
    }

    public void setDateOfTransaction(Date dateOfTransaction) {
        this.dateOfTransaction = dateOfTransaction;
    }

    public Double getAmountInTransaction() {
        return amountInTransaction;
    }

    public void setAmountInTransaction(Double amountInTransaction) {
        this.amountInTransaction = amountInTransaction;
    }

    public String getToRecipient() {
        return toRecipient;
    }

    public void setToRecipient(String toRecipient) {
        this.toRecipient = toRecipient;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "dateOfTransaction=" + dateOfTransaction +
                ", amountInTransaction=" + amountInTransaction +
                ", toRecipient='" + toRecipient + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}