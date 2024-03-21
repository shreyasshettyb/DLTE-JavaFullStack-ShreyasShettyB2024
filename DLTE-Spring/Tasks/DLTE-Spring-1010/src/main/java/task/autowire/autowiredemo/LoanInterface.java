package task.autowire.autowiredemo;

import java.util.List;

public interface LoanInterface {
    List<Loan> find(List<Loan> loans);
}
