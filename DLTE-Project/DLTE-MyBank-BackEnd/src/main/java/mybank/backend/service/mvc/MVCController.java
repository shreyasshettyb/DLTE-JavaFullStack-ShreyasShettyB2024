package mybank.backend.service.mvc;

import mybank.db.dao.dltemybankdaolayer.entity.Customer;
import mybank.db.dao.dltemybankdaolayer.service.CustomerAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping
public class MVCController {

    @Autowired
    CustomerAuthService customerAuthService;
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
    public String error(){ return "exception";}

    @GetMapping("/calculator")
    public String calcultor(){return "calculator";}

    @GetMapping("/apply")
    public String availDeposit(){return "depositForm";}

    @GetMapping("/name")
    @ResponseBody
    public String customerName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        return customerAuthService.findByUsername(name).getCustomerName();
    }




}
