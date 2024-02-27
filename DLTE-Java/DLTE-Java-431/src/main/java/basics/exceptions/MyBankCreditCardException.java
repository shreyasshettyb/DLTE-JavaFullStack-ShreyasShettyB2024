package basics.exceptions;

public class MyBankCreditCardException extends Exception{
    public MyBankCreditCardException(String msg){
        super(msg.toString());
    }
}
