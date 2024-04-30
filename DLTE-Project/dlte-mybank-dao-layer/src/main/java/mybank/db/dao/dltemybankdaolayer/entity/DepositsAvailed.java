package mybank.db.dao.dltemybankdaolayer.entity;


import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;

public class DepositsAvailed {

//    @NotNull(message = "{deposit.bean.null}")
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

    public DepositsAvailed(Long depositAvailId, @NotNull(message = "{deposit.bean.null}") Long depositId, @NotNull(message = "{deposit.bean.null}") Long customerId, @NotNull(message = "{deposit.bean.null}") @Positive Double depositAmount, @NotNull(message = "{deposit.bean.null}") @Min(1) Integer depositDuration, @NotNull(message = "{deposit.bean.null}") Date depositMaturity) {
        this.depositAvailId = depositAvailId;
        this.depositId = depositId;
        this.customerId = customerId;
        this.depositAmount = depositAmount;
        this.depositDuration = depositDuration;
        this.depositMaturity = depositMaturity;
    }

    public Long getDepositAvailId() {
        return depositAvailId;
    }

    public void setDepositAvailId(Long depositAvailId) {
        this.depositAvailId = depositAvailId;
    }

    public Long getDepositId() {
        return depositId;
    }

    public void setDepositId(Long depositId) {
        this.depositId = depositId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Double getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(Double depositAmount) {
        this.depositAmount = depositAmount;
    }

    public Integer getDepositDuration() {
        return depositDuration;
    }

    public void setDepositDuration(Integer depositDuration) {
        this.depositDuration = depositDuration;
    }

    public Date getDepositMaturity() {
        return depositMaturity;
    }

    public void setDepositMaturity(Date depositMaturity) {
        this.depositMaturity = depositMaturity;
    }
}
