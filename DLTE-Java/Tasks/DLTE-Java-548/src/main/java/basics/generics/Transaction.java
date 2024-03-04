package basics.generics;

public class Transaction {
    private String dateOfTransaction;
    private   amountInTransaction;
    private String to;
    private String remarks;

    public Transaction(String dateOfTransaction, Integer amountInTransaction, String transactionTo, String transactionRemarks) {
        this.transactionDate = transactionDate;
        this.transactionAmount = transactionAmount;
        this.transactionTo = transactionTo;
        this.transactionRemarks = transactionRemarks;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionDate='" + transactionDate + '\'' +
                ", transactionAmount=" + transactionAmount +
                ", transactionTo='" + transactionTo + '\'' +
                ", transactionRemarks='" + transactionRemarks + '\'' +
                '}';
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Integer getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Integer transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionTo() {
        return transactionTo;
    }

    public void setTransactionTo(String transactionTo) {
        this.transactionTo = transactionTo;
    }

    public String getTransactionRemarks() {
        return transactionRemarks;
    }

    public void setTransactionRemarks(String transactionRemarks) {
        this.transactionRemarks = transactionRemarks;
    }
}
