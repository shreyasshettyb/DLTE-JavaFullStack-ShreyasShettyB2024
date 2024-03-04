package task.collection;

import java.util.ArrayList;

public interface MyBank{
    ArrayList<Loan> loanList = new ArrayList<>();
    void addLoans();
    void checkLoans();
    void checkClosedLoans();
}