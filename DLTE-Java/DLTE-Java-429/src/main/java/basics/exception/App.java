package basics.exception;

import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {
    public static void main(String[] args) throws MyBankException {
        Scanner scanner = new Scanner(System.in);
        Double paymentAmount;
        Integer mpin;
        int pinAttempts = 0;
        MyBankException myBankException = new MyBankException();
        ResourceBundle resourceBundle= ResourceBundle.getBundle("application");
        Gpay gpay = new Gpay();
        Logger logger=Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        // Menu
        while (true) {
            System.out.println("----------------Welcome to MyBank------------------");
                    System.out.println("Enter your MPIN");
                    mpin = scanner.nextInt();
                    if (gpay.validateMPin(mpin)) {
                        System.out.println("Enter Payment Amount");
                        paymentAmount = scanner.nextDouble();
                        gpay.makePayment(paymentAmount);
                        break;
                    } else {

                        pinAttempts++;
                        if (pinAttempts < 5) {
                            logger.log(Level.WARNING, resourceBundle.getString("exception.MpinWrong"));
                            System.out.println("Re-Enter Your MPIN");
                            continue;
                        } else {
                            logger.log(Level.SEVERE, myBankException.toString());
                            break;
                        }
                    }

        }
    }
}
