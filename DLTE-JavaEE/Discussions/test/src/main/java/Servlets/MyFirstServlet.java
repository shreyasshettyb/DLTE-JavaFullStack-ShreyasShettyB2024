package Servlets;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;


@WebServlet(name = "MyFirstServlet", value = "/first/api/")
public class MyFirstServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int principle = Integer.parseInt(req.getParameter("Principle"));
        int teneure = Integer.parseInt(req.getParameter("tenure"));
        double totalRepayment = 0.0,EMI=0.0;
        if(principle>=100000 && principle<300000)
            totalRepayment=principle+(principle*0.240);
        else if(principle>=100000 && principle<300000)
            totalRepayment=principle+(principle*0.250);
        else
            totalRepayment=principle+(principle*0.290);

        EMI = totalRepayment/teneure;

        resp.setContentType("application/json");
        Gson gson = new Gson();
        String message = gson.toJson(EMI);
//        resp.getWriter();
        resp.getWriter().println(message);
    }
}
