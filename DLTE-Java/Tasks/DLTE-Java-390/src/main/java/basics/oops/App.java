package basics.oops;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Double paymentAmount;
        Integer mpin, cardPin;
        int option;
        Gpay gpay = new Gpay();
        // Menu
        while (true) {
            System.out.println("----------------Welcome to MyBank------------------");
            System.out.println("Enter your choice\n1.Withdraw\n2.Make Payment");
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Enter your pin");
                    cardPin = scanner.nextInt();
                    if (gpay.validatePin(cardPin)) {
                        System.out.println("Enter Withdraw Amount");
                        Double withdrawAmount = scanner.nextDouble();
                        gpay.makeWithdraw(withdrawAmount);
                    } else {
                        System.out.println("Invalid Pin!!!!");
                    }
                    break;
                case 2:
                    System.out.println("Enter your pin");
                    mpin = scanner.nextInt();
                    if (gpay.validateMPin(mpin)) {
                        System.out.println("Enter Payment Amount");
                        paymentAmount = scanner.nextDouble();
                        gpay.makePayment(paymentAmount);
                    } else {
                        System.out.println("Invalid Pin!!!!");
                    }
                    break;
                default:break;
            }
        }
    }
}
