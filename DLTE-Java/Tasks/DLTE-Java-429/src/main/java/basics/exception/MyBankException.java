package basics.exception;

public class MyBankException extends Exception{
    public MyBankException(){
        super("Maximum Attempts Exhausted.Account Locked,Please Visit your nearest branch.");
    }
}
