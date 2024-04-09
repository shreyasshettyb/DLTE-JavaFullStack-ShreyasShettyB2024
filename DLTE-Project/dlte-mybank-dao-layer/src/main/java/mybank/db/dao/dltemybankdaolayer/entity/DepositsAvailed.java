package mybank.db.dao.dltemybankdaolayer.entity;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;

public class DepositsAvailed {
    @NotNull(message = "{deposit.bean.null}")
    private Long depositAvailId;

    @NotNull(message = "{deposit.bean.null}")
    private Long depositId;

    @NotNull(message = "{deposit.bean.null}")
    private Long customerId;

    @NotNull(message = "{deposit.bean.null}")
    @Positive
    private Double depositAmount;

    @NotNull(message = "{deposit.bean.null}")
    @Min(1)
    private Integer depositDuration;

    @NotNull(message = "{deposit.bean.null}")
    private Date depositMaturity;

    public DepositsAvailed(long depositAvailId, long depositId, long customerId, double depositAmount, int depositDuration, Date depositMaturity) {
        this.depositAvailId = depositAvailId;
        this.depositId = depositId;
        this.customerId = customerId;
        this.depositAmount = depositAmount;
        this.depositDuration = depositDuration;
        this.depositMaturity = depositMaturity;
    }

    public DepositsAvailed() {
    }

    public long getDepositAvailId() {
        return depositAvailId;
    }

    public void setDepositAvailId(long depositAvailId) {
        this.depositAvailId = depositAvailId;
    }

    public long getDepositId() {
        return depositId;
    }

    public void setDepositId(long depositId) {
        this.depositId = depositId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public double getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(double depositAmount) {
        this.depositAmount = depositAmount;
    }

    public int getDepositDuration() {
        return depositDuration;
    }

    public void setDepositDuration(int depositDuration) {
        this.depositDuration = depositDuration;
    }

    public Date getDepositMaturity() {
        return depositMaturity;
    }

    public void setDepositMaturity(Date depositMaturity) {
        this.depositMaturity = depositMaturity;
    }

    @Override
    public String toString() {
        return "DepositsAvailed{" +
                "depositAvailId=" + depositAvailId +
                ", depositId=" + depositId +
                ", customerId=" + customerId +
                ", depositAmount=" + depositAmount +
                ", depositDuration=" + depositDuration +
                ", depositMaturity=" + depositMaturity +
                '}';
    }
}
