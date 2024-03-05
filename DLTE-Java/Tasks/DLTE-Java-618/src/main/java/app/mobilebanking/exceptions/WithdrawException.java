package app.mobilebanking.exceptions;

import java.util.ResourceBundle;

public class WithdrawException extends RuntimeException{
    public WithdrawException() {
        super(ResourceBundle.getBundle("accounts").getString("accounts.wrong.password"));
    }
}
