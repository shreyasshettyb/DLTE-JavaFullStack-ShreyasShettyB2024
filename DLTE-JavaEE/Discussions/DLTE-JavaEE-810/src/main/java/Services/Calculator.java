package Services;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/calculator/")
public class Calculator extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        double principle = 0, interestRate = 0, annualIncome = 0;
        int period = 0;

        if (req.getParameter("principle") != null && req.getParameter("interest") != null && req.getParameter("period") != null) {

            principle = Double.parseDouble(req.getParameter("principle"));
            interestRate = Double.parseDouble(req.getParameter("interest"));
            period = Integer.parseInt(req.getParameter("period"));
            interestRate /= (12 * 100);
            period *= 12;
            resp.getWriter().println("SIP = " + (principle * ((Math.pow((1 + interestRate), period) - 1) / interestRate) * (1 + interestRate)));
        } else if (req.getParameter("income") != null) {
            annualIncome = Double.parseDouble(req.getParameter("income"));
            if (annualIncome >= 0 && annualIncome < 250000)
                resp.getWriter().println("Your Are exempt from any tax on both regime ");
            else if (annualIncome >= 250000 && annualIncome < 500000)
                resp.getWriter().println("Old Regime: " + (annualIncome * 0.05) + " New Regime: " + (annualIncome * 0.05));
            else if (annualIncome >= 500000 && annualIncome < 750000)
                resp.getWriter().println("Old Regime: " + (annualIncome * 0.20) + " New Regime: " + (annualIncome * 0.10));
            else if (annualIncome >= 750000 && annualIncome < 1000000)
                resp.getWriter().println("Old Regime: " + (annualIncome * 0.20) + " New Regime: " + (annualIncome * 0.15));
            else if (annualIncome >= 1000000 && annualIncome < 1250000)
                resp.getWriter().println("Old Regime: " + (annualIncome * 0.30) + " New Regime: " + (annualIncome * 0.20));
            else if (annualIncome >= 1250000 && annualIncome < 1500000)
                resp.getWriter().println("Old Regime: " + (annualIncome * 0.30) + " New Regime: " + (annualIncome * 0.25));
            else
                resp.getWriter().println("Old Regime: " + (annualIncome * 0.30) + " New Regime: " + (annualIncome * 0.30));
        }
    }
}
