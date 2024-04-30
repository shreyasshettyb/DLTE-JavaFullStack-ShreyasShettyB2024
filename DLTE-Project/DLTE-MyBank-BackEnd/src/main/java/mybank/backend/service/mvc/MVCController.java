package mybank.backend.service.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MVCController {

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


}
