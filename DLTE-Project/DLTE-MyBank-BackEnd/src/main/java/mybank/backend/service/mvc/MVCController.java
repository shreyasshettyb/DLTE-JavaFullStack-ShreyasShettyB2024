package mybank.backend.service.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MVCController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/nav")
    public String nav(){return "nav";}
}
