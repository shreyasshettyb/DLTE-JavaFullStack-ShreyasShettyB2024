package org.example;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TransactionAnalysis implements Runnable{
    Lock lock = new ReentrantLock();
    static Transaction[] transaction = {
            new Transaction(new Date(2023,12,9),31231.0,"Elroy","Bills"),
            new Transaction(new Date(2024,06,19),5415252.0,"Varun","Family"),
            new Transaction(new Date(2024,02,10),962000.0,"Prashanth","Education"),
            new Transaction(new Date(2022,04,29),413.0,"Raskesh","Bills"),
            new Transaction(new Date(2023,06,19),4621.0,"Akash","Friend")
    };

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);

        TransactionAnalysis analysis = new TransactionAnalysis();
        System.out.println("Transaction Analysis");
        System.out.println("Enter Your Choice\n1.Filter based on given ranges of date\n" +
                "2.least amount transferred\n" +
                "3.maximum amount transferred\n" +
                "4.number of transaction made to particular beneficiary\n" +
                "5.filter based on particular remarks\n"+"Press Any Other Key to Exit");
        int choice = scanner.nextInt();
        lock.lock();
        switch(choice){
            case 1: System.out.println("Enter the start date");
                int start = scanner.nextInt();
                System.out.println("Enter the stop date");
                int stop = scanner.nextInt();
                analysis.range(transaction,start,stop);
                break;
            case 2:
                analysis.leastAmountTransferred(transaction);
                break;
            case 3:
                analysis.maximumAmount(transaction);
                break;
            case 4:
                System.out.println("Enter the beneficiary name");
                String beneficiaryName = scanner.next();
                analysis.transactionBeneficiary(transaction,beneficiaryName);
                break;
            case 5:
                System.out.println("Enter the remarks name");
                String remarks = scanner.next();
                analysis.remarks(transaction,remarks);
                break;
            default:
                System.out.println("Invalid Identifier");
                break;
        }
        lock.unlock();
    }

    public void range(Transaction[] transaction,int start,int stop){
        for(Transaction each:transaction){
            if(each.getTransactionDate().getDate()>=start && each.getTransactionDate().getDate()<=stop){
                System.out.println(each.getTransactionDate()+" "+each.getAmountInTransaction()+" "+each.getSentTo()+" "+ each.getRemarks());
            }
        }
    }

    public void leastAmountTransferred(Transaction[] transaction){
        Double minAmount = Double.MAX_VALUE;
        for(Transaction each:transaction){
            if(each.getAmountInTransaction()<=minAmount){
                minAmount = each.getAmountInTransaction();
            }
        }
        System.out.println("The minimum transaction amount is "+minAmount);

    }
    public void maximumAmount(Transaction[] transaction){
        Double maxAmount = Double.MIN_VALUE;
        for(Transaction each:transaction){
            if(each.getAmountInTransaction()>=maxAmount){
                maxAmount = each.getAmountInTransaction();
            }
        }
        System.out.println("The minimum transaction amount is "+maxAmount);

    }

    public void transactionBeneficiary(Transaction[] transaction,String beneficiaryName){
        for(Transaction each: transaction){
            if(each.getSentTo().equalsIgnoreCase(beneficiaryName)){
                System.out.println("The amount returned is "+ each.getAmountInTransaction());
            }
        }
    }

    public  void remarks(Transaction[] transaction,String remarks){
        int flag=0;
        for(Transaction each:transaction){
            if(each.getRemarks().equalsIgnoreCase(remarks)){
                flag =1;
                System.out.println(each.toString());
            }
        }
        if(flag==0){
            System.out.println("No Transaction Made");
        }
    }


}