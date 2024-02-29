package basics.services;

import java.util.Scanner;

public class PersonalLoan {
    public static void main( String[] args )
    {
        //Initialization
        Scanner sc = new Scanner(System.in);
        int years;
        long principal;
        double interest;
        //Logic
        System.out.println("Hello,Welcome to Personal Loan calculator");
        System.out.println("Enter Principal Amount");
        principal=sc.nextLong();
        System.out.println("Enter rate of Interest");
        interest=sc.nextDouble();
        System.out.println("Enter time duration in years");
        years=sc.nextInt();
        System.out.println("Emi is "+((principal*interest*years)/100)/12); // Simple Interest

    }
}
