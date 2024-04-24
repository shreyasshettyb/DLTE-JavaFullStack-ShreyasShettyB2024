package jsf.loans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ManagedBean
@SessionScoped
public class LoanService {
    private List<Loans> loanArrayList;

    public LoanService() {
        loanArrayList = new ArrayList<>();
        loanArrayList.add(new Loans(1231323L, 464641.0, "2023/06/11", "open", "varun", 91312131L));
        loanArrayList.add(new Loans(8656235L, 895622.0, "2023/01/10", "open", "prasanth", 9844124515L));
        loanArrayList.add(new Loans(36552415L, 8524112.0, "2022/03/20", "closed", "elroy", 845451222L));
        loanArrayList.add(new Loans(74445521L, 464641.0, "2023/02/16", "closed", "rakesh", 877745777L));
    }

//    public Date convertStringToDate(String dateString) {
//        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
//        try {
//            return format.parse(dateString);
//        } catch (ParseException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//
//    public String convertDateToString(Date date) {
//        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
//        return format.format(date);
//    }


    public List<Loans> getLoans() {
        return loanArrayList;
    }

    public void setLoans(List<Loans> loanArrayList) {
        this.loanArrayList = loanArrayList;
    }

    public void addLoans(Loans loan) {
        loanArrayList.add(loan);
        loan.setMessage("Loan Added Successfully");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Loan added", null));
    }

    public List<Loans> displayClosedLoans() {
        return loanArrayList.stream().filter(each -> each.getLoansStatus().equals("closed")).collect(Collectors.toList());
    }

    public void deleteLoans(Long loanNumber) {
        Loans loan = loanArrayList.stream().filter(each -> each.getLoansNumber().equals(loanNumber)).findAny().orElse(null);
        if (loan != null && !loan.getLoansStatus().equalsIgnoreCase("closed")) {
            loan.setLoansStatus("closed");
            loan.setMessage("Successfully deleted");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Loans closed", null));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed", null));
        }
    }
}
