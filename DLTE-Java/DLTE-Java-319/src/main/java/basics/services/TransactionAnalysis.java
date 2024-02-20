package basics.services;

import java.util.Date;
import java.util.Scanner;

public class TransactionAnalysis {
    public static void main(String[] args) {
        //Intialization
        int option;
        Scanner scanner = new Scanner(System.in);
        Transaction[] transaction={new Transaction(new Date(24,4,5),50000.0,"Shreyas","Family"),
                new Transaction(new Date(24,8,15),200000.0,"Akash","Education"),
                new Transaction(new Date(24,1,14),10000.0,"Varun","Emergency"),
                new Transaction(new Date(24,4,05),150000.0,"Prasanth","Bills"),
                new Transaction(new Date(24,2,25),40000.0,"Elroy","Friends"),
        };
        //Menu
        System.out.println("Enter your Option\n1.Analysis\n2.Sorting");
        option = scanner.nextInt();
        TransactionAnalysis transactionAnalysis = new TransactionAnalysis();
        //submenu
        if(option==1){
            System.out.println("Enter your Option\n1.Filter based on given ranges of date\n2.least amount transferred\n3.maximum amount transferred\n4.number of transaction made to particular beneficiary\n5.filter based on particular remarks");
            int suboption=scanner.nextInt();
            switch (suboption){
                case 1:System.out.println("Enter Start Date");
                        int startDate = scanner.nextInt();
                    System.out.println("Enter Stop Date");
                        int stopDate = scanner.nextInt();
                    transactionAnalysis.filterDate(startDate,stopDate,transaction);
                        break;
                case 2:transactionAnalysis.leastAmount(transaction);
                        break;
                case 3:transactionAnalysis.maxAmount(transaction);
                        break;
                case 4:
                    System.out.println("Enter Beneficary Name");
                    String beneficiary = scanner.next();
                        transactionAnalysis.numberOfTransactionBeneficiary(beneficiary,transaction);
                        break;
                case 5:System.out.println("Enter the remarks");
                        String remarks = scanner.next();
                        transactionAnalysis.filterRemarks(remarks,transaction);
                        break;
            }
        }else if(option == 2){
            System.out.println("Enter your Option\n1.based on Beneficiary in descending\n2.based on amount in ascending");
            int suboption=scanner.nextInt();
            switch (suboption){
                case 1:transactionAnalysis.sortBeneficiary(transaction);
                        break;
                case 2:transactionAnalysis.sortAmount(transaction);
                        break;
            }
        }
    }
    public void filterDate(int startDate,int stopDate,Transaction transaction[]){
        for(Transaction each:transaction){
            if(each.getDateOfTransaction().getDate()>=startDate && each.getDateOfTransaction().getDate()<=stopDate ){
                System.out.println("Transaction sent to "+each.getTo()+" of amount "+each.getAmountInTransaction());
            }
        }
    }

    public void leastAmount(Transaction transaction[]){
        double min = transaction[0].getAmountInTransaction();
        for(Transaction each: transaction){
            if(each.getAmountInTransaction()<=min){
                min=each.getAmountInTransaction();
            }
        }
        System.out.println("Least Transaction amount is "+min);
    }
    public void maxAmount(Transaction transaction[]){
        double max = transaction[0].getAmountInTransaction();
        for(Transaction each: transaction){
            if(each.getAmountInTransaction()>=max){
                max=each.getAmountInTransaction();
            }
        }
        System.out.println("Maximum Transaction amount is "+max);
    }
    public void numberOfTransactionBeneficiary(String beneficiary,Transaction transaction[]){
        int count=0;
        for(Transaction each: transaction){
            if(each.getTo().equalsIgnoreCase(beneficiary)){
               count++;
            }
        }
        System.out.println(count);
    }
    public void filterRemarks(String remarks, Transaction transaction[]){
        for(Transaction each: transaction){
            if(each.getRemarks().equalsIgnoreCase(remarks)){
                System.out.println(each.toString());
            }
        }
    }

    public void sortBeneficiary(Transaction transaction[]){
        Transaction temp;
        for(int i=0;i<transaction.length-1;i++){
            for (int j=i+1;j<transaction.length;j++){
                if(transaction[i].getTo().compareTo(transaction[j].getTo())<0){
                    temp = transaction[i];
                    transaction[i]=transaction[j];
                    transaction[j]=temp;
                }
            }
        }
        for(Transaction each:transaction){
            System.out.println(each.toString());
        }
    }
    public void sortAmount(Transaction transaction[]){
        Transaction temp;
        for(int i=0;i<transaction.length-1;i++){
            for (int j=i+1;j<transaction.length;j++){
                if(transaction[i].getAmountInTransaction().compareTo(transaction[j].getAmountInTransaction())>0){
                    temp = transaction[i];
                    transaction[i]=transaction[j];
                    transaction[j]=temp;
                }
            }
        }
        for(Transaction each:transaction){
            System.out.println(each.toString());
        }
    }
}
