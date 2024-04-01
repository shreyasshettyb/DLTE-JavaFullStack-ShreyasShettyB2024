package task.autowire.autowiredemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class MyBank {

    @Autowired
    @Qualifier("HomeLoans")
    LoanInterface loanInterface;
    public List<Loan> callFind(){
        List<Loan> loanList = new ArrayList<>();
        loanList.add(new Loan(741852963L, 741852.0, new Date(2024, Calendar.MARCH,12), "personal", "rakesh", 9638527410L));
        loanList.add(new Loan(789456123L, 963852.0, new Date(2024, Calendar.AUGUST,18), "home", "shreyas", 8529637410L));
        loanList.add(new Loan(963852741L, 74185296.0, new Date(2024, Calendar.JANUARY,12), "personal", "elroy", 9876543210L));
        loanList.add(new Loan(321654987L, 74185.0, new Date(2024, Calendar.MAY,12), "home", "varun", 7418529630L));
        return loanInterface.find(loanList);
    }

}
