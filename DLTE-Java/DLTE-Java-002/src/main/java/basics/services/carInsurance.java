package basics.services;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class carInsurance {
    public static void main(String[] args)
    {
        //Intialization
        String borrowerName="", borrowerPan="", borrowerAddress="", borrowerEmail="", borrowerIncomeType="",mobileNumber,aadhaar;
        Scanner scanner=new Scanner(System.in);

        System.out.println("-----------------Welcome to MyBank--------------------");
        System.out.println("Fill your name ");
        borrowerName=scanner.nextLine();
        Pattern pattern = Pattern.compile("^[a-zA-Z]+[a-zA-Z]+$");
        Matcher matcher = pattern.matcher(borrowerName);
        while (!validate(borrowerName, pattern)){
            System.out.println("!Invalid Name.Retype Name ");
            borrowerName=scanner.nextLine();
        }
        System.out.println("Fill your aadhaar number");
        aadhaar=scanner.nextLine();
        pattern = Pattern.compile("^\\d{12}$");
        matcher = pattern.matcher(aadhaar);
        while (!validate(aadhaar, pattern)){
            System.out.println("!Invalid Aadhaar.Retype Aadhaar ");
            aadhaar=scanner.nextLine();
        }
        System.out.println("Enter the PAN ");
        borrowerPan= scanner.next();
        pattern = Pattern.compile("^[A-Z]{5}[0-9]{4}[A-Z]$");
        matcher = pattern.matcher(borrowerPan);
        while (!validate(borrowerPan, pattern)){
            System.out.println(borrowerPan+" !Invalid PAN.Retype PAN ");
            scanner.next();
            borrowerPan=scanner.nextLine();
        }
        System.out.println("Let us know Income type(Salaried/self employed)");
        scanner.next();
        borrowerIncomeType= scanner.nextLine();
        System.out.println("Mention the mobile number ");
//        scanner.next();
        mobileNumber=scanner.nextLine();
        pattern = Pattern.compile("^\\d{10}$");
        matcher = pattern.matcher(mobileNumber);
        while (!validate(mobileNumber, pattern)){
            System.out.println("!Invalid Mobile Number.Retype Mobile Number ");
//            scanner.next();
            mobileNumber=scanner.nextLine();
        }
        System.out.println("Enter the Email address");
//        scanner.next();
        borrowerEmail= scanner.next();
        pattern = Pattern.compile("[A-Za-z0-9]+@[A-Za-z]+\\.[]A-Za-z]{2,}");
        matcher = pattern.matcher(borrowerEmail);
        while (!validate(borrowerEmail, pattern)){
            System.out.println("!Invalid Email.Retype Email ");
            scanner.nextLine();
            borrowerEmail=scanner.nextLine();
        }
        System.out.println("Dear "+borrowerName+" Thanks for showing interest on taking car loan in MyBank your application has submitted and further details will be mailed to you "+borrowerEmail+" or SMS to "+mobileNumber);
    }

    //Validation Funtion
    static boolean validate(String x,Pattern pattern){
        Matcher matcher = pattern.matcher(x);
        return matcher.matches();
    }
}
