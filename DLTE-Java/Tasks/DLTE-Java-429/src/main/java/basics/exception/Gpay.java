package basics.exception;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Gpay extends Account{
    //Initialization
    String billerName = "Shreyas", billerType = "SB";
    Integer A_Pin = 54210;
    // Validate MPIN
    public boolean validateMPin(Integer mpin) {
        if(A_Pin.equals(mpin))
            return true;
        else {
            return false;
        }

    }
    // Make Payment
    public void makePayment(Double paymentAmount){
        if(super.getAccountBalance()-paymentAmount>=0){
            super.setAccountBalance(super.getAccountBalance()-paymentAmount);
            System.out.println("Successfully Payment from "+getAccountNumber()+ "\nAvailable balance is "+getAccountBalance()+"Rs.");

        }else {
            System.out.println("Insufficient Balance !!!");
        }
    }

}

