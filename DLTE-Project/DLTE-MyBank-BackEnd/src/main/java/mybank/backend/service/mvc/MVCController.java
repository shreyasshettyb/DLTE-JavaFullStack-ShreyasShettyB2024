package mybank.backend.service.mvc;

import mybank.db.dao.dltemybankdaolayer.entity.DepositsAvailable;
import mybank.db.dao.dltemybankdaolayer.exception.DepositsException;
import mybank.db.dao.dltemybankdaolayer.remotes.MyBankRemote;
import mybank.db.dao.dltemybankdaolayer.service.CustomerAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;
import java.util.List;

@Controller
public class MVCController {

    @Autowired
    CustomerAuthService customerAuthService;

    @Autowired
    MyBankRemote myBankRemote;

    Logger logger = LoggerFactory.getLogger(MVCController.class);

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login/")
    public String redirectLogin() {
        return "index";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @GetMapping("/view")
    public String viewDeposits() {
        return "viewDeposits";
    }

    @GetMapping("/error")
    public String errorPage() {
        return "error";
    }

    @GetMapping("/apply")
    public String availDeposit(@RequestParam String depositName, Model model) {
        try {
            List<DepositsAvailable> deposits = myBankRemote.availableDeposits();
            DepositsAvailable depositsAvailable = deposits.stream().filter(each -> each.getDepositName().equals(depositName)).findAny().orElseThrow(() -> new DepositsException("No Deposits found with the name " + depositName));
            model.addAttribute("depositId", depositsAvailable.getDepositId());
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return "error?code=500&msg=Server Error";
        } catch (DepositsException e) {
            logger.error(e.getMessage());
            return "error?code=404&msg=Could not find Deposit Information";
        }
        return "depositForm";
    }

    @GetMapping("/name")
    @ResponseBody
    public String customerName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        return customerAuthService.findByUsername(name).getCustomerName();
    }
}
