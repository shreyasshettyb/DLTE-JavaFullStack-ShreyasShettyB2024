package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class App implements MyBank {
    private Long loanNumber;
    private Double loanAmount;
    private Integer loanDate;
    private String loanStatus, borrowerName;
    private Long borrowerContact;
    private static int numberLoans;
    private ArrayList<Loan> loanList = new ArrayList<>();
    private static int option;

    public static void main(String[] args) {
        App app = new App();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter option\n1.Add Loans\n2.Check Available Loans\n3.Check Closed Loans");
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Enter number of Loans");
                    numberLoans = scanner.nextInt();
                    app.addLoans();
                    break;
                case 2:
                    app.checkLoans();
                    break;
                case 3:
                    app.checkClosedLoans();
                    break;
            }
        }
    }

    @Override
    public void addLoans(){

        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < numberLoans; i++) {
            System.out.println("Enter Loan Amount");
            loanAmount = scanner.nextDouble();
            System.out.println("Enter Loan Number");
            loanNumber = scanner.nextLong();
            System.out.println("Enter Loan Date");
            loanDate = scanner.nextInt();
            System.out.println("Enter Loan Status");
            loanStatus = scanner.next();
            System.out.println("Enter Borrower Name");
            borrowerName = scanner.next();
            System.out.println("Enter Borrower Contact");
            borrowerContact = scanner.nextLong();
            loanList.add(new Loan(loanNumber, loanAmount, loanDate, loanStatus, borrowerName, borrowerContact));
        }
        System.out.println("Loans inserted Successfully");

    }


    @Override
    public String checkLoans() {
        try {
            for (Loan each : loanList) {
                if (each.getLoanStatus().equalsIgnoreCase("open")) {
                    System.out.println(each.toString());
                    return each.toString();
                }
            }

        } catch (Exception e) {
            return "";
        }
        return "";
    }

    @Override
    public String checkClosedLoans() {
        try {
            for (Loan each : loanList) {
                if (each.getLoanStatus().equalsIgnoreCase("closed")) {
                    System.out.println(each.toString());
                    return each.toString();
                }
            }
        } catch (Exception e) {
            return "";
        }
        return "";
    }
}