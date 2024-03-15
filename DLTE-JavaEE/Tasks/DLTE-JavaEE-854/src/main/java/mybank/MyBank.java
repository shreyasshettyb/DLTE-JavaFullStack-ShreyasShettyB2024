package mybank;

import com.google.gson.Gson;
import org.example.entity.Account;
import org.example.middleware.DatabaseTarget;
import org.example.remotes.StorageTarget;
import org.example.services.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

class Output {
    private String username;
    private String password;
    private double withdrawAmount;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public double getWithdrawAmount() {
        return withdrawAmount;
    }
}

@WebServlet("/mybank")
public class MyBank extends HttpServlet {
    AccountService service;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        resp.setContentType("application/json");
        Account account = service.callFindUserByUsername(username);
        Gson gson = new Gson();
        String allTransaction = gson.toJson(account);
        resp.getWriter().println(allTransaction);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
//            req.getReader().lines().forEach(System.out::println);
            Gson gson = new Gson();
            Output account = gson.fromJson(req.getReader(), Output.class);

            service.callWithdraw(account.getUsername(), account.getPassword(), account.getWithdrawAmount());
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void init() throws ServletException {
        StorageTarget storageTarget = new DatabaseTarget();
        service = new AccountService(storageTarget);
    }
}
