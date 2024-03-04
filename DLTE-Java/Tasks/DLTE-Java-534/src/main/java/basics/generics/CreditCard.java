package basics.generics;

import java.util.Date;

public class CreditCard {
    private Long cardNumber;
    private Integer cvv;
    private Integer pin;
    private Date cardExpiry;

    public CreditCard(Long cardNumber, Integer cvv, Integer pin, Date cardExpiry) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.pin = pin;
        this.cardExpiry = cardExpiry;
    }

    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Integer getCvv() {
        return cvv;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }

    public Integer getPin() {
        return pin;
    }

    public void setPin(Integer pin) {
        this.pin = pin;
    }

    public Date getCardExpiry() {
        return cardExpiry;
    }

    public void setCardExpiry(Date cardExpiry) {
        this.cardExpiry = cardExpiry;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "cardNumber=" + cardNumber +
                ", cvv=" + cvv +
                ", pin=" + pin +
                ", cardExpiry=" + cardExpiry +
                '}';
    }
}
