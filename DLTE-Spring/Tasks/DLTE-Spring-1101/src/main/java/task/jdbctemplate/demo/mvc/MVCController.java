package task.jdbctemplate.demo.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionException;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import task.jdbctemplate.demo.model.Transaction;
import task.jdbctemplate.demo.service.TransactionService;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
public class MVCController {
    @Autowired
    TransactionService transactionService;
    @GetMapping("/")
    public String landing(){
        return "index";
    }

    @GetMapping("/customer")
    public String customer(){
        return "customerDashboard";
    }

    @GetMapping("/admin")
    public String admin(){
        return "adminDashboard";
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String addTransactions(Model model) {
        model.addAttribute("transaction", new Transaction());
        return "newTransaction";
    }

    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public String deleteTransactions(Model model) {
        model.addAttribute("transaction", new Transaction());
        return "deleteTransactions";
    }

    @GetMapping("/removeBetweenDates")
    public String removeTransactionsBetweenDates(@RequestParam("startDate") String startDateString,
                                                 @RequestParam("endDate") String endDateString,
                                                 RedirectAttributes redirectAttributes,
                                                 Model model) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = dateFormat.parse(startDateString);
            Date endDate = dateFormat.parse(endDateString);
            String result = transactionService.apiRemoveTransactionByDates(startDate,endDate);
            redirectAttributes.addFlashAttribute("message", "Transaction Deleted successfully"+result);
            return "success";
        } catch (ParseException | TransactionException exception) {
            model.addAttribute("error", exception.getMessage());
            return "error";
        }
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String newTransaction(@Valid @ModelAttribute("transaction") Transaction transactionEntity,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes,
                                 @RequestParam("transactionDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date transactionDate) {
        if (bindingResult.hasErrors()) {
            return "newTransaction";
        }

        try {
            transactionEntity.setTransactionDate(transactionDate);
            transactionService.apiAddTransaction(transactionEntity);
            redirectAttributes.addFlashAttribute("message", "Transaction created successfully");
            return "success";
        } catch (TransactionException exception) {
            redirectAttributes.addFlashAttribute("error", exception.getMessage());
            return "error";
        }
    }

    @GetMapping("/findBySender")
    public String displayTransactionsBySender(@RequestParam("sender") String sender, Model model) {
        try {
            List<Transaction> transactions = transactionService.apiFindBySender(sender);
            model.addAttribute("transactions", transactions);
            return "displayTransaction"; // Return the name of the Thymeleaf template
        } catch (TransactionException exception) {
            model.addAttribute("error", exception.getMessage());
            return "error";
        }
    }

    @GetMapping("/findByReceiver")
    public String displayTransactionsByReceiver(@RequestParam("receiver") String receiver, Model model) {
        try {
            List<Transaction> transactions = transactionService.apiFindByReceiver(receiver);
            model.addAttribute("transactions", transactions);
            return "displayTransaction";
        } catch (TransactionException exception) {
            model.addAttribute("error", exception.getMessage());
            return "error";
        }
    }

    @GetMapping("/findByAmount")
    public String displayTransactionsByAmount(@RequestParam("amount") Double amount, Model model) {
        try {
            List<Transaction> transactions = transactionService.apiFindByAmount(amount);
            model.addAttribute("transactions", transactions);
            return "displayTransaction";
        } catch (TransactionException exception) {
            model.addAttribute("error", exception.getMessage());
            return "error";
        }
    }


}
