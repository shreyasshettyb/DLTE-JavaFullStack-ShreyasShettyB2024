package Parsing;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        List<Transaction> transactionList = new ArrayList();
        TransactionOperations transactionOperations = new TransactionOperations();
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("Menu\n1.Create transactions\n2.Filter transaction by username");
            int option = scanner.nextInt();
            switch (option){
                case 1:
                    do {
                        System.out.println("Enter Username");
                        String user = scanner.next();
                        System.out.println("Enter Amount");
                        double amount = scanner.nextDouble();
                        System.out.println("Enter Date");
                        String date = scanner.next();
                        transactionList.add(new Transaction(user,amount,date));
                        System.out.println("Add more transaction(yes/no)");
                    }while (scanner.next().equalsIgnoreCase("yes"));
                    transactionOperations.create(transactionList);
                    break;
                case 2:
                    System.out.println("Enter user name");
                    String user = scanner.next();
                    transactionOperations.filter(user);
            }
        }
    }
}
