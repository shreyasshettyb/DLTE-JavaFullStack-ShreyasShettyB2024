package mybank.backend.service.mvc;

import mybank.db.dao.dltemybankdaolayer.entity.Customer;
import mybank.db.dao.dltemybankdaolayer.entity.DepositsAvailable;
import mybank.db.dao.dltemybankdaolayer.exception.DepositsException;
import mybank.db.dao.dltemybankdaolayer.remotes.MyBankRemote;
import mybank.db.dao.dltemybankdaolayer.service.CustomerAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping
public class MVCController {

    @Autowired
    CustomerAuthService customerAuthService;

    @Autowired
    MyBankRemote myBankRemote;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/login/")
    public String redirectLogin(){
        return "index";
    }

    @GetMapping("/dashboard")
    public String dashboard(){return "dashboard";}

    @GetMapping("/view")
    public String viewDeposits(){ return "viewDeposits";}

    @GetMapping("/error")
    public String errorPage(){ return "error";}

    @GetMapping("/calculator")
    public String calculator(){return "calculator";}

    @GetMapping("/apply")
    public String availDeposit(@RequestParam String depositName,Model model){
        try {
            List<DepositsAvailable> deposits = myBankRemote.availableDeposits();
            DepositsAvailable depositsAvailable=deposits.stream().filter(each -> each.getDepositName().equals(depositName)).findAny().orElseThrow(()->new DepositsException("No Deposits found with the name "+depositName));
            model.addAttribute("depositId",depositsAvailable.getDepositId());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DepositsException e) {
            e.printStackTrace();
        }
        return "depositForm";
    }

    @GetMapping("/name")
    @ResponseBody
    public String customerName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        return customerAuthService.findByUsername(name).getCustomerName();
    }




}
