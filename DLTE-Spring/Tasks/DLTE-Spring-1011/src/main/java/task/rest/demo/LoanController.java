package task.rest.demo;

import org.apache.el.stream.Stream;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoanController {
    List<Loan> loanList = new ArrayList<>();
    public LoanController(){
        loanList.add(new Loan(741852963L, 741852.0, new Date(2024, Calendar.MARCH,12), "personal", "rakesh", 9638527410L));
        loanList.add(new Loan(789456123L, 963852.0, new Date(2024, Calendar.AUGUST,18), "home", "shreyas", 8529637410L));
        loanList.add(new Loan(963852741L, 74185296.0, new Date(2024, Calendar.JANUARY,12), "personal", "elroy", 9876543210L));
        loanList.add(new Loan(321654987L, 74185.0, new Date(2024, Calendar.MAY,12), "home", "varun", 7418529630L));
    }

    @GetMapping("/view")
    public Loan getLoan( @RequestParam int index){
        return loanList.get(index);
    }

    @PostMapping("/add")
    public Loan addLoan(@RequestBody Loan loan){
        loanList.add(new Loan(loan.getLoanNumber(),loan.getLoanAmount(),loan.getLoanDate(),loan.getLoanStatus(),loan.getBorrowerName(),loan.getBorrowerContact()));
        return loan;
    }




}
