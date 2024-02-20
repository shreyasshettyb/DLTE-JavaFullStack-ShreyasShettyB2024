package basics.services;

import java.util.Scanner;

public class InternetBanking {
    public static void main(String[] args) {
        //Initialization
        Scanner sc = new Scanner(System.in);
        String name="",email="",password="";
        long phone,bankAcc;
        int attempts=0;
        int op=0;
        //Logic
        System.out.println("Welcome to My Bank Internet Banking");
        while (true){
            System.out.println("Enter Option\n1.Sign In\n2.Sign Up");
            op=sc.nextInt();
            switch (op){
                case 1:if (email.isEmpty()){
                    System.out.println("No Username found ,Please Register");
                    break;
                }
                        if(attempts>=3){
                            System.out.println("Maximum attempt crossed");
                            System.exit(0);
                        }
                        System.out.println("Enter Your Email");
                        String email1=sc.nextLine();
                        sc.next();
                        System.out.println("Enter Password");
                        String pass1=sc.next();
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
                    System.out.println("Enter Password");
                    password = sc.next();
                    System.out.println("Sucessfully Signed Up "+ name);

            }
        }


    }
}
