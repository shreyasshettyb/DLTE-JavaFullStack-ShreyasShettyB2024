package mybank.servelts;

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

@WebServlet("/withdrawal")
public class WithdrawalHandler extends HttpServlet {

    private AccountService accountService;
    private ResourceBundle resourceBundle;
    private Logger logger;

    @Override
    public void init()  {
        StorageTarget storageTarget=new DatabaseTarget();
        accountService=new AccountService(storageTarget);
        resourceBundle = ResourceBundle.getBundle("mybank");
        logger = LoggerFactory.getLogger(WithdrawalHandler.class);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        double withdrawAmount = Double.parseDouble(req.getParameter("withdrawAmount"));
        accountService.callWithdraw(username, password, withdrawAmount);
        RequestDispatcher dispatcher = req.getRequestDispatcher("withdraw.jsp");
        dispatcher.forward(req, resp);
    }
}
