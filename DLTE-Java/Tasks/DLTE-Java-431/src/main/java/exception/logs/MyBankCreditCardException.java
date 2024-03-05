package exception.logs;

import java.util.ResourceBundle;

public class MyBankCreditCardException extends RuntimeException {
    public MyBankCreditCardException(String application){
        super(ResourceBundle.getBundle("application").getString("exception.card"));
    }
    public static void filterException(){
        throw new MyBankCreditCardException(ResourceBundle.getBundle("application").getString("exception.filter1"));
    }
    public static void billDateException(){
        throw new MyBankCreditCardException(ResourceBundle.getBundle("application").getString("exception.billDate1"));
    }
}
