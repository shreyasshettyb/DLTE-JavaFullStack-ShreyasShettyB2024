package jsf.loans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class Loans {
    private Long loansNumber;
    private Double loansAmount;
    private String loansDate;
    private String loansStatus;
    private String borrowerName;
    private Long phone;
    private String message;

    public Loans() {
    }

    public Loans(Long loansNumber, Double loansAmount, String loansDate, String loansStatus, String borrowerName, Long phone) {
        this.loansNumber = loansNumber;
        this.loansAmount = loansAmount;
        this.loansDate = loansDate;
        this.loansStatus = loansStatus;
        this.borrowerName = borrowerName;
        this.phone = phone;
    }

    public Long getLoansNumber() {
        return loansNumber;
    }

    public void setLoansNumber(Long loansNumber) {
        this.loansNumber = loansNumber;
    }

    public Double getLoansAmount() {
        return loansAmount;
    }

    public void setLoansAmount(Double loansAmount) {
        this.loansAmount = loansAmount;
    }

    public String getLoansDate() {
        return loansDate;
    }

    public void setLoansDate(String loansDate) {
        this.loansDate = loansDate;
    }

    public String getLoansStatus() {
        return loansStatus;
    }

    public void setLoansStatus(String loansStatus) {
        this.loansStatus = loansStatus;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Loans{" +
                "loansNumber=" + loansNumber +
                ", loansAmount=" + loansAmount +
                ", loansDate='" + loansDate + '\'' +
                ", loansStatus='" + loansStatus + '\'' +
                ", borrowerName='" + borrowerName + '\'' +
                ", phone=" + phone +
                ", message='" + message + '\'' +
                '}';
    }
}
