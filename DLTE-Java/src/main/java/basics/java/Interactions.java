package basics.java;

import java.awt.*;
import java.util.Scanner;

//Commandline Interaction : Car Loan
/*
Personal details: name,address, aadhaar, pan, mobile,email
Income: salaried, self-employed itr

*/
public class Interactions {
    public static void main(String[] args) {
        String borrowerName="",borrowerPan="",borrowerAddress="",borrowerEmail="",borrowertype="";
        Long mobile=0L, aadhaar=0L;
        Scanner sc = new Scanner(System.in);
        System.out.println("-------------------Welcome to MyBank---------------");
        System.out.println("Fill your name");
        borrowerName=sc.nextLine();
        System.out.println("Fill your aadhaar number");
        aadhaar=sc.nextLong();
        System.out.println("Enter the PAN");
        borrowerPan=sc.next();
        System.out.println("Let us know you Income");
        borrowertype=sc.next();
        System.out.println("Mention the mobile number");
        mobile=sc.nextLong();
        System.out.println("Enter the email address");
        borrowerEmail=sc.next();
        System.out.println("Dear "+borrowerName+",Thanks for showing interest in taking the car loan Your application has been submited "+borrowerEmail+" "+mobile);
    }
}
