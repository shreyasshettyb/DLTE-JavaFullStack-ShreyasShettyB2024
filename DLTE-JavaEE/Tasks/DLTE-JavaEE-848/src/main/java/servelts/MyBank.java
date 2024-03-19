package servelts;

import com.google.gson.Gson;
import org.example.entity.Transaction;
import org.example.middleware.DatabaseTarget;
import org.example.middleware.UserDatabaseRepository;
import org.example.remotes.StorageTarget;
import org.example.remotes.UserRepository;
import org.example.services.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;


@WebServlet("/mybank")
public class MyBank extends HttpServlet {
   public StorageTarget storageTarget = new DatabaseTarget();
    public AccountService service = new AccountService(storageTarget);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StorageTarget storageTarget = new DatabaseTarget();
        AccountService service = new AccountService(storageTarget);
        if(req.getParameter("date")!=null && req.getParameter("username")!=null){
//            Gson gson = new Gson();
              List<Transaction> transactionList = service.callFindAllDate(Date.valueOf(req.getParameter("date")),req.getParameter("username"));
              for(Transaction each:transactionList) {
//                  gson.toJson(each.toString());
                  resp.getWriter().println(each.toString());
              }
        }else if(req.getParameter("username")!=null){
//            Gson gson = new Gson();
            List<Transaction> transactionList = service.callFindAllUser(req.getParameter("username"));
            for(Transaction each:transactionList) {
//                gson.toJson(each.toString());
                resp.getWriter().println(each.toString());
            }
        }else{
//            Gson gson = new Gson();
            List<Transaction> transactionList=  service.callFindAll();
            for(Transaction each:transactionList) {
//                gson.toJson(each.toString());
                resp.getWriter().println(each.toString());
            }
        }
    }

}
