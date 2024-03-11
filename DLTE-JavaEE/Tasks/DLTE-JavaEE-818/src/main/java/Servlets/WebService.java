package Servlets;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebServlet("/transaction/")
public class WebService extends HttpServlet {
    List<Transaction> transactions = Stream.of(new Transaction(new Date(24,4,5),50000.0,"Shreyas","Family"),
            new Transaction(new Date(24,8,15),200000.0,"Akash","Education"),
            new Transaction(new Date(24,1,14),10000.0,"Varun","Emergency"),
            new Transaction(new Date(24,4,5),150000.0,"Prasanth","Bills"),
            new Transaction(new Date(24,2,25),40000.0,"Elroy","Friends")).collect(Collectors.toList());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("min")!=null && req.getParameter("max")!=null){
            double min = Double.parseDouble(req.getParameter("min"));
            double max = Double.parseDouble(req.getParameter("max"));
            String json= "";
            Gson gson=new Gson();
            resp.setContentType("application/json");
            resp.setStatus(HttpServletResponse.SC_OK);
            List<Transaction> filterTransaction =  transactions.stream().filter(each->each.getAmountInTransaction().compareTo(min)>=0 && each.getAmountInTransaction().compareTo(max)<=0).collect(Collectors.toList());
            json = gson.toJson(filterTransaction);
            resp.getWriter().println(json);
        }else{
            String json= "";
            Gson gson=new Gson();
            resp.setContentType("application/json");
            resp.setStatus(HttpServletResponse.SC_OK);
            json = gson.toJson(transactions);
            resp.getWriter().println(json);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson=new Gson();

        Transaction transaction = gson.fromJson(req.getReader(),Transaction.class);
        transactions.add(transaction);

        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().println("Transaction has been added into the records");
    }
}
