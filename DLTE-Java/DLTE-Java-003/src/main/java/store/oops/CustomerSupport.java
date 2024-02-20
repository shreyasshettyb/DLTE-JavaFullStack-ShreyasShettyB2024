package store.oops;

import java.util.Date;
import java.util.Scanner;

public class CustomerSupport {
    public static void main(String[] args) {
        //Intialization
        CreditCard[] myBank={
                new CreditCard(8765678765678L,"Prashanth",new Date(2034,12,30),555,100000,new Date(2024,3,11),new Date(2024,03,30),1111),
                new CreditCard(2343234345445L,"Shreyas",new Date(2029,1,4),134,50000,new Date(2024,3,2),new Date(2024,03,22),9999),
                new CreditCard(8765678764545L,"Elroy",new Date(2031,5,15),955,200000,new Date(2024,3,5),new Date(2024,03,11),9864),
                new CreditCard(7234565456767L,"Varun",new Date(2029,8,17),546,150000,new Date(2024,3,18),new Date(2024,04,05),2954),
                new CreditCard(9234565454667L,"Rakesh",new Date(2038,4,21),787,600000,new Date(2024,3,18),new Date(2024,04,25),2955),
                new CreditCard(4253565456767L,"Shreya",new Date(2025,7,16),797,200000,new Date(2024,3,18),new Date(2024,01,21),3947),
                new CreditCard(8534565456767L,"Akash",new Date(2028,7,17),417,120000,new Date(2024,3,18),new Date(2024,04,20),3965),
        };
        //Menu
        System.out.println("What do you want to do? Enter your choice\n 1.Analysis\n2.Updates");
        int option = new Scanner(System.in).nextInt();
        int subOption;
        CustomerSupport support=new CustomerSupport();
        //Submenu
        switch(option){
            case 1: System.out.println("What do you want to filter? Enter your choice\n 1.Filter based on the given limit\n2.Filter based on the date of bill payment");
                     subOption = new Scanner(System.in).nextInt();
                     support.filter(subOption,myBank);
                    break;
            case 2: System.out.println("What do you want to update? Enter your choice\n 1.Update specific PIN of customer\n2.Update the limit of customers those date of bill generation is 05th");
                     subOption = new Scanner(System.in).nextInt();
                     support.update(subOption,myBank);
                     break;
        }
    }
    //Filter
    public void filter(int subOption,CreditCard[] customers){
        if (subOption == 1) {
            System.out.println("Enter limt");
            long filterAmount=new Scanner(System.in).nextLong();
            for(CreditCard each:customers){
                if(each.getCreditCardLimit()<=filterAmount){
                    System.out.println(each.getCreditCardHolder()+" your credit card "+each.getCreditCardLimit());
                }
            }
        }
        else{
            System.out.println("Enter Date");
            int date=new Scanner(System.in).nextInt();
            for(CreditCard each:customers){
                if(each.getDateOfBillPayment().getDate()<=date){
                    System.out.println(each.getCreditCardHolder()+" your credit card bill payment date  "+each.getDateOfBillPayment());
                }
            }
        }
    }
    //update
    public void update(int subOption,CreditCard[] customers){
        if (subOption == 1) {
            System.out.println("Enter Card Number");
            long cardNumber = new Scanner(System.in).nextLong();
            System.out.println("Enter Old Pin");
            int pin = new Scanner(System.in).nextInt();
            for (CreditCard each : customers) {
                if (each.getCreditCardNumber() == cardNumber) {
                    if (each.getCreditCardPin() == pin) {
                        System.out.println("Enter New Pin");
                        int updatedPin = new Scanner(System.in).nextInt();
                        each.setCreditCardPin(updatedPin);
                        System.out.println("Pin Update Successful");
                    }
                }
            }
        }
        else{
            for (CreditCard each : customers) {
                if (each.getDateOfBillPayment().getDate() == 5) {
                    each.setCreditCardLimit((int) (each.getCreditCardLimit()+(each.getCreditCardLimit()*0.5)));
                    System.out.println("Congratulations "+each.getCreditCardHolder()+" your credit card limit as been increased to "+each.getCreditCardLimit());
                }
            }
        }
    }

}