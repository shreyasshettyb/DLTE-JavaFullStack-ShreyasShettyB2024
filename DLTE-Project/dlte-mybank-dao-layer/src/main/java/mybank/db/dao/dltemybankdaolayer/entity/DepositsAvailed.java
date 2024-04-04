package mybank.db.dao.dltemybankdaolayer.entity;


import java.util.Date;

public class DepositsAvailed {
    private long depositAvailId,depositId,customerId;
    private double depositedAmount;
    private int depositedDuration;
    private Date depositMaturity;

    public DepositsAvailed(long depositAvailId, long depositId, long customerId, double depositedAmount, int depositedDuration, Date depositMaturity) {
        this.depositAvailId = depositAvailId;
        this.depositId = depositId;
        this.customerId = customerId;
        this.depositedAmount = depositedAmount;
        this.depositedDuration = depositedDuration;
        this.depositMaturity = depositMaturity;
    }

    public long getDeposit_avail_id() {
        return depositAvailId;
    }

    public void setDeposit_avail_id(long depositAvailId) {
        this.depositAvailId = depositAvailId;
    }

    public long getDeposit_id() {
        return depositId;
    }

    public void setDeposit_id(long depositId) {
        this.depositId = depositId;
    }

    public long getCustomer_id() {
        return customerId;
    }

    public void setCustomer_id(long customerId) {
        this.customerId = customerId;
    }

    public double getDeposited_amount() {
        return depositedAmount;
    }

    public void setDeposited_amount(double depositedAmount) {
        this.depositedAmount = depositedAmount;
    }

    public int getDeposited_duration() {
        return depositedDuration;
    }

    public void setDeposited_duration(int depositedDuration) {
        this.depositedDuration = depositedDuration;
    }

    public Date getDeposit_maturity() {
        return depositMaturity;
    }

    public void setDeposit_maturity(Date depositMaturity) {
        this.depositMaturity = depositMaturity;
    }

    public DepositsAvailed() {
    }

    @Override
    public String toString() {
        return "DepositsAvailed{" +
                "depositAvailId=" + depositAvailId +
                ", depositId=" + depositId +
                ", customerId=" + customerId +
                ", depositedAmount=" + depositedAmount +
                ", depositedDuration=" + depositedDuration +
                ", depositMaturity=" + depositMaturity +
                '}';
    }
}
