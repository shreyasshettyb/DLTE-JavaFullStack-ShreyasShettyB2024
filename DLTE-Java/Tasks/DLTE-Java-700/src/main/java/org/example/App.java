package org.example;

import org.example.Loan;
import org.example.MyBank;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class App  {
    public static void main(String[] args) {
        ArrayList<Loan> loanArrayList = new ArrayList<>();
        loanArrayList.add(new Loan(1231323L,464641.0,new Date(2023,05,10),"open","varun",91312131L));
        loanArrayList.add(new Loan(8656235L,895622.0,new Date(2022,8,15),"open","prasanth",9844124515L));
        loanArrayList.add(new Loan(36552415L,8524112.0,new Date(2022,05,14),"closed","elroy",845451222L));
        loanArrayList.add(new Loan(74445521L,464641.0,new Date(2023,05,21),"closed","rakesh",877745777L));

        MyBank myBank = (start,end)->{
            for(Loan each: loanArrayList){
                if(each.getLoanDate().before(start) && each.getLoanDate().after(end)){
                    System.out.println(each.toString());
                }
            }
        };

        myBank.filter(new Date(2023,04,1),new Date(2022,04,01));
    }

}