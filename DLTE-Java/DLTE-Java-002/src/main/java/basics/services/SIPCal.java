package basics.services;

import java.util.Scanner;

public class SIPCal {
    public static void main(String[] args) {
        //Intailization
        long investAmt;
        double cInterest;
        int years;

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Monthly Investment");
        investAmt=sc.nextLong();
        System.out.println(investAmt+"Enter Expected Interest");
        cInterest=sc.nextDouble();
        cInterest/=(12*100);
        System.out.println(" Enter Time Period");
        years=sc.nextInt();
        years*=12;
        System.out.println("Total Return "+(investAmt*((Math.pow((1+cInterest),years)-1)/cInterest)*(1+cInterest))); // SIP Formula

    }
}
