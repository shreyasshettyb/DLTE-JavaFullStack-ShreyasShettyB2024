package basics.services;

import java.util.Scanner;

public class minBalance {
    public static void main(String[] args) {
        //Intialization
        long balance[] = new long[20];
        Scanner sc = new Scanner(System.in);
        for(int i=0;i<20;i++){
            System.out.println("Enter "+i+"th Balance");
            balance[i]=sc.nextLong();
        }
        penalty(balance);
        System.out.println("Balance after adding penalty");
        for(long i:balance){
            System.out.println(i);
        }
    }

    //Penalty
    static void penalty(long bal[]){
        for(int i=0;i<20;i++){
            if(bal[i]<=10000){
                if(bal[i]>=5000 && bal[i]<=9999){
                    bal[i]+=(bal[i]*0.03);
                }
                else{
                    bal[i]+=(bal[i]*0.05);
                }
            }
        }
    }
}
