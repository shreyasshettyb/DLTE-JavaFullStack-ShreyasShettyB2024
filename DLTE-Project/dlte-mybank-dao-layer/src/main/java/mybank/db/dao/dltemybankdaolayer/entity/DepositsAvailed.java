package mybank.db.dao.dltemybankdaolayer.entity;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;

public class DepositsAvailed {

    private Long depositAvailId;

    @NotNull(message = "{value.null}")
    private Long depositId;

    private Long customerId;

    @NotNull(message = "{value.null}")
    @Positive(message = "{0xV002}")
    private Double depositAmount;

    @NotNull(message = "{value.null}")
    @Positive(message = "{value.positive.zero}")
    private Integer depositDuration;

    @NotNull(message = "{value.null}")
//    @DateTimeFormat(pattern= "yyyy-MM-dd")
    private Date depositMaturity;

    public DepositsAvailed() {
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
