package exception.logs;

import java.util.Date;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerSupport {
    static ResourceBundle resourceBundle= ResourceBundle.getBundle("application");
    static Logger logger=Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    boolean found=false;
    public static void main(String[] args) {
    CreditCard[] myBank={
            new CreditCard(8765678765678L,"Razak Mohamed S",new Date(2034,12,30),555,100000,new Date(2024,3,11),new Date(2024,03,30),1111),
            new CreditCard(2343234345445L,"Shreyas",new Date(2029,1,4),134,50000,new Date(2024,3,2),new Date(2024,03,22),9999),
            new CreditCard(8765678764545L,"Sanath",new Date(2031,5,15),955,600000,new Date(2024,3,10),new Date(2024,03,11),9864),
            new CreditCard(1234565456767L,"Akash",new Date(2028,8,11),767,200000,new Date(2024,3,5),new Date(2024,03,29),1945),
    };

    CustomerSupport support=new CustomerSupport();
    Scanner input=new Scanner(System.in);
    logger.log(Level.INFO,resourceBundle.getString("members.init"));

    System.out.println("enter the credit card limit");
    int limit=input.nextInt();
    try {
        support.filter(myBank, limit);
    }catch (MyBankCreditCardException e){
        logger.log(Level.WARNING,resourceBundle.getString("exception.filter"),e);
    }


    System.out.println("enter the day of bill payment");
    int day=input.nextInt();
    try {
        support.billPaymentDay(myBank, day);
    }catch (MyBankCreditCardException e){

        logger.log(Level.WARNING,resourceBundle.getString("exception.billDate"),e);
    }


}

    public void billPaymentDay(CreditCard[] customer, int day) throws MyBankCreditCardException {
        for (CreditCard each: customer){
            if (each.getDateOfBillPayment().getDate()<=day){
                System.out.println("customer with bill payment day before "+day+" is"+each.getCreditCardHolder());
                found=true;
            }
        }
        if (!found){
            MyBankCreditCardException.billDateException();
        }
    }

    public void filter(CreditCard[] customers, int limit) throws MyBankCreditCardException {
        for (CreditCard each: customers){
            if (each.getCreditCardLimit()<=limit){
                System.out.println("credit card users with limit less than "+limit+" is"+each.getCreditCardHolder());
                found=true;
            }
        }
        if(!found){
            MyBankCreditCardException.filterException();
        }
    }
}
