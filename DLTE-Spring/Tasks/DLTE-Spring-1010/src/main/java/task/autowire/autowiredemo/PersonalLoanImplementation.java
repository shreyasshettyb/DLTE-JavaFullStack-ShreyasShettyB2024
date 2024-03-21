package task.autowire.autowiredemo;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("PersonalLoans")
public class PersonalLoanImplementation implements LoanInterface {

    @Override
    public List<Loan> find(List<Loan> loans) {
        return loans.stream().filter(each->each.getLoanStatus().equalsIgnoreCase("personal")).collect(Collectors.toList());
    }
}
