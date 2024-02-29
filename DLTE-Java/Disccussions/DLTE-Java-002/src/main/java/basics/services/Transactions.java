package basics.services;

import java.util.Scanner;

public class Transactions {
    public static void main(String[] args) {
        //Initialization
        int dCount = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter 1st balance");
        long temp = sc.nextLong();
        //Logic
        for(int i=1;i<10;i++) {
            System.out.println("Enter " + (i + 1) + " balance");
            long temp2 = sc.nextLong();
            if(temp2<temp){
                dCount++;
            }
            temp=temp2;
        }
        System.out.println("Total Number of Debit Transactions are "+dCount);

    }
}
