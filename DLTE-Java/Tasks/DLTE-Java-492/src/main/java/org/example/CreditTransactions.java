package org.example;
import org.example.CreditCard;

import java.util.Date;
import java.util.ResourceBundle;
import java.util.Scanner;

public class CreditTransactions implements Runnable {
    private CreditCard[] transactionsDetails;

    public CreditTransactions(CreditCard[] transactionsDetails) {
        this.transactionsDetails = transactionsDetails;
    }

    public static void main(String[] args) {
        CreditCard[] transactionsDetails = {
                new CreditCard(new Date(2023, 1, 25), 213121.0, "varun", "Bills"),
                new CreditCard(new Date(2022, 4, 15), 356.0, "elroy", "Education"),
                new CreditCard(new Date(2023, 6, 10), 353100.0, "rakesh", "Eduction"),
                new CreditCard(new Date(2022, 1, 27), 451500.0, "prashanth", "Family"),
                new CreditCard(new Date(2022, 2, 31), 1125007.0, "akash", "Education"),
                new CreditCard(new Date(2022, 3, 4), 1350.0, "sumanth", "Bills")
        };

        CreditTransactions creditTransactions = new CreditTransactions(transactionsDetails);
        Thread thread1 = new Thread(creditTransactions::filterDate);
        Thread thread2 = new Thread(creditTransactions::sortReciptant);
        Thread thread3 = new Thread(creditTransactions::minTransaction);
        Thread thread4 = new Thread(creditTransactions);
        Thread thread5 = new Thread(creditTransactions);
        Thread thread6 = new Thread(creditTransactions);
        Thread thread7 = new Thread(creditTransactions);
        Thread thread8 = new Thread(creditTransactions);
        Thread thread9 = new Thread(creditTransactions);
        Thread thread10 = new Thread(creditTransactions);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
        thread7.start();
        thread8.start();
        thread9.start();
        thread10.start();
    }

    @Override
    public void run() {
        Scanner input = new Scanner(System.in);
        ResourceBundle resourceBundle =ResourceBundle.getBundle("app");
        while (true) {
            System.out.println(resourceBundle.getString("credit.welcome"));
            System.out.println(resourceBundle.getString("credit.menu"));
            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    minTransaction();
                    break;
                case 2:
                    maxTransaction();
                    break;
                case 3:
                    System.out.println("Enter name");
                    String beneficiaryName = input.next();
                    numberOfTransactions(beneficiaryName);
                    break;
                case 4:
                    System.out.println("Enter remark");
                    String remark = input.next();
                    filterRemarks(remark);
                    break;
                case 5:
                    filterDate();
                case 6:
                    System.exit(0);
                    break;

            }
        }
    }

    public synchronized void filterDate() {
        for (CreditCard each : transactionsDetails) {
            if (each.getDateOfTransaction().after(new Date(2022, 1, 1)) && each.getDateOfTransaction().before(new Date(2023, 1, 31))) {
                System.out.println("Date of Transaction:" + each.getDateOfTransaction() +
                        "\nAmount transacted:" + each.getAmountInTransaction() +
                        "\nRecipient:" + each.getToRecipient() +
                        "\nRemarks:" + each.getRemarks());
            }
        }
    }

    public synchronized void sortReciptant() {

        int flag = 0;
        int size = transactionsDetails.length;

        do {
flag =0;
            for (int index = 0; index < size - 1; index++) {
                if (transactionsDetails[index].getToRecipient().compareTo(transactionsDetails[index + 1].getToRecipient()) < 0) {
                    CreditCard temp = transactionsDetails[index];
                    transactionsDetails[index] = transactionsDetails[index + 1];
                    transactionsDetails[index + 1] = temp;
                    flag = 1;
                }
            }
            size--;
        } while (flag==1);


        System.out.println("Sorted transactions in Descending order");
        for (CreditCard card : transactionsDetails) {
            System.out.println(card);
        }
    }

    public void numberOfTransactions(String name) {
        int count=0;
        for (CreditCard each: transactionsDetails){
            if(each.getToRecipient().equals(name)){
                count++;
            }
        }
        System.out.println("Number of times the transactions has been made to "+name+" is "+count);
    }

    public void filterRemarks(String remark) {
        double total = 0;
        for (CreditCard each : transactionsDetails) {
            if (each.getRemarks().equals(remark)) {
                total += each.getAmountInTransaction();
            }
        }
        System.out.println("Total amount transacted toward the remark " + remark + " is " + total);
    }

    public void minTransaction() {
        String name= "";
        double compare=Double.MAX_VALUE;
        for(CreditCard each: transactionsDetails){
            if(each.getAmountInTransaction()<compare){
                compare=each.getAmountInTransaction();
                name=each.getToRecipient();
            }
        }
        System.out.println("Minimum amount transferred to "+name+"="+compare);
    }

    public void maxTransaction() {
        String name= "";
        double compare=Double.MIN_VALUE;
        for(CreditCard each: transactionsDetails){
            if(each.getAmountInTransaction()>compare){
                compare=each.getAmountInTransaction();
                name=each.getToRecipient();
            }
        }
        System.out.println("Maximum amount transferred to "+name+"="+compare);
    }


}