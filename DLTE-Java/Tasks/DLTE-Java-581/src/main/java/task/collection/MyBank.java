package task.collection;

import java.io.IOException;
import java.util.ArrayList;

public interface MyBank{
    ArrayList<Loan> loanList = new ArrayList<>();
    void addLoans() throws IOException;
    void checkLoans();
    void checkClosedLoans();
}