package thymeleaf.nav.task.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MVCController {
    @GetMapping("/index")
    public String home() {
        return "home";
    }

    @GetMapping("/listAllDeposits.html")
    public String listAll() {
        return "listAllDeposits";
    }

    @GetMapping("/form.html")
    public String avail() {
        return "form";
    }

}
