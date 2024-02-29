package technical.review;

public class Communication {
    private Long phone;
    private String email;

    public Communication(Long phone, String email) {
        this.phone = phone;
        this.email = email;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "\nPhone= " + phone +
                "\nEmail= " + email;
    }
}
