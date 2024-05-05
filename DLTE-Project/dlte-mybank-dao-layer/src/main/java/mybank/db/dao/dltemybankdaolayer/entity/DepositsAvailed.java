package mybank.db.dao.dltemybankdaolayer.entity;


import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

public class DepositsAvailed {

    private Long depositAvailId;

    @NotNull(message = "{0xV001}")
    private Long depositId;

    private Long customerId;

    @NotNull(message = "{0xV001}")
    @Positive(message = "{0xV002}")
    private Double depositAmount;

    @NotNull(message = "{0xV001}")
    @Digits(integer = 2,fraction = 0,message = "{0xV004}")
    @Positive(message ="{0xV002}")
    private Integer depositDuration;

    @NotNull(message = "{0xV001}")
    @Pattern(regexp = "^\\d{2}-\\d{2}-\\d{4}$", message = "{0xV003}")
    private Date depositMaturity;

    public DepositsAvailed(Long depositAvailId, @NotNull(message = "{0xV001}") Long depositId, Long customerId, @NotNull(message = "{0xV001}") @Positive(message = "{0xV002}") Double depositAmount, @NotNull(message = "{0xV001}") @Digits(integer = 2, fraction = 0, message = "{0xV004}") @Positive(message = "{0xV002}") Integer depositDuration, @NotNull(message = "{0xV001}") @Pattern(regexp = "^\\d{2}-\\d{2}-\\d{4}$", message = "{0xV003}") Date depositMaturity) {
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
