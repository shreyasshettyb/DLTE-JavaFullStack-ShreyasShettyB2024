package task.autowire.autowiredemo;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component("HomeLoans")
public class HomeLoanImplementation implements LoanInterface {

    @Override
    public List<Loan> find(List<Loan> loans) {
        return loans.stream().filter(each->each.getLoanStatus().equalsIgnoreCase("home")).collect(Collectors.toList());
    }
}
