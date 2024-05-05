package mybank.db.dao.dltemybankdaolayer.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotNull;
import java.util.Collection;

public class Customer implements UserDetails {
    @NotNull(message = "{0xV001}")
    private Long customerId;
    @NotNull(message = "{0xV001}")
    private String customerName;
    @NotNull(message = "{0xV001}")
    private String customerAddress;
    @NotNull(message = "{0xV001}")
    private String customerStatus;
    @NotNull(message = "{0xV001}")
    private Long customerContact;
    @NotNull(message = "{0xV001}")
    private String username;
    @NotNull(message = "{0xV001}")
    private String password;
    @NotNull(message = "{0xV001}")
    private Integer attempts;
    private final int maxAttempt = 3;


    public Customer() {
    }

    public Customer(@NotNull Long customerId, @NotNull String customerName, @NotNull String customerAddress, @NotNull String customerStatus, @NotNull Long customerContact, @NotNull String username, @NotNull String password, @NotNull Integer attempts) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerStatus = customerStatus;
        this.customerContact = customerContact;
        this.username = username;
        this.password = password;
        this.attempts = attempts;
    }

    public int getMaxAttempt() {
        return maxAttempt;
    }

    public Integer getAttempts() {
        return attempts;
    }

    public void setAttempts(Integer attempts) {
        this.attempts = attempts;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(String customerStatus) {
        this.customerStatus = customerStatus;
    }

    public Long getCustomerContact() {
        return customerContact;
    }

    public void setCustomerContact(Long customerContact) {
        this.customerContact = customerContact;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
