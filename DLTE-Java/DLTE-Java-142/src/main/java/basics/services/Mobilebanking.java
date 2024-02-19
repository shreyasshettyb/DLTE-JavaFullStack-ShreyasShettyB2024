package basics.services;

import java.util.Scanner;

public class Mobilebanking {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String name="",email="";
        long phone,bankAcc;
        int mpin;
        int attempts=0;
        int op=0;
        System.out.println("Welcome to My Bank Internet Banking");
        while (true){
            System.out.println("Enter Option\n1.Sign In\n2.Sign Up");
            op=sc.nextInt();
            switch (op){
                case 1:if (email.isEmpty()){
                    System.out.println("No Username found ,Please Register");
                    break;
                }
                    if(attempts>=5){
                        System.out.println("Maximum attempt crossed");
                        System.exit(0);
                    }
                    System.out.println("Enter Your Email");
                    String email1=sc.nextLine();
                    sc.next();
                    System.out.println("Enter mpin");
                    mpin=sc.nextInt();
                    System.out.println("Successfully Signed In! "+ name);
                    break;
                case 2:System.out.println("Enter Name");
                    name=sc.nextLine();
                    sc.nextLine();
                    System.out.println("Enter Email");
                    email=sc.nextLine();
                    System.out.println("Enter Phone");
                    phone=sc.nextLong();
                    System.out.println("Enter Bank Account Number");
                    bankAcc=sc.nextLong();
                    System.out.println("Enter mpin");
                    mpin = sc.nextInt();
                    System.out.println("Sucessfully Signed Up "+ name);

            }
        }


    }
}
