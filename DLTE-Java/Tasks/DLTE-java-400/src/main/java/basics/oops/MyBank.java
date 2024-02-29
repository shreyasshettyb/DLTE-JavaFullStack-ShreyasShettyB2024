package basics.oops;

public interface MyBank{
    Loan loan[] = new Loan[25];
    void addLoans();
    void checkLoans();
    void checkClosedLoans();
}
