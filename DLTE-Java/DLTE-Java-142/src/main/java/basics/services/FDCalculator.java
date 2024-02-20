package basics.services;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class FDCalculator
{
    public static void main( String[] args )
    {
        //Initialization
        Scanner sc = new Scanner(System.in);
        int years;
        long principal;
        double interest;
        //Logic
        System.out.println("Hello,Welcome to Fd Calculator");
        System.out.println("Enter Principal Amount");
        principal=sc.nextLong();
        System.out.println("Enter rate of Interest");
        interest=sc.nextDouble();
        System.out.println("Enter time duration in years");
        years=sc.nextInt();
        System.out.println("Total interest is "+(principal*interest*years)/100);
        System.out.println("Maturity Amount is "+(principal+(principal*interest*years)/100));

    }
}
