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

public class App implements MyBank {
    public static void main(String[] args) {
        ArrayList<Loan> loanArrayList = new ArrayList(new Loan(131351531L, 5000.0, "2024-03-06", "Approved",
                "Varun", 9876543210L), new Loan(131351531L, 5000.0, "2024-03-06", "Approved",
                "Varun", 9876543210L), new Loan(131351531L, 5000.0, "2024-03-06", "Approved",
                "Varun", 9876543210L));
    }

}