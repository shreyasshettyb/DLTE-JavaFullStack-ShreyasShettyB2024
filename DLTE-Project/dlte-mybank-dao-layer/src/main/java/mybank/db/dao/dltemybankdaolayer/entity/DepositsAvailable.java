package mybank.db.dao.dltemybankdaolayer.entity;


import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class DepositsAvailable {
    @NotNull(message = "{value.null}")
    private long depositId;

    @NotNull(message = "{value.null}")
    private String depositName;

    @NotNull(message = "{value.null}")
    private String depositType;

    @NotNull(message = "{value.null}")
    private String depositDescription;

    @NotNull(message = "{value.null}")
    @Positive
    @Digits(integer = 3, fraction = 2)
    private double depositRoi;

    public DepositsAvailable() {
    }

    public long getDepositId() {
        return depositId;
    }

    public void setDepositId(long depositId) {
        this.depositId = depositId;
    }

    public String getDepositName() {
        return depositName;
    }

    public void setDepositName(String depositName) {
        this.depositName = depositName;
    }

    public String getDepositType() {
        return depositType;
    }

    public void setDepositType(String depositType) {
        this.depositType = depositType;
    }

    public String getDepositDescription() {
        return depositDescription;
    }

    public void setDepositDescription(String depositDescription) {
        this.depositDescription = depositDescription;
    }

    public double getDepositRoi() {
        return depositRoi;
    }

    public void setDepositRoi(double depositRoi) {
        this.depositRoi = depositRoi;
    }
}
