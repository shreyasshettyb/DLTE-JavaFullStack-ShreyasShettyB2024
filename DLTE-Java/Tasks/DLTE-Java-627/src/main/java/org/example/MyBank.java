package org.example;

import java.io.IOException;
import java.util.ArrayList;

public interface MyBank{
    ArrayList<Loan> loanList = new ArrayList<>();
    void addLoans();
    String checkLoans();
    String checkClosedLoans();
}