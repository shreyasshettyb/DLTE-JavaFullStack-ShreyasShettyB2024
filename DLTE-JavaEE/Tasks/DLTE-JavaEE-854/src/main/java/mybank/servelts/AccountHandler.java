package mybank.servelts;

import org.example.entity.Account;
import org.example.middleware.DatabaseTarget;
import org.example.remotes.StorageTarget;
import org.example.services.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

@WebServlet("/create")
public class AccountHandler extends HttpServlet {
    private AccountService accountService;
    private ResourceBundle resourceBundle;
    private Logger logger;

    @Override
    public void init() throws ServletException {
        StorageTarget storageTarget=new DatabaseTarget();
        accountService=new AccountService(storageTarget);
        resourceBundle = ResourceBundle.getBundle("mybank");
        logger = LoggerFactory.getLogger(AccountHandler.class);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long accountNumber = Long.parseLong(req.getParameter("accountNumber"));
        long customerId = Long.parseLong(req.getParameter("customerId"));
        String email = req.getParameter("email");
        String name = req.getParameter("name");
        double balance = Double.parseDouble(req.getParameter("balance"));
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Account bankAccount = new Account(accountNumber, customerId, email, name, balance, username, password);

        accountService.callAddTransactions(bankAccount);
        req.setAttribute("info", resourceBundle.getString("mybank.account.created"));

        RequestDispatcher dispatcher = req.getRequestDispatcher("newAccount.jsp");
        dispatcher.forward(req, resp);
    }
}
